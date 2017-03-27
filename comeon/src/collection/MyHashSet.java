package collection;

public class MyHashSet<E> {
private SeparateChainingHashMap<E, Object> map;
private static final Object PRESENT=new Object(); 
public int size(){
	return map.getCurrentSize();
}
public MyHashSet() {
	map=new SeparateChainingHashMap<E, Object>();
	
}
public void add(E item){
	map.put(item, PRESENT);
}

}
