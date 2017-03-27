package TCP_MultiThread;

import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {
	Socket s = null;
	BufferedReader br = null;

	public ServerThread(Socket s) throws IOException {
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}

	public void run() {
		try {
			String content = null;
			// ����ѭ�����ϴ�Socket�ж�ȡ�ͻ��˷��͹���������
			while ((content = readFromClient()) != null) {
				// ����socketList�е�ÿ��Socket�� ��������������ÿ��Socket����һ��
				for (Socket s : MyServer.socketList) {
					if (s != this.s) {
						PrintStream ps = new PrintStream(s.getOutputStream());
						ps.println(content);
						System.out.println(content);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �����ȡ�ͻ������ݵķ���
	private String readFromClient() {
		try {
			return br.readLine();
		}
		// �����׽���쳣��������Socket��Ӧ�Ŀͻ����Ѿ��ر�
		catch (IOException e) {
			// ɾ����Socket��
			MyServer.socketList.remove(s); // ��
		}
		return null;
	}
}
