package enums.modelsEnum;

import models.Technology;

import java.util.ArrayList;

public enum TechnologyEnum {
    AGRICULTURE("Ancient", "Agriculture", 20, new ArrayList<>()),

    ANIMAL_HUSBANDRY("Ancient", "Animal_Husbandry", 35, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.AGRICULTURE);
    }}),

    ARCHERY("Ancient", "Archery", 35, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.AGRICULTURE);
    }}),

    MINING("Ancient", "Mining", 35, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.AGRICULTURE);
    }}),

    BRONZE_WORKING("Ancient", "Bronze_Working", 55, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MINING);
    }}),

    POTTERY("Ancient", "Pottery", 35, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.AGRICULTURE);
    }}),

    CALENDAR("Ancient", "Calendar", 70, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.POTTERY);
    }}),


    TRAPPING("Ancient", "Trapping", 55, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ANIMAL_HUSBANDRY);
    }}),

    WRITING("Ancient", "Writing", 55, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.POTTERY);
    }}),

    MASONRY("Ancient", "Masonry", 55, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MINING);
    }}),

    CONSTRUCTION("Classical", "Construction", 100, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MASONRY);
    }}),

    THE_WHEEL("Ancient", "The_Wheel", 55, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ANIMAL_HUSBANDRY);
    }}),

    HORSEBACK_RIDING("Classical", "Horseback_Riding", 100, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.THE_WHEEL);
    }}),

    IRON_WORKING("Classical", "Iron_Working", 150, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.BRONZE_WORKING);
    }}),

    MATHEMATICS("Classical", "Mathematics", 100, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.THE_WHEEL);
        add(TechnologyEnum.ARCHERY);
    }}),

    PHILOSOPHY("Classical", "Philosophy", 100, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.WRITING);
    }}),


    CIVIL_SERVICE("Medieval", "Civil_Service", 400, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.PHILOSOPHY);
        add(TechnologyEnum.TRAPPING);
    }}),

    CURRENCY("Medieval", "Currency", 250, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MATHEMATICS);
    }}),

    CHIVALRY("Medieval", "Chivalry", 440, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.CIVIL_SERVICE);
        add(TechnologyEnum.HORSEBACK_RIDING);
        add(TechnologyEnum.CURRENCY);
    }}),

    THEOLOGY("Medieval", "Theology", 250, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.CALENDAR);
        add(TechnologyEnum.PHILOSOPHY);
    }}),

    EDUCATION("Medieval", "Education", 440, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.THEOLOGY);
    }}),

    ENGINEERING("Medieval", "Engineering", 250, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MATHEMATICS);
        add(TechnologyEnum.CONSTRUCTION);
    }}),

    MACHINERY("Medieval", "Machinery", 440, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ENGINEERING);
    }}),

    METAL_CASTING("Medieval", "Metal_Casting", 240, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.IRON_WORKING);
    }}),

    PHYSICS("Medieval", "Physics", 440, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ENGINEERING);
        add(TechnologyEnum.METAL_CASTING);
    }}),

    STEEL("Medieval", "Steel", 440, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.METAL_CASTING);
    }}),

    ACOUSTICS("Renaissance", "Acoustics", 650, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.EDUCATION);
    }}),

    ARCHAEOLOGY("Renaissance", "Archaeology", 1300, new ArrayList<>()),

    BANKING("Renaissance", "Banking", 650, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.EDUCATION);
        add(TechnologyEnum.CHIVALRY);
    }}),

    GUNPOWDER("Renaissance", "Gunpowder", 680, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.PHYSICS);
        add(TechnologyEnum.STEEL);
    }}),

    CHEMISTRY("Renaissance", "Chemistry", 900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.GUNPOWDER);
    }}),

    PRINTING_PRESS("Renaissance", "Printing_Press", 650, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.MACHINERY);
        add(TechnologyEnum.PHYSICS);
    }}),

    ECONOMICS("Renaissance", "Economics", 900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.BANKING);
        add(TechnologyEnum.PRINTING_PRESS);
    }}),

    FERTILIZER("Renaissance", "Fertilizer", 1300, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.CHEMISTRY);
    }}),

    METALLURGY("Renaissance", "Metallurgy", 900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.GUNPOWDER);
    }}),

    MILITARY_SCIENCE("Renaissance", "Military_Science", 1300, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ECONOMICS);
        add(TechnologyEnum.CHEMISTRY);
    }}),


    RIFLING("Renaissance", "Rifling", 1425, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.METALLURGY);
    }}),

    SCIENTIFIC_THEORY("Renaissance", "Scientific_Theory", 1300, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ACOUSTICS);
    }}),


    BIOLOGY("Industrial", "Biology", 1680, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ARCHAEOLOGY);
        add(TechnologyEnum.SCIENTIFIC_THEORY);
    }}),

    STEAM_POWER("Industrial", "Steam Power", 1680, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.SCIENTIFIC_THEORY);
        add(TechnologyEnum.MILITARY_SCIENCE);
    }}),

    REPLACEABLE_PARTS("Industrial", "Replaceable ", 1900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.STEAM_POWER);
    }}),

    RAILROAD("Industrial", "Railroad", 1900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.STEAM_POWER);
    }}),

    DYNAMITE("Industrial", "Dynamite", 1900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.FERTILIZER);
        add(TechnologyEnum.RIFLING);
    }}),

    COMBUSTION("Industrial", "Combustion", 2200, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.REPLACEABLE_PARTS);
        add(TechnologyEnum.RAILROAD);
        add(TechnologyEnum.DYNAMITE);
    }}),

    ELECTRICITY("Industrial", "Electricity", 1900, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.BIOLOGY);
        add(TechnologyEnum.STEAM_POWER);
    }}),

    RADIO("Industrial", "Radio", 2200, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ELECTRICITY);
    }}),

    TELEGRAPH("Industrial", "Telegraph", 2200, new ArrayList<TechnologyEnum>() {{
        add(TechnologyEnum.ELECTRICITY);
    }});

    private final String type;
    private final String name;
    private final int cost;
    private final ArrayList<TechnologyEnum> neededTechnologiesEnum;


    TechnologyEnum(String type, String name, int cost, ArrayList<TechnologyEnum> neededTechnologiesEnum) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.neededTechnologiesEnum = neededTechnologiesEnum;
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
        ArrayList<Technology> technologies = new ArrayList<>();
        for (TechnologyEnum technologyEnum : neededTechnologiesEnum) {
            technologies.add(new Technology(technologyEnum));
        }
        return technologies;
    }
}
