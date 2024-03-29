package com.bmhs.gametitle.game.assets.worlds;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bmhs.gametitle.gfx.assets.tiles.statictiles.WorldTile;
import com.bmhs.gametitle.gfx.utils.TileHandler;

import java.util.logging.FileHandler;


public class WorldGenerator {

    private int worldMapRows, worldMapColumns;
    int a = 0;

    private int[][] worldIntMap;

    public WorldGenerator(int worldMapRows, int worldMapColumns) {
        this.worldMapRows = worldMapRows;
        this.worldMapColumns = worldMapColumns;


        worldIntMap = new int[worldMapRows][worldMapColumns];

        Vector2 mapseed = new Vector2(MathUtils.random(worldIntMap[0].length), MathUtils.random(worldIntMap.length));
        System.out.println(mapseed.y + " " + mapseed.x);

        worldIntMap[(int) mapseed.y][(int) mapseed.x] = 20;

        for (int r = 0; r < worldIntMap.length; r++) {
            for (int c = 0; c < worldIntMap[r].length; c++) {
                worldIntMap[r][c] = 21;
            }
        }

        generateIslands(5);
        generateIslands(6);
        generateIslands(5);

    }


    public void generateIslands(int num) {
        if (num > 1) {
            int y = MathUtils.random(0, worldIntMap.length);
            int x = MathUtils.random(0, worldIntMap[0].length);
            for (int i = 0; i < num; i++) {
                generateIsland(x + MathUtils.random(-5,5), y + MathUtils.random(-5,5));
            }
        }
    }
    public void generateIsland(int x, int y) {

        Vector2 mapseed = new Vector2(x, y);
        for (int rad = 6; rad > 0; rad--) {
            for (int r = 0; r < worldIntMap.length; r++) {
                for (int c = 0; c < worldIntMap[r].length; c++) {
                    Vector2 tempVector = new Vector2(c, r);
                    int radius = 1;
                    if (rad == 6) {
                        radius = MathUtils.random(5, 6);
                    } else {
                        radius = MathUtils.random(1, rad);
                    }
                    if (r >= 0 && c >= 0 && r <= worldIntMap.length - 1 && c < worldIntMap[r].length - 1) {
                        if (tempVector.dst(mapseed) < radius * 2) {
                            if (rad == 6 && worldIntMap[r][c] == 21) {
                                worldIntMap[r][c] = 16;

                            }
                            if (rad == 5 && worldIntMap[r][c] != 16 && worldIntMap[r][c] == 8) {
                                worldIntMap[r][c] = 8;
                            }
                            if (rad == 4) {
                                worldIntMap[r][c] = 8;
                            }
                            if (rad == 3) {
                                worldIntMap[r][c] = 7;
                            }
                            if (rad == 2) {
                                worldIntMap[r][c] = 6;
                            }
                            if (rad == 1) {
                                worldIntMap[r][c] = 5;
                                worldIntMap[(int) mapseed.y][(int) mapseed.x] = 5;
                                int n = MathUtils.random(1, 4);
                                if (n == 1) {
                                    worldIntMap[((int) mapseed.y) + 1][((int) mapseed.x)] = 5;
                                    worldIntMap[((int) mapseed.y) + 2][((int) mapseed.x) - 1] = 5;
                                }
                                if (n == 2) {
                                    worldIntMap[((int) mapseed.y) - 2][((int) mapseed.x)] = 5;
                                    worldIntMap[((int) mapseed.y) - 1][((int) mapseed.x) + 2] = 5;
                                }
                                if (n == 3) {
                                    worldIntMap[((int) mapseed.y) + 3][((int) mapseed.x)] = 5;
                                } else {
                                    worldIntMap[((int) mapseed.y)][((int) mapseed.x) + 3] = 5;
                                    worldIntMap[((int) mapseed.y)][((int) mapseed.x) - 3] = 5;
                                    worldIntMap[((int) mapseed.y) + 2][((int) mapseed.x)] = 5;
                                }
                            }


                        }
                    }
                }
            }
        }
    }






        //call methods to build 2D array

        //leftCoast();
        //generateWorldTextFile();

        //Gdx.app.error("WorldGenerator", "WorldGenerator(WorldTile[][][])");


    /*public void generateIsland() {
        Vector2 seed = new Vector2(MathUtils.random(worldIntMap[0].length), MathUtils.random(worldIntMap.length));

        for(int r = 0; r < worldIntMap.length; r++)
        for(int c = 0; c < worldIntMap[r].length ; c++) {
                Vector2 tempVector1 = new Vector2(c,r);
                if (tempVector1.dst(seed) < radius) {
                    worldIntMap[(int) seed.y][(int) seed.x] = 19;
                    worldIntMap[r][c] = 5;


                }
            }

        }*/

    public String getWorld3DArrayToString() {
        String returnString = "";

        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                returnString += worldIntMap[r][c] + " ";
            }
            returnString += "\n";
        }

        return returnString;
    }

    public void leftCoast() {
        for (int r = 0; r < worldIntMap.length; r++) {
            for (int c = 0; c < worldIntMap[r].length; c++) {
                if(c < 1 || c > worldIntMap[r].length - 2 || r < 1  || r > worldIntMap.length - 2) {
                    worldIntMap[r][c] = 0;
                }
            }
        }
    }
    public void randomize() {
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                    int n = MathUtils.random(0,1);
                if (n == 0 && r < worldIntMap.length - 2) {
                    worldIntMap[r][c] = 19;
                    worldIntMap[r + 1][c] = 20;
                }
                else {
                    worldIntMap[r][c] = 20;
                }
            }
        }
    }
    public WorldTile[][] generateWorld() {
        WorldTile[][] worldTileMap = new WorldTile[worldMapRows][worldMapColumns];
        for(int r = 0; r < worldIntMap.length; r++) {
            for(int c = 0; c < worldIntMap[r].length; c++) {
                worldTileMap[r][c] = TileHandler.getTileHandler().getWorldTileArray().get(worldIntMap[r][c]);
            }
        }
        return worldTileMap;
    }

    private void generateWorldTextFile() {
        FileHandle file = Gdx.files.local("assets/world/world.txt");
        file.writeString(getWorld3DArrayToString(), false);
    }

}
