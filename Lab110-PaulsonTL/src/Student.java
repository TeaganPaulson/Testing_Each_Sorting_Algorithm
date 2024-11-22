
import java.util.Random;
/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Student class to store the data and methods for a student object
 */

public class Student {
    //Instance variables
    private int id;
    private String lname;
    private String fname;
    private String standing;
    private float gpa;
    private static ArrayListReplaceWithLast<Integer> availableId;
    private static int numberOfStudent=0;
    
    private static int sophomore=0;
    private static int juniors=0;
    private static int seniors=0;
    private static int freshman=0;
    
    private static int zero=0;
    private static int one=0;
    private static int two=0;
    private static int three;
    private static int four=0;
    /**
     * Default Constructor
     * @param lastName String last name representation of student
     * @param firstName String first name representation of student
     * @param standingLevel String representation of the senior to freshman
     * @param gradePointAverage a float representation that is between 4.00 and 0.00
     */
    public Student(String lastName, String firstName, String standingLevel, float gradePointAverage){
        lname = lastName;
        fname = firstName;
        setStanding(standingLevel);
        setGPA(gradePointAverage);
        id = getRandom();
        numberOfStudent++;
    }
    
    public Student(){
        id = getRandom();
        lname = this.randomLastName();
        fname = this.randomFirstName();
        setPercentedStanding();
        setPercentGPA();
        numberOfStudent++;
    }
    
