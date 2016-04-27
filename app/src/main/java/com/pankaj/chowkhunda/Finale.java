package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Pankaj on 29-01-2015.
 */
public class Finale extends Activity implements Event, Event1, Interface_lifeline {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int dont=0, star=0, donot=0;
    TextView p1, p2, p3, p4, n1;
    FrameLayout fm2;
    Drawline bark;
    int yup=0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_one, R.anim.act_close_scale);
        setContentView(R.layout.finale);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        p1=(TextView)findViewById(R.id.pankaj);
        p2=(TextView)findViewById(R.id.abhay);
        p3=(TextView)findViewById(R.id.varun);
        p4=(TextView)findViewById(R.id.rishubh);
        n1=(TextView)findViewById(R.id.name1);
        fm2=(FrameLayout)findViewById(R.id.fL1);
        fm2.setVisibility(View.GONE);
        bark=(Drawline)findViewById(R.id.drawing);
        bark.eve=this;
        if(sp.contains("uname1"))
            n1.setText(""+sp.getString("uname1", "User"));
        else n1.setText("User");
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
        if (donot == 0) {
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
                Fragment fragment = fm1.findFragmentById(R.id.fL1);
                ft1.remove(fragment);
                donot = 0;
                ft1.commit();
                fm2.setVisibility(View.GONE);
            } else {
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
            int who=1;
            bark.canter=0;
                if (bark.userscore1 > bark.andscore2 && bark.userscore1>bark.andscore3 && bark.userscore1>bark.andscore1) {
                    who=0;
                    editor.putBoolean("WON", true);
                } else {
                    editor.putBoolean("WON", false);
                }
            editor.putInt("result", who);
            editor.putInt("score", bark.userscore1);
            editor.putInt("coins", bark.userscore1/20);
            editor.putInt("subLevel", 9);
            editor.putString("mode", "finale");
            editor.apply();
            star=1;
            try {
                intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Paused"));
                intent.putExtra("called", "Finale");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }startActivity(intent);
        }
        if(players==4){
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

