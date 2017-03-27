package collection;

import java.util.*;




public class Main {


    // Simple main
public static void main( String [ ] args )
{
//	 TreeSet <Eddie>edd=new TreeSet<Eddie>();
	Eddie e1= new Eddie(5,"1");
	Eddie e2= new Eddie(2,"2");
	Eddie e3= new Eddie(9,"3");
	Eddie e4= new Eddie(6,"4");
 MyTreeSet<GM> mm=new  MyTreeSet<>();
 Comparator<Eddie> c=new Mycomparator();
 MyTreeSet_No<Eddie> ee=new  MyTreeSet_No<>(c);
 ee.insert(e2);
 ee.insert(e1);
 ee.insert(e4);
 ee.insert(e3);
 System.out.println(ee.findMax());
 System.out.println(ee.findMin());
 /*
	 Myarraylist<Integer> list =new Myarraylist<>();
	 list.add(1);
	 list.add(2);
	 list.add(3);
	 list.add(4);
	 list.add(5);
	 list.add(6);
	 Map<Eddie,GM> map=new HashMap<Eddie,GM>();
	 map.put(e1,new GM(1,"haha"));
	 map.put(e2,new GM(2,"haha"));
	 map.put(e1,new GM(3,"haha"));
	 map.put(e2,new GM(4,"haha"));
	 System.out.println(map);
	 System.out.println(list);
	 System.out.println(list.get(4));
	 Myarraylist<Integer> listbig =new Myarraylist<Integer>();
	 
	 
	HashSet <Integer> hs=new HashSet<Integer>();
	hs.add(1);
 
	listbig.add(9);
	listbig.add(8);
	listbig.add(7);
	
	listbig.addAll(list);
	 System.out.println(listbig);
	 ListIterator<Integer> it= listbig.iterator();
	 while (it.hasNext())
		 System.out.println(it.next());
	 while(it.hasPrevious())
		 System.out.println(it.previous());
		 */
}


}



