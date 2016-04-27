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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Pankaj on 14-12-2014.
 */
public class Multiandroid extends Activity implements Event, Event1, Interface_lifeline {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int dont=0, star=0, donot=0;
    TextView p1, p2, p3, p4, n1, n2, n3, n4;
    FrameLayout fm2;
    Testing_algo bark;
    int yup=0;
    Intent intent;
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
        setContentView(R.layout.multiandroid);
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
        fm2.setVisibility(View.GONE);
        bark=(Testing_algo)findViewById(R.id.drawing);
        bark.eve=this;
        if(sp.contains("uname1"))
            n1.setText(""+sp.getString("uname1", "User"));
        else n1.setText("User");
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

    private void frags() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (dont == 0) {
            Log.i("tag", "entered dont0");
            dont = 1;
            if (ft.isEmpty())
                ft.add(R.id.fL1, new Lifeline());
            else
                ft.replace(R.id.fL1, new Lifeline());
            ft.addToBackStack("Lifeline");
            ft.commit();
            fm2.setVisibility(View.VISIBLE);
        } else {
            if (!ft.isEmpty()) {
                Log.i("tag", "entered dont else1");
                Fragment fragment = fm.findFragmentById(R.id.fL1);
                ft.remove(fragment);
                dont = 0;
                ft.commit();
                fm2.setVisibility(View.GONE);
            } else {
                Log.i("tag", "entered dont else2");
                dont = 0;
                ft.replace(R.id.fL1, new Empty());
                ft.commit();
                fm2.setVisibility(View.GONE);
            }
        }
    }

    private void frags1() {
        FragmentManager fm1 = getFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        if (donot == 0) {
            Log.i("tag", "entered donot0");
            donot = 1;
            if (ft1.isEmpty())
                ft1.add(R.id.fL1, new Naming());
            else
                ft1.replace(R.id.fL1, new Naming());
            ft1.addToBackStack("Naming");
            ft1.commit();
            fm2.setVisibility(View.VISIBLE);
        } else {
            if (!ft1.isEmpty()) {
                Log.i("tag", "entered donot else1");
                Fragment fragment = fm1.findFragmentById(R.id.fL1);
                ft1.remove(fragment);
                donot = 0;
                ft1.commit();
                fm2.setVisibility(View.GONE);
            } else {
                Log.i("tag", "entered donot else2");
                donot = 0;
                ft1.replace(R.id.fL1, new Empty());
                ft1.commit();
                fm2.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(donot==0 && dont==0)
            finish();
        else if(donot!=0)
            frags1();
        else
            frags();
    }

    public void textclick(View view) {
        frags1();
    }



    @Override
    protected void onPause() {
        super.onPause();
        if(volume)
        sP.release();
        yup=1;
        if(star!=1) {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Levels"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        finish();
    }


    @Override
    public void onOcc(int players, int canter) {
        if(players==2) {
            if(volume) {
                if (loaded) {
                    if(canter==0)
                    sP.play(soundID1, volumes, volumes, 1, 0, 1f);
                    else if(canter==1)
                        sP.play(soundID2, volumes, volumes, 1, 0, 1f);
                }
            }
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText("Busy!");
            p4.setText("Sleeping!!");
        }
        else if(players==3){
            if(volume) {
                if (loaded) {
                    if(canter==0)
                        sP.play(soundID1, volumes, volumes, 1, 0, 1f);
                    else if(canter==1)
                        sP.play(soundID2, volumes, volumes, 1, 0, 1f);
                    else if(canter==2)
                        sP.play(soundID3, volumes, volumes, 1, 0, 1f);
                }
            }
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText(""+bark.andscore2);
            p4.setText("Busy!");
        }
        else if(players==4){
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
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText(""+bark.andscore2);
            p4.setText(""+bark.andscore3);
        }
        if (bark.totxm != 0 || bark.totym != 0) {
            if(canter!=0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bark.amove();
                            }
                        }, (sp.getInt("progress", 0)*10));
                    }
                });
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
            int who=1;
            bark.canter=0;
            if (players == 2) {
                if (bark.userscore1 > bark.andscore1) {
                    who = 0;
                }
                else if(bark.userscore1 == bark.andscore1) {
                    who=9;
                }
            } else if (players == 3) {
                if (bark.userscore1 > bark.andscore2 && bark.userscore1>bark.andscore1) {
                    who=0;
                } else if((bark.userscore1 <= bark.andscore2 && bark.userscore1<=bark.andscore1)) {
                    if (bark.userscore1 == bark.andscore2 || bark.userscore1 == bark.andscore1) {
                        who = 9;
                    }
                }
            } else if(players==4) {
                if (bark.userscore1 > bark.andscore2 && bark.userscore1>bark.andscore3 && bark.userscore1>bark.andscore1) {
                    who=0;
                }
                else if (bark.userscore1 == bark.andscore2 || bark.userscore1==bark.andscore3 || bark.userscore1==bark.andscore1){
                    who=9;
                }
            }
            editor.putInt("result", who);
            editor.putInt("score", bark.userscore1);
            editor.putInt("coins", bark.userscore1/20);
            editor.apply();
            star=1;
            try {
                intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Paused"));
                intent.putExtra("called", "Multiandroid");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }startActivity(intent);
        }
        if(players==2) {
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText("Busy!");
            p4.setText("Sleeping!!");
        }
        else if(players==3){
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText(""+bark.andscore2);
            p4.setText("Busy!");
        }
        else if(players==4){
            p1.setText(""+bark.userscore1);
            p2.setText(""+bark.andscore1);
            p3.setText(""+bark.andscore2);
            p4.setText(""+bark.andscore3);
        }
    }

    @Override
    public void over(int who) {

    }

    @Override
    public void clicked_life(int select) {
        if(select==1) {
            if (sp.getInt("move", 0) > 0) {
                editor.putInt("move", (sp.getInt("move", 0) - 1));
                editor.apply();
                bark.confused = 2;
            }
        }
        else if(select==9 && yup==0)
            fm2.setVisibility(View.GONE);
    }

    @Override
    public void awesome(int over) {
        if(over==1){
            if(sp.contains("uname1")) {
                bark.uscore1 = sp.getString("uname1", "User1");
                n1.setText(""+bark.uscore1);
            }
            frags1();
        }
        else if(over==9 && yup==0){
            fm2.setVisibility(View.GONE);
        }
    }
}
