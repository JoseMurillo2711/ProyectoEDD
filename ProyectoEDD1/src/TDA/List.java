/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TDA;

import java.util.Comparator;


/**
 *
 * @author josem
 * @param <E>
 */
public interface List<E> extends Iterable<E> {
    
    public int size();
    public  boolean isEmpty();
    public void clear();
    public boolean addFirst(E e); 
    public void addLast(E e);
    public E removeFirst();
    public E removeLast(); 
    public void add (int index, E element);
    public E remove (int index); 
    public void remove (E element); 
    public int indexOf(E element);
    public E get (int index);
    public E set (int index, E element);
    @Override
    public String toString();
    public List<E> findIntersection(List<E> otherList);
    public void sort(Comparator<E> comp);
    Object[] toArray();
    public <T> T[] toArray(T[] a);
    
    public List<E> subList(int inicio, int fin);
}