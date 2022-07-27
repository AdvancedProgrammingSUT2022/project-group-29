package controllers;

import app.Main;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import enums.modelsEnum.TechnologyEnum;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import models.*;


import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameController {
    private static GameController instance = null;
    private final int LENGTH = 45;
    private final int WIDTH = 30;
    private Game game;
    private ArrayList<User> players = new ArrayList<>();
    private ArrayList<SocketController> socketControllers = new ArrayList<>();

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }


    public void endGame(String nickname) {
        for (SocketController socket : socketControllers) {
            if (socket.getUpdater()) {
                try {
                    HashMap hashMap = new HashMap<>();
                    hashMap.put("win",nickname);
                    Request request = new Request("end",hashMap);
                    socket.getDataOutputStream().writeUTF(new Gson().toJson(request));
                    socket.getDataOutputStream().flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }
    }


    public Response initGame(String hash) {
        User user = User.getHash().get(hash);
        players.add(user);
        StringBuilder str = new StringBuilder();
        for (SocketController socket : Main.getSockets()) {
            if (!socket.getUpdater() && socket.getUser().equals(user)) {
                for (SocketController socketController : Main.getSockets()) {
                    if (socketController.getStatus() == socket.getStatus() + 1) {
                        socketControllers.add(socket);
                        socketControllers.add(socketController);
                    }
                }
            }
        }
        str.append(user.getUsername()).append("-");
        for (SocketController socket : Main.getSockets()) {
            if (!socket.getUpdater() && socket.getUser() != null && !socket.getUser().equals(user)) {
                str.append(socket.getUser().getUsername()).append("-");
            }
        }
        str.append(1);
        return new Response(1, String.valueOf(str));
    }

    public Response addUserToGame(String username) {
        for (User player : players) {
            if (username.equals(player.getUsername()))
                return new Response(0, "User Has Been Selected");
        }

        for (SocketController socket : Main.getSockets()) {
            if (!socket.getUpdater() && socket.getUser().getUsername().equals(username)) {
                for (SocketController socketController : Main.getSockets()) {
                    if (socketController.getStatus() == socket.getStatus() + 1) {
                        try {
                            Request request = new Request("add", null);
                            socketController.getDataOutputStream().writeUTF(new Gson().toJson(request));
                            socketController.getDataOutputStream().flush();
                            String message = socketController.getDataInputStream().readUTF();
                            Response response = new Gson().fromJson(message, Response.class);
                            response.setMessage(socket.getUser().getUsername() + " " + response.getMessage());
                            if (response.getStatus_code() == 1) {
                                players.add(socket.getUser());
                                socketControllers.add(socket);
                                socketControllers.add(socketController);
                            }
                            return response;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }
        return new Response(0, "");
    }


    public Response startGame() {
        Tile[][] map = new Tile[WIDTH][LENGTH];
        game = new Game(null, map);
        MapController.getInstance().createMap(map, WIDTH, LENGTH);
        game.addRuins();
        ArrayList<Civilization> civilizations = new ArrayList<>();
        createCivilizations(civilizations, players);
        game.setCivilizations(civilizations);
        for (int i = 0; i < game.getMap().length; i++) {
            for (int j = 0; j < game.getMap()[0].length; j++) {
                game.getMap()[i][j].setNeighbourTiles(null);
            }
        }
        notifyPlayers();

        return new Response(0, "");
    }

    public void notifyPlayers() {
        int i = 5;
        int k = 0;
        for (SocketController socketController : socketControllers) {
            if (socketController.getUpdater()) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("game", addSources(i, i, k));
                hashMap.put("x", String.valueOf(i));
                hashMap.put("y", String.valueOf(i));
                Request request = new Request("start", hashMap);
                try {
                    socketController.getDataOutputStream().writeUTF(new Gson().toJson(request));
                    socketController.getDataOutputStream().flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                i += 5;
                k++;
            }
        }
    }

    public boolean isTerrainVisible(int x, int y, int c) {
        for (City city : game.getCivilizations().get(c).getCities()) {
            for (Tile cityTile : city.getCityTiles()) {
                if (x == cityTile.getX() && (y == cityTile.getY() || y == cityTile.getY() + 1 || y == cityTile.getY() - 1))
                    return true;
                if (y == cityTile.getY() && (x == cityTile.getX() || x == cityTile.getX() + 1 || x == cityTile.getX() - 1))
                    return true;
            }
        }
        for (MilitaryUnit militaryUnit : game.getCivilizations().get(c).getMilitaryUnits()) {
            for (int i = x - 2; i <= x + 2; i++) {
                for (int j = y - 2; j <= y + 2; j++) {
                    if ((i == militaryUnit.getX()) && (j == militaryUnit.getY()))
                        return true;
                }
            }
        }

        for (Unit unit : game.getCivilizations().get(c).getUnits()) {
            for (int i = x - 2; i <= x + 2; i++) {
                for (int j = y - 2; j <= y + 2; j++) {
                    if ((i == unit.getX()) && (j == unit.getY()))
                        return true;
                }
            }
        }

        return false;
    }

    public String addSources(int x, int y, int c) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if (isTerrainVisible(x + i, y + j, c)) {
                    str.append(game.getMap()[x + i][y + j].getTerrain().getKind()).append("-");
                    if (game.getMap()[x + i][y + j].getFeature() != null)
                        str.append(game.getMap()[x + i][y + j].getFeature().getKind()).append("-");
                    else
                        str.append("no").append("-");
                    if (game.getMap()[x + i][y + i].isDiscoveredRuin())
                        str.append("yes").append("-");
                    else
                        str.append("no").append("-");
                    if (game.getMap()[x + i][y + j].getMilitaryUnit() != null && game.getMap()[x + i][y + j].getCivilian() != null) {
                        str.append(game.getMap()[x + i][y + j].getCivilian().getName()).append("-");
                        str.append(game.getMap()[x + i][y + j].getMilitaryUnit().getName()).append("-");
                    } else if (game.getMap()[x + i][y + j].getMilitaryUnit() != null && game.getMap()[x + i][y + j].getCivilian() == null) {
                        str.append("no").append("-");
                        str.append(game.getMap()[x + i][y + j].getMilitaryUnit().getName()).append("-");
                    } else if (game.getMap()[x + i][y + j].getMilitaryUnit() == null && game.getMap()[x + i][y + j].getCivilian() != null) {
                        str.append("no").append("-");
                        str.append(game.getMap()[x + i][y + j].getCivilian().getName()).append("-");
                    } else {
                        str.append("no").append("-");
                        str.append("no").append("-");
                    }
                    if (game.getMap()[x + i][y + j].getBuilding() != null)
                        str.append(game.getMap()[x + i][y + j].getBuilding().getName()).append("-");
                    else
                        str.append("no").append("-");
                    int u = 0;
                    outer:
                    for (Civilization civilization : game.getCivilizations()) {
                        for (City city : civilization.getCities()) {
                            if (city.getX() == x + i && city.getY() == y + j) {
                                u = 1;
                                str.append("yes").append("-");
                                str.append(city.getName()).append("-");
                                break outer;
                            }
                        }
                    }
                    if (u == 0) {
                        str.append("no").append("-");
                    }

                } else
                    str.append("no").append("-");

                /*
                    // TODO city

                                    // TODO city panel
                                    game.setSelectedCity(city);
                                    Label label = new Label("name: " + city.getName());
                                    Label label1 = new Label("\npopulation: " + city.getPopulation());
                                    Label label2 = new Label("\ncity hp: " + city.getHitPoint());
                                    Label label3 = new Label("\ngold: " + city.getGold());
                                    Label label4 = new Label("\nmilitary unit: " + (city.getMilitaryUnit() == null ? "nothing" : city.getMilitaryUnit().getName()));
                                    Label label5 = new Label("\nnon military unit: " + (city.getCivilian() == null ? "nothing" : city.getCivilian().getName()));
                                    Label label6 = new Label("\nyou can not\ncreate military unit");
                                    Label label15 = new Label("\ncity output: " + city.getFood());
                                    StringBuilder s = new StringBuilder();
                                    for (Resource resource : city.getAllResources()) {
                                        s.append(resource.getName() + "\n");
                                    }
                                    Button button5 = new Button("show resources");
                                    button5.setOnMouseClicked(event18 -> {
                                        Main.getPopup().getContent().clear();
                                        Main.showPopupJustText(String.valueOf(s));
                                    });
                                    TextField textField = new TextField();
                                    textField.setDisable(true);
                                    Button button = new Button("\nok");
                                    if (city.getMilitaryUnit() == null) {
                                        textField.setDisable(false);
                                        label6 = new Label("\ncreate unit");
                                        //button.setOnMouseClicked(event12 -> Main.showPopupJustText(CityController.getInstance().createUnit(textField.getText())));
                                    }
                                    Label label7 = new Label("\nyou can not create\nnon military unit");
                                    TextField textField1 = new TextField();
                                    Button button1 = new Button("\nok");
                                    textField1.setDisable(true);
                                    if (city.getCivilian() == null) {
                                        textField1.setDisable(false);
                                        label7 = new Label("\ncreate unit");
                                        //button1.setOnMouseClicked(event13 -> Main.showPopupJustText(CityController.getInstance().createUnit(textField1.getText())));
                                    }
                                    Label label8 = new Label("\nbuy tile");
                                    TextField textFieldX = new TextField("x");
                                    TextField textFieldY = new TextField("y");
                                    Button button2 = new Button("ok");
                                    button2.setOnMouseClicked(event14 -> {
                                        if (textFieldX.getText().matches("\\d+") && textFieldY.getText().matches("\\d+"))
                                            ;
                                        //Main.showPopupJustText(CityController.getInstance().cityBuyTile(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText())));
                                    });
                                    Label label9 = new Label("attack");
                                    TextField textFieldX1 = new TextField("x");
                                    TextField textFieldY1 = new TextField("y");
                                    Button button3 = new Button("ok");
                                    button3.setOnMouseClicked(event1 -> {
                                        if (textFieldX.getText().matches("\\d+") && textFieldY.getText().matches("\\d+"))
                                            ;
                                        //Main.showPopupJustText(CityController.getInstance().cityAttack(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText())));
                                    });
                                    Label label10 = new Label("create building");
                                    label10.addEventHandler(MouseEvent.MOUSE_CLICKED, event16 -> {
                                        ArrayList<Label> labels = new ArrayList<>();
                                        for (Building building : city.getCanBuild()) {
                                            Label label11 = new Label(building.getName());
                                            labels.add(label11);
                                            label11.addEventHandler(MouseEvent.MOUSE_CLICKED, event15 -> {
                                                Label label12 = new Label("\nneeded tech: " + (building.getNeededTechnology() != null ? building.getNeededTechnology().getName() : "nothing")
                                                        + "\ncost: " + building.getCost() + "\nMaintenance: " + building.getMaintenance());
                                                Button button4 = new Button("build");
                                                button4.setOnMouseClicked(event17 -> {
                                                    Main.getPopup().getContent().clear();
                                                    //Main.showPopupJustText(CityController.getInstance().createBuilding(building, city));
                                                });
                                                Main.getPopup().getContent().clear();
                                                VBox vBox = new VBox();
                                                vBox.getChildren().addAll(label12, button4);
                                                vBox.setStyle("-fx-background-color: #da76d6");
                                                Main.getPopup().getContent().add(vBox);
                                                Main.getPopup().show(Main.getScene().getWindow());
                                            });
                                        }
                                        Main.getPopup().getContent().clear();
                                        VBox vBox = new VBox();
                                        vBox.getChildren().addAll(labels);
                                        vBox.setStyle("-fx-background-color: #da76d6");
                                        Main.getPopup().getContent().add(vBox);
                                        Main.getPopup().show(Main.getScene().getWindow());
                                    });
                                    Main.getPopup().getContent().clear();
                                    VBox vBox = new VBox(label, label1, label2, label3, label4, label5, label6, label15, button5, textField, button, label7, textField1, button1, label8, textFieldX, textFieldY, button2, label9, textFieldX1, textFieldY1, button3, label10);
                                    vBox.setStyle("-fx-background-color: #da76d6");
                                    Main.getPopup().getContent().add(vBox);
                                    Main.getPopup().show(Main.getScene().getWindow());
                                });
                                pane.getChildren().add(imageView2);
                                break outer;
                            }
                        }
                    }
*/
            }
        }
        return String.valueOf(str);
    }

    public Response getCity(String name) {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                if (city.getName().equals(name)) {
                    game.setSelectedCity(city);
                    break;
                }
            }
        }


        StringBuilder str = new StringBuilder();
        str.append(game.getSelectedCity().getName()).append("-");
        str.append(game.getSelectedCity().getPopulation()).append("-");
        str.append(game.getSelectedCity().getHitPoint()).append("-");
        str.append(game.getSelectedCity().getGold()).append("-");
        if (game.getSelectedCity().getMilitaryUnit() == null)
            str.append("nothing").append("-");
        else
            str.append(game.getSelectedCity().getMilitaryUnit()).append("-");
        if (game.getSelectedCity().getCivilian() == null)
            str.append("nothing").append("-");
        else
            str.append(game.getSelectedCity().getCivilian()).append("-");
        str.append(game.getSelectedCity().getFood()).append("-");

        StringBuilder s = new StringBuilder();
        for (Resource resource : game.getSelectedCity().getAllResources()) {
            s.append(resource.getName() + "\n");
        }
        str.append(s).append("-");
        return new Response(1, String.valueOf(str));
    }


    private void createCivilizations(ArrayList<Civilization> civilizations, ArrayList<User> users) {
        int x = 5, y = 5;
        for (User user : users) {
            Civilization civilization;
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

    public String cheat(String type) {
        if (type.equals("gold"))
            cheatGold(10);
        else
            cheatHappiness(10);
        return "successful";
    }

    public Game getGame() {
        return game;
    }

    public String showBar() {
        StringBuilder str = new StringBuilder();
        Civilization civilization = game.getCurrentCivilization();
        str.append(civilization.getGold()).append("-");
        str.append(civilization.getHappiness()).append("-");
        str.append(civilization.getScience()).append("-");
        if (civilization.getCurrentTechnology() == null)
            str.append("nothing").append("-");
        else
            str.append(civilization.getCurrentTechnology().getName()).append("-");
        if (game.getSelectedNonCombatUnit() != null)
            str.append(game.getSelectedNonCombatUnit().getName()).append("-");
        else if (game.getSelectedCombatUnit() != null)
            str.append(game.getSelectedCombatUnit().getName()).append("-");
        else
            str.append("nothing").append("-");
        if (game.getCurrentCivilization().getCurrentTechnology() == null)
            str.append("no").append("-");
        else {
            str.append("yes").append("-");
            str.append(game.getCurrentCivilization().getCurrentTechnology().getName()).append("-");
        }
        str.append(civilization.getAvailableTechnology().size()).append("-");
        for (Technology technology : civilization.getAvailableTechnology()) {
            str.append(technology.getName()).append("-");
        }
        if (civilization.getCurrentTechnology() == null)
            str.append("yes").append("-");
        else
            str.append("no").append("-");
        return String.valueOf(str);
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
        game.getCurrentCivilization().setRemainingTurns(40 / game.getCurrentCivilization().getHappiness());
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
        if (game.getCurrentCivilization().getScience() < technology.getCost())
            return "science is ont enough";
        game.getCurrentCivilization().setCurrentTechnology(technology);
        game.getCurrentCivilization().addTechnology(technology);
        game.getCurrentCivilization().decreaseScience(technology.getCost());
        game.getCurrentCivilization().setRemainingTurns(40 / game.getCurrentCivilization().getHappiness());
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
        //Main.getPopup().setY(600);
        TextField textFieldX = new TextField("enter x");
        TextField textFieldY = new TextField("enter y");
        Button button = new Button("ok");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /*if (textFieldX.getText().matches("\\d+"))
                    CreateGameMenu.certainX = Integer.parseInt(textFieldX.getText());
                if (textFieldY.getText().matches("\\d+"))
                    CreateGameMenu.certainY = Integer.parseInt(textFieldY.getText());


                if (CreateGameMenu.certainX > 8 || CreateGameMenu.certainX < 1
                        || CreateGameMenu.certainY > 8 || CreateGameMenu.certainY < 1) {
                    Main.showPopupJustText("enter number between 2 and 8 for x and y");
                    CreateGameMenu.certainX = 5;
                    CreateGameMenu.certainY = 5;
                }*/
            }
        });
        VBox vBox = new VBox(textFieldX, textFieldY, button);
        //Main.getPopup().getContent().add(vBox);
        //Main.getPopup().show(Main.getScene().getWindow());

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

    public void maintenanceGold() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                int cost = 0;
                for (Building building : city.getBuildings()) {
                    cost += building.getMaintenance();
                }
                city.setGold(city.getGold() - cost);
            }
        }
    }

    public void updateCanBuild() {
        for (Civilization civilization : game.getCivilizations()) {
            for (City city : civilization.getCities()) {
                for (Building building : city.getCanTBuild()) {
                    if (isExistNeededBuilding(building, city) && isExistNeededTechnology(building, civilization)) {
                        city.getCanBuild().add(building);
                        city.getCanTBuild().remove(building);
                    }
                }
            }
        }
    }

    private boolean isExistNeededTechnology(Building building, Civilization civilization) {
        for (Technology technology : civilization.getTechnologies()) {
            if (technology.getName().equals(building.getNeededTechnology().getName()))
                return true;
        }
        return false;
    }

    private boolean isExistNeededBuilding(Building building, City city) {
        for (Building cityBuilding : city.getBuildings()) {
            if (building.getNeededBuilding().getName().equals(cityBuilding.getName()))
                return true;
        }
        return false;
    }

    public String tech(String name, String type) {
        if (type.equals("study"))
            return technologyStudy(name);
        else
            return technologyChange(name);
    }

    public Response addRequest(String hash, String username) {
        User sender = User.getHash().get(hash);
        User asked = User.getUserByUsername(username);
        for (String friend : sender.getFriends()) {
            if (friend.equals(username))
                return new Response(0, "User is already a friend");
        }
        asked.getFriendRequests().put(sender.getUsername(), 0);
        return new Response(1, "friend request sent");
    }

    public Response requestSent(String hash) {
        User sender = User.getHash().get(hash);
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : User.getAllUsers()) {
            if (user.getFriendRequests().get(sender.getUsername()) != null) {
                if (user.getFriendRequests().get(sender.getUsername()) == 0)
                    stringBuilder.append(user.getUsername()).append("-").append("waiting");
                else
                    stringBuilder.append(user.getUsername()).append("-").append("rejected");
                stringBuilder.append("\n");
            }
        }
        return new Response(1, String.valueOf(stringBuilder));
    }

    public Response requestAsked(String hash) {
        User sender = User.getHash().get(hash);
        StringBuilder stringBuilder = new StringBuilder();
        for(Map.Entry<String, Integer> entry : sender.getFriendRequests().entrySet()) {
            if (entry.getValue() == 0)
                stringBuilder.append(entry.getKey()).append("-");
        }
        return new Response(1, String.valueOf(stringBuilder));
    }

    public Response addFriend(String hash, String username) {
        User user = User.getHash().get(hash);
        User asked = User.getUserByUsername(username);
        user.getFriendRequests().remove(username);
        user.getFriends().add(asked.getUsername());
        asked.getFriends().add(user.getUsername());
        return new Response(1, "");
    }

    public Response rejectFriend(String hash, String username) {
        User user = User.getHash().get(hash);
        User asked = User.getUserByUsername(username);
        user.getFriendRequests().replace(username, 1);
        return new Response(1, "");
    }



}


