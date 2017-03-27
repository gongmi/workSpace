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
import cn.edu.nju.dan.entities.Field;
import cn.edu.nju.dan.entities.Reference;

public class Id2AuId {
	private final static int LIST_TYPE_SRC_PAPER_REFS = 0;
	private final static int LIST_TYPE_DST_AUTHOR_PAPERS = 1;

	public static JSONArray getPath4Id2AuId(final Entity src, final long dstAuthorId) throws Exception {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<List<Entity>> getSrcPaperRefsFuture = executor.submit(new IAGetSrcPaperRefsThread(src));
		Future<List<Entity>> getDstAuthorPapersFuture = executor.submit(new IAGetDstAuthorPapersThread(dstAuthorId));
		Future<List<Entity>> getSrcAuthorsPapersFuture = executor.submit(new IAGetSrcAuthorsPapersThread(src));
		executor.shutdown();

		List<Path> pathResult = new ArrayList<>();

		// A: Id->AuId
		if (getId2AuId(src, dstAuthorId))
			pathResult.add(new Path(src.entityId, dstAuthorId));

		final List<Entity> srcPaperRefs;
		final List<Entity> dstAuthorPapers;
		final List<Entity> srcAuthorsPapers;
		while (!(getSrcPaperRefsFuture.isDone() || getDstAuthorPapersFuture.isDone()))
			;

		if (getSrcPaperRefsFuture.isDone()) {

			srcPaperRefs = getSrcPaperRefsFuture.get();

			// B: Id->Id->AuId
			pathResult.addAll(getId2Id2AuId(src, dstAuthorId, srcPaperRefs, LIST_TYPE_SRC_PAPER_REFS));

			while (!getDstAuthorPapersFuture.isDone())
				;
			dstAuthorPapers = getDstAuthorPapersFuture.get();

			// C: Id->Id->Id->AuId
			pathResult.addAll(getId2Id2Id2AuId(src, dstAuthorId, srcPaperRefs, dstAuthorPapers));

			// E: Id->(AuId|CId|FId|JId)->Id->Id
			pathResult.addAll(getId2ACFJ2Id2AuId(src, dstAuthorId, dstAuthorPapers));

			while (!getSrcAuthorsPapersFuture.isDone())
				;
			srcAuthorsPapers = getSrcAuthorsPapersFuture.get();

			// D: Id->AuId->AfId->AuId
			pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));
		} else {
			dstAuthorPapers = getDstAuthorPapersFuture.get();

			// B: Id->Id->AuId
			pathResult.addAll(getId2Id2AuId(src, dstAuthorId, dstAuthorPapers, LIST_TYPE_DST_AUTHOR_PAPERS));

			// E: ID->(AuId|CId|FId|JId)->Id->AuId
			pathResult.addAll(getId2ACFJ2Id2AuId(src, dstAuthorId, dstAuthorPapers));

			while (!(getSrcPaperRefsFuture.isDone() || getSrcAuthorsPapersFuture.isDone()))
				;

			if (getSrcPaperRefsFuture.isDone()) {

				srcPaperRefs = getSrcPaperRefsFuture.get();

				// C: Id->Id->Id->AuId
				pathResult.addAll(getId2Id2Id2AuId(src, dstAuthorId, srcPaperRefs, dstAuthorPapers));

				while (!getSrcAuthorsPapersFuture.isDone())
					;
				srcAuthorsPapers = getSrcAuthorsPapersFuture.get();

				// D: Id->AuId->AfId->AuId
				pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));
			} else {
				srcAuthorsPapers = getSrcAuthorsPapersFuture.get();

				// D: Id->AuId->AfId->AuId
				pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));

				while (!getSrcPaperRefsFuture.isDone())
					;
				srcPaperRefs = getSrcPaperRefsFuture.get();

				// C: Id->Id->Id->AuId
				pathResult.addAll(getId2Id2Id2AuId(src, dstAuthorId, srcPaperRefs, dstAuthorPapers));
			}
		}

		JSONArray finalResult = new JSONArray();
		for (Path path : pathResult) {
			finalResult.put(path.toJSONArray());
		}
		return finalResult;

	}

	public static List<Path> getId2Id2AuId(Entity src, long dstAuthorId, List<Entity> entityList, int listType) {
		List<Path> paths = new ArrayList<>();

		if (listType == LIST_TYPE_SRC_PAPER_REFS) {
			for (Entity srcRefPaper : entityList) {
				if (srcRefPaper.authors != null) {
					for (Author author : srcRefPaper.authors) {
						if (author.authorId == dstAuthorId) {
							paths.add(new Path(src.entityId, srcRefPaper.entityId, dstAuthorId));
						}
					}
				}
			}
		} else if (listType == LIST_TYPE_DST_AUTHOR_PAPERS) {
			HashSet<Long> ids = Utils.getIds(entityList);
			if (src.references != null) {
				for (Reference ref : src.references) {
					if (ids.contains(ref.referenceId)) {
						paths.add(new Path(src.entityId, ref.referenceId, dstAuthorId));
					}
				}
			}
		}

		return paths;
	}

	public static List<Path> getId2Id2Id2AuId(Entity src, long dstAuthorId, List<Entity> srcPaperRefs,
			List<Entity> dstAuthorPapers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> ids = Utils.getIds(dstAuthorPapers);

		for (Entity entity : srcPaperRefs) {
			if (entity.references != null) {
				for (Reference ref : entity.references) {
					if (ids.contains(ref.referenceId)) {
						paths.add(new Path(src.entityId, entity.entityId, ref.referenceId, dstAuthorId));
					}
				}
			}
		}
		return paths;
	}

	public static List<Path> getId2ACFJ2Id2AuId(Entity src, long dstAuthorId, List<Entity> dstAuthorPapers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> srcAuIds = Utils.getAuIds(src);
		HashSet<Long> srcFIds = Utils.getFIds(src);

		final boolean shouldCheckAuId = (srcAuIds.size() > 0);
		final boolean shouldCheckCId = (src.conferenceSeriesId != 0) ? true : false;
		final boolean shouldCheckFId = (srcFIds.size() > 0);
		final boolean shouldCheckJId = (src.journalId != 0) ? true : false;

		for (Entity dstEntity : dstAuthorPapers) {
			if (shouldCheckAuId) {
				if (dstEntity.authors != null) {
					for (Author author : dstEntity.authors) {
						if (srcAuIds.contains(author.authorId)) {
							paths.add(new Path(src.entityId, author.authorId, dstEntity.entityId, dstAuthorId));
						}
					}
				}
			}
			if (shouldCheckCId) {
				if (src.conferenceSeriesId == dstEntity.conferenceSeriesId) {
					paths.add(new Path(src.entityId, src.conferenceSeriesId, dstEntity.entityId, dstAuthorId));
				}
			}
			if (shouldCheckFId) {
				if (dstEntity.fields != null) {
					for (Field field : dstEntity.fields) {
						if (srcFIds.contains(field.fieldOfStudyId)) {
							paths.add(new Path(src.entityId, field.fieldOfStudyId, dstEntity.entityId, dstAuthorId));
						}
					}
				}
			}

			if (shouldCheckJId) {
				if (src.journalId == dstEntity.journalId) {
					paths.add(new Path(src.entityId, src.journalId, dstEntity.entityId, dstAuthorId));
				}
			}
		}
		return paths;
	}

	public static List<Path> getId2AuId2AfId2AuId(Entity src, long dstAuthorId, List<Entity> srcAuthorsPapers,
			List<Entity> dstAuthorPapers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> dstAfIds = Utils.getAfId(dstAuthorId, dstAuthorPapers);
		if (dstAfIds == null || dstAfIds.size() == 0)
			return paths;

		for (Author author : src.authors) {
			List<Entity> srcAuthorEntityList = Utils.getAuthorEntityList(author.authorId, srcAuthorsPapers);
			HashSet<Long> srcAfIds = Utils.getAfId(author.authorId, srcAuthorEntityList);
			for (Long srcAfId : srcAfIds) {
				if (dstAfIds.contains(srcAfId)) {
					paths.add(new Path(src.entityId, author.authorId, srcAfId, dstAuthorId));
				}
			}
		}
		return paths;
	}

	public static boolean getId2AuId(Entity src, long dstAuthorId) {
		for (Author srcAuthor : src.authors) {
			if (srcAuthor.authorId == dstAuthorId) {
				return true;
			}
		}
		return false;
	}
}

class IAGetSrcPaperRefsThread implements Callable<List<Entity>> {
	private Entity src;

	public IAGetSrcPaperRefsThread(Entity src) {
		this.src = src;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		if (src.references != null) {
			try {
				entityList = BOPEntityManager.getSrcPaperRefs4IA(src.references);
			} catch (Exception e) {
			}
		}
		return entityList;
	}
}

class IAGetDstAuthorPapersThread implements Callable<List<Entity>> {
	private long dstAuthorId;

	public IAGetDstAuthorPapersThread(long dstAuthorId) {
		this.dstAuthorId = dstAuthorId;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getDstAuthorPapers4IA(dstAuthorId);
		} catch (Exception e) {
		}
		return entityList;
	}
}

class IAGetSrcAuthorsPapersThread implements Callable<List<Entity>> {
	private Entity src;

	public IAGetSrcAuthorsPapersThread(Entity src) {
		this.src = src;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getSrcAuthorsPapers4IA(src.authors);
		} catch (Exception e) {
		}
		return entityList;
	}
}
