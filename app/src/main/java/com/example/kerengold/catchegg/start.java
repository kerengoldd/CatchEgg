package com.example.kerengold.catchegg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

public class start extends AppCompatActivity {

    private InfoDialog infoDialog;
    private SettingsDialog settingsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
        infoDialog = new InfoDialog(this);
        settingsDialog = new SettingsDialog(this);
    }

    public void startGame(View view) {
        startActivity(new Intent(getApplicationContext(), main.class));

    }

    public void openAbout(View view) {
        infoDialog.show();
    }

    public void openSettings(View view) {
        settingsDialog.show();
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
