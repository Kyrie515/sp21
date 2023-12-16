package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T t);

    void addLast(T t);

    boolean isEmpty();

    int size();

    void printDeque();

    T removeLast();

    T removeFirst();

    T get(int index);

    Iterator<T> iterator();

    boolean equals(Object o);

}
