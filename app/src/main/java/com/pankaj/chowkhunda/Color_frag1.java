package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Pankaj on 20-01-2015.
 */
public class Color_frag1 extends Fragment implements View.OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, ten;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Interface_color listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.color_frags1, container, false);
        one=(Button)view.findViewById(R.id.one);
        two=(Button)view.findViewById(R.id.two);
        three=(Button)view.findViewById(R.id.three);
        four=(Button)view.findViewById(R.id.four);
        five=(Button)view.findViewById(R.id.five);
        six=(Button)view.findViewById(R.id.six);
        seven=(Button)view.findViewById(R.id.seven);
        eight=(Button)view.findViewById(R.id.eight);
        nine=(Button)view.findViewById(R.id.nine);
        ten=(Button)view.findViewById(R.id.ten);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        ten.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Interface_color){
            listener=(Interface_color) activity;
        } else{
            Log.i("tag", "atb Activity Must implement the interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.one:
                listener.onClicked(0);
                break;
            case R.id.two:
                listener.onClicked(1);
                break;
            case R.id.three:
                listener.onClicked(2);
                break;
            case R.id.four:
                listener.onClicked(3);
                break;
            case R.id.five:
                listener.onClicked(4);
                break;
            case R.id.six:
                listener.onClicked(5);
                break;
            case R.id.seven:
                listener.onClicked(6);
                break;
            case R.id.eight:
                listener.onClicked(7);
                break;
            case R.id.nine:
                listener.onClicked(8);
                break;
            case R.id.ten:
                listener.onClicked(9);
                break;
        }
    }
}
