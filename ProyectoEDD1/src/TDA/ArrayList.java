/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author richardo
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Serializable {

    private E[] elements = null;
    private int capacity = 100;
    private int effectiveSize;
    private static final long serialVersionUID = 5874329925326L;

    public ArrayList() {
        elements = (E[]) (new Object[capacity]);
        effectiveSize = 0;
    }

    private boolean isFull() {
        return effectiveSize == capacity;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elements[0] = e;
            effectiveSize++;
            return true;
        } else if (isFull()) {
            addCapacity();
        }

        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = e;
        effectiveSize++;
        return true;
    }

    @Override
    public void addLast(E e) {
        if (e == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo.");
        }
        if (isFull()) {
            addCapacity();
        }
        elements[effectiveSize] = e;
        effectiveSize++;
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < effectiveSize; i++) {
            elements[i] = null;
        }
        effectiveSize = 0;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > effectiveSize) {
            throw new IllegalArgumentException("El objeto no puede ser nulo.");
        }
        if (isFull()) {
            addCapacity();
        }
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        effectiveSize++;
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= effectiveSize) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < effectiveSize; i++) {
            s += elements[i] + ", ";
        }
        return s;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < effectiveSize;
            }

            @Override
            public E next() {
                E e = elements[cursor];
                cursor++;
                return e;
            }
        };
        return it;
    }

    @Override
    public List<E> findIntersection(List<E> otherList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void sort(Comparator<E> comp) {
        if (effectiveSize < 2) {
            return;
        }
        quickSort(elements, 0, effectiveSize - 1, comp);
    }

    private void quickSort(E[] array, int low, int high, Comparator<E> comp) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comp);
            quickSort(array, low, pivotIndex - 1, comp);
            quickSort(array, pivotIndex + 1, high, comp);
        }
    }

    private int partition(E[] array, int low, int high, Comparator<E> comp) {
        E pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comp.compare(array[j], pivot) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        E temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    @Override
    public void remove(E element) {
        if (element == null) {
            return;
        }
        int index = -1;
        for (int i = 0; i < effectiveSize; i++) {
            if (element.equals(elements[i])) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            elements[effectiveSize - 1] = null;
            effectiveSize--;
        }
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < effectiveSize; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    public void addAll(List<E> list) {
        for (E element : list) {
            this.addLast(element);
        }
    }

    public static <E> ArrayList<E> asList(E[] array) {
        ArrayList<E> list = new ArrayList<>();
        for (E element : array) {
            list.addLast(element);
        }
        return list;
    }

    @Override
    public List<E> subList(int inicio, int fin) {
        if (inicio < 0 || fin > effectiveSize || inicio > fin) {
            throw new IndexOutOfBoundsException("Invalid subList range.");
        }

        ArrayList<E> subList = new ArrayList<>();
        for (int i = inicio; i < fin; i++) {
            subList.addLast(elements[i]);
        }

        return subList;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[effectiveSize];
        System.arraycopy(elements, 0, array, 0, effectiveSize);
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < effectiveSize) {
            return (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), effectiveSize);
        }
        System.arraycopy(elements, 0, a, 0, effectiveSize);
        if (a.length > effectiveSize) {
            a[effectiveSize] = null;
        }
        return a;
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            return false;
        }
        for (int i = 0; i < effectiveSize; i++) {
            if (elements[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

}
