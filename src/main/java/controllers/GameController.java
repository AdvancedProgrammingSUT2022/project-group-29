package controllers;

import app.Main;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import enums.modelsEnum.TechnologyEnum;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.*;
import views.CreateGameMenu;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

public class GameController {
    private static GameController instance = null;
    private final int LENGTH = 45;
    private final int WIDTH = 30;
    private Game game;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    public void addUserToGame(String username, ArrayList<String> players, VBox vbox, Label message, int numberOfPlayers) {
        if (numberOfPlayers == players.size()) {
            message.setText("Error | You Had Added All Players");
            return;
        }

        if (isExistUsername(username) == null) {
            message.setText("Error | Username Is Not Exist");
            return;
        }

        for (String player : players) {
            if (player.equals(username)) {
                message.setText("Error | User Had Been Selected");
                return;
            }
        }

        Label label = new Label(username);
        vbox.getChildren().add(label);
        players.add(username);
        message.setText("Success | User Added");
    }

    public void removeUserFromGame(String username, ArrayList<String> players, VBox vbox, Label message) {
        if (username.equals(User.getLoggedInUser().getUsername())) {
            message.setText("Error | You Can't Remove Yourself");
            return;
        }

        for (String player : players) {
            if (player.equals(username)) {
                message.setText("Success | User Removed");
                players.remove(player);
                removeLabel(vbox, username);
                return;
            }
        }
        message.setText("Error | You Haven't Add This User Yet");
    }

    public void checkNumberOfUsers(ArrayList<String> players, int numberOfPlayers, int number, Label message, ChoiceBox<String> playersNumber) {
        if (players.size() > number) {
            message.setText("Error | You Should Remove Some Users");
            playersNumber.setValue(numberOfPlayers + " Players");
        }
    }

    private void removeLabel(VBox vbox, String username) {
        for (Node child : vbox.getChildren()) {
            Label label = (Label) child;

            if (label.getText().equals(username)) {
                vbox.getChildren().remove(child);
                return;
            }
        }
    }

    private User isExistUsername(String username) {
        ArrayList<User> users = User.getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public void startGame(ArrayList<User> users, boolean autoSave) {
        Tile[][] map = new Tile[WIDTH][LENGTH];
        game = new Game(null, map);
        MapController.getInstance().createMap(map, WIDTH, LENGTH);
        game.addRuins();
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, users);
        game.setCivilizations(civilizations);
        if (autoSave)
            autoSave();
        //saveGame();
        //loadGame();
    }

    private void createCivilizations(ArrayList<Civilization> civilizations, ArrayList<User> users) {
        int x = 5, y = 5;
        for (User user : users) {
            Civilization civilization;
            if (user.getUsername().equals(User.getLoggedInUser().getUsername()))
                civilization = new Civilization(user, CreateGameMenu.certainX, CreateGameMenu.certainY);
            else
                civilization = new Civilization(user, x, y);

            civilizations.add(civilization);
            user.setCivilization(civilization);
            x += 5;
            y += 5;
        }
    }

    public String cheatTurn(int turn) {
        for (int i = 0; i < turn; i++)
            this.game.nextTurn();
        return "successful";
    }

    public String cheatGold(int amount) {
        game.getCurrentCivilization().setGold(amount + game.getCurrentCivilization().getGold());
        return "successful";
    }

    public Game getGame() {
        return game;
    }

    public String combat(int x, int y) {
        City city;
        MilitaryUnit enemyMilitaryUnit;
        Unit enemyUnit;
        if (game.getSelectedCombatUnit() == null)
            return "no selected combat unit";
        if (!game.getSelectedCombatUnit().getState().equals("ready"))
            return "combat unit is not ready";
        if (!CityController.getInstance().isXTileValid(x))
            return "x position is not valid";
        if (!CityController.getInstance().isYTileValid(y))
            return "y position is not valid";
        if ((city = CityController.getInstance().getCity(x, y)) != null) {
            Civilization civilization = null;
            for (Civilization civilization1 : game.getCivilizations()) {
                for (City civilization1City : civilization1.getCities()) {
                    if (civilization1City.getName().equals(city.getName())) {
                        civilization = civilization1;
                        break;
                    }
                }
            }
            if (game.getWar().get(game.getCurrentCivilization().leaderName()).equals(civilization.leaderName()))
                return CityController.getInstance().combat(city, game.getSelectedCombatUnit(), x, y);
            else
                return "you are not in war with this civilization";
        } else if ((enemyMilitaryUnit = game.getMap()[x][y].getMilitaryUnit()) != null) {
            Civilization civilization = null;
            for (Civilization civilization1 : game.getCivilizations()) {
                for (MilitaryUnit militaryUnit : civilization1.getMilitaryUnits()) {
                    if (militaryUnit.getX() == enemyMilitaryUnit.getX() && militaryUnit.getY() == enemyMilitaryUnit.getY()) {
                        civilization = civilization1;
                        break;
                    }
                }
            }
            if (game.getWar().get(game.getCurrentCivilization().leaderName()).equals(civilization.leaderName()))
                return UnitController.getInstance().combat(enemyMilitaryUnit);
            else
                return "you are not in war with this civilization";
        } else if ((enemyUnit = game.getMap()[x][y].getCivilian()) != null) {
            Civilization civilization = null;
            for (Civilization civilization1 : game.getCivilizations()) {
                for (Unit unit : civilization1.getUnits()) {
                    if (unit.getX() == enemyUnit.getX() && unit.getY() == enemyUnit.getY()) {
                        civilization = civilization1;
                        break;
                    }
                }
            }
            if (game.getWar().get(game.getCurrentCivilization().leaderName()).equals(civilization.leaderName()))
                return UnitController.getInstance().combat(enemyUnit);
            else
                return "you are not in war with this civilization";
        }
        return "combat is not possible";
    }

