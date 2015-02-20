package com.vincent.framework.animation;

import android.graphics.Bitmap;

/**
 * Created by Vincent on 1/19/2015.
 *
 * Animations are made up of a series of frames.
 * Each frame has a duration and an image attached
 */
public class Frame {
    private Bitmap image; //the picture for this frame
    private double duration; //how long this frame is displayed

    public Frame(Bitmap image, double duration) {
        this.image = image;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public Bitmap getImage() {
        return image;
    }
}
