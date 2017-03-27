package url;

import java.io.*;
import java.net.*;
import java.util.*;

public class GetPostTest {
	public static String sendGet(String url, String param) {
		String result = "";
		String urlName = url + "?" + param;
		try {
			URL realUrl = new URL(urlName);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			conn.connect();
			Map<String, List<String>> map = conn.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			try (
			// ����BufferedReader����������ȡURL����Ӧ
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					"utf-8"))) {
				String line;
				while ((line = in.readLine()) != null) {
					result += "\n" + line;
				}
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		return result;
	}

	public static String sendPost(String url, String param) {
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			try (
			// ��ȡURLConnection�����Ӧ�������
			PrintWriter out = new PrintWriter(conn.getOutputStream())) {
				// �����������
				out.print(param);
				// flush������Ļ���
				out.flush();
			}
			try (
			// ����BufferedReader����������ȡURL����Ӧ
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),
					"utf-8"))) {
				String line;
				while ((line = in.readLine()) != null) {
					result += "\n" + line;
				}
			}
		} catch (Exception e) {
			System.out.println("����POST��������쳣��" + e);
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]) {
		String s = GetPostTest.sendGet("http://localhost:8888/abc/a.jsp", null);
		System.out.println(s);
		String s1 = GetPostTest.sendPost("http://localhost:8888/abc/login.jsp",
				"name=crazyit.org&pass=leegang");
		System.out.println(s1);
	}
}
