package com.vincent.game.state;

import android.view.MotionEvent;
import com.vincent.framework.util.Painter;

/**
 * Created by Vincent on 1/19/2015.
 *
 * Most of the code for the actual game goes here
 */
public class PlayState extends State {

    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false; //This needs to be set to true if there is touch input
    }
}
