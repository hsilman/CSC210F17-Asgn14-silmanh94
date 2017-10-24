package com.harrysilman.towersofhanoi;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * Created by Silman on 10/22/2017.
 */

public class GameAnimationView extends SurfaceView implements SurfaceHolder.Callback {
    public GameAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }


   @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = holder.lockCanvas();
        onDraw(c);
        holder.unlockCanvasAndPost(c);
    }



    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void onDraw(Canvas canvas) {

        int x = getWidth();
        int y = getHeight();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0,0,x / 2, y / 2 ,paint);


    }

    }
