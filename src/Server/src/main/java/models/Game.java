package models;

import app.Main;
import controllers.GameController;
import controllers.UnitController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {
    private int year = 1000;
    private ArrayList<Civilization> civilizations;
    private Civilization currentCivilization;
    private Tile[][] map;
    private int turn;
    private MilitaryUnit selectedCombatUnit = null;
    private Unit selectedNonCombatUnit = null;
    private City selectedCity = null;
    private final HashMap<String, String> war = new HashMap<>();
    private final HashMap<String, String> friendship = new HashMap<>();
    private ArrayList<Tile> ruins;

    public Game(ArrayList<Civilization> civilizations, Tile[][] map) {
        this.civilizations = civilizations;
        this.map = map;
        this.turn = 0;
        this.ruins = new ArrayList<>();
    }

    public void nextTurn() {
        year += 50;
        GameController.getInstance().maintenanceGold();
        GameController.getInstance().updateCanBuild();
        UnitController.getInstance().changePlaceAfterTurnAllUnits();
        UnitController.getInstance().healAfterTurn();
        currentCivilization.increaseScience(3 + currentCivilization.calculatePopulation());
        GameController.getInstance().getResourceAndGoldTurn();
        GameController.getInstance().decreaseTechnologyTurn();
        GameController.getInstance().food();
        GameController.getInstance().makeFood();

        currentCivilization = civilizations.get(++turn % civilizations.size());

        checkWinner();
    }

    private void checkWinner() {
        if (currentCivilization.getCities().size() == 0) {
            int size = 0;
            for (Unit unit : currentCivilization.getUnits()) {
                if (unit.getName().equals("settler"))
                    size++;
            }
            if (size == 0) {
                loose();
            }
        } else if (year == 2050) {
            Civilization winner = civilizations.get(0);
            for (Civilization civilization : civilizations)
                civilization.getLeader().setScore(civilization.getLeader().getScore() + civilization.getHappiness() / 20);
            for (Civilization civilization : civilizations)
                if (civilization.getHappiness() > winner.getHappiness())
                    winner = civilization;
            GameController.getInstance().endGame(winner.getLeader().getNickname());
        } else if (civilizations.size() == 1) {
            GameController.getInstance().endGame(civilizations.get(0).getLeader().getNickname());
        }
    }

    private void loose() {
        //Main.showPopupJustText("you lost!");
        currentCivilization.getUnits().clear();
        currentCivilization.getMilitaryUnits().clear();
        currentCivilization.getLeader().setScore(currentCivilization.getLeader().getScore() - 1);
        civilizations.remove(currentCivilization);
        currentCivilization = civilizations.get(turn % civilizations.size());
        currentCivilization.getLeader().setLastVisit(System.currentTimeMillis());
    }


    public ArrayList<Civilization> getCivilizations() {
        return civilizations;
    }

    public Tile[][] getMap() {
        return map;
    }

    public Civilization getCurrentCivilization() {
        return currentCivilization;
    }

    private void giveColor() {
        for (int i = 0; i < civilizations.size(); i++) {
            civilizations.get(i).setColor("\033[48;5;" + (10 * i) + "m");
        }
    }

    public void setSelectedCombatUnit(MilitaryUnit selectedCombatUnit) {
        this.selectedCombatUnit = selectedCombatUnit;
    }

    public void setSelectedNonCombatUnit(Unit selectedNonCombatUnit) {
        this.selectedNonCombatUnit = selectedNonCombatUnit;
    }

    public void setSelectedCity(City selectedCity) {
        this.selectedCity = selectedCity;
    }

    public MilitaryUnit getSelectedCombatUnit() {
        return selectedCombatUnit;
    }

    public Unit getSelectedNonCombatUnit() {
        return selectedNonCombatUnit;
    }

    public City getSelectedCity() {
        return selectedCity;
    }

    public int getTurn() {
        return turn;
    }

    public void setCivilizations(ArrayList<Civilization> civilizations) {
        this.civilizations = civilizations;
        this.currentCivilization = civilizations.get(0);
        giveColor();
    }

    public HashMap<String, String> getWar() {
        return war;
    }

    public HashMap<String, String> getFriendship() {
        return friendship;
    }

    public ArrayList<Tile> getRuins() {
        return ruins;
    }

    public void addRuins() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(map.length);
            int rand2 = random.nextInt(map[0].length);
            for (Tile ruin : ruins) {
                while (ruin.getX() == rand && ruin.getY() == rand2) {
                    rand = random.nextInt(map.length);
                    rand2 = random.nextInt(map[0].length);
                }
            }
            ruins.add(map[rand][rand2]);
        }
    }

    public Civilization getCivilizationByName(String name) {
        for (Civilization c : civilizations) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }


}
