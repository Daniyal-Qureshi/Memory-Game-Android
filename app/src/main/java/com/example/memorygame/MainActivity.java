package com.example.memorygame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amrdeveloper.lottiedialog.LottieDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialog;
import com.shashank.sony.fancygifdialoglib.FancyGifDialogListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    int[] imgarray;
    int counter;

    ImageButton[] btns;
    String[] imagenames;
    int win;
    TextView timer;


    public  void gameStatus(String msg,int gif){

        LottieDialog dialog = new LottieDialog(this)
                .setAnimation(gif)
                .setAnimationRepeatCount(LottieDialog.INFINITE)
                .setAutoPlayAnimation(true)
                .setDialogBackground(R.color.black)
                .setMessage(msg)
                .setMessageColor(R.color.my)
                .setMessageTextSize(20)
                .setMessageColor(getResources().getColor(R.color.my))
                .setDialogWidthPercentage(0.70f)
                .setDialogHeightPercentage(0.60f)

                .setOnDismissListener((DialogInterface.OnDismissListener) view -> {

                   finish();
                    Intent intent = new Intent(MainActivity.this,MainActivity.class);
                    startActivity(intent);


                })
                ;


        dialog.show();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=-1;
        win=0;
        imgarray=new int[24];
        Random rand=new Random();
        timer=findViewById(R.id.timer);
        for (int i=0;i<imgarray.length;i++)
            imgarray[i]=rand.nextInt(3);

        btns=new ImageButton[2];
        imagenames=new String[2];

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Time Remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                gameStatus("Oops Time out Try Again !",R.raw.lose);
            }
        }.start();
    }

    public void Change(View view){

        ++counter;
        ImageButton button=(ImageButton)view;
        int tag= Integer.parseInt(button.getTag().toString());

        btns[counter]=button;

        if(imgarray[tag]==0) {
            button.setImageResource(R.drawable.b);
            imagenames[counter]="b";
        }


        else if(imgarray[tag]==1) {
            button.setImageResource(R.drawable.c);
            imagenames[counter]="c";

        }

        else {
            button.setImageResource(R.drawable.d);
            imagenames[counter]="d";
           }

        if(counter==1)
        {
            counter=-1;
            if(imagenames[0]==imagenames[1]) {
                Log.i("win", "you win");
            btns[0].setEnabled(false);
            btns[1].setEnabled(false);
            win+=2;
            }

            else
            {
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        btns[0].setImageResource(R.drawable.a);
                        btns[1].setImageResource(R.drawable.a);

                    }
                },500);


            }

        }

        if(win==24||win==22){
          gameStatus("Congratulations, You won !",R.raw.tropy);
        }
    }

}