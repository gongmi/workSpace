package NIO;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.*;

public class SimpleAIOServer {
	static final int PORT = 30000;

	public static void main(String[] args) throws Exception {
		try (
		// �ٴ���AsynchronousServerSocketChannel����
		AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open()) {
			// ��ָ����ָ����ַ���˿ڼ�����
			serverChannel.bind(new InetSocketAddress(PORT));
			while (true) {
				// �۲���ѭ���������Կͻ��˵�����
				Future<AsynchronousSocketChannel> future = serverChannel.accept();
				// ��ȡ������ɺ󷵻ص�AsynchronousSocketChannel
				AsynchronousSocketChannel socketChannel = future.get();
				// ִ�������
				socketChannel.write(ByteBuffer.wrap("��ӭ������AIO�����磡".getBytes("UTF-8"))).get();
			}
		}
	}
}
