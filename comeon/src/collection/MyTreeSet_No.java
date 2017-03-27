package collection;

import java.util.Comparator;
import java.util.Iterator;

//代码来自数据结构与算法分析java
//二叉查找树 有父节点
//不使用自然排序 使用自定义排序 这样不用要求元素必须要实现Comparable接口
//但是在使用时必须传一个实现Comparator接口的东东进来
public class MyTreeSet_No<T> implements Iterable<T> {
	private static class BinaryNode<T> {

		// Friendly data; accessible by other package routines
		T element; // The data in the node
		BinaryNode<T> left; // Left child
		BinaryNode<T> right; // Right child
		BinaryNode<T> parent; // Right child
 

		BinaryNode(T theElement, BinaryNode<T> lt, BinaryNode<T> rt,
				BinaryNode<T> pt) {
			element = theElement;
			left = lt;
			right = rt;
			parent = pt;
		}

	}

	private BinaryNode<T> root;
	private Comparator<T> cmp;
	public MyTreeSet_No() {
		cmp=null;
	}
	//但是在使用时必须传一个实现Comparator接口的东东进来
	public MyTreeSet_No(Comparator<T> c) {
		cmp=c;
	}

	public void insert(T x) {
		root = insert(x, root, null);
	}

	public void remove(T x) {
		root = remove(x, root);
	}

	public T findMin() {
		return elementAt(findMin(root));
	}

	public T findMax() {
		return elementAt(findMax(root));
	}

	public T find(T x) {
		return elementAt(find(x, root));
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	private T elementAt(BinaryNode<T> t) {
		return  (t == null ? null : t.element);
	}

	private BinaryNode<T> insert(T x, BinaryNode<T> t, BinaryNode<T> pt) {
		/* 1 */if (t == null)

			/* 2 */t = new BinaryNode<T>(x, null, null, pt);

		/* 3 */else if (MyCompare(x,t.element) < 0)
			/* 4 */t.left = insert(x, t.left, t);
		/* 5 */else if (MyCompare(x,t.element) > 0)
			/* 6 */t.right = insert(x, t.right, t);
		/* 7 */else
			/* 8 */; // Duplicate; do nothing
		/* 9 */return t;
	}

	private BinaryNode<T> remove(T x, BinaryNode<T> t) {
		if (t == null)
			return t; // Item not found; do nothing
		if (MyCompare(x,t.element) < 0)
			t.left = remove(x, t.left);
		else if (MyCompare(x,t.element) > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else if(t.left != null && t.right == null) {
			BinaryNode<T> onechild =t.left;
            onechild.parent = t.parent;
			t = onechild; }
		else if(t.left == null && t.right!= null) {
			BinaryNode<T> onechild =t.right;
            onechild.parent = t.parent;
			t = onechild; }
		else
			t=null;
		
		return t;
	}

	private BinaryNode<T> findMin(BinaryNode<T> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	private BinaryNode<T> findMax(BinaryNode<T> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	private BinaryNode<T> find(T x, BinaryNode<T> t) {
		if (t == null)
			return null;
		if (MyCompare(x,t.element) < 0)
			return find(x, t.left);
		else if (MyCompare(x,t.element) > 0)
			return find(x, t.right);
		else
			return t; // Match
	}

	private int MyCompare(T x, T element) {
		if (cmp==null)
			return ((Comparable)x).compareTo(element);
		else 
			return cmp.compare(x, element);
	}
	private void printTree(BinaryNode<T> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}
public Iterator<T> iterator(){
	return new MyIterator();

}
private class MyIterator  implements Iterator<T>{
private BinaryNode<T> current = findMin(root);
private BinaryNode<T> previous;
private boolean atEnd=false;
public boolean hasNext() {
return (!atEnd);
	
}

@Override
public T next() {

previous=current;
if(current.right!=null)
	current=findMin(current.right);
else
	
{	BinaryNode<?> child=current;
	current=current.parent;
	
	while(current!=null&&current.right==child)
	{	child=current;
	current=current.parent;}
if (current==null)
	atEnd=true;
}

	return (T) previous.element;
}

@Override
public void remove() {
MyTreeSet_No.this.remove((T) previous.element);
			}
 
}
 

}




