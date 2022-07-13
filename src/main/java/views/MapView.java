package views;

import app.Main;
import controllers.GameController;
import controllers.MapController;
import enums.modelsEnum.TechnologyEnum;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MapView implements Initializable {
    public Pane pane;
    private static int xMap = 0, yMap = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMap(xMap, yMap);
        showBar();
        cheat();
        select();
    }

    private void cheat() {
        pane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.isShiftDown() && event.getCode().getName().equals("C")) {
                TextField textField = new TextField();
                Button button = new Button("ok");
                VBox vBox = new VBox(textField, button);
                button.setOnMouseClicked(event1 -> {
                    if (textField.getText().equals("cheat gold"))
                        GameController.getInstance().cheatGold(10);
                    else if (textField.getText().equals("cheat happiness"))
                        GameController.getInstance().cheatHappiness(10);
                });
                vBox.setStyle("-fx-background-color: #da76d6");
                Main.getPopup().getContent().clear();
                Main.getPopup().getContent().add(vBox);
                Main.getPopup().show(Main.getScene().getWindow());
            }
        });
    }

    private void showMap(int x, int y) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                // terrain
                ImageView imageView;
                if (MapController.getInstance().isTerrainVisible(x + i, y + j)) {
                    imageView = new ImageView(new Image(Main.class.getResource("/assets/terrainTexture/" +
                                    GameController.getInstance().getGame().getMap()[x + i][y + j].getTerrain().getKind() + ".png")
                            .toExternalForm()));
                } else {
                    imageView = new ImageView(new Image(Main.class.getResource("/assets/terrainTexture/fogofwar.png")
                            .toExternalForm()));
                }
                imageView.setX(j * 75 + 50);
                imageView.setY(i * 120 + 60 + (j % 2) * 60);
                imageView.setFitHeight(120);
                imageView.setFitWidth(100);
                pane.getChildren().add(imageView);

                // feature
                ImageView imageView1;
                if (MapController.getInstance().isTerrainVisible(x + i, y + j)) {
                    if (GameController.getInstance().getGame().getMap()[x + i][y + j].getFeature() != null) {
                        imageView1 = new ImageView(new Image(Main.class.getResource("/assets/featureTexture/" +
                                        GameController.getInstance().getGame().getMap()[x + i][y + j].getFeature().getKind() + ".png")
                                .toExternalForm()));
                        imageView1.setX(j * 75 + 50);
                        imageView1.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView1.setFitHeight(120);
                        imageView1.setFitWidth(100);
                        pane.getChildren().add(imageView1);
                    }

                    // unit
                    if (GameController.getInstance().getGame().getMap()[x + i][y + j].getMilitaryUnit() != null &&
                            GameController.getInstance().getGame().getMap()[x + i][y + j].getCivilian() != null) {
                        ImageView imageView2 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                GameController.getInstance().getGame().getMap()[x + i][y + j].getCivilian().getName() + ".png").toExternalForm()));
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                GameController.getInstance().getGame().getMap()[x + i][y + j].getMilitaryUnit().getName() + ".png").toExternalForm()));
                        VBox box = new VBox(imageView2, imageView3);
                        box.setLayoutX(j * 75 + 50);
                        box.setLayoutY(i * 200 + 60 + (j % 2) * 60);
                        box.setPrefHeight(100);
                        box.setPrefWidth(90);
                        pane.getChildren().add(box);
                    } else if (GameController.getInstance().getGame().getMap()[x + i][y + j].getMilitaryUnit() != null &&
                            GameController.getInstance().getGame().getMap()[x + i][y + j].getCivilian() == null) {
                        System.out.println((x + i) + "  " + (y + j));
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/warrior.png").toExternalForm()));
                        imageView3.setX(j * 75 + 50);
                        imageView3.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView3.setFitHeight(120);
                        imageView3.setFitWidth(100);
                        pane.getChildren().add(imageView3);
                    } else if (GameController.getInstance().getGame().getMap()[x + i][y + j].getMilitaryUnit() == null &&
                            GameController.getInstance().getGame().getMap()[x + i][y + j].getCivilian() != null) {
                        System.out.println();
                        System.out.println((x + i) + " " + (y + j));
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                GameController.getInstance().getGame().getMap()[x + i][y + j].getCivilian().getName() + ".png").toExternalForm()));
                        imageView3.setX(j * 75 + 50);
                        imageView3.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView3.setFitHeight(120);
                        imageView3.setFitWidth(100);
                        pane.getChildren().add(imageView3);
                    }

                    // TODO city
                    for (Civilization civilization : GameController.getInstance().getGame().getCivilizations()) {
                        for (City city : civilization.getCities()) {
                            if (city.getX() == x + i && city.getY() == y + j) {

                            }
                        }
                    }
                }

            }
        }
    }

    private void showBar() {
        Label label = new Label("gold: " + GameController.getInstance().getGame().getCurrentCivilization().getGold());
        label.setLayoutX(10);
        label.setLayoutY(10);
        Label label1 = new Label("happiness: " + GameController.getInstance().getGame().getCurrentCivilization().getHappiness());
        label1.setLayoutX(70);
        label1.setLayoutY(10);
        Label label2 = new Label("science: " + GameController.getInstance().getGame().getCurrentCivilization().getScience());
        label2.setLayoutX(165);
        label2.setLayoutY(10);
        Label label3 = new Label("current technology: " + (GameController.getInstance().getGame().getCurrentCivilization().getCurrentTechnology() == null ? "nothing" : GameController.getInstance().getGame().getCurrentCivilization().getCurrentTechnology()));
        label3.setLayoutX(240);
        label3.setLayoutY(10);
        Label label4 = new Label("selected unit: " + func());
        label4.setLayoutX(440);
        label4.setLayoutY(10);

        // TODO show technology graph
        Circle technologyPic = new Circle(60, 90, 50, Color.WHITE);
        if (GameController.getInstance().getGame().getCurrentCivilization().getCurrentTechnology() != null)
            technologyPic.setFill(new ImagePattern(new Image(Main.class.getResource("/assets/technology/" +
                    GameController.getInstance().getGame().getCurrentCivilization().getCurrentTechnology().getName() + ".png").toExternalForm())));
        technologyPic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (GameController.getInstance().getGame().getCurrentCivilization().getTechnologies().size() > 0) {
                    Label label5 = new Label("last technology: " + GameController.getInstance().getGame().getCurrentCivilization().getTechnologies().get(GameController.getInstance().getGame().getCurrentCivilization().getTechnologies().size() - 1).getName());
                    Main.getPopup().getContent().clear();
                    VBox vBox = new VBox(label5);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }
            }
        });
        pane.getChildren().add(label);
        pane.getChildren().add(label1);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(label4);
        pane.getChildren().add(technologyPic);
    }

    private String func() {
        if (GameController.getInstance().getGame().getSelectedNonCombatUnit() != null)
            return GameController.getInstance().getGame().getSelectedNonCombatUnit().getName();
        if (GameController.getInstance().getGame().getSelectedCombatUnit() != null)
            return GameController.getInstance().getGame().getSelectedCombatUnit().getName();
        return "nothing";
    }

    public void research(MouseEvent mouseEvent) {
        showInfo("info research");
    }

    public void units(MouseEvent mouseEvent) {
        showInfo("info units");
    }

    public void cities(MouseEvent mouseEvent) {
        showInfo("info cities");
    }

    public void demographics(MouseEvent mouseEvent) {
        showInfo("info demographics");
    }

    public void notifications(MouseEvent mouseEvent) {
        showInfo("info notifications");
    }

    public void military(MouseEvent mouseEvent) {
        showInfo("info military");
    }

    public void economic() {
        showInfo("info economic");
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
            System.out.println("error");
    }

    // TODO
    private void showEconomic() {

    }

    private void showMilitary() {
        showUnits();
    }

    private void showNotifications() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        for (String notification : civilization.getNotifications())
            stringBuilder.append(notification).append("\n");
        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    private void showDemographics() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        stringBuilder.append("population: ").append(civilization.calculatePopulation()).append("\ngold: ").append(civilization.getGold()).append("\nnumber of combat units: ").append(civilization.getMilitaryUnits().size()).append("\nhappiness: ").append(civilization.getHappiness());
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

        stringBuilder.append("rank in gold: ").append(gold).append("\n");
        stringBuilder.append("rank in population: ").append(population).append("\n");
        stringBuilder.append("rank in number of combat units: ").append(numberOfCombatUnits).append("\n");
        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    private void showCities() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        stringBuilder.append("capital: ").append(civilization.getCapital() != null ? civilization.getCapital().getName() : " no capital").append("\n");
        for (City city : civilization.getCities()) {
            stringBuilder.append(city.getName()).append("\n");
        }

        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    private void showUnits() {
        Main.getPopup().getContent().clear();
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        stringBuilder.append("all units:").append("\n");
        for (MilitaryUnit militaryUnit : civilization.getMilitaryUnits()) {
            stringBuilder.append(militaryUnit.toString()).append("\n");
        }
        for (Unit unit : civilization.getUnits()) {
            stringBuilder.append(unit.toString()).append("\n");
        }

        stringBuilder.append("choose a unit or exit").append("\n");
        TextField textField = new TextField("number of unit");
        Button button = new Button("ok");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (textField.getText().matches("\\d+")) {
                    String string = textField.getText();
                    if (Integer.parseInt(string) - 1 < civilization.getMilitaryUnits().size())
                        showUnit(civilization.getMilitaryUnits().get(Integer.parseInt(string) - 1));
                    else if (Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1 < civilization.getUnits().size())
                        showUnit(civilization.getUnits().get(Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1));
                    else
                        System.out.println("number out of range!");
                }
            }
        });
        VBox vBox = new VBox(new Label(String.valueOf(stringBuilder)), textField, button);
        vBox.setStyle("-fx-background-color: #da76d6");
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());

    }

    private void showUnit(Unit unit) {
        Main.showPopupJustText("name: " + unit.getName() + "\nx , y: " + unit.getX() + " , " + unit.getY() + "\nmovement: " + unit.getMovement());
    }

    private void showUnit(MilitaryUnit militaryUnit) {
        String string = "name: " + militaryUnit.getName() + "\nx , y: " + militaryUnit.getX() + " , " +
                militaryUnit.getY() + "\ncombat strength: " + militaryUnit.getCombatStrength() +
                "\nrange: " + militaryUnit.getRange() + "\nrange strength: " + militaryUnit.getRangedCombatStrength()
                + "\nmovement: " + militaryUnit.getMovement() + "\nhp: " + militaryUnit.getHp() +
                "\nstate: " + militaryUnit.getState();
        Main.showPopupJustText(string);
    }

    private void showResearch() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = GameController.getInstance().getGame().getCurrentCivilization();
        stringBuilder.append("discovered technologies: " + (civilization.getTechnologies().size() == 0 ? "nothing" : "")).append("\n");
        for (Technology technology : civilization.getTechnologies()) {
            stringBuilder.append(technology.getName()).append("\n");
        }

        stringBuilder.append("detectable technologies: ").append("\n");
        if (civilization.getCurrentTechnology() != null) {
            for (TechnologyEnum technology : Technology.getAllTechnologies()) {
                for (Technology neededTechnology : technology.getNeededTechnologies()) {
                    if (neededTechnology.getName().equals(civilization.getCurrentTechnology().getName()))
                        stringBuilder.append(technology.getName()).append("\n");
                }
            }
        } else
            stringBuilder.append("there is no current technology");
        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    // TODO has unknown bug
    public void move(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("Right") && yMap < 44)
            showMap(xMap, yMap++);
        if (keyEvent.getCode().getName().equals("Left") && yMap > 0)
            showMap(xMap, yMap--);
        if (keyEvent.getCode().getName().equals("Down") && xMap < 29)
            showMap(xMap++, yMap);
        if (keyEvent.getCode().getName().equals("Up") && xMap > 0)
            showMap(xMap--, yMap);
    }

    public void select() {
        pane.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown()) {
                // tile
                if (event.getCode().getName().equals("T")) {
                    TextField textField = new TextField("x");
                    TextField textField1 = new TextField("y");
                    Button button = new Button("ok");
                    button.setOnMouseClicked(event1 -> {
                        Main.getPopup().getContent().clear();
                        if (!(isXTileValid(Integer.parseInt(textField.getText())) || isYTileValid(Integer.parseInt(textField1.getText()))))
                            Main.showPopupJustText("invalid tile");
                        else if (MapController.getInstance().isTerrainVisible(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText()))) {
                            Label label;
                            if (GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource() != null)
                                label = new Label("resources: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource().getName()
                                        + "\ntype: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource().getType());
                            else
                                label = new Label("there is no resource in this tile!");
                            Label label1 = new Label("food: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getFood());
                            Label label2 = new Label("production: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getProduction());
                            Label label3 = new Label("gold: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getGold());
                            Label label4 = new Label("movement point: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMovementCost());
                            Label label5 = new Label("combat modification: " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCombatChange());
                            VBox vBox = new VBox(label, label1, label2, label3, label4, label5);
                            vBox.setStyle("-fx-background-color: #da76d6");
                            Main.getPopup().getContent().clear();
                            Main.getPopup().getContent().add(vBox);
                            Main.getPopup().show(Main.getScene().getWindow());
                        } else
                            Main.showPopupJustText("this tile is not visible for you!");
                    });
                    VBox vBox = new VBox(textField, textField1, button);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }

                // unit
                else if (event.getCode().getName().equals("U")) {
                    TextField textField = new TextField("x");
                    TextField textField1 = new TextField("y");
                    Button button = new Button("ok");
                    button.setOnMouseClicked(event1 -> {
                        Main.getPopup().getContent().clear();
                        if (!(isXTileValid(Integer.parseInt(textField.getText())) || isYTileValid(Integer.parseInt(textField1.getText()))))
                            Main.showPopupJustText("invalid tile");
                        else if (GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMilitaryUnit() != null) {
                            GameController.getInstance().getGame().setSelectedCombatUnit(GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMilitaryUnit());
                            GameController.getInstance().getGame().setSelectedNonCombatUnit(null);
                            Main.showPopupJustText("unit " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMilitaryUnit().getName() + " selected");
                        } else if (GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCivilian() != null) {
                            GameController.getInstance().getGame().setSelectedNonCombatUnit(GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCivilian());
                            GameController.getInstance().getGame().setSelectedCombatUnit(null);
                            Main.showPopupJustText("unit " + GameController.getInstance().getGame().getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCivilian().getName() + " selected");
                        } else
                            Main.showPopupJustText("there is no unit");
                    });
                    VBox vBox = new VBox(textField, textField1, button);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }
            }
        });
    }

    public boolean isXTileValid(int x) {
        return x <= 45 && x >= 0;
    }

    public boolean isYTileValid(int y) {
        return y <= 30 && y >= 0;
    }
}
