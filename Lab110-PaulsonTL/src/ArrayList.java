/**
 * Data Structures & Algorithms 6th Edition
 * Goodrick, Tamassia, Goldwasser
 * Code Fragement 7.2,7.3,7.13
 * 
 * Transcribed by Teagan Lyn Paulson
 * @version 20230319
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayList<E> implements List<E> {

    //instance variables
    public static final int CAPACITY = 16;    //defualt array capacity
    private E[] data;                       // generic array used for storage
    private int size = 0;                     // current number of elements
    // constructor                          

    public ArrayList() {
        this(CAPACITY);
    }   // constructs list with default capacity

    public ArrayList(int capacity) {       // constructs list with given capacity
        data = (E[]) new Object[capacity];  // safe cast; compiler may give warning
    }

    // public mehtods
    /**
     * Returns the number of elements in the array list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns wether the array list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the element at index i.
     */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    /**
     * Replaces the element at index i with e, and returns the replaced element.
     */
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /**
     * Inserts element e to be at index i, shifting all subsequent elements
     * later.
     */
    public void add(int i, E e) throws IndexOutOfBoundsException, IllegalStateException {
        checkIndex(i, size + 1);
        if (size == data.length) // not enough capacity
        {
            resize(2 * data.length);              // so double the current capacity
        }
        for (int k = size - 1; k >= i; k--) // start by shifting rightmost
        {
            data[k + 1] = data[k];
        }
        data[i] = e;                            // ready to place the new element
        size++;
    }

    /**
     * Removes/returns the element at index i, shifting subsequent elements
     * earlier.
     */
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++) // shift element to fill hole
        {
            data[k] = data[k + 1];
        }
        data[size - 1] = null;                // help garbage collection
        size--;
        return temp;
    }

    //utility method
    /**
     * Checks wether the given index is in the range [0, n-1].
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal Index: " + i);
        }
    }

    /**
     * Resizes internal array to have given capacity >= size.
     */
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity]; // safe case; compiler may give warning
        for (int k = 0; k < size; k++) {
            temp[k] = data[k];
        }
        data = temp;                            // start using the new array
    }

    //---------------- nested ArrayIterator class -------------
    /**
     * A (nonstatic) inner class. Note well that each instance contains an
     * implicit reference to the containing list, allowing it to access the
     * list's members.
     */
    private class ArrayIterator implements Iterator<E>
    {
        private int j = 0;                   // index of the next element to report
        private boolean removable = false;   // can remove be called at this time?

        /**
        * Tests wether the iterator has a next object
        *
        * @Return true if there are further objects, false otherwise
        */
        public boolean hasNext() {
            return j < size;
        }//size is field of outer instance

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) {
                throw new NoSuchElementException("No next element");
            }
            removable = true;       // this element can subsequently be removed
            return data[j++];       // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since recent
         * next
         */
        public void remove() throws IllegalStateException {
            if (!removable) {
                throw new IllegalStateException("nothing to remove");
            }
            ArrayList.this.remove(j - 1);     // that was the last one returned
            j--;
            removable = false;
        }
    }//------------- end of nested ArrayIterator class -----------   
    /** Returns an iterator of the elements stored in the list. */ 
    public Iterator<E> iterator(){
        return new ArrayIterator();     //create a new instance fo the inner class
    }
    /**
     * add Element to the end of the array and resizes if full
     */
    public void add(E e) throws IndexOutOfBoundsException, IllegalStateException {
        if (size == data.length) // not enough capacity
        {
            resize(2 * data.length);              // so double the current capacity
        }
        data[size] = e;                            // ready to place the new element
        size++;
    }
}
