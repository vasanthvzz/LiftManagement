package com.vasanthvz.liftmanagement.mainpage;

import java.util.Scanner;

public class MainPageView {

    MainPageModel mainPageModel;
    static Scanner sc= new Scanner(System.in);

    public MainPageView(){
        mainPageModel = new MainPageModel(this);
    }
    public void init() {
        System.out.println("Welcome to lift management System");
        mainPageModel.getInput();
    }

    void showMenu(){//default access modifier
        System.out.println("Please Enter a choice below : ");
        System.out.println("""
                1.Display Lifts\s
                2.Assign Lift\s
                3.Assign Nearest Lift\s
                4.Assign Nearest with Direction\s
                5.Assign with restriction \s
                6.Assign lift with least stops \s
                7.Assign with capacity\s
                9.Manual assign capacity                       
                0.Manual Assign""");
        mainPageModel.redirectChoice(sc.next());
    }
}
