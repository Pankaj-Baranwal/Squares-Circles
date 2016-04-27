package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pankaj on 20-01-2015.
 */
public class Lifeline extends Fragment implements View.OnClickListener {
    TextView hint;
    private Interface_lifeline listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lifelines, container, false);
        Button l1=(Button)view.findViewById(R.id.button31);
        Button l2=(Button)view.findViewById(R.id.button32);
        hint=(TextView) view.findViewById(R.id.hint);
        l1.setOnClickListener(this);
        l2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        hint.setText("Look at the comments for further info!");
        switch (v.getId()){
            case R.id.button31:
                listener.clicked_life(1);
                break;
            case R.id.button32:
                listener.clicked_life(2);
                break;
        }
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
        listener.clicked_life(9);
        super.onDetach();
        listener=null;
    }
}
