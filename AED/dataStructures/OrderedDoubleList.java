package dataStructures;

public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Node at the head of the list.
	 */
	protected DListNode<Entry<K, V>> head;

	/**
	 * Node at the tail of the list.
	 */
	protected DListNode<Entry<K, V>> tail;

	/**
	 * Number of elements in the list.
	 */
	protected int currentSize;

	/**
	 * Constructor of an empty OrderedDoubleList head and tail are initialized
	 * as null. currentSize is initialized as 0.
	 */
	public OrderedDoubleList() {
		head = null;
		tail = null;
		currentSize = 0;
	}

	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public V find(K key) {

		if (this.isEmpty())
			return null;

		DListNode<Entry<K, V>> node = findNode(key);

		if (node == null)
			return null;

		return node.getElement().getValue();
	}

	@Override
	public V insert(K key, V value) {

		DListNode<Entry<K, V>> newNode;

		if (this.isEmpty()) {
			newNode = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, null);
			head = newNode;
			tail = newNode;
			currentSize++;

			return null;
		}
		if (key.compareTo(head.getElement().getKey()) < 0) {
			// In case the key is smaller than the head's key
			addFirst(key, value);
			return null;
		}
		if (key.compareTo(tail.getElement().getKey()) > 0) {
			// In case the key is bigger than the tail's key
			addLast(key, value);
			return null;
		}

		// In case it's somewhere in the list
		return addMiddle(key, value);
	}

	@Override
	public V remove(K key) {

		if (this.isEmpty())
			return null;

		DListNode<Entry<K, V>> node = findNode(key);

		if (node == null)
			return null;
		if (this.size() == 1) {
			head = null;
			tail = null;
		} else if (node.equals(head)) {
			head = head.getNext();
			head.setPrevious(null);
		} else if (node.equals(tail)) {
			tail = tail.getPrevious();
			tail.setNext(null);
		} else {
			node.getPrevious().setNext(node.getNext());
			node.getNext().setPrevious(node.getPrevious());
		}
		currentSize--;

		return node.getElement().getValue();
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();

		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();

		return tail.getElement();
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}

	/***
	 * Finds the node with <code>key</code> as its' key pre: !isEmpty()
	 * 
	 * @param key
	 * @return DLisNode we're looking for
	 */
	protected DListNode<Entry<K, V>> findNode(K key) {

		DListNode<Entry<K, V>> current = head;
		do {
			if (key.compareTo(current.getElement().getKey()) == 0)
				return current;

			current = current.getNext();
		} while (current != null);

		return null;
	}

	/**
	 * Adds a new node with its' element a Entry with the specified <code>key</code> and <code>value</value>
	 * the new node will be new head and it will be linked to the old head
	 * @param key key of entry
	 * @param value value of the entry
	 */
	private void addFirst(K key, V value) {
		DListNode<Entry<K, V>> newNode;
		newNode = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), null, head);
		newNode.setNext(head);
		head = newNode;
		currentSize++;
	}
	
	/**
	 * Adds a new node with its' element a Entry with the specified <code>key</code> and <code>value</value>
	 * the new node will be the new tail and it will be linked to the old tail
	 * @param key key of entry
	 * @param value value of the entry
	 */
	private void addLast(K key, V value) {
		DListNode<Entry<K, V>> newNode;

		newNode = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), tail, null);
		tail.setNext(newNode);
		tail = newNode;
		currentSize++;
	}

	/**
	 * Adds a new node with its' element a Entry with the specified <code>key</code> and <code>value</value>
	 * using the compareTo method, addMiddle will search for the node with a higher compareTo key 
	 * than the one we want to insert
	 * @param key
	 * @param value
	 * @return
	 */
	private V addMiddle(K key, V value) {
		DListNode<Entry<K, V>> newNode;
		DListNode<Entry<K, V>> current = head;

		do {
			int compRe = key.compareTo(current.getElement().getKey());
			if (compRe == 0) {
				V oldV = current.getElement().getValue();
				current.setElement(new EntryClass<K, V>(key, value));
				return oldV;
			}
			if (compRe < 0) {
				newNode = new DListNode<Entry<K, V>>(new EntryClass<K, V>(key, value), current.getPrevious(), current);
				current.getPrevious().setNext(newNode);
				current.setPrevious(newNode);
				currentSize++;
				break;
			}
			current = current.getNext();
		} while (current != null);
		return null;
	}

}
