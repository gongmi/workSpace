package Socket;

import java.net.*;
import java.io.*;

//���뵱Ȼ������˷������ĸ���״̬ �����ƺ�������ȷ
public class TCPServer {
	public static void main(String[] args) throws Exception {
		int n = 1;
		ServerSocket ss = new ServerSocket(9999);
		System.out.println("����������Listen״̬");
		Thread.sleep(10000);
		while (true) {
			System.out.println("׼�������û�" + n + "��������");
			Socket socket = ss.accept(); // һֱ�ȴ����û������� ����ʽ
			System.out.println("���������û�" + n + "���ӳɹ� ����������Established״̬");
			Thread.sleep(20000);
			InputStream is = socket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			System.out.println(dis.readUTF()); // һֱ�ȴ����û������� ����ʽ
			// ���Ե����û������ݹ������� ����һֱ�������� ����û��޷�����accept
			System.out.println("���տͻ��˵���Ϣ ��Ȼ��Established״̬");
			dis.close();
			socket.close();
			System.out.println("����������close-wait״̬");
			Thread.sleep(20000);
			n++;
		}
	}
}