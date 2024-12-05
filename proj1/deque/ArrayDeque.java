package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        nextFirst--;
        nextFirst = (nextFirst + items.length) % items.length;

        size++;
        checkResize();
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
        nextLast %= items.length;

        size++;
        checkResize();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i = nextFirst;
        for (int left = size; left > 0; left--) {
            i = (i + 1) % items.length;
            System.out.print(items[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        T first = null;
        if (size > 0) {
            nextFirst++;
            nextFirst %= items.length;
            first = items[nextFirst];

            size--;
            checkResize();
        }
        return first;
    }

    @Override
    public T removeLast() {
        T last = null;
        if (size > 0) {
            nextLast--;
            nextLast = (nextLast + items.length) % items.length;
            last = items[nextLast];

            size--;
            checkResize();
        }
        return last;
    }

    @Override
    public T get(int index) {
        T item = null;
        if (index >= 0 && index < size) {
            int i = nextFirst + index + 1;
            i %= items.length;
            item = items[i];
        }
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<?> other = (Deque<?>) o;
        if (size != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            T t = get(i);
            if (t == null) {
                if (other.get(i) != null) {
                    return false;
                }
            } else if (!t.equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void checkResize() {
        if (size == items.length) {
            resize(items.length * 2);
        } else if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    private void resize(int s) {
        T[] newItems = (T[]) new Object[s];
        int i = nextFirst;
        int j = 0;
        for (int left = size; left > 0; left--) {
            i = (i + 1) % items.length;
            newItems[j] = items[i];
            j++;
        }
        items = newItems;
        nextFirst = newItems.length - 1;
        nextLast = size;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int cursor;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            int i = (nextFirst + 1 + cursor) % items.length;
            cursor++;
            return items[i];
        }
    }
}
