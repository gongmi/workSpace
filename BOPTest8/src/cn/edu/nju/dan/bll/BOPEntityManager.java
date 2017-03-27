package cn.edu.nju.dan.bll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import cn.edu.nju.dan.common.Constant;
import cn.edu.nju.dan.dal.BOPHttpClient;
import cn.edu.nju.dan.entities.Author;
import cn.edu.nju.dan.entities.Entity;
import cn.edu.nju.dan.entities.Reference;

public class BOPEntityManager {

	public static HashMap<Long, Entity> getByInputs(long input1, long input2) throws Exception {

		HashMap<Long, Entity> result = new HashMap<>();

		Entity e1 = BOPCacheManager.get(input1);
		Entity e2 = BOPCacheManager.get(input2);
		if (e1 != null && e2 != null) {
			result.put(input1, e1);
			result.put(input2, e2);
			return result;
		}

		if (e1 == null && e2 == null) {
			String expr = String.format("Or(Id=%d,Id=%d)", input1, input2);

			HashMap<String, String> params = new HashMap<>();
			params.put(Constant.EXPR_KEY, expr);
			params.put(Constant.ATTR_KEY, Constant.ATTR_ALL);

			List<Entity> entityList = BOPHttpClient.getInstance().getEntities(params);
			for (Entity entity : entityList) {
				BOPCacheManager.put(entity.entityId, entity);
				result.put(entity.entityId, entity);
			}

		} else if (e1 == null) {
			e1 = getById(input1);
			BOPCacheManager.put(e1.entityId, e1);

			result.put(input1, e1);
			result.put(e2.entityId, e2);

		} else {
			e2 = getById(input2);
			BOPCacheManager.put(e2.entityId, e2);

			result.put(e1.entityId, e1);
			result.put(input2, e2);
		}
		return result;
	}

