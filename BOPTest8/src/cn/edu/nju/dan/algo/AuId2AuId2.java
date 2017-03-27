package cn.edu.nju.dan.algo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

import org.json.JSONArray;

import cn.edu.nju.dan.bll.BOPEntityManager;
import cn.edu.nju.dan.common.Path;
import cn.edu.nju.dan.common.Utils;
import cn.edu.nju.dan.entities.Author;
import cn.edu.nju.dan.entities.Entity;
import cn.edu.nju.dan.entities.Reference;

public class AuId2AuId2 {

	private final static int LIST_TYPE_SRC_AUTHOR_PAPERS = 0;
	private final static int LIST_TYPE_DST_AUTHOR_PAPERS = 1;

	private final static int SRC_AUTHOR_PAPERS_DONE = 1;
	private final static int DST_AUTHOR_PAPERS_DONE = 2;

	public static JSONArray getPath4AuId2AuId(final long srcAuthorId, final long dstAuthorId) throws Exception {

		final Vector<Integer> finished = new Vector<>();
		final List<Entity> srcAuthorPapers = new ArrayList<>();
		final List<Entity> dstAuthorPapers = new ArrayList<>();

		new Thread(new Runnable() {
			public void run() {
				try {
					srcAuthorPapers.addAll(BOPEntityManager.getSrcAuthorPapers4AA(srcAuthorId));
					finished.add(SRC_AUTHOR_PAPERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		new Thread(new Runnable() {
			public void run() {
				try {
					dstAuthorPapers.addAll(BOPEntityManager.getDstAuthorPapers4AA(dstAuthorId));
					finished.add(DST_AUTHOR_PAPERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		List<Path> pathResult = new ArrayList<>();

		while (!(finished.contains(SRC_AUTHOR_PAPERS_DONE) || finished.contains(DST_AUTHOR_PAPERS_DONE)))
			;

		if (finished.contains(SRC_AUTHOR_PAPERS_DONE)) {

			// A: AuId->Id->AuId
			pathResult.addAll(getAuId2Id2AuId(srcAuthorId, dstAuthorId, srcAuthorPapers, LIST_TYPE_SRC_AUTHOR_PAPERS));

			while (!finished.contains(DST_AUTHOR_PAPERS_DONE))
				;

		} else {

			// A: AuId->Id->AuId
			pathResult.addAll(getAuId2Id2AuId(srcAuthorId, dstAuthorId, dstAuthorPapers, LIST_TYPE_DST_AUTHOR_PAPERS));

			while (!finished.contains(SRC_AUTHOR_PAPERS_DONE))
				;
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
