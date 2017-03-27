package cn.edu.nju.dan.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONArray;

import cn.edu.nju.dan.bll.BOPEntityManager;
import cn.edu.nju.dan.common.Path;
import cn.edu.nju.dan.common.Utils;
import cn.edu.nju.dan.entities.Author;
import cn.edu.nju.dan.entities.Entity;
import cn.edu.nju.dan.entities.Reference;

public class AuId2Id {
	private final static int LIST_TYPE_SRC_AUTHOR = 0;
	private final static int LIST_TYPE_DST_REFBY = 1;

	public static JSONArray getPath4AuId2Id(final long srcAuthorId, final Entity dst) throws Exception {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<List<Entity>> getSrcAuthorPapersFuture = executor.submit(new AIGetSrcAuthorPapersThread(srcAuthorId));
		Future<List<Entity>> getDstPaperReferersFuture = executor.submit(new AIGetDstPaperReferersThread(dst));
		Future<List<Entity>> getDstAuthorsPapersFuture = executor.submit(new AIGetDstAuthorsPapersThread(dst));
		executor.shutdown();

		List<Path> pathResult = new ArrayList<>();

		// A: AuId->Id
		if (getAuId2Id(srcAuthorId, dst))
			pathResult.add(new Path(srcAuthorId, dst.entityId));

		final List<Entity> srcAuthorPapers;
		final List<Entity> dstPaperReferers;
		final List<Entity> dstAuthorsPapers;
		while (!(getSrcAuthorPapersFuture.isDone() || getDstPaperReferersFuture.isDone()))
			;

		if (getSrcAuthorPapersFuture.isDone()) {

			srcAuthorPapers = getSrcAuthorPapersFuture.get();

			// B: AuId->Id->Id
			pathResult.addAll(getAuId2Id2Id(srcAuthorId, dst, srcAuthorPapers, LIST_TYPE_SRC_AUTHOR));

			// D: AuId->Id->(AuId|CId|FId|JId)->Id
			pathResult.addAll(getAuId2Id2ACFJ2Id(srcAuthorId, dst, srcAuthorPapers));

			while (!(getDstPaperReferersFuture.isDone() || getDstAuthorsPapersFuture.isDone()))
				;

			if (getDstPaperReferersFuture.isDone()) {
				dstPaperReferers = getDstPaperReferersFuture.get();

				// C: AuId->Id->Id->Id
				pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

				while (!getDstAuthorsPapersFuture.isDone())
					;
				dstAuthorsPapers = getDstAuthorsPapersFuture.get();

				// E: AuId->AfId->AuId->Id
				pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));

			} else {

				dstAuthorsPapers = getDstAuthorsPapersFuture.get();

				// E: AuId->AfId->AuId->Id
				pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));

				while (!getDstPaperReferersFuture.isDone())
					;
				dstPaperReferers = getDstPaperReferersFuture.get();

				// C: AuId->Id->Id->Id
				pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

			}
		} else {

			dstPaperReferers = getDstPaperReferersFuture.get();

			// B: AuId->Id->Id
			pathResult.addAll(getAuId2Id2Id(srcAuthorId, dst, dstPaperReferers, LIST_TYPE_DST_REFBY));

			while (!getSrcAuthorPapersFuture.isDone())
				;
			srcAuthorPapers = getSrcAuthorPapersFuture.get();

			// C: AuId->Id->Id->Id
			pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

			// D: AuId->Id->(AuId|CId|FId|JId)->Id
			pathResult.addAll(getAuId2Id2ACFJ2Id(srcAuthorId, dst, srcAuthorPapers));

			while (!getDstAuthorsPapersFuture.isDone())
				;
			dstAuthorsPapers = getDstAuthorsPapersFuture.get();

			// E: AuId->AfId->AuId->Id
			pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));
		}

		JSONArray fianlResult = new JSONArray();
		for (Path path : pathResult) {
			fianlResult.put(path.toJSONArray());
		}
		return fianlResult;

	}

	public static List<Path> getAuId2Id2ACFJ2Id(long srcAuthorId, Entity dst, List<Entity> srcAuthorPapers) {
		List<Path> paths = new ArrayList<>();
		// ACFJ = (AuId|CId|FId|JId)
		for (Entity entity : srcAuthorPapers) {
			List<Path> midPath = Id2Id.getId2ACFJ2Id(entity, dst);
			for (Path path : midPath) {
				paths.add(new Path(srcAuthorId, entity.entityId, path.get(1), dst.entityId));
			}
		}
		return paths;
	}

	public static List<Path> getAuId2Id2Id2Id(long srcAuthorId, Entity dst, List<Entity> srcAuthorPapers,
			List<Entity> dstPaperReferers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> ids = Utils.getIds(dstPaperReferers);
		for (Entity entity : srcAuthorPapers) {
			if (entity.references != null) {
				for (Reference ref : entity.references) {
					if (ids.contains(ref.referenceId)) {
						paths.add(new Path(srcAuthorId, entity.entityId, ref.referenceId, dst.entityId));
					}
				}
			}
		}
		return paths;
	}

	public static List<Path> getAuId2AfId2AuId2Id(long srcAuthorId, Entity dst, List<Entity> srcAuthorPapers,
			List<Entity> dstAuthorsPapers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> srcAfIds = Utils.getAfId(srcAuthorId, srcAuthorPapers);
		if (srcAfIds == null || srcAfIds.size() == 0)
			return paths;

		for (Author author : dst.authors) {
			List<Entity> dstAuthorEntityList = Utils.getAuthorEntityList(author.authorId, dstAuthorsPapers);
			HashSet<Long> dstAfIds = Utils.getAfId(author.authorId, dstAuthorEntityList);
			for (Long srcAfId : srcAfIds) {
				if (dstAfIds.contains(srcAfId)) {
					paths.add(new Path(srcAuthorId, srcAfId, author.authorId, dst.entityId));
				}
			}
		}
		return paths;
	}

	public static boolean getAuId2Id(long srcAuthorId, Entity dst) {
		for (Author dstAuthor : dst.authors) {
			if (dstAuthor.authorId == srcAuthorId) {
				return true;
			}
		}
		return false;
	}

	public static List<Path> getAuId2Id2Id(long srcAuthorId, Entity dst, List<Entity> entityList, int listType) {
		List<Path> paths = new ArrayList<>();

		if (listType == LIST_TYPE_SRC_AUTHOR) {
			for (Entity entity : entityList) {
				if (entity.references != null) {
					for (Reference ref : entity.references) {
						if (ref.referenceId == dst.entityId) {
							paths.add(new Path(srcAuthorId, entity.entityId, dst.entityId));
						}
					}
				}
			}
		} else if (listType == LIST_TYPE_DST_REFBY) {
			for (Entity entity : entityList)
				if (entity.authors != null) {
					for (Author author : entity.authors) {
						if (author.authorId == srcAuthorId) {
							paths.add(new Path(srcAuthorId, entity.entityId, dst.entityId));
						}
					}
				}
		}
		return paths;
	}
}

class AIGetSrcAuthorPapersThread implements Callable<List<Entity>> {
	private long srcAuthorId;

	public AIGetSrcAuthorPapersThread(long srcAuthorId) {
		this.srcAuthorId = srcAuthorId;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getSrcAuthorPapers4AI(srcAuthorId);
		} catch (Exception e) {
		}

		return entityList;
	}
}

class AIGetDstPaperReferersThread implements Callable<List<Entity>> {
	private Entity dst;

	public AIGetDstPaperReferersThread(Entity dst) {
		this.dst = dst;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getDstPaperReferers4AI(dst.entityId);
		} catch (Exception e) {
		}
		return entityList;
	}
}

class AIGetDstAuthorsPapersThread implements Callable<List<Entity>> {
	private Entity dst;

	public AIGetDstAuthorsPapersThread(Entity dst) {
		this.dst = dst;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getDstAuthorsPapers4AI(dst.authors);
		} catch (Exception e) {
		}
		return entityList;
	}
}