package TDA;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 *
 * @author Michelle
 * @param <E>
 */
public class DoubleCircleLinkedList<E> implements Iterable<E>, Serializable, List<E> {

    private Node<E> last;
    private int effective;
    
    private static final long serialVersionUID = 58743299253201266L;

    public DoubleCircleLinkedList() {
        last = null;
        effective = 0;
    }

    public void setLast(int index) {
        if (index >= size() || index < 0 || isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> nodo = last.getNext();
        int cont = 0;
        do {
            if (index == cont) {
                last = nodo;
                break;
            }
            nodo = nodo.getNext();
            cont++;
        } while (nodo != last.getNext());
    }

    @Override
    public void clear() {
        last = null;
        effective = 0;
    }

    public void addAll(PriorityQueue<E> cola) {
        while (!cola.isEmpty()) {
            this.addLast(cola.poll());
        }
    }

    public void addAll(DoubleCircleLinkedList<E> lista) {
        for (E value : lista) {
            this.addLast(value);
        }
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            return false;
        } else if (size() == 0) {
            last = nodo;
            last.setNext(last);
            last.setPrevious(last);
        } else {
            nodo.setNext(last.getNext());
            last.getNext().setPrevious(nodo);
            last.setNext(nodo);
            nodo.setPrevious(last);
        }
        effective++;
        return true;
    }

    @Override
    public void addLast(E element) {
        Node<E> nodo = new Node<>(element);
        if (element == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo.");
        } else if (size() == 0) {
            last = nodo;
            last.setNext(last);
            last.setPrevious(last);
        } else {
            nodo.setNext(last.getNext());
            last.getNext().setPrevious(nodo);
            last.setNext(nodo);
            nodo.setPrevious(last);
            last = nodo;
        }
        effective++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<E> first = last.getNext();
        last.setNext(first.getNext());
        first.getNext().setPrevious(last);
        effective--;
        return first.getContent();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<E> nodo = last.getPrevious();
        nodo.setNext(last.getNext());
        last.getNext().setPrevious(nodo);
        E content = last.getContent();
        last = nodo;
        effective--;
        return content;
    }

    public E getFirst() {
        return last.getNext().getContent();
    }

    public E getLast() {
        return last.getContent();
    }

    public boolean insert(int index, E element) {
        if (index > size() || index < 0) {
            return false;
        } else if (index == 0) {
            addFirst(element);
        } else if (index == size()) {
            addLast(element);
        } else {
            Node<E> nodo = new Node<>(element);
            int buf = 0;
            for (Node<E> n = last.getNext(); n != last; n = n.getNext()) {
                if (buf == index - 1) {
                    nodo.setNext(n.getNext());
                    n.getNext().setPrevious(nodo);
                    n.setNext(nodo);
                    nodo.setPrevious(n);
                    effective++;
                    return true;
                }
                buf++;
            }
        }
        return true;
    }

