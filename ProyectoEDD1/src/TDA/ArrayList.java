/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.util.Iterator;
/**
 *
 * @author richardo
 * @param <E>
 */

public class ArrayList<E> implements List<E>{

    private E[] elements = null;
    private int capacity = 100;
    private int effectiveSize;
    
    public ArrayList (){
        elements = (E[])(new Object[capacity]);
        effectiveSize = 0;
    }
    
    private boolean isFull(){
        return effectiveSize == capacity;
    }
   
    @Override
    public boolean addFirst(E e) {
        if(e==null){
            return false;
        } else if (isEmpty()){
            elements[0] = e;
            effectiveSize++;
            return true;
        } else if (isFull()){
            addCapacity();
        }
        
        for (int i=effectiveSize-1; i >=0; i--){
            elements[i+1]=elements[i];
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        for (int i = 0; i < capacity; i++){
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }
    
    @Override
    public String toString() {
        String s="";
        for (int i=0; i<effectiveSize; i++) {
            s+=elements[i]+", ";
        }
        return s;
    }
    
    public Iterator<E> iterator(){
            Iterator<E> it=new Iterator<E>() {
                int cursor = 0;
                @Override
                public boolean hasNext() {
                    return cursor < effectiveSize;
                }

                @Override
                public E next() {
                    E e=elements[cursor];
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
}