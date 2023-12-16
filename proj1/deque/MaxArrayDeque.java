package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
        int n = size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (c.compare((T) array[j], (T) array[j + 1]) > 0) {
                    Object t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }

    public T max() {
        int n = size();
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return (T) array[0];
        }
        return (T) array[n - 1];
    }

    public T max(Comparator<T> c) {
        int n = size();
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            return (T) array[0];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (c.compare((T) array[j], (T) array[j + 1]) == 1) {
                    Object t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
        return (T) array[n - 1];
    }
}
