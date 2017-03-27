package CrazyJavaThread;

//用Lock实现同步的Account类
import java.util.concurrent.locks.*;

public class Account_byLock {
	private final ReentrantLock lock = new ReentrantLock();
	private String accountNo;
	private double balance;

	public Account_byLock() {
	}

	public Account_byLock(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public double getBalance() {
		return this.balance;
	}

	// 提供一个线程安全draw()方法来完成取钱操作
	public void draw(double drawAmount) {
		// 加锁
		lock.lock();
		try {
			// 账户余额大于取钱数目
			if (balance >= drawAmount) {
				// 吐出钞票
				System.out.println(Thread.currentThread().getName() + "取钱成功！吐出钞票:" + drawAmount);
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				// 修改余额
				balance -= drawAmount;
				System.out.println("\t余额为: " + balance);
			} else {
				System.out.println(Thread.currentThread().getName() + "取钱失败！余额不足！");
			}
		} finally {
			lock.unlock();
		}
	}

	public int hashCode() {
		return accountNo.hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj != null && obj.getClass() == Account.class) {
			Account target = (Account) obj;
			return target.getAccountNo().equals(accountNo);
		}
		return false;
	}
}