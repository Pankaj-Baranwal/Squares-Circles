package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Pankaj on 22-01-2015.
 */
public class Tutorial extends Fragment implements Event1{
    ImageButton close;
    CheckBox dsa;
    TextView hint;
    drawline_frag minu;
    private Interface_lifeline listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tutorial, container, false);
        close=(ImageButton) view.findViewById(R.id.close);
        dsa=(CheckBox)view.findViewById(R.id.dsa);
        hint=(TextView) view.findViewById(R.id.hint);
        minu=(drawline_frag) view.findViewById(R.id.frag_draw);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clicked_life(9);
            }
        });
        dsa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    listener.clicked_life(1);
                else
                    listener.clicked_life(0);
            }
        });
        minu.init(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof Interface_lifeline){
            listener=(Interface_lifeline) activity;
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
    public void awesome(int over) {
        if(over==1 || over==3) {
            if (over == 1) {
                final String[] a = new String[]{"Now, ", "Now, its ", "Now, its android's ", "Now, its android's turn ", "Now, its android's turn to move"};
                new CountDownTimer(1600, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished > 599)
                            hint.setText(a[(int) (((1600 - millisUntilFinished) / 200))]);
                    }

                    @Override
                    public void onFinish() {
                        minu.mcanter1 = true;
                        minu.amove();
                    }
                }.start();
            } else {
                final String[] a = new String[]{"Again, ", "Again, its ", "Again, its android's ", "Again, its android's turn ", "Again, its android's turn to move"};
                new CountDownTimer(1200, 200) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished > 199)
                            hint.setText(a[(int) (((1200 - millisUntilFinished) / 200))]);
                    }

                    @Override
                    public void onFinish() {
                        minu.mcanter3 = true;
                        minu.amove();
                    }
                }.start();
            }
        }else if(over==2){
            minu.mcanter1=false;
            minu.mcanter3=false;
            hint.setText("Now, its your turn. Touch anywhere.");
        }else if(over==4)
            hint.setText("Hurrah! End of Tutorial!");
        else if(over==5)
            hint.setText("Noo, Don't overdraw! Make a NEW line.");
        else if(over==6)
            hint.setText("Make lines between NEAREST 2 points.");
        else if(over==7)
            hint.setText("Its not your turn. -_-");
    }
}

