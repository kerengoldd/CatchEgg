package com.example.kerengold.catchegg;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView hand;
    private ImageView goldegg;
    private ImageView egg;
    private ImageView brokenegg;
    private int frameHeight;
    private int handSize;
    private int screenHeight;
    private int screenWidth;

    private int handY;
    private int eggX;
    private int eggY;
    private int goldeggX;
    private int goldeggY;
    private int brokeneggX;
    private int brokeneggY;
    private int score = 0;

    private int handSpeed;
    private int eggSpeed;
    private int goldeggSpeed;
    private int brokeneggSpeed;


    //initialize class
    private Handler hendler = new Handler();
    private Timer timer = new Timer();
    private sound sound;

    private boolean action_flg = false;
    private boolean start_flg = false;
    public static Button btn;


    public void dialogevent(View view){

        btn = (Button)findViewById(R.id.dial);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder altdial = new AlertDialog.Builder(main.this);
                altdial.setMessage("Do you want to Quit this app ???").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = altdial.create();
                alert.setTitle("Dialog Header");
                alert.show();

            }
        });}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new sound(this);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        hand = (ImageView) findViewById(R.id.hand);
        goldegg = (ImageView) findViewById(R.id.egg);
        egg = (ImageView) findViewById(R.id.goldegg);
        brokenegg = (ImageView) findViewById(R.id.brokenegg);


            WindowManager wm = getWindowManager();
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            screenWidth = size.x;
            screenHeight = size.y;

            handSpeed = Math.round(screenHeight / 80F);
            eggSpeed = Math.round(screenWidth / 80F);
            goldeggSpeed = Math.round(screenWidth / 76F);
            brokeneggSpeed = Math.round(screenWidth / 65F);


            egg.setX(-80);
            egg.setY(-80);
            goldegg.setX(-80);
            goldegg.setY(-80);
            brokenegg.setX(-80);
            brokenegg.setY(-80);
            scoreLabel.setText("score : 0");

        }


    public void changePos() {

        hit();

        eggX -= eggSpeed;
        if (eggX < 0){
            eggX = screenWidth + 10;
            eggY = (int) Math.floor(Math.random() * (frameHeight - egg.getHeight()));
        }
        egg.setX(eggX);
        egg.setY(eggY);

        brokeneggX -= brokeneggSpeed;
        if (brokeneggX < 0){
            brokeneggX = screenWidth + 10;
            brokeneggY = (int) Math.floor(Math.random() * (frameHeight - brokenegg.getHeight()));
        }
        brokenegg.setX(brokeneggX);
        brokenegg.setY(brokeneggY);


        goldeggX -= goldeggSpeed;
        if (goldeggX < 0){
            goldeggX = screenWidth + 300;
            goldeggY = (int) Math.floor(Math.random()*(frameHeight-goldegg.getHeight()));
        }
        goldegg.setX(goldeggX);
        goldegg.setY(goldeggY);

        if (action_flg == true) {
            handY -= handSpeed;
        } else {
            handY += handSpeed;
        }
        if (handY < 0) handY = 0;
        if (handY > frameHeight - handSize) handY = frameHeight - handSize;
        hand.setY(handY);
        scoreLabel.setText("score : "+ score);

    }
    public void hit(){

        int eggCenterX = eggX + egg.getWidth()/2;
        int eggCenterY = eggY + egg.getHeight()/2;

        if(0 <= eggCenterX && eggCenterX <= handSize &&
                handY <= eggCenterY && eggCenterY <= handY+handSize) {
            score += 30;
            eggX = -10;
            sound.playHitSound();
        }

        int goldeggCenterX = goldeggX + goldegg.getWidth()/2;
        int goldeggCenterY = goldeggY + goldegg.getHeight()/2;

        if(0 <= goldeggCenterX && goldeggCenterX <= handSize &&
                handY <= goldeggCenterY && goldeggCenterY <= handY+handSize){
            score += 10;
            goldeggX = -10;
            sound.playHitSound();
    }
        int brokeneggCenterX = brokeneggX + brokenegg.getWidth()/2;
        int brokeneggCenterY = brokeneggY + brokenegg.getHeight()/2;

        if (0 <= brokeneggCenterX && brokeneggCenterX <= handSize &&
                handY <= brokeneggCenterY && brokeneggCenterY <= handY + handSize){
            timer.cancel();
            timer = null;
            sound.playFailSound();


            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

    }

    public boolean onTouchEvent(MotionEvent me) {
        if (start_flg == false) {
            start_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);

            frameHeight = frame.getHeight();

            handY = (int)hand.getY();

            handSize = hand.getHeight();


            startLabel.setVisibility(View.GONE);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    hendler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {

            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }


        }
        return true;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return false;
            }
        }

        return super.dispatchKeyEvent(event);
    }


}
