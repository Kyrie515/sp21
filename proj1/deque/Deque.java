package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T t);

    public void addLast(T t);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public T removeLast();

    public T removeFirst();

    public T get(int index);

    public Iterator<T> iterator();

    public boolean equals(Object o);

}
