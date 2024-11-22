/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Comparator for first Name ascending order
 */
public class StudentFirstNameAscendingComparator implements Comparator<Student>{

    
    @Override
    public int compare(Student a, Student b) {
        String bName = b.getFirstName();
        String aName = a.getFirstName();
        int shortest;
        if(aName.length()<bName.length())
            shortest = aName.length();
        else
            shortest = bName.length();
        for(int i = 0; i<shortest;i++){
            int check = compareChars(a.getFirstName().charAt(i),b.getFirstName().charAt(i));
            if(check != 0)
                return check;
        }
        if(a.getFirstName().length() > b.getFirstName().length())
            return 1;
        if(a.getFirstName().length() < b.getFirstName().length())
            return -1;
        return 0;
    }
    
    private static int compareChars(char aObject,char bObject)
    {
        if(aObject < bObject)
            return 1;
        else if(aObject > bObject)
            return -1;
        return 0;
    }
}
