package UDP;

import java.net.*;
import java.io.*;

public class UdpServer {
	public static final int PORT = 30000;
	private static final int DATA_LEN = 4096;
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket;
	// 定义一个字符串数组，服务器发送该数组的的元素
	String[] books = new String[] { "疯狂Java讲义", "轻量级Java EE企业应用实战", "疯狂Android讲义", "疯狂Ajax讲义" };

	public void init() throws IOException {
		try (
		DatagramSocket socket = new DatagramSocket(PORT)) {
			for (int i = 0; i < 1000; i++) {
//				先接受client发过来的一个数据报 打印在自己的console
				socket.receive(inPacket);
				System.out.println(inPacket.getPort()+":"+new String(inBuff, 0, inPacket.getLength()));
//				再从books中随便选一个发送给这个client 
				String s="Hello,port:"+inPacket.getPort()+",please read:"+books[i % 4];
				byte[] sendData = s.getBytes();
				outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
				socket.send(outPacket);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpServer().init();
	}
}