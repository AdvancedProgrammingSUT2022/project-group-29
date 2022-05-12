package enums.modelsEnum;

import models.Technology;

import java.util.ArrayList;

public enum TechnologyEnum {
    AGRICULTURE("Ancient", "Agriculture", 20, new ArrayList<Technology>(), new ArrayList<Technology>() {{
        add(new Technology(POTTERY));
        add(new Technology(ANIMAL_HUSBANDRY));
        add(new Technology(ARCHERY));
        add(new Technology(MINING));
    }}),

    ANIMAL_HUSBANDRY("Ancient", "Animal_Husbandry", 35, new ArrayList<Technology>() {{
        add(new Technology(AGRICULTURE));
    }}, new ArrayList<Technology>() {{
        add(new Technology(TRAPPING));
        add(new Technology(THE_WHEEL));
    }}),

    ARCHERY("Ancient", "Archery", 35, new ArrayList<Technology>() {{
        add(new Technology(AGRICULTURE));
    }}, new ArrayList<Technology>() {{
        add(new Technology(MATHEMATICS));
    }}),

    BRONZE_WORKING("Ancient", "Bronze_Working", 55, new ArrayList<Technology>() {{
        add(new Technology(MINING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(IRON_WORKING));
    }}),

    CALENDAR("Ancient", "Calendar", 70, new ArrayList<Technology>() {{
        add(new Technology(POTTERY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(THEOLOGY));
    }}),

    MASONRY("Ancient", "Masonry", 55, new ArrayList<Technology>() {{
        add(new Technology(MINING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CONSTRUCTION));
    }}),

    MINING("Ancient", "Mining", 35, new ArrayList<Technology>() {{
        add(new Technology(AGRICULTURE));
    }}, new ArrayList<Technology>() {{
        add(new Technology(MASONRY));
        add(new Technology(BRONZE_WORKING));

    }}),
    POTTERY("Ancient", "Pottery", 35, new ArrayList<Technology>() {{
        add(new Technology(AGRICULTURE));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CALENDAR));
        add(new Technology(WRITING));
    }}),

    THE_WHEEL("Ancient", "The_Wheel", 55, new ArrayList<Technology>() {{
        add(new Technology(ANIMAL_HUSBANDRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(HORSEBACK_RIDING));
        add(new Technology(MATHEMATICS));
    }}),

    TRAPPING("Ancient", "Trapping", 55, new ArrayList<Technology>() {{
        add(new Technology(ANIMAL_HUSBANDRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CIVIL_SERVICE));
    }}),

    WRITING("Ancient", "Writing", 55, new ArrayList<Technology>() {{
        add(new Technology(POTTERY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(PHILOSOPHY));
    }}),

    CONSTRUCTION("Classical", "Construction", 100, new ArrayList<Technology>() {{
        add(new Technology(MASONRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ENGINEERING));
    }}),

    HORSEBACK_RIDING("Classical", "Horseback_Riding", 100, new ArrayList<Technology>() {{
        add(new Technology(THE_WHEEL));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CHIVALRY));
    }}),

