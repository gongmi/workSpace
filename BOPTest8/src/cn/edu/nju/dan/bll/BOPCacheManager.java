package cn.edu.nju.dan.bll;

import java.util.concurrent.ConcurrentHashMap;

import cn.edu.nju.dan.common.Constant;
import cn.edu.nju.dan.entities.Entity;

public class BOPCacheManager {

	private static ConcurrentHashMap<Long, Entity> map;

	static {

		map = new ConcurrentHashMap<>();
	}

	public static void put(long id, Entity entity) {
		if (map.size() > Constant.MAX_ELEMENTS_IN_CACHE)
			map.clear();
		map.put(id, entity);
	}

	public static Entity get(long id) {

		return map.get(id);
	}
}
