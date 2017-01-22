package com.example.kerengold.catchegg;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by KerenGold on 2/28/17.
 */

public class SettingsDialog extends Dialog implements android.view.View.OnClickListener{
    public Activity c;
    public Button quitButton;

    public SettingsDialog(Activity a) {
        super(a);
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);

        quitButton = (Button) findViewById(R.id.settingButton);
        quitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                dismiss();
                break;
            default:
                break;
        }
    }
}
