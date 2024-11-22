package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
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
    public boolean isEmpty() {
        return size == 0;
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
        return null;
    }

    private class LinkedNode {
        public T item;
        public LinkedNode next;
        public LinkedNode previous;

        public LinkedNode(T i, LinkedNode n, LinkedNode p) {
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
}
