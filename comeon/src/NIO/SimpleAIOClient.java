package NIO;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;

public class SimpleAIOClient {
	static final int PORT = 30000;

	public static void main(String[] args) throws Exception {
		// ���ڶ�ȡ���ݵ�ByteBuffer��
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset utf = Charset.forName("utf-8");
		try (
		// �ٴ���AsynchronousSocketChannel����
		AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open()) {
			// ������Զ�̷�����
			clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get(); // ��
			buff.clear();
			// �۴�clientChannel�ж�ȡ����
			clientChannel.read(buff).get(); // ��
			buff.flip();
			// ��buff������ת��Ϊ�ַ���
			String content = utf.decode(buff).toString();
			System.out.println("��������Ϣ��" + content);
		}
	}
}
