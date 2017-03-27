package CrazyJavaThread;

public class Account {
	private String accountNo;
	private double balence;

	public Account(String accountNo, double balence) {

		this.accountNo = accountNo;
		this.balence = balence;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalence() {
		return balence;
	}

	public void setBalence(double balence) {
		this.balence = balence;
	}

}
