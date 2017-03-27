package collection;
//代码来自数据结构与算法分析java
//二叉查找树 没有父节点
public class BiSearchTree {
private static class BinaryNode {
		Comparable element;  
		BinaryNode left;  
		BinaryNode right;  
		
		BinaryNode(Comparable theElement) {
			this(theElement, null, null);
		}

		BinaryNode(Comparable theElement, BinaryNode lt, BinaryNode rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

	}

	/** The tree root. */
	private BinaryNode root;

	public BiSearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(Comparable x) {
		root = insert(x, root);
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(Comparable x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 */
	public Comparable findMin() {
		return elementAt(findMin(root));
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 */
	public Comparable findMax() {
		return elementAt(findMax(root));
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return the matching item or null if not found.
	 */
	public Comparable find(Comparable x) {
		return elementAt(find(x, root));
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	/**
	 * Internal method to get element field.
	 * 
	 * @param t
	 *            the node.
	 * @return the element field or null if t is null.
	 */
	private Comparable elementAt(BinaryNode t) {
		return t == null ? null : t.element;
	}

	private BinaryNode insert(Comparable x, BinaryNode t) {
		/* 1 */if (t == null)
			/* 2 */t = new BinaryNode(x, null, null);
		/* 3 */else if (x.compareTo(t.element) < 0)
			/* 4 */t.left = insert(x, t.left);
		/* 5 */else if (x.compareTo(t.element) > 0)
			/* 6 */t.right = insert(x, t.right);
		/* 7 */else
			/* 8 */; // Duplicate; do nothing
		/* 9 */return t;
	}

	private BinaryNode remove(Comparable x, BinaryNode t) {
		if (t == null)
			return t; // Item not found; do nothing
		if (x.compareTo(t.element) < 0)
			t.left = remove(x, t.left);
		else if (x.compareTo(t.element) > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	private BinaryNode findMin(BinaryNode t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	private BinaryNode findMax(BinaryNode t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	private BinaryNode find(Comparable x, BinaryNode t) {
		if (t == null)
			return null;
		if (x.compareTo(t.element) < 0)
			return find(x, t.left);
		else if (x.compareTo(t.element) > 0)
			return find(x, t.right);
		else
			return t; // Match
	}

	private void printTree(BinaryNode t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	// Test program

}
