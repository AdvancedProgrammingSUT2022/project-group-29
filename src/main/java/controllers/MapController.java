package controllers;

import enums.TerrainsAndFeaturesEnum;
import models.*;

import java.util.Random;

public class MapController {
    private static MapController instance = null;

    private MapController() {}

    public static MapController getInstance() {
        if (instance == null)
            instance = new MapController();
        return instance;
    }

    void createMap(Tile[][] map) {
        Random random = new Random();
        for (int i = 0; i < GameController.getInstance().getLENGTH(); i++) {
            for (int j = 0; j < GameController.getInstance().getWIDTH(); j++) {
                TerrainAndFeature terrain = addTerrain(random);
                map[i][j] = new Tile(i, j, terrain);
            }
        }
    }

    private TerrainAndFeature addTerrain(Random random) {
        int x = random.nextInt() % 8 + 1;
        if (x == 1)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.DESERT);
        if (x == 2)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.GRASSLAND);
        if (x == 3)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.HILLS);
        if (x == 4)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.MOUNTAIN);
        if (x == 5)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.OCEAN);
        if (x == 6)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.PLAINS);
        if (x == 7)
            return new TerrainAndFeature(TerrainsAndFeaturesEnum.SNOW);
        return new TerrainAndFeature(TerrainsAndFeaturesEnum.TUNDRA);
    }

    public boolean isTerrainVisible(int x, int y) {
        Game game = GameController.getInstance().getGame();
        int LENGTH = GameController.getInstance().getLENGTH();
        int WIDTH = GameController.getInstance().getWIDTH();
        for (City city : game.getCurrentCivilization().getCities()) {
            for (Tile cityTile : city.getCityTiles()) {
                if (x == cityTile.getX() && (y == cityTile.getY() || y == cityTile.getY() + 1 || y == cityTile.getY() - 1))
                    return true;
                if (y == cityTile.getY() && (x == cityTile.getX() || x == cityTile.getX() + 1 || x == cityTile.getX() - 1))
                    return true;
            }
        }
        for (MilitaryUnit militaryUnit : game.getCurrentCivilization().getMilitaryUnits()) {
            if (x == militaryUnit.getX() &&
                    (y == militaryUnit.getY() || y == militaryUnit.getY() + 1 || y == militaryUnit.getY() - 1
                    || y == militaryUnit.getY() + 2 || y == militaryUnit.getY() - 2))
                return true;
            if (y == militaryUnit.getY() &&
                    (x == militaryUnit.getX() || x == militaryUnit.getX() + 1 || x == militaryUnit.getX() - 1
                            || x == militaryUnit.getX() + 2 || x == militaryUnit.getX() - 2))
                return true;
        }

        for (Unit Unit : game.getCurrentCivilization().getUnits()) {
            if (x == Unit.getX() &&
                    (y == Unit.getY() || y == Unit.getY() + 1 || y == Unit.getY() - 1
                            || y == Unit.getY() + 2 || y == Unit.getY() - 2))
                return true;
            if (y == Unit.getY() &&
                    (x == Unit.getX() || x == Unit.getX() + 1 || x == Unit.getX() - 1
                            || x == Unit.getX() + 2 || x == Unit.getX() - 2))
                return true;
        }

        return false;
    }
}
