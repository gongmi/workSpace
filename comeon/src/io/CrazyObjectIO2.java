package io;

import java.io.*;

import collection.*;
//对象引用的序列化
public class CrazyObjectIO2 {
	public static void main(String args[]) throws Exception {
		
		GM gm = new GM(23, "female");
		T t1 = new T(8,gm);
		T t2 = new T(8,gm);
		try (FileOutputStream fos = new FileOutputStream("d:\\GM\\testobjectio.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(t1);
			oos.writeObject(t2);
			oos.writeObject(gm);
			oos.writeObject(t2);
		}

		try (FileInputStream fis = new FileInputStream("d:\\GM\\testobjectio.txt");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			T tt1 = (T) ois.readObject();
			T tt2 = (T) ois.readObject();
			GM gm1 = (GM) ois.readObject();
			T tt3 = (T) ois.readObject();
			System.out.println(tt1);
			System.out.println(tt2);
			System.out.println(tt1.gm == tt2.gm);
			System.out.println(gm1);
			System.out.println(tt1.gm == gm1);
			System.out.println(tt3 == tt2);
		}
	}
}

class T implements Serializable {
	//改变类中的实例变量 产生的序列化版本就不一样
	private static final long serialVersionUID = 2556700383896091430L;
	//	private static final long serialVersionUID = 5562907163484036370L;
	int i = 10;
	int j = 9;
//	double dd = 2.3;
	double d = 2.3;
	transient int k;
	GM gm;

	public T(int k, GM gm) {
		super();
		this.k = k;
		this.gm = gm;
	}

	@Override
	public String toString() {
		return "T [i=" + i + ", j=" + j + ", d=" + d + ", k=" + k + ", gm=" + gm + "]";
	}

}