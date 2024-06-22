/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author josem
 */
public class CircularDoubleLinkedList<E> implements List<E>{
    
    private CircularDoubleNodeList<E> tail;
    private int size;
    
    public CircularDoubleLinkedList() {
        this.tail = null;
        this.size = 0;
    }
    

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        boolean bl = false;
        if (size == 0){
            bl = true;
        }
        return bl;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addLast(E e) {
        CircularDoubleNodeList<E> newNode = new CircularDoubleNodeList<>(e);
        if (size == 0) {
            tail = newNode;
            tail.setNext(tail);
            tail.setPrev(tail);
        } else {
            newNode.setNext(tail.getNext());
            newNode.setPrev(tail);
            tail.getNext().setPrev(newNode);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public E removeFirst() {
        if (size == 0) throw new IllegalStateException("List is empty");
        CircularDoubleNodeList<E> head = tail.getNext();
        E element = head.getElement();
        if (size == 1) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
            head.getNext().setPrev(tail);
        }
        size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) throw new IllegalStateException("List is empty");
        E element = tail.getElement();
        if (size == 1) {
            tail = null;
        } else {
            CircularDoubleNodeList<E> header = tail.getNext();
            tail = tail.getPrev();
            tail.setNext(header);
            header.setPrev(tail);
        }
        size--;
        return element;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<E> findIntersection(List<E> otherList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString() {
        if (tail == null) return "";
        String result = "";
        CircularDoubleNodeList<E> current = tail.getNext();
        do {
            result += current.getElement() + (current.getNext() != tail.getNext() ? "," : "");
            current = current.getNext();
        } while (current != tail.getNext());
        return result;
    }
    
     @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private CircularDoubleNodeList<E> current = (tail != null) ? tail.getNext() : null;
            private int remaining = size;

            @Override
            public boolean hasNext() {
                return remaining > 0;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E element = current.getElement();
                current = current.getNext();
                remaining--;
                return element;
            }
        };
    }
    
    public int find(Comparator<E> comp, E elemento) {
        if (size == 0) return -1;
        CircularDoubleNodeList<E> current = tail.getNext();
        int index = 0;
        do {
            if (comp.compare(current.getElement(), elemento) == 0) {
                return index;
            }
            current = current.getNext();
            index++;
        } while (current != tail.getNext());
        return -1;
    }
    
     public CircularDoubleLinkedList<E> findAll(Comparator<E> comp, E elemento) {
        CircularDoubleLinkedList<E> resultList = new CircularDoubleLinkedList<>();
        if (size == 0) return resultList;
        CircularDoubleNodeList<E> current = tail.getNext();
        do {
            if (comp.compare(current.getElement(), elemento) == 0) {
                resultList.addLast(current.getElement());
            }
            current = current.getNext();
        } while (current != tail.getNext());
        return resultList;
    }
}
