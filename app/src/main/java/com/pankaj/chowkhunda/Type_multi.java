package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Pankaj on 22-01-2015.
 */
public class Type_multi extends Activity implements View.OnClickListener {
    Button levels, rate;
    ImageButton same, blue, online;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    boolean volume=true;
    static final int S1=R.raw.button;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans, R.anim.act_close_scale);
        setContentView(R.layout.multi);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        levels=(Button)findViewById(R.id.levell);
        rate=(Button)findViewById(R.id.rate);
        same=(ImageButton)findViewById(R.id.same);
        online=(ImageButton)findViewById(R.id.online);
        blue=(ImageButton)findViewById(R.id.bluetooth);
        levels.setOnClickListener(this);
        same.setOnClickListener(this);
        blue.setOnClickListener(this);
        rate.setOnClickListener(this);
        online.setOnClickListener(this);
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
        switch(v.getId()){
            case R.id.levell:
                try {
                    Intent intent=new Intent(this, Class.forName("com.pankaj.chowkhunda.Sublevels"));
                    intent.putExtra("called", "Type_multi");
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.same:
                try {
                    Intent intent=new Intent(this, Class.forName("com.pankaj.chowkhunda.Play"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.online:
                try {
                    Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Firstworst"));
                    intent.putExtra("called", "Type_multi");
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bluetooth:
                try {
                    Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Firstworst"));
                    intent.putExtra("called", "Type_multi");
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.rate:
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.pankaj.chowkhunda"));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
