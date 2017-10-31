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


import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Silman on 10/22/2017.
 */

public class GameAnimationView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;

    // int for number of disks
    private int size = 0;

    // implements the pegs as individual stacks
    private Stack<Tower> A = new Stack<Tower>();
    private Stack<Tower> B = new Stack<Tower>();
    private Stack<Tower> C = new Stack<Tower>();

    public int getSize() {
        return size;
    }

   // implement thread for the animation to run on

    Thread runThread;
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    // constructor for view
    public GameAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder =  getHolder();
        holder.addCallback(this);
    }


    // setSize starts the thread
    public void setSize(final int size) {
        if(runThread != null){
            runThread.interrupt();
        }

        // set the size of the towers and push the Tower objects
        this.size = size;
        A = new Stack<Tower>();
        B = new Stack<Tower>();
        C = new Stack<Tower>();

        // put all the disks on the first tower
        for (int i = size; i > 0; i--) {
            A.push(new Tower(i - 1, size));
        }

        runThread = new Thread(new Runnable() {

            @Override
            public void run() {
                draw(size, A, B, C);
            }
        });
        runThread.start();
    }

    // sets running boolean to true when surface is created
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        running = true;
        gameThread.start();
    }

    // standard surfaceChanged call
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    // stops running the gameThread when app closed
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        running = false;
    }

    // boolean to control thread running
    private boolean running = false;

    Canvas canvas;


    // D.R.Y. clean up code for exception handling
    private void sleepWithErrorCatch(int num) {
        try {
            Thread.sleep(num);
        } catch (InterruptedException e) {
        }
    }

    // game's thread is named
    Thread gameThread = new Thread() {

        @Override
        public void run() {
            super.run();
            while(running){
                drawInitial();
                //sleep .5 second between drawings
                sleepWithErrorCatch(500);
            }
        }
    };

    // initialize variables for tower sizes and position
    float offset = 500f;
    float width = 40;
    float height = 30;
    float addWidth = 20, off_x = -20;
    float init_x = 90, init_y = 180;

    // initialize painter object
    Paint p;

    // starting draw function
    private void drawInitial() {
        canvas = holder.lockCanvas();
        canvas.drawColor(Color.GRAY);
        p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(40);
        canvas.drawText("A", 100, 100, p);
        canvas.drawText("B", 100 + offset, 100, p);
        canvas.drawText("C", 100 + offset * 2, 100, p);

        // show function displays disks/towers
        newDraw(canvas);
        holder.unlockCanvasAndPost(canvas);
    }


    // routine draw function
    private void draw(int towerSize, Stack<Tower> A, Stack<Tower> B,
                      Stack<Tower> C) {
        towerSolve(towerSize, A, B, C);
    }

    //tower solver recursion
    public void towerSolve(int towerSize, Stack<Tower> A, Stack<Tower> B,
                        Stack<Tower> C) {
        if (runThread.isInterrupted()) {
            return;
        }
        if (towerSize == 1) {
            moveDisk(A, C);
            sleepWithErrorCatch(1000);
        } else {
            towerSolve(towerSize - 1, A, C, B);
            moveDisk(A, C);
            sleepWithErrorCatch(1000);
            towerSolve(towerSize - 1, B, A, C);
        }
    }


    // function to move disk from top of one tower to another
    public void moveDisk(Stack<Tower> A, Stack<Tower> B) {
        Tower pop = A.pop();
        B.push(pop);
    }

    public void newDraw(Canvas canvas) {
        for (int i = 0; i < A.size(); i++) {
            int num = A.get(i).getSize();
            canvas.drawRect(init_x + off_x * num, init_y + height * num, init_x
                    + width + addWidth * num, init_y + height * (num + 1), p);
        }
        for (int i = 0; i < B.size(); i++) {
            int num = B.get(i).getSize();
            canvas.drawRect(init_x + off_x * num + offset, init_y + height
                    * num, init_x + width + addWidth * num + offset, init_y
                    + height * (num + 1), p);
        }
        for (int i = 0; i < C.size(); i++) {
            int num = C.get(i).getSize();
            canvas.drawRect(init_x + off_x * num + offset * 2, init_y + height
                    * num, init_x + width + addWidth * num + offset * 2, init_y
                    + height * (num + 1), p);
        }
    }











}


