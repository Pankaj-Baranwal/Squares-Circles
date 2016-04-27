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
public class ScoreBoardtb extends Activity implements View.OnClickListener {
    Button fw, gg, tb;
    TextView easy1, easy2, easy3, medium1, medium2, medium3, hard1, hard2, hard3, player1, player2, player3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans_fast, R.anim.act_close_scale_fast);
        setContentView(R.layout.scoreboardtb);
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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button26:

                break;
            case R.id.button27:
                try {
                    Intent intent= new Intent(this, Class.forName("com.pankaj.chowkhunda.ScoreBoardfw"));
                    startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
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
        if(sp.contains("tbescore1"))
            easy1.setText(String.valueOf(sp.getInt("tbescore1", 0)));
        if(sp.contains("tbescore2"))
            easy2.setText(String.valueOf(sp.getInt("tbescore2", 0)));
        if(sp.contains("tbescore3"))
            easy3.setText(String.valueOf(sp.getInt("tbescore3", 0)));
        if(sp.contains("tbmscore1"))
            medium1.setText(String.valueOf(sp.getInt("tbmscore1", 0)));
        if(sp.contains("tbmscore2"))
            medium2.setText(String.valueOf(sp.getInt("tbmscore2", 0)));
        if(sp.contains("tbmscore3"))
            medium3.setText(String.valueOf(sp.getInt("tbmscore3", 0)));
        if(sp.contains("tbhscore1"))
            hard1.setText(String.valueOf(sp.getInt("tbhscore1", 0)));
        if(sp.contains("tbhscore2"))
            hard2.setText(String.valueOf(sp.getInt("tbhscore2", 0)));
        if(sp.contains("tbhscore3"))
            hard3.setText(String.valueOf(sp.getInt("tbhscore3", 0)));
        if(sp.contains("tbPlayer1"))
            player1.setText(String.valueOf(sp.getString("tbPlayer1", "")));
        if(sp.contains("tbPlayer2"))
            player2.setText(String.valueOf(sp.getString("tbPlayer2", "")));
        if(sp.contains("tbPlayer3"))
            player3.setText(String.valueOf(sp.getString("tbPlayer3", "")));
    }
    @Override
    protected void onPause() {
        super.onPause();
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

}
