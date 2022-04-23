package models;

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
