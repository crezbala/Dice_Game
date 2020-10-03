package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class game2 extends AppCompatActivity {
    public static final Random RANDOM = new Random();
    private Button player1,player2,quit;
    private ImageView imageView1, imageView2;
    TextView play1,play2,result,mv1,mv2;
    int roll;
    int point_1=0;
    int point_2=0;
    int count_1,count_2=0;
    ConstraintLayout constraint;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        constraint=(ConstraintLayout)findViewById(R.id.cons);
        player2 = (Button) findViewById(R.id.rollDices);
        player1=(Button)findViewById(R.id.rollDices2);
        quit=(Button)findViewById(R.id.button2);
        play1=(TextView)findViewById(R.id.textView9);
        play2=(TextView)findViewById(R.id.textView10);
        mv1=(TextView)findViewById(R.id.textView8);
        mv2=(TextView)findViewById(R.id.textView12);
        result=(TextView)findViewById(R.id.textView11);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        player1.setEnabled(true);
        player2.setEnabled(false);

        Bundle bundle=getIntent().getExtras();
        roll= Integer.parseInt(bundle.get("rolls").toString());
        String theme=bundle.get("theme").toString();
        System.out.println(roll);
        System.out.println(theme);


        if(theme.equals("Dark")){
            System.out.println("dark theme");
            constraint.setBackgroundColor(Color.BLACK);

        }else if(theme.equals("Blue")){
            System.out.println("Bright theme");
            constraint.setBackgroundColor(Color.BLUE);
        }

        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value1 = randomDiceValue();

                count_1=count_1+1;
                System.out.println("player1 clicked"+count_1);
                int rem=roll-count_1;
                mv1.setText(""+rem);

                if(count_1 ==roll ) {
                    System.out.println("inside counter_1 iff loop  "+roll);
                    player1.setEnabled(false);

                    player2.setEnabled(true);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something here
                            player2.performClick();

                        }
                    }, 3000);



                }
                else{
                    player1.setEnabled(false);
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something here
                            player2.performClick();
                        }
                    }, 3000);
                }

                play1.setText("ROLL");


                if(value1==1) {
                    imageView1.setImageResource(R.drawable.dice_1);
                    point_1 = 1 + point_1;

                }
                else if(value1==2) {
                    imageView1.setImageResource(R.drawable.dice_2);
                    point_1 = 2 + point_1;
                }
                else if(value1==3) {
                    imageView1.setImageResource(R.drawable.dice_3);
                    point_1 = 3 + point_1;
                }
                else if(value1==4) {
                    imageView1.setImageResource(R.drawable.dice_4);
                    point_1 = 4 + point_1;
                }
                else if(value1==5) {
                    imageView1.setImageResource(R.drawable.dice_5);
                    point_1 = 5 + point_1;
                }
                else if(value1==6) {
                    imageView1.setImageResource(R.drawable.dice_6);
                    point_1 = 6 + point_1;
                }


                play1.setText(""+point_1);


                return;
            }
        });


        player2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                count_2=count_2+1;
                //System.out.println("player2 clicked"+count_2);
                int rem=roll-count_2;
                mv2.setText(""+rem);
                // for giving equal number of tries
                if(count_2 ==roll ) {
                    //   System.out.println("inside counter_2 iff loop"+roll);
                    player1.setEnabled(false);
                    player2.setEnabled(false);

                }
                else{
                    player2.setEnabled(false);
                    player1.setEnabled(true);
                }

                int value2 = randomDiceValue();//picking up a random value less than 6
                //adding images according to the numbers
                if(value2==1) {
                    imageView2.setImageResource(R.drawable.dice_1);
                    point_2 = point_2 + 1;
                }
                else if(value2==2) {
                    imageView2.setImageResource(R.drawable.dice_2);
                    point_2 = point_2 + 2;
                }
                else if(value2==3) {
                    imageView2.setImageResource(R.drawable.dice_3);
                    point_2 = point_2 + 3;
                }
                else if(value2==4) {
                    imageView2.setImageResource(R.drawable.dice_4);
                    point_2 = point_2 + 4;
                }
                else if(value2==5) {
                    imageView2.setImageResource(R.drawable.dice_5);
                    point_2 = point_2 + 5;
                }
                else if(value2==6) {
                    imageView2.setImageResource(R.drawable.dice_6);
                    point_2 = point_2 + 6;
                }
                play2.setText(""+point_2);
                return;


            }
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(point_1 > point_2){
                    result.setText("player1 Won ");
                }
                else{
                    result.setText("player2 Won ");
                }
            }
        });

    }
    public static int randomDiceValue() {
        return RANDOM.nextInt(6) + 1;
    }
}
