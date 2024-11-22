/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Comparator for descending last name
 */
public class StudentLastNameComparatorDescending implements Comparator<Student>{   
    @Override
    public int compare(Student a, Student b) {
        String bName = b.getLastName();
        String aName = a.getLastName();
        int shortest;
        if(aName.length()<bName.length())
            shortest = aName.length();
        else
            shortest = bName.length();
        for(int i = 0; i<shortest;i++){
            int check = compareChars(a.getLastName().charAt(i),b.getLastName().charAt(i));
            if(check != 0)
                return check;
        }
        if(a.getLastName().length() < b.getLastName().length())
            return 1;
        if(a.getLastName().length() > b.getLastName().length())
            return -1;
        return 0;
    }
    
    
    
    
    private static int compareChars(char aObject,char bObject)
    {
        if(aObject > bObject)
            return 1;
        else if(aObject < bObject)
            return -1;
        return 0;
    }
    
}
