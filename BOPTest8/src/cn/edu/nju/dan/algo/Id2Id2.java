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

public class Id2Id2 {

	private final static int LIST_TYPE_SRC_PAPER_REFS = 0;
	private final static int LIST_TYPE_DST_PAPER_REFERERS = 1;

	private final static int SRC_PAPER_REFS_DONE = 1;
	private final static int DST_PAPER_REFERERS_DONE = 2;

	public static JSONArray getPath4Id2Id(final Entity src, final Entity dst) throws Exception {

		final Vector<Integer> finished = new Vector<>();
		final List<Entity> srcPaperRefs = new ArrayList<>();
		final List<Entity> dstPaperReferers = new ArrayList<>();

		new Thread(new Runnable() {
			public void run() {
				try {
					srcPaperRefs.addAll(BOPEntityManager.getSrcPaperRefs4II(src.references));
					finished.add(SRC_PAPER_REFS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		new Thread(new Runnable() {
			public void run() {
				try {
					dstPaperReferers.addAll(BOPEntityManager.getDstPaperReferers4II(dst.entityId));
					finished.add(DST_PAPER_REFERERS_DONE);
				} catch (Exception e) {

				}
			}
		}).run();

		List<Path> pathResult = new ArrayList<>();

		// A: Id->Id
		if (getId2Id(src, dst))
			pathResult.add(new Path(src.entityId, dst.entityId));

		// C: Id->(AuId|CId|FId|JId)->Id
		pathResult.addAll(getId2ACFJ2Id(src, dst));

		while (!(finished.contains(SRC_PAPER_REFS_DONE) || finished.contains(DST_PAPER_REFERERS_DONE)))
			;

		if (finished.contains(SRC_PAPER_REFS_DONE)) {

			// B: Id->Id->Id
			pathResult.addAll(getId2Id2Id(src, dst, srcPaperRefs, LIST_TYPE_SRC_PAPER_REFS));

			// E: Id->Id->(AuId,CId,FId|JId)->Id
			pathResult.addAll(getId2Id2ACFJ2Id(src, dst, srcPaperRefs));

			while (!finished.contains(DST_PAPER_REFERERS_DONE))
				;

			// D: Id->Id->Id->Id
			pathResult.addAll(getId2Id2Id2Id(src, dst, srcPaperRefs, dstPaperReferers));

			// F: Id->(AuId|CId|FId|JId)->Id->Id
			pathResult.addAll(getId2ACFJ2Id2Id(src, dst, dstPaperReferers));

		} else {

			// B: Id->Id->Id
			pathResult.addAll(getId2Id2Id(src, dst, dstPaperReferers, LIST_TYPE_DST_PAPER_REFERERS));

			// F: Id->(AuId|CId|FId|JId)->Id->Id
			pathResult.addAll(getId2ACFJ2Id2Id(src, dst, dstPaperReferers));

			while (!finished.contains(SRC_PAPER_REFS_DONE))
				;

			// D: Id->Id->Id->Id
			pathResult.addAll(getId2Id2Id2Id(src, dst, srcPaperRefs, dstPaperReferers));

			// E: Id->Id->(AuId,CId,FId|JId)->Id
			pathResult.addAll(getId2Id2ACFJ2Id(src, dst, srcPaperRefs));

		}

		JSONArray finalResult = new JSONArray();
		for (Path path : pathResult) {
			finalResult.put(path.toJSONArray());
		}
		return finalResult;
	}

	public static List<Path> getId2ACFJ2Id(Entity src, Entity dst) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> srcAuIds = Utils.getAuIds(src);
		if (dst.authors != null) {
			for (Author author : dst.authors) {
				if (srcAuIds.contains(author.authorId)) {
					paths.add(new Path(src.entityId, author.authorId, dst.entityId));
				}
			}
		}

		if (src.conferenceSeriesId != 0 && src.conferenceSeriesId == dst.conferenceSeriesId)
			paths.add(new Path(src.entityId, src.conferenceSeriesId, dst.entityId));

		HashSet<Long> srcFIds = Utils.getFIds(src);
		if (dst.fields != null) {
			for (Field field : dst.fields) {
				if (srcFIds.contains(field.fieldOfStudyId)) {
					paths.add(new Path(src.entityId, field.fieldOfStudyId, dst.entityId));
				}
			}
		}

		if (src.journalId != 0 && src.journalId == dst.journalId)
			paths.add(new Path(src.entityId, src.journalId, dst.entityId));

		return paths;
	}

	public static List<Path> getId2Id2Id(Entity src, Entity dst, List<Entity> entityList, int listType) {
		List<Path> paths = new ArrayList<>();

		if (listType == LIST_TYPE_SRC_PAPER_REFS) {
			for (Entity entity : entityList) {
				if (entity.references != null) {
					for (Reference ref : entity.references) {
						if (ref.referenceId == dst.entityId) {
							paths.add(new Path(src.entityId, entity.entityId, dst.entityId));
						}
					}
				}
			}
		} else if (listType == LIST_TYPE_DST_PAPER_REFERERS) {
			HashSet<Long> rids = Utils.getRIds(src);
			HashSet<Long> ids = Utils.getIds(entityList);
			for (Long rid : rids) {
				if (ids.contains(rid)) {
					paths.add(new Path(src.entityId, rid, dst.entityId));
				}
			}
		}

		return paths;
	}

	public static List<Path> getId2Id2ACFJ2Id(Entity src, Entity dst, List<Entity> srcPaperRefs) {
		List<Path> paths = new ArrayList<>();

		for (Entity entity : srcPaperRefs) {
			List<Path> midPath = getId2ACFJ2Id(entity, dst);
			for (Path path : midPath) {
				paths.add(new Path(src.entityId, entity.entityId, path.get(1), dst.entityId));
			}

		}

		return paths;
	}

	public static List<Path> getId2ACFJ2Id2Id(Entity src, Entity dst, List<Entity> dstPaperReferers) {
		List<Path> paths = new ArrayList<>();
		// ACFJ = (AuId|CId|FId|JId)

		HashSet<Long> srcAuIds = Utils.getAuIds(src);
		final boolean shouldCheckAuId = (srcAuIds.size() > 0);

		HashSet<Long> srcFids = Utils.getFIds(src);
		final boolean shouldCheckFId = (srcFids.size() > 0);

		final boolean shouldCheckCId = (src.conferenceSeriesId != 0) ? true : false;

		final boolean shouldCheckJId = (src.journalId != 0) ? true : false;

		for (Entity tmp : dstPaperReferers) {
			if (shouldCheckAuId) {
				if (tmp.authors != null) {
					for (Author author : tmp.authors) {
						if (srcAuIds.contains(author.authorId)) {
							paths.add(new Path(src.entityId, author.authorId, tmp.entityId, dst.entityId));
						}
					}
				}
			}
			if (shouldCheckFId) {
				if (tmp.fields != null) {
					for (Field field : tmp.fields) {
						if (srcFids.contains(field.fieldOfStudyId)) {
							paths.add(new Path(src.entityId, field.fieldOfStudyId, tmp.entityId, dst.entityId));
						}
					}
				}
			}

			if (shouldCheckCId) {
				if (src.conferenceSeriesId == tmp.conferenceSeriesId) {
					paths.add(new Path(src.entityId, src.conferenceSeriesId, tmp.entityId, dst.entityId));
				}
			}

			if (shouldCheckJId) {
				if (src.journalId == tmp.journalId)
					paths.add(new Path(src.entityId, src.journalId, tmp.entityId, dst.entityId));
			}
		}

		return paths;
	}

	public static List<Path> getId2Id2Id2Id(Entity src, Entity dst, List<Entity> srcPaperRefs,
			List<Entity> dstPaperReferers) {
		List<Path> paths = new ArrayList<>();

		HashSet<Long> dstReferIds = Utils.getIds(dstPaperReferers);
		for (Entity entity : srcPaperRefs) {
			if (entity.references != null) {
				for (Reference reference : entity.references) {
					if (dstReferIds.contains(reference.referenceId)) {
						paths.add(new Path(src.entityId, entity.entityId, reference.referenceId, dst.entityId));
					}
				}
			}
		}

		return paths;
	}

	public static boolean getId2Id(Entity src, Entity dst) {
		if (src.references != null) {
			for (Reference ref : src.references) {
				if (ref.referenceId == dst.entityId) {
					return true;
				}
			}
		}
		return false;
	}
}
