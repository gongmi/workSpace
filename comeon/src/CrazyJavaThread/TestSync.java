package CrazyJavaThread;

public class TestSync implements Runnable {
	private Account account;
	private double drawamount;

	public TestSync(Account account, double drawamount) {

		this.account = account;
		this.drawamount = drawamount;
	}

	public void run() {
		// synchronized (account) {
		if (account.getBalence() > drawamount) {
			System.out.println(Thread.currentThread().getName() + "ȡǮ�ɹ�:" + drawamount + "RMB");
			account.setBalence(account.getBalence() - drawamount);

			// ���gongmiȡǮ������ �߳�ת�� yuanҲȡǮ ��ô��û�� ��ôyuanҲ����ȡ��
			System.out.println(Thread.currentThread().getName() + "ȡǮ�����:" + account.getBalence()
					+ "RMB");
		} else {
			System.out.println(Thread.currentThread().getName() + "ȡǮʧ������:"
					+ account.getBalence() + "RMB");
		}
		// }
	}

	public static void main(String[] args) {
		Account YQX = new Account("Ԭ����", 20000);
		TestSync test = new TestSync(YQX, 18000);
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("gongmi");
		t2.setName("yuan");
		t1.start();
		t2.start();
	}

}
