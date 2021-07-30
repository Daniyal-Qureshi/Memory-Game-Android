package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter=-1;
        win=0;
        imgarray=new int[24];
        Random rand=new Random();

        for (int i=0;i<imgarray.length;i++)
            imgarray[i]=rand.nextInt(3);

        btns=new ImageButton[2];
        imagenames=new String[2];



    }

    public void Change(View view){
        ++counter;
        ImageButton button=(ImageButton)view;
        int tag= Integer.parseInt(button.getTag().toString());

        btns[counter]=button;

        if(imgarray[tag]==0) {
            button.setImageResource(R.drawable.b);
//            images.put(""+tag,"b");
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
            TextView txt=findViewById(R.id.text);
            txt.setText("You win");

        }
    }

}