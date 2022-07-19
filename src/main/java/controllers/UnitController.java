package controllers;

import enums.modelsEnum.ImprovementsEnum;
import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.*;

public class UnitController {
    private static UnitController instance = null;
    private Game game = GameController.getInstance().getGame();

    private UnitController() {
    }

    public static UnitController getInstance() {
        if (instance == null)
            instance = new UnitController();
        return instance;
    }

    public void changePlace(int x, int y, MilitaryUnit militaryUnit) {
        militaryUnit.setxEnd(x);
        militaryUnit.setyEnd(y);
        while (militaryUnit.getMovement() > 0) {
            if (y > militaryUnit.getY() && movePossible(militaryUnit.getX(), militaryUnit.getY() + 1, militaryUnit))
                moveRight(militaryUnit);
            else if (y < militaryUnit.getY() && movePossible(militaryUnit.getX(), militaryUnit.getY() - 1, militaryUnit))
                moveLeft(militaryUnit);
            else if (x > militaryUnit.getX() && movePossible(militaryUnit.getX() + 1, militaryUnit.getY(), militaryUnit))
                moveDown(militaryUnit);
            else if (x < militaryUnit.getX() && movePossible(militaryUnit.getX() - 1, militaryUnit.getY(), militaryUnit))
                moveUp(militaryUnit);
            else {
                militaryUnit.setxEnd(-1);
                militaryUnit.setyEnd(-1);
                return;
            }
        }
    }

