package classload;

import java.lang.reflect.*;

public class ArrayTest1 {
	public static void main(String args[]) {
			Object arr = Array.newInstance(String.class, 10);
			Array.set(arr, 5, "���Java����");
			Array.set(arr, 6, "������Java EE��ҵӦ��ʵս");
			Object book1 = Array.get(arr, 5);
			Object book2 = Array.get(arr, 6);
			System.out.println(book1);
			System.out.println(book2);
	}
}
