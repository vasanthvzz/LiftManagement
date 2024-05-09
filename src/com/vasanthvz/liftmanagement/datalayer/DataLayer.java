package com.vasanthvz.liftmanagement.datalayer;

import com.vasanthvz.liftmanagement.model.Lift;

import java.util.*;

public class DataLayer {
    static int totalPath = 10;
    static int totalLift = 0;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Lift> liftList;

    static void createLift(){
        System.out.println("enter the number of lifts : ");
        totalLift = sc.nextInt();
        liftList = new ArrayList<>();
        for(int i=1;i<=totalLift;i++){
            liftList.add(new Lift(i,getPath(i)));
        }
    }

    private static HashSet<Integer> getPath(int liftCounter) {
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

    public static void displayLift(){
        for(Lift lift : liftList){
            System.out.println(lift.name+" at position "+lift.position);
        }
    }

    public static List<Lift> getInstance(){
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

    public static List<Lift> getNearestLiftsWithRestriction(int startingPoint,int droppingPoint) {
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

    public static List<Lift> getWorkingLift(){
        List<Lift> resultList = new ArrayList<>();
        for (Lift lift : liftList){
            if(lift.position != -1){
                resultList.add(lift);
            }
        }
        return resultList;
    }

    public static List<Lift> LiftInPath(int startingPoint, int droppingPoint) {
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
        for(Lift lift : LiftInPath(startingPoint,droppingPoint)){
            if(lift.capacity >= capacity){
                resultList.add(lift);
            }
        }
        return resultList;
    }
}
