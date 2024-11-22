/**
 * 
 * @author Teagan Paulson
 * @version ascending ID
 * Comparator for standing
 */
public class StudentIDAscendingComparator implements Comparator<Student>{

    @Override
    public int compare(Student a, Student b) {
        if(a.getID()== b.getID())
            return 0;
        else if(a.getID() <  b.getID())
            return 1;
        else
            return -1;
    }
    
}
