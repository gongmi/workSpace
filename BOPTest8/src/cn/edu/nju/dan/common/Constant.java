package cn.edu.nju.dan.common;

public class Constant {

	public final static String ACADEMIC_URI = "https://oxfordhk.azure-api.net/academic/v1.0/evaluate";

	public final static String SUBSCRIPTION_KEY_KEY = "Ocp-Apim-Subscription-Key";
	public final static String SUBSCRIPTION_KEY_VALUE = "f7cc29509a8443c5b3a5e56b0e38b5a6";

	public final static String COUNT_KEY = "count";
	public final static String COUNT_VALUE = "1000000";

	public final static String EXPR_KEY = "expr";
	public final static String ID_FORMAT = "Id=%d";
	public final static String RID_FORMAT = "RId=%d";
	public final static String AUID_FORMAT = "Composite(AA.AuId=%d)";

	public final static String ATTR_KEY = "attributes";

	public final static String ATTR_ALL = "Id,AA.AuId,AA.AfId,C.CId,F.FId,J.JId,RId";

	public final static String ATTR_AA_GET_SRC_AUTHOR_PAPERS = "Id,AA.AuId,AA.AfId,RId";
	public final static String ATTR_AA_GET_DST_AUTHOR_PAPERS = "Id,AA.AuId,AA.AfId";

	public final static String ATTR_AI_GET_SRC_AUTHOR_PAPERS = "Id,AA.AuId,C.CId,F.FId,J.JId,RId";
	public final static String ATTR_AI_GET_DST_PAPER_REFERERS = "Id,AA.AuId,C.CId,F.FId,J.JId";
	public final static String ATTR_AI_GET_DST_AUTHORS_PAPERS = "Id,AA.AuId,AA.AfId";

	public final static String ATTR_IA_GET_SRC_PAPER_REFS = "Id,AA.AuId,RId";
	public final static String ATTR_IA_GET_SRC_AUTHORS_PAPERS = "Id,AA.AuId,AA.AfId";
	public final static String ATTR_IA_GET_DST_AUTHOR_PAPERS = "Id,AA.AuId,AA.AfId,F.FId,C.CId,J.JId";

	public final static String ATTR_II_GET_SRC_PAPER_REFS = "Id,AA.AuId,C.CId,F.FId,J.JId,RId";
	public final static String ATTR_II_GET_DST_PAPER_REFERS = "Id,AA.AuId,C.CId,F.FId,J.JId";

	public final static String ENTITIES_KEY = "entities";

	public final static int MAX_ELEMENTS_IN_CACHE = 10000;

}
