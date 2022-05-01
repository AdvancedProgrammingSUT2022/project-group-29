package views;

import controllers.GameController;
import controllers.MapController;
import enums.TechnologyEnum;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu {
    private static GameMenu instance = null;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null)
            instance = new GameMenu();
        return instance;
    }


    public void run(Scanner scanner, ArrayList<User> users) {
        printGameStarted(users);
        GameController.getInstance().startGame(users);
        int a = 0;
        String command;
        Matcher matcher;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("info")) {
                showInfo(command);
            }
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

    private void showMap() {
        Tile[][] tiles = GameController.getInstance().getGame().getMap();
        int x = GameController.getInstance().getLENGTH();
        int y = GameController.getInstance().getWIDTH();
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
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;250m" : "");
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
                            color = (i / 6 < x && j / 10 < y ? "\033[48;5;250m" : "");
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
}
