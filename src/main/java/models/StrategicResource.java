package models;

import java.util.ArrayList;

public class StrategicResource extends Resource{
    private Technology neededTechnology;

    public StrategicResource(String type, String name, int food, int production, int gold , Improvement neededImprovement) {
        super(type, name, food, production, gold, neededImprovement);
    }
}
