package TCP_MultiThread;

import java.io.*;
import java.net.*;
public class MyClient
{
	public static void main(String[] args)throws Exception
	{
		Socket s = new Socket("127.0.0.1" , 30000);
		// �ͻ�������ClientThread�߳� ���϶�ȡ���Է�����������
		new Thread(new ClientThread(s)).start();   // ��

	
		
		// ���϶�ȡ��������
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		Socket��Ӧ�������
		PrintStream ps = new PrintStream(s.getOutputStream());
		String line = null;
		while ((line = br.readLine()) != null)
		{
			// ���û��ļ�����������    д��Socket��Ӧ�������
			ps.println("�˿�Ϊ"+s.getLocalPort()+"�Ŀͻ�˵:"+line);
		}
	}
}