    //Get methods
    /**
     * 
     * @return String last name of the student
     */
    public String getLastName(){return lname;}
    /**
     * 
     * @return String first name of the student
     */
    public String getFirstName(){return fname;}
    /**
     * 
     * @return float representation of gpa.
     */
    public float getGPA(){return gpa;}
    /**
     * 
     * @return String of the standing
     */
    public String getStanding(){return standing;}
    /**
     * 
     * @return int of the student id from 0 to 9,999,999 id there will be no repeat
     */
    public int getID(){return id;}
    //Set methods
    /**
     * 
     * @param lastName last name of the student
     * changes last name of the student
     */
    public void setLastName(String lastName){lname = lastName;}
    /**
     * @param firstName first name of the student
     * changes first name of the student
     */
    public void setFirstName(String firstName){fname = firstName;}
    /**
     * 
     * @param standingLevel standing level of the student may be sophomore junior freshman or senior
     * @throws IllegalArgumentException when not valid standing for student
     * sets the standing of the student
     */
    public void setStanding(String standingLevel)throws IllegalArgumentException{
        String stan = standingLevel.toLowerCase();
        switch(stan){
            case "freshman":
            case "sophomore":
            case "junior":
            case "senior":
                standing = standingLevel;
                break;
            default:
                throw new IllegalArgumentException("Not valid input for standing level");
        }
    }
    /**
     * 
     * @param gradePointAverage gpa of the student between 4.00 and 0.00 including outside values but not more than 2 decimals of precision
     * @throws IllagalArgumentException when GPA is not within range or precision
     */
    public void setGPA(float gradePointAverage)throws IllegalArgumentException{
        if(gradePointAverage>4.0||gradePointAverage<0.0)
            throw new IllegalArgumentException("GPA not within range");
        gpa = gradePointAverage;
    }
    /**
     * 
     * @return an integer value between 0 and 19 million excluding ten million
     */
    public static Integer getRandom(){
        if(availableId==null){
            availableId = new ArrayListReplaceWithLast<>();
            fillArrayList(availableId,10000000);
        }
        Random num = new Random();
        int index = num.nextInt(availableId.size());
        return availableId.replaceWithLast(index);
    }
    /**
     * 
     * @param list the arrayList being filled
     * @param maxVal max value to fill to not including
     */
    public static void fillArrayList(ArrayList<Integer> list,int maxVal){
        for(int i=0;i<maxVal;i++)
            list.add((Integer)i);
    }
    public String randomLastName(){
        Random rand = new Random();
        switch(rand.nextInt(10)){
            case 0:
                return "Smith";
            case 1:
                return "Johnson";
            case 2:
                return "Williams";
            case 3:
                return "Brown";
            case 4:
                return "Jones";
            case 5:
                return "Miller";
            case 6:
                return "Davis";
            case 7:
                return "Garcia";
            case 8:
                return "Rodriguez";
            case 9:
                return "Wilson";
        }
        //Should never reach but must in put in-order to make the compiler function
        return "Ryan";
    }
    /**
     * 
     * @return a capital first letter and a random assortment of the letters after
     */
    public String randomFirstName(){
        Random rand = new Random();
        int length = rand.nextInt(6);
        String first="";
        for(int i=0;i<5+length;i++)
            if(i==0){
                int end = rand.nextInt(26);
                first+=(char)(end+65);
            }
            else{
                int end = rand.nextInt(26);
                first+=(char)(end+97);
            }
        return first;
    }
    public String randomStanding(){
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                return "sophomore";
            case 1:
                return "junior";
            case 2:
                return "senior";
            case 3:
                return "freshman";
        }
        //Should never reach but must in put in-order to make the compiler function
        return "freshman";
    }
    /**
     * Sets the standing for their completion and to a 40, 30, 20, 10
     */
    public void setPercentedStanding(){
        Random rand = new Random();
        int num = rand.nextInt(10);
        if(num<=3){standing = "freshman";freshman++;}
        else if(num<=6){standing = "sophomore";sophomore++;}
        else if(num<=8){standing = "junior";juniors++;}
        else if(num==9){standing = "senior";seniors++;}
    }
    /**
     * This sets the gpa bewteen the value that is expected for the Class of a 50% split for 2.0 
     * 5% twice for 4.0 and 0.0-0.99 20% for 3.0 and 1.0
     * any division of 100 should maintain this ratio 
     */
    public void setPercentGPA(){
        Random rand = new Random();
        // Concatinate a Float using a string and a random digit between 0-9 twice for decimals
        String preCut = "0."+rand.nextInt(10)+""+rand.nextInt(10);
        //parse Float from string into float
        float temp = Float.parseFloat(preCut);
        int num = rand.nextInt(100);
        //2.00 add 2
        if(num<=49){temp+=2;two++;}
        //3.00 add 3
        else if(num<=69){temp+=3;three++;}
        //1.00 add 1
        else if(num<=89){temp+=1;one++;}
        //4.0 set to 4.00
        else if(num<=94){temp=(float)4.00;four++;}
        //0.00 add none
        else if(num<=99){temp = temp;zero++;}
        //Set gpa
        setGPA(temp);
    }
    /**
     * 
     * @return standing from freshman to seniors
     */
    public static double[] getDistrubutionStandingArray(){
        double[] stand = new double[4];
        stand[0]=(double)freshman/numberOfStudent;
        stand[1]=(double)sophomore/numberOfStudent;
        stand[2]=(double)juniors/numberOfStudent;
        stand[3]=(double)seniors/numberOfStudent;
        return stand;
    }
    /**
     * 
     * @return String of the distribution of the class for Standing
     */
    public static String getDistrubutionStandingString(){
        double[] stand = getDistrubutionStandingArray();
        String temp = "Freshman: "+stand[0];
        temp +="\nSophomores: "+stand[1];
        temp +="\nJuniors: "+stand[2];
        temp +="\nSeniors: "+stand[3];
        return temp;
    }
    /**
     * 
     * @return distrubution of gpa in double array
     */
    public static double[] getGPADistrubutionArray(){
        double[] gpaDistrubution = new double[5];
        gpaDistrubution[0]=(double)zero/numberOfStudent;
        gpaDistrubution[1]=(double)one/numberOfStudent;
        gpaDistrubution[2]=(double)two/numberOfStudent;
        gpaDistrubution[3]=(double)three/numberOfStudent;
        gpaDistrubution[4]=(double)four/numberOfStudent;
        return gpaDistrubution;
    }
    /**
     * 
     * @return String of the distribution of the class for GPA
     */
    public static String getGPADistrubutionString(){
        double[] gpaDistr = getGPADistrubutionArray();
        String temp = "0.00-0.99: "+gpaDistr[0];
        temp +="\n1.00-1.99: "+gpaDistr[1];
        temp +="\n2.00-2.99: "+gpaDistr[2];
        temp +="\n3.00-3.99: "+gpaDistr[3];
        temp +="\n4.00's: "+gpaDistr[4];
        return temp;
    }
    
    public String toString(){
        return getClass().getName()+" @ "+fname+" : "+lname+" : "+gpa+" : "+standing;
    }
}
