package enums;

import models.Technology;

import java.util.ArrayList;

public enum TechnologyEnum {
    AGRICULTURE("Ancient", "Agriculture", 20, new ArrayList<Technology>()),
    ANIMAL_HUSBANDRY("Ancient", "Animal_Husbandry", 35, new ArrayList<Technology>()),
    ARCHERY("Ancient", "Archery", 35, new ArrayList<Technology>()),
    BRONZE_WORKING("Ancient", "Bronze_Working", 55, new ArrayList<Technology>()),
    CALENDAR("Ancient", "Calendar", 70, new ArrayList<Technology>()),
    MASONRY("Ancient", "Masonry", 55, new ArrayList<Technology>()),
    MINING("Ancient", "Mining", 35, new ArrayList<Technology>()),
    POTTERY("Ancient", "Pottery", 35, new ArrayList<Technology>()),
    THE_WHEEL("Ancient", "The_Wheel", 55, new ArrayList<Technology>()),
    TRAPPING("Ancient", "Trapping", 55, new ArrayList<Technology>()),
    WRITING("Ancient", "Writing", 55, new ArrayList<Technology>()),

    CONSTRUCTION("Classical", "Construction", 100, new ArrayList<Technology>()),
    HORSEBACK_RIDING("Classical", "Horseback_Riding", 100, new ArrayList<Technology>()),
    IRON_WORKING("Classical", "Iron_Working", 150, new ArrayList<Technology>()),
    MATHEMATICS("Classical", "Mathematics", 100, new ArrayList<Technology>()),
    PHILOSOPHY("Classical", "Philosophy", 100, new ArrayList<Technology>()),


    CHIVALRY("Medieval", "Chivalry", 440, new ArrayList<Technology>()),
    CIVIL_SERVICE("Medieval", "Civil_Service", 400, new ArrayList<Technology>()),
    CURRENCY("Medieval", "Currency", 250, new ArrayList<Technology>()),
    EDUCATION("Medieval", "Education", 440, new ArrayList<Technology>()),
    ENGINEERING("Medieval", "Engineering", 250, new ArrayList<Technology>()),
    MACHINERY("Medieval", "Machinery", 440, new ArrayList<Technology>()),
    METAL_CASTING("Medieval", "Metal_Casting", 240, new ArrayList<Technology>()),
    PHYSICS("Medieval", "Physics", 440, new ArrayList<Technology>()),
    STEEL("Medieval", "Steel", 440, new ArrayList<Technology>()),
    THEOLOGY("Medieval", "Theology", 250, new ArrayList<Technology>()),

    ACOUSTICS("Renaissance","Acoustics",650, new ArrayList<Technology>()),
    ARCHAEOLOGY("Renaissance","Archaeology",1300, new ArrayList<Technology>()),
    BANKING("Renaissance","Banking",650, new ArrayList<Technology>()),
    CHEMISTRY("Renaissance","Chemistry",900, new ArrayList<Technology>()),
    ECONOMICS("Renaissance","Economics",900, new ArrayList<Technology>()),
    FERTILIZER("Renaissance","Fertilizer",1300, new ArrayList<Technology>()),
    GUNPOWDER("Renaissance","Gunpowder",680, new ArrayList<Technology>()),
    METALLURGY("Renaissance","Metallurgy",900, new ArrayList<Technology>()),
    MILITARY_SCIENCE("Renaissance","Military_Science",1300, new ArrayList<Technology>()),
    PRINTING_PRESS("Renaissance","Printing_Press",650, new ArrayList<Technology>()),
    RIFLING("Renaissance","Rifling",1425, new ArrayList<Technology>()),
    SCIENTIFIC_THEORY("Renaissance","Scientific_Theory",1300, new ArrayList<Technology>()),


    BIOLOGY("Industrial","Biology",1680, new ArrayList<Technology>()),
    COMBUSTION("Industrial","Combustion",2200, new ArrayList<Technology>()),
    DYNAMITE("Industrial","Dynamite",1900, new ArrayList<Technology>()),
    ELECTRICITY("Industrial","Electricity",1900, new ArrayList<Technology>()),
    RADIO("Industrial","Radio",2200, new ArrayList<Technology>()),
    RAILROAD("Industrial","Railroad",1900, new ArrayList<Technology>()),
    REPLACEABLE_PARTS("Industrial","Replaceable ",1900, new ArrayList<Technology>()),
    STEAM_POWER("Industrial","Steam Power",1680, new ArrayList<Technology>()),
    TELEGRAPH("Industrial","Telegraph",2200, new ArrayList<Technology>());

    private String type;
    private String name;
    private int cost;
    private ArrayList<Technology> neededTechnologies;

    TechnologyEnum(String type, String name, int cost, ArrayList<Technology> neededTechnologies) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.neededTechnologies = neededTechnologies;
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
}
