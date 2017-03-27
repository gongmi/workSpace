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
import cn.edu.nju.dan.entities.Field;
import cn.edu.nju.dan.entities.Reference;

public class Id2AuId2 {
	private final static int LIST_TYPE_SRC_PAPER_REFS = 0;
	private final static int LIST_TYPE_DST_AUTHOR_PAPERS = 1;

	private final static int SRC_PAPER_REFS_DONE = 1;
	private final static int DST_AUTHOR_PAPERS_DONE = 2;
	private final static int SRC_AUTHORS_PAPERS_DONE = 3;
	private final static int COMMON_AA_DONE = 4;

	public static JSONArray getPath4Id2AuId(final Entity src, final long dstAuthorId) throws Exception {

		final Vector<Integer> finished = new Vector<>();
		final List<Entity> srcPaperRefs = new ArrayList<>();
		final List<Entity> dstAuthorPapers = new ArrayList<>();
		final List<Entity> srcAuthorsPapers = new ArrayList<>();
		final List<Entity> commonAAs = new ArrayList<>();

		new Thread(new Runnable() {
			public void run() {
				try {
					srcPaperRefs.addAll(BOPEntityManager.getSrcPaperRefs4IA(src.references));
					finished.add(SRC_PAPER_REFS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();
		new Thread(new Runnable() {
			public void run() {
				try {
					dstAuthorPapers.addAll(BOPEntityManager.getDstAuthorPapers4IA(dstAuthorId));
					finished.add(DST_AUTHOR_PAPERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();
		new Thread(new Runnable() {
			public void run() {
				try {
					srcAuthorsPapers.addAll(BOPEntityManager.getSrcAuthorsPapers4IA(src.authors));
					finished.addElement(SRC_AUTHORS_PAPERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		List<Path> pathResult = new ArrayList<>();

		// A: Id->AuId
		if (getId2AuId(src, dstAuthorId))
			pathResult.add(new Path(src.entityId, dstAuthorId));

		while (!(finished.contains(SRC_PAPER_REFS_DONE) || finished.contains(DST_AUTHOR_PAPERS_DONE)))
			;

		if (finished.contains(SRC_PAPER_REFS_DONE)) {

			// B: Id->Id->AuId
			pathResult.addAll(getId2Id2AuId(src, dstAuthorId, srcPaperRefs, LIST_TYPE_SRC_PAPER_REFS));

			while (!finished.contains(DST_AUTHOR_PAPERS_DONE))
				;

			final HashSet<Long> dstAuthorAfIds = Utils.getAfId(dstAuthorId, dstAuthorPapers);
			final HashSet<Long> srcAuthors = Utils.getAuIds(src);
			new Thread(new Runnable() {
				public void run() {
					try {
						commonAAs.addAll(BOPEntityManager.getByAfIdsAndAuIds4AI(dstAuthorAfIds, srcAuthors));
						finished.add(COMMON_AA_DONE);
					} catch (Exception e) {

					}

				}
			}).run();

			// C: Id->Id->Id->AuId
			pathResult.addAll(getId2Id2Id2AuId(src, dstAuthorId, srcPaperRefs, dstAuthorPapers));

			// E: Id->(AuId|CId|FId|JId)->Id->Id
			pathResult.addAll(getId2ACFJ2Id2AuId(src, dstAuthorId, dstAuthorPapers));

			while (!(finished.contains(SRC_AUTHORS_PAPERS_DONE) || finished.contains(COMMON_AA_DONE)))
				;

			if (finished.contains(SRC_AUTHORS_PAPERS_DONE)) {

				// D: Id->AuId->AfId->AuId
				pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));
			} else {
				pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, commonAAs, dstAuthorPapers));
			}

		} else {

			final HashSet<Long> dstAuthorAfIds = Utils.getAfId(dstAuthorId, dstAuthorPapers);
			final HashSet<Long> srcAuthors = Utils.getAuIds(src);
			new Thread(new Runnable() {
				public void run() {
					try {
						commonAAs.addAll(BOPEntityManager.getByAfIdsAndAuIds4AI(dstAuthorAfIds, srcAuthors));
						finished.add(COMMON_AA_DONE);
					} catch (Exception e) {

					}

				}
			}).run();

			// B: Id->Id->AuId
			pathResult.addAll(getId2Id2AuId(src, dstAuthorId, dstAuthorPapers, LIST_TYPE_DST_AUTHOR_PAPERS));

			// E: ID->(AuId|CId|FId|JId)->Id->AuId
			pathResult.addAll(getId2ACFJ2Id2AuId(src, dstAuthorId, dstAuthorPapers));

			while (!(finished.contains(SRC_PAPER_REFS_DONE) || finished.contains(SRC_AUTHORS_PAPERS_DONE)
					|| finished.contains(COMMON_AA_DONE)))
				;

			if (finished.contains(SRC_PAPER_REFS_DONE)) {

				// C: Id->Id->Id->AuId
				pathResult.addAll(getId2Id2Id2AuId(src, dstAuthorId, srcPaperRefs, dstAuthorPapers));

				while (!(finished.contains(SRC_AUTHORS_PAPERS_DONE) || finished.contains(COMMON_AA_DONE)))
					;

				if (finished.contains(SRC_AUTHORS_PAPERS_DONE)) {

					// D: Id->AuId->AfId->AuId
					pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));
				} else {
					// D: Id->AuId->AfId->AuId
					pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, commonAAs, dstAuthorPapers));
				}

			} else {

				if (finished.contains(SRC_AUTHORS_PAPERS_DONE)) {

					// D: Id->AuId->AfId->AuId
					pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, srcAuthorsPapers, dstAuthorPapers));
				} else {
					// D: Id->AuId->AfId->AuId
					pathResult.addAll(getId2AuId2AfId2AuId(src, dstAuthorId, commonAAs, dstAuthorPapers));
				}

				while (!finished.contains(SRC_PAPER_REFS_DONE))
					;

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
