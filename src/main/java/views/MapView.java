package views;

import app.Main;
import controllers.*;
import enums.modelsEnum.TechnologyEnum;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import models.*;
import models.diplomacy.Trade;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class MapView implements Initializable {
    public Pane pane;
    private static int xMap = 0, yMap = 0;
    private static final Game game = GameController.getInstance().getGame();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMap(xMap, yMap);
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
                                    game.getMap()[x + i][y + j].getTerrain().getKind() + ".png")
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

                if (MapController.getInstance().isTerrainVisible(x + i, y + j)) {

                    // feature
                    if (game.getMap()[x + i][y + j].getFeature() != null) {
                        ImageView imageView1 = new ImageView(new Image(Main.class.getResource("/assets/featureTexture/" +
                                        game.getMap()[x + i][y + j].getFeature().getKind() + ".png")
                                .toExternalForm()));
                        imageView1.setX(j * 75 + 50);
                        imageView1.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView1.setFitHeight(120);
                        imageView1.setFitWidth(100);
                        pane.getChildren().add(imageView1);
                    }

                    // ruin
                    for (Tile ruin : game.getRuins()) {
                        if (ruin.getX() == x + i
                                && ruin.getY() == y + j) {
                            ImageView imageView1;
                            if (ruin.isDiscoveredRuin())
                                imageView1 = new ImageView(new Image(Main.class.getResource("/assets/ruin.png").toExternalForm()));
                            else
                                imageView1 = new ImageView(new Image(Main.class.getResource("/assets/ruin1.png").toExternalForm()));
                            imageView1.setX(j * 75 + 50);
                            imageView1.setY(i * 120 + 60 + (j % 2) * 60);
                            imageView1.setFitHeight(60);
                            imageView1.setFitWidth(60);
                            pane.getChildren().add(imageView1);
                        }
                    }

                    // unit
                    if (game.getMap()[x + i][y + j].getMilitaryUnit() != null &&
                            game.getMap()[x + i][y + j].getCivilian() != null) {
                        ImageView imageView2 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                game.getMap()[x + i][y + j].getCivilian().getName() + ".png").toExternalForm()));
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                game.getMap()[x + i][y + j].getMilitaryUnit().getName() + ".png").toExternalForm()));
                        imageView2.setFitWidth(50);
                        imageView2.setFitHeight(40);
                        imageView3.setFitWidth(50);
                        imageView3.setFitHeight(40);
                        imageView2.setX(j * 75 + 50);
                        imageView2.setY(i * 120 + 60 + (j % 2) * 60 - 20);
                        imageView2.setX(j * 75 + 50);
                        imageView2.setY(i * 120 + 60 + (j % 2) * 60 + 20);
                    } else if (game.getMap()[x + i][y + j].getMilitaryUnit() != null &&
                            game.getMap()[x + i][y + j].getCivilian() == null) {
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/warrior.png").toExternalForm()));
                        imageView3.setX(j * 75 + 50);
                        imageView3.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView3.setFitHeight(120);
                        imageView3.setFitWidth(100);
                        pane.getChildren().add(imageView3);
                    } else if (game.getMap()[x + i][y + j].getMilitaryUnit() == null &&
                            game.getMap()[x + i][y + j].getCivilian() != null) {
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/Units/" +
                                game.getMap()[x + i][y + j].getCivilian().getName() + ".png").toExternalForm()));
                        imageView3.setX(j * 75 + 50);
                        imageView3.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView3.setFitHeight(120);
                        imageView3.setFitWidth(100);
                        pane.getChildren().add(imageView3);
                    }

                    // building
                    if (game.getMap()[x + i][y + j].getBuilding() != null) {
                        ImageView imageView3 = new ImageView(new Image(Main.class.getResource("/assets/BuildingIcons/" + game.getMap()[x + i][y + j].getBuilding().getName() + ".png").toExternalForm()));
                        imageView3.setX(j * 75 + 50);
                        imageView3.setY(i * 120 + 60 + (j % 2) * 60);
                        imageView3.setFitHeight(60);
                        imageView3.setFitWidth(70);
                        pane.getChildren().add(imageView3);
                    }

                    // TODO city
                    outer:
                    for (Civilization civilization : game.getCivilizations()) {
                        for (City city : civilization.getCities()) {
                            if (city.getX() == x + i && city.getY() == y + j) {
                                ImageView imageView2 = new ImageView(new Image(Main.class.getResource("/assets/city.png").toExternalForm()));
                                imageView2.setX(j * 75 + 50);
                                imageView2.setY(i * 120 + 60 + (j % 2) * 60);
                                imageView2.setFitHeight(70);
                                imageView2.setFitWidth(70);
                                imageView2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
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
                                        button.setOnMouseClicked(event12 -> Main.showPopupJustText(CityController.getInstance().createUnit(textField.getText())));
                                    }
                                    Label label7 = new Label("\nyou can not create\nnon military unit");
                                    TextField textField1 = new TextField();
                                    Button button1 = new Button("\nok");
                                    textField1.setDisable(true);
                                    if (city.getCivilian() == null) {
                                        textField1.setDisable(false);
                                        label7 = new Label("\ncreate unit");
                                        button1.setOnMouseClicked(event13 -> Main.showPopupJustText(CityController.getInstance().createUnit(textField1.getText())));
                                    }
                                    Label label8 = new Label("\nbuy tile");
                                    TextField textFieldX = new TextField("x");
                                    TextField textFieldY = new TextField("y");
                                    Button button2 = new Button("ok");
                                    button2.setOnMouseClicked(event14 -> {
                                        if (textFieldX.getText().matches("\\d+") && textFieldY.getText().matches("\\d+"))
                                            Main.showPopupJustText(CityController.getInstance().cityBuyTile(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText())));
                                    });
                                    Label label9 = new Label("attack");
                                    TextField textFieldX1 = new TextField("x");
                                    TextField textFieldY1 = new TextField("y");
                                    Button button3 = new Button("ok");
                                    button3.setOnMouseClicked(event1 -> {
                                        if (textFieldX.getText().matches("\\d+") && textFieldY.getText().matches("\\d+"))
                                            Main.showPopupJustText(CityController.getInstance().cityAttack(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText())));
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
                                                    Main.showPopupJustText(CityController.getInstance().createBuilding(building, city));
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


                }
            }
        }

        showBar();
    }

    private void moveUnit() {
        Main.getPopup().getContent().clear();
        TextField textFieldX = new TextField("x");
        TextField textFieldY = new TextField("y");
        Button button1 = new Button("ok");
        button1.setOnMouseClicked(event1 ->
                Main.showPopupJustText(UnitController.getInstance().moveUnit(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()))));
        VBox vBox = new VBox(textFieldX, textFieldY, button1);
        vBox.setStyle("-fx-background-color: #da76d6");
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());
    }

    private void showBar() {
        Label label = new Label("gold: " + game.getCurrentCivilization().getGold());
        label.setLayoutX(10);
        label.setLayoutY(10);
        Label label1 = new Label("happiness: " + game.getCurrentCivilization().getHappiness());
        label1.setLayoutX(70);
        label1.setLayoutY(10);
        Label label2 = new Label("science: " + game.getCurrentCivilization().getScience());
        label2.setLayoutX(165);
        label2.setLayoutY(10);
        Label label3 = new Label("current technology: " + (game.getCurrentCivilization().getCurrentTechnology() == null ? "nothing" : game.getCurrentCivilization().getCurrentTechnology()));
        label3.setLayoutX(240);
        label3.setLayoutY(10);
        Label label4 = new Label("selected unit: " + func());
        label4.setLayoutX(440);
        label4.setLayoutY(10);

        Circle technologyPic = new Circle(60, 90, 50, Color.WHITE);
        if (game.getCurrentCivilization().getCurrentTechnology() != null)
            technologyPic.setFill(new ImagePattern(new Image(Main.class.getResource("/assets/technology/" +
                    game.getCurrentCivilization().getCurrentTechnology().getName() + ".png").toExternalForm())));
        technologyPic.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//            if (game.getCurrentCivilization().getTechnologies().size() > 0) {
//                Label label5 = new Label("last technology: " + game.getCurrentCivilization().getTechnologies().get(game.getCurrentCivilization().getTechnologies().size() - 1).getName() + "\n");
//            }
            VBox vBox = new VBox();
            Button button = new Button("tech tree");
            button.setOnMouseClicked(event13 -> {
                Stage stage = new Stage();
                ScrollPane scrollPane = (ScrollPane) Main.loadFXML("techPage");
                stage.setScene(new Scene(scrollPane));
                stage.show();
            });
            ArrayList<Label> labels = new ArrayList<>();
            for (Technology technology : game.getCurrentCivilization().getAvailableTechnology()) {
                Label label6 = new Label(technology.getName());
                labels.add(label6);
                label6.addEventHandler(MouseEvent.MOUSE_ENTERED, event1 -> {
                    StringBuilder stringBuilder = new StringBuilder();
                    ArrayList<Technology> technologies = new ArrayList<>();
                    for (TechnologyEnum allTechnology : Technology.getAllTechnologies()) {
                        for (Technology neededTechnology : allTechnology.getNeededTechnologies()) {
                            if (neededTechnology.getName().equals(technology.getName())) {
                                technologies.add(new Technology(allTechnology));
                                break;
                            }
                        }
                    }
                    for (Technology technology1 : technologies) {
                        stringBuilder.append(technology1.getName()).append("\n");
                    }
                    Label label7 = new Label("\ntype: " + technology.getType() + "\nneeded for:\n" + stringBuilder);
                    vBox.getChildren().add(label7);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                });
                label6.addEventHandler(MouseEvent.MOUSE_CLICKED, event12 -> {
                    if (event12.getClickCount() == 2) {
                        if (game.getCurrentCivilization().getCurrentTechnology() == null)
                            Main.showPopupJustText(GameController.getInstance().technologyStudy(technology.getName()));
                        else
                            Main.showPopupJustText(GameController.getInstance().technologyChange(technology.getName()));
                    }
                });
            }

            Main.getPopup().getContent().clear();
            vBox.getChildren().add(button);
            vBox.getChildren().addAll(labels);
            vBox.setStyle("-fx-background-color: #da76d6");
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        });
        pane.getChildren().add(label);
        pane.getChildren().add(label1);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(label4);
        pane.getChildren().add(technologyPic);
    }

    private String func() {
        if (game.getSelectedNonCombatUnit() != null)
            return game.getSelectedNonCombatUnit().getName();
        if (game.getSelectedCombatUnit() != null)
            return game.getSelectedCombatUnit().getName();
        return "nothing";
    }

    public void research() {
        showInfo("info research");
    }

    public void units() {
        showInfo("info units");
    }

    public void cities() {
        showInfo("info cities");
    }

    public void demographics() {
        showInfo("info demographics");
    }

    public void notifications() {
        showInfo("info notifications");
    }

    public void military() {
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
    }

    private void showEconomic() {
        Label label = new Label("declare war");
        ArrayList<String> strings = new ArrayList<>();
        for (Civilization civilization : game.getCivilizations()) {
            if (!civilization.leaderName().equals(game.getCurrentCivilization().leaderName()))
                strings.add(civilization.leaderName());
        }
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(strings);
        Button button = new Button("ok");
        button.setOnMouseClicked(event -> Main.showPopupJustText(CityController.getInstance().declareWar(choiceBox.getValue())));
        Label label1 = new Label("\npeace");
        Button button1 = new Button("ok");
        if (game.getWar().get(game.getCurrentCivilization().leaderName()) == null) {
            label1.setText(label1.getText() + " you are not in war");
            button1.setDisable(true);
        } else {
            label1.setText(label1.getText() + " with " + game.getWar().get(game.getCurrentCivilization().leaderName()));
            button1.setDisable(false);
            button1.setOnMouseClicked(event -> CityController.getInstance().peace(game.getCurrentCivilization().leaderName()));
        }
        Label label2 = new Label("\nsend trade");
        ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
        choiceBox1.getItems().addAll(strings);
        TextField resource = new TextField("\nresource name");
        TextField gold = new TextField("\nsuggested gold");
        Button button2 = new Button("ok");
        button2.setOnMouseClicked(event -> {
            if (choiceBox1.getValue() != null && gold.getText().matches("\\d+"))
                Main.showPopupJustText(CityController.getInstance().createTrade(choiceBox1.getValue(), "", Integer.parseInt(gold.getText()), resource.getText()));
            else
                Main.showPopupJustText("something went wrong");
        });
        Label label3 = new Label("\ntrade offers");
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        ArrayList<String> strings1 = new ArrayList<>();
        for (Trade trade : game.getCurrentCivilization().getAllTrades()) {
            strings1.add("id: " + trade.getId() + " resource asked: " + trade.getResourceName() + "\nciv name: " + trade.getGuestName() + " money offered: " + trade.getSuggestedGold());
        }
        choiceBox2.getItems().addAll(strings1);
        Button accept = new Button("accept");
        Button reject = new Button("reject");
        accept.setOnMouseClicked(event -> {
            if (choiceBox2.getValue() != null)
                CityController.getInstance().acceptTrade(Integer.parseInt(choiceBox2.getValue().split(" ")[1]));
        });
        reject.setOnMouseClicked(event -> {
            if (choiceBox2.getValue() != null)
                CityController.getInstance().rejectTrade(Integer.parseInt(choiceBox2.getValue().split(" ")[1]));
        });
        Button button3 = new Button("enter chat");
        button3.setOnMouseClicked(event -> {
            Label label4 = new Label("choose civ to chat");
            ChoiceBox<String> choiceBox3 = new ChoiceBox<>();
            choiceBox3.getItems().addAll(strings);
            TextField textField = new TextField("text");
            Button button4 = new Button("send");
            button4.setOnMouseClicked(event1 -> {
                if (choiceBox3.getValue() != null && !choiceBox3.getValue().matches(".*-.*")) {
                    game.getCivilizationByName(choiceBox3.getValue()).getMessages().add(game.getCurrentCivilization().leaderName() + "-" + textField.getText());
                    textField.setText("");
                } else Main.showPopupJustText("invalid format");
            });
            Button button5 = new Button("view messages from: ");
            ChoiceBox<String> choiceBox4 = new ChoiceBox<>();
            choiceBox4.getItems().addAll(strings);
            button5.setOnMouseClicked(event12 -> {
                if (choiceBox4.getValue() != null) {
                    ArrayList<String> messages = new ArrayList<>();
                    for (String message : game.getCurrentCivilization().getMessages())
                        if (message.split("-")[0].equals(choiceBox4.getValue()))
                            messages.add(message);
                    Label label5 = new Label("\n");
                    for (String message : messages) {
                        label5.setText(label5.getText() + message + "\n");
                    }
                    Main.getPopup().getContent().clear();
                    VBox vBox = new VBox(label5);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }
            });
            Main.getPopup().getContent().clear();
            VBox vBox = new VBox(label4, choiceBox3, textField, button4, button5, choiceBox4);
            vBox.setStyle("-fx-background-color: #da76d6");
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        });

        Main.getPopup().getContent().clear();
        VBox vBox = new VBox(label, choiceBox, button, label1, button1, label2, choiceBox1, resource, gold, button2, label3, choiceBox2, accept, reject, button3);
        vBox.setStyle("-fx-background-color: #da76d6");
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());
    }

    private void showMilitary() {
        showUnits();
    }

    private void showNotifications() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = game.getCurrentCivilization();
        for (String notification : civilization.getNotifications())
            stringBuilder.append(notification).append("\n");
        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    private void showDemographics() {
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = game.getCurrentCivilization();
        stringBuilder.append("population: ").append(civilization.calculatePopulation()).append("\ngold: ").append(civilization.getGold()).append("\nnumber of combat units: ").append(civilization.getMilitaryUnits().size()).append("\nhappiness: ").append(civilization.getHappiness());
        int population = 1, numberOfCombatUnits = 1, gold = 1;
        ArrayList<Civilization> arrayList = game.getCivilizations();
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
        Civilization civilization = game.getCurrentCivilization();
        stringBuilder.append("capital: ").append(civilization.getCapital() != null ? civilization.getCapital().getName() : " no capital").append("\n");
        for (City city : civilization.getCities()) {
            stringBuilder.append(city.getName()).append("\n");
        }

        Main.showPopupJustText(String.valueOf(stringBuilder));
    }

    private void showUnits() {
        Main.getPopup().getContent().clear();
        StringBuilder stringBuilder = new StringBuilder();
        Civilization civilization = game.getCurrentCivilization();
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
        button.setOnMouseClicked(event -> {
            if (textField.getText().matches("\\d+")) {
                String string = textField.getText();
                if (Integer.parseInt(string) - 1 < civilization.getMilitaryUnits().size())
                    showUnit(civilization.getMilitaryUnits().get(Integer.parseInt(string) - 1));
                else if (Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1 < civilization.getUnits().size())
                    showUnit(civilization.getUnits().get(Integer.parseInt(string) - civilization.getMilitaryUnits().size() - 1));
                else
                    Main.showPopupJustText("out of range!");
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
        Civilization civilization = game.getCurrentCivilization();
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
                            Label label = new Label("there is no resource in this tile!");

                            if (game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource() != null)
                                label = new Label("resources: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource().getName()
                                        + "\ntype: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getResource().getType());
                            Label label1 = new Label("food: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getFood());
                            Label label2 = new Label("production: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getProduction());
                            Label label3 = new Label("gold: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getGold());
                            Label label4 = new Label("movement point: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMovementCost());
                            Label label5 = new Label("combat modification: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCombatChange());
                            Label label6 = new Label("terrain: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getTerrain().getKind());
                            Label label7 = new Label("feature: " + (game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getFeature() == null ? "nothing" : game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getFeature().getKind()));
                            Label label8 = new Label("has road: " + game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].isHasRoute());
                            VBox vBox = new VBox(label, label1, label2, label3, label4, label5, label6, label7);
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
                        else if (game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMilitaryUnit() != null
                                && game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCivilian() != null) {
                            Label label = new Label("select military or nonmilitary");
                            Button button1 = new Button("military");
                            Button button2 = new Button("nonmilitary");
                            button1.setOnMouseClicked(event2 -> GameMenuController.getInstance().selectCombatUnit(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                            button2.setOnMouseClicked(event3 -> GameMenuController.getInstance().selectNonCombatUnit(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                            VBox vBox = new VBox(label, button1, button2);
                            vBox.setStyle("-fx-background-color: #da76d6");
                            Main.getPopup().getContent().clear();
                            Main.getPopup().getContent().add(vBox);
                            Main.getPopup().show(Main.getScene().getWindow());
                        } else if (game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getMilitaryUnit() != null) {
                            Main.showPopupJustText(GameMenuController.getInstance().selectCombatUnit(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                            showMap(xMap, yMap);
                        } else if (game.getMap()[Integer.parseInt(textField.getText())][Integer.parseInt(textField1.getText())].getCivilian() != null) {
                            Main.showPopupJustText(GameMenuController.getInstance().selectNonCombatUnit(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                            showMap(xMap, yMap);
                        } else
                            Main.showPopupJustText("there is no unit");
                    });
                    VBox vBox = new VBox(textField, textField1, button);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }

                // locking citizen
                else if (event.getCode().getName().equals("L") && !event.isShiftDown()) {
                    Label label = new Label("locking citizen");
                    TextField textField = new TextField("x");
                    TextField textField1 = new TextField("y");
                    Button button = new Button("ok");

                    button.setOnMouseClicked(event12 -> {
                        Main.getPopup().getContent().clear();
                        Main.showPopupJustText(CityController.getInstance().lockingCitizenToTile(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                    });
                    Main.getPopup().getContent().clear();
                    VBox vBox = new VBox(label, textField, textField1, button);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }

                // unlock
                else if (event.getCode().getName().equals("L")) {
                    Label label = new Label("unlocking citizen");
                    TextField textField = new TextField("x");
                    TextField textField1 = new TextField("y");
                    Button button = new Button("ok");

                    button.setOnMouseClicked(event12 -> {
                        Main.getPopup().getContent().clear();
                        Main.showPopupJustText(CityController.getInstance().removingCitizenFromWork(Integer.parseInt(textField.getText()), Integer.parseInt(textField1.getText())));
                    });
                    VBox vBox = new VBox(label, textField, textField1, button);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }

                // save
                else if (event.getCode().getName().equals("S")) {
                    GameController.getInstance().saveGame();
                    Main.showPopupJustText("game saved!");
                }
            }

            // unit action
            else if (event.getCode().getName().equals("S") && !event.isShiftDown()) {
                unitAction();
            }

            // close game
            else if (event.getCode().equals(KeyCode.ESCAPE)) {
                GameController.getInstance().saveGame();
                Main.changeMenu("mainPage");
            }
        });
    }

    private void unitAction() {
        if (game.getSelectedCombatUnit() != null) {
            Main.getPopup().getContent().clear();
            Label label = new Label("choose unit action");
            TextField textField = new TextField();
            Button button = new Button("ok");
            button.setOnMouseClicked(event -> {
                if (textField.getText().equals("move")) {
                    moveUnit();
                } else if (textField.getText().equals("sleep"))
                    Main.showPopupJustText(UnitController.getInstance().unitSleep());
                else if (textField.getText().equals("wake"))
                    Main.showPopupJustText(UnitController.getInstance().unitWake());
                else if (textField.getText().equals("alert"))
                    Main.showPopupJustText(UnitController.getInstance().unitAlert());
                else if (textField.getText().equals("garrison"))
                    Main.showPopupJustText(UnitController.getInstance().unitGarrison());
                else if (textField.getText().equals("fortify"))
                    Main.showPopupJustText(UnitController.getInstance().unitFortify());
                else if (textField.getText().equals("setup"))
                    Main.showPopupJustText(UnitController.getInstance().readySiege());
                else if (textField.getText().equals("delete"))
                    Main.showPopupJustText(UnitController.getInstance().deleteUnit());
                else if (textField.getText().equals("attack")) {
                    unitAttack();
                } else
                    Main.showPopupJustText("invalid input");
            });
            Label label1 = new Label("name: " + game.getSelectedCombatUnit().getName()
                    + "\nhp: " + game.getSelectedCombatUnit().getHp() + "\nmelee attack: "
                    + game.getSelectedCombatUnit().getCombatStrength() + "\nrange attack: "
                    + game.getSelectedCombatUnit().getRangedCombatStrength() + "\n");
            VBox vBox = new VBox(label1, label, textField, button);
            vBox.setStyle("-fx-background-color: #da76d6");
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        } else if (game.getSelectedNonCombatUnit() != null) {
            Main.getPopup().getContent().clear();
            Label label = new Label("choose unit action");
            Label label1 = new Label("build <improvement> - build city - clear lands\nbuild road to route - repair - move");
            TextField textField = new TextField();
            Button button = new Button("ok");
            button.setOnMouseClicked(event -> {
                if (textField.getText().equals("move"))
                    moveUnit();
                else if (textField.getText().equals("build city")) {
                    Main.getPopup().getContent().clear();
                    TextField textFieldName = new TextField("name");
                    Button button1 = new Button("ok");
                    button1.setOnMouseClicked(event1 -> {
                        if (!textFieldName.getText().equals(""))
                            Main.showPopupJustText(CityController.getInstance().createCity(textFieldName.getText()));
                    });
                    VBox vBox = new VBox(textFieldName, button1);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                } else if (textField.getText().matches("build \\S+"))
                    Main.showPopupJustText(UnitController.getInstance().buildImprovement(textField.getText().split("")[1]));
                else if (textField.getText().equals("clear lands"))
                    Main.showPopupJustText(UnitController.getInstance().removeJungle());
                else if (textField.getText().equals("build road to route"))
                    Main.showPopupJustText(UnitController.getInstance().createRoute());
                else if (textField.getText().matches("repair"))
                    Main.showPopupJustText(UnitController.getInstance().repair());
                else
                    Main.showPopupJustText("invalid input");
            });
            Label label2 = new Label("name: " + game.getSelectedNonCombatUnit().getName()
                    + "\n");
            VBox vBox = new VBox(label2, label, label1, textField, button);
            vBox.setStyle("-fx-background-color: #da76d6");
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        }
    }

    private void unitAttack() {
        TextField textFieldX = new TextField("x");
        TextField textFieldY = new TextField("y");
        Button button = new Button("ok");
        button.setOnMouseClicked(event -> {
            if (textFieldX.getText().matches("\\d+") && textFieldY.getText().matches("\\d+")) {
                String s = (GameController.getInstance().combat(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText())));
                if (s.equals("you are not in war with this civilization")) {
                    Label label = new Label("you are not in war with this civilization");
                    Button button1 = new Button("start war");
                    button1.setOnMouseClicked(event1 -> {
                        GameController.getInstance().startWar(Integer.parseInt(textFieldX.getText()), Integer.parseInt(textFieldY.getText()));
                        Main.showPopupJustText("war started!");
                    });
                    VBox vBox = new VBox(label, button1);
                    vBox.setStyle("-fx-background-color: #da76d6");
                    Main.getPopup().getContent().clear();
                    Main.getPopup().getContent().add(vBox);
                    Main.getPopup().show(Main.getScene().getWindow());
                }
            } else
                Main.showPopupJustText("invalid input!");
        });
        VBox vBox = new VBox(textFieldX, textFieldY, button);
        vBox.setStyle("-fx-background-color: #da76d6");
        Main.getPopup().getContent().clear();
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());
    }

    public boolean isXTileValid(int x) {
        return x <= 45 && x >= 0;
    }

    public boolean isYTileValid(int y) {
        return y <= 30 && y >= 0;
    }

    public synchronized void setting() {
        Button button = new Button("mute");
        button.setOnMouseClicked(event -> {
            if (button.getText().equals("mute")) button.setText("unmute");
            else button.setText("mute");
            //Main.getMediaPlayer().setMute(!Main.getMediaPlayer().isMute());
        });
        Button button1 = new Button("guide");
        button1.setOnMouseClicked(event1 -> {
            Label label = new Label("lock citizen: ctrl + l");
            Label label1 = new Label("\nunlock citizen: ctrl + shift + l");
            Label label2 = new Label("\nselect tile: ctrl + t");
            Label label3 = new Label("\nselect unit: ctrl + u");

            VBox vBox = new VBox(label, label1, label2, label3);
            vBox.setStyle("-fx-background-color: #da76d6");
            Main.getPopup().getContent().clear();
            Main.getPopup().getContent().add(vBox);
            Main.getPopup().show(Main.getScene().getWindow());
        });
        Button button2 = new Button("auto save: on");
        button2.setOnMouseClicked(event -> {
            if (button2.getText().endsWith("on")) {
                button2.setText("auto save: off");
                GameController.getInstance().getThread().notify();
            } else {
                button2.setText("auto save: on");
                try {
                    GameController.getInstance().getThread().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        VBox vBox = new VBox(button, button1);
        vBox.setStyle("-fx-background-color: #da76d6");
        Main.getPopup().getContent().clear();
        Main.getPopup().getContent().add(vBox);
        Main.getPopup().show(Main.getScene().getWindow());
    }

    public void nextTurn() {
        GameController.getInstance().cheatTurn(1);
    }
}
