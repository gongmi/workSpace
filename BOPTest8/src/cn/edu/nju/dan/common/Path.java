package cn.edu.nju.dan.common;

import org.json.JSONArray;

public class Path {
	private long[] path;

	public Path(long... ids) {
		this.path = ids;
	}

	public long get(int index) {
		return this.path[index];
	}

	public JSONArray toJSONArray() {
		JSONArray array = new JSONArray();
		for (long i : path) {
			array.put(i);
		}
		return array;
	}
}
