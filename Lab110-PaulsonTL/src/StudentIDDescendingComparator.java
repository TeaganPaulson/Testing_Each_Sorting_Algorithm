/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Comparator for descending id
 */
public class StudentIDDescendingComparator implements Comparator<Student>{

    @Override
    public int compare(Student a, Student b) {
        if(a.getID()== b.getID())
            return 0;
        else if(a.getID() > b.getID())
            return 1;
        else
            return -1;
    }
    
}
