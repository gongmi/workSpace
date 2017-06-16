package CrazyJavaThread;

public class TestATM   {
 

	public static void main(String[] args) throws InterruptedException {
		Account2 YQX = new Account2("‘¨∆‰œÈ",  0);
		DrawThread draw = new DrawThread(YQX, 18000);
		SaveThread save = new SaveThread(YQX, 18000);
		Thread t1 = new Thread(draw);
		 Thread t2 = new Thread(draw);
		Thread t3 = new Thread(save);
		t3.setName("qiansu");
		t1.setName("gongmi");
		 t2.setName("yuan");
		 t3.start();
		t1.start();
		
		
		
		Integer a=null;
		System.out.println((Integer)null);
		System.out.println(1==a);
		System.out.println(a instanceof Integer);
		System.out.println(new Integer(1)==a);
//		 t2.start();
		 
		
	}

}


