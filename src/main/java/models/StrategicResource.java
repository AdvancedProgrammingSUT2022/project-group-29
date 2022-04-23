package models;

import java.util.ArrayList;

public class StrategicResource extends Resource{
    /*TODO Part Buildings Requiring this Resource And Complete  Technology
     */
    private static ArrayList<StrategicResource> allStrategicResource = new ArrayList<>();
    private Technology neededTechnology;
    private final ArrayList<Unit>UnitsRequiringThisResource = new ArrayList<>();

    static{
        allStrategicResource.add(new StrategicResource("Strategic", "Coal", 0, 1, 0,
                Improvement.getImprovementByName("Mine"), Technology.getTechnologyByName("ScientificTheory")));
        allStrategicResource.add(new StrategicResource("Strategic", "Horses", 0, 1, 0,
                Improvement.getImprovementByName("Pasture"), Technology.getTechnologyByName("AnimalHusbandry")));
        allStrategicResource.add(new StrategicResource("Strategic", "Iron", 0, 1, 0,
                Improvement.getImprovementByName("Mine"), Technology.getTechnologyByName("IronWorking")));
    }
    public StrategicResource(String type, String name, int food, int production, int gold ,
                             Improvement neededImprovement,Technology neededTechnology) {
        super(type, name, food, production, gold, neededImprovement);
        this.neededTechnology = neededTechnology;

    }
}
