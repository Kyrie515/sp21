package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private final int DEFAULT_LENGTH = 10;
    private int nextFirst = 4;
    private int nextLast = 5;
    private int size = 0;
    Object[] array;
    public ArrayDeque() {
        array = new Object[DEFAULT_LENGTH];
    }

    @Override
    public void addFirst(T t) {
        if (size + 1 == array.length) {
            resize();
        }
        array[nextFirst] = t;
        //update nextFirst
        if (nextFirst == 0) {
            nextFirst = array.length - 1;
        } else {
            nextFirst--;
        }
        size++;
    }

    @Override
    public void addLast(T t) {
        if (size + 1 == array.length) {
            resize();
        }

        array[nextLast] = t;
        if (nextLast == array.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        System.out.print("[");
        if (nextFirst < nextLast) {
            for (int i = nextFirst + 1; i < nextLast - 1; i++) {
                System.out.print(array[i]);
                System.out.print(", ");
            }
            System.out.print(array[nextLast - 1]);
            System.out.println("]");
        } else {
            for (int i = nextFirst + 1; i < array.length; i++) {
                System.out.print(array[i]);
                System.out.print(", ");
            }

            for (int i = 0; i < nextLast - 1; i++) {
                System.out.println(array);
                System.out.println(", ");
            }
            System.out.print(array[nextLast - 1]);
            System.out.println("]");
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rmvEle;
        if (nextLast == 0) {
            rmvEle = (T) array[array.length - 1];
            array[array.length - 1] = null;
            nextLast = array.length - 1;
        } else {
            rmvEle = (T) array[nextLast - 1];
            array[nextLast - 1] = null;
            nextLast--;
        }

        //check if needed resize
        if (array.length > 100 && ((double) size - 1) / array.length < 0.25) {
            resize();
        }
        size--;
        return rmvEle;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rmvEle;
        if (nextFirst == array.length - 1) {
            rmvEle = (T) array[0];
            array[0] = null;
            nextFirst = 0;
        } else {
            rmvEle = (T) array[nextFirst + 1];
            array[nextFirst + 1] = null;
            nextFirst++;
        }
        //check if needed resize
        if (array.length > 100 && ((double) size - 1) / array.length < 0.25) {
            resize();
        }
        size--;
        return rmvEle;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (nextFirst < nextLast) {
            return (T) array[nextFirst + 1 + index];
        } else {
            if (nextFirst + 1 + index < array.length) {
                return (T) array[nextFirst + 1 + index];
            } else {
                return (T) array[nextFirst + 1 + index - array.length];
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        } else {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (other.size() != this.size()) {
                return false;
            } else {
                for (int i = 0; i < size; i++) {
                    if (!other.get(i).equals(this.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    private void resize() {
        if (size + 1 == array.length) { //grow: nextLast == nextFirst
            Object[] newArray = new Object[2 * array.length];
            Iterator<T> it = this.iterator();
            int i = 0;
            while (it.hasNext()) {
                newArray[i] = it.next();
                i++;
            }
            nextFirst = newArray.length - 1;
            nextLast = i;
            array = newArray;
            return;
        }
        if (((double) size - 1) / array.length < 0.25) {
            Object[] newArray = new Object[array.length / 2];
            Iterator<T> it = this.iterator();
            int i = 0;
            while (it.hasNext()) {
                newArray[i] = it.next();
                i++;
            }
            nextFirst = newArray.length - 1;
            nextLast = i;
            array = newArray;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int wiser;
        int p;
        ArrayDequeIterator() {
            this.wiser = 0;
            this.p = nextFirst + 1;
        }

        @Override
        public boolean hasNext() {
            return wiser < size;
        }

        @Override
        public T next() {
            if (p == array.length) {
                p = 0;
            }
            p++;
            wiser++;
            return (T) array[p - 1];
        }
    }
}
