package com.vincent.framework.tile;

import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by Vincent on 1/31/2015.
 *
 * This class is used for loading 2d arrays of tiles into memory
 * and displaying them as a grid. this can be used for isometric tiles
 * or top down tiles
 */
public class TileMap {

  //converts a text file into a 2d array of Tiles and loads it into the memory
  public static Tile[][] loadTileMap(String fileName, int height, int width) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(GameMainActivity.assets.open(fileName)));
        Tile[][] tileArray = new Tile[width][height];
        String line;
        int type;

      for (int i = 0; i < width; i++) {
            line = reader.readLine();
            if (line == null) {
                reader.close();
            }
            for (int j = 0; j < height; j++) {
                type = Integer.parseInt(line.substring(j, j + 1));
                tileArray[i][j] = new Tile(type);
                tileArray[i][j].setImageAndWalkable();
                tileArray[i][j].setX(Tile.WIDTH * j);
                tileArray[i][j].setY(Tile.WIDTH * i);
            }
        }
        return tileArray;
    }

    public static Tile[][] loadIsoTileMap(String fileName, int height, int width) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(GameMainActivity.assets.open(fileName)));
        Tile[][] tileArray = new Tile[width][height];
        String line;
        int type;

        for (int i = 0; i < width; i++) {
            line = reader.readLine();
            if (line == null) {
                reader.close();
            }
            for (int j = 0; j < height; j++) {
                type = Integer.parseInt(line.substring(j, j + 1));
                tileArray[i][j] = new Tile(type);
                tileArray[i][j].setImageAndWalkable();
                tileArray[i][j].setX((j * (Tile.WIDTH / 2)) - (i * (Tile.WIDTH / 2)) + (GameMainActivity.GAME_WIDTH / 2) - (Tile.WIDTH / 2));
                tileArray[i][j].setY((j * (Tile.HEIGHT / 2)) + (i * (Tile.HEIGHT / 2)));
            }
        }
        return tileArray;
    }

    //renders a 2d array of Tiles
    public static void renderTileMap(Painter g, Tile[][] tileArray) {
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length; j++) {
                if (tileArray[i][j].getY() < GameMainActivity.GAME_HEIGHT && tileArray[i][j].getX() < GameMainActivity.GAME_WIDTH) {
                    tileArray[i][j].renderTile(g);
                }
            }
        }
    }

}
