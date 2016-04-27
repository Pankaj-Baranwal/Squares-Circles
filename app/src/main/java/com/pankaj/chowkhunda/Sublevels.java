package com.pankaj.chowkhunda;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pankaj on 19-12-2014.
 */
public class Sublevels extends Activity implements View.OnClickListener {
    Button sL33, sL34, sL44, sL45, sL55, sL56, sL66, sL67, sL77, sL88;
    TextView series;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    AnimatorSet animSet1;
    ObjectAnimator anims2, anims1;
    String called;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    boolean volume=true;
    static final int S1=R.raw.button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans, R.anim.act_close_scale);
        setContentView(R.layout.sublevels);
        sp = getSharedPreferences("Myprefs", MODE_PRIVATE);
        editor = sp.edit();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            called = extras.getString("called");
        } else
            called = "sorry";
        sL33 = (Button) findViewById(R.id.button33);
        sL34 = (Button) findViewById(R.id.button34);
        sL44 = (Button) findViewById(R.id.button44);
        sL45 = (Button) findViewById(R.id.button45);
        sL55 = (Button) findViewById(R.id.button55);
        sL56 = (Button) findViewById(R.id.button56);
        sL66 = (Button) findViewById(R.id.button66);
        sL67 = (Button) findViewById(R.id.button67);
        sL77 = (Button) findViewById(R.id.button77);
        sL88 = (Button) findViewById(R.id.button88);
        series = (TextView) findViewById(R.id.textView18);
        sL33.setOnClickListener(this);
        sL34.setOnClickListener(this);
        sL44.setOnClickListener(this);
        sL45.setOnClickListener(this);
        sL55.setOnClickListener(this);
        sL56.setOnClickListener(this);
        sL66.setOnClickListener(this);
        sL67.setOnClickListener(this);
        sL77.setOnClickListener(this);
        sL88.setOnClickListener(this);
        volume=sp.getBoolean("volume", true);
        if(volume) {
            sP = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            sP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    loaded = true;
                }
            });
            soundID = sP.load(this, R.raw.button, 1);
        }
    }

    @Override
    public void onClick(View v) {
        if(volume) {
            AudioManager aM = (AudioManager) getSystemService(AUDIO_SERVICE);
            float actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = actualVolume / maxVolume;
            if (loaded) {
                sP.play(soundID, volume, volume, 1, 0, 1f);
            }
        }
        switch (v.getId()) {
            case R.id.button33:
                editor.putInt("sublevel", 1);
                editor.apply();
                series.setAlpha(0);
                series.setText("3x3");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button34:
                editor.putInt("sublevel", 2);
                editor.apply();
                series.setAlpha(0);
                series.setText("3x4");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button44:
                editor.putInt("sublevel", 3);
                editor.apply();
                series.setAlpha(0);
                series.setText("4x4");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button45:
                editor.putInt("sublevel", 4);
                editor.apply();
                series.setAlpha(0);
                series.setText("4x5");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button55:
                editor.putInt("sublevel", 5);
                editor.apply();
                series.setAlpha(0);
                series.setText("5x5");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button56:
                editor.putInt("sublevel", 6);
                editor.apply();
                series.setAlpha(0);
                series.setText("5x6");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button66:
                editor.putInt("sublevel", 7);
                editor.apply();
                series.setAlpha(0);
                series.setText("6x6");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button67:
                editor.putInt("sublevel", 8);
                editor.apply();
                series.setAlpha(0);
                series.setText("6x7");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button77:
                editor.putInt("sublevel", 9);
                editor.apply();
                series.setAlpha(0);
                series.setText("7x7");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                break;
            case R.id.button88:
                try {
                    Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Last_level"));
                    intent.putExtra("called", "Sublevels");
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
/*                editor.putInt("sublevel", 10);
                editor.apply();
                series.setAlpha(0);
                series.setText("8x8");
                anims2 = ObjectAnimator.ofFloat(series, "x", -(series.getWidth()));
                anims2.setDuration(1);
                series.setAlpha(1);
                anims1 = ObjectAnimator.ofFloat(series, "x", series.getLeft());
                anims1.setDuration(400);
                animSet1 = new AnimatorSet();
                animSet1.setInterpolator(new AnticipateOvershootInterpolator());
                animSet1.play(anims2).before(anims1);
                animSet1.start();
                */
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.act_open_scale, R.anim.act_close_trans);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (called.equalsIgnoreCase("sorry")) {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda." + called));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void pomClick(View view){
        if (called.equalsIgnoreCase("sorry")) {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda." + called));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
