package cn.edu.nju.dan.dal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.nju.dan.common.Constant;
import cn.edu.nju.dan.entities.Entity;

public class BOPHttpClient {

	private static CloseableHttpClient httpClient = null;

	private BOPHttpClient() {
		httpClient = HttpClients.createDefault();
	}

	private static final ThreadLocal<BOPHttpClient> _localClient = new ThreadLocal<BOPHttpClient>() {
		protected BOPHttpClient initialValue() {
			return new BOPHttpClient();
		}
	};

	public static BOPHttpClient getInstance() {
		return _localClient.get();
	}

	public List<Entity> getEntities(HashMap<String, String> params) throws URISyntaxException, IOException {
		List<Entity> entityList = new ArrayList<Entity>();

		URIBuilder uriBuilder = new URIBuilder(Constant.ACADEMIC_URI);
		uriBuilder.setParameter(Constant.COUNT_KEY, Constant.COUNT_VALUE);

		for (Entry<String, String> p : params.entrySet()) {
			uriBuilder.setParameter(p.getKey(), p.getValue());
		}

		HttpGet httpGet = new HttpGet(uriBuilder.build());
		httpGet.setHeader(Constant.SUBSCRIPTION_KEY_KEY, Constant.SUBSCRIPTION_KEY_VALUE);

		CloseableHttpResponse response = httpClient.execute(httpGet);

		try {
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				JSONObject queryResult = new JSONObject(EntityUtils.toString(httpEntity));
				if (queryResult.has(Constant.ENTITIES_KEY)) {
					JSONArray entityArray = queryResult.getJSONArray(Constant.ENTITIES_KEY);
					for (int i = 0; i < entityArray.length(); i++) {
						entityList.add(new Entity(entityArray.getJSONObject(i)));
					}
				}
			}
		} finally {
			response.close();
			httpGet.releaseConnection();
		}

		return entityList;
	}

}
