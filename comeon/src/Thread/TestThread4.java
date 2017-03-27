package Thread;
public class TestThread4 {	//怎么正确停止一个线程 ！！！！
	public static void main(String args[]){
		Runner4 r = new Runner4();
       	Thread t = new Thread(r);
        t.start();
        for(int i=0;i<100000;i++){
        	if(i%10000==0 & i>0)
        		System.out.println("in thread main i=" + i);
        }
        System.out.println("Thread main is over");
        r.shutDown();
        //t.stop();
    }
}

class Runner4 implements Runnable {
  private boolean flag=true;
  
	public void run() {
		int i = 0;
		while (flag==true) {
			System.out.println(" " + i++);	
		}
	}
	
  public void shutDown() {
		flag = false;
  }
}