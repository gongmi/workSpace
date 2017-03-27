package CrazyJavaThread;

public class DrawThread implements Runnable{
	 
		private Account2 account;
		private double drawamount;

		public DrawThread(Account2 account, double drawamount) {

			this.account = account;
			this.drawamount = drawamount;
		}

		public void run() {
		for (int i=0;i<10;i++)
			account.draw(drawamount);

		}
}
