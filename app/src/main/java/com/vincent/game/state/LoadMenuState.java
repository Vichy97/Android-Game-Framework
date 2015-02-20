package com.vincent.game.state;

import android.view.MotionEvent;

import com.caverock.androidsvg.SVG;
import com.vincent.framework.tile.Tile;
import com.vincent.framework.tile.TileMap;
import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.Assets;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

import java.io.IOException;

/**
 * Created by Vincent on 1/18/2015.
 */
public class LoadMenuState extends State {
    @Override
    public void init() {
        load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState());
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }

    @Override
    public void load() {
      Assets.menuBackground = Assets.loadBitmap("menuBackground.png", true);

    }

}
