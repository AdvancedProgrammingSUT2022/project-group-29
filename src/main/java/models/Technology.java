package models;

import enums.modelsEnum.TechnologyEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Technology {
    private static List<TechnologyEnum> allTechnologies = Arrays.asList(TechnologyEnum.values());
    private String type;
    private String name;
    private int cost;
    private ArrayList<Technology> neededTechnologies;

    public Technology(TechnologyEnum type) {
        this.type = type.getType();
        this.name = type.getName();
        this.cost = type.getCost();
        this.neededTechnologies = type.getNeededTechnologies();

    }

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

    public static List<TechnologyEnum> getAllTechnologies() {
        return null;
    }

    public boolean equals(Technology other) {
        if (this.name.equals(other.name))
            return true;
        return false;
    }
}