package dataStructures;

/**
 * Iterates a BST's Entries
 * is part of dataStructures Package
 * 
 * @author Angelo Duarte (47427) <amv.duarte@campus.fct.unl.pt>
 * @author Marta Carlos (47592) <m.carlos@campus.fct.unl.pt>
 *
 */
public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 1L;

	private BSTNode<K, V> root;
	private Stack<BSTNode<K, V>> stack;


	public BSTKeyOrderIterator(BSTNode<K, V> root) {
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!hasNext())
			throw new NoSuchElementException();

		BSTNode<K, V> node = stack.pop();

		if (node.getRight() != null)
			stackLeftChild(node.getRight());

		return node.getEntry();
	}

	@Override
	public void rewind() {
		stack = new StackInList<BSTNode<K, V>>();
		stackLeftChild(root);
	}

	/**
	 * If the <code>node</code> has a left child, both the node and its' child will be put in the <code>stack</code>
	 * this will continue in a cycle until the node is null 
	 * @param node node that we want to stack
	 */
	private void stackLeftChild(BSTNode<K, V> node) {
		BSTNode<K, V> father = node;
		while (father != null) {
			stack.push(father);
			father = father.getLeft();
		}
	}

}
