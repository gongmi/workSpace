package Thread;

import java.util.*;

public class TestInterrupt {
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		thread.start();
		try {
			Thread.sleep(10000); //让main睡十秒钟 让thread线程运行
		}// 10秒钟后
		catch (InterruptedException e) {
		}
//		thread.interrupt();// 打断thread线程的睡眠 interrupt它 
		thread.flag=false; //这才是正确的打断方式
	}
}

class MyThread extends Thread {
	boolean flag = true;

	public void run() {
		while (flag) {
			System.out.println("===" + new Date() + "===");
			try {
				sleep(1000);// 每一秒钟打印一个时间
			} catch (InterruptedException e) {//检测到interrupt 结束run方法
				return;
			}
		}
	}

//public void run() {
//    while (true) {
//      String temp = new Date().toString();
//      String t = temp.substring(11, temp.indexOf('C'));
//      t = t.trim();
//      String[] time = t.split(":");
//      if (time.length == 3) {
//        System.out.println("现在是"+ time[0] + "点" +time[1] + "分" + time[2] + "秒");
//      }
//      try {
//        Thread.sleep(5000);
//      } catch (InterruptedException e) {
//        return;
//      }  
//    }
//  }
}
