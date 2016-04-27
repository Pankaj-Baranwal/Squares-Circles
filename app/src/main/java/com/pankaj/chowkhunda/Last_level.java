package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pankaj on 29-01-2015.
 */
public class Last_level extends Activity {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Intent intent;
    String called;
    Button cont;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    boolean volume=true;
    static final int S1=R.raw.button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            called=extras.getString("called");
        }
        else
            called="sorry";
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        cont=(Button)findViewById(R.id.conti);
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
        if(sp.getBoolean("premium", false) && (sp.getBoolean("rate", false)))
                cont.setText("CONTINUE");
        else
            cont.setText("BACK");
        cont.setOnClickListener(new View.OnClickListener() {
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
                if(cont.getText().toString().equalsIgnoreCase("CONTINUE")) {
                    try {
                        intent=new Intent(Last_level.this, Class.forName("com.pankaj.chowkhunda.Finale"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        intent=new Intent(Last_level.this, Class.forName("com.pankaj.chowkhunda."+called));
                        intent.putExtra("called", "sorry");
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(called.equalsIgnoreCase("sorry")){
            try {
                intent=new Intent(Last_level.this, Class.forName("com.pankaj.chowkhunda.Start"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                intent = new Intent(Last_level.this, Class.forName("com.pankaj.chowkhunda." + called));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
