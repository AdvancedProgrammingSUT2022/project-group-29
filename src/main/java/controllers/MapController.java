package controllers;

import enums.modelsEnum.TerrainsAndFeaturesEnum;
import models.*;

import enums.modelsEnum.ResourceEnum;
import models.Resource;
import models.TerrainAndFeature;
import models.Tile;

import java.util.ArrayList;
import java.util.Random;

public class MapController {
    private static MapController instance = null;

    private int LENGTH;
    private int WIDTH;

    private MapController() {
    }


    public static MapController getInstance() {
        if (instance == null)
            instance = new MapController();
        return instance;
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

    public void createMap(Tile[][] map, int WIDTH, int LENGTH) {
        this.LENGTH = LENGTH;
        this.WIDTH = WIDTH;
        Random random = new Random();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                map[i][j] = new Tile(i, j);
                if (i < 1 || i > WIDTH - 2 || j < 1 || j > LENGTH - 3)
                    map[i][j].setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.OCEAN));
            }
        }

        for (int i = 1; i < WIDTH - 1; i++) {
            for (int j = 1; j < LENGTH - 2; j++) {
                ArrayList<Tile> tiles = new ArrayList<>();
                if (j % 2 == 1) {
                    tiles.add(map[i - 1][j]);
                    tiles.add(map[i][j + 1]);
                    tiles.add(map[i + 1][j + 1]);
                    tiles.add(map[i + 1][j]);
                    tiles.add(map[i + 1][j - 1]);
                    tiles.add(map[i][j - 1]);
                } else {
                    tiles.add(map[i - 1][j]);
                    tiles.add(map[i - 1][j + 1]);
                    tiles.add(map[i][j + 1]);
                    tiles.add(map[i + 1][j]);
                    tiles.add(map[i][j - 1]);
                    tiles.add(map[i - 1][j - 1]);
                }
                map[i][j].setNeighbourTiles(tiles);
            }
        }

        createOceans(random, map);
        createSnows(random, map);
        createTundras(random, map);
        createPlains(random, map);
        createDesert(random, map);
        createMountains(random, map);
        createHills(random, map);
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain() == null)
                    map[i][j].setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.GRASSLAND));
            }
        }
        increaseTerrainsVariety(random, map);
        setRivers(random, map);
        setFeatures(map, random);
        setResources(map, random);
    }

    private void setRivers(Random random, Tile[][] map) {
        ArrayList<Tile> mountains = getAllOfSimilarTerrains(map, "mountain");
        Tile first, second;
        for (int k = 0; k < 5; k++) {
            int rand = random.nextInt(mountains.size());
            int side = random.nextInt(6);

            first = mountains.get(rand);
            first.addRiver(side);
            second = first.getNeighbourTiles().get(side);
            if (second.getTerrain().getKind().equals("ocean"))
                continue;
            if (side < 3)
                second.addRiver(side + 3);
            else
                second.addRiver(side - 3);

            for (int i = 0; i < 70; i++) {
                if (random.nextBoolean()) {
                    side--;
                    if (side < 0)
                        side += 6;
                } else {
                    if (side < 3)
                        side += 3;
                    else
                        side -= 3;
                    side++;
                    if (side > 5)
                        side -= 6;
                    first = second;

                }
                first.addRiver(side);
                second = first.getNeighbourTiles().get(side);
                if (side < 3)
                    second.addRiver(side + 3);
                else
                    second.addRiver(side - 3);

                int x1 = side - 1;
                if (x1 < 0)
                    x1 += 6 ;

                int x2 = side + 1;
                if (x2 > 5)
                    x2 -= 6 ;

                if (first.getNeighbourTiles().get(x1).getTerrain().getKind().equals("ocean") ||
                        first.getNeighbourTiles().get(x2).getTerrain().getKind().equals("ocean"))
                    break;
            }
        }


    }

    private void setResources(Tile[][] map, Random random) {
        ArrayList<Tile> nullDesert = getAllOfSimilarTerrainsWithFeatures(map, "desert", "0");
        setAmountOfResource(random, nullDesert, 7, ResourceEnum.IRON);
        setAmountOfResource(random, nullDesert, 6, ResourceEnum.GOLD);
        setAmountOfResource(random, nullDesert, 3, ResourceEnum.SILVER);
        setAmountOfResource(random, nullDesert, 5, ResourceEnum.MARBLE);
        setAmountOfResource(random, nullDesert, 4, ResourceEnum.COTTON);
        setAmountOfResource(random, nullDesert, 3, ResourceEnum.INCENSE);
        setAmountOfResource(random, nullDesert, 6, ResourceEnum.SHEEP);

        ArrayList<Tile> nullGrassLand = getAllOfSimilarTerrainsWithFeatures(map, "grassLand", "0");
        setAmountOfResource(random, nullGrassLand, 7, ResourceEnum.IRON);
        setAmountOfResource(random, nullGrassLand, 8, ResourceEnum.COAL);
        setAmountOfResource(random, nullGrassLand, 8, ResourceEnum.HORSES);
        setAmountOfResource(random, nullGrassLand, 10, ResourceEnum.CATTLE);
        setAmountOfResource(random, nullGrassLand, 4, ResourceEnum.GOLD);
        setAmountOfResource(random, nullGrassLand, 3, ResourceEnum.MARBLE);
        setAmountOfResource(random, nullGrassLand, 4, ResourceEnum.COTTON);
        setAmountOfResource(random, nullGrassLand, 8, ResourceEnum.SHEEP);

        ArrayList<Tile> nullHill = getAllOfSimilarTerrainsWithFeatures(map, "hills", "0");
        setAmountOfResource(random, nullHill, 3, ResourceEnum.IRON);
        setAmountOfResource(random, nullHill, 4, ResourceEnum.COAL);
        setAmountOfResource(random, nullHill, 7, ResourceEnum.DEER);
        setAmountOfResource(random, nullHill, 3, ResourceEnum.GOLD);
        setAmountOfResource(random, nullHill, 4, ResourceEnum.SILVER);
        setAmountOfResource(random, nullHill, 2, ResourceEnum.MARBLE);
        setAmountOfResource(random, nullHill, 2, ResourceEnum.SHEEP);


        ArrayList<Tile> nullPlains = getAllOfSimilarTerrainsWithFeatures(map, "plains", "0");
        setAmountOfResource(random, nullPlains, 6, ResourceEnum.IRON);
        setAmountOfResource(random, nullPlains, 6, ResourceEnum.COAL);
        setAmountOfResource(random, nullPlains, 7, ResourceEnum.HORSES);
        setAmountOfResource(random, nullPlains, 5, ResourceEnum.WHEAT);
        setAmountOfResource(random, nullPlains, 4, ResourceEnum.GOLD);
        setAmountOfResource(random, nullPlains, 3, ResourceEnum.MARBLE);
        setAmountOfResource(random, nullPlains, 6, ResourceEnum.IVORY);
        setAmountOfResource(random, nullPlains, 3, ResourceEnum.COTTON);
        setAmountOfResource(random, nullPlains, 2, ResourceEnum.INCENSE);
        setAmountOfResource(random, nullPlains, 4, ResourceEnum.SHEEP);


        ArrayList<Tile> nullSnow = getAllOfSimilarTerrainsWithFeatures(map, "snow", "0");
        setAmountOfResource(random, nullSnow, 5, ResourceEnum.IRON);


        ArrayList<Tile> nullTundra = getAllOfSimilarTerrainsWithFeatures(map, "tundra", "0");
        setAmountOfResource(random, nullTundra, 6, ResourceEnum.IRON);
        setAmountOfResource(random, nullTundra, 7, ResourceEnum.HORSES);
        setAmountOfResource(random, nullTundra, 5, ResourceEnum.DEER);
        setAmountOfResource(random, nullTundra, 4, ResourceEnum.SILVER);
        setAmountOfResource(random, nullTundra, 6, ResourceEnum.MARBLE);


        ArrayList<Tile> forest = getAllOfSimilarTerrainsWithFeatures(map, "0", "forest");
        setAmountOfResource(random, forest, 13, ResourceEnum.DEER);
        setAmountOfResource(random, forest, 14, ResourceEnum.DYES);
        setAmountOfResource(random, forest, 17, ResourceEnum.SILK);


        ArrayList<Tile> jungle = getAllOfSimilarTerrainsWithFeatures(map, "0", "jungle");
        setAmountOfResource(random, jungle, 17, ResourceEnum.BANANA);
        setAmountOfResource(random, jungle, 13, ResourceEnum.DYES);


        ArrayList<Tile> marsh = getAllOfSimilarTerrainsWithFeatures(map, "0", "marsh");
        setAmountOfResource(random, marsh, 5, ResourceEnum.SUGAR);

        ArrayList<Tile> floodPlains = getAllOfSimilarTerrainsWithFeatures(map, "0", "floodPlains");
        setAmountOfResource(random, floodPlains, floodPlains.size() / 3, ResourceEnum.SUGAR);
        setAmountOfResource(random, floodPlains, floodPlains.size() / 3, ResourceEnum.WHEAT);
    }

    private void setAmountOfResource(Random random, ArrayList<Tile> tiles, int size, ResourceEnum resourceEnum) {
        for (int i = 0; i < size; i++) {
            int rand = random.nextInt(tiles.size());
            tiles.get(rand).setResource(new Resource(resourceEnum));
            tiles.remove(rand);
        }
    }

    private ArrayList<Tile> getAllOfSimilarTerrainsWithFeatures(Tile[][] map, String terrainType, String featureType) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if ((terrainType.equals("0") || map[i][j].getTerrain().getKind().equals(terrainType)) && ((featureType.equals("0") && map[i][j].getFeature() == null) ||
                        (map[i][j].getFeature() != null && map[i][j].getFeature().getKind().equals(featureType))))
                    tiles.add(map[i][j]);
            }
        }
        return tiles;
    }

    private void setFeatures(Tile[][] map, Random random) {
        ArrayList<Tile> deserts = getAllOfSimilarTerrains(map, "desert");
        setAmountOfFeatures(random, deserts, 30, TerrainsAndFeaturesEnum.OASIS);

        ArrayList<Tile> grassLands = getAllOfSimilarTerrains(map, "grassLand");
        setAmountOfFeatures(random, grassLands, 25, TerrainsAndFeaturesEnum.MARSH);
        setAmountOfFeatures(random, grassLands, 80, TerrainsAndFeaturesEnum.FOREST);

        ArrayList<Tile> hills = getAllOfSimilarTerrains(map, "hills");
        setAmountOfFeatures(random, hills, 30, TerrainsAndFeaturesEnum.FOREST);
        setAmountOfFeatures(random, hills, 20, TerrainsAndFeaturesEnum.JUNGLE);

        ArrayList<Tile> plains = getAllOfSimilarTerrains(map, "plains");
        setAmountOfFeatures(random, plains, 70, TerrainsAndFeaturesEnum.FOREST);
        setAmountOfFeatures(random, plains, 50, TerrainsAndFeaturesEnum.JUNGLE);

        ArrayList<Tile> tundras = getAllOfSimilarTerrains(map, "tundra");
        setAmountOfFeatures(random, tundras, 40, TerrainsAndFeaturesEnum.FOREST);

        ArrayList<Tile> oceans = getAllOfSimilarTerrains(map, "ocean");
        setAmountOfFeatures(random, oceans, 100, TerrainsAndFeaturesEnum.ICE);
        setFloodPlains(random, map);

    }

    private void setFloodPlains(Random random, Tile[][] map) {
        ArrayList<Tile> tilesToMakeFloodPlains = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain().getKind().equals("desert")) {
                    boolean[] river = map[i][j].getRivers();
                    for (int k = 0; k < 6; k++) {
                        if (river[k]) {
                            tilesToMakeFloodPlains.add(map[i][j]);
                            break;
                        }
                    }
                }
            }
        }
        setAmountOfFeatures(random, tilesToMakeFloodPlains, tilesToMakeFloodPlains.size() / 4, TerrainsAndFeaturesEnum.FLOODPLAINS);
    }

    private void setAmountOfFeatures(Random random, ArrayList<Tile> tiles, int size, TerrainsAndFeaturesEnum featureEnum) {
        for (int i = 0; i < size; i++) {
            int rand = random.nextInt(tiles.size());
            tiles.get(rand).setFeature(new TerrainAndFeature(featureEnum));
            tiles.remove(rand);
        }
    }

    private void increaseTerrainsVariety(Random random, Tile[][] map) {
        ArrayList<Tile> plains = getAllOfSimilarTerrains(map, "plains");

        ArrayList<Tile> deserts = getAllOfSimilarTerrains(map, "desert");

        ArrayList<Tile> grassLands = getAllOfSimilarTerrains(map, "grassLand");

        for (int i = 0; i < 150; i++) {
            int rand = random.nextInt(plains.size());
            if (random.nextBoolean())
                plains.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.GRASSLAND));
            else
                plains.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.DESERT));
            plains.remove(rand);
        }

        for (int i = 0; i < 150; i++) {
            int rand = random.nextInt(deserts.size());
            if (random.nextBoolean())
                deserts.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.GRASSLAND));
            else
                deserts.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.PLAINS));
            deserts.remove(rand);
        }

        for (int i = 0; i < 150; i++) {
            int rand = random.nextInt(grassLands.size());
            if (random.nextBoolean())
                grassLands.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.PLAINS));
            else
                grassLands.get(rand).setTerrain(new TerrainAndFeature(TerrainsAndFeaturesEnum.DESERT));
            grassLands.remove(rand);
        }
    }

    private ArrayList<Tile> getAllOfSimilarTerrains(Tile[][] map, String type) {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain().getKind().equals(type))
                    tiles.add(map[i][j]);
            }
        }
        return tiles;
    }

    private void createHills(Random random, Tile[][] map) {
        ArrayList<Tile> terrainsToMakeHills = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain() == null || map[i][j].getTerrain().getKind().equals("plains") || map[i][j].getTerrain().getKind().equals("desert") ||
                        map[i][j].getTerrain().getKind().equals("tundra"))
                    terrainsToMakeHills.add(map[i][j]);
            }
        }

        for (int i = 0; i < 80; i++) {
            int rand = random.nextInt(terrainsToMakeHills.size());
            Tile selectedTile = terrainsToMakeHills.get(rand);
            makeMountOrHillsLikeNeighbour(selectedTile.getX(), selectedTile.getY(), map, TerrainsAndFeaturesEnum.HILLS);
            terrainsToMakeHills.remove(rand);
        }
    }

    private void createMountains(Random random, Tile[][] map) {
        ArrayList<Tile> terrainsToMakeMountain = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain() == null || map[i][j].getTerrain().getKind().equals("plains") || map[i][j].getTerrain().getKind().equals("desert") ||
                        map[i][j].getTerrain().getKind().equals("tundra"))
                    terrainsToMakeMountain.add(map[i][j]);
            }
        }

        for (int i = 0; i < 60; i++) {
            int rand = random.nextInt(terrainsToMakeMountain.size());
            Tile selectedTile = terrainsToMakeMountain.get(rand);
            makeMountOrHillsLikeNeighbour(selectedTile.getX(), selectedTile.getY(), map, TerrainsAndFeaturesEnum.MOUNTAIN);
            terrainsToMakeMountain.remove(rand);
        }

    }

    private void makeMountOrHillsLikeNeighbour(int x, int y, Tile[][] map, TerrainsAndFeaturesEnum terrainType) {
        Random random = new Random();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (checkIsNotNeighbourTile(x, y, i, j) || map[i][j].getTerrain() != null || random.nextInt(3) < 1)
                    continue;
                map[i][j].setTerrain(new TerrainAndFeature(terrainType));
            }
        }
    }

    private void createDesert(Random random, Tile[][] map) {
        ArrayList<Tile> nullTerrainTiles = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain() == null)
                    nullTerrainTiles.add(map[i][j]);
            }
        }

        createAmountOfNeighbourTile(random, map, nullTerrainTiles, 22, TerrainsAndFeaturesEnum.DESERT);


        for (int k = 0; k < 3; k++) {
            ArrayList<Tile> tilesToMakeDesert = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (map[i][j].getTerrain() == null && (checkNeighbourTile(i, j, "ocean", map) || checkNeighbourTile(i, j, "plains", map) ||
                            checkNeighbourTile(i, j, "desert", map)))
                        tilesToMakeDesert.add(map[i][j]);
                }
            }

            setAmountOfTerrains(random, tilesToMakeDesert, 48, TerrainsAndFeaturesEnum.DESERT);
        }
    }

    private void createPlains(Random random, Tile[][] map) {
        ArrayList<Tile> nullTerrainTiles = new ArrayList<>();
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (map[i][j].getTerrain() == null)
                    nullTerrainTiles.add(map[i][j]);
            }
        }

        createAmountOfNeighbourTile(random, map, nullTerrainTiles, 23, TerrainsAndFeaturesEnum.PLAINS);

        for (int k = 0; k < 4; k++) {
            ArrayList<Tile> tilesToMakePlain = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (map[i][j].getTerrain() == null && (checkNeighbourTile(i, j, "ocean", map) || checkNeighbourTile(i, j, "snow", map) ||
                            checkNeighbourTile(i, j, "tundra", map) || checkNeighbourTile(i, j, "plains", map)))
                        tilesToMakePlain.add(map[i][j]);
                }
            }
            setAmountOfTerrains(random, tilesToMakePlain, 32, TerrainsAndFeaturesEnum.PLAINS);
        }
    }

    private void createAmountOfNeighbourTile(Random random, Tile[][] map, ArrayList<Tile> tiles, int size, TerrainsAndFeaturesEnum terrainsEnum) {
        for (int i = 0; i < size; i++) {
            int rand = random.nextInt(tiles.size());
            Tile selectedTile = tiles.get(rand);
            makeTerrainLikeNeighbour(selectedTile.getX(), selectedTile.getY(), map, terrainsEnum);
            tiles.remove(rand);
        }
    }

    private void createTundras(Random random, Tile[][] map) {
        for (int k = 0; k < 3; k++) {
            ArrayList<Tile> tilesToMakeTundra = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (map[i][j].getTerrain() == null && (checkNeighbourTile(i, j, "ocean", map) || checkNeighbourTile(i, j, "snow", map) ||
                            checkNeighbourTile(i, j, "tundra", map)))
                        tilesToMakeTundra.add(map[i][j]);
                }
            }
            setAmountOfTerrains(random, tilesToMakeTundra, 40, TerrainsAndFeaturesEnum.TUNDRA);
        }
    }

    private void createSnows(Random random, Tile[][] map) {
        for (int k = 0; k < 2; k++) {
            ArrayList<Tile> tilesToMakeSnow = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (map[i][j].getTerrain() == null && (checkNeighbourTile(i, j, "ocean", map) || checkNeighbourTile(i, j, "snow", map)))
                        tilesToMakeSnow.add(map[i][j]);
                }
            }

            setAmountOfTerrains(random, tilesToMakeSnow, 20, TerrainsAndFeaturesEnum.SNOW);
        }
    }

    private void createOceans(Random random, Tile[][] map) {
        int x = random.nextInt(5) + 10;
        int y = random.nextInt(10) + 25;
        makeTerrainLikeNeighbour(x, y, map, TerrainsAndFeaturesEnum.OCEAN);
        x = random.nextInt(5) + 17;
        y = random.nextInt(10) + 10;
        makeTerrainLikeNeighbour(x, y, map, TerrainsAndFeaturesEnum.OCEAN);
        x = random.nextInt(3) + 21;
        y = random.nextInt(8) + 32;
        makeTerrainLikeNeighbour(x, y, map, TerrainsAndFeaturesEnum.OCEAN);

        for (int k = 0; k < 4; k++) {
            ArrayList<Tile> tilesToMakeOcean = new ArrayList<>();
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < LENGTH; j++) {
                    if (map[i][j].getTerrain() == null && checkNeighbourTile(i, j, "ocean", map))
                        tilesToMakeOcean.add(map[i][j]);
                }
            }
            setAmountOfTerrains(random, tilesToMakeOcean, 20, TerrainsAndFeaturesEnum.OCEAN);
        }
    }

    private void setAmountOfTerrains(Random random, ArrayList<Tile> tiles, int size, TerrainsAndFeaturesEnum terrainEnum) {
        for (int i = 0; i < size; i++) {
            int rand = random.nextInt(tiles.size());
            tiles.get(rand).setTerrain(new TerrainAndFeature(terrainEnum));
            tiles.remove(rand);
        }
    }

    private Boolean checkNeighbourTile(int x, int y, String tileType, Tile[][] map) {
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (checkIsNotNeighbourTile(x, y, i, j) || !isValidTile(i, j))
                    continue;
                if (map[i][j].getTerrain() != null && map[i][j].getTerrain().getKind().equals(tileType))
                    return true;
            }
        }
        return false;
    }

    private void makeTerrainLikeNeighbour(int x, int y, Tile[][] map, TerrainsAndFeaturesEnum terrainType) {
        Random random = new Random();
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (checkIsNotNeighbourTile(x, y, i, j) || map[i][j].getTerrain() != null || random.nextInt(5) == 3)
                    continue;
                map[i][j].setTerrain(new TerrainAndFeature(terrainType));
            }
        }
    }

    private Boolean checkIsNotNeighbourTile(int x, int y, int i, int j) {
        return ((y % 2 == 0 && i == x + 1) || (y % 2 == 1 && i == x - 1)) && (j == y + 1 || j == y - 1);
    }

    private Boolean isValidTile(int i, int j) {
        return i >= 0 && i < WIDTH && j >= 0 && j < LENGTH;
    }
}
