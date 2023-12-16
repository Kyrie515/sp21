package deque;

import jh61b.junit.In;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    public void addRemoveTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    public void addRemoveTest2() {
        ArrayDeque<Character> ad = new ArrayDeque<>();
        ad.addLast('a');
        ad.addLast('b');
        ad.addFirst('c');
        ad.addLast('d');
        ad.addLast('e');
        ad.addFirst('f');
        ad.addLast('g');
        ad.addLast('h');
        ad.printDeque();
        Iterator<Character> it = ad.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void resizeTest1() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 11; i++) {
            if(i % 2 == 0) {
                ad.addLast(i);
            } else {
                ad.addFirst(i);
            }
        }
        assertEquals(ad.array.length, 20);
        Iterator<Integer> it = ad.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void resizeTest2() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            ad.addLast(i);
        }
        assertEquals(ad.array.length, 1280);
        for (int i = 0; i < 700; i++) {
            ad.removeLast();
        }
        assertEquals(ad.array.length, 640);
        Iterator<Integer> it = ad.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void randomTest() {
        ArrayDeque<Integer> ArrayDeque = new ArrayDeque<>();
        ArrayDeque.addFirst(0);
        ArrayDeque.addFirst(1);
        ArrayDeque.get(1)     ;
        ArrayDeque.removeLast() ;
        ArrayDeque.addLast(4);
        ArrayDeque.addLast(5);
        ArrayDeque.removeFirst() ;
        ArrayDeque.addFirst(7);
        ArrayDeque.addLast(8);
        ArrayDeque.get(0)     ;
        ArrayDeque.removeLast()  ;
        ArrayDeque.addLast(11);
        ArrayDeque.removeLast() ;
        int i = ArrayDeque.removeLast();
        assertEquals(i, 5);

    }
}
