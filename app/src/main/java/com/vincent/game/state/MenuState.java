package com.vincent.game.state;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.caverock.androidsvg.SVGAndroidRenderer;
import com.vincent.framework.tile.Tile;
import com.vincent.framework.tile.TileMap;
import com.vincent.framework.util.Joystick;
import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.Assets;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;
import com.vincent.simpleandroidgamedevelopmentframework.GameView;

/**
 * Created by Vincent on 1/18/2015.
 *
 * Menu screen for the game
 */


public class MenuState extends State {

    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
       // g.drawImage(Assets.menuBackground, 0, 0, 1920, 1080);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
