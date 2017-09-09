package chapter12;

public class happensBefore {
	class ThreadA extends Thread {
		public void run() {
			new ThreadB().start();
		}
	}

	class ThreadB extends Thread {
		public void run() {

		}
	}

 
}
