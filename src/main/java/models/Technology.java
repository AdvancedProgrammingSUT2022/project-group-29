package models;

import enums.TechnologyEnum;

import java.util.ArrayList;

public class Technology {
    private String name;
    private int cost;
    private ArrayList<Technology> neededTechnology;
    private static ArrayList<Technology>allTechnology = new ArrayList<>();
    public Technology(String type) {
        this.name = type;
    }
    public static Technology getTechnologyByName(String name) {
        for (Technology technology :allTechnology){
            if(technology.name.equals(name))
                return technology;
        }
        return null;
    }
}
//public class Technology {
//    private String type;
//    private String name;
//    private int cost;
//    private ArrayList<Technology> neededTechnologies;
//    public Technology(TechnologyEnum type) {
//        this.type = type.getType();
//        this.name = type.getName();
//        this.cost = type.getCost();
//        this.neededTechnologies = type.getNeededTechnologies();
//
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCost() {
//        return cost;
//    }
//
//    public ArrayList<Technology> getNeededTechnologies() {
//        return neededTechnologies;
//    }
//}