package io;

import java.io.*;

import collection.*;

public class CrazyObjectIO {
	public static void main(String args[]) {
		TT tt = new TT(8, 9.5, "gm");
		try (FileOutputStream fos = new FileOutputStream("d:\\GM\\CrazyObjectIO.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(tt);
			oos.writeObject(tt);//已经序列化过的就不会再序列化了 有序列化编号
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (FileInputStream fis = new FileInputStream("d:\\GM\\CrazyObjectIO.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			TT t1 = (TT) ois.readObject();
			TT t2 = (TT) ois.readObject();
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(t1 == t2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class TT implements Serializable {
	private static final long serialVersionUID = 1025L;
	int i;
	double d;
	String s = "hello";

	public TT(int i, double d, String s) {
		System.out.println("反序列时不会调用构造器");
		this.i = i;
		this.d = d;
		this.s = s;
	}

	@Override
	public String toString() {
		return "TT [i=" + i + ", d=" + d + ", s=" + s + "]";
	}

}