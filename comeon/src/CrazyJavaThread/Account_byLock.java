package CrazyJavaThread;

//��Lockʵ��ͬ����Account��
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

	// �ṩһ���̰߳�ȫdraw()���������ȡǮ����
	public void draw(double drawAmount) {
		// ����
		lock.lock();
		try {
			// �˻�������ȡǮ��Ŀ
			if (balance >= drawAmount) {
				// �³���Ʊ
				System.out.println(Thread.currentThread().getName() + "ȡǮ�ɹ����³���Ʊ:" + drawAmount);
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				// �޸����
				balance -= drawAmount;
				System.out.println("\t���Ϊ: " + balance);
			} else {
				System.out.println(Thread.currentThread().getName() + "ȡǮʧ�ܣ����㣡");
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