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
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Pankaj on 16-12-2014.
 */
public class Timebomb extends Activity implements Event, Interface_lifeline, Event1 {
    TextView timer;
    int chipmunk = 0;
    int time = 30;
    int dont = 0, star=0, donot=0;
    int thetis = 30000;
    int chuy=0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FrameLayout fm2;
    private Drawlinetimebomb main;
    TextView p1, p2, n1;
    int yup=0;
    Intent intent;
    boolean volume=true;
    static SoundPool sP;
    static int soundID;
    static int soundID1;
    static int soundID2;
    boolean loaded=false;
    static final int S1=R.raw.button;
    static final int S2=R.raw.one;
    static final int S3=R.raw.two;
    AudioManager aM;
    float actualVolume;
    float maxVolume;
    float volumes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_one, R.anim.act_close_scale);
        setContentView(R.layout.timebomb);
        sp = getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        timer = (TextView) findViewById(R.id.textView9);
        fm2=(FrameLayout)findViewById(R.id.fL1);
        fm2.setVisibility(View.GONE);
        if(sp.contains("level")) {
            if (sp.getInt("level", 2) == 2)
                time = 60;
            else if (sp.getInt("level", 2) == 3)
                time = 45;
            else if (sp.getInt("level", 2) == 4)
                time = 30;
        }else{
            time=60;
        }
        thetis=time;
        timer.setText("Time: " + time);
        p1 = (TextView) findViewById(R.id.pankaj);
        p2 = (TextView) findViewById(R.id.abhay);
        n1=(TextView)findViewById(R.id.name1);
        if(sp.contains("uname1"))
                n1.setText(""+ sp.getString("uname1", "User"));
        else
            n1.setText("User");
        main = (Drawlinetimebomb) findViewById(R.id.drawing4);
        main.eve = this;
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
            aM = (AudioManager) getSystemService(AUDIO_SERVICE);
            actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
            maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            volumes = actualVolume / maxVolume;
        }
        countdown();
    }

    private void countdown() {
        new CountDownTimer(thetis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time -= 1;
                timer.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                if (time == 0) {
                    if (chipmunk != 1 && chuy==0) {
                        editor.putInt("result", 1);
                        editor.apply();
                        star=1;
                        try {
                            intent = new Intent(getApplicationContext(), Class.forName("com.pankaj.chowkhunda.Paused"));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                }else{
                    if(chipmunk!=1 && chuy==0)
                        hula();
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(donot==0 && dont==0) {
            chuy=1;
            finish();
        }
        else if(donot!=0)
            frags1();
        else
            frags();
    }

    private void hula() {
        int shime = (time - 1) * 1000;
        new CountDownTimer(shime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time -= 1;
                timer.setText("Time: " + time);
            }

            @Override
            public void onFinish() {
                if(time!=0)
                    timer.setText("Time: 0");
                if (chipmunk != 1 && chuy==0) {
                    editor.putInt("result", 1);
                    editor.apply();
                    star=1;
                    if(chipmunk!=1 && chuy==0) {
                        try {
                            intent = new Intent(getApplicationContext(), Class.forName("com.pankaj.chowkhunda.Paused"));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                    }
                }
            }
        }.start();
    }


    private void frags() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (dont == 0) {
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
                Fragment fragment = fm.findFragmentById(R.id.fL1);
                ft.remove(fragment);
                dont = 0;
                ft.commit();
                fm2.setVisibility(View.GONE);
            } else {
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

    public void textclick(View view) {
        frags1();
    }

    @Override
    protected void onPause() {
        super.onPause();
        yup=1;
        if(star!=1 && chipmunk!=1) {
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
        if(volume) {
            if (loaded) {
                if(canter==0)
                    sP.play(soundID1, volumes, volumes, 1, 0, 1f);
                else if(canter==1)
                    sP.play(soundID2, volumes, volumes, 1, 0, 1f);
            }
        }
        p1.setText("" + main.userscore);
        p2.setText("" + main.andscore);
        if(canter!=0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            main.amove();
                        }
                    }, (sp.getInt("progress", 0)*10));
                }
            });
        }
    }

    @Override
    public void over(int who) {
        chipmunk = 1;
        editor.putInt("result", who);
        editor.putInt("score", main.userscore);
        editor.putInt("coins", main.userscore / 20);
        editor.putString("name", main.uscore);
        editor.apply();
        star=1;
        chipmunk=1;
        try {
            intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Paused"));
            intent.putExtra("called", "Timebomb");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }

    @Override
    public void clicked_life(int select) {
        if (select == 1) {
            if (sp.getInt("move", 0) > 0) {
                editor.putInt("move", (sp.getInt("move", 0) - 1));
                editor.apply();
                main.confused = 2;
            }
        }
        else if (select == 2) {
            if (sp.getInt("timer", 0) > 0) {
                editor.putInt("timer", (sp.getInt("timer", 0) - 1));
                editor.apply();
                time += 15;
            }
        }
        else if(select==9 && yup==0)
            fm2.setVisibility(View.GONE);
    }

    @Override
    public void awesome(int over) {
        if(over==1){
            if(sp.contains("uname1")) {
                main.uscore = sp.getString("uname1", "User");
                n1.setText(""+main.uscore);
            }
            frags1();
        }
        else if(over==9 && yup==0){
            fm2.setVisibility(View.GONE);
        }
    }
}