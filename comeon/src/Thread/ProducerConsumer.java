package Thread;
//主函数
public class ProducerConsumer {
	public static void main(String[] args) {
	
		SyncStack ss = new SyncStack();
		
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		//这些线程都是对 ss这个对象进行pop或者push操作
		//每一个时候只能有一个线程操作ss
		Thread p_th1 = new Thread(p);
		Thread p_th2 = new Thread(p);
		Thread p_th3 = new Thread(p);
		Thread c_th1 =new Thread(c);
		Thread c_th2 =new Thread(c);
		p_th1.setName("厂商1");
		p_th2.setName("厂商2");
		p_th3.setName("厂商3");
		c_th1.setName("龚宓1");
		c_th2.setName("龚宓2");
		p_th1.start();
		p_th2.start();
		p_th3.start();
		c_th1.start();
		c_th2.start();
	}
}
//馒头的类
class WoTou {
	int id;

	WoTou(int id) {
		this.id = id;
	}

	public String toString() {
		return ""+id;
	}
}
//篓子的类 是一个栈 只能装6个馒头 这个东西是synchronized的对象！！
class SyncStack {
	int index = 0;
	WoTou[] arrWT = new WoTou[6];
	
	
	//生产馒头
	public synchronized void push(String s,WoTou wt) {
		
		
		while (index == arrWT.length) {
			try {
				System.out.println(s+"要等待了" );
				this.wait();
				System.out.println(s+"唤醒了" );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		arrWT[index] = wt;
		index++;
		System.out.println(s+"产了位置："+index+"编号为:" + wt);
		// this.notify();//我只想notify wait在pop的进程 可是
		 //使用这个会同时notify wait在push的进程 怎么办？
	}
	//吃馒头
	public synchronized  void pop(String s)  {
	
		while (index == 0) {
			try {
				System.out.println(s+"要等待了" );
				this.wait();
				System.out.println(s+"唤醒了" );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
			index--;
		System.out.println(s+"吃了位置为"+(index+1)+"编号为:" + arrWT[index]);
		this.notify();
		
	}
}

class Producer implements Runnable {
	SyncStack ss = null;
	int i=1;
	Producer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		while (true) {
			WoTou wt = new WoTou(i);
			i++;
			String s=Thread.currentThread().getName();
			ss.push(s,wt);
			
			try {
				Thread.sleep((int) (100));
				//Math.random() * 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	SyncStack ss = null;

	Consumer(SyncStack ss) {
		this.ss = ss;
	}

	public void run() {
		while(true) {
			String s=Thread.currentThread().getName();
			ss.pop(s);
			
			try {
				Thread.sleep((int) (200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}