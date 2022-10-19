package dataStructures;

/**
 * Iterates a BST's values
 * is part of dataStructures Package
 * 
 * @author Angelo Duarte (47427) <amv.duarte@campus.fct.unl.pt>
 * @author Marta Carlos (47592) <m.carlos@campus.fct.unl.pt>
 *
 */
public class BSTValueOrderIterator<K, V> implements Iterator<V> {

	private static final long serialVersionUID = 1L;

	private Iterator<Entry<K, V>> it;

	public BSTValueOrderIterator(BSTNode<K, V> root) {
		it = new BSTKeyOrderIterator<K, V>(root);
		rewind();
	}

	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public V next() throws NoSuchElementException {
		
		if(!hasNext())
			throw new NoSuchElementException();
		
		return it.next().getValue();
	}

	@Override
	public void rewind() {
		it.rewind();
	}

}
