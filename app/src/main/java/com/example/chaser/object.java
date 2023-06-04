package com.example.chaser;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

import android.os.Looper;
import android.view.View;

import android.os.Handler;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


class objects extends View{
    Paint chaser,runner,obstacle;
    public static int highest,chaserposition=120,runnerpositon=400,
    runnerRadius=60,
    chaserRadius=100,
    runnerY,chaserY,initialRunnerY,initialChaserY;
    public boolean first, gamerunning;
    int blockX=0,blockHeight,jumpedBlocks;
    Random r =new Random( getHeight()/5);






    public objects(Context context) {
        super(context);

        chaser = new Paint();                                                 //chaser paint
        chaser.setColor(Color.BLUE);
        chaser.setStyle(Paint.Style.FILL);

        runner = new Paint();                                                  //runner paint
        runner.setColor(Color.RED);
        runner.setStyle(Paint.Style.FILL);

        obstacle = new Paint();                                               //obstacle paint
        obstacle.setStyle(Paint.Style.FILL);
        obstacle.setColor(Color.BLACK);
    }

    @Override
    protected void
    onDraw (Canvas canvas) {                                                          //on draw method
        super.onDraw(canvas);
        canvas.drawCircle(runnerpositon, getHeight()/2, runnerRadius, runner);
        canvas.drawCircle(chaserposition, getHeight()/2, chaserRadius, chaser);
        canvas.drawRect(blockX+100, getHeight() / 2+200 , blockX, getHeight() / 2-200, obstacle);
initialChaserY=getHeight()/2;
initialRunnerY=getHeight()/2;
        Paint text = new Paint();
        text.setColor(Color.BLACK);
        text.setStyle(Paint.Style.FILL);
        text.setTextSize(60);
        canvas.drawText("Highest Score: " + highest, 50, 50, text);
    }

    public void startgame(){                                                       //initializing variables at the start
        jumpedBlocks=0;
        runnerY = initialRunnerY;
        chaserY = initialChaserY;
        blockX = getWidth();
        blockHeight = r.nextInt();
gamerunning=true;
        first = false;

        Handler handler =new Handler();                                             //handler and runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

        MoveBlock();runnerMove();chaserMove();
        handler.postDelayed(this, 100);


            }
        };
Thread thread =new Thread(runnable);
thread.start();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        runnerY = h - runnerRadius;
        chaserY = h - chaserRadius;
        initialRunnerY = runnerY;
        initialChaserY =chaserY ;
        blockHeight = r.nextInt();

    }





private void MoveBlock(){


        if(gamerunning){
    blockX -= 30;
    if (blockX  < 0&&!first) {
        blockX = getWidth();
        blockHeight =r.nextInt();
        jumpedBlocks++;}
        if (first) {gamerunning=false;
            moveLargeCircle();
        }
    }invalidate();


}
public void moveLargeCircle(){
    Handler ball=new Handler();
        Runnable forward=new Runnable() {
            @Override
            public void run() {

                while(chaserposition<=runnerpositon){chaserposition+=10;
                ball.postDelayed(this,50);
                }
invalidate();

        }};


    }


    public void runnerMove() {
        Thread updown = new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                Handler run = new Handler();
                Runnable up = new Runnable() {
                    @Override
                    public void run() {
                        while (initialRunnerY <= runnerY - 200) {
                            initialRunnerY -= 20;
                            run.postDelayed(this, 56);
                            invalidate();
                        }
                        /*while (initialRunnerY <= runnerY) {
                            initialRunnerY += 20;
                            run.postDelayed(this, 57);
                           postInvalidate();
                        }*/
                    }
                };

                run.postDelayed(up, 0);
                Looper.loop();
            }
        };

        updown.start();
    }

public void chaserMove(){}}
