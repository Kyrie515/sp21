package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    private int size;
    public LinkedListDeque() {
        this.head = new Node<>(null, null, null);
        this.tail = new Node<>(null, null, null);
        this.head.next = this.tail;
        this.tail.pre = this.head;
        this.size = 0;
    }
    @Override
    public void addFirst(T t) {
        Node<T> addEle = new Node<>(t);
        addEle.next = this.head.next;
        this.head.next.pre = addEle;
        this.head.next = addEle;
        addEle.pre = this.head;
        this.size += 1;
    }

    @Override
    public void addLast(T t) {
        Node<T> addEle = new Node<>(t);
        this.tail.pre.next = addEle;
        addEle.pre = this.tail.pre;
        this.tail.pre = addEle;
        addEle.next = this.tail;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == this.tail;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        System.out.print("[");
        Node<T> p = this.head.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.val);
            p = p.next;
            System.out.print(", ");
        }
        if (p == this.tail) {
            System.out.println("]");
        } else {
            System.out.print(p.val + "]");
        }
    }

    @Override
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        Node<T> rmvEle = this.tail.pre;
        rmvEle.pre.next = this.tail;
        this.tail.pre = rmvEle.pre;
        this.size -= 1;
        return rmvEle.val;
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        Node<T> rmvEle = this.head.next;
        this.head.next = rmvEle.next;
        rmvEle.next.pre = this.head;
        this.size -= 1;
        return rmvEle.val;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node<T> p = this.head.next;
        int i = 0;
        while (i != index) {
            p = p.next;
            i++;
        }
        return p.val;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node<T> p;
        LinkedListDequeIterator() {
            this.p = head;
        }

        @Override
        public boolean hasNext() {
            return p.next != tail;
        }

        @Override
        public T next() {
            p = p.next;
            return p.val;
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return recursive(this.head.next, index);
    }

    public T recursive(Node<T> p, int index) {
        if (index == 0) {
            return p.val;
        }
        return recursive(p.next, index - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    private static class Node<T> {
        T val;
        Node<T> pre;
        Node<T> next;
        Node(T val) {
            this.val = val;
            this.next = null;
            this.pre = null;
        }

        Node(Node<T> next) {
            this.next = next;
            this.pre = null;
        }

        Node(T val, Node<T> next, Node<T> pre) {
            this.val = val;
            this.next = next;
            this.pre = pre;
        }
    }
}
