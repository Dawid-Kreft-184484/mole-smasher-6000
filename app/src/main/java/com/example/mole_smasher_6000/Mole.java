package com.example.mole_smasher_6000;

import static com.example.mole_smasher_6000.GameView.screenRatioX;
import static com.example.mole_smasher_6000.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.Random;

public class Mole {

    int x,y, width, height;
    float myTimer=-0.1f;
    Bitmap mole,redMole,hole;
    long t0=System.nanoTime();
    int state;
    float displayTime1,displayTime2;
    boolean isDetermined=false;
    boolean displayState=false;

    Mole(int screenY,int screenX, Resources res){
        mole=BitmapFactory.decodeResource(res,R.drawable.mole);
        redMole=BitmapFactory.decodeResource(res,R.drawable.red_mole);
        hole=BitmapFactory.decodeResource(res,R.drawable.hole);

        width=mole.getWidth();
        height=mole.getHeight();

        width/=12;
        height/=12;

        width*=(int)screenRatioX;
        height*=(int)screenRatioY;

        mole= Bitmap.createScaledBitmap(mole,width,height,false);
        redMole= Bitmap.createScaledBitmap(redMole,width,height,false);
        hole= Bitmap.createScaledBitmap(hole,width,height,false);

        y=screenY;

        x=screenX-mole.getWidth()/2;

    }


    Bitmap getMole(){   //\\ //\\rysowanie kretow//\\ //\\

        myTimer+=0.1;
        if(myTimer>=displayTime1){
            displayState=true;
            if(myTimer>=displayTime2){
                myTimer=0;
                isDetermined=false;
            }
            System.out.println(displayTime2);

            if(state==0){
                return  mole;
            }if(state==1){
                return  redMole;
            }else{
                return hole;
            }

        }else{
            System.out.println(displayTime2);
            displayState=false;
            if(!isDetermined){
                state=new Random().nextInt(2);
                displayTime1=new Random().nextInt(8)+4;
                displayTime2=(displayTime1+displayTime1*(new Random().nextInt(75)+1)/100)+10;
                isDetermined=true;

            }

            return hole;

        }


    }

}