    IRON_WORKING("Classical", "Iron_Working", 150, new ArrayList<Technology>() {{
        add(new Technology(BRONZE_WORKING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(METAL_CASTING));
    }}),

    MATHEMATICS("Classical", "Mathematics", 100, new ArrayList<Technology>() {{
        add(new Technology(THE_WHEEL));
        add(new Technology(ARCHERY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CURRENCY));
        add(new Technology(ENGINEERING));
    }}),

    PHILOSOPHY("Classical", "Philosophy", 100, new ArrayList<Technology>() {{
        add(new Technology(WRITING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(THEOLOGY));
        add(new Technology(CIVIL_SERVICE));
    }}),


    CHIVALRY("Medieval", "Chivalry", 440, new ArrayList<Technology>() {{
        add(new Technology(CIVIL_SERVICE));
        add(new Technology(HORSEBACK_RIDING));
        add(new Technology(CURRENCY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(BANKING));
    }}),

    CIVIL_SERVICE("Medieval", "Civil_Service", 400, new ArrayList<Technology>() {{
        add(new Technology(PHILOSOPHY));
        add(new Technology(TRAPPING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CHIVALRY));
    }}),

    CURRENCY("Medieval", "Currency", 250, new ArrayList<Technology>() {{
        add(new Technology(MATHEMATICS));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CHIVALRY));
    }}),

    EDUCATION("Medieval", "Education", 440, new ArrayList<Technology>() {{
        add(new Technology(THEOLOGY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ACOUSTICS));
        add(new Technology(BANKING));
    }}),

    ENGINEERING("Medieval", "Engineering", 250, new ArrayList<Technology>() {{
        add(new Technology(MATHEMATICS));
        add(new Technology(CONSTRUCTION));
    }}, new ArrayList<Technology>() {{
        add(new Technology(MACHINERY));
        add(new Technology(PHYSICS));
    }}),

    MACHINERY("Medieval", "Machinery", 440, new ArrayList<Technology>() {{
        add(new Technology(ENGINEERING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(PRINTING_PRESS));
    }}),

    METAL_CASTING("Medieval", "Metal_Casting", 240, new ArrayList<Technology>() {{
        add(new Technology(IRON_WORKING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(PHYSICS));
        add(new Technology(STEEL));
    }}),

    PHYSICS("Medieval", "Physics", 440, new ArrayList<Technology>() {{
        add(new Technology(ENGINEERING));
        add(new Technology(METAL_CASTING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(PRINTING_PRESS));
        add(new Technology(GUNPOWDER));
    }}),

    STEEL("Medieval", "Steel", 440, new ArrayList<Technology>() {{
        add(new Technology(METAL_CASTING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(GUNPOWDER));
    }}),

    THEOLOGY("Medieval", "Theology", 250, new ArrayList<Technology>() {{
        add(new Technology(CALENDAR));
        add(new Technology(PHILOSOPHY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(EDUCATION));
    }}),


    ACOUSTICS("Renaissance", "Acoustics", 650, new ArrayList<Technology>() {{
        add(new Technology(EDUCATION));
    }}, new ArrayList<Technology>() {{
        add(new Technology(SCIENTIFIC_THEORY));
    }}),

    ARCHAEOLOGY("Renaissance", "Archaeology", 1300, new ArrayList<Technology>()
            , new ArrayList<Technology>() {{
        add(new Technology(BIOLOGY));
    }}),

    BANKING("Renaissance", "Banking", 650, new ArrayList<Technology>() {{
        add(new Technology(EDUCATION));
        add(new Technology(CHIVALRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ECONOMICS));
    }}),

    CHEMISTRY("Renaissance", "Chemistry", 900, new ArrayList<Technology>() {{
        add(new Technology(GUNPOWDER));
    }}, new ArrayList<Technology>() {{
        add(new Technology(MILITARY_SCIENCE));
        add(new Technology(FERTILIZER));
    }}),

    ECONOMICS("Renaissance", "Economics", 900, new ArrayList<Technology>() {{
        add(new Technology(BANKING));
        add(new Technology(PRINTING_PRESS));
    }}, new ArrayList<Technology>() {{
        add(new Technology(MILITARY_SCIENCE));
    }}),

    FERTILIZER("Renaissance", "Fertilizer", 1300, new ArrayList<Technology>() {{
        add(new Technology(CHEMISTRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(DYNAMITE));
    }}),

    GUNPOWDER("Renaissance", "Gunpowder", 680, new ArrayList<Technology>() {{
        add(new Technology(PHYSICS));
        add(new Technology(STEEL));
    }}, new ArrayList<Technology>() {{
        add(new Technology(CHEMISTRY));
        add(new Technology(METALLURGY));
    }}),

    METALLURGY("Renaissance", "Metallurgy", 900, new ArrayList<Technology>() {{
        add(new Technology(GUNPOWDER));
    }}, new ArrayList<Technology>() {{
        add(new Technology(RIFLING));
    }}),

    MILITARY_SCIENCE("Renaissance", "Military_Science", 1300, new ArrayList<Technology>() {{
        add(new Technology(ECONOMICS));
        add(new Technology(CHEMISTRY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(STEAM_POWER));
    }}),

    PRINTING_PRESS("Renaissance", "Printing_Press", 650, new ArrayList<Technology>() {{
        add(new Technology(MACHINERY));
        add(new Technology(PHYSICS));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ECONOMICS));
    }}),

    RIFLING("Renaissance", "Rifling", 1425, new ArrayList<Technology>() {{
        add(new Technology(METALLURGY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(DYNAMITE));
    }}),

    SCIENTIFIC_THEORY("Renaissance", "Scientific_Theory", 1300, new ArrayList<Technology>() {{
        add(new Technology(ACOUSTICS));
    }}, new ArrayList<Technology>() {{
        add(new Technology(BIOLOGY));
        add(new Technology(STEAM_POWER));
    }}),


    BIOLOGY("Industrial", "Biology", 1680, new ArrayList<Technology>() {{
        add(new Technology(ARCHAEOLOGY));
        add(new Technology(SCIENTIFIC_THEORY));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ELECTRICITY));
    }}),

    COMBUSTION("Industrial", "Combustion", 2200, new ArrayList<Technology>() {{
        add(new Technology(REPLACEABLE_PARTS));
        add(new Technology(RAILROAD));
        add(new Technology(DYNAMITE));
    }}, new ArrayList<>()),

    DYNAMITE("Industrial", "Dynamite", 1900, new ArrayList<Technology>() {{
        add(new Technology(FERTILIZER));
        add(new Technology(RIFLING));
    }}, new ArrayList<Technology>() {{
        add(new Technology(COMBUSTION));
    }}),

    ELECTRICITY("Industrial", "Electricity", 1900, new ArrayList<Technology>() {{
        add(new Technology(BIOLOGY));
        add(new Technology(STEAM_POWER));
    }}, new ArrayList<Technology>() {{
        add(new Technology(TELEGRAPH));
        add(new Technology(RADIO));
    }}),

    RADIO("Industrial", "Radio", 2200, new ArrayList<Technology>() {{
        add(new Technology(ELECTRICITY));
    }}, new ArrayList<>()),

    RAILROAD("Industrial", "Railroad", 1900, new ArrayList<Technology>() {{
        add(new Technology(STEAM_POWER));
    }}, new ArrayList<Technology>() {{
        add(new Technology(COMBUSTION));
    }}),

    REPLACEABLE_PARTS("Industrial", "Replaceable ", 1900, new ArrayList<Technology>() {{
        add(new Technology(STEAM_POWER));
    }}, new ArrayList<Technology>() {{
        add(new Technology(COMBUSTION));
    }}),

    STEAM_POWER("Industrial", "Steam Power", 1680, new ArrayList<Technology>() {{
        add(new Technology(SCIENTIFIC_THEORY));
        add(new Technology(MILITARY_SCIENCE));
    }}, new ArrayList<Technology>() {{
        add(new Technology(ELECTRICITY));
        add(new Technology(REPLACEABLE_PARTS));
        add(new Technology(RAILROAD));
    }}),

    TELEGRAPH("Industrial", "Telegraph", 2200, new ArrayList<Technology>() {{
        add(new Technology(ELECTRICITY));
    }}, new ArrayList<>());

    private String type;
    private String name;
    private int cost;
    private ArrayList<Technology> neededTechnologies;
    private ArrayList<Technology> leadsToTechnologies;

    TechnologyEnum(String type, String name, int cost, ArrayList<Technology> neededTechnologies, ArrayList<Technology> leadsToTechnologies) {
        this.type = type;
        this.name = name;
        this.cost = cost;
        this.neededTechnologies = neededTechnologies;
        this.leadsToTechnologies = leadsToTechnologies;
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

    public ArrayList<Technology> getLeadsToTechnologies() {
        return leadsToTechnologies;
    }
}
