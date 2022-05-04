package views;

import controllers.*;
import enums.GameMenuCommands;
import enums.modelsEnum.TechnologyEnum;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private static GameMenu instance = null;
    private int xMap, yMap;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null)
            instance = new GameMenu();
        return instance;
    }

    public void run(Scanner scanner, ArrayList<User> users) {
        GameController.getInstance().startGame(users);
        printGameStarted(users);
        int a = 0;
        String command;
        Matcher matcher;

        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("info"))
                showInfo(command);
            else if (command.startsWith("select"))
                select(command);
            else if (command.startsWith("unit"))
                unit(command);
            else if (command.startsWith("map"))
                map(command);
            else if (command.startsWith("city"))
                city(command);
            else if (command.trim().equals("end"))
                break;
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.INCREASE_TURN)) != null)
                GameController.getInstance().cheatTurn(Integer.parseInt(matcher.group("amount")));
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.INCREASE_GOLD)) != null)
                GameController.getInstance().cheatGold(Integer.parseInt(matcher.group("amount")));
            else
                err();
        }

    }

    private void printGameStarted(ArrayList<User> users) {
        StringBuilder stringBuilder = new StringBuilder("A new game started between ");
        for (User user : users) {
            stringBuilder.append(user.getUsername()).append(", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder);
    }

    private void showMap(int xBegin, int yBegin, int xEnd, int yEnd) {
        Tile[][] tiles = GameController.getInstance().getGame().getMap();

        int x = GameController.getInstance().getWIDTH();
        int y = GameController.getInstance().getLENGTH();
        for (int i = 0; i < x * 6 + 2; i++) {
            for (int j = 0; j < 10 * y + 1; j++) {

                if (i % 6 == 0) {

                    if (j % 20 >= 2 && j % 20 < 10)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (j % 20 == 1) {
                        String color;
                        if (i / 6 < x && MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < x && j / 10 < y ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
                        else
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;0m" : "");
                        System.out.print("/" + color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
                        j += 7;
                    } else if (j % 20 == 10)
                        System.out.print("\033[000m\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 2) {

                    if (j % 20 == 0) {
                        String s = " ";
                        if (GameController.getInstance().getCivilization(i / 6, j / 10) != null) {
                            s = GameController.getInstance().getCivilization(i / 6, j / 10).getColor() +
                                    GameController.getInstance().getCivilization(i / 6, j / 10).LeaderName() +
                                    "\033[000m";

                        }
                        System.out.print("/    " + (i / 6 < x && j / 10 < y ? s : " "));
                        j += 5;


                    } else if (j % 20 == 11)

                        System.out.print("\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (j % 20 >= 12)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (j % 20 == 0)
                        System.out.print("\u001B[0m\\");
                    else if (j % 20 == 11) {
                        String color;
                        if (i / 6 < x && MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < x && j / 10 < y ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
                        else
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;0m" : "");
                        System.out.print("/" + color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
                        j += 7;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 5) {

                    if (j % 20 == 1)
                        System.out.print("\\");
                    else if (j % 20 == 10)
                        System.out.print("/");
                    else
                        System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private void cheatTurn(int turn) {
        GameController.getInstance().cheatTurn(turn);
    }

    private void cheatGold(int turn) {
        GameController.getInstance().cheatGold(turn);
    }

    private void showInfo(String command) {
        String newCommand = command.split(" ")[1];
        if (newCommand.equals("research"))
            showResearch();
        else if (newCommand.equals("units"))
            showUnits();
        else if (newCommand.equals("cities"))
            showCities();
        else if (newCommand.equals("demographics"))
            showDemographics();
        else if (newCommand.equals("notifications"))
            showNotifications();
        else if (newCommand.equals("military"))
            showMilitary();
        else if (newCommand.equals("economic"))
            showEconomic();
    }

    private void showEconomic() {
    }

    private void showMilitary() {
    }

    private void showNotifications() {
    }

    private void showDemographics() {

    }

    private void showCities() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("capital: " + civilization.getCapital().getName());
        for (City city : civilization.getCities()) {
            System.out.println(city.getName());
        }
    }

    private void showUnits() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("all combat units:");
        for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
            System.out.println(militaryUnit.toString());
        }
    }

    private void showResearch() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("discovered technologies: ");
        for (Technology technology : civilization.getTechnologies()) {
            System.out.println(technology.getName());
        }

        System.out.println("detectable technologies: ");
        for (TechnologyEnum technology : Technology.getAllTechnologies()) {
            boolean flag1 = true;
            for (Technology neededTechnology : technology.getNeededTechnologies()) {
                boolean flag = false;
                for (Technology civilizationTechnology : civilization.getTechnologies()) {
                    if (neededTechnology.equals(civilizationTechnology))
                        flag = true;
                }
                if (!flag)
                    flag1 = false;
            }
            if (flag1)
                System.out.println(technology.getName());
        }
    }

    private void select(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_COMBAT)) != null)
            System.out.println(GameMenuController.getInstance().selectCombatUnit(matcher));
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_NON_COMBAT)) != null)
            System.out.println(GameMenuController.getInstance().selectNonCombatUnit(matcher));
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_CITY1)) != null)
            System.out.println(GameMenuController.getInstance().selectCityByName(matcher));
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SELECT_CITY2)) != null)
            System.out.println(GameMenuController.getInstance().selectCityByPosition(matcher));
        else
            err();
    }

    private void unit(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.UNIT_MOVE)) != null)
            System.out.println(UnitController.getInstance().moveUnit(matcher));
        else if (command.equals("unit sleep"))
            System.out.println(UnitController.getInstance().unitSleep());
        else if (command.equals("unit alert"))
            System.out.println(UnitController.getInstance().unitAlert());
        else if (command.equals("unit fortify heal"))
            System.out.println(UnitController.getInstance().unitHeal());
        else if (command.equals("unit fortify"))
            System.out.println(UnitController.getInstance().unitFortify());
        else if (command.equals("unit garrison"))
            System.out.println(UnitController.getInstance().unitGarrison());
        else if (command.equals("unit setup ranged"))
            System.out.println(UnitController.getInstance().unitSetupRanged());
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.ATTACK)) != null)
            System.out.println(GameController.getInstance().combat(matcher));
        else if (command.equals("unit found city"))
            System.out.println(GameController.getInstance().foundCity());
        else if (command.equals("unit cancel mission"))
            System.out.println(GameController.getInstance().cancelMission());
        else if (command.equals("unit wake"))
            System.out.println(UnitController.getInstance().unitWake());
        else if (command.equals("unit build"))
            unitBuild(command);
        else if (command.equals("unit remove jungle"))
            System.out.println(UnitController.getInstance().removeJungle());
        else if (command.equals("unit remove route"))
            System.out.println(UnitController.getInstance().removeRoute());
        else if (command.equals("unit repair"))
            System.out.println(UnitController.getInstance().repair());
        else
            err();
    }

    private void unitBuild(String command) {
        String improvement = command.split(" ")[2];
        if (improvement.equals("road"))
            System.out.println(UnitController.getInstance().buildRoad());
        else if (improvement.equals("railroad"))
            System.out.println(UnitController.getInstance().buildRailroad());
        else if (improvement.equals("farm"))
            System.out.println(UnitController.getInstance().buildFarm());
        else if (improvement.equals("mine"))
            System.out.println(UnitController.getInstance().buildMine());
        else if (improvement.equals("trading post"))
            System.out.println(UnitController.getInstance().buildTradingPost());
        else if (improvement.equals("lumber mill"))
            System.out.println(UnitController.getInstance().buildLumberMill());
        else if (improvement.equals("pasture"))
            System.out.println(UnitController.getInstance().buildPasture());
        else if (improvement.equals("camp"))
            System.out.println(UnitController.getInstance().buildCamp());
        else if (improvement.equals("plantation"))
            System.out.println(UnitController.getInstance().buildPlantation());
        else if (improvement.equals("quarry"))
            System.out.println(UnitController.getInstance().buildQuarry());
        else
            err();
    }

    private void map(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP1)) != null)
            showMapByPosition(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP2)) != null)
            showMapByCityName(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_MAP)) != null)
            moveMap(matcher);
    }

    private void moveMap(Matcher matcher) {
        if (matcher.group("direction").equals("right"))
            yMap += Integer.parseInt(matcher.group("c"));
        else if (matcher.group("direction").equals("left"))
            yMap -= Integer.parseInt(matcher.group("c"));
        else if (matcher.group("direction").equals("down"))
            xMap += Integer.parseInt(matcher.group("c"));
        else if (matcher.group("direction").equals("up"))
            xMap -= Integer.parseInt(matcher.group("c"));
    }

    private void showMapByCityName(Matcher matcher) {
    }

    private void showMapByPosition(Matcher matcher) {
        xMap = Integer.parseInt(matcher.group("x"));
        yMap = Integer.parseInt(matcher.group("y"));
        showMap(xMap - 5, yMap - 5, xMap + 5, yMap + 5);
    }

    private void city(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_CREATE_UNIT)) != null)
            createUnit(matcher);
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_TILE)) != null)
            cityShowTile();
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_CIVILIZATION)) != null)
            cityShowCivilization();
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_RESOURCES)) != null)
            cityShowResources();
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_UNIT)) != null)
            cityShowUnit();
        else if((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_INFORMATION)) != null)
            cityShowInformation();
        else if((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_ATTACK)) != null)
            cityAttack(matcher);
        else
            err();
    }

    private void cityAttack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("xPoint"));
        int y = Integer.parseInt(matcher.group("yPoint"));
        System.out.println(CityController.getInstance().cityAttack(x, y));
    }

    private void cityShowInformation() {
        System.out.println(CityController.getInstance().cityShowInformation());
    }

    private void cityShowUnit() {
        System.out.println(CityController.getInstance().cityShowUnit());

    }

    private void cityShowResources() {
        System.out.println(CityController.getInstance().cityShowResources());

    }

    private void cityShowCivilization() {
        System.out.println(CityController.getInstance().cityShowCivilization());
    }

    private void cityShowTile() {
        System.out.println(CityController.getInstance().cityShowTilePosition());
    }

    private void createUnit(Matcher matcher) {
        String unitName = matcher.group("unitName");
        System.out.println(CityController.getInstance().createUnit(unitName));
    }

    private void err() {
        System.out.println("invalid command");
    }
}
