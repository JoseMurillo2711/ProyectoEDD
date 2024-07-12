package TDA;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<E> implements List<E> {
    private Node<E> tail;
    private int size;

    public CircularLinkedList() {
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        tail = null;
        size = 0;
    }

    @Override
    public boolean addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) {
            tail = newNode;
            tail.setNext(tail);
        } else {
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        size++;
        return true;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> head = tail.getNext();
        if (tail == head) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getContent();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        Node<E> current = tail.getNext();
        if (tail == current) {
            tail = null;
        } else {
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(tail.getNext());
            tail = current;
        }
        size--;
        return tail.getContent();
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            addLast(element);
            return;
        }
        Node<E> current = tail.getNext();
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        Node<E> newNode = new Node<>(element);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> current = tail.getNext();
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        Node<E> temp = current.getNext();
        current.setNext(current.getNext().getNext());
        size--;
        return temp.getContent();
    }

    @Override
    public void remove(E element) {
        if (isEmpty()) {
            return;
        }
        Node<E> current = tail.getNext();
        Node<E> previous = tail;
        do {
            if (current.getContent().equals(element)) {
                if (current == tail) {
                    if (tail.getNext() == tail) {
                        tail = null;
                    } else {
                        tail = previous;
                        tail.setNext(current.getNext());
                    }
                } else {
                    previous.setNext(current.getNext());
                }
                size--;
                return;
            }
            previous = current;
            current = current.getNext();
        } while (current != tail.getNext());
    }

    public int indexOf(E element, Comparator<E> comparator) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> current = tail.getNext();
        int index = 0;
        do {
            if (comparator.compare(current.getContent(), element) == 0) {
                return index;
            }
            current = current.getNext();
            index++;
        } while (current != tail.getNext());
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node<E> current = tail.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getContent();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node<E> current = tail.getNext();
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        E oldElement = current.getContent();
        current.setContent(element);
        return oldElement;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = tail.getNext();
        do {
            sb.append(current.getContent().toString()).append(", ");
            current = current.getNext();
        } while (current != tail.getNext());
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public List<E> findIntersection(List<E> otherList) {
        CircularLinkedList<E> intersection = new CircularLinkedList<>();
        for (E element : this) {
            if (otherList.contains(element)) {
                intersection.addLast(element);
            }
        }
        return intersection;
    }

    @Override
    public void sort(Comparator<E> comp) {
        if (size < 2) {
            return;
        }
        boolean swapped;
        do {
            Node<E> current = tail.getNext();
            swapped = false;
            do {
                Node<E> next = current.getNext();
                if (comp.compare(current.getContent(), next.getContent()) > 0) {
                    E temp = current.getContent();
                    current.setContent(next.getContent());
                    next.setContent(temp);
                    swapped = true;
                }
                current = current.getNext();
            } while (current != tail);
        } while (swapped);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E element : this) {
            array[i++] = element;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
            a = newArray;
        }
        int i = 0;
        Object[] result = a;
        for (E element : this) {
            result[i++] = element;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public List<E> subList(int inicio, int fin) {
        if (inicio < 0 || fin > size || inicio > fin) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        CircularLinkedList<E> sublist = new CircularLinkedList<>();
        Node<E> current = tail.getNext();
        for (int i = 0; i < inicio; i++) {
            current = current.getNext();
        }
        for (int i = inicio; i < fin; i++) {
            sublist.addLast(current.getContent());
            current = current.getNext();
        }
        return sublist;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = tail != null ? tail.getNext() : null;
            private boolean first = true;

            @Override
            public boolean hasNext() {
                return current != null && (first || current != tail.getNext());
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.getContent();
                current = current.getNext();
                first = false;
                return element;
            }
        };
    }

    @Override
    public int indexOf(E element) {
        if (isEmpty()) {
            return -1;
        }
        Node<E> current = tail;
        int index = 0;
        do {
            if (current.getContent().equals(element)) {
                return index;
            }
            current = current.getNext();
            index++;
        } while (current != tail);
        return -1;
    }
}
