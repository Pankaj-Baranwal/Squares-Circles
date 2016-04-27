package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Pankaj on 13-12-2014.
 */
public class Settings extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    Button color, about;
    SeekBar sK1;
    TextView pro;
    CheckBox vol, tut;
    int prog=50;
    boolean stored=false;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    boolean volume=true;
    static final int S1=R.raw.button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans, R.anim.act_close_scale);
        setContentView(R.layout.settings);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        edit=sp.edit();
        color=(Button)findViewById(R.id.button);
        about=(Button)findViewById(R.id.button2);
        tut=(CheckBox) findViewById(R.id.tutorials);
        vol=(CheckBox) findViewById(R.id.checkBox);
        vol.setOnCheckedChangeListener(this);
        tut.setOnCheckedChangeListener(this);
        pro=(TextView) findViewById(R.id.progress);
        sK1=(SeekBar) findViewById(R.id.seekBar);
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
        if(sp.contains("volume"))
            vol.setChecked(sp.getBoolean("volume", true));
        setProg();
        sK1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stored=true;
                prog=progress;
                pro.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        color.setOnClickListener(this);
        about.setOnClickListener(this);
        if(sp.contains("dsa"))
            tut.setChecked(!sp.getBoolean("dsa", false));
    }

    private void setProg() {
        sK1.getProgressDrawable().setColorFilter(0xFFFF0000, PorterDuff.Mode.SRC_IN);
        if(sp.contains("progress")){
            sK1.setProgress(100-sp.getInt("progress", 50));
            pro.setText(""+(100-sp.getInt("progress", 50)));
        }else{
            sK1.setProgress(50);
            pro.setText("50");
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
            case R.id.button2:
                try {
                    Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.About"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button:
                try {
                    Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Colors"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void ponClick(View view) {
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
        if(stored) {
            edit.putInt("progress", 100-prog);
            edit.apply();
        }
        overridePendingTransition(R.anim.act_open_scale, R.anim.act_close_trans);
        finish();
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()){
            case R.id.tutorials:
                if(isChecked)
                    edit.putBoolean("dsa", false);
                else
                    edit.putBoolean("dsa", true);
                edit.apply();
                break;
            case R.id.checkBox:
                if(!isChecked)
                edit.putBoolean("volume", false);
                else
                edit.putBoolean("volume", true);
                edit.apply();
                break;
        }

    }
}
