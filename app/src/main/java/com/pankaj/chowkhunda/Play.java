package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Pankaj on 14-12-2014.
 */
public class Play extends Activity implements Event, Event1 {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Drawline2player meow;
    FrameLayout fm2;
    TextView p1, p2, p3, p4, n1, n2, n3, n4;
    Intent intent;
    int dont=0, star=0, donot=0;
    boolean volume=true;
    static SoundPool sP;
    static int soundID;
    static int soundID1;
    static int soundID2;
    static int soundID3;
    static int soundID4;
    boolean loaded=false;
    static final int S1=R.raw.button;
    static final int S2=R.raw.one;
    static final int S3=R.raw.two;
    static final int S4=R.raw.three;
    static final int S5=R.raw.four;
    AudioManager aM;
    float actualVolume;
    float maxVolume;
    float volumes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_one, R.anim.act_close_scale);
        setContentView(R.layout.activity_players);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        p1=(TextView)findViewById(R.id.pankaj);
        p2=(TextView)findViewById(R.id.abhay);
        p3=(TextView)findViewById(R.id.varun);
        p4=(TextView)findViewById(R.id.rishubh);
        n1=(TextView)findViewById(R.id.name1);
        n2=(TextView)findViewById(R.id.name2);
        n3=(TextView)findViewById(R.id.name3);
        n4=(TextView)findViewById(R.id.name4);
        fm2=(FrameLayout)findViewById(R.id.fL1);
        fm2.setVisibility(View.VISIBLE);
        meow=(Drawline2player)findViewById(R.id.drawing);
        meow.eve=this;
        frags1();
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
            soundID1 = sP.load(this, R.raw.one, 1);
            soundID2 = sP.load(this, R.raw.two, 1);
            soundID3 = sP.load(this, R.raw.three, 1);
            soundID4 = sP.load(this, R.raw.four, 1);
            aM = (AudioManager) getSystemService(AUDIO_SERVICE);
            actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
            maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            volumes = actualVolume / maxVolume;
        }

    }


    private void frags1() {
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        if(donot==0) {
            donot=1;
            if (ft1.isEmpty())
                ft1.add(R.id.fL1, new Naming());
            else
                ft1.replace(R.id.fL1, new Naming());
            ft1.addToBackStack("Naming");
            ft1.commit();
            fm2.setVisibility(View.VISIBLE);
        }else{
            if(!ft1.isEmpty()) {
                Fragment fragment = fm1.findFragmentById(R.id.fL1);
                ft1.remove(fragment);
                donot = 0;
                ft1.commit();
                fm2.setVisibility(View.GONE);
            }else{
                donot = 0;
                ft1.replace(R.id.fL1, new Empty());
                ft1.commit();
                fm2.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onOcc(int players, int canter) {
        if (meow.totxm != 0 || meow.totym != 0) {
            if(volume) {
                if (loaded) {
                    if(canter==0)
                        sP.play(soundID1, volumes, volumes, 1, 0, 1f);
                    else if(canter==1)
                        sP.play(soundID2, volumes, volumes, 1, 0, 1f);
                    else if(canter==2)
                        sP.play(soundID3, volumes, volumes, 1, 0, 1f);
                    else if(canter==3)
                        sP.play(soundID4, volumes, volumes, 1, 0, 1f);
                }
            }
        }
        else {
            if(volume) {
                if (loaded) {
                    if(canter==0)
                        sP.play(soundID1, volumes, volumes, 1, 0, 1f);
                    else if(canter==1)
                        sP.play(soundID2, volumes, volumes, 1, 0, 1f);
                    else if(canter==2)
                        sP.play(soundID3, volumes, volumes, 1, 0, 1f);
                    else if(canter==3)
                        sP.play(soundID4, volumes, volumes, 1, 0, 1f);
                }
            }
            meow.canter=0;
            if (players == 2) {
                editor.putInt("score3", 0);
                editor.putInt("score4", 0);
            } else if (players == 3) {
                editor.putInt("score3", meow.userscore3);
                editor.putInt("score4", 0);
                editor.apply();
            } else if(players==4) {
                editor.putInt("score3", meow.userscore3);
                editor.putInt("score4", meow.userscore4);
                editor.apply();
            }
            editor.putInt("result", 5);
            editor.putInt("score1", meow.userscore1);
            editor.putInt("score2", meow.userscore2);
            editor.apply();
            star=1;
            try {
                intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Paused"));
                intent.putExtra("called", "Play");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }startActivity(intent);
        }
        if(players==2) {
            p1.setText(""+meow.userscore1);
            p2.setText(""+meow.userscore2);
            p3.setText("Coward!");
            p4.setText("Coward!");
        }
        else if(players==3){
            p1.setText(""+meow.userscore1);
            p2.setText(""+meow.userscore2);
            p3.setText(""+meow.userscore3);
            p4.setText("Not playing!");
        }
        else if(players==4){
            p1.setText(""+meow.userscore1);
            p2.setText(""+meow.userscore2);
            p3.setText(""+meow.userscore3);
            p4.setText(""+meow.userscore4);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dont=1;
        if(star!=1) {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Type_multi"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        finish();
    }


    @Override
    public void over(int who) {

    }

    @Override
    public void awesome(int over) {
        if(over==1){
            if(sp.contains("name1")) {
                meow.uscore1 = sp.getString("name1", "User1");
                n1.setText(""+meow.uscore1);
            }
            if(sp.contains("name2")) {
                meow.uscore2 = sp.getString("name2", "User2");
                n2.setText(""+meow.uscore2);
            }
            if(sp.contains("name3")) {
                meow.uscore3 = sp.getString("name3", "User3");
                n3.setText(""+meow.uscore3);
            }
            if(sp.contains("name4")) {
                meow.uscore4 = sp.getString("name4", "User4");
                n4.setText(""+meow.uscore4);
            }
            meow.shut=0;
            frags1();
        }else if(over==9 && dont==0){
        	donot=1;
        	meow.shut=0;
            frags1();
        }
    }
}
