package com.example.kerengold.catchegg;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/**
 * Created by KerenGold on 2/28/17.
 */

    public class SettingsDialog extends Dialog {

    SeekBar seekBar;

    MediaPlayer mp;

    Handler handler;

    Runnable run;

    public SettingsDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        handler = new Handler();

        seekBar = (SeekBar) findViewById(R.id.seekbar1);
        mp = MediaPlayer.create(getContext(), R.raw.ss);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        seekBar.setMax(mp.getDuration());

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
                playcycle();
                mp.start();
            }
        });


        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progrss, boolean input) {
                if (input) {
                    mp.seekTo(progrss);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playcycle() {
        seekBar.setProgress(mp.getCurrentPosition());

        if (mp.isPlaying()) {
            run = new Runnable() {
                @Override
                public void run() {
                    playcycle();
                }
            };
            handler.postDelayed(run, 1000);
        }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        mp.start();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mp.stop();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mp.release();
//        handler.removeCallbacks(run);
//
//    }
}



}
