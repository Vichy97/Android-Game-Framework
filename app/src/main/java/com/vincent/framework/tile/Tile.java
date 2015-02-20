package com.vincent.framework.tile;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.drawable.PictureDrawable;

import com.caverock.androidsvg.SVG;

 import com.vincent.framework.util.Painter;
 import com.vincent.simpleandroidgamedevelopmentframework.Assets;
 import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

 /**
 * Created by Vincent on 1/31/2015.
 *
 * This class is for a 2d flat tile used in a top down game
 * as well as other uses such as a puzzle game.
 */
public class Tile {

    //dimensions of the tile
    public static final int WIDTH = 200;
    public static final int HEIGHT = WIDTH / 2;
    public static final int OVERLAP = (int)(HEIGHT * .3); //only used for isometric tiles that overlap

    //type is the kind of tile this is. this determines what image will be used
     //as well as whether it is walkable or not
    private int x, y, type;

    //the image for this tile
    private Bitmap image;

    //whether you can walk on this tile or not. This is mainly only used in
    //a top down tile based game
    private boolean walkable = true;

    //constructor
    public Tile(int type) {
       this.type = type;
    }

    public void renderTile(Painter g) {
         g.drawImage(image, x, y);
    }

    //it sets the image and whether the tile is walkable based on the tile type
    public void setImageAndWalkable() {
        switch (type) {
            case 0: {
               break;
              //example: image = Assets.assetName;
              //walkable = true;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            default: {
                image = null;
                break;
            }
        }
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public boolean getWalkable() {
        return walkable;
    }

    public int getType() {
        return type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }


}
