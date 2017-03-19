package com.example.kerengold.catchegg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class result extends AppCompatActivity {
    private start start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView yourscore = (TextView) findViewById(R.id.yourscore);
        TextView highscorelabel = (TextView) findViewById(R.id.highscorelabel);
        int score = getIntent().getIntExtra("SCORE" ,0);
        yourscore.setText("Your score : "+ score + "");

        SharedPreferences settings = getSharedPreferences("HIGH_SCORE", Context.MODE_PRIVATE);

        int highscore = settings.getInt("HIGH_SCORE" , 0 );

        if (score > highscore){
            highscorelabel.setText("High Score : " + score);

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();
        }else {
            highscorelabel.setText("High score : " + highscore);
        }
    }

    public void start(View view) {
        startActivity(new Intent(getApplicationContext(), start.class));
    }


    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(), main.class));

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
