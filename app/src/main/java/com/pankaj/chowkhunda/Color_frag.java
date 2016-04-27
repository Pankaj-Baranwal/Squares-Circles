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
public class Color_frag extends Fragment implements View.OnClickListener {
    Button one, two, three, four, five, six, seven, eight, nine, ten;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Interface_color listener;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.color_frags, container, false);
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



/*
01-20 23:53:28.169  26584-26584/com.pankaj.chowkhunda D/GC﹕ <tid=26584> OES20 ===> GC Version   : GC Ver rls_pxa988_KK44_GC13.20
01-20 23:53:28.229  26584-26584/com.pankaj.chowkhunda D/OpenGLRenderer﹕ Enabling debug mode 0
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda I/dalvikvm﹕ Could not find method android.view.ViewGroup.onNestedScrollAccepted, referenced from method android.support.v7.internal.widget.ActionBarOverlayLayout.onNestedScrollAccepted
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda W/dalvikvm﹕ VFY: unable to resolve virtual method 11420: Landroid/view/ViewGroup;.onNestedScrollAccepted (Landroid/view/View;Landroid/view/View;I)V
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda D/dalvikvm﹕ VFY: replacing opcode 0x6f at 0x0000
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda I/dalvikvm﹕ Could not find method android.view.ViewGroup.onStopNestedScroll, referenced from method android.support.v7.internal.widget.ActionBarOverlayLayout.onStopNestedScroll
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda W/dalvikvm﹕ VFY: unable to resolve virtual method 11426: Landroid/view/ViewGroup;.onStopNestedScroll (Landroid/view/View;)V
01-20 23:53:53.313  26584-26584/com.pankaj.chowkhunda D/dalvikvm﹕ VFY: replacing opcode 0x6f at 0x0000
01-20 23:53:53.323  26584-26584/com.pankaj.chowkhunda I/dalvikvm﹕ Could not find method android.support.v7.internal.widget.ActionBarOverlayLayout.stopNestedScroll, referenced from method android.support.v7.internal.widget.ActionBarOverlayLayout.setHideOnContentScrollEnabled
01-20 23:53:53.323  26584-26584/com.pankaj.chowkhunda W/dalvikvm﹕ VFY: unable to resolve virtual method 8995: Landroid/support/v7/internal/widget/ActionBarOverlayLayout;.stopNestedScroll ()V
01-20 23:53:53.323  26584-26584/com.pankaj.chowkhunda D/dalvikvm﹕ VFY: replacing opcode 0x6e at 0x000e
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda I/dalvikvm﹕ Could not find method android.content.res.TypedArray.getChangingConfigurations, referenced from method android.support.v7.internal.widget.TintTypedArray.getChangingConfigurations
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda W/dalvikvm﹕ VFY: unable to resolve virtual method 394: Landroid/content/res/TypedArray;.getChangingConfigurations ()I
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda D/dalvikvm﹕ VFY: replacing opcode 0x6e at 0x0002
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda I/dalvikvm﹕ Could not find method android.content.res.TypedArray.getType, referenced from method android.support.v7.internal.widget.TintTypedArray.getType
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda W/dalvikvm﹕ VFY: unable to resolve virtual method 416: Landroid/content/res/TypedArray;.getType (I)I
01-20 23:53:53.333  26584-26584/com.pankaj.chowkhunda D/dalvikvm﹕ VFY: replacing opcode 0x6e at 0x0002
01-20 23:53:53.514  26584-26584/com.pankaj.chowkhunda I/dalvikvm-heap﹕ Grow heap (frag case) to 8.296MB for 2183016-byte allocation

 */

/*
01-21 10:51:48.485    8055-8055/com.pankaj.chowkhunda D/GC﹕ <tid=8055> OES20 ===> GC Version   : GC Ver rls_pxa988_KK44_GC13.20
01-21 10:51:48.576    8055-8055/com.pankaj.chowkhunda D/OpenGLRenderer﹕ Enabling debug mode 0
01-21 10:51:52.109    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ setProgressDrawable drawableHeight = 16
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.119    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecodeFrame 20140421 Rev.6376
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ This is decoding
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ decoding stream->hasLength()
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageDecParseHeader call : QM
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage parsing for decoding ok
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode :  QmageHeader.NinePatched 0
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : QmageHeader Height() 16 Width() : 606 sampleSize : 1
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ Qmage Make Color table
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ SkBitmap::kIndex8_Config == config && 1 == sampleSize
01-21 10:51:52.129    8055-8055/com.pankaj.chowkhunda E/Qmage﹕ onDecode : return true QM
01-21 10:51:52.269    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: left = 0
01-21 10:51:52.269    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: top = 0
01-21 10:51:52.269    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: right = 200
01-21 10:51:52.269    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: bottom = 100
01-21 10:51:52.269    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: mProgressDrawable.setBounds()
01-21 10:52:02.049    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ setProgressDrawable drawableHeight = 16
01-21 10:52:02.299    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: left = 0
01-21 10:52:02.299    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: top = 0
01-21 10:52:02.299    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: right = 200
01-21 10:52:02.299    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: bottom = 100
01-21 10:52:02.299    8055-8055/com.pankaj.chowkhunda D/ProgressBar﹕ updateDrawableBounds: mProgressDrawable.setBounds()
01-21 10:52:07.104    8055-8055/com.pankaj.chowkhunda I/dalvikvm-heap﹕ Grow heap (frag case) to 10.094MB for 2334056-byte allocation
01-21 10:52:10.497    8055-8055/com.pankaj.chowkhunda I/dalvikvm-heap﹕ Grow heap (frag case) to 12.533MB for 2334056-byte allocation
01-21 10:52:33.009    8055-8059/com.pankaj.chowkhunda I/dalvikvm﹕ Total arena pages for JIT: 11
01-21 10:52:33.009    8055-8059/com.pankaj.chowkhunda I/dalvikvm﹕ Total arena pages for JIT: 12
01-21 10:52:33.009    8055-8059/com.pankaj.chowkhunda I/dalvikvm﹕ Total arena pages for JIT: 13
01-21 10:52:33.009    8055-8059/com.pankaj.chowkhunda I/dalvikvm﹕ Total arena pages for JIT: 14

 */