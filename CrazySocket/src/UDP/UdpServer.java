package UDP;

import java.net.*;
import java.io.*;

public class UdpServer {
	public static final int PORT = 30000;
	private static final int DATA_LEN = 4096;
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket;
	// ����һ���ַ������飬���������͸�����ĵ�Ԫ��
	String[] books = new String[] { "���Java����", "������Java EE��ҵӦ��ʵս", "���Android����", "���Ajax����" };

	public void init() throws IOException {
		try (
		DatagramSocket socket = new DatagramSocket(PORT)) {
			for (int i = 0; i < 1000; i++) {
//				�Ƚ���client��������һ�����ݱ� ��ӡ���Լ���console
				socket.receive(inPacket);
				System.out.println(inPacket.getPort()+":"+new String(inBuff, 0, inPacket.getLength()));
//				�ٴ�books�����ѡһ�����͸����client 
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