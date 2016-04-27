package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Pankaj on 13-12-2014.
 */
public class Colors extends Activity implements Interface_color {
    RadioButton rB1, rB2, rB3, rB4;
    int[] ccolor, color;
    int donot=0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colors);
        sp = getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        rB1 = (RadioButton) findViewById(R.id.radioButton);
        rB2 = (RadioButton) findViewById(R.id.radioButton2);
        rB3 = (RadioButton) findViewById(R.id.radioButton3);
        rB4 = (RadioButton) findViewById(R.id.radioButton4);
        ccolor = new int[]{0xff224040, 0xff847034, 0xff5ab78d, 0xff886bb8, 0xff7f2fd4, 0xff606f7f, 0xffb64e80, 0xff64394d, 0xff1f8df0, 0xff1f40a5, 0xd2a5e6};
        color = new int[]{0xffff4040, 0xff8470ff, 0xffdab7ed, 0xfff86b78, 0xff7fffd4, 0xff00ff7f, 0xff76ee00, 0xff60994d, 0xffff8d00, 0xff4040ff, 0xd0f0e0};
        editor.putString("rB", "c");
        editor.apply();
        frags();
    }


    private void frags() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        if(!ft.isEmpty())
            fragment = fm.findFragmentById(R.id.fL1);
        if(sp.getString("rB", "c").equalsIgnoreCase("c")||sp.getString("rB", "c").equalsIgnoreCase("l")) {
            if(donot==0) {
                donot=1;
                ft.add(R.id.rllst2, new Color_frag1());
                ft.commit();
            }else{
                ft.setCustomAnimations(R.animator.frag_close1, R.animator.frag_open1);
                ft.replace(R.id.rllst2, new Color_frag1());
                ft.commit();
            }
        }else if(sp.getString("rB", "c").equalsIgnoreCase("u")||sp.getString("rB", "c").equalsIgnoreCase("a")) {
            if(donot==0) {
                ft.add(R.id.rllst2, new Color_frag());
                ft.commit();
            }else{
                ft.setCustomAnimations(R.animator.frag_close1, R.animator.frag_open1);
                ft.replace(R.id.rllst2, new Color_frag());
                ft.commit();
            }
        }
    }

    public void onRBClicked(View v){
        switch (v.getId()){
            case R.id.radioButton:
                    editor.putString("rB", "c");
                    editor.apply();
                frags();
                break;
            case R.id.radioButton2:
                    editor.putString("rB", "l");
                    editor.apply();
                frags();
                break;
            case R.id.radioButton3:
                    editor.putString("rB", "u");
                    editor.apply();
                frags();
                break;
            case R.id.radioButton4:
                    editor.putString("rB", "a");
                    editor.apply();
                frags();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Settings"));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onClick(View view) {
        try {
            Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Settings"));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void assign(int i) {
        String fiji= sp.getString("rB", "u");
        if(fiji.equalsIgnoreCase("c")) {
            editor.putInt("c", i);
            rB1.setTextColor(ccolor[i]);
        }
        else if(fiji.equalsIgnoreCase("l")) {
            editor.putInt("l", i);
            rB2.setTextColor(ccolor[i]);
        }
        else if(fiji.equalsIgnoreCase("u")) {
            editor.putInt("u", i);
            rB3.setTextColor(color[i]);
        }
        else if(fiji.equalsIgnoreCase("a")) {
            editor.putInt("a", i);
            rB4.setTextColor(color[i]);
        }
        editor.apply();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (sp.contains("c"))
                rB1.setTextColor(ccolor[sp.getInt("c", 10)]);
            else
                rB1.setTextColor(ccolor[0]);
            if (sp.contains("l"))
                rB2.setTextColor(ccolor[sp.getInt("l", 10)]);
            else
                rB2.setTextColor(ccolor[0]);
            if (sp.contains("a"))
                rB4.setTextColor(color[sp.getInt("a", 10)]);
            else
                rB4.setTextColor(color[5]);
            if (sp.contains("u"))
                rB3.setTextColor(color[sp.getInt("u", 10)]);
            else
                rB3.setTextColor(color[0]);
        }
    }

    @Override
    public void onClicked(int digit) {
        assign(digit);
    }
}
