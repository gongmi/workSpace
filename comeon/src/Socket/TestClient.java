package Socket;

// *		1. Java Socket��̲���
// *		2. Socket/ServerSocket���÷�
// *		3. ͨ��Socket������Ի�ȡͨ�ŶԷ�Socket����Ϣ

//�Ұ�װ����BufferedReader
import java.net.*;
import java.io.*;

public class TestClient {
	public static void main(String args[]) throws Exception {
		while (true) {
			Socket s1 = new Socket("127.0.0.3", 8888);
			InputStream is = s1.getInputStream();
//			DataInputStream dis = new DataInputStream(is);
//			System.out.println(dis.readUTF());
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			System.out.println(br.readLine());
			Thread.sleep(10000);
			br.close();
			s1.close();

		}
	}
}