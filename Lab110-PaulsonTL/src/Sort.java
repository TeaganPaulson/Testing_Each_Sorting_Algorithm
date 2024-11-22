/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Class that stores all the sorting algorithms that are being raced against each other
 */
import java.util.Arrays;


public class Sort{
    public static <K> void simpleBubbleSort( K[] data, Comparator<K> comp )
    {
        for ( int i = 0; i < data.length; i++ )
        {
            for ( int j = 0; j < data.length - 1; j++ )
            {
                if ( comp.compare( data[j], data[j+1] ) < 0 )
                {
                    K temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
    }
    public static <K> void efficentBubbleSort( K[] data, Comparator<K> comp )
    {
        boolean state = false;
        for ( int i = 0; i < data.length && !state; i++ )
        {
            state = true;
            for ( int j = 0; j < data.length - i - 1; j++ )
            {
                if ( comp.compare( data[j], data[j+1] ) <  0 )
                {
                    state = false;
                    K temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
            //hide for testing
            //printArray(data);
        }
    }
    
    /** Insertion-sort of an array of characters into non-decreasing order */
    public static <K> void insertionSort(K[] data, Comparator<K> com){
        int j;
        K temp;
        for(int i=1;i<data.length;i++){
            j=i;
            temp = data[i];
            while(j != 0 && com.compare(data[j-1], temp) < 0){
                data[j]=data[j-1];
                j--;
            }
            data[j]=temp;
        }
    }
    
    public static <K> void selectionSort(K[] data, Comparator<K> com){
        int lenght = data.length;
        
        for(int i=0; i<lenght;i++){
            int indexMin = i;
            for(int j = i+1;j<lenght;j++){
                if(com.compare(data[indexMin], data[j])>0)
                    indexMin=j;
            }
            K temp = data[indexMin];
            data[indexMin]=data[i];
            data[i]=temp;}
    }
    
    
    /**Code fragment 12.5*/
    /** Quick-sort contents of a queue. */
    public static <K> void quickSort(Queue<K> S, Comparator<K> comp){
        int n = S.size();
        if(n<2)return;                          // queue is trivially sorted
        //divide
        K pivot = S.first();                    // using first as arbitrary pivot
        Queue<K> L = new LinkedQueue<>();
        Queue<K> E = new LinkedQueue<>();
        Queue<K> G = new LinkedQueue<>();
        while(!S.isEmpty()){                    // divide origional into L, E, and G
            K element = S.dequeue();
            int c = comp.compare(element, pivot);
            if(c<0)
                L.enqueue(element);             // element is less than pivot
            else if(c==0)
                E.enqueue(element);             // element is equal to pivot
            else
                G.enqueue(element);             // element is greater than pivot
        }
        //Conquer
        quickSort(L,comp);                      // sort elements less than pivot
        quickSort(G,comp);                      // sort elements greater than pivot
        // concatenate results
        while(!L.isEmpty())
            S.enqueue(L.dequeue());
        while(!E.isEmpty())
            S.enqueue(E.dequeue());
        while(!G.isEmpty())
            S.enqueue(G.dequeue());
    }
    
    private static void printArray(Object[] test){
        System.out.println("");
        for(int i=0;i<test.length;i++){
            System.out.print(test[i].toString()+" ");
        }
    }
    
    /** Code fragment 12.1 */
    /** Merge contents of arrays S1 and S2 into properly sized array S. */
    public static <K> void merge(K[] S1,K[] S2, K[] S, Comparator<K> comp){
        int i = 0, j = 0;
        while(i+j<S.length){
            if(j==S2.length||(i<S1.length && comp.compare(S1[i], S2[j])>=0))
                S[i+j]=S1[i++];             // copy ith element of S1 and increment i
            else
                S[i+j]=S2[j++];             // copy jth element of S2 and increment j
        }
    }
    /** Code fragment 12.2 */
    /** Merge-sort contents of array S. */
    public static <K> void mergeSort(K[] S, Comparator<K> comp){
        int n = S.length;
        if(n<2)return;                                  // array is trivially sorted
        //Divide
        int mid = n/2;
        K[] S1 = Arrays.copyOfRange(S, 0, mid);     //copy of first half
        K[] S2 = Arrays.copyOfRange(S, mid, n);     //copy of second half
        // conquer (with recursion)
        mergeSort(S1, comp);                        //sort copy of first half  
        mergeSort(S2, comp);                        //sort copy of second half
        // merge results
        merge(S1, S2, S, comp);         // merge sorted halves back into original
    }
    /**
     * 
     * @param <K> Class that will be sorted
     * @param S array of the data that will be sorted
     * @param comp comparator list separated by commas first comparator is most last is least significant
     */
    public static <K> void radixSort(K[] S, Comparator<K>... comp){
        for(int i = comp.length-1 ; i >= 0 ; i--)
            mergeSort(S, comp[i]);
    }
}