	public static Entity getById(long id) throws Exception {

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.ID_FORMAT, id));
		params.put(Constant.ATTR_KEY, Constant.ATTR_ALL);
		List<Entity> entityList = BOPHttpClient.getInstance().getEntities(params);
		if (entityList.size() > 0) {
			BOPCacheManager.put(id, entityList.get(0));
			return entityList.get(0);
		}
		return null;
	}

	public static List<Entity> getSrcAuthorPapers4AA(long srcAuthorId) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.AUID_FORMAT, srcAuthorId));
		params.put(Constant.ATTR_KEY, Constant.ATTR_AA_GET_SRC_AUTHOR_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getDstAuthorPapers4AA(long dstAuthorId) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.AUID_FORMAT, dstAuthorId));
		params.put(Constant.ATTR_KEY, Constant.ATTR_AA_GET_DST_AUTHOR_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getSrcAuthorPapers4AI(long srcAuthorId) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.AUID_FORMAT, srcAuthorId));
		params.put(Constant.ATTR_KEY, Constant.ATTR_AI_GET_SRC_AUTHOR_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getDstPaperReferers4AI(long dstPaperId) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.RID_FORMAT, dstPaperId));
		params.put(Constant.ATTR_KEY, Constant.ATTR_AI_GET_DST_PAPER_REFERERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getDstAuthorsPapers4AI(List<Author> authors) throws Exception {
		List<String> queryList = new ArrayList<>();
		for (Author author : authors) {
			queryList.add(String.format(Constant.AUID_FORMAT, author.authorId));
		}
		String queryExpr = mergeQuery(queryList);
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr);
		params.put(Constant.ATTR_KEY, Constant.ATTR_AI_GET_DST_AUTHORS_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getSrcPaperRefs4IA(List<Reference> references) throws Exception {
		List<Entity> result = new ArrayList<>();

		List<String> queryList = new ArrayList<>();
		for (Reference r : references) {
			Entity entity = BOPCacheManager.get(r.referenceId);
			if (entity != null) {
				result.add(entity);
			} else {
				queryList.add(String.format(Constant.ID_FORMAT, r.referenceId));
			}
		}

		String queryExpr = mergeQuery(queryList);

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr);
		params.put(Constant.ATTR_KEY, Constant.ATTR_IA_GET_SRC_PAPER_REFS);
		result.addAll(BOPHttpClient.getInstance().getEntities(params));
		return result;
	}

	public static List<Entity> getSrcAuthorsPapers4IA(List<Author> authors) throws Exception {

		List<String> queryList = new ArrayList<>();
		for (Author a : authors) {
			queryList.add(String.format(Constant.AUID_FORMAT, a.authorId));
		}
		String queryExpr = mergeQuery(queryList);

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr);
		params.put(Constant.ATTR_KEY, Constant.ATTR_IA_GET_SRC_AUTHORS_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getByAfIdsAndAuIds4IA(HashSet<Long> afids, HashSet<Long> auids) throws Exception {
		if (afids.size() == 0)
			return new ArrayList<Entity>();

		List<String> queryList = new ArrayList<>();
		for (Long afid : afids) {
			for (Long auid : auids) {
				queryList.add(String.format("Composite(And(AA.AfId=%d,AA.AuId=%d))", afid, auid));
			}
		}
		String queryExpr = mergeQuery(queryList);

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr.toString());
		params.put(Constant.ATTR_KEY, Constant.ATTR_IA_GET_SRC_AUTHORS_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getDstAuthorPapers4IA(long dstAuthorId) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.AUID_FORMAT, dstAuthorId));
		params.put(Constant.ATTR_KEY, Constant.ATTR_IA_GET_DST_AUTHOR_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	public static List<Entity> getSrcPaperRefs4II(List<Reference> references) throws Exception {
		List<Entity> result = new ArrayList<>();

		List<String> queryList = new ArrayList<>();
		for (Reference r : references) {
			Entity entity = BOPCacheManager.get(r.referenceId);
			if (entity != null) {
				result.add(entity);
			} else {
				queryList.add(String.format(Constant.ID_FORMAT, r.referenceId));
			}

		}
		String queryExpr = mergeQuery(queryList);

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr.toString());
		params.put(Constant.ATTR_KEY, Constant.ATTR_II_GET_SRC_PAPER_REFS);
		result.addAll(BOPHttpClient.getInstance().getEntities(params));
		return result;
	}

	public static List<Entity> getDstPaperReferers4II(long rid) throws Exception {
		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, String.format(Constant.RID_FORMAT, rid));
		params.put(Constant.ATTR_KEY, Constant.ATTR_II_GET_DST_PAPER_REFERS);
		List<Entity> entityList = BOPHttpClient.getInstance().getEntities(params);
		return entityList;
	}

	public static List<Entity> getByAfIdsAndAuIds4AI(HashSet<Long> afids, HashSet<Long> auids) throws Exception {
		if (afids.size() == 0)
			return new ArrayList<>();

		List<String> queryList = new ArrayList<>();
		for (Long afid : afids) {
			for (Long auid : auids) {
				queryList.add(String.format("Composite(And(AA.AfId=%d,AA.AuId=%d))", afid, auid));
			}
		}
		String queryExpr = mergeQuery(queryList);

		HashMap<String, String> params = new HashMap<>();
		params.put(Constant.EXPR_KEY, queryExpr.toString());
		params.put(Constant.ATTR_KEY, Constant.ATTR_AI_GET_DST_AUTHORS_PAPERS);
		return BOPHttpClient.getInstance().getEntities(params);
	}

	private static String mergeQuery(List<String> queryList) {
		if (queryList.size() == 0) {
			return "";
		}
		if (queryList.size() == 1) {
			return queryList.get(0);
		}
		if (queryList.size() % 2 != 0) {
			queryList.add(queryList.get(0));
		}
		List<String> left = queryList.subList(0, queryList.size() / 2);
		List<String> right = queryList.subList(queryList.size() / 2, queryList.size());

		List<String> newList = new ArrayList<>();
		for (int i = 0; i < left.size(); i++) {
			newList.add("Or(" + left.get(i) + "," + right.get(i) + ")");
		}
		return mergeQuery(newList);

	}

}
