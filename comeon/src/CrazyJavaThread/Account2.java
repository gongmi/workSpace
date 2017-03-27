package CrazyJavaThread;

public class Account2 {
	private String accountNo;
	private double balence;

	public Account2(String accountNo, double balence) {

		this.accountNo = accountNo;
		this.balence = balence;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public double getBalence() {
		return balence;
	}

	// // ���draw��������draw
	// public synchronized void draw(double drawamount) {
	//
	// if (balence >= drawamount) {
	// System.out.println(Thread.currentThread().getName() + "ȡǮ�ɹ�:" +
	// drawamount + "RMB");
	// balence = balence - drawamount;
	// System.out.println(Thread.currentThread().getName() + "ȡǮ�����:" + balence
	// + "RMB");
	// } else {
	// System.out.println(Thread.currentThread().getName() + "ȡǮʧ������:" +
	// balence + "RMB");
	// }
	// }

	// �����Ǯ��sync ֻ��ȡǮsync�ͻ���ִ���
	// ȡǮ�Ľ��̱���Ǯ�����
	// ����Ҫ������sync
	// ��Ȼsync��������ǰ���� Ԭ�������account ���� û��sync�ķ������ǿ������ִ�е�Ŷ
	public synchronized void save(double saveamount) {
		notifyAll();
		balence = balence + saveamount;
		System.out.println(Thread.currentThread().getName() + "��Ǯ�����:" + balence + "RMB");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ���drawչʾ�˴�һ��ȡһ�� ��һ��ȡһ�� ��һ��ȡһ��
	public synchronized void draw(double drawamount) {
		notifyAll();
		balence = balence - drawamount;
		System.out.println(Thread.currentThread().getName() + "ȡǮ�����:" + balence + "RMB");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
