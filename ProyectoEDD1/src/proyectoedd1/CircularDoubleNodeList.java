/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectoedd1;

/**
 *
 * @author josem
 * @param <E>
 */
public class CircularDoubleNodeList<E> {
    
    private E element;
    private CircularDoubleNodeList<E> next;
    private CircularDoubleNodeList<E> prev;

    public CircularDoubleNodeList(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public CircularDoubleNodeList<E> getNext() {
        return next;
    }

    public void setNext(CircularDoubleNodeList<E> next) {
        this.next = next;
    }

    public CircularDoubleNodeList<E> getPrev() {
        return prev;
    }

    public void setPrev(CircularDoubleNodeList<E> prev) {
        this.prev = prev;
    }
    
    
    
}
