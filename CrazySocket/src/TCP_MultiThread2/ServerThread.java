package TCP_MultiThread2;

import java.net.*;
import java.io.*;
public class ServerThread extends Thread
{
	private Socket socket;
	BufferedReader br = null;
	PrintStream ps = null;
	// ����һ�������������ڽ���һ��Socket������ServerThread�߳�
	public ServerThread(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		try
		{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while((line = br.readLine())!= null)
			{
				// �������������CrazyitProtocol.USER_ROUND��ʼ�������������
				// ����ȷ�����������û���¼���û���
				if (line.startsWith(Protocol.USER_ROUND) && line.endsWith(Protocol.USER_ROUND))
				{
					// �õ���ʵ��Ϣ
					String userName = getRealMsg(line);
					// ����û����ظ�
					if (Server.clients.map.containsKey(userName))
					{
						System.out.println("�ظ�");
						ps.println(Protocol.NAME_REP);
					}
					else
					{
						System.out.println("�ɹ�");
						ps.println(Protocol.LOGIN_SUCCESS);
						Server.clients.put(userName , ps);
					}
				}
				// �������������Protocol.PRIVATE_ROUND��ʼ�������������
				// ����ȷ����˽����Ϣ��˽����Ϣֻ���ض������������
				else if (line.startsWith(Protocol.PRIVATE_ROUND) && line.endsWith(Protocol.PRIVATE_ROUND))
				{
					// �õ���ʵ��Ϣ
					String userAndMsg = getRealMsg(line);
					// ��SPLIT_SIGN�ָ��ַ�����ǰ����˽���û��������������Ϣ
					String user = userAndMsg.split(Protocol.SPLIT_SIGN)[0];
					String msg = userAndMsg.split(Protocol.SPLIT_SIGN)[1];
					// ��ȡ˽���û���Ӧ���������������˽����Ϣ
					Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps) + "���ĵض���˵��" + msg);
				}
				// ����Ҫ��ÿ��Socket����
				else
				{
					// �õ���ʵ��Ϣ
					String msg = getRealMsg(line);
					// ����clients�е�ÿ�������
					for (PrintStream clientPs : Server.clients.valueSet())
					{
						clientPs.println(Server.clients.getKeyByValue(ps) + "˵��" + msg);
					}
				}
			}
		}
		// ��׽���쳣�󣬱�����Socket��Ӧ�Ŀͻ����Ѿ�����������
		// ���Գ������Ӧ���������Map��ɾ��
		catch (IOException e)
		{
			Server.clients.removeByValue(ps);
			System.out.println(Server.clients.map.size());
			// �ر����硢IO��Դ
			try
			{
				if (br != null)
				{
					br.close();
				}
				if (ps != null)
				{
					ps.close();
				}
				if (socket != null)
				{
					socket.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	// ������������ȥ��ǰ���Э���ַ����ָ�����ʵ����
	private String getRealMsg(String line)
	{
		return line.substring(Protocol.PROTOCOL_LEN , line.length() - Protocol.PROTOCOL_LEN);
	}
}