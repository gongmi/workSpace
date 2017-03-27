package url;

import java.net.*;

public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("join.qq.com");
		System.out.println("baidu�Ƿ�ɴ" + ip.isReachable(1000));
		System.out.println(ip.getHostName());
		System.out.println(ip.getHostAddress());
		System.out.println(ip.getCanonicalHostName());
		InetAddress local = InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 });
		System.out.println("�����Ƿ�ɴ" + local.isReachable(5000));
		System.out.println(local.getHostName());
		System.out.println(local.getHostAddress());
		System.out.println(local.getCanonicalHostName());
	}
}
