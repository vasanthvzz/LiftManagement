package com.vasanthvz.liftmanagement.mainpage;

import com.vasanthvz.liftmanagement.datalayer.DataLayer;
import com.vasanthvz.liftmanagement.model.Lift;

import java.util.List;
import java.util.Scanner;

public class MainPageModel {
    private static Scanner sc = new Scanner(System.in);
    private MainPageView mainPageView;

    MainPageModel(MainPageView mainPageView) { //default access modifier
        this.mainPageView = mainPageView;
    }

    public void getInput() {
        DataLayer.getInstance();
        mainPageView.showMenu();
    }

    public void redirectChoice(String choice) {
        switch (choice) {
            case "1": {
                DataLayer.displayLift();
                break;
            }
            case "2": {
                assignPosition();
                break;
            }
            case "3": {
                assignNearestLift();
                break;
            }
            case "4": {
                assignDirectionalLift();
                break;
            }
            case "5": {
                assignDirectionalRestrictedLift();
                break;
            }
            case "6": {
                liftWithLeastStops();
                break;
            }
            case "7": {
                assignWithCapacity();
                break;
            }
            case "8": {
                showPath();
                break;
            }
            case "9": {
                assignCapacity();
                break;
            }
            case "0": {
                assignManual();
                break;
            }
            default:
                break;
        }
        mainPageView.showMenu();
    }

    private void assignCapacity() {
        System.out.println("Enter the lift name :");
        String name = sc.next();
        System.out.println("Enter the lift capacity :");
        int capacity = sc.nextInt();
        for (Lift lift : DataLayer.getInstance()) {
            if (lift.name.equals(name)) {
                lift.capacity = capacity;
                return;
            }
        }
    }

    private void assignWithCapacity() {
        System.out.println("Enter starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        System.out.println("Enter the number of passengers : ");
        int capacity = sc.nextInt();
        List<Lift> liftWithCapacity = DataLayer.getLiftWithCapacity(startingPoint, droppingPoint, capacity);
        if (liftWithCapacity.isEmpty()) {
            System.out.println("No lift found with this capacity");
        } else {
            liftWithLeastStops(liftWithCapacity, startingPoint, droppingPoint);
        }
    }

    private void liftWithLeastStops(List<Lift> givenLifts, int startingPoint, int droppingPoint) {

        int left = Math.min(startingPoint, droppingPoint);
        int right = Math.max(startingPoint, droppingPoint);
        int minStop = Integer.MAX_VALUE;
        Lift resultLift = givenLifts.getFirst();
        for (Lift lift : givenLifts) {
            int stop = 0;
            for (int i = left; i <= right; i++) {
                if (lift.path.contains(i)) {
                    stop++;
                }
            }
            if (stop < minStop) {
                resultLift = lift;
                minStop = stop;
            }
        }
        System.out.println(resultLift.name + " has been assigned");
        resultLift.position = droppingPoint;
    }


    private void liftWithLeastStops() {
        System.out.println("Enter the starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        List<Lift> liftWithPath = DataLayer.LiftInPath(startingPoint,
                droppingPoint);
        int left = Math.min(startingPoint, droppingPoint);
        int right = Math.max(startingPoint, droppingPoint);
        int minStop = Integer.MAX_VALUE;
        Lift resultLift = liftWithPath.getFirst();
        for (Lift lift : liftWithPath) {
            int stop = 0;
            for (int i = left; i <= right; i++) {
                if (lift.path.contains(i)) {
                    stop++;
                }
            }
            if (stop < minStop) {
                resultLift = lift;
                minStop = stop;
            }
        }
        System.out.println(resultLift.name + " has been assigned");
        resultLift.position = droppingPoint;
    }

    private void assignDirectionalLift() {
        System.out.println("Enter the starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        Lift lift = null;
        boolean toRight = startingPoint < droppingPoint;
        List<Lift> nearestLift = DataLayer.getNearestLifts(startingPoint);
        if (nearestLift.size() == 1) {
            lift = nearestLift.getFirst();
        }
        if (toRight) {
            for (Lift lift1 : nearestLift) {
                if (lift1.position < startingPoint) {
                    lift = lift1;
                }
            }
        } else {
            for (Lift lift1 : nearestLift) {
                if (lift1.position > startingPoint) {
                    lift = lift1;
                }
            }
        }
        lift.position = droppingPoint;
        System.out.println(lift.name + " has been assigned");
    }

    private void assignDirectionalRestrictedLift() {
        System.out.println("Enter the starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        Lift lift = null;
        boolean toRight = startingPoint < droppingPoint;
        List<Lift> nearestLift = DataLayer.getNearestLiftsWithRestriction(startingPoint, droppingPoint);
        if (nearestLift.size() == 1) {
            lift = nearestLift.getFirst();
        }
        if (toRight) {
            for (Lift lift1 : nearestLift) {
                if (lift1.position < startingPoint) {
                    lift = lift1;
                }
            }
        } else {
            for (Lift lift1 : nearestLift) {
                if (lift1.position > startingPoint) {
                    lift = lift1;
                }
            }
        }
        lift.position = droppingPoint;
        System.out.println(lift.name + " has been assigned");
    }

    private void assignManual() {
        System.out.println("enter lift name :");
        String liftName = sc.next();
        System.out.println("Enter lift position :");
        int position = sc.nextInt();
        for (Lift lift : DataLayer.getInstance()) {
            if (lift.name.equals(liftName)) {
                lift.position = position;
                return;
            }
        }

    }

    private void assignNearestLift() {
        System.out.println("Enter the starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        List<Lift> nearestLift = DataLayer.getNearestLifts(startingPoint);
        System.out.println(nearestLift.getFirst().name + " has been assigned");
        nearestLift.getFirst().position = droppingPoint;
    }

    private void assignPosition() {
        System.out.println("Enter the starting point : ");
        int startingPoint = sc.nextInt();
        System.out.println("Enter the dropping point : ");
        int droppingPoint = sc.nextInt();
        DataLayer.assignFirstPosition(droppingPoint);
    }

    private void showPath() {
        for (Lift lift1 : DataLayer.getInstance()) {
            System.out.println(lift1.name + " : " + lift1.path);
        }
    }
}
