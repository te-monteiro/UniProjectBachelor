package dataStructures;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeTest {

	  @Test
	    public void insertSimpleTest() { // teste de iterador
	        BinarySearchTree<Integer, Integer> od = new BinarySearchTree<>();

	        od.insert(5, 5); // insercao a cabeca
	        od.insert(10, 10); // insercao a cauda
	        od.insert(-3, -3); // insercao a cabeca
	        od.insert(4, 4); // insercao do segundo
	        od.insert(9, 9); // insercao do penultimo
	        od.insert(7, 77); // insercao no meio
	        od.insert(7, 7); // substituição do valor

	        assertEquals(od.size(), 6);

	        @SuppressWarnings("unchecked")
			BSTKeyOrderIterator<Integer, Integer> it =  (BSTKeyOrderIterator<Integer, Integer>) od.iterator();

	        assertTrue(it.hasNext());

	      
	        System.out.println(it.next().getValue());
	        System.out.println(it.next().getValue());
	        System.out.println(it.next().getValue());
	        System.out.println(it.next().getValue());
	        System.out.println(it.next().getValue());
	        System.out.println(it.next().getValue());
	        
	       /** int i = next.getValue();
	        assertEquals(i, -3);

	        next = it.next();
	        System.out.println(next.getValue());
	        i = next.getValue();
	        assertEquals(i, 4);

	        next = it.next();
	        System.out.println(next.getValue());
	        i = next.getValue();
	        assertEquals(i, 5);

	        next = it.next();
	        System.out.println(next.getValue());

	        assertTrue(next.getValue() == 7);
	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 9);

	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 10);*/

	    }

	    @Test
	    public void removeSimpleTestOne() {

	    	BinarySearchTree<Integer, Integer> od = new BinarySearchTree<>();

	        od.insert(5, 5);
	        od.insert(10, 10);
	        od.insert(-3, -3);
	        od.insert(4, 4);
	        od.insert(9, 9);
	        od.insert(7, 77);
	        od.insert(7, 7);

	        od.remove(9);

	        assertTrue(od.root.getValue() == 5);
	        assertTrue(od.root.getRight().getValue() == 7);
	        assertTrue(od.root.getRight().getRight().getValue() == 10);
	        assertTrue(od.root.getLeft().getValue() == -3);
	        assertTrue(od.root.getLeft().getRight().getValue() == 4);


	        @SuppressWarnings("unchecked")
			BSTKeyOrderIterator<Integer, Integer> it = (BSTKeyOrderIterator<Integer, Integer>) od.iterator();

	        assertTrue(it.hasNext());

	        Entry<Integer, Integer> next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == -3);

	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 4);

	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 5);

	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 7);

	        next = it.next();
	        System.out.println(next.getValue());
	        assertTrue(next.getValue() == 10);

	    }

	    @Test
	    public void removeSimpleTesttwo () {

	    	BinarySearchTree<Integer, Integer> od = new BinarySearchTree<>();

	        od.insert(5, 5);
	        od.insert(10, 10);
	        od.insert(3, 3);
	        od.insert(4, 4);
	        od.insert(9, 9);
	        od.insert(7, 7);

	        od.remove(4);
	        od.remove(3);

	        assertTrue(od.root.getValue() == 9);
	        assertTrue(od.root.getLeft().getValue() == 5);
	        assertTrue(od.root.getLeft().getRight().getValue() == 7);
	        assertTrue(od.root.getRight().getValue() == 10);




	    }

	    @Test
	    public void removeSimpleTestthree () { //remove head

	    	BinarySearchTree<Integer, Integer> od = new BinarySearchTree<>();

	        od.insert(5, 5);
	        od.insert(10, 10);
	        od.insert(3, 3);
	        od.insert(4, 4);
	        od.insert(9, 9);
	        od.insert(7, 77);
	        od.insert(7, 7);

	        od.remove(5);

	        assertTrue(od.root.getValue() == 4);
	        assertTrue(od.root.getRight().getValue() == 9);
	        assertTrue(od.root.getRight().getLeft().getValue() == 7);
	        assertTrue(od.root.getRight().getRight().getValue() == 10);
	        assertTrue(od.root.getLeft().getValue() == 3);

	    }

	    @Test
	    public void removeSimpleTestfour () { //remove head

	    	BinarySearchTree<Integer, Integer> od = new BinarySearchTree<>();

	        od.insert(20,20);
	        od.insert(10, 10);
	        od.insert(30, 30);
	        od.insert(5, 5);
	        od.insert(15, 15);
	        od.insert(25, 25);
	        od.insert(35, 35);
	        od.insert(6, 6);
	        od.insert(11, 11);
	        od.insert(16, 16);
	        od.insert(17, 17);

	        od.remove(10);

	        assertTrue(od.root.getValue() == 20);
	        assertTrue(od.root.getRight().getValue() == 30);
	        assertTrue(od.root.getRight().getLeft().getValue() == 25);
	        assertTrue(od.root.getRight().getRight().getValue() == 35);
	        assertTrue(od.root.getLeft().getValue() == 11);
	        assertTrue(od.root.getLeft().getLeft().getValue() == 5);
	        assertTrue(od.root.getLeft().getLeft().getRight().getValue() == 6);
	        assertTrue(od.root.getLeft().getRight().getValue() == 16);
	        assertTrue(od.root.getLeft().getRight().getLeft().getValue() == 15);
	        assertTrue(od.root.getLeft().getRight().getRight().getValue() == 6);



	    }
}
