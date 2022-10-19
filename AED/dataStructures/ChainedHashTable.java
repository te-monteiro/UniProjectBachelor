package dataStructures;

/**
 * Chained Hash table implementation
 * 
 * @author AED Team
 * @version 1.0
 * @param <K>
 *            Generic Key, must extend comparable
 * @param <V>
 *            Generic Value
 */

public class ChainedHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty chained hash table, with the specified initial
	 * capacity. Each position of the array is initialized to a new ordered list
	 * maxSize is initialized to the capacity.
	 * 
	 * @param capacity
	 *            defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public ChainedHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];
		for (int i = 0; i < arraySize; i++)
			table[i] = new OrderedDoubleList<K, V>();
		maxSize = capacity;
		currentSize = 0;
	}

	public ChainedHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key
	 *            to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		V ret;
		if (this.isFull())
			this.rehash();
		ret = table[this.hash(key)].insert(key, value);
		if (ret == null)
			currentSize++;
		return ret;
	}

	@Override
	public V remove(K key) {
		V ret;
		ret = table[this.hash(key)].remove(key);
		if (ret != null)
			currentSize--;
		// returns null if the list is empty or there was no node with the
		// specified key
		return ret;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new HashTableIterator<K, V>(table);
	}

	@SuppressWarnings("unchecked")
	protected void rehash() {

		int arraySize = HashTable.nextPrime((int) (1.1 * this.size()));
		Dictionary<K, V>[] newTable = (Dictionary<K, V>[]) new Dictionary[arraySize];
		Iterator<Entry<K, V>> it = this.iterator();

		for (int i = 0; i < arraySize; i++)
			newTable[i] = new OrderedDoubleList<K, V>();
		maxSize = arraySize;

		while (it.hasNext()) {
			Entry<K, V> current = it.next();
			int j = Math.abs(current.getKey().hashCode()) % newTable.length;
			newTable[j].insert(current.getKey(), current.getValue());
		}

		table = newTable;
	}
}
