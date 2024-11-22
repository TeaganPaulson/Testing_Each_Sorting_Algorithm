/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Comparator for GPA ascending 
 */
public class StudentGPAAscendingComparator implements Comparator<Student>{

    @Override
    public int compare(Student a, Student b) {
        if(a.getGPA()==b.getGPA())
            return 0;
        else if(a.getGPA()<b.getGPA())
            return 1;
        else
            return -1;
    }
    
}
