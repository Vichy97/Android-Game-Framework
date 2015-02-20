package com.vincent.game.state;

import android.view.MotionEvent;

import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.Assets;

/**
 * Created by Vincent on 1/28/2015.
 */
public class LoadPlayState extends State {
    @Override
    public void init() {
        load();
    }

    @Override
    public void update(float delta) {
        unload();
        setCurrentState(new PlayState());
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

    }

    @Override
    public void unload() {
        Assets.unloadBitmap(Assets.menuBackground);
    }
}
