package cn.edu.nju.dan.algo;

import java.util.HashMap;

import org.json.JSONArray;

import cn.edu.nju.dan.bll.BOPEntityManager;
import cn.edu.nju.dan.entities.Entity;

public class Algo {

	public static JSONArray exec(long input1, long input2) throws Exception {

		HashMap<Long, Entity> inputEntity = BOPEntityManager.getByInputs(input1, input2);
		Entity src = inputEntity.get(input1);
		Entity dst = inputEntity.get(input2);

		if (isPaper(src) && isPaper(dst)) {
			return Id2Id2.getPath4Id2Id(src, dst);
		} else if (isPaper(src)) {
			return Id2AuId2.getPath4Id2AuId(src, dst.entityId);
		} else if (isPaper(dst)) {
			return AuId2Id2.getPath4AuId2Id(src.entityId, dst);
		} else {
			return AuId2AuId2.getPath4AuId2AuId(src.entityId, dst.entityId);
		}
	}

	private static boolean isPaper(Entity e) {
		return (e.authors != null) && (e.authors.size() > 0);
	}

}
