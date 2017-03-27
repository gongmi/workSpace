package CrazyJavaThread;

public class SaveThread implements Runnable {
	 
		private Account2 account;
		private double saveamount;

		public SaveThread(Account2 account, double saveamount) {

			this.account = account;
			this.saveamount = saveamount;
		}

		public void run() {
	 for (int i=0;i<10;i++)
			account.save(saveamount);

		}
	}
 
