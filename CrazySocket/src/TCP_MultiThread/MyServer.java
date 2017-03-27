package TCP_MultiThread;

import java.net.*;
import java.io.*;
import java.util.*;

public class MyServer {
	// ���屣������Socket��ArrayList���������װΪ�̰߳�ȫ��
	public static List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>());

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(30000);
		while (true) {
			// ���д������������һֱ�ȴ����˵�����
			Socket s = ss.accept();
			socketList.add(s);
			// ÿ���ͻ������Ӻ�����һ��ServerThread�߳�Ϊ�ÿͻ��˷���
			new Thread(new ServerThread(s)).start();
		}
	}
}