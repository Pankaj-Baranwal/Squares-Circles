package com.pankaj.chowkhunda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;

import java.util.Random;

/**
 * Created by Pankaj on 14-12-2014.
 */
public class Store extends Activity implements View.OnClickListener {
    TextView total,  tmove, ttimer, tdcoins, thalved, text1, text2, text3, text4;
    Button fullv, sum, move, timer, dcoins, halved;
    int to=0, m=0, ti=0, d=0, h=0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    int seq=0;
    int n4=0;
    String pro;
    boolean mIsPremium = false;
    boolean mzerotaxes = false;
    static final String SKU_PREMIUM = "premium";
    static final String SKU_EXTRA_MONEY = "extra_money";
    static final String SKU_ZERO_TAXES = "zero_taxes";
    static final int RC_REQUEST = 10001;
    IabHelper mHelper;
    boolean volume=true;
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    char[] pay;
    static final int S1=R.raw.button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.act_open_trans, R.anim.act_close_scale);
        setContentView(R.layout.store);
        total=(TextView) findViewById(R.id.tvst2);
        tmove=(TextView) findViewById(R.id.ivst1);
        ttimer=(TextView) findViewById(R.id.ivst2);
        tdcoins=(TextView) findViewById(R.id.ivst3);
        thalved=(TextView) findViewById(R.id.ivst4);
        text1=(TextView) findViewById(R.id.tvst4);
        text2=(TextView) findViewById(R.id.tvst6);
        text3=(TextView) findViewById(R.id.tvst8);
        text4=(TextView) findViewById(R.id.tvst10);
        sum=(Button) findViewById(R.id.btst1);
        move=(Button) findViewById(R.id.btst2);
        timer=(Button) findViewById(R.id.btst3);
        dcoins=(Button) findViewById(R.id.btst4);
        halved=(Button) findViewById(R.id.btst5);
        fullv=(Button) findViewById(R.id.button30);
        sp=getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        if(sp.contains("tcoins")){
            to=sp.getInt("tcoins", 0);
            total.setText(String.valueOf(sp.getInt("tcoins", 0)));
        }
        if(sp.contains("halved")){
            h=sp.getInt("halved", 0);
            if(h>0 && n4==0){
                n4=3;
            }
            thalved.setText(String.valueOf(sp.getInt("halved", 0)));
            if(n4>0){
                text1.setText("100 \nMove 2 times consecutively!");
                text2.setText("100 \nTimer increases by 15 seconds");
                text3.setText("250 \n 2X points for the next game");
                text4.setText("500 \nNext 4 purchases cost half!");
            }else{
                text1.setText("200 \nMove 2 times consecutively!");
                text2.setText("200 \nTimer increases by 15 seconds");
                text3.setText("500 \n 2X points for the next game");
                text4.setText("1000 \nNext 4 purchases cost half!");
            }
        }
        if(sp.contains("move")){
            m=sp.getInt("move", 0);
            tmove.setText(String.valueOf(sp.getInt("move", 0)));}
        if(sp.contains("timer")){
            ti=sp.getInt("timer", 0);
            ttimer.setText(String.valueOf(sp.getInt("timer", 0)));}
        if(sp.contains("dcoins")){
            d=sp.getInt("dcoins", 0);
            tdcoins.setText(String.valueOf(sp.getInt("dcoins", 0)));}
        if(sp.contains("zt")){
            mzerotaxes=sp.getBoolean("zt", false);
        }
        fullv.setOnClickListener(this);
        sum.setOnClickListener(this);
        move.setOnClickListener(this);
        timer.setOnClickListener(this);
        dcoins.setOnClickListener(this);
        halved.setOnClickListener(this);
        String base64EncodedPublicKey;
            base64EncodedPublicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAli8zSkQ9DRNO7EBoTa7Qz3rJMubEhMIMUmnCtkwBr7xhl62eIVkr4v0wAw71NoyNVScA3iVHYdgkhQssLoSlOk4cjbydvQB76fNVNprY7z3Jq+13fEJXSxxcGd9mOqs0veDnrxXUKpRiDQZxvRZFn0NoBPZj2s0c1pz1ds1ES2agAre4jIDjLkNsA4QUiL4I/51Gqp0Q5Oj2u7KJWRReEHjY1OwCr1nsxmPnYDkh1X3Hs9bZOWBB7xelrYWYe/XzqIbatHYArz7Wt4+b3B14WpArTXqWyMCQ/GwisaHU/cZUoaKUtPT/l7ywdA27hhFxkNPKT11UBmzNPjU3I/MLmwIDAQAB";
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }
                if (mHelper == null) return;
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });
        volume=sp.getBoolean("volume", true);
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
    }


    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            if(mIsPremium){
                editor.putBoolean("premium", true);
                editor.apply();
            }

            Purchase halved = inventory.getPurchase(SKU_ZERO_TAXES);
            mzerotaxes = (halved != null &&
                    verifyDeveloperPayload(halved));
            if (mzerotaxes){
                editor.putInt("halved", 100);
                editor.apply();
            }

            Purchase extra_money = inventory.getPurchase(SKU_EXTRA_MONEY);
            if (extra_money != null && verifyDeveloperPayload(extra_money)) {
                mHelper.consumeAsync(inventory.getPurchase(SKU_EXTRA_MONEY), mConsumeFinishedListener);
                editor.putInt("tcoins", (sp.getInt("move", 0)+1000));
                editor.apply();
            }

            updateUi();
            setWaitScreen(false);
        }
    };


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


            if (purchase.getSku().equals(SKU_EXTRA_MONEY)) {
                editor.putInt("tcoins", (sp.getInt("move", 0) + 1000));
                editor.apply();
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
                alert("Thank you for purchasing!");
                updateUi();
                setWaitScreen(false);
            }
            else if (purchase.getSku().equals(SKU_PREMIUM)) {
                alert("Thank you for upgrading to premium!");
                mIsPremium = true;
                updateUi();
                setWaitScreen(false);
            }
            else if (purchase.getSku().equals(SKU_ZERO_TAXES)) {
                alert("Thank you for purchasing!");
                mzerotaxes=true;
                editor.putBoolean("zt", true);
                editor.putInt("halved", 100);
                editor.apply();
                updateUi();
                setWaitScreen(false);
            }
        }
    };


    void setup(){
        setWaitScreen(true);
        pay=new char[]{'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F'};
        Random random=new Random();
        String payload = "P"+pay[random.nextInt(pay.length)]+pay[random.nextInt(pay.length)]+random.nextInt(10000);
        editor.putString("payload", payload);
        editor.apply();

        if(pro.equalsIgnoreCase("premium")) {
            mHelper.launchPurchaseFlow(this, SKU_PREMIUM, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        }else if(pro.equalsIgnoreCase("coins")){
            mHelper.launchPurchaseFlow(this, SKU_EXTRA_MONEY, RC_REQUEST,
                    mPurchaseFinishedListener, payload);
        }else if(pro.equalsIgnoreCase("halved")){
            mHelper.launchPurchaseFlow(this,
                    SKU_ZERO_TAXES, IabHelper.ITEM_TYPE_SUBS,
                    RC_REQUEST, mPurchaseFinishedListener, payload);
        }
    }


    // Called when consumption is complete
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {

            if (mHelper == null) return;
            if (result.isSuccess()) {
                editor.putInt("tcoins", (sp.getInt("tcoins", 0) + 1000));
                editor.apply();

            }
            else {
                complain("Oh ,snap! Something went wrong. :(" + result);
            }
            updateUi();
            setWaitScreen(false);
        }
    };

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
        int z=0;
        switch (v.getId()){
            case R.id.button30:
                if (mIsPremium) {
                    complain("No need! You already have Premium! Isn't that awesome?");
                    return;
                }else {
                    pro="premium";
                    alerts("Confirm purchase? (Full Version for $1.49)");
                }
                break;
            case R.id.btst1:
                pro="coins";
                alerts("Confirm purchase? (1000 coins for $1)");
                break;
            case R.id.btst2:
                z=200;
                if(n4!=0)
                    z = 100;
                if(to>=z){
                    if(n4!=0)
                        n4-=1;
                    to-=z;
                    editor.putInt("move", m+1);
                    editor.putInt("tcoins", to);
                    editor.apply();
                    tmove.setText(String.valueOf(sp.getInt("move", 0)));
                    total.setText(String.valueOf(to));
                }else {
                    pro="coins";
                    alerts("Confirm purchase? (1000 coins for $1)");
                }
                break;
            case R.id.btst3:
                z=200;
                if(n4!=0)
                    z= 100;
                if(to>=z){
                    to-=z;
                    if(n4!=0)
                        n4-=1;
                    editor.putInt("tcoins", to);
                    editor.putInt("timer", ti+1);
                    editor.apply();
                    ttimer.setText(String.valueOf(sp.getInt("timer", 0)));
                    total.setText(String.valueOf(to));
                }else {
                    pro="coins";
                    alerts("Confirm purchase? (1000 coins for $1)");
                }
                break;
            case R.id.btst4:
                z=500;
                if(n4!=0)
                    z= 250;
                if(to>=z){
                    to -= z;
                    if(n4!=0)
                        n4-=1;
                    editor.putInt("dcoins", d + 1);
                    editor.putInt("tcoins", to);
                    editor.apply();
                    tdcoins.setText(String.valueOf(sp.getInt("dcoins", 0)));
                    total.setText(String.valueOf(to));
                }else {
                    pro="coins";
                    alerts("Confirm purchase? (1000 coins for $1)");
                }
                break;
            case R.id.btst5:
                z=1000;
                if(n4!=0)
                    z= 500;
                if(to>=z){
                    to-=z;
                    if(n4!=0)
                        n4-=1;
                    editor.putInt("tcoins", to);
                    total.setText(String.valueOf(to));
                    editor.putInt("halved", h+1);
                    editor.apply();
                    thalved.setText(String.valueOf(sp.getInt("halved", 0)));
                }else {
                    if (!mHelper.subscriptionsSupported()) {
                        complain("Subscriptions not supported on your device yet. Sorry!");
                    }else if(mzerotaxes) {
                        complain("No need! You already have too many! Isn't that awesome?");
                    }else
                        {
                        pro = "halved";
                        alerts("Confirm purchase? (100 ZeroTaxes for $1)");
                    }
                }
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.act_open_scale, R.anim.act_close_trans);
        if(seq==0)
        finish();
    }

    public void pomClick(View view){
        try {
            Intent intent = new Intent(this, Class.forName("com.pankaj.chowkhunda.Start"));
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        // very important:
        if (mHelper != null) {
            mHelper.dispose();
            mHelper = null;
        }
    }

    // updates UI to reflect model
    public void updateUi() {
        // update the car color to reflect premium status or lack thereof
        findViewById(R.id.button30).setVisibility(mIsPremium ? View.INVISIBLE : View.VISIBLE);

        // "Upgrade" button is only visible if the user is not premium
        findViewById(R.id.button30).setClickable(!mIsPremium);
        total.setText(String.valueOf(sp.getInt("tcoins", 0)));
        thalved.setText(String.valueOf(sp.getInt("halved", 0)));
    }

    // Enables or disables the "please wait" screen.
    void setWaitScreen(boolean set) {
        findViewById(R.id.main_screen).setVisibility(set ? View.GONE : View.VISIBLE);
        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
        if(set) seq=1;
        else seq=0;
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
}