    public int getLENGTH() {
        return LENGTH;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public Civilization getCivilization(int x, int y) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (x == cityTile.getX() && y == cityTile.getY())
                        return civilization;
                }
            }
        }
        return null;
    }

//    public String foundCity(Matcher matcher) {
//        return CityController.getInstance().createCity(matcher);
//    }

    // TODO complete
    public String cancelMission() {
        if (game.getSelectedCombatUnit() != null) {
            if (!game.getSelectedCombatUnit().isHasDone())
                return "unit has no works";

        }
        return null;
    }


    public String technologyStudy(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (game.getCurrentCivilization().getCurrentTechnology() != null)
            return "no need buy";
        if (technology == null)
            return "technologyName is invalid";
        if (!game.getCurrentCivilization().isExistTechnology(technologyName))
            return "don not have access to this technology";
        if (game.getCurrentCivilization().getScience() < technology.getCost())
            return "science is ont enough";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        game.getCurrentCivilization().setRemainingTurns(20 / game.getCurrentCivilization().getHappiness());
        return "technology buy successfully";
    }

    public String technologyChange(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (technology == null)
            return "technologyName is invalid";
        if (!game.getCurrentCivilization().isExistTechnology(technologyName))
            return "don not have access to this technology";
        if (game.getCurrentCivilization().getCurrentTechnology() == null)
            return "no need to change";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        game.getCurrentCivilization().setRemainingTurns(20 / game.getCurrentCivilization().getHappiness());
        return "technology change successfully";
    }

    public String technologyMenu() {
        StringBuilder sb = new StringBuilder();
        Civilization currentCivilization = game.getCurrentCivilization();
        sb.append("studyTechnology: " + "\n");

        for (int i = 0; i < currentCivilization.getTechnologies().size(); i++) {
            sb.append(currentCivilization.getTechnologies().get(i).getName() + "\n");
        }

        sb.append("availableTechnology: " + "\n");
        for (int i = 0; i < currentCivilization.getAvailableTechnology().size(); i++) {
            sb.append(currentCivilization.getAvailableTechnology().get(i).getName() + "\n");
        }
        return sb.toString();
    }

    public void getResourceAndGoldTurn() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (cityTile.isThereCitizen()) {
                        if (civilization.isExistTechnology(cityTile.getResource().getNeededTechnology().getName())) {
                            if (cityTile.getImprovement() != null && cityTile.getImprovement().getName()
                                    .equals(cityTile.getResource().getNeededImprovement().getName()) &&
                                    !cityTile.isNeedRepair()) {
                                if (cityTile.getResource().getType().equals("Luxury")) {
                                    civilization.addLuxuryResource(cityTile.getResource());
                                    if (cityTile.getResource().getName().equals("Gold")) {
                                        int amount = 0;
                                        amount += cityTile.getTerrain().getGold();
                                        amount += cityTile.getResource().getGold();
                                        if (cityTile.getImprovement() != null)
                                            amount += cityTile.getImprovement().getGoldChange();
                                        for (int i = 0; i < cityTile.getRivers().length; i++) {
                                            if (cityTile.getRivers()[i])
                                                amount++;
                                        }

                                        city.setGold(city.getGold() + amount);
                                    }
                                    if (!civilization.hasLuxuryResource(cityTile.getResource()))
                                        civilization.increaseHappiness(10);
                                } else
                                    city.addResource(cityTile.getResource());
                            }
                        }
                    }
                }
            }
        }
    }

    public void decreaseTechnologyTurn() {
        for (Civilization civilization : game.getCivilizations()) {
            if (civilization.getRemainingTurns() != -1) {
                if (civilization.getRemainingTurns() == 0) {
                    civilization.setRemainingTurns(-1);
                    civilization.addTechnology(civilization.getCurrentTechnology());
                    civilization.setCurrentTechnology(null);
                } else
                    civilization.setRemainingTurns(civilization.getRemainingTurns() - 1);
            }
        }
    }

    public String cheatHappiness(int amount) {
        game.getCurrentCivilization().increaseHappiness(amount);
        return "successful";
    }

    public String cheatScience(int amount) {
        game.getCurrentCivilization().increaseScience(amount);
        return "successful";
    }

    public String cheatTechnology(int amount) {
        game.getCurrentCivilization().setRemainingTurns(0);
        return "successful";
    }

    public String cheatAllTechnology(String technologyName) {
        Technology technology = null;
        for (TechnologyEnum technologyEnum : TechnologyEnum.values()) {
            if (technologyName.equals(technologyEnum.getName()))
                technology = new Technology(technologyEnum);
        }
        if (game.getCurrentCivilization().getCurrentTechnology() != null)
            return "no need buy";
        if (technology == null)
            return "technologyName is invalid";

        game.getCurrentCivilization().addTechnology(technology);
        return "successful";
    }

    public void food() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                city.setAllFood(city.getAllFood() - city.getPopulation());
                if (city.getCivilian() != null && !city.getCivilian().isHasDone())
                    city.setAllFood(city.getAllFood() - 1);
            }
        }
    }

    public void makeFood() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Tile cityTile : city.getCityTiles()) {
                    if (cityTile.isThereCitizen()) {
                        int amount = cityTile.getFood();
                        if (cityTile.getImprovement() != null)
                            amount += cityTile.getImprovement().getFoodChange();
                        city.setAllFood(city.getAllFood() + amount);
                    }
                }
            }
        }
        for (int i = 0; i < game.getCivilizations().size(); i++) {
            for (int i1 = 0; i1 < game.getCivilizations().get(i).getCities().size(); i1++) {
                if (game.getCivilizations().get(i).getCities().get(i1).getAllFood() <= 0) {
                    game.getCivilizations().get(i).getCities().get(i1).setAllFood(0);
                    game.getCivilizations().get(i).decreaseHappiness(10);
                }
            }
        }
    }

    private void autoSave() {
        Thread thread = new Thread(() -> {
            long time = new Date().getTime();

            while (true) {
                if (new Date().getTime() > 60000 + time) {
                    GameController.getInstance().saveGame();
                    time = new Date().getTime();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void saveGame() {
        try {
            FileWriter fileWriter = new FileWriter("game.xml");
            XStream xStream = new XStream();
            for (int i = 0; i < game.getMap().length; i++) {
                for (int j = 0; j < game.getMap()[0].length; j++) {
                    game.getMap()[i][j].setNeighbourTiles(null);
                }
            }
            fileWriter.write(xStream.toXML(game));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            String path = new String(Files.readAllBytes(Paths.get("game.xml")));
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            this.game = (Game) xStream.fromXML(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlace() {
        Main.getPopup().setY(600);
        TextField textFieldX = new TextField("enter x");
        TextField textFieldY = new TextField("enter y");
        Button button = new Button("ok");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (textFieldX.getText().matches("\\d+"))
                    CreateGameMenu.certainX = Integer.parseInt(textFieldX.getText());
                if (textFieldY.getText().matches("\\d+"))
                    CreateGameMenu.certainY = Integer.parseInt(textFieldY.getText());


                if (CreateGameMenu.certainX > 8 || CreateGameMenu.certainX < 1
                        || CreateGameMenu.certainY > 8 || CreateGameMenu.certainY < 1) {
                    Main.showPopupJustText("enter number between 2 and 8 for x and y");
                    CreateGameMenu.certainX = 5;
                    CreateGameMenu.certainY = 5;
                }
            }
        });
        VBox vBox = new VBox(textFieldX, textFieldY, button);
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());

    }

    public void startWar(int x, int y) {
        City city;
        MilitaryUnit enemyMilitaryUnit;
        if ((city = CityController.getInstance().getCity(x, y)) != null) {
            Civilization civilization = null;
            for (Civilization civilization1 : game.getCivilizations()) {
                for (City civilization1City : civilization1.getCities()) {
                    if (civilization1City.getName().equals(city.getName())) {
                        civilization = civilization1;
                        break;
                    }
                }
            }
            startWar(civilization);
        }
        if ((enemyMilitaryUnit = game.getMap()[x][y].getMilitaryUnit()) != null) {
            Civilization civilization = null;
            for (Civilization civilization1 : game.getCivilizations()) {
                for (MilitaryUnit militaryUnit : civilization1.getMilitaryUnits()) {
                    if (militaryUnit.getX() == enemyMilitaryUnit.getX() && militaryUnit.getY() == enemyMilitaryUnit.getY()) {
                        civilization = civilization1;
                        break;
                    }
                }
            }
            startWar(civilization);
        }
    }

    private void startWar(Civilization civilization) {
        game.getWar().put(game.getCurrentCivilization().leaderName(), civilization.leaderName());
        game.getWar().put(civilization.leaderName(), game.getCurrentCivilization().leaderName());
        game.getCurrentCivilization().addToNotifications("you start war with " + civilization.leaderName());
        civilization.addToNotifications("civilization " + game.getCurrentCivilization().leaderName() + " started war against you");
    }
}
