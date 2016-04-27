package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by Pankaj on 26-01-2015.
 */
public class Naming extends Fragment implements View.OnClickListener {
    EditText user1, user2, user3, user4;
    Button b1, b2;
    RelativeLayout r2, r3, r4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int players=0;
    Event1 list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.frag_name, container, false);
        r2=(RelativeLayout)view.findViewById(R.id.rllll2);
        r3=(RelativeLayout)view.findViewById(R.id.rllll3);
        r4=(RelativeLayout)view.findViewById(R.id.rllll4);
        user1=(EditText)view.findViewById(R.id.editText);
        user2=(EditText)view.findViewById(R.id.editText1);
        user3=(EditText)view.findViewById(R.id.editText2);
        user4=(EditText)view.findViewById(R.id.editText3);
        b1=(Button) view.findViewById(R.id.ok);
        b2=(Button) view.findViewById(R.id.cancel);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        setup();
        return view;
    }


    private void setup() {
        if(sp.contains("playing")) {
            if(sp.getString("playing", "multi").equalsIgnoreCase("multi")) {
                if (sp.contains("level"))
                    players = sp.getInt("player", 2);
                if (players == 2)
                    r2.setVisibility(View.VISIBLE);
                else if(players==3) {
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                }else if(players==4) {
                    r2.setVisibility(View.VISIBLE);
                    r3.setVisibility(View.VISIBLE);
                    r4.setVisibility(View.VISIBLE);
                }
            }else
                players=1;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        sp=activity.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        if(activity instanceof Event1){
            list=(Event1) activity;
        } else{
            Log.i("tag", "atb Activity Must implement the interface");
        }
    }

	@Override
    public void onDetach() {
        super.onDetach();
        list.awesome(9);
        list=null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ok:
                if(players==1) {
                    if(user1.getText().toString().length()>2)
                        editor.putString("uname1", user1.getText().toString());
                    else
                        editor.putString("uname1", "User");
                }
                else if(players==2) {
                    if(user1.getText().toString().length()>2)
                    editor.putString("name1", user1.getText().toString());
                    else
                        editor.putString("name1", "User1");
                    if(user2.getText().toString().length()>2)
                    editor.putString("name2", user2.getText().toString());
                    else
                        editor.putString("name2", "User2");
                }else if(players==3) {
                    if(user1.getText().toString().length()>2)
                        editor.putString("name1", user1.getText().toString());
                    else
                        editor.putString("name1", "User1");
                    if(user2.getText().toString().length()>2)
                        editor.putString("name2", user2.getText().toString());
                    else
                        editor.putString("name2", "User2");
                    if(user3.getText().toString().length()>2)
                        editor.putString("name3", user3.getText().toString());
                    else
                        editor.putString("name3", "User3");
                }else if(players==4) {
                    if(user1.getText().toString().length()>2)
                        editor.putString("name1", user1.getText().toString());
                    else
                        editor.putString("name1", "User1");
                    if(user2.getText().toString().length()>2)
                        editor.putString("name2", user2.getText().toString());
                    else
                        editor.putString("name2", "User2");
                    if(user3.getText().toString().length()>2)
                        editor.putString("name3", user3.getText().toString());
                    else
                        editor.putString("name3", "User3");
                    if(user4.getText().toString().length()>2)
                        editor.putString("name4", user4.getText().toString());
                    else
                        editor.putString("name4", "User4");
                }
                editor.apply();
                break;
            case R.id.cancel:
                editor.putString("name1", "User1");
                editor.putString("name2", "User2");
                editor.putString("name3", "User3");
                editor.putString("name4", "User4");
                editor.apply();
            break;
        }
        list.awesome(1);
    }
}
