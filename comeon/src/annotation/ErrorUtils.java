package annotation;

import java.util.*;
public class ErrorUtils

{
	@SafeVarargs
	public static void faultyMethod(List<String>... listStrArray)
	{
		// Java���Բ��������������飬���listArrayֻ�ܱ�����List[]����
		// ��ʱ�൱�ڰ�List<String>������List���Ѿ������ˡ�������
		List[] listArray = listStrArray;
		List<Integer> myList = new ArrayList<Integer>();
		myList.add(6);
		// ��listArray�ĵ�һ��Ԫ�ظ�ΪmyList
		listArray[0] = myList;
		String s = listStrArray[0].get(0);
	}
}