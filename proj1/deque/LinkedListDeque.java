package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private final LinkedNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new LinkedNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        LinkedNode node = new LinkedNode(item, sentinel.next, sentinel);
        sentinel.next.previous = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        LinkedNode node = new LinkedNode(item, sentinel, sentinel.previous);
        sentinel.previous.next = node;
        sentinel.previous = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        LinkedNode node = sentinel.next;
        while (node != sentinel) {
            System.out.print(node.item);
            System.out.print(" ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        T first = null;
        if (size > 0) {
            first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size--;
        }
        return first;
    }

    @Override
    public T removeLast() {
        T last = null;
        if (size > 0) {
            last = sentinel.previous.item;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size--;
        }
        return last;
    }

    @Override
    public T get(int index) {
        T item = null;
        if (index >= 0 && index < size) {
            LinkedNode node = sentinel.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            item = node.item;
        }
        return item;
    }

    public T getRecursive(int index) {
        T item = null;
        if (index >= 0 && index < size) {
            item = sentinel.get(index + 1);
        }
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
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

    private class LinkedNode {
        private final T item;
        private LinkedNode next;
        private LinkedNode previous;

        LinkedNode(T i, LinkedNode n, LinkedNode p) {
            item = i;
            next = n;
            previous = p;
        }

        public T get(int i) {
            if (i == 0) {
                return item;
            }
            return next.get(i - 1);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private LinkedNode cursor = sentinel;

        @Override
        public boolean hasNext() {
            return cursor.next != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            cursor = cursor.next;
            return cursor.item;
        }
    }
}
