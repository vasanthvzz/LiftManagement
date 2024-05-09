package com.vasanthvz.liftmanagement.model;

import java.util.HashSet;

public class Lift {
    public String name;
    public int position;
    public int capacity;
    public HashSet<Integer> path;

    public Lift(int i , HashSet<Integer> path) {
        this.name = "L" + i;
        this.position = 0;
        this.path = path;
    }
}
