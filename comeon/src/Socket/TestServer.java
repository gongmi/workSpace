package Socket;

//*		1. Java Socket��̲���
//*		2. Socket/ServerSocket���÷�
//*		3. ͨ��Socket������Ի�ȡͨ�ŶԷ�Socket����Ϣ

// �Ұ�װ����PrintStream
import java.net.*;
import java.io.*;

//
public class TestServer {
	public static void main(String args[]) {
		try {
			ServerSocket s = new ServerSocket(8888);
			while (true) {
				Socket s1 = s.accept();
				OutputStream os = s1.getOutputStream();
//				DataOutputStream dos = new DataOutputStream(os);
//				dos.writeUTF("Hello," + s1.getInetAddress() + "port#" + s1.getPort() + "  bye-bye!");
				PrintStream ps=new PrintStream(os);
				ps.println("Hello," + s1.getInetAddress() + "port#" + s1.getPort() + "  bye-bye!");
				// �õ�����client��Socket���캯��д��ip�� 127.0.0.1
				// �õ�����client����������port
//				dos.close();
				ps.close();
				s1.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("�������г���:" + e);
		}
	}
}