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
			System.out.println(Thread.currentThread().getName() + "取钱成功:" + drawamount + "RMB");
			account.setBalence(account.getBalence() - drawamount);

			// 如果gongmi取钱到这里 线程转给 yuan也取钱 那么余额还没改 那么yuan也可以取到
			System.out.println(Thread.currentThread().getName() + "取钱后余额:" + account.getBalence()
					+ "RMB");
		} else {
			System.out.println(Thread.currentThread().getName() + "取钱失败余额不足:"
					+ account.getBalence() + "RMB");
		}
		// }
	}

	public static void main(String[] args) {
		Account YQX = new Account("袁其祥", 20000);
		TestSync test = new TestSync(YQX, 18000);
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("gongmi");
		t2.setName("yuan");
		t1.start();
		t2.start();
	}

}
