package com.example.kerengold.catchegg;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by KerenGold on 12/25/16.
 */

public class sound {
    private static SoundPool soundPool;
    private static int hit;
    private static int fail;
    private static int newWinner;
     public sound(Context context){
         soundPool = new SoundPool(3, AudioManager.STREAM_MUSIC,0);
         hit = soundPool.load(context,R.raw.hit,1);
         fail = soundPool.load(context,R.raw.fail,1);
         newWinner = soundPool.load(context,R.raw.newwinner,1);
     }

    public void playHitSound(){
        soundPool.play(hit,1.0f,1.0f,1,0,1.0f);
    }
    public void playFailSound(){soundPool.play(fail,1.0f,1.0f,1,0,1.0f);}
    public void playWinSound(){soundPool.play(newWinner,1.0f,1.0f,1,0,1.0f);}
}
