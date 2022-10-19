package dataStructures;

/**
 * Iterates a BST that has Lists as its' values
 * is part of dataStructures Package
 * 
 * @author Angelo Duarte (47427) <amv.duarte@campus.fct.unl.pt>
 * @author Marta Carlos (47592) <m.carlos@campus.fct.unl.pt>
 *
 */
public class BSTValueListOrderIterator<V, E> implements Iterator<E> {

	private static final long serialVersionUID = 1L;

	private Iterator<List<E>> treeIt;
	private Iterator<E> listIt;

	@SuppressWarnings("unchecked")
	public BSTValueListOrderIterator(Iterator<V> it) {
		treeIt = (Iterator<List<E>>) it;
		rewind();
	}

	@Override
	public boolean hasNext() {
		return listIt.hasNext();
	}

	@Override
	public E next() throws NoSuchElementException {

		if (!hasNext())
			throw new NoSuchElementException();

		E ret = listIt.next();
		getNext();
		
		return ret;
	}

	@Override
	public void rewind() {
		treeIt.rewind();
		listIt = treeIt.next().iterator();
	}

	/**
	 * Will search for the next List to iterate
	 */
	private void getNext() {
		if (!listIt.hasNext() && treeIt.hasNext())
			listIt = treeIt.next().iterator();
	}

}
