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
    private int xMap = 10, yMap = 10;

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
            else if (command.equals("next turn"))
                System.out.println(GameController.getInstance().cheatTurn(1));
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.INCREASE_TURN)) != null)
                System.out.println(GameController.getInstance().cheatTurn(Integer.parseInt(matcher.group("amount"))));
            else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.INCREASE_GOLD)) != null)
                System.out.println(GameController.getInstance().cheatGold(Integer.parseInt(matcher.group("amount"))));
            else
                err();
        }

        MainMenu.getInstance().run(scanner);
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
        Tile[][] tiles = GameController.getInstance().getGame().getMap();
        if (xBegin % 2 == 0) xBegin++;
        if (yBegin % 2 == 0) yBegin++;
        System.out.println();
        System.out.println(xBegin);
        System.out.println();
        for (int i = (xBegin + 1) * 6; i < (xEnd + 1) * 6 + 1; i++) {
            for (int j = (yBegin + 1) * 10; j < 10 * (yEnd + 1) + 2; j++) {

                if (i % 6 == 0) {

                    if (j % 20 == 10) {
                        j = printUnits(xEnd, yEnd, i - 6, j, whiteColor);
                    }
                    if (j % 20 >= 2 && j % 20 < 10)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 1) {

                    if (j % 20 == 1) {
                        j = printTerrain(xEnd, yEnd, tiles, i, j, whiteColor);
                    } else if (j % 20 == 10) {
                        String color = "", f = " ";

                        if (!MapController.getInstance().isTerrainVisible(i / 6 - 1, j / 10))
                            color = (i / 6 - 1< (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            TerrainAndFeature feature;
                            if ((feature = GameController.getInstance().getGame().getMap()[i / 6 - 1][j / 10].getFeature()) != null)
                                f = feature.getColor();
                        }
                        System.out.print("\u001B[0m\\" + color + "    " + f + "     \033[000m");
                        j += 10;
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 2) {

                    if (j % 20 == 0) {
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
                        System.out.print("/" + color + "    " + (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? s : " "));
                        j += 5;

                    } else if (j % 20 == 11)
                        System.out.print("\033[000m\\");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 3) {

                    if (j % 20 == 0) {
                        j = printUnits(xEnd, yEnd, i, j, whiteColor);
                    }
                    if (j % 20 >= 12)
                        System.out.print("-");
                    else
                        System.out.print(" ");

                } else if (i % 6 == 4) {
                    if (j % 20 == 0) {
                        String color = "", f = " ";

                        if (!MapController.getInstance().isTerrainVisible(i / 6, j / 10))
                            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1)) ? whiteColor : "";
                        else {
                            TerrainAndFeature feature;
                            if ((feature = GameController.getInstance().getGame().getMap()[i / 6][j / 10].getFeature()) != null)
                                f = feature.getColor();
                        }
                        System.out.print("\033[00m\\");
                        if (j / 10 < (yEnd + 1))
                            System.out.print(color + "    " + f + "     \033[000m");
                        j += 10;
                    } else if (j % 20 == 11) {
                        j = printTerrain(xEnd, yEnd, tiles, i, j, whiteColor);
                    } else
                        System.out.print(" ");

                } else if (i % 6 == 5) {

                    if (j % 20 == 1)
                        System.out.print("\033[000m\\");
                    else if (j % 20 == 10) {
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
                        System.out.print("/" + color + "    " + (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? s : " "));
                        j += 5;
                    } else
                        System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private int printStuff(int xEnd, int yEnd, Tile[][] tiles, int i, int j, String whiteColor) {
        StringBuilder color = new StringBuilder("");
        if (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) && !MapController.getInstance().isTerrainVisible(i / 6, j / 10))
            System.out.print("          ");
        else {
            String terrainColor = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
            String m = "  ", u = "  ", s = " ", f = " ";
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

            if (GameController.getInstance().getCivilization(i / 6, j / 10) != null) {
                s = GameController.getInstance().getCivilization(i / 6, j / 10).getColor() +
                        GameController.getInstance().getCivilization(i / 6, j / 10).LeaderName() +
                        "\033[000m";

            }

            TerrainAndFeature feature;
            if ((feature = GameController.getInstance().getGame().getMap()[i / 6][j / 10].getFeature()) != null)
                f = feature.getColor();
            color.append(m).append(terrainColor).append(" \033[000m").append(u).append(terrainColor).
                    append(" \033[000m").append(s).append(terrainColor).append(" ").append(f).append(" \033[000m");

            System.out.print(color);
        }
        return j + 10;
    }

    private int printTerrain(int xEnd, int yEnd, Tile[][] tiles, int i, int j, String whiteColor) {
        String color;
        if (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) && MapController.getInstance().isTerrainVisible(i / 6, j / 10))
            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? tiles[i / 6][j / 10].getTerrain().getColor() : "");
        else
            color = (i / 6 < (xEnd + 1) && j / 10 < (yEnd + 1) ? whiteColor : "");
        if (j / 10 < (yEnd + 1))
            System.out.print("/" + color + "  " + (i / 6 > 9 ? i / 6 : "0" + i / 6) + "," + (j / 10 > 9 ? j / 10 : "0" + j / 10));
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
        else
            err();
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
        System.out.println("capital: " + (civilization.getCapital() != null ? civilization.getCapital().getName() : " no capital"));
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
        for (Unit unit : civilization.getUnits()) {
            System.out.println(unit.toString());
        }
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
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.SHOW_MAP3)) != null)
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
        else if ((GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SHOW_INFORMATION)) != null)
            cityShowInformation();
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_ATTACK)) != null)
            cityAttack(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.LOCK_CITIZEN_TO_TILE)) != null)
            cityLockingCitizenToTile(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.REMOVE_CITIZEN_FROM_TILE)) != null)
            cityRemoveCitizenFromTile(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_SCREEN_MENU)) != null)
            cityScreenMenu(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_RESOURCES_OUTPUT)) != null)
            cityResourcesOutput(matcher);
        else if ((matcher = GameMenuCommands.getMatcher(command, GameMenuCommands.CITY_UNEMPLOYED_CITIZEN_SECTION)) != null)
            unemployedCitizenSection(matcher);
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

    private void unemployedCitizenSection(Matcher matcher) {
        System.out.println(CityController.getInstance().unemployedCitizenSection());
    }

    private void cityResourcesOutput(Matcher matcher) {
        System.out.println(CityController.getInstance().cityResourcesOutput());
    }

    private void cityScreenMenu(Matcher matcher) {
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

    private void err() {
        System.out.println("invalid command");
    }
}
