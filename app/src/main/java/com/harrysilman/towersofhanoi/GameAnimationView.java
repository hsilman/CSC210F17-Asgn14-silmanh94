package com.harrysilman.towersofhanoi;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by Silman on 10/22/2017.
 */

public class GameAnimationView extends SurfaceView {
    private GameThread gameThread;
    private SurfaceHolder surfaceHolder;

    List<String> diskMovesList = MainActivity.returnList();

    ArrayList<Integer>[] pegs = new ArrayList[3];

    public GameAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {

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

        Paint paint = new Paint();


        // paint the three pegs as boxes
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawRect(5,getBottom(),x/3,0,paint);
        canvas.drawRect(x/3,getBottom(),(x/3)+(x/3),0,paint);
        canvas.drawRect(2*(x/3),getBottom(),x,0,paint);


    }

    public class GameThread extends Thread {

        GameAnimationView gameAnimation;
        private boolean running = false;

        public GameThread(GameAnimationView view) {
            gameAnimation = view;
        }

        public void setRunning(boolean run) {
            running = run;
        }

        @Override
        public void run() {
            while(running){


                Canvas canvas = gameAnimation.getHolder().lockCanvas();

                if(canvas != null){
                    synchronized (gameAnimation.getHolder()){
                        gameAnimation.onDraw(canvas);
                    }
                    gameAnimation.getHolder().unlockCanvasAndPost(canvas);
                }

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}


