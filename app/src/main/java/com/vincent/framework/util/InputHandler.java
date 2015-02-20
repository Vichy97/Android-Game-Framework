package com.vincent.framework.util;

import android.view.MotionEvent;
import android.view.View;

import com.vincent.game.state.State;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

/**
 * Created by Vincent on 1/18/2015.
 *
 * This class handles touch input and State changing
 * Dont add anything to this class! all touch events should be
 * handled in th current State
 */
public class InputHandler implements View.OnTouchListener {

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       //it uses scaledX and Y instead of event.getX or event.getY
       // so there wont be bugs on different screen sizes
       int scaledX = (int) ((event.getX() / v.getWidth()) * GameMainActivity.GAME_WIDTH);
       int scaledY = (int) ((event.getY() / v.getHeight()) * GameMainActivity.GAME_HEIGHT);
       return currentState.onTouch(event, scaledX, scaledY);
    }
}
