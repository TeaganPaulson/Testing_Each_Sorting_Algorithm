/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 7.1
 * 
 * Transcribed by Teagan Lyn Paulson
 * @version 20230319
 */
/** A simplified version of the java.util.List interface */
public interface List<E> {
    /** Returns the number of elements in this list. */
    int size();
    
    /** Returns wether the list is empty. */
    boolean isEmpty();
    
    /** Returns (but does not remove) the element at index i. */
    E get(int i) throws IndexOutOfBoundsException;
    
    /** Replaces the element at index i with e, and returns the replaced element. */
    E set(int i, E e) throws IndexOutOfBoundsException;
    
    /** Inserts element e to be at index i, shifting all subsequent elements later. */
    void add(int i, E e) throws IndexOutOfBoundsException;
    
    /** Removes/returns the element at index i, shifting subsequent elements later. */
    E remove(int i) throws IndexOutOfBoundsException;
    
    /** created new add method otherwise code wont work. This adds element to the end and resizes if full. */
    void add(E e);
}
