package models;

import enums.ResourceEnum;

import java.util.ArrayList;

public class StrategicResource extends Resource {
    /*TODO Part Buildings Requiring this Resource And Complete  neededTechnology
     */
    private Technology neededTechnology;
    private final ArrayList<Unit> UnitsRequiringThisResource = new ArrayList<>();

    public StrategicResource(ResourceEnum type) {
        super(type);
    }
}
