/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.io.Serializable;

/**
 *
 * @author Michelle
 * @param <E>
 */
public class Node<E> implements Serializable {
    private E content;
    private Node<E> previous;
    private Node<E> next;
    
    private static final long serialVersionUID = 58743201266L;

    public Node(E content) {
        this.content = content;
        this.next = null;
        this.previous = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Node<E> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" + "content=" + content + '}';
    }
}

