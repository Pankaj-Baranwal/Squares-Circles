package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Pankaj on 14-12-2014.
 */
public class Paused extends Activity implements View.OnClickListener {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    TextView comment1, comment2, score, coins;
    ImageButton next, restart, scoreboard, exit;
    ImageView heart, face, heart1;
    int result=0;
    Intent intent;
    String mode, called;
    int[] source;
    static SoundPool sP;
    static int soundID;
    static int soundID1;
    static int soundID2;
    boolean loaded=false;
    boolean volume=true;
    static final int S1=R.raw.button;
    static final int S2=R.raw.woho;
    static final int S3=R.raw.wow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paused);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            called=extras.getString("called");
        }else
            called="sorry";
        comment1=(TextView)findViewById(R.id.textView2);
        comment2=(TextView)findViewById(R.id.textView3);
        score=(TextView)findViewById(R.id.textView4);
        coins=(TextView)findViewById(R.id.textView5);
        next=(ImageButton)findViewById(R.id.imageView);
        restart=(ImageButton)findViewById(R.id.imageButton3);
        source= new int[]{R.drawable.nerd_small, R.drawable.oldie_small, R.drawable.lady_small, R.drawable.bloodyvamp_small, R.drawable.rate_small};
        scoreboard=(ImageButton)findViewById(R.id.imageButton4);
        exit=(ImageButton)findViewById(R.id.imageButton5);
        next.setOnClickListener(this);
        restart.setOnClickListener(this);
        scoreboard.setOnClickListener(this);
        exit.setOnClickListener(this);
        heart=(ImageView)findViewById(R.id.heart);
        heart1=(ImageView)findViewById(R.id.heart1);
        face=(ImageView)findViewById(R.id.face);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
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
            soundID1 = sP.load(this, R.raw.woho, 1);
            soundID2 = sP.load(this, R.raw.wow, 1);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Levels"));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            setup();
            highscore();
        }
    }

    private void highscore() {
        int score=0, level=2;
        if(sp.contains("score"))
            score= sp.getInt("score", 0);
        if(sp.contains("mode")) {
            mode=sp.getString("mode", "Greedyguru");
            if(mode.equalsIgnoreCase("Greedyguru")) {
                if (sp.contains("level"))
                    level = sp.getInt("level", 2);
                if (level == 2)
                    assign("ggescore1", "ggescore2", "ggescore3", score);
                else if(level==3)
                    assign("ggmscore1", "ggmscore2", "ggmscore3", score);
                else if(level==4)
                    assign("gghscore1", "gghscore2", "gghscore3", score);
            }if(mode.equalsIgnoreCase("Firstworst")) {
                if (sp.contains("level"))
                    level = sp.getInt("level", 2);
                if (level == 2)
                    assign("fwescore1", "fwescore2", "fwescore3", score);
                else if(level==3)
                    assign("fwmscore1", "fwmscore2", "fwmscore3", score);
                else if(level==4)
                    assign("fwhscore1", "fwhscore2", "fwhscore3", score);
            }else if(mode.equalsIgnoreCase("Timebomb")) {
                if (sp.contains("level"))
                    level = sp.getInt("level", 2);
                if (level == 2)
                    assign("tbescore1", "tbescore2", "tbescore3", score);
                else if(level==3)
                    assign("tbmscore1", "tbmscore2", "tbmscore3", score);
                else if(level==4)
                    assign("tbhscore1", "tbhscore2", "tbhscore3", score);
            }
        }
    }

    private void assign(String a1, String a2, String a3, int score){
        if(sp.contains(a1)) {
            if (score > sp.getInt(a1, 0)) {
                if (sp.contains(a2)) {
                    editor.putInt(a3, sp.getInt(a2, 0));
                    editor.putInt(a2, sp.getInt(a1, 0));
                    editor.putInt(a1, score);
                } else {
                    editor.putInt(a2, sp.getInt(a1, 0));
                    editor.putInt(a1, score);
                }
            } else if (sp.contains(a2)) {
                if (score > sp.getInt(a2, 0)) {
                    editor.putInt(a3, sp.getInt(a2, 0));
                    editor.putInt(a2, score);
                } else if (sp.contains(a3)) {
                    if (score > sp.getInt(a3, 0))
                        editor.putInt(a3, score);
                } else
                    editor.putInt(a3, score);
            } else
                editor.putInt(a2, score);
        }else
            editor.putInt(a1, score);
        editor.apply();
    }

    private void setup() {
        Random rand= new Random();
        if (sp.contains("result")) {
            if (sp.getInt("result", 1) != 5) {
                if (sp.contains("score"))
                    score.setText("Score: " + String.valueOf(sp.getInt("score", 0)));
                if (sp.contains("coins"))
                    coins.setText("Coins: " + String.valueOf(sp.getInt("coins", 0)));
                if (sp.contains("tcoins"))
                    editor.putInt("tcoins", ((sp.getInt("tcoins", 0)) + (sp.getInt("coins", 0))));
                else
                    editor.putInt("tcoins", (sp.getInt("coins", 0)));
                editor.apply();
                if (sp.getInt("result", 1) == 1) {
                    result = 1;
                    next.setBackgroundResource(R.drawable.play_again);
                    face.setBackgroundResource(source[4-rand.nextInt(2)]);
                    face.setVisibility(View.VISIBLE);
                    heart.setVisibility(View.VISIBLE);
                    comment1.setText("LOSER!");
                    comment2.setText("BETTEr LUCk NEXt TIMe");
                } else if (sp.getInt("result", 1) == 0) {
                    if(volume) {
                        AudioManager aM = (AudioManager) getSystemService(AUDIO_SERVICE);
                        float actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
                        float maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                        float volume = actualVolume / maxVolume;
                        if (loaded) {
                            Random dhruv=new Random();
                            if(dhruv.nextInt(2)==0)
                                sP.play(soundID1, volume, volume, 1, 0, 1f);
                            else
                                sP.play(soundID2, volume, volume, 1, 0, 1f);
                        }
                    }
                    result = 0;
                    heart.setVisibility(View.INVISIBLE);
                    next.setBackgroundResource(R.drawable.next_level);
                    face.setBackgroundResource(source[rand.nextInt(3)]);
                    face.setVisibility(View.VISIBLE);
                    heart1.setVisibility(View.VISIBLE);
                    comment1.setText("YOU WON!");
                    comment2.setText("KEEp Up THe AWESOMENESs!!");
                } else {
                    result = 1;
                    heart.setVisibility(View.VISIBLE);
                    next.setBackgroundResource(R.drawable.play_again);
                    comment1.setText("IT'S A TIE!");
                    comment2.setText("NICe COMPETITIOn THERe!!");
                }
            } else if (sp.getInt("result", 1) == 5) {
                result = 0;
                if (sp.contains("score1")) {
                    score.setText("User1: " + String.valueOf(sp.getInt("score1", 0)) + "   " + "User2: " + String.valueOf(sp.getInt("score2", 0)));
                    if(sp.getInt("score3", 0)==0)
                        coins.setText("User3: N/A" + "   " + "User4: N/A");
                    else if(sp.getInt("score4", 0)==0)
                        coins.setText("User3: " + String.valueOf(sp.getInt("score3", 0)) + "   " + "User4: N/A");
                    else if(sp.getInt("score4", 0)!=0)
                        coins.setText("User3: " + String.valueOf(sp.getInt("score3", 0)) + "   " + "User4: " + String.valueOf(sp.getInt("score4", 0)));
                }
                next.setBackgroundResource(R.drawable.next_level);
                comment1.setText("WELL PLAYED!");
                comment2.setText("KEEp Up THe AWESOMENESs!!");
            }
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
            case R.id.imageView:
                int rew = sp.getInt("sublevel", 4) + 1;
                if(rew==10){
                    if(sp.getString("playing", "modes").equalsIgnoreCase("multi")) {
                        rew = 9;
                    }
                    else if(sp.getString("playing", "modes").equalsIgnoreCase("modes")){
                        if(sp.getInt("level", 2)!=4) {
                            rew = 1;
                            editor.putInt("level", (sp.getInt("level", 2)+1));
                            editor.apply();
                        }
                        else{
                            try {
                                intent=new Intent(this, Class.forName("com.pankaj.chowkhunda.Last_level"));
                                intent.putExtra("called", "Levels");
                                startActivity(intent);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                if (result == 0) {
                    editor.putInt("sublevel", rew);
                    editor.apply();
                    open();
                }else if(result==1){
                    editor.putInt("sublevel", rew-1);
                    editor.apply();
                    open();
                }
                intent = new Intent(Paused.this, Multiandroid.class);
                startActivity(intent);
                break;
            case R.id.imageButton3:
                if(called.contentEquals("sorry")){
                    try {
                        intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Levels"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        intent = new Intent(this, Class.forName("com.pankaj.chowkhunda." + called));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.imageButton4:
                if(mode.equalsIgnoreCase("Firstworst")) {
                    try {
                        intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoardfw"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else if(mode.equalsIgnoreCase("Timebomb")) {
                    try {
                        intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoardtb"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoard"));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.imageButton5:
                try {
                    intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    
    private void open(){
    	if(sp.getString("playing", "modes").equalsIgnoreCase("modes")) {
            if (sp.contains("mode")) {
                if (sp.contains("level")) {
                    if (sp.getString("mode", "Greedyguru").equalsIgnoreCase("Greedyguru")) {
                        try {
                            intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Multiandroid"));
                            startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (sp.getString("mode", "Greedyguru").equalsIgnoreCase("Timebomb")) {
                        try {
                            intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Timebomb"));
                            startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (sp.getString("mode", "Greedyguru").equalsIgnoreCase("Firstworst")) {
                        try {
                            intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Firstworst"));
                            startActivity(intent);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else if (sp.getString("mode", "Greedyguru").equalsIgnoreCase("finale")) {
                        if(result==1) {
                            try {
                                intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Finale"));
                                startActivity(intent);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }else{
                            try {
                                intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
                                startActivity(intent);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }else if(sp.getString("playing", "modes").equalsIgnoreCase("multi")) {
            try {
                Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Play"));
                startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
