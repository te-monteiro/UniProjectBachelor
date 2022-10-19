package dataStructures;

public class BSTBreadthFirstIterator<K,V> implements Iterator<Entry<K,V>> {

	private static final long serialVersionUID = 1L;
	
	private BSTNode<K,V> root;
	private Queue<BSTNode<K,V>> queue;
	
	public BSTBreadthFirstIterator ( BSTNode<K,V> root ) {
		this.root = root;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		
		if ( !hasNext() )
			throw new NoSuchElementException();
		
		BSTNode<K,V> father = queue.dequeue();
		if ( !father.isLeaf() ){
			if( father.getLeft() != null )
				queue.enqueue(father.getLeft());
			if( father.getRight() != null )
				queue.enqueue(father.getRight());
		}
		
		return father.getEntry();
	}

	@Override
	public void rewind() {
		queue = new QueueInList<BSTNode<K,V>>();
		queue.enqueue(root);
	}

	
	
}
