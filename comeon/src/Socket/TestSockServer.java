package Socket;

import java.io.*;
import java.net.*;

public class TestSockServer {
	public static void main(String[] args) {
		InputStream in = null;
		OutputStream out = null;
		try {
			ServerSocket ss = new ServerSocket(5888);
			Socket socket = ss.accept();
			in = socket.getInputStream();
			out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			DataInputStream dis = new DataInputStream(in);
//			dos.writeUTF("server:hi���ͻ������Ƿ�����");  //����˵ ��������˵
			String s = null;
			if ((s = dis.readUTF()) != null) {
				System.out.println(s);
				System.out.println("from: " + socket.getInetAddress());
				System.out.println("Port: " + socket.getPort());
			}
			dos.writeUTF("server:hi���ͻ������Ƿ�����");
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}