package com.vasanthvz.liftmanagement;

import com.vasanthvz.liftmanagement.mainpage.MainPageView;

public class LiftManagementMain {

    final static String name = "Lift management system";
    final static String version = "1.2.0";
    public static void main(String[] args) {
        new MainPageView().init();
    }

    //1.1.0 Fixes :
    //Structured the code with packages
    //Removed function from the pojo class
    //Removed static variables in pojo class
    //Removed unwanted spacing from the code
    //provided access specifier for all the functions and variables


    //1.2.0 Fixes :
    // Remove unnecessary static function from the Data Layer class
}
