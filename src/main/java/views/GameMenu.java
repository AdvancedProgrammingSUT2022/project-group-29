package views;

import controllers.*;
import enums.GameMenuCommands;
import enums.modelsEnum.TechnologyEnum;
import models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu {
    private static GameMenu instance = null;
    private int xMap = 10, yMap = 10;
    private Scanner scanner;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null)
            instance = new GameMenu();
        return instance;
    }

    public void run(Scanner scanner, ArrayList<User> users) {
        this.scanner = scanner;
        GameController.getInstance().startGame(users);
        printGameStarted(users);
        String command;
        Matcher matcher;
        this.showMap(0, 0, 29, 20);


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
            else if (command.startsWith("technology"))
                technology(command);
            else if (command.trim().equals("end"))
                break;
            else if (command.equals("next turn"))
                System.out.println(GameController.getInstance().cheatTurn(1));
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CHEAT)) != null)
                cheat(matcher);
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CHEAT_TECHNOLOGY)) != null)
                cheatTechnology(matcher);
            else
                err();
            showMapWithoutPosition();
        }

//        MainMenu.getInstance().run(scanner);
    }

    private void cheatTechnology(Matcher matcher) {
        System.out.println(GameController.getInstance().cheatAllTechnology(matcher.group("name")));
    }

    private void cheat(Matcher matcher) {
        if (matcher.group("name").equals("turn"))
            System.out.println(GameController.getInstance().cheatTurn(Integer.parseInt(matcher.group("amount"))));
        else if (matcher.group("name").equals("gold"))
            System.out.println(GameController.getInstance().cheatGold(Integer.parseInt(matcher.group("amount"))));
        else if (matcher.group("name").equals("happiness"))
            System.out.println(GameController.getInstance().cheatHappiness(Integer.parseInt(matcher.group("amount"))));
        else if (matcher.group("name").equals("science"))
            System.out.println(GameController.getInstance().cheatScience(Integer.parseInt(matcher.group("amount"))));
        else if (matcher.group("name").equals("finishTechnology"))
            System.out.println(GameController.getInstance().cheatTechnology(Integer.parseInt(matcher.group("amount"))));
        else
            err();
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
        String whiteColor = "\033[48;5;250m";
        String blueColor = "\033[48;5;20m";
        Tile[][] tiles = GameController.getInstance().getGame().getMap();
        if (xBegin % 2 == 0) xBegin++;
        if (yBegin % 2 == 0) yBegin++;
        for (int i = (xBegin + 1) * 6; i < (xEnd + 1) * 6 + 1; i++) {
            for (int j = (yBegin + 1) * 10; j < 10 * (yEnd + 1) + 2; j++) {

                if (i % 6 == 0) {
                    if (j % 20 == 10)
                        j = printUnits(xEnd, yEnd, i - 6, j, whiteColor);

                    if (j % 20 == 2) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[0])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print(hasRiver + "--------\033[000m");
                        j += 7;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (j % 20 == 1) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[5])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print(hasRiver + "/\033[000m");

                        j = printTerrain(xEnd, yEnd, tiles, i, j, whiteColor);

                    } else if (j % 20 == 10) {
                        // river

                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[1])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "\\" + "\033[000m");
                        // feature

                        String color = "", f = " ";

                        if (!MapController.getInstance().isTerrainVisible(i / 6 - 1, j / 10))
                            color = (i / 6 - 1 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            TerrainAndFeature feature;
                            if ((feature = GameController.getInstance().getGame().getMap()[i / 6 - 1][j / 10].getFeature()) != null)
                                f = feature.getColor();
                        }
                        System.out.print(color + "    " + f + "     \033[000m");
                        j += 10;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 2) {

                    if (j % 20 == 0) {
                        // river

                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[5])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print(hasRiver + "/" + "\033[000m");

                        // name

                        String s = " ", color = "";
                        if (!MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            if (GameController.getInstance().getCivilization(i / 6, j / 10) != null) {
                                s = GameController.getInstance().getCivilization(i / 6, j / 10).getColor() +
                                        GameController.getInstance().getCivilization(i / 6, j / 10).LeaderName() +
                                        "\033[000m";

                            }
                        }
                        System.out.print(color + "    " + (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? s : " "));
                        j += 5;

                    } else if (j % 20 == 11) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10 - 1].getRivers()[1])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 - 1 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "\\" + "\033[000m");
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (j % 20 == 0) {
                        j = printUnits(xEnd, yEnd, i, j, whiteColor);
                    }
                    if (j % 20 == 12) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[0])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";

                        System.out.print(hasRiver + "--------" + "\033[000m");
                        j += 7;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (j % 20 == 0) {
                        //river

                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[4])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "\\" + "\033[000m");

                        // feature
                        String color = "", f = " ";

                        if (!MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            TerrainAndFeature feature;
                            if ((feature = GameController.getInstance().getGame().getMap()[i / 6][j / 10].getFeature()) != null)
                                f = feature.getColor();
                        }
                        if (j / 10 < (yEnd + 1))
                            System.out.print(color + "    " + f + "     \033[000m");
                        j += 10;
                    } else if (j % 20 == 11) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10 - 1].getRivers()[2])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 - 1 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "/" + "\033[000m");


                        j = printTerrain(xEnd, yEnd, tiles, i, j, whiteColor);
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 5) {

                    if (j % 20 == 1) {
                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[4])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "\\" + "\033[000m");
                    } else if (j % 20 == 10) {
                        // river

                        String hasRiver = "";
                        if ((i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) && tiles[i / 6][j / 10].getRivers()[2])
                            hasRiver = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? blueColor : "";
                        System.out.print("\033[000m" + hasRiver + "/" + "\033[000m");

                        // name
                        String s = " ", color = "";
                        if (!MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            if (GameController.getInstance().getCivilization(i / 6, j / 10) != null) {
                                s = GameController.getInstance().getCivilization(i / 6, j / 10).getColor() +
                                        GameController.getInstance().getCivilization(i / 6, j / 10).LeaderName() +
                                        "\033[000m";

                            }
                        }
                        System.out.print(color + "    " + (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? s : " "));
                        j += 5;
                    } else
                        System.out.print(" ");
                }
            }

            System.out.println();
        }

        showDemographics();
    }

    private int printTerrain(int xEnd, int yEnd, Tile[][] tiles, int i, int j, String whiteColor) {
        String color;
        if (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) && MapController.getInstance().isTerrainVisible(i / 6, j / 10))
            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
        else
            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? whiteColor : "");
        if (j / 10 < (yEnd + 1))
            System.out.print(color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
        j += 7;
        return j;
    }

    private int printUnits(int xEnd, int yEnd, int i, int j, String whiteColor) {
        String m = "  ", color = "", u = "  ";
        if (!MapController.getInstance().isTerrainVisible(i / 6, j / 10))
            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
        else {
            String mColor = "";
            if (GameController.getInstance().getGame().getMap()[i / 6][j / 10].getMilitaryUnit() != null) {
                for (Civilization civilization : GameController.getInstance().getGame().getCivilizations()) {
                    for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
                        if (militaryUnit.getX() == i / 6 && militaryUnit.getY() == j / 10) {
                            mColor = civilization.getColor();
                            break;
                        }
                    }
                }
                m = mColor +
                        GameController.getInstance().getGame().getMap()[i / 6][j / 10].getMilitaryUnit().
                                getName().substring(0, 2) + "\033[000m";
            }

            String uColor = "";
            if (GameController.getInstance().getGame().getMap()[i / 6][j / 10].getCivilian() != null) {
                for (Civilization civilization : GameController.getInstance().getGame().getCivilizations()) {
                    for (Unit unit : civilization.getUnits()) {
                        if (unit.getX() == i / 6 && unit.getY() == j / 10) {
                            uColor = civilization.getColor();
                            break;
                        }
                    }
                }
                u = uColor + GameController.getInstance().getGame().getMap()[i / 6][j / 10].getCivilian().
                        getName().substring(0, 2) + "\033[000m";
            }
        }
        if (j / 10 < (yEnd + 1))
            System.out.print(color + " " + m + "     " + u + "  " + "\033[000m");
        j += 12;
        return j;
    }

    public void showInfo(String command) {
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
        else
            err();
    }

    private void showEconomic() {

    }

    private void showMilitary() {
        showUnits();
    }

    private void showNotifications() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        for (String notification : civilization.getNotifications())
            System.out.println(notification);
    }

    private void showDemographics() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("population: " + civilization.calculatePopulation() +
                "\ngold: " + civilization.getGold() +
                "\nnumber of combat units: " + civilization.getMilitaryUnits().size() +
                "\nhappiness: " + civilization.getHappiness());
        int population = 1, numberOfCombatUnits = 1, gold = 1;
        ArrayList<Civilization> arrayList = GameController.getInstance().getGame().getCivilizations();
        for (Civilization value : arrayList) {
            if (value.getGold() > civilization.getGold())
                gold++;
            if (value.calculatePopulation() > civilization.calculatePopulation())
                population++;
            if (value.getMilitaryUnits().size() > civilization.getMilitaryUnits().size())
                numberOfCombatUnits++;
        }

        System.out.println("rank in gold: " + gold);
        System.out.println("rank in population: " + population);
        System.out.println("rank in number of combat units: " + numberOfCombatUnits);
    }

    private void showCities() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("capital: " + (civilization.getCapital() != null ? civilization.getCapital().getName() : " no capital"));
        for (City city : civilization.getCities()) {
            System.out.println(city.getName());
        }
    }

    private void showUnits() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("all units:");
        for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
            System.out.println(militaryUnit.toString());
        }
        for (Unit unit : civilization.getUnits()) {
            System.out.println(unit.toString());
        }

        System.out.println("choose a unit or exit");
        String string = this.scanner.nextLine();

        if (string.equals("exit"))
            return;
        if (!Pattern.compile("\\d+").matcher(string).matches()) {
            System.out.println("invalid input");
            return;
        }

        if (Integer.parseInt(string) - 1 < civilization.getMilitaryUnits().size())
            showUnit(civilization.getMilitaryUnits().get(Integer.parseInt(string) - 1));
        else if (Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1 < civilization.getUnits().size())
            showUnit(civilization.getUnits().get(Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1));
        else
            System.out.println("number out of range!");
    }

    private void showUnit(Unit unit) {
        System.out.println("name: " + unit.getName() + "\nx , y: " + unit.getX() + " , " + unit.getY() +
                "\nmovement: " + unit.getMovement());
    }

    private void showUnit(MilitaryUnit militaryUnit) {
        System.out.println("name: " + militaryUnit.getName() + "\nx , y: " + militaryUnit.getX() + " , " +
                militaryUnit.getY() + "\ncombat strength: " + militaryUnit.getCombatStrength() +
                "\nrange: " + militaryUnit.getRange() + "\nrange strength: " + militaryUnit.getRangedCombatStrength()
                + "\nmovement: " + militaryUnit.getMovement() + "\nhp: " + militaryUnit.getHp() +
                "\nstate: " + militaryUnit.getState());
    }

    private void showResearch() {
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        System.out.println("discovered technologies: " + (civilization.getTechnologies().size() == 0 ? "nothing" : ""));
        for (Technology technology : civilization.getTechnologies()) {
            System.out.println(technology.getName());
        }

        System.out.println("detectable technologies: ");
        if (civilization.getCurrentTechnology() != null) {
            for (TechnologyEnum technology : Technology.getAllTechnologies()) {
                for (Technology neededTechnology : technology.getNeededTechnologies()) {
                    if (neededTechnology.getName().equals(civilization.getCurrentTechnology().getName()))
                        System.out.println(technology.getName());
                }
            }
        } else
            System.out.println("there is no current technology");
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
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.UNIT_FOUND_CITY)) != null)
            System.out.println(GameController.getInstance().foundCity(matcher));
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
        else if (command.equals("unit delete"))
            System.out.println(UnitController.getInstance().deleteUnit());
        else if (command.equals("unit create route"))
            System.out.println(UnitController.getInstance().createRoute());
        else if (command.equals("unit ready siege"))
            System.out.println(UnitController.getInstance().readySiege());
        else
            err();
    }

    private void unitBuild(String command) {
        Matcher matcher;
        String improvementName;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.UNIT_BUILD)) != null) {
            improvementName = matcher.group("improvementName");
            System.out.println(UnitController.getInstance().buildImprovement(improvementName));
        }
        else
            err();
    }

    private void map(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP1)) != null)
            showMapByPosition(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP2)) != null)
            showMapByCityName(matcher);
        else if (GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP3) != null)
            showMapWithoutPosition();
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.MOVE_MAP)) != null)
            moveMap(matcher);
        else
            err();
    }

    private void showMapWithoutPosition() {
        showMap(xMap - 5, yMap - 5, xMap + 5, yMap + 5);
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
        else
            err();
    }

    private void showMapByCityName(Matcher matcher) {
        City city;
        if ((city = MapController.getInstance().getCity(matcher)) == null)
            System.out.println("no city with this name");
        else {
            xMap = city.getX();
            yMap = city.getY();
            showMapWithoutPosition();
        }
    }

    private void showMapByPosition(Matcher matcher) {
        xMap = Integer.parseInt(matcher.group("x"));
        yMap = Integer.parseInt(matcher.group("y"));
        showMapWithoutPosition();
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
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_INFORMATION)) != null)
            cityShowInformation();
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_ATTACK)) != null)
            cityAttack(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.LOCK_CITIZEN_TO_TILE)) != null)
            cityLockingCitizenToTile(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.REMOVE_CITIZEN_FROM_TILE)) != null)
            cityRemoveCitizenFromTile(matcher);
        else if (GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SCREEN_MENU) != null)
            cityScreenMenu();
        else if (GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_RESOURCES_OUTPUT) != null)
            cityResourcesOutput();
        else if (GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_UNEMPLOYED_CITIZEN_SECTION) != null)
            unemployedCitizenSection();
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_BUY_TILE)) != null)
            cityBuyTile(matcher);
        else
            err();
    }

    private void cityBuyTile(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("xPoint"));
        int y = Integer.parseInt(matcher.group("yPoint"));
        System.out.println(CityController.getInstance().cityBuyTile(x, y));
    }

    private void unemployedCitizenSection() {
        System.out.println(CityController.getInstance().unemployedCitizenSection());
    }

    private void cityResourcesOutput() {
        System.out.println(CityController.getInstance().cityResourcesOutput());
    }

    private void cityScreenMenu() {
        System.out.println(CityController.getInstance().cityScreenMenu());
    }

    private void cityRemoveCitizenFromTile(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("xPoint"));
        int y = Integer.parseInt(matcher.group("yPoint"));
        System.out.println(CityController.getInstance().removingCitizenFromWork(x, y));

    }

    private void cityLockingCitizenToTile(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("xPoint"));
        int y = Integer.parseInt(matcher.group("yPoint"));
        System.out.println(CityController.getInstance().lockingCitizenToTile(x, y));

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

    private void technology(String command) {
        Matcher matcher;
        if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.TECHNOLOGY_STUDY)) != null)
            technologyStudy(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.TECHNOLOGY_CHANGE)) != null)
            technologyChange(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.TECHNOLOGY_MENU)) != null)
            System.out.println(GameController.getInstance().technologyMenu());
        else
            err();
    }

    private void technologyChange(Matcher matcher) {
        String technologyName = matcher.group("technologyName");
        System.out.println(GameController.getInstance().technologyChange(technologyName));
    }


    private void technologyStudy(Matcher matcher) {
        String technologyName = matcher.group("technologyName");
        System.out.println(GameController.getInstance().technologyStudy(technologyName));
    }

    private void err() {
        System.out.println("invalid command");
    }
}
