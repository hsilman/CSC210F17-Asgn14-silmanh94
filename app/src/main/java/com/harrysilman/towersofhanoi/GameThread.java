package com.harrysilman.towersofhanoi;

import android.graphics.Canvas;

/**
 * Created by Silman on 10/24/2017.
 */

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
