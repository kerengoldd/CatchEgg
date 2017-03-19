package com.example.kerengold.catchegg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by KerenGold on 1/10/17.
 */

public class splash extends Activity {
    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView iv = (ImageView) findViewById(R.id.eggrt);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.abc_fade_out);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                Intent i =  new Intent(getBaseContext(), start.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
