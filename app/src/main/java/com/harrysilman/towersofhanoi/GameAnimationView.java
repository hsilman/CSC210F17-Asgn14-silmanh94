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

public class GameAnimationView extends SurfaceView {
    private GameThread gameThread;
    private SurfaceHolder surfaceHolder;

    public GameAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void init() {

        gameThread = new GameThread(this);

        surfaceHolder = getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceChanged(SurfaceHolder holder,int format, int width,
            int height){

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder){
                gameThread.setRunning(true);
                gameThread.start();
            }


            @Override
            public void surfaceDestroyed(SurfaceHolder holder){
                boolean retry = true;
                gameThread.setRunning(false);
                while (retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e){
                }

            }
        }});
    }

    public void onDraw(Canvas canvas) {

        int x = getWidth();
        int y = getHeight();
        long currentTime = System.currentTimeMillis();


        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, x / 2, y / 2, paint);


    }
}


