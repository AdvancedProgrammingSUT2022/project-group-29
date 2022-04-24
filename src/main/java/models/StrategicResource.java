package models;

import java.util.ArrayList;

public class StrategicResource extends Resource{
    /*TODO Part Buildings Requiring this Resource And Complete  Technology
     */
    private static ArrayList<StrategicResource> allStrategicResource = new ArrayList<>();
    private Technology neededTechnology;
    private final ArrayList<Unit>UnitsRequiringThisResource = new ArrayList<>();

    public StrategicResource(String type, String name, int food, int production, int gold ,
                             Improvement neededImprovement,Technology neededTechnology) {
        super(type, name, food, production, gold, neededImprovement);
        this.neededTechnology = neededTechnology;

    }
}
