package com.vincent.framework.animation;

import com.vincent.framework.util.Painter;

/**
 * Created by Vincent on 1/19/2015.
 *
 * The boolean looping determines if the animation will keep looping
 * If you want a non-looping animation to disappear after the last frame
 * just add a blank frame at the end. I will update this later so that a
 * blank frame is not required along with other improvements
 */

public class LoopingAnimation {
    private Frame[] frames;
    private double[] frameEndTimes;
    private int currentFrameIndex = 0;
    private double totalDuration = 0;
    private double currentTime = 0;

    private boolean looping = true;

    //this can take any number of frames and they will be displayed in the order they are added
    public LoopingAnimation(Boolean looping, Frame... frames) {
        this.looping = looping;
        this.frames = frames;
        frameEndTimes = new double[frames.length];
        for (int i = 0; i < frames.length; i++) {
            Frame f = frames[i];
            totalDuration += f.getDuration();
            frameEndTimes[i] = totalDuration;
        }
    }

    public synchronized void update(float increment) {
        currentTime += increment;
        if (currentTime > totalDuration && looping) {
            wrapAnimation();
        }
        if (currentFrameIndex < frameEndTimes.length) {
            while (currentTime > frameEndTimes[currentFrameIndex]) {
                currentFrameIndex++;
            }
        }
    }

    //restart the Animation
    private synchronized void wrapAnimation() {
        currentFrameIndex = 0;
        currentTime %= totalDuration;
    }

    public synchronized void render(Painter g, int x, int y) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y);
    }

    public synchronized void render(Painter g, int x, int y, int width, int height) {
        g.drawImage(frames[currentFrameIndex].getImage(), x, y, width, height);
    }
}
