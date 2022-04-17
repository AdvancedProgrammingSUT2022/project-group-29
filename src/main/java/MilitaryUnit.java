public class MilitaryUnit extends Unit{
    private int combatStrength;
    private int rangedCombatStrength, range;
    private Technology neededTechnology;
    private Resource neededResource;

    public MilitaryUnit(String type) {
        super(type);
    }

    public int getCombatStrength() {
        return combatStrength;
    }

    public int getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public int getRange() {
        return range;
    }

    public Technology getNeededTechnology() {
        return neededTechnology;
    }

    public Resource getNeededResource() {
        return neededResource;
    }
}
