package com.vincent.framework.util;

import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.caverock.androidsvg.SVG;
import com.vincent.simpleandroidgamedevelopmentframework.Assets;

/**
 * Created by Vincent on 1/19/2015.
 *
 * This class is used to display a joystick on the screen
 */
public class Joystick {

    //x and y are the x and y coordinate of the nob not the whole joystick
    private int x, y, nobWidth, radius;

    private float angle, distance, deltaX, deltaY, deltaAngle;

    private Point center; //center of the joystick

    private boolean touch = false; //whether your finger is on the screen

    private Rect bounds;//rectangular bounds for the joystick to be drawn in

    //constructor, it has no height parameter because the joystick should be a square
    public Joystick(int x, int y, int width) {

        radius = width / 2;
        bounds = new Rect(x - radius, y - radius, x + width - radius, y + width - radius);

        this.x = bounds.left + (width / 5);
        this.y = bounds.top + (width / 5);
        this.nobWidth = (int) (width * .6);

        center = new Point(bounds.centerX(), bounds.centerY());
    }

    //handles touch events, call this in the current states onTouch method
    public  void onTouch(MotionEvent e, int scaledX, int scaledY) {
        //x1 - x2 is a value used in calculating angle and distance
        deltaX = (center.x - scaledX);
        deltaY = (center.y - scaledY);

        //angle between center point and touch point
        angle = (float)calculateAngle(deltaX, deltaY);
        //distance between center point and touch point
        distance = (float)Math.hypot(deltaX, deltaY);

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (distance <= radius) {
                x = scaledX - (nobWidth / 2);
                y = scaledY - (nobWidth / 2);
                touch = true;

              //this is so you dont need to touch exactly on the joystick to
              //activate it. this can be adjusted depending on you preferences
            } else if (distance <= bounds.width()) {
                x = scaledX - (nobWidth / 2);
                y = scaledY - (nobWidth / 2);
                //deltaAngle is here just to reduce the number of calculations that have
                //to be made each time onTouch is called. (calculated once and used twice)
                deltaAngle = (float)Math.toRadians(180 + calculateAngle(deltaX, deltaY));
                touch = true;
                x = (int) (Math.cos(deltaAngle) * radius + bounds.left - (nobWidth / 2) + radius);
                y = (int) (Math.sin(deltaAngle) * radius + bounds.top - (nobWidth / 2) + radius);
            }
        } else if (e.getAction() == MotionEvent.ACTION_MOVE && touch) {
            if (distance <= radius) {
                x = scaledX - (nobWidth / 2);
                y = scaledY - (nobWidth / 2);
           } else if (distance >= radius) {

                 x = (int) (Math.cos(Math.toRadians(180 + calculateAngle(deltaX, deltaY))) * radius + bounds.left - (nobWidth / 2) + radius);
                 y = (int) (Math.sin(Math.toRadians(180 + calculateAngle(deltaX, deltaY))) * radius + bounds.top - (nobWidth / 2) + radius);
        }
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            resetNob();
        }

    }

    //used to render the joystick, call this in the current states render method
    public void renderJoystick(Painter g) {
        g.drawImage(Assets.joystickOutline, bounds.left, bounds.top, bounds.width(), bounds.width());
        g.drawImage(Assets.joystickNob, x, y, nobWidth, nobWidth);
    /*  g.drawRect(bounds);
        g.drawRect(x, y, nobWidth, nobWidth);
        g.setFont(Typeface.SANS_SERIF, 60);
        g.setColor(Color.WHITE);
        g.drawString("distance: " + distance, 100, 100);
        g.drawString("deltaX: " + deltaX, 100, 180);
        g.drawString("deltaY: " + deltaY, 100, 260);
        g.drawString("angle: " + angle, 100, 340);
        g.drawString("fourDirection: " + getFourDirection(), 100, 420);
        g.drawString("eightDirection: " + getEightDirection(), 100, 480);

        this can be used for testing purposes so i commented it out
        instead of deleting it
     */

    }

    //when you let go it resets the nob position and all of the values
    public void resetNob() {
        touch = false;
        angle = 0f;
        distance = 0f;
        deltaX = 0f;
        deltaY = 0f;
        x = bounds.left + (bounds.width() / 5);
        y = bounds.top + (bounds.width() / 5);
    }

    //for making the joystick return 4 different directions based
    //on the position. ie up, down, left, right
    public byte getFourDirection() {
        if (touch) {
            if (angle >= 45 && angle < 135) {
                return 1;
            } else if (angle >= 135 && angle < 225) {
                return 2;
            } else if (angle >= 225 && angle < 315) {
                return 3;
            } else if ((angle >= 315 && angle < 360) || (angle >= 0 && angle < 45)) {
                return 4;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    //for making the return the 4 normal direction(up, down, left, right) in
    //addition to the 4 angles (uppper left, upper right, lower left, lower right)
    public byte getEightDirection() {
        if (touch) {
            if (angle >= 67.5 && angle < 112.5) {
                return 1;
            } else if (angle >= 112.5 && angle < 157.5) {
                return 2;
            } else if (angle >= 157.5 && angle < 202.5) {
                return 3;
            } else if (angle >= 202.5 && angle < 247.5) {
                return 4;
            } else if (angle >= 247.5 && angle < 292.5) {
                return 5;
            } else if (angle >= 292.5 && angle < 337.5) {
                return 6;
            } else if ((angle >= 337.5 && angle < 360) || (angle >= 0 && angle < 22.5)) {
                return 7;
            } else if (angle >= 22.5 && angle < 67.5) {
                return 8;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    //used to calculate the angle between two points
    public double calculateAngle(float x, float y) {
        if(x >= 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x));
        else if(x < 0 && y >= 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x < 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 180;
        else if(x > 0 && y < 0)
            return Math.toDegrees(Math.atan(y / x)) + 360;
        return 0;
    }


}
