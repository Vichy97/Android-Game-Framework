package com.vincent.game.state;

import android.view.MotionEvent;
import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

/**
 * Created by Vincent on 1/18/2015.
 *
 * The methods load() and unload() are used for
 * loading assets used for that particular state
 * and unloading them before proceeding to the next
 * state. If you use load() you must run it in the init()
 * method or nothing will be rendered
 */
public abstract class State {

    public void setCurrentState(State newState) {
        GameMainActivity.sGame.setCurrentState(newState);
    }

    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);

    public void onPause(){}

    public void onResume() {}

    public void load() {}

    public void unload() {}

}
