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

	// // 这个draw是正常的draw
	// public synchronized void draw(double drawamount) {
	//
	// if (balence >= drawamount) {
	// System.out.println(Thread.currentThread().getName() + "取钱成功:" +
	// drawamount + "RMB");
	// balence = balence - drawamount;
	// System.out.println(Thread.currentThread().getName() + "取钱后余额:" + balence
	// + "RMB");
	// } else {
	// System.out.println(Thread.currentThread().getName() + "取钱失败余额不足:" +
	// balence + "RMB");
	// }
	// }

	// 如果存钱不sync 只有取钱sync就会出现错误
	// 取钱的进程被存钱打断了
	// 所以要两个都sync
	// 虽然sync是锁定当前对象 袁其祥这个account 但是 没有sync的方法还是可以随便执行的哦
	public synchronized void save(double saveamount) {
		notifyAll();
		balence = balence + saveamount;
		System.out.println(Thread.currentThread().getName() + "存钱后余额:" + balence + "RMB");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 这个draw展示了存一下取一下 存一下取一下 存一下取一下
	public synchronized void draw(double drawamount) {
		notifyAll();
		balence = balence - drawamount;
		System.out.println(Thread.currentThread().getName() + "取钱后余额:" + balence + "RMB");
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
