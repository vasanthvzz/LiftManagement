package com.vasanthvz.liftmanagement.datalayer;

import com.vasanthvz.liftmanagement.model.Lift;

import java.util.*;

public class DataLayer {
    int totalPath = 10;
    int totalLift = 0;
    Scanner sc = new Scanner(System.in);
    ArrayList<Lift> liftList;
    static DataLayer db ;

    public static DataLayer getInstance(){
        if(db == null){
            db = new DataLayer();
        }
        return db;
    }

    DataLayer(){
        System.out.println("enter the number of lifts : ");
        totalLift = sc.nextInt();
        liftList = new ArrayList<>();
        for(int i=1;i<=totalLift;i++){
            liftList.add(new Lift(i,getPath(i)));
        }
    }

    public List<Lift> getLiftList(){
        return liftList;
    }

    private HashSet<Integer> getPath(int liftCounter) {
        HashSet<Integer> path = new HashSet<>();
        path.add(0);
        for (int i = 1; i <= totalPath; i++) {
            if ((liftCounter == 1 || liftCounter == 2) && i <= 5) {
                path.add(i);
            } else if ((liftCounter == 3 || liftCounter == 4) && i > 5) {
                path.add(i);
            } else if (liftCounter == 5) {
                path.add(i);
            }
        }
        return path;
    }

    public void displayLift(){
        for(Lift lift : liftList){
            System.out.println(lift.name+" at position "+lift.position);
        }
    }




    public void assignFirstPosition(int droppingPoint) {
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

    public List<Lift> getNearestLifts(int startingPoint) {
        int nearestDistance = Integer.MAX_VALUE;
        List<Lift> resultLift = new ArrayList<>();
        for(Lift lift : getWorkingLift()){
            int currentDistance = Math.abs(startingPoint-lift.position);
            if(currentDistance < nearestDistance){
                resultLift.clear();
                nearestDistance = currentDistance;
                resultLift.add(lift);
            }else if(currentDistance == nearestDistance){
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public List<Lift> getNearestLiftsWithRestriction(int startingPoint,int droppingPoint) {
        int nearestDistance = Integer.MAX_VALUE;
        List<Lift> resultLift = new ArrayList<>();
        List<Lift>liftWithPath = LiftInPath(startingPoint,droppingPoint);
        for (Lift lift : liftWithPath) {
            int currentDistance = Math.abs(startingPoint - lift.position);
            if (currentDistance < nearestDistance) {
                resultLift.clear();
                nearestDistance = currentDistance;
                resultLift.add(lift);
            } else if (currentDistance == nearestDistance) {
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public List<Lift> getWorkingLift(){
        List<Lift> resultList = new ArrayList<>();
        for (Lift lift : liftList){
            if(lift.position != -1){
                resultList.add(lift);
            }
        }
        return resultList;
    }

    public List<Lift> LiftInPath(int startingPoint, int droppingPoint) {
        List<Lift> resultLift = new ArrayList<>();
        for (Lift lift : getWorkingLift()) {
            if (lift.path.contains(startingPoint) && lift.path.contains(droppingPoint)){
                resultLift.add(lift);
            }
        }
        return resultLift;
    }

    public List<Lift> getLiftWithCapacity(int startingPoint,int droppingPoint,int capacity) {
        List<Lift> resultList = new ArrayList<>();
        for(Lift lift : LiftInPath(startingPoint,droppingPoint)){
            if(lift.capacity >= capacity){
                resultList.add(lift);
            }
        }
        return resultList;
    }
}
