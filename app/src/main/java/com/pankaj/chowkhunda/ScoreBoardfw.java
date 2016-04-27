package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pankaj on 14-12-2014.
 */
public class ScoreBoardfw extends Activity implements View.OnClickListener {
    Button fw, gg, tb;
    TextView easy1, easy2, easy3, medium1, medium2, medium3, hard1, hard2, hard3, player1, player2, player3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans_fast, R.anim.act_close_scale_fast);
        setContentView(R.layout.scoreboardfw);
        tb=(Button)findViewById(R.id.button26);
        fw=(Button)findViewById(R.id.button27);
        gg=(Button)findViewById(R.id.button28);
        tb.setOnClickListener(this);
        fw.setOnClickListener(this);
        gg.setOnClickListener(this);
        easy1=(TextView)findViewById(R.id.easy1);
        easy2=(TextView)findViewById(R.id.easy2);
        easy3=(TextView)findViewById(R.id.easy3);
        medium1=(TextView)findViewById(R.id.medium1);
        medium2=(TextView)findViewById(R.id.medium2);
        medium3=(TextView)findViewById(R.id.medium3);
        hard1=(TextView)findViewById(R.id.hard1);
        hard2=(TextView)findViewById(R.id.hard2);
        hard3=(TextView)findViewById(R.id.hard3);
        player1=(TextView)findViewById(R.id.Player1);
        player2=(TextView)findViewById(R.id.Player2);
        player3=(TextView)findViewById(R.id.Player3);
        setup();
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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button26:
                try {
                    Intent intent= new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoardtb"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.button27:

                break;
            case R.id.button28:
                try {
                    Intent intent= new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoard"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void setup() {
        SharedPreferences sp=getSharedPreferences("Myprefs", MODE_PRIVATE);
        int escore1, escore2, escore3, mscore1, mscore2, mscore3, hscore1, hscore2, hscore3;
        if(sp.contains("fwescore1"))
            easy1.setText(String.valueOf(sp.getInt("fwescore1", 0)));
        if(sp.contains("fwescore2"))
            easy2.setText(String.valueOf(sp.getInt("fwescore2", 0)));
        if(sp.contains("fwescore3"))
            easy3.setText(String.valueOf(sp.getInt("fwescore3", 0)));
        if(sp.contains("fwmscore1"))
            medium1.setText(String.valueOf(sp.getInt("fwmscore1", 0)));
        if(sp.contains("fwmscore2"))
            medium2.setText(String.valueOf(sp.getInt("fwmscore2", 0)));
        if(sp.contains("fwmscore3"))
            medium3.setText(String.valueOf(sp.getInt("fwmscore3", 0)));
        if(sp.contains("fwhscore1"))
            hard1.setText(String.valueOf(sp.getInt("fwhscore1", 0)));
        if(sp.contains("fwhscore2"))
            hard2.setText(String.valueOf(sp.getInt("fwhscore2", 0)));
        if(sp.contains("fwhscore3"))
            hard3.setText(String.valueOf(sp.getInt("fwhscore3", 0)));
        if(sp.contains("fwPlayer1"))
            player1.setText(String.valueOf(sp.getString("fwPlayer1", "")));
        if(sp.contains("fwPlayer2"))
            player2.setText(String.valueOf(sp.getString("fwPlayer2", "")));
        if(sp.contains("fwPlayer3"))
            player3.setText(String.valueOf(sp.getString("fwPlayer3", "")));
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
