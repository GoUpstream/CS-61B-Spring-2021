package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }

        T maximum = get(0);
        for (T item : this) {
            if (c.compare(maximum, item) < 0) {
                maximum = item;
            }
        }
        return maximum;
    }
}
