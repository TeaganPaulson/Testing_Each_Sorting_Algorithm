/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Client that will race all of the sorting algorithms that will neatly display the data in a ascii table.
 */
public class Client {
    public static void main(String[]args){
        //All the instance variables for the sorting algorithms
        String[] data = new String[7]; 
        Student[] origional = fillStudentArray(1000000);
        Student[] temp = copyArray(origional.length-1,origional);
        Comparator<Student> comp = new StudentIDDescendingComparator();
        
        //Print Standing and GPA
        System.out.println("\tStandings Distribution\n"+Student.getDistrubutionStandingString());
        System.out.println("\n\tGPA Distribution\n"+Student.getGPADistrubutionString()+"\n");
        
        //Do the seven differient sorts with differient comparators
        
        //merge sort
        //Descending merge sort
        long preTime = System.currentTimeMillis();
        Sort.mergeSort(temp,comp);
        data[0]=((Long)System.currentTimeMillis()- preTime)+"";
        System.out.println("Merge Sort id descending: "+data[0]);
        
        //quick sort
        //Refresh variables and apply proper sort
        temp = copyArray(origional.length-1,origional);
        comp = new StudentGPAAscendingComparator();//recreate new comparator
        Queue<Student> quick = arrayToQueue(temp);//make new queue using array
        preTime = System.currentTimeMillis();//take time start
        Sort.quickSort(quick, comp);//do sort
        //Store time
        data[1]=((Long)System.currentTimeMillis()- preTime)+"";//subtract for elapsed time
        System.out.println("Quick Sort ascending Gpa: "+data[1]);
        
        //simple bubble sort
        //Refresh variables and apply proper sort
        temp = copyArray(100000-1,origional);
        comp = new StudentGPAAscendingComparator();//recreate new comparator
        preTime = System.currentTimeMillis();//take time start
        Sort.simpleBubbleSort(temp, comp);//do sort
        //Store time
        data[2]=((Long)System.currentTimeMillis()- preTime)+"";//subtract for elapsed time
        System.out.println("Bubble simple sort ascending id: "+data[2]);
        
        //efficent bubble sort
        //Refresh variables and apply proper sort
        temp = copyArray(100000-1,origional);
        comp = new StudentIDDescendingComparator();//recreate new comparator
        preTime = System.currentTimeMillis();//take time start
        Sort.efficentBubbleSort(temp, comp);//do sort
        //Store time
        data[3]=((Long)System.currentTimeMillis()- preTime)+"";//subtract for elapsed time
        System.out.println("Bubble effecient sort descending id: "+data[3]);
        
        //insertion sort
        //Refresh variables and apply proper sort
        temp = copyArray(100000-1,origional);
        comp = new StudentGPAAscendingComparator();//recreate new comparator
        preTime = System.currentTimeMillis();//take time start
        Sort.insertionSort(temp, comp);//do sort
        preTime = System.currentTimeMillis()-preTime;
        System.out.println(preTime);
        //Store time
        data[4]=(preTime)+"";//subtract for elapsed time
        System.out.println("insertion sort ascending gpa: "+data[4]);
        
        //selection sort
        //Refresh variables and apply proper sort
        temp = copyArray(100000-1,origional);
        comp = new StudentStandingComparator();//recreate new comparator
        preTime = System.currentTimeMillis();//take time start
        Sort.selectionSort(temp, comp);//do sort
        //Store time
        data[5]=((Long)System.currentTimeMillis()- preTime)+"";//subtract for elapsed time
        System.out.println("selection sort standing order: "+data[5]);
        
        //Radix Sort
        //Refresh variables and apply proper sort
        temp = copyArray(1000000-1,origional);
        preTime = System.currentTimeMillis();//take time start
        //do sort
        Sort.radixSort(temp, new StudentStandingComparator(),new StudentGPAAscendingComparator(),new StudentLastNameComparatorDescending(),new StudentFirstNameAscendingComparator(), new StudentIDAscendingComparator());
        //Store time
        data[6]=((Long)System.currentTimeMillis()- preTime)+"";//subtract for elapsed time
        System.out.println("Radix sort: "+data[6]);
        
        
        
        //for two dimensional array specification of table class
        String[][] column = new String[1][];
        //Set data equal to the first and last column in the table
        column[0]=data;
        //Ascii table for formating data
        TableClassResizableHeaders ascii = createStandardTable();
        ascii.addTwoDimensionalArrayReversed(column);
        ascii.setHeaders(0, 2, "Time\n(msec)");
        System.out.println(ascii.asciiTable());
    }
    /**
     * 
     * @param length
     * @return 
     */
    public static Student[] fillStudentArray(int length){
        Student[] temp = new Student[length];
        for(int i = 0; i < length; i++)
            temp[i]=new Student();
        return temp;
    }
    /**
     * 
     * @param lastIndex that is going to be copied
     * @param arr array of type student that is going to be shallow copied from
     * @return 
     */
    public static Student[] copyArray(int lastIndex, Student[] arr){
        Student[] temp = new Student[lastIndex+1];
        for(int i = 0; i <= lastIndex; i++)
            temp[i]=arr[i];
        return temp;
    }
    /**
     * 
     * @param arr array being turned into queue
     * @return queue made from the array
     */
    public static Queue<Student> arrayToQueue(Student[] arr){
        LinkedQueue<Student> temp = new LinkedQueue();
        for(int i=0; i<arr.length;i++)
            temp.enqueue(arr[i]);
        return temp;
    }
    /**
     * 
     * @return creates standard table for the values
     */
    public static TableClassResizableHeaders createStandardTable(){
        TableClassResizableHeaders temp = new TableClassResizableHeaders();
        Integer[][]x=new Integer[1][];
        x[0]=new Integer[]{1000000,1000000,100000,100000,100000,100000,1000000};
        temp.addTwoDimensionalArrayReversed(x);
        temp.setCommas(true);
        temp.addSides();
        temp.setSides(0, "Merge");
        temp.setSides(1, "Quick");
        temp.setSides(2, "SimpleBubble");
        temp.setSides(3, "EfficientBubble");
        temp.setSides(4, "Insertion");
        temp.setSides(5, "Selection");
        temp.setSides(6, "Radix");
        temp.addHeaders();
        temp.setHeaders(0, 0, "Sort");
        temp.setHeaders(0, 1, "N");
        temp.addTableHeadder("CSCI 161 Lab110 Sorting");
        return temp;
    }
    
}
