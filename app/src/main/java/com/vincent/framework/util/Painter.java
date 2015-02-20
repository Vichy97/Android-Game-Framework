package com.vincent.framework.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.PictureDrawable;


import com.caverock.androidsvg.SVG;

/**
 * Created by Vincent on 1/18/2015.
 *
 * This class just makes drawing images and shapes easier and
 * more similar to using the Graphics class in java.
 */
public class Painter {

    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;
    private Picture picture;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    //sets the color of the Painter
    public void setColor(int color) {
        paint.setColor(color);
    }

    //set the font and the size of the text
    public void setFont(Typeface typeface, float textSize) {
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    //draws a string at the specified location
    public void drawString(String str, int x, int y) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(str, x, y, paint);
    }

    //draws a rectangle with the specified location and size
    public void fillRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    //draws a rectangle with the same location and size as the
    //Rect parameter
    public void fillRect(Rect rectangle) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle, paint);
    }
    //draws an outline of a rectangle with the specified location and size
    public void drawRect(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width, y + height, paint);
    }
    //draws an outline of a rectangle with the same location and size of the
    //Rect parameter
    public void drawRect(Rect rectangle) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectangle, paint);
    }

    //draws an oval with the specified location and size
    public void fillOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
    //draws an oval with the same location and size of the Rect argument
    public void fillOval(RectF rectangle) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(rectangle, paint);

    }
    //draws an outline of an oval with the specified location and size
    public void drawOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        dstRectF.set(x, y, x +width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
    //draws an outline of an oval with the same location and
    // size of the Rect argument
    public void drawOval(RectF rectangle) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rectangle, paint);

    }

    //method for drawing a bitmap (raster) image
    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    //alternate method for drawing a bitmap that allows you to scale it
    //this method is not recommended because it requires it to be scaled eac time
    //it is drawn. it is better to scale it before hand and use the other method
    public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }

 }
