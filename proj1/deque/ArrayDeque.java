package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
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
        if (size == items.length) {
            resize(items.length * 2);
        }
    }

    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        nextLast++;
        nextLast %= items.length;

        size++;
        if (size == items.length) {
            resize(items.length * 2);
        }
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
            if (size >= 16 && size < items.length / 4) {
                resize(items.length / 2);
            }
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
            if (size >= 16 && size < items.length / 4) {
                resize(items.length / 2);
            }
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
        return null;
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
}
