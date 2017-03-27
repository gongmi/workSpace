package collection;

import java.util.Comparator;

public class Mycomparator implements Comparator<Eddie>{

	@Override
	public int compare(Eddie e1, Eddie e2) {
		return Integer.parseInt(e1.getS())>Integer.parseInt(e2.getS())?1:(Integer.parseInt(e1.getS())==Integer.parseInt(e2.getS())?0:-1);
	 //根据eddie的string的大小来对比大小
	}

//	Eddie e1= new Eddie(5,"1");
//	Eddie e2= new Eddie(2,"2");
//	Eddie e3= new Eddie(9,"3");
//	Eddie e4= new Eddie(6,"4");
// 
// MyTreeSet<GM> mm=new  MyTreeSet<GM>();
// Comparator<Eddie> c=new Mycomparator();
// MyTreeSet_No<Eddie> ee=new  MyTreeSet_No<Eddie>(c);
// ee.insert(e2);
// ee.insert(e1);
// ee.insert(e4);
// ee.insert(e3);
// System.out.println(ee.findMax());
// System.out.println(ee.findMin());
 
	
}
