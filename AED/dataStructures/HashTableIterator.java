package dataStructures;

public class HashTableIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 1L;

	Dictionary<K, V>[] table;
	Iterator<Entry<K, V>> itCurrent;
	int current;

	public HashTableIterator(Dictionary<K, V>[] table) {
		this.table = table;
		this.rewind();
	}

	@Override
	public boolean hasNext() {
		return itCurrent.hasNext();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!this.hasNext())
			throw new NoSuchElementException();

		Entry<K, V> e = itCurrent.next();
			
		findNext();
		return e;
	}

	@Override
	public void rewind() {
		itCurrent = table[0].iterator();
		current = 0;
		findNext();
		
	}

	/**
	 * Gets the next not empty Iterator
	 */
	private void findNext () {
		if (!itCurrent.hasNext()) {
			while (++current != table.length) {
				itCurrent = table[current].iterator();
				if (itCurrent.hasNext())
					break;
			}
		}
	}
	
}
