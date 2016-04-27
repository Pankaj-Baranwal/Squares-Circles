package com.pankaj.chowkhunda;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.*;
import android.os.Process;

/**
 * Created by Pankaj on 26-01-2015.
 */
public class Soundpool extends Service {
    static SoundPool sP;
    static int soundID;
    boolean loaded=false;
    Context cont;
    static final int S1=R.raw.button;

    public void setup(Context context){
        cont=context;
        sP = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        sP.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        soundID = sP.load(context, R.raw.button, 1);
    }

    public void runa() {
        AudioManager aM = (AudioManager) cont.getSystemService(Context.AUDIO_SERVICE);
        float actualVolume = (float) aM.getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) aM.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = actualVolume / maxVolume;
        if (loaded) {

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
