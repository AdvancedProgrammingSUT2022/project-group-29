package controllers;

import enums.modelsEnum.MilitaryUnitsEnum;
import enums.modelsEnum.nonCombatUnitsEnum;
import models.Civilization;
import models.Game;
import models.MilitaryUnit;
import models.Unit;

import java.util.regex.Matcher;

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
        if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                game.getMap()[x][y].getMilitaryUnit() != null ||
                militaryUnit.getMovement() < game.getMap()[x][y].getMovementCost())
            return false;
        return true;
    }

    private boolean movePossible(int x, int y, Unit unit) {
        if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                game.getMap()[x][y].getTerrain().getKind().equals("ocean") ||
                game.getMap()[x][y].getMilitaryUnit() != null ||
                unit.getMovement() < game.getMap()[x][y].getMovementCost() ||
                !GameController.getInstance().getCivilization(x, y).LeaderName().equals(
                        game.getCurrentCivilization().LeaderName()))
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

    public String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (game.getSelectedCombatUnit() != null) {
            if (!game.getSelectedCombatUnit().getState().equals("ready"))
                return "unit is slept";
            if (game.getSelectedCombatUnit().isHasDone())
                return "unit has done its work";
            if (game.getMap()[x][y].getTerrain().getKind().equals("mountain") ||
                    game.getMap()[x][y].getTerrain().getKind().equals("ocean"))
                return "can not move to mountain or ocean";
            if (!GameController.getInstance().getCivilization(x, y).LeaderName().equals(
                    game.getCurrentCivilization().LeaderName()))
                return "can not move to enemies tile";

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

    // TODO .. complete
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

    public String combat(MilitaryUnit militaryUnit, MilitaryUnit selectedCombatUnit) {
        return null;
    }
}
