package com.harrysilman.towersofhanoi;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Paint;


public class GameElement {
    protected GameAnimationView view; // The view that contains this element
    protected Paint paint = new Paint(); // Paint to draw game elements
    protected Rect shape; // each element's rectangular bounds

    // public constructor
    public GameElement(GameAnimationView view, int color, int x, int y, int width,
                       int length) {
        this.view = view;
        paint.setColor(color);
        shape = new Rect(x, y, x + width, y + length); // set bounds
    }

    // draws this GameElement on the given canvas
    public void draw(Canvas canvas) {
        canvas.drawRect(shape, paint);
    }
}
