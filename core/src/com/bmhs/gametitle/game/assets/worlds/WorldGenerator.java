package com.bmhs.gametitle.game.assets.worlds;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.bmhs.gametitle.gfx.assets.tiles.statictiles.WorldTile;
import com.bmhs.gametitle.gfx.utils.TileHandler;

import java.util.logging.FileHandler;


public class WorldGenerator {

    private int worldMapRows, worldMapColumns;

    private int[][] worldIntMap;

    public WorldGenerator (int worldMapRows, int worldMapColumns) {
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


        /*for (int r = 0; r < worldIntMap.length; r++) {
            for (int c = 0; c < worldIntMap[r].length; c++) {
                Vector2 tempVector = new Vector2(c, r);
                if (tempVector.dst(mapseed) < 5) {
                    worldIntMap[(int) mapseed.y][(int) mapseed.x] = 19;
                    worldIntMap[r][c] = 20;
                    if (worldIntMap[(int) mapseed.y][(int) mapseed.x] == 19) {
                        int radius = 1;
                        for (int row = r - radius; row <= r + radius; row++) {
                            for (int col = c - radius; col <= c + radius; col++) {
                                if (row >= 0 && col >= 0 && row <= worldIntMap.length - 1 && col < worldIntMap[r].length - 1) {
                                    worldIntMap[row + 1][col -1] = 14;
                                }
                            }
                        }
                    }
                }
            }
        }*/
    }
        //call methods to build 2D array

        //leftCoast();
        //generateWorldTextFile();

        //Gdx.app.error("WorldGenerator", "WorldGenerator(WorldTile[][][])");


    public void generateIsland() {
        Vector2 seed = new Vector2(MathUtils.random(worldIntMap[0].length), MathUtils.random(worldIntMap.length));
        for(int i = 0; i < 6; i++) {

        }
    }

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
