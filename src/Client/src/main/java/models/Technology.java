package models;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Technology {
    private String type;
    private String name;
    private int cost;
    private ArrayList<Technology> neededTechnologies;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<Technology> getNeededTechnologies() {
        return neededTechnologies;
    }

    public boolean equals(Technology other) {
        if (this.name.equals(other.name))
            return true;
        return false;
    }
}