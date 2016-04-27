package com.pankaj.chowkhunda;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pankaj on 15-12-2014.
 */
public class Levels extends Activity implements View.OnClickListener {
    Button easy, medium, hard, levels;
    TextView coins;
    float scale;
    int size=10;
    int width=0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans, R.anim.act_close_scale);
        setContentView(R.layout.levels);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        easy=(Button)findViewById(R.id.easy);
        medium=(Button)findViewById(R.id.medium);
        hard=(Button)findViewById(R.id.hard);
        levels=(Button)findViewById(R.id.levels);

        scale= getResources().getDisplayMetrics().density;
        coins=(TextView) findViewById(R.id.coins);
        if(sp.contains("tcoins"))
        coins.setText("Coins: " + sp.getInt("tcoins", 0));
        levels.setOnClickListener(this);
        easy.setOnClickListener(this);
        medium.setOnClickListener(this);
        hard.setOnClickListener(this);
        size=sp.getInt("size", 10);
        width=sp.getInt("width", 600);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.easy:
                editor.putInt("level", 2);
                editor.apply();
                intent= new Intent(Levels.this, Multiandroid.class);
                startActivity(intent);
                break;
            case R.id.medium:
                editor.putInt("level", 3);
                editor.apply();
                intent= new Intent(Levels.this, Multiandroid.class);
                startActivity(intent);
                break;
            case R.id.hard:
                editor.putInt("level", 4);
                editor.apply();
                intent= new Intent(Levels.this, Multiandroid.class);
                startActivity(intent);
                break;
            case R.id.levels:
                intent= new Intent(Levels.this, Sublevels.class);
                startActivity(intent);
                break;
        }
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
