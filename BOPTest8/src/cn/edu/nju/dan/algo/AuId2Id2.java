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

public class AuId2Id2 {
	private final static int LIST_TYPE_SRC_AUTHOR = 0;
	private final static int LIST_TYPE_DST_REFBY = 1;

	private final static int SRC_AUTHOR_PAPERS_DONE = 1;
	private final static int DST_PAPER_REFERERS_DONE = 2;
	private final static int DST_AUTHORS_PAPERS_DONE = 3;
	private final static int COMMON_AA_DONE = 4;

	public static JSONArray getPath4AuId2Id(final long srcAuthorId, final Entity dst) throws Exception {

		final Vector<Integer> finished = new Vector<>();
		final List<Entity> srcAuthorPapers = new ArrayList<>();
		final List<Entity> dstPaperReferers = new ArrayList<>();
		final List<Entity> dstAuthorsPapers = new ArrayList<>();
		final List<Entity> commonAAs = new ArrayList<>();

		new Thread(new Runnable() {
			public void run() {
				try {
					srcAuthorPapers.addAll(BOPEntityManager.getSrcAuthorPapers4AI(srcAuthorId));
					finished.add(SRC_AUTHOR_PAPERS_DONE);
				} catch (Exception e) {
				}
			}
		}).run();
		new Thread(new Runnable() {
			public void run() {
				try {
					dstPaperReferers.addAll(BOPEntityManager.getDstPaperReferers4AI(dst.entityId));
					finished.add(DST_PAPER_REFERERS_DONE);
				} catch (Exception e) {
				}
			}
		}).run();
		new Thread(new Runnable() {
			public void run() {
				try {
					dstAuthorsPapers.addAll(BOPEntityManager.getDstAuthorsPapers4AI(dst.authors));
					finished.add(DST_AUTHORS_PAPERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		List<Path> pathResult = new ArrayList<>();

		// A: AuId->Id
		if (getAuId2Id(srcAuthorId, dst))
			pathResult.add(new Path(srcAuthorId, dst.entityId));

		while (!(finished.contains(SRC_AUTHOR_PAPERS_DONE) || finished.contains(DST_PAPER_REFERERS_DONE)))
			;

		if (finished.contains(SRC_AUTHOR_PAPERS_DONE)) {

			final HashSet<Long> srcAuthorAfIds = Utils.getAfId(srcAuthorId, srcAuthorPapers);
			final HashSet<Long> dstAuthors = Utils.getAuIds(dst);
			new Thread(new Runnable() {
				public void run() {
					try {
						commonAAs.addAll(BOPEntityManager.getByAfIdsAndAuIds4AI(srcAuthorAfIds, dstAuthors));
						finished.add(COMMON_AA_DONE);
					} catch (Exception e) {

					}

				}
			}).run();

			// B: AuId->Id->Id
			pathResult.addAll(getAuId2Id2Id(srcAuthorId, dst, srcAuthorPapers, LIST_TYPE_SRC_AUTHOR));

			// D: AuId->Id->(AuId|CId|FId|JId)->Id
			pathResult.addAll(getAuId2Id2ACFJ2Id(srcAuthorId, dst, srcAuthorPapers));

			while (!(finished.contains(DST_PAPER_REFERERS_DONE) || finished.contains(DST_AUTHORS_PAPERS_DONE)
					|| finished.contains(COMMON_AA_DONE)))
				;

			if (finished.contains(DST_PAPER_REFERERS_DONE)) {

				// C: AuId->Id->Id->Id
				pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

				while (!(finished.contains(DST_AUTHORS_PAPERS_DONE) || finished.contains(COMMON_AA_DONE)))
					;

				if (finished.contains(DST_AUTHORS_PAPERS_DONE)) {

					// E: AuId->AfId->AuId->Id
					pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));
				} else {

					// E: AuId->AfId->AuId->Id
					pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, commonAAs));
				}
			} else {
				if (finished.contains(DST_AUTHORS_PAPERS_DONE)) {

					// E: AuId->AfId->AuId->Id
					pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));
				} else {

					// E: AuId->AfId->AuId->Id
					pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, commonAAs));
				}

				while (!finished.contains(DST_PAPER_REFERERS_DONE))
					;

				// C: AuId->Id->Id->Id
				pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

			}
		} else {

			// B: AuId->Id->Id
			pathResult.addAll(getAuId2Id2Id(srcAuthorId, dst, dstPaperReferers, LIST_TYPE_DST_REFBY));

			while (!finished.contains(SRC_AUTHOR_PAPERS_DONE))
				;

			final HashSet<Long> srcAuthorAfIds = Utils.getAfId(srcAuthorId, srcAuthorPapers);
			final HashSet<Long> dstAuthors = Utils.getAuIds(dst);
			new Thread(new Runnable() {
				public void run() {
					try {
						commonAAs.addAll(BOPEntityManager.getByAfIdsAndAuIds4AI(srcAuthorAfIds, dstAuthors));
						finished.add(COMMON_AA_DONE);
					} catch (Exception e) {

					}

				}
			}).run();

			// C: AuId->Id->Id->Id
			pathResult.addAll(getAuId2Id2Id2Id(srcAuthorId, dst, srcAuthorPapers, dstPaperReferers));

			// D: AuId->Id->(AuId|CId|FId|JId)->Id
			pathResult.addAll(getAuId2Id2ACFJ2Id(srcAuthorId, dst, srcAuthorPapers));

			while (!(finished.contains(DST_AUTHORS_PAPERS_DONE) || finished.contains(COMMON_AA_DONE)))
				;
			if (finished.contains(DST_AUTHORS_PAPERS_DONE)) {

				// E: AuId->AfId->AuId->Id
				pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, srcAuthorPapers, dstAuthorsPapers));
			} else {

				// E: AuId->AfId->AuId->Id
				pathResult.addAll(getAuId2AfId2AuId2Id(srcAuthorId, dst, commonAAs, commonAAs));
			}

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
