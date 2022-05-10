package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.Civilization;
import models.MilitaryUnit;
import models.Unit;

import java.util.regex.Matcher;

public class UnitController {
    private static UnitController instance = null;

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
        militaryUnit.setMovement(militaryUnit.getMovement() - GameController.getInstance().getGame().getMap()[militaryUnit.getX() - 1][militaryUnit.getY()].getMovementCost());
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        GameController.getInstance().getGame().getMap()[militaryUnit.getX() - 1][militaryUnit.getY()].setMilitaryUnit(militaryUnit);
        militaryUnit.setX(militaryUnit.getX() - 1);
    }

    private void moveLeft(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY() - 1].getMovementCost());
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY() - 1].setMilitaryUnit(militaryUnit);
        militaryUnit.setY(militaryUnit.getY() - 1);
    }

    private void moveDown(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - GameController.getInstance().getGame().getMap()[militaryUnit.getX() + 1][militaryUnit.getY()].getMovementCost());
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        GameController.getInstance().getGame().getMap()[militaryUnit.getX() + 1][militaryUnit.getY()].setMilitaryUnit(militaryUnit);
        militaryUnit.setX(militaryUnit.getX() + 1);
    }

    private void moveRight(MilitaryUnit militaryUnit) {
        militaryUnit.setMovement(militaryUnit.getMovement() - GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY() + 1].getMovementCost());
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY()].setMilitaryUnit(null);
        GameController.getInstance().getGame().getMap()[militaryUnit.getX()][militaryUnit.getY() + 1].setMilitaryUnit(militaryUnit);
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
        unit.setMovement(unit.getMovement() - GameController.getInstance().getGame().getMap()[unit.getX() - 1][unit.getY()].getMovementCost());
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY()].setCivilian(null);
        GameController.getInstance().getGame().getMap()[unit.getX() - 1][unit.getY()].setCivilian(unit);
        unit.setX(unit.getX() - 1);
    }

    private void moveLeft(Unit unit) {
        unit.setMovement(unit.getMovement() - GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY() - 1].getMovementCost());
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY()].setCivilian(null);
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY() - 1].setCivilian(unit);
        unit.setY(unit.getY() - 1);
    }

    private void moveDown(Unit unit) {
        unit.setMovement(unit.getMovement() - GameController.getInstance().getGame().getMap()[unit.getX() + 1][unit.getY()].getMovementCost());
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY()].setCivilian(null);
        GameController.getInstance().getGame().getMap()[unit.getX() + 1][unit.getY()].setCivilian(unit);
        unit.setX(unit.getX() + 1);
    }

    private void moveRight(Unit unit) {
        unit.setMovement(unit.getMovement() - GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY() + 1].getMovementCost());
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY()].setCivilian(null);
        GameController.getInstance().getGame().getMap()[unit.getX()][unit.getY() + 1].setCivilian(unit);
        unit.setY(unit.getY() + 1);
    }


    private boolean movePossible(int x, int y, MilitaryUnit militaryUnit) {
        if (GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                GameController.getInstance().getGame().getMap()[x][y].getMilitaryUnit() != null ||
                militaryUnit.getMovement() < GameController.getInstance().getGame().getMap()[x][y].getMovementCost())
            return false;
        return true;
    }

    private boolean movePossible(int x, int y, Unit unit) {
        if (GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                GameController.getInstance().getGame().getMap()[x][y].getMilitaryUnit() != null ||
                unit.getMovement() < GameController.getInstance().getGame().getMap()[x][y].getMovementCost())
            return false;
        return true;
    }

    private void changePlaceAfterTurn(MilitaryUnit militaryUnit) {
        if (militaryUnit.getxEnd() != -1)
            changePlace(militaryUnit.getxEnd(), militaryUnit.getyEnd(), militaryUnit);
    }

    private void changePlaceAfterTurn(Unit unit) {
        if (unit.getxEnd() != -1)
            changePlace(unit.getxEnd(), unit.getyEnd(), unit);
    }

    public void changePlaceAfterTurnAllUnits() {
        for (Civilization civilization : GameController.getInstance().getGame().getCivilizations()) {
            for (int i = 0; i < civilization.getUnits().size(); i++) {
                changePlaceAfterTurn(civilization.getUnits().get(i));
            }
            for (int i = 0; i < civilization.getMilitaryUnits().size(); i++) {
                changePlaceAfterTurn(civilization.getMilitaryUnits().get(i));
            }
        }
    }

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {
            if (GameController.getInstance().getGame().getSelectedCombatUnit().getState().equals("sleep"))
                return "unit is slept";
            if (GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("ocean"))
                return "can not move to mountain or ocean";

            GameController.getInstance().getGame().getSelectedCombatUnit().setxEnd(x);
            GameController.getInstance().getGame().getSelectedCombatUnit().setyEnd(y);
        } else if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null) {
            if (GameController.getInstance().getGame().getSelectedNonCombatUnit().getState().equals("sleep"))
                return "unit is slept";
            if (GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    GameController.getInstance().getGame().getMap()[x][y].getTerrain().getKind().equals("ocean"))
                return "can not move to mountain or ocean";

            GameController.getInstance().getGame().getSelectedNonCombatUnit().setxEnd(x);
            GameController.getInstance().getGame().getSelectedNonCombatUnit().setyEnd(y);
        } else
            return "no selected unit";

        return "unit moved successfully";
    }

    public String unitSleep() {
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedCombatUnit().setState("sleep");
        } else if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedNonCombatUnit().setState("sleep");
        } else
            return "no selected unit";
        return "unit slept successfully";
    }

    public String unitAlert() {
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedCombatUnit().setState("alert");
        } else if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedNonCombatUnit().setState("alert");
        } else
            return "no selected unit";
        return "unit alerted successfully";
    }

    public String unitHeal() {
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedCombatUnit().setHp((GameController.getInstance().getGame()
                    .getSelectedCombatUnit().getFullHp() - GameController.getInstance().getGame()
                    .getSelectedCombatUnit().getHp()) / 2);
        } else
            return "no selected Combat unit";
        return null;
    }

    public String unitFortify() {
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {

        } else if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null) {
            GameController.getInstance().getGame().getSelectedNonCombatUnit().setState("sleep");
        } else
            return "no selected unit";
        return "unit slept successfully";
    }

    public String deleteUnit() {
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null) {
            int x = GameController.getInstance().getGame().getSelectedCombatUnit().getX();
            int y = GameController.getInstance().getGame().getSelectedCombatUnit().getY();
            GameController.getInstance().getGame().getCurrentCivilization().deleteMilitaryUint(x, y);
        } else if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null) {
            int x = GameController.getInstance().getGame().getSelectedNonCombatUnit().getX();
            int y = GameController.getInstance().getGame().getSelectedNonCombatUnit().getY();
            GameController.getInstance().getGame().getCurrentCivilization().deleteNonMilitaryUint(x, y);
        } else
            return "no selected unit";
        return "unit deleted successfully";
    }

    public String unitGarrison() {
        return null;
    }

    public String unitSetupRanged() {
        return null;
    }

    public String unitWake() {
        return null;
    }

    public String removeJungle() {
        return null;
    }

    public String removeRoute() {
        return null;
    }

    public String repair() {
        return null;
    }

    public String buildRoad() {
        return null;
    }

    public String buildRailroad() {
        return null;
    }

    public String buildFarm() {
        return null;
    }

    public String buildMine() {
        return null;
    }

    public String buildTradingPost() {
        return null;
    }

    public String buildLumberMill() {
        return null;
    }

    public String buildPasture() {
        return null;
    }

    public String buildCamp() {
        return null;
    }

    public String buildPlantation() {
        return null;
    }

    public String buildQuarry() {
        return null;
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
}
