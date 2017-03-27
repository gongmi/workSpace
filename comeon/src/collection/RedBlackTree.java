package collection;

//我把findmin findmax 什么的删掉了 和bst一样的 只保留了旋转部分
public class RedBlackTree<T extends Comparable<? super T>>
{
 public RedBlackTree( )
 {
     nullNode = new RedBlackNode<>( null,null,null );
     nullNode.left = nullNode.right = nullNode;
     header      = new RedBlackNode<>( null,null,null );
     header.left = header.right = nullNode;
 }
 private static class RedBlackNode<T>
 {
     RedBlackNode( T theElement, RedBlackNode<T> lt, RedBlackNode<T> rt )
     {
         element  = theElement;
         left     = lt;
         right    = rt;
         color    = RedBlackTree.BLACK;
     }

     T               element;    // The data in the node
     RedBlackNode<T> left;       // Left child
     RedBlackNode<T> right;      // Right child
     int             color;      // Color
 }
 
 private RedBlackNode<T> header;
 private RedBlackNode<T> nullNode;

 private static final int BLACK = 1;    // BLACK must be 1
 private static final int RED   = 0;

     // Used in insert routine and its helpers
 private RedBlackNode<T> current;
 private RedBlackNode<T> parent;
 private RedBlackNode<T> grand;
 private RedBlackNode<T> great;


 /**
  * Compare item and t.element, using compareTo, with
  * caveat that if t is header, then item is always larger.
  * This routine is called if is possible that t is header.
  * If it is not possible for t to be header, use compareTo directly.
  */
 private int compare( T item, RedBlackNode<T> t )
 {
     if( t == header )
         return 1;
     else
         return item.compareTo( t.element );    
 }
 
 /**
  * Insert into the tree.
  * @param item the item to insert.
  */
	public void insert(T item) {
		current = parent = grand = header;
		nullNode.element = item;

		while (compare(item, current) != 0) {
			great = grand;
			grand = parent;
			parent = current;
			current = compare(item, current) < 0 ? current.left : current.right;

			// Check if two red children; fix if so
			if (current.left.color == RED && current.right.color == RED)
				handleReorient(item);
		}

		// Insertion fails if already present
		if (current != nullNode)
			return;
		current = new RedBlackNode<>(item, nullNode, nullNode);

		// Attach to parent
		if (compare(item, parent) < 0)
			parent.left = current;
		else
			parent.right = current;
		handleReorient(item);
	}

 /**
  * Internal routine that is called during an insertion
  * if a node has two red children. Performs flip and rotations.
  * @param item the item being inserted.
  */
 private void handleReorient( T item )
 {
         // Do the color flip
     current.color = RED;
     current.left.color = BLACK;
     current.right.color = BLACK;

     if( parent.color == RED )   // Have to rotate
		{
			grand.color = RED;
			if ((compare(item, grand) < 0) != (compare(item, parent) < 0))
				parent = rotate(item, grand); // Start dbl rotate
			current = rotate(item, great);
			current.color = BLACK;
		}
     header.right.color = BLACK; // Make root black
 }

 /**
  * Internal routine that performs a single or double rotation.
  * Because the result is attached to the parent, there are four cases.
  * Called by handleReorient.
  * @param item the item in handleReorient.
  * @param parent the parent of the root of the rotated subtree.
  * @return the root of the rotated subtree.
  */
 private RedBlackNode<T> rotate( T item, RedBlackNode<T> parent )
 {
     if( compare( item, parent ) < 0 )
         return parent.left = compare( item, parent.left ) < 0 ?
             rotateWithLeftChild( parent.left )  :  // LL
             rotateWithRightChild( parent.left ) ;  // LR
     else
         return parent.right = compare( item, parent.right ) < 0 ?
             rotateWithLeftChild( parent.right ) :  // RL
             rotateWithRightChild( parent.right );  // RR
 }

 private RedBlackNode<T> rotateWithLeftChild( RedBlackNode<T> k2 )
 {
     RedBlackNode<T> k1 = k2.left;
     k2.left = k1.right;
     k1.right = k2;
     return k1;
 }

 private RedBlackNode<T> rotateWithRightChild( RedBlackNode<T> k1 )
 {
     RedBlackNode<T> k2 = k1.right;
     k1.right = k2.left;
     k2.left = k1;
     return k2;
 }



}
