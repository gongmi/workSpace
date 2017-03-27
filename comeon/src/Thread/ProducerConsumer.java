package Thread;
//������
public class ProducerConsumer {
	public static void main(String[] args) {
	
		SyncStack ss = new SyncStack();
		
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		//��Щ�̶߳��Ƕ� ss����������pop����push����
		//ÿһ��ʱ��ֻ����һ���̲߳���ss
		Thread p_th1 = new Thread(p);
		Thread p_th2 = new Thread(p);
		Thread p_th3 = new Thread(p);
		Thread c_th1 =new Thread(c);
		Thread c_th2 =new Thread(c);
		p_th1.setName("����1");
		p_th2.setName("����2");
		p_th3.setName("����3");
		c_th1.setName("���1");
		c_th2.setName("���2");
		p_th1.start();
		p_th2.start();
		p_th3.start();
		c_th1.start();
		c_th2.start();
	}
}
//��ͷ����
class WoTou {
	int id;

	WoTou(int id) {
		this.id = id;
	}

	public String toString() {
		return ""+id;
	}
}
//¨�ӵ��� ��һ��ջ ֻ��װ6����ͷ ���������synchronized�Ķ��󣡣�
class SyncStack {
	int index = 0;
	WoTou[] arrWT = new WoTou[6];
	
	
	//������ͷ
	public synchronized void push(String s,WoTou wt) {
		
		
		while (index == arrWT.length) {
			try {
				System.out.println(s+"Ҫ�ȴ���" );
				this.wait();
				System.out.println(s+"������" );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		arrWT[index] = wt;
		index++;
		System.out.println(s+"����λ�ã�"+index+"���Ϊ:" + wt);
		// this.notify();//��ֻ��notify wait��pop�Ľ��� ����
		 //ʹ�������ͬʱnotify wait��push�Ľ��� ��ô�죿
	}
	//����ͷ
	public synchronized  void pop(String s)  {
	
		while (index == 0) {
			try {
				System.out.println(s+"Ҫ�ȴ���" );
				this.wait();
				System.out.println(s+"������" );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
			index--;
		System.out.println(s+"����λ��Ϊ"+(index+1)+"���Ϊ:" + arrWT[index]);
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