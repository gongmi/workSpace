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

public class AuId2AuId {

	private final static int LIST_TYPE_SRC_AUTHOR_PAPERS = 0;
	private final static int LIST_TYPE_DST_AUTHOR_PAPERS = 1;

	public static JSONArray getPath4AuId2AuId(final long srcAuthorId, final long dstAuthorId) throws Exception {

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<List<Entity>> getSrcAuthorPapersFuture = executor.submit(new AAGetSrcAuthorPapersThread(srcAuthorId));
		Future<List<Entity>> getDstAuthorPapersFuture = executor.submit(new AAGetDstAuthorPapersThread(dstAuthorId));
		executor.shutdown();

		List<Path> pathResult = new ArrayList<>();

		final List<Entity> srcAuthorPapers;
		final List<Entity> dstAuthorPapers;
		while (!(getSrcAuthorPapersFuture.isDone() || getDstAuthorPapersFuture.isDone()))
			;

		if (getSrcAuthorPapersFuture.isDone()) {

			srcAuthorPapers = getSrcAuthorPapersFuture.get();

			// A: AuId->Id->AuId
			pathResult.addAll(getAuId2Id2AuId(srcAuthorId, dstAuthorId, srcAuthorPapers, LIST_TYPE_SRC_AUTHOR_PAPERS));

			while (!getDstAuthorPapersFuture.isDone())
				;
			dstAuthorPapers = getDstAuthorPapersFuture.get();
		} else {
			dstAuthorPapers = getDstAuthorPapersFuture.get();

			// A: AuId->Id->AuId
			pathResult.addAll(getAuId2Id2AuId(srcAuthorId, dstAuthorId, dstAuthorPapers, LIST_TYPE_DST_AUTHOR_PAPERS));

			while (!getSrcAuthorPapersFuture.isDone())
				;
			srcAuthorPapers = getSrcAuthorPapersFuture.get();
		}

		// B: AuId->AfId->AuId
		pathResult.addAll(getAuId2AfId2AuId(srcAuthorId, dstAuthorId, srcAuthorPapers, dstAuthorPapers));

		// C: AuId->Id->Id->AuId
		pathResult.addAll(getAuId2Id2Id2AuId(srcAuthorId, dstAuthorId, srcAuthorPapers, dstAuthorPapers));

		JSONArray finalResult = new JSONArray();
		for (Path path : pathResult) {
			finalResult.put(path.toJSONArray());
		}
		return finalResult;
	}

	public static HashSet<Path> getAuId2Id2AuId(long srcAuthorId, long dstAuthorId, List<Entity> entityList,
			int listType) {
		HashSet<Path> paths = new HashSet<>();

		if (listType == LIST_TYPE_SRC_AUTHOR_PAPERS) {
			for (Entity entity : entityList) {
				if (entity.authors != null) {
					for (Author author : entity.authors) {
						if (author.authorId == dstAuthorId) {
							paths.add(new Path(srcAuthorId, entity.entityId, dstAuthorId));
						}
					}
				}
			}
		} else if (listType == LIST_TYPE_DST_AUTHOR_PAPERS) {
			for (Entity entity : entityList) {
				if (entity.authors != null) {
					for (Author author : entity.authors) {
						if (author.authorId == srcAuthorId) {
							paths.add(new Path(srcAuthorId, entity.entityId, dstAuthorId));
						}
					}
				}
			}
		}

		return paths;
	}

	public static List<Path> getAuId2AfId2AuId(long srcAuthorId, long dstAuthorId, List<Entity> srcAuthorPapers,
			List<Entity> dstAuthorPapers) {
		List<Path> paths = new ArrayList<>();
		HashSet<Long> srcAfIds = Utils.getAfId(srcAuthorId, srcAuthorPapers);
		HashSet<Long> dstAfIds = Utils.getAfId(dstAuthorId, dstAuthorPapers);
		if (srcAfIds != null && srcAfIds.size() > 0) {
			for (Long srcAfId : srcAfIds) {
				if (dstAfIds.contains(srcAfId)) {
					paths.add(new Path(srcAuthorId, srcAfId, dstAuthorId));
				}
			}
		}
		return paths;
	}

	public static List<Path> getAuId2Id2Id2AuId(long srcAuthorId, long dstAuthorId, List<Entity> srcAuthorPapers,
			List<Entity> dstAuthorPapers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> dstIds = Utils.getIds(dstAuthorPapers);
		for (Entity entity : srcAuthorPapers) {
			if (entity.references != null) {
				for (Reference ref : entity.references) {
					if (dstIds.contains(ref.referenceId)) {
						paths.add(new Path(srcAuthorId, entity.entityId, ref.referenceId, dstAuthorId));
					}
				}
			}
		}
		return paths;
	}

}

class AAGetSrcAuthorPapersThread implements Callable<List<Entity>> {
	private long srcAuthorId;

	public AAGetSrcAuthorPapersThread(long srcAuthorId) {
		this.srcAuthorId = srcAuthorId;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getSrcAuthorPapers4AA(srcAuthorId);
		} catch (Exception e) {
		}

		return entityList;

	}
}

class AAGetDstAuthorPapersThread implements Callable<List<Entity>> {
	private long dstAuthorId;

	public AAGetDstAuthorPapersThread(long dstAuthorId) {
		this.dstAuthorId = dstAuthorId;
	}

	@Override
	public List<Entity> call() throws Exception {
		List<Entity> entityList = new ArrayList<>();
		try {
			entityList = BOPEntityManager.getDstAuthorPapers4AA(dstAuthorId);
		} catch (Exception e) {
		}
		return entityList;
	}
}
