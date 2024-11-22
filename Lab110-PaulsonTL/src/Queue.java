/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 6.9
 * 
 * Transcribed by Teagan Lyn Paulson
 * @version 20230319
 */
interface Queue<E> {
    /** Returns the number of elements in the queue. */
    int size();
    /** Tests wether the queue is empty. */
    boolean isEmpty();
    /** Inserts an element at the rear of the queue. */
    void enqueue(E e);
    /** Returns, but does not remove, the first element of the queue (null if empty). */
    E first();
    /** Removes and returns the first element of the queue (null if empty). */
    E dequeue();
}
