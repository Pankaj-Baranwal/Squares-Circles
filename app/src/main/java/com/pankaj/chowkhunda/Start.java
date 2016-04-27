package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;

import java.util.Random;

/**
 * Created by Pankaj on 13-12-2014.
 */
public class Start extends Activity implements View.OnClickListener, Interface_lifeline {
    Button rate, buy, settings, about;
    ImageButton p1, p2, p3, p4;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    FrameLayout ade;
    int seq=0;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    boolean volume=true;
    static final int S1= com.pankaj.chowkhunda.R.raw.button;
    boolean mIsPremium = false;
    static final String SKU_PREMIUM = "premium";
    static final int RC_REQUEST = 10001;
    IabHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.pankaj.chowkhunda.R.layout.start);
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAli8zSkQ9DRNO7EBoTa7Qz3rJMubEhMIMUmnCtkwBr7xhl62eIVkr4v0wAw71NoyNVScA3iVHYdgkhQssLoSlOk4cjbydvQB76fNVNprY7z3Jq+13fEJXSxxcGd9mOqs0veDnrxXUKpRiDQZxvRZFn0NoBPZj2s0c1pz1ds1ES2agAre4jIDjLkNsA4QUiL4I/51Gqp0Q5Oj2u7KJWRReEHjY1OwCr1nsxmPnYDkh1X3Hs9bZOWBB7xelrYWYe/XzqIbatHYArz7Wt4+b3B14WpArTXqWyMCQ/GwisaHU/cZUoaKUtPT/l7ywdA27hhFxkNPKT11UBmzNPjU3I/MLmwIDAQAB";
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }
                if (mHelper == null) return;
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });
        p1=(ImageButton)findViewById(R.id.player1);
        p2=(ImageButton)findViewById(R.id.player2);
        p3=(ImageButton)findViewById(R.id.player3);
        p4=(ImageButton)findViewById(R.id.player4);
        settings = (Button) findViewById(R.id.settings);
        about = (Button) findViewById(R.id.about);
        ade=(FrameLayout) findViewById(R.id.fll1);
        rate=(Button)findViewById(R.id.rate);
        buy=(Button) findViewById(R.id.buy);
        rate.setOnClickListener(this);
        buy.setOnClickListener(this);
        p1.setOnClickListener(this);
        p2.setOnClickListener(this);
        p3.setOnClickListener(this);
        p4.setOnClickListener(this);
        settings.setOnClickListener(this);
        about.setOnClickListener(this);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        if(sp.contains("volume"))
            volume=sp.getBoolean("volume", true);
        else{
            volume=true;
            editor.putBoolean("volume", true);
            editor.apply();
        }
        if(volume) {
            sP = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            sP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    loaded = true;
                }
            });
            soundID = sP.load(this, R.raw.button, 1);
        }
        int[] a=dimen();
        if(a[0]<=320)
            editor.putInt("size", 5);
        else if(a[0]<=640)
            editor.putInt("size", 10);
        else
            editor.putInt("size", 15);
        editor.apply();
        if(!sp.contains("dsa")) {
            Log.i("tag", "dsa=false");
            frags();
        }
        else{
            if(!sp.getBoolean("dsa", false)) {
                frags();
                Log.i("tag", "dsa=false");
            }
        }
    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            /*
             * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().
             */

            // Do we have the premium upgrade?
            Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            updateUi();
            setWaitScreen(false);
        }
    };


    private void frags() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fll1, new Tutorial());
            ft.addToBackStack("Tutorial");
            ft.commit();
        }

    @Override
    public void onClick(View v) {
        if(volume) {
            AudioManager aM = (AudioManager) getSystemService(AUDIO_SERVICE);
            float actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
            float maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            float volume = actualVolume / maxVolume;
            if (loaded) {
                sP.play(soundID, volume, volume, 1, 0, 1f);
            }
        }
        Intent intent;
        switch(v.getId()){
            case R.id.levels:
                intent = new Intent(this, Sublevels.class);
                intent.putExtra("called", "Start");
                startActivity(intent);
                break;
            case R.id.player1:
                editor.putString("playing", "modes");
                editor.apply();
                intent = new Intent(this, Levels.class);
                startActivity(intent);
                break;
            case R.id.player2:
                editor.putString("playing", "multi");
                editor.putInt("player", 2);
                editor.apply();
                intent=new Intent(this, Type_multi.class);
                startActivity(intent);
                break;
            case R.id.player3:
                editor.putString("playing", "multi");
                editor.putInt("player", 3);
                editor.apply();
                intent=new Intent(this, Type_multi.class);
                startActivity(intent);
                break;
            case R.id.player4:
                editor.putString("playing", "multi");
                editor.putInt("player", 4);
                editor.apply();
                intent=new Intent(this, Type_multi.class);
                startActivity(intent);
                break;
            case R.id.buy:
                if (mIsPremium) {
                    complain("No need! You already have Premium! Isn't that awesome?");
                    return;
                }else
                    alerts("Confirm purchase? (Full Version for $1.49)");
                break;
            case R.id.rate:
                intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.pankaj.chowkhunda"));
                startActivity(intent);
                break;
            case R.id.settings:
                intent=new Intent(Start.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent=new Intent(Start.this, Store.class);
                startActivity(intent);
                break;
        }
    }


    public int[] dimen(){
        int widthPixels;
        int heightPixels;
        WindowManager w= getWindowManager();
        Display d=w.getDefaultDisplay();
        DisplayMetrics metrics= new DisplayMetrics();
        d.getMetrics(metrics);
        widthPixels=metrics.widthPixels;
        heightPixels= metrics.heightPixels;
        if(Build.VERSION.SDK_INT>=14&& Build.VERSION.SDK_INT<17){
            try{
                widthPixels=(Integer)Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels=(Integer)Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored){
            }
            if(Build.VERSION.SDK_INT>=17){
                try {
                    Point realSize = new Point();
                    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                    widthPixels = realSize.x;
                    heightPixels = realSize.y;
                }
                catch(Exception ignored){
                }
            }
        }
        int s[]=new int[]{widthPixels, heightPixels};
        editor.putInt("width", s[0]);
        editor.putInt("height", s[1]);
        editor.apply();
        return s;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(seq==0)
        finish();
    }

    @Override
    public void clicked_life(int select) {
        if(select==1)
            editor.putBoolean("dsa", true);
        else if(select==0)
            editor.putBoolean("dsa", false);
        else if(select==9)
            ade.setVisibility(View.GONE);
        editor.apply();
    }

    void complain(String message) {
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        bld.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /** Verifies the developer payload of a purchase. */
    boolean verifyDeveloperPayload(Purchase p) {
        return sp.getString("payload", "Paa1").matches(p.getDeveloperPayload());
    }

    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                setWaitScreen(false);
                return;
            }


            if (purchase.getSku().equals(SKU_PREMIUM)) {
                // bought the premium upgrade!
                alert("Thank you for upgrading to premium!");
                mIsPremium = true;
                updateUi();
                setWaitScreen(false);
            }
        }
    };

    // We're being destroyed. It's important to dispose of the helper here!
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) {
            mHelper.dispose();
            mHelper = null;
        }
    }

    // updates UI to reflect model
    public void updateUi() {
        if(mIsPremium) {
            findViewById(R.id.buy).setVisibility(View.GONE);
            editor.putBoolean("premium", true);
            editor.apply();
        }else{
            findViewById(R.id.buy).setVisibility(View.VISIBLE);
            editor.putBoolean("premium", false);
            editor.apply();
        }
    }

    // Enables or disables the "please wait" screen.
    void setWaitScreen(boolean set) {
        findViewById(R.id.rll1).setVisibility(set ? View.GONE : View.VISIBLE);
        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
        if(set) seq=1;
        else seq=0;
    }

    void alerts(String a){
        AlertDialog.Builder bld= new AlertDialog.Builder(this);
        bld.setMessage(a);
        bld.setPositiveButton("Noo!", null);
        bld.setNegativeButton("Yes!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setup();
            }
        });
        bld.create().show();
    }

    void setup(){
        setWaitScreen(true);
        char[] pay=new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G'};
        Random random=new Random();
        String payload = "P"+pay[random.nextInt(pay.length)]+pay[random.nextInt(pay.length)]+random.nextInt(10000);
        editor.putString("payload", payload);
        editor.apply();
        mHelper.launchPurchaseFlow(this, SKU_PREMIUM, RC_REQUEST,
                mPurchaseFinishedListener, payload);
    }





}


//    private void setup() {
//        int[] loc1= new int[]{0};


//        Log.i("tag", "atb" + left1 + " " + left2 + " "+ left3 + " " + left4 + " " + top1+ " " + top4);
  //  }

//    private void animate(View v1, View v2, View v3, View v4, View v5, View v6) {
//      float scale = getResources().getDisplayMetrics().density;
//    ObjectAnimator anims1 = ObjectAnimator.ofFloat(v1, "y", top1, bottom4);
//  ObjectAnimator anims2 = ObjectAnimator.ofFloat(v2, "x", left4, right5);
//        ObjectAnimator anims3 = ObjectAnimator.ofFloat(v3, "x", left5, left6);
//      ObjectAnimator anims4 = ObjectAnimator.ofFloat(v4, "y", bottom6, top3);
//    ObjectAnimator anims5 = ObjectAnimator.ofFloat(v5, "x", right3, left2);
//  ObjectAnimator anims6 = ObjectAnimator.ofFloat(v6, "x", right2, left1);
//        animSet= new AnimatorSet();
//      animSet.setDuration(5000);
//    animSet.play(anims1).with(anims6);
//  animSet.start();
//}