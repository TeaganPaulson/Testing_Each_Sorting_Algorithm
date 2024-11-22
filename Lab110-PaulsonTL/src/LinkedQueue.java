/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 6.11
 * 
 * Transcribed by Teagan Lyn Paulson
 * @version 20230319
 */
/** Realization of a FIFO queue as an adaptation of a SinglyLinkedList. */
public class LinkedQueue<E> implements Queue<E> {
    public SinglyLinkedList<E> list = new SinglyLinkedList<>();     // an empty list
    public LinkedQueue( ){ }            // new queue relies on the intially empty list
    public int size( ){ return list.size(); }
    public boolean isEmpty( ){ return list.isEmpty( );}
    public void enqueue(E element){ list.addLast(element);}
    public E first( ){ return list.first( );}
    public E dequeue( ){ return list.removeFirst( );}
}
