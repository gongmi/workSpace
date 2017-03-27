package Proxy;

import java.io.*;
import java.net.*;
import java.util.*;

public class ProxyTest {
	final String PROXY_ADDR = "129.82.12.188";
	final int PROXY_PORT = 3124;
	String urlStr = "http://www.crazyit.org";

	public void init() throws IOException, MalformedURLException {
		URL url = new URL(urlStr);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_ADDR, PROXY_PORT));
		URLConnection conn = url.openConnection(proxy);
		conn.setConnectTimeout(5000);
		try (
		Scanner scan = new Scanner(conn.getInputStream(), "utf-8");
				PrintStream ps = new PrintStream("index.htm")) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				
				System.out.println(line);
				ps.println(line);
			}
		}
	}

	public static void main(String[] args) throws IOException, MalformedURLException {
		new ProxyTest().init();
	}
}
