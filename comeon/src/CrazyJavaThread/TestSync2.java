package CrazyJavaThread;

public class TestSync2 implements Runnable {
	private Account2 account;
	private double drawamount;

	public TestSync2(Account2 account, double drawamount) {

		this.account = account;
		this.drawamount = drawamount;
	}

	public void run() {
		account.draw(drawamount);
	}

	public static void main(String[] args) {
		Account2 YQX = new Account2("‘¨∆‰œÈ", 20000);
		TestSync2 test = new TestSync2(YQX, 18000);
		Thread t1 = new Thread(test);
		Thread t2 = new Thread(test);
		t1.setName("gongmi");
		t2.setName("yuan");
		t1.start();
		t2.start();
	}

}
