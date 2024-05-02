import java.util.*;

public class DataLayer {


    static int totalLift = 0;
    static Scanner sc = new Scanner(System.in);

    static ArrayList<Lift> liftList;

    static void createLift(){
        System.out.println("enter the number of lifts : ");
        totalLift = sc.nextInt();
        liftList = new ArrayList<>();
        for(int i=1;i<=totalLift;i++){
            liftList.add(new Lift());
        }
    }

    static void displayLift(){
        for(Lift lift : liftList){
            System.out.println(lift.name+" at position "+lift.position);
        }
    }

    static List<Lift> getInstance(){
        if(liftList == null){
            createLift();
        }
        return liftList;
    }

    public static void assignFirstPosition(int droppingPoint) {
        for(Lift lift : getWorkingLift()){
            if(lift == null){
                System.out.println("No lift present");
            }else{
                System.out.println(lift.name + " has been assigned");
                lift.position = droppingPoint;
                break;
            }
        }
    }

    public static List<Lift> getNearestLifts(int startingPoint) {
        int smallDifference = Integer.MAX_VALUE;
        List<Lift> resultLift = new ArrayList<>();
        for(Lift lift : getWorkingLift()){
            int currentDistance = Math.abs(startingPoint-lift.position);
            if(currentDistance < smallDifference){
                resultLift.clear();
                smallDifference = currentDistance;
                resultLift.add(lift);
            }else if(currentDistance == smallDifference){
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public static List<Lift> getNearestLiftsWithRestriction(int startingPoint,int droppingPoint) {
        int smallDifference = Integer.MAX_VALUE;
        List<Lift> resultLift = new ArrayList<>();
        List<Lift>liftWithPath = getLiftWithPaths(startingPoint,droppingPoint);
        for (Lift lift : liftWithPath) {
            int currentDistance = Math.abs(startingPoint - lift.position);
            if (currentDistance < smallDifference) {
                resultLift.clear();
                smallDifference = currentDistance;
                resultLift.add(lift);
            } else if (currentDistance == smallDifference) {
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public static List<Lift> getWorkingLift(){
        List<Lift> resultList = new ArrayList<>();
        for (Lift lift : liftList){
            if(lift.position != -1){
                resultList.add(lift);
            }
        }
        return resultList;
    }

    public static List<Lift> getLiftWithPaths(int startingPoint, int droppingPoint) {
        List<Lift> resultLift = new ArrayList<>();
        for (Lift lift : getWorkingLift()) {
            if (lift.path.contains(startingPoint) && lift.path.contains(droppingPoint)){
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public static List<Lift> getLiftWithCapacity(int startingPoint,int droppingPoint,int capacity) {
        List<Lift> resultList = new ArrayList<>();
        for(Lift lift : getLiftWithPaths(startingPoint,droppingPoint)){
            if(lift.capacity >= capacity){
                resultList.add(lift);
            }
        }
        return resultList;
    }
}
