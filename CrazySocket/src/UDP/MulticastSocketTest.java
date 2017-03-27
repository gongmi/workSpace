package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

// �ø���ʵ��Runnable�ӿڣ������ʵ������Ϊ�̵߳�target
public class MulticastSocketTest implements Runnable {
	private static final String BROADCAST_IP = "230.0.0.1";
	public static final int BROADCAST_PORT = 30000;
	private static final int DATA_LEN = 4096;
	private MulticastSocket socket = null;
	private InetAddress broadcastAddress = null;
	private Scanner scan = null;
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
	private DatagramPacket outPacket = null;

	public void init() throws IOException {
		try (
		// ��������������
		Scanner scan = new Scanner(System.in)) {
			// �������ڷ��͡��������ݵ�MulticastSocket����
			// ���ڸ�MulticastSocket������Ҫ�������ݣ�������ָ���˿�
			socket = new MulticastSocket(BROADCAST_PORT);
			broadcastAddress = InetAddress.getByName(BROADCAST_IP);
			// ����socket����ָ���Ķ��㲥��ַ
			socket.joinGroup(broadcastAddress);
			// ���ñ�MulticastSocket���͵����ݱ��ᱻ���͵�����
			socket.setLoopbackMode(false);
			// ��ʼ�������õ�DatagramSocket��������һ������Ϊ0���ֽ�����
			outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
			// �����Ա�ʵ����run()������Ϊ�߳�����߳�
			new Thread(this).start();
			// ���϶�ȡ��������
			while (scan.hasNextLine()) {
				// �����������һ���ַ���ת���ֽ�����
				byte[] buff = scan.nextLine().getBytes();
				outPacket.setData(buff);
				socket.send(outPacket);
			}
		} finally {
			socket.close();
		}
	}

	public void run() {
		try {
			while (true) {
				// ��ȡSocket�е����ݣ����������ݷ���inPacket����װ���ֽ������
				socket.receive(inPacket);
				// ��ӡ�����socket�ж�ȡ������
				System.out.println("������Ϣ��" + new String(inBuff, 0, inPacket.getLength()));
			}
		}
		// ��׽�쳣
		catch (IOException ex) {
			ex.printStackTrace();
			try {
				if (socket != null) {
					// �ø�Socket�뿪�ö��IP�㲥��ַ
					socket.leaveGroup(broadcastAddress);
					// �رո�Socket����
					socket.close();
				}
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new MulticastSocketTest().init();
	}
}
