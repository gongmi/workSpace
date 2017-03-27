package classload;

import java.lang.reflect.*;
import java.lang.annotation.*;

public class CrazyitArray {
	// 对Array的newInstance方法进行包装
	@SuppressWarnings("unchecked")
	public static <T> T[] newInstance(Class<T> componentType, int length) {
		return (T[]) Array.newInstance(componentType, length); // ①
	}

	public static void main(String[] args) {
		String[] arr = CrazyitArray.newInstance(String.class, 10);
		int[][] intArr = CrazyitArray.newInstance(int[].class, 5);
		arr[5] = "疯狂Java讲义";
		intArr[1] = new int[] { 23, 12 };
		System.out.println(arr[5]);
		System.out.println(intArr[1][1]);
	}
}
