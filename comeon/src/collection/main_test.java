package collection;

import java.util.*;

public class main_test {

	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<>();
		set.add(3);
		set.add(1);
		set.add(4);
		set.add(6);
		set.add(9);
		set.add(3);
		ConcurrentHashMap
		set.add(5);
		set.add(7);
		StringBuffer sb=new StringBuffer();
		sb.append("ase");
		StringBuilder sbb=new StringBuilder();
		sbb.append(false);
		String s="";
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			int i = it.next();
			System.out.println(i);
			if (i==6)
			it.remove();
		}
		Iterator<Integer> it2 = set.iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
	}

	public static <AnyType> void printLots(List<AnyType> L, List<Integer> P) {
		Iterator<AnyType> iterL = L.iterator();
		Iterator<Integer> iterP = P.iterator();
		AnyType itemL = null;
		Integer itemP = 0;
		int start = 0;
		while (iterL.hasNext() && iterP.hasNext()) {
			itemP = iterP.next();
			System.out.println("Looking for position " + itemP);
			while (start < itemP && iterL.hasNext()) {
				start++;
				itemL = iterL.next();
			}
			System.out.println(itemL);
		}
	}

	public static <AnyType extends Comparable<? super AnyType>> void intersection(List<AnyType> L1,
			List<AnyType> L2, List<AnyType> Intersect) {
		Iterator<AnyType> itL1 = L1.iterator();
		Iterator<AnyType> itL2 = L2.iterator();
		AnyType itemL1 = null, itemL2 = null;
		if (itL1.hasNext() && itL2.hasNext()) {
			itemL1 = itL1.next();
			itemL2 = itL2.next();
		}
		while (itemL1 != null && itemL2 != null)

		{
			int compareResult = itemL1.compareTo(itemL2);
			if (compareResult < 0) {
				Intersect.add(itemL1);
				itemL1 = itL1.hasNext() ? itL1.next() : null;
			} else if (compareResult == 0) {
				Intersect.add(itemL1);
				itemL1 = itL1.hasNext() ? itL2.next() : null;
				itemL2 = itL2.hasNext() ? itL2.next() : null;
			} else {
				Intersect.add(itemL2);
				itemL2 = itL2.hasNext() ? itL2.next() : null;
			}

		}
	}
}
