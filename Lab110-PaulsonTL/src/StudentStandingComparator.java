/**
 * 
 * @author Teagan Paulson
 * @version 2023426
 * Comparator for standing
 */
public class StudentStandingComparator implements Comparator<Student>{

    @Override
    public int compare(Student a, Student b) {
        switch(a.getStanding()){
            case "freshman":
                switch(b.getStanding())
                {
                    case "freshman":
                        return 0;
                    case "sophomore":
                    case "junior":
                    case "senior":
                        return -1;
                }
            case "sophomore":
                switch(b.getStanding())
                {
                    case "freshman":
                        return 1;
                    case "sophomore":
                        return 0;
                    case "junior":
                    case "senior":
                        return -1;
                }
            case "junior":
                switch(b.getStanding())
                {
                    case "freshman":
                    case "sophomore":
                        return 1;
                    case "junior":
                        return 0;
                    case "senior":
                        return -1;
                }
            case "senior":
                switch(a.getStanding())
                {
                    case "freshman":
                    case "sophomore":
                    case "junior":
                        return 1;
                    case "senior":
                        return 0;
                }
        }
        return -25;
    }
    
}
