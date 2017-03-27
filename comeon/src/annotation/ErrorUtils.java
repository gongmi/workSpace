package annotation;

import java.util.*;
public class ErrorUtils

{
	@SafeVarargs
	public static void faultyMethod(List<String>... listStrArray)
	{
		// Java语言不允许创建泛型数组，因此listArray只能被当成List[]处理
		// 此时相当于把List<String>赋给了List，已经发生了“擦除”
		List[] listArray = listStrArray;
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(6);
		// 把listArray的第一个元素赋为myList
		listArray[0] = myList;
		String s = listStrArray[0].get(0);
	}
}