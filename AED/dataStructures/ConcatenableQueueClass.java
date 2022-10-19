package dataStructures;

public class ConcatenableQueueClass <E> extends QueueInList<E> implements ConcatenableQueue <E>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConcatenableQueueClass () {
		super();
	}

	@Override
	public void append(ConcatenableQueue<E> queue) {
		if ( queue instanceof ConcatenableQueue<?>){
			//aqui o codigo nao e constante
			
	
		}
	}
	
}
