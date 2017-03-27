package TCP_MultiThread2;

import java.io.*;

public class ClientThread extends Thread {
	BufferedReader br = null;

	public ClientThread(BufferedReader br) {
		this.br = br;
	}

	public void run() {
		try {
			String line = null;
			// ���ϴ��������ж�ȡ���ݣ�������Щ���ݴ�ӡ���
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
