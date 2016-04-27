package com.pankaj.chowkhunda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Pankaj on 19-12-2014.
 */
public class Firstworst extends Activity{
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coming);
        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            a=extras.getString("called");
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
            Intent intent=new Intent(this, Class.forName("com.pankaj.chowkhunda."+a));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
