package Socket;

import java.net.*;
//
//����TestSockServer +TestSockClient���������� ˫��ͨ�� ���ܽ��� һ����˵һ����������
//����Ҫһ����˵�� ��Ϊdis.readUTF()������ʽ�� ������߶�����˵�� ��ô�ͻῨס
import java.io.*;

public class TestSockClient {
	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		try {
			Socket socket = new Socket("localhost", 5888);
			is = socket.getInputStream();
			os = socket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
//			dos.writeUTF("client:hey�������� ���ǿͻ�");	//����˵ ��������˵
			String s = null;
			if ((s = dis.readUTF()) != null)
			System.out.println(s);
			dos.writeUTF("client:hey�������� ���ǿͻ�");
			dos.close();
			dis.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
