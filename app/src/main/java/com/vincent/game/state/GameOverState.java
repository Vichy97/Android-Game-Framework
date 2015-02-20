package com.vincent.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.vincent.framework.util.Painter;
import com.vincent.simpleandroidgamedevelopmentframework.GameMainActivity;

/**
 * Created by Vincent on 1/18/2015
 */
public class GameOverState extends State {

    private String playerScore;

    public GameOverState(int playerScore) {
        this.playerScore = "" + playerScore;
    }

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
        return false;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }
}
