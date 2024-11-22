
public class testMethod {
    public static void main(String[]args){
        //All the instance variables for the sorting algorithms
        String[] data = new String[7]; 
        Student[] origional = fillStudentArray(10);
        Student[] temp = copyArray(origional.length-1,origional);
        Comparator<Student> comp = new StudentStandingComparator();
        Long start = System.currentTimeMillis();
        Sort.simpleBubbleSort(temp,comp);
        System.out.println(start-System.currentTimeMillis());
        
        for(Student tem: temp)
        {
            System.out.println(tem.getStanding()+"\n");
        }
        System.out.println("____________________________");
        temp = copyArray(origional.length-1,origional);
        Long preTime = System.currentTimeMillis();//take time start
        Sort.efficentBubbleSort(temp, comp);//do sort
        preTime = System.currentTimeMillis()-preTime;
        System.out.println(preTime+"\n\n");
        for(Student tem: temp)
        {
            System.out.println(tem.getStanding()+"\n");
        }
        
//        for(Student i: temp)
//        {
//            System.out.println(i+"\n");
//        }
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