    public boolean contains(E element) {
        if (element == null || isEmpty()) {
            return false;
        }
        Node<E> nodo = last.getNext();
        do {
            if (element.equals(nodo.getContent())) {
                return true;
            }
            nodo = nodo.getNext();
        } while (nodo != last.getNext());
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= size() || index < 0 || isEmpty()) {
            return null;
        }
        Node<E> nodo = last.getNext();
        int cont = 0;
        do {
            if (index == cont) {
                return nodo.getContent();
            }
            nodo = nodo.getNext();
            cont++;
        } while (nodo != last.getNext());
        return null;
    }

    public int indexOf(E element) {
        if (isEmpty() || element == null) {
            return -1;
        }
        Node<E> nodo = last.getNext();
        int cont = 0;
        do {
            if (element.equals(nodo.getContent())) {
                return cont;
            }
            nodo = nodo.getNext();
            cont++;
        } while (nodo != last.getNext());
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return effective == 0;
    }

    @Override
    public E remove(int index) {
        if (index >= size() || index < 0 || isEmpty()) {
            return null;
        } else if (index == 0) {
            return removeFirst();
        } else if (index == size() - 1) {
            return removeLast();
        } else {
            int cont = 0;
            Node<E> n = last.getNext();
            do {
                if (cont == index - 1) {
                    Node<E> toRemove = n.getNext();
                    n.setNext(toRemove.getNext());
                    toRemove.getNext().setPrevious(n);
                    effective--;
                    return toRemove.getContent();
                }
                n = n.getNext();
                cont++;
            } while (n != last.getNext());
        }
        return null;
    }

    public boolean remove(E element, Comparator<E> cmp) {
        Node<E> nodo = last.getNext();
        do {
            if (cmp.compare(element, nodo.getContent()) == 0) {
                nodo.getPrevious().setNext(nodo.getNext());
                nodo.getNext().setPrevious(nodo.getPrevious());
                effective--;
                return true;
            }
            nodo = nodo.getNext();
        } while (nodo != last.getNext());
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index >= size() || index < 0 || element == null) {
            return null;
        }
        Node<E> nodo = last.getNext();
        int cont = 0;
        do {
            if (index == cont) {
                E oldContent = nodo.getContent();
                nodo.setContent(element);
                return oldContent;
            }
            cont++;
            nodo = nodo.getNext();
        } while (nodo != last.getNext());
        return null;
    }

    @Override
    public int size() {
        return effective;
    }

    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            Node<E> nodo = last != null ? last.getNext() : null;
            Node<E> lastReturned = null;

            @Override
            public boolean hasNext() {
                return size() > 0 && nodo != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = nodo.getContent();
                lastReturned = nodo;
                nodo = nodo.getNext();
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return size() > 0 && nodo != null;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                if (lastReturned == null) {
                    lastReturned = last;
                } else {
                    lastReturned = lastReturned.getPrevious();
                }
                nodo = lastReturned;
                return nodo.getContent();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> nodoN = isEmpty() ? null : last.getPrevious();
            Node<E> currentNode = null;

            @Override
            public boolean hasNext() {
                if (nodoN == null) {
                    return false;
                }
                return currentNode == null || currentNode.getNext() != last.getNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentNode == null) {
                    currentNode = last.getNext();
                } else {
                    currentNode = currentNode.getNext();
                }
                return currentNode.getContent();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        switch (size()) {
            case 0 -> {
                s.append("]");
                return s.toString();
            }
            case 1 -> {
                s.append(last.getNext().getContent().toString()).append("]");
                return s.toString();
            }
            default -> {
                Node<E> nodo = last.getNext();
                do {
                    if (nodo == last) {
                        s.append(nodo.getContent().toString()).append("]");
                    } else {
                        s.append(nodo.getContent().toString()).append(",");
                    }
                    nodo = nodo.getNext();
                } while (nodo != last.getNext());
                return s.toString();
            }
        }
    }

    @Override
    public void add(int index, E element) {
        insert(index, element);
    }

    @Override
    public List<E> findIntersection(List<E> otherList) {
        DoubleCircleLinkedList<E> intersection = new DoubleCircleLinkedList<>();
        if (otherList == null || otherList.isEmpty() || this.isEmpty()) {
            return intersection;
        }
        for (E element : otherList) {
            if (this.contains(element)) {
                intersection.addLast(element);
            }
        }
        return intersection;
    }

    public int find(Comparator<E> comp, E elemento) {
        if (elemento == null || isEmpty()) {
            return -1;
        }
        Node<E> nodo = last.getNext();
        int index = 0;
        do {
            if (comp.compare(nodo.getContent(), elemento) == 0) {
                return index;
            }
            nodo = nodo.getNext();
            index++;
        } while (nodo != last.getNext());
        return -1;
    }

    public DoubleCircleLinkedList<E> findAll(Comparator<E> comp, E elemento) {
        DoubleCircleLinkedList<E> resultList = new DoubleCircleLinkedList<>();
        if (elemento == null || isEmpty()) {
            return resultList;
        }
        Node<E> nodo = last.getNext();
        do {
            if (comp.compare(nodo.getContent(), elemento) == 0) {
                resultList.addLast(nodo.getContent());
            }
            nodo = nodo.getNext();
        } while (nodo != last.getNext());
        return resultList;
    }

}
