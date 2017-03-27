package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class UdpClient {
	// 定义发送数据报的目的地
	public static final int DEST_PORT = 30000;
	public static final String DEST_IP = "127.0.0.1";
	private static final int DATA_LEN = 4096;
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket = null;

	public void init() throws IOException {
		try (
		// 创建一个客户端DatagramSocket，使用随机端口
		DatagramSocket socket = new DatagramSocket()) {
			// 初始化发送用的DatagramSocket，它包含一个长度为0的字节数组
			outPacket = new DatagramPacket(new byte[0], 0, InetAddress.getByName(DEST_IP),
					DEST_PORT);
			// 创建键盘输入流
			Scanner scan = new Scanner(System.in);
			// 不断读取键盘输入
			while (scan.hasNextLine()) {
//				先发一句话给server
				byte[] buff = scan.nextLine().getBytes();
				outPacket.setData(buff);
				socket.send(outPacket);
//				然后接受server的返回
				socket.receive(inPacket);
				System.out.println(new String(inBuff, 0, inPacket.getLength()));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpClient().init();
	}
}
