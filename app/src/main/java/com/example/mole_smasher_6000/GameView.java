package com.example.mole_smasher_6000;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class GameView extends SurfaceView implements Runnable{
    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    public static float screenRatioX, screenRatioY;
    private Background background1, background2;
    private Paint paint;
    private  Mole mole1,mole2,mole3,mole4,mole5,mole6;
    private double time;

    private int moleHits, redMoleHits;

    private int state;
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX=screenX;
        this.screenY=screenY;

        screenRatioX=1080f/screenX;
        screenRatioY=1920f/screenY;

        background1=new Background(screenX,screenY,getResources());
        background2=new Background(screenX,screenY,getResources());


        mole1=new Mole(screenY/2, (int) (screenX*0.2),getResources());
        mole2=new Mole(screenY/2,(int) (screenX*0.5),getResources());
        mole3=new Mole(screenY/2,(int) (screenX*0.8),getResources());
        mole4=new Mole((int) (screenY/1.35),(int) (screenX*0.2),getResources());
        mole5=new Mole((int) (screenY/1.35),(int) (screenX*0.5),getResources());
        mole6=new Mole((int) (screenY/1.35),(int) (screenX*0.8),getResources());

        background2.x=screenX;

        paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(100);

        time=System.nanoTime();


    }

    @Override
    public void run() {
        while(isPlaying){
            update();
            draw();
            sleep();
        }
    }

    private void update(){
        background1.x-=10*screenRatioX;
        background2.x-=10*screenRatioX;

        if(background1.x+background1.background.getWidth()<0){
            background1.x=screenX;
        }

        if(background2.x+background2.background.getWidth()<0){
            background2.x=screenX;
        }

        if((System.nanoTime()-time)/1000000000>300){

            isPlaying=false;

        }

    }

    private void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas   =getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x,background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x,background2.y, paint);

            canvas.drawBitmap(mole1.getMole(),mole1.x,mole1.y,paint);
            canvas.drawBitmap(mole2.getMole(),mole2.x,mole2.y,paint);
            canvas.drawBitmap(mole3.getMole(),mole3.x,mole3.y,paint);
            canvas.drawBitmap(mole4.getMole(),mole4.x,mole4.y,paint);
            canvas.drawBitmap(mole5.getMole(),mole5.x,mole5.y,paint);
            canvas.drawBitmap(mole6.getMole(),mole6.x,mole6.y,paint);

            canvas.drawText(String.valueOf(moleHits)+"|"+String.valueOf(redMoleHits),screenX/2,screenY/3,paint);

            canvas.drawText(String.format("%.0f:%.0f",(float)(int)((((System.nanoTime()-time)/1000000000) % 3600) / 60),(float)(int)(((System.nanoTime()-time)/1000000000)%60)),screenX/2,screenY/10,paint);


            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        isPlaying=true;
        thread=new Thread(this);
        thread.start();
    }

    public void pause(){
        try {
            isPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        float x=event.getX();
        float y = event.getY();
        System.out.println("weeeeee");



        if(y<=(3*screenY)/4){
            if(x<=screenX/3){
                if(mole1.displayState){
                    if(mole1.state==0){
                        moleHits++;
                    }else if(mole1.state==1){
                        redMoleHits++;
                    }
                    mole1.state=2;
                    mole1.myTimer=0;
                    mole1.isDetermined=false;
                }


            }
            if(x>(screenX)/3 && x<=(2*screenX)/3){
                if(mole2.displayState){
                    if(mole2.state==0){
                        moleHits++;
                    }else if(mole2.state==1){
                        redMoleHits++;
                    }
                    mole2.state=2;
                    mole2.myTimer=0;
                    mole2.isDetermined=false;

                }
            }if(x>(2*screenX)/3){
                if(mole3.displayState){
                    if(mole3.state==0){
                        moleHits++;
                    }else if(mole3.state==1){
                        redMoleHits++;
                    }
                    mole3.state=2;
                    mole3.myTimer=0;
                    mole3.isDetermined=false;
                }
            }
        }if(y>(3*screenY)/4){
            if(x<=screenX/3){

                if(mole4.displayState){
                    if(mole4.state==0){
                        moleHits++;
                    }else if(mole4.state==1){
                        redMoleHits++;
                    }
                    mole4.state=2;
                    mole4.myTimer=0;
                    mole4.isDetermined=false;
                }
            }
            if(x>(screenX)/3 && x<=(2*screenX)/3){
                if(mole5.displayState){
                    if(mole5.state==0){
                        moleHits++;
                    }else if(mole5.state==1){
                        redMoleHits++;
                    }
                    mole5.state=2;
                    mole5.myTimer=0;
                    mole5.isDetermined=false;
                }
            }if(x>(2*screenX)/3){
                if(mole6.displayState){
                    if(mole6.state==0){
                        moleHits++;
                    }else if(mole6.state==1){
                        redMoleHits++;
                    }
                    mole6.state=2;
                    mole6.myTimer=0;
                    mole6.isDetermined=false;
                }
            }
        }


        return true;
    }
}