    private void moveUp(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - game.getMap()[militaryUnit.getX() - 1][militaryUnit.getY()].getMovementCost());
        game.getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        game.getMap()[militaryUnit.getX() - 1][militaryUnit.getY()].setMilitaryUnit(militaryUnit);
        militaryUnit.setX(militaryUnit.getX() - 1);
    }

    private void moveLeft(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - game.getMap()[militaryUnit.getX()][militaryUnit.getY() - 1].getMovementCost());
        game.getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        game.getMap()[militaryUnit.getX()][militaryUnit.getY() - 1].setMilitaryUnit(militaryUnit);
        militaryUnit.setY(militaryUnit.getY() - 1);
    }

    private void moveDown(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - game.getMap()[militaryUnit.getX() + 1][militaryUnit.getY()].getMovementCost());
        game.getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        game.getMap()[militaryUnit.getX() + 1][militaryUnit.getY()].setMilitaryUnit(militaryUnit);
        militaryUnit.setX(militaryUnit.getX() + 1);
    }

    private void moveRight(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - game.getMap()[militaryUnit.getX()][militaryUnit.getY() + 1].getMovementCost());
        game.getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        game.getMap()[militaryUnit.getX()][militaryUnit.getY() + 1].setMilitaryUnit(militaryUnit);
        militaryUnit.setY(militaryUnit.getY() + 1);
    }

    public void changePlace(int x, int y, Unit unit) {
        unit.setxEnd(x);
        unit.setyEnd(y);
        while (unit.getMovement() > 0) {
            if (y > unit.getY() && movePossible(unit.getX(), unit.getY() + 1, unit))
                moveRight(unit);
            else if (y < unit.getY() && movePossible(unit.getX(), unit.getY() - 1, unit))
                moveLeft(unit);
            else if (x > unit.getX() && movePossible(unit.getX() + 1, unit.getY(), unit))
                moveDown(unit);
            else if (x < unit.getX() && movePossible(unit.getX() - 1, unit.getY(), unit))
                moveUp(unit);
            else {
                unit.setxEnd(-1);
                unit.setyEnd(-1);
                return;
            }
        }
    }

    private void moveUp(Unit unit) {
        unit.setMovement(unit.getMovement() - game.getMap()[unit.getX() - 1][unit.getY()].getMovementCost());
        game.getMap()[unit.getX()][unit.getY()].setCivilian(null);
        game.getMap()[unit.getX() - 1][unit.getY()].setCivilian(unit);
        unit.setX(unit.getX() - 1);
    }

    private void moveLeft(Unit unit) {
        unit.setMovement(unit.getMovement() - game.getMap()[unit.getX()][unit.getY() - 1].getMovementCost());
        game.getMap()[unit.getX()][unit.getY()].setCivilian(null);
        game.getMap()[unit.getX()][unit.getY() - 1].setCivilian(unit);
        unit.setY(unit.getY() - 1);
    }

    private void moveDown(Unit unit) {
        unit.setMovement(unit.getMovement() - game.getMap()[unit.getX() + 1][unit.getY()].getMovementCost());
        game.getMap()[unit.getX()][unit.getY()].setCivilian(null);
        game.getMap()[unit.getX() + 1][unit.getY()].setCivilian(unit);
        unit.setX(unit.getX() + 1);
    }

    private void moveRight(Unit unit) {
        unit.setMovement(unit.getMovement() - game.getMap()[unit.getX()][unit.getY() + 1].getMovementCost());
        game.getMap()[unit.getX()][unit.getY()].setCivilian(null);
        game.getMap()[unit.getX()][unit.getY() + 1].setCivilian(unit);
        unit.setY(unit.getY() + 1);
    }


    private boolean movePossible(int x, int y, MilitaryUnit militaryUnit) {
        if (isZoc(x, y))
            return false;
        if (!game.getMap()[x][y].isHasRoute()) {
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                    game.getMap()[x][y].getMilitaryUnit() != null ||
                    (militaryUnit.getMovement() < game.getMap()[x][y].getMovementCost() &&
                            !militaryUnit.getName().equals("scout")) ||
                    (GameController.getInstance().getCivilization(x, y) != null &&
                            !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                            game.getCurrentCivilization().leaderName())))
                return false;
        } else {
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                    game.getMap()[x][y].getMilitaryUnit() != null ||
                    (militaryUnit.getMovement() < 1 &&
                            !militaryUnit.getName().equals("scout"))||
                    (GameController.getInstance().getCivilization(x, y) != null &&
                            !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                            game.getCurrentCivilization().leaderName())))
                return false;
        }
        return true;
    }

    private boolean movePossible(int x, int y, Unit unit) {
        if (isZoc(x, y))
            return false;
        if (!game.getMap()[x][y].isHasRoute()) {
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                    game.getMap()[x][y].getMilitaryUnit() != null ||
                    unit.getMovement() < game.getMap()[x][y].getMovementCost() ||
                    (GameController.getInstance().getCivilization(x, y) != null &&
                            !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                            game.getCurrentCivilization().leaderName())))
                return false;
        } else {
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                    game.getMap()[x][y].getMilitaryUnit() != null ||
                    unit.getMovement() < 1 ||
                    (GameController.getInstance().getCivilization(x, y) != null &&
                            !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                            game.getCurrentCivilization().leaderName())))
                return false;
        }
        return true;
    }

    private boolean isZoc(int x, int y) {
        for (Civilization civilization : GameController.getInstance().getGame().getCivilizations()) {
            if (!civilization.getLeader().getUsername().equals(GameController.getInstance().getGame().getCurrentCivilization().getLeader().getUsername())) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
                            if (militaryUnit.getX() == i && militaryUnit.getY() == j) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void changePlaceAfterTurn(MilitaryUnit militaryUnit) {
        if (militaryUnit.getxEnd() != -1)
            changePlace(militaryUnit.getxEnd(), militaryUnit.getyEnd(), militaryUnit);
    }

    private void changePlaceAfterTurn(Unit unit) {
        System.out.println(unit.getxEnd() + " " + unit.getyEnd());
        if (unit.getxEnd() != -1)
            changePlace(unit.getxEnd(), unit.getyEnd(), unit);
    }

    public void changePlaceAfterTurnAllUnits() {
        for (Civilization civilization : game.getCivilizations()) {
            for (int i = 0; i < civilization.getUnits().size(); i++) {
                if (civilization.getUnits().get(i).getxEnd() == -1)
                    civilization.getUnits().get(i).setHasDone(false);
                civilization.getUnits().get(i).setMovement(civilization.getUnits().get(i).getMaxMovement());
            }
            for (int i = 0; i < civilization.getMilitaryUnits().size(); i++) {
                if (civilization.getMilitaryUnits().get(i).getxEnd() == -1)
                    civilization.getMilitaryUnits().get(i).setHasDone(false);
                civilization.getMilitaryUnits().get(i).setMovement(civilization.getMilitaryUnits().get(i).getMaxMovement());
            }
        }

        for (Civilization civilization : game.getCivilizations()) {
            for (int i = 0; i < civilization.getUnits().size(); i++) {
                changePlaceAfterTurn(civilization.getUnits().get(i));
            }
            for (int i = 0; i < civilization.getMilitaryUnits().size(); i++) {
                changePlaceAfterTurn(civilization.getMilitaryUnits().get(i));
            }
        }
    }

    public String moveUnit(int x, int y) {
        if (game.getSelectedCombatUnit() != null) {
            if (!game.getSelectedCombatUnit().getState().equals("ready"))
                return "unit is not ready";
            if (game.getSelectedCombatUnit().isHasDone())
                return "unit has done its work";
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean"))
                return "can not move to mountain or ocean";
            if (GameController.getInstance().getCivilization(x, y) != null &&
                    !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                    game.getCurrentCivilization().leaderName()))
                return "can not move to enemies tile";
            if (CityController.getInstance().getCity(x, y) != null &&
                    CityController.getInstance().getCity(x, y).getMilitaryUnit() != null)
                return "there is a military unit in that city";

            game.getSelectedCombatUnit().setHasDone(true);
            game.getSelectedCombatUnit().setxEnd(x);
            game.getSelectedCombatUnit().setyEnd(y);
        } else if (game.getSelectedNonCombatUnit() != null) {
            if (!game.getSelectedNonCombatUnit().getState().equals("ready"))
                return "unit is not ready";
            if (game.getSelectedNonCombatUnit().isHasDone())
                return "unit has done its work";
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean"))
                return "can not move to mountain or ocean";
            if (GameController.getInstance().getCivilization(x, y) != null &&
                    !GameController.getInstance().getCivilization(x, y).leaderName().equals(
                    game.getCurrentCivilization().leaderName()))
                return "can not move to enemies tile";
            if (CityController.getInstance().getCity(x ,y) != null &&
                    CityController.getInstance().getCity(x, y).getCivilian() != null)
                return "there is a non combat unit in that city";

            game.getSelectedNonCombatUnit().setHasDone(true);
            game.getSelectedNonCombatUnit().setxEnd(x);
            game.getSelectedNonCombatUnit().setyEnd(y);
        } else
            return "no selected unit";

        return "unit moved successfully";
    }

    public String unitSleep() {
        if (game.getSelectedCombatUnit() != null) {
            game.getSelectedCombatUnit().setState("sleep");
        } else if (game.getSelectedNonCombatUnit() != null) {
            game.getSelectedNonCombatUnit().setState("sleep");
        } else
            return "no selected unit";
        return "unit slept successfully";
    }

    public String unitAlert() {
        if (game.getSelectedCombatUnit() != null) {
            game.getSelectedCombatUnit().setState("alert");
        } else if (game.getSelectedNonCombatUnit() != null) {
            game.getSelectedNonCombatUnit().setState("alert");
        } else
            return "no selected unit";
        return "unit alerted successfully";
    }

    public void unitHeal(MilitaryUnit militaryUnit) {
        if (militaryUnit != null) {
            if (militaryUnit.isHasDone())
                return;
            militaryUnit.setHp((militaryUnit.getFullHp() -
                    militaryUnit.getHp()) / 2 + militaryUnit.getHp() + 1);
            militaryUnit.setHasDone(true);
        }
    }

    public String unitHeal() {
        if (game.getSelectedCombatUnit() != null) {
            if (game.getSelectedCombatUnit().isHasDone())
                return "unit has done its work";
            game.getSelectedCombatUnit().setHp((game.getSelectedCombatUnit().getFullHp() -
                    game.getSelectedCombatUnit().getHp()) / 2 + game.getSelectedCombatUnit().getHp() + 1);
            game.getSelectedCombatUnit().setHasDone(true);
        } else
            return "no selected Combat unit";
        return null;
    }

    public String unitFortify() {
        if (game.getSelectedCombatUnit() != null) {
            if (game.getSelectedCombatUnit().isHasDone())
                return "unit has done its work";
        } else if (game.getSelectedNonCombatUnit() != null) {
            if (game.getSelectedNonCombatUnit().isHasDone())
                return "unit has done its work";
        } else
            return "no selected unit";

        game.getSelectedCombatUnit().setHasDone(true);
        game.getSelectedCombatUnit().setState("heal");
        unitHeal();
        return "unit is fortifying";
    }

    public void healAfterTurn() {
        for (Civilization civilization : game.getCivilizations()) {
            for (int i = 0; i < civilization.getMilitaryUnits().size(); i++) {
                if (civilization.getMilitaryUnits().get(i).getHp() == civilization.getMilitaryUnits().get(i).getFullHp()) {
                    civilization.getMilitaryUnits().get(i).setState("ready");
                    civilization.getMilitaryUnits().get(i).setHasDone(false);
                }
                if (civilization.getMilitaryUnits().get(i).getState().equals("heal")) {
                    unitHeal(civilization.getMilitaryUnits().get(i));
                }
            }
        }
    }

    public String deleteUnit() {
        if (game.getSelectedCombatUnit() != null) {
            int x = game.getSelectedCombatUnit().getX();
            int y = game.getSelectedCombatUnit().getY();
            game.getCurrentCivilization().addToNotifications("combat unit " +
                    game.getSelectedCombatUnit().getName() + "destroyed");
            game.getCurrentCivilization().deleteMilitaryUnit(x, y);
        } else if (game.getSelectedNonCombatUnit() != null) {
            int x = game.getSelectedNonCombatUnit().getX();
            int y = game.getSelectedNonCombatUnit().getY();
            game.getCurrentCivilization().addToNotifications("non combat unit " +
                    game.getSelectedNonCombatUnit().getName() + " destroyed");
            game.getCurrentCivilization().deleteNonMilitaryUnit(x, y);
        } else
            return "no selected unit";
        return "unit deleted successfully";
    }

    // TODO complete
    public String unitGarrison() {
        return null;
    }

    public String unitSetupRanged() {
        return null;
    }

    public String unitWake() {
        if (game.getSelectedCombatUnit() == null)
            return "no selected combat unit";
        if (game.getSelectedCombatUnit().getState().equals("ready"))
            return "unit is awake";

        game.getSelectedCombatUnit().setState("ready");

        return "successful";
    }

    public String removeJungle() {
        if (game.getSelectedNonCombatUnit() == null)
            return "no selected non combat unit";
        if (!game.getSelectedNonCombatUnit().getName().equals("worker"))
            return "select worker first";
        if (game.getSelectedNonCombatUnit().isHasDone())
            return "worker has done its work";
        if (!game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].
                getFeature().getKind().equals("jungle"))
            return "tile does not have jungle";

        game.getSelectedNonCombatUnit().setHasDone(true);
        game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].setFeature(null);

        return "jungle removed";
    }

    public String removeRoute() {
        if (game.getSelectedNonCombatUnit() == null)
            return "no selected non combat unit";
        if (!game.getSelectedNonCombatUnit().getName().equals("worker"))
            return "select worker first";
        if (game.getSelectedNonCombatUnit().isHasDone())
            return "worker has done its work";
        if (!game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].isHasRoute())
            return "tile does not have route";

        game.getSelectedNonCombatUnit().setHasDone(true);
        game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].setHasRoute(false);

        return "route removed";
    }

    public String repair() {
        if (game.getSelectedNonCombatUnit() == null)
            return "no selected non combat unit";
        if (!game.getSelectedNonCombatUnit().getName().equals("worker"))
            return "select worker first";
        if (game.getSelectedNonCombatUnit().isHasDone())
            return "worker has done its work";
        if (!game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].isNeedRepair())
            return "tile does not need repair";

        game.getSelectedNonCombatUnit().setHasDone(true);
        game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].setNeedRepair(false);
        return null;
    }


    public String buildImprovement(String improvementName) {
        Improvement improvement = null;
        Tile tile;
        for (ImprovementsEnum improvementsEnum : ImprovementsEnum.values()) {
            if (improvementName.equals(improvementsEnum.getName()))
                improvement = new Improvement(improvementsEnum);
        }
        if (improvement == null)
            return "improvementName is invalid";
        if (game.getSelectedNonCombatUnit() == null)
            return "no selected noncombat unit";
        if (!game.getSelectedNonCombatUnit().getName().equals("worker"))
            return "select a worker";
        if (game.getSelectedNonCombatUnit().isHasDone())
            return "unit has done its work";
        if (!game.getCurrentCivilization().isExistImprovement(improvementName))
            return "don not have access to this improvement";
        int x = game.getSelectedNonCombatUnit().getX();
        int y = game.getSelectedNonCombatUnit().getY();
        if (game.getMap()[x][y].getImprovement() != null)
            return "tile has an improvement";
        if (!canSetImprovement(improvement, game.getMap()[x][y]))
            return "can not build an improvement there";
        tile = game.getMap()[x][y];
        tile.setImprovement(improvement);
        game.getSelectedNonCombatUnit().setHasDone(true);
        return "improvement build successfully";
    }

    public MilitaryUnitsEnum isExistMilitaryUnits(String unitName) {
        for (MilitaryUnitsEnum mu : MilitaryUnitsEnum.values()) {
            if (mu.getName().equalsIgnoreCase(unitName))
                return mu;
        }
        return null;
    }

    public nonCombatUnitsEnum isExistNonCombatUnits(String unitName) {
        for (nonCombatUnitsEnum u : nonCombatUnitsEnum.values()) {
            if (u.getName().equalsIgnoreCase(unitName))
                return u;
        }
        return null;
    }

    private boolean canSetImprovement(Improvement improvement, Tile tile) {
        for (TerrainAndFeature terrain : improvement.getTerrainFeaturesThatCanBeBuilt()) {
            if (terrain.getKind().equals(tile.getTerrain().getKind()))
                return true;
        }
        return false;
    }

    public String combat(MilitaryUnit enemyUnit) {
        MilitaryUnit combatUnit = GameController.getInstance().getGame().getSelectedCombatUnit();
        if (!combatUnit.getState().equals("ready"))
            return "unit is not ready";
        if (combatUnit.isHasDone())
            return "unit has done its work";
        if (!combatUnit.isReadySiege())
            return "siege unit is not set";
        if (combatUnit.getRange() == 0) {
            if ((enemyUnit.getY() - combatUnit.getY()) * (enemyUnit.getY() - combatUnit.getY()) +
                    (enemyUnit.getX() - combatUnit.getX()) * (enemyUnit.getX() - combatUnit.getX()) > 1)
                return "out of range";
        }
        else {
            if ((enemyUnit.getY() - combatUnit.getY()) * (enemyUnit.getY() - combatUnit.getY()) +
                    (enemyUnit.getX() - combatUnit.getX()) * (enemyUnit.getX() - combatUnit.getX())
                    > combatUnit.getRange() * combatUnit.getRange())
                return "position is not in range";
        }

        unitAttackUnit(combatUnit, enemyUnit);
        return "successful";
    }

    private void unitAttackUnit(MilitaryUnit combatUnit, MilitaryUnit enemyUnit) {
        if (!(combatUnit.getName().equals("HorseMan") || combatUnit.getName().equals("Knight") ||
                combatUnit.getName().equals("Cavalry")) || combatUnit.getName().equals("Lancer") ||
                combatUnit.getName().equals("Tank"))
            combatUnit.setHasDone(true);

        int x = enemyUnit.getX();
        int y = enemyUnit.getY();
        int attack = (int) (combatUnit.getCombatStrength() * game.getMap()[combatUnit.getX()][combatUnit.getY()].getCombatChange() + combatUnit.getCombatStrength());
        int rangedAttack = (int) (combatUnit.getRangedCombatStrength() * game.getMap()[combatUnit.getX()][combatUnit.getY()].getCombatChange() + combatUnit.getRangedCombatStrength());
        if ((combatUnit.getName().equals("Spearman") || combatUnit.getName().equals("Pikeman"))&& enemyUnit.getCombatType().equals("Mounted"))
            attack *= 2;
        if (1 == (y - combatUnit.getY()) * (y - combatUnit.getY())
                + (x - combatUnit.getX()) * (x - combatUnit.getX())) {
            if (attack >= enemyUnit.getHp())
                destroyUnit(enemyUnit);
            else
                enemyUnit.setHp(enemyUnit.getHp() - attack);
        } else {
            if (rangedAttack >= enemyUnit.getHp())
                destroyUnit(enemyUnit);
            else
                enemyUnit.setHp(enemyUnit.getHp() - rangedAttack);
        }
    }

    private void destroyUnit(MilitaryUnit enemyUnit) {
        for (Civilization civilization : game.getCivilizations()) {
            for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
                if (militaryUnit.getX() == enemyUnit.getX() && militaryUnit.getY() == enemyUnit.getY()) {
                    civilization.getMilitaryUnits().remove(militaryUnit);
                    civilization.addToNotifications("military unit " + enemyUnit.getName() + " destroyed :(");
                    return;
                }
            }
        }
    }


    public String createRoute() {
        if (game.getSelectedNonCombatUnit() == null)
            return "no selected non combat unit";
        if (!game.getSelectedNonCombatUnit().getName().equals("worker"))
            return "select worker first";
        if (game.getSelectedNonCombatUnit().isHasDone())
            return "worker has done its work";
        if (game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].isHasRoute())
            return "tile have a route already";

        game.getSelectedNonCombatUnit().setHasDone(true);
        game.getMap()[game.getSelectedNonCombatUnit().getX()][game.getSelectedNonCombatUnit().getY()].setHasRoute(true);

        return "successful";
    }

    public String readySiege() {
        MilitaryUnit militaryUnit = game.getSelectedCombatUnit();
        if (militaryUnit == null)
            return "no selected combat unit";
        if (!militaryUnit.getCombatType().equals("Siege"))
            return "unit is not siege";
        if (militaryUnit.isReadySiege())
            return "unit is already set";

        militaryUnit.setHasDone(true);
        militaryUnit.setReadySiege(true);
        return "siege set";
    }
}
