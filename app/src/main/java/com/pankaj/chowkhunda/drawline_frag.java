package com.pankaj.chowkhunda;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Pankaj on 23-01-2015.
 */
public class drawline_frag extends View {
    private Bitmap canvasBitmap;
    private Canvas drawCanvas;
    private Paint drawPaint, canvasPaint, spaint, paints;
    private Path drawPath, drawPath1;
    private int color3, color4;
    private int[] ccolor, color;
    int d=0, e=0;
    float ax, bx, ex, ay, by, ey;
    boolean mcanter1=false;
    boolean mcanter3=false;
    int rows=2, cols=2;
    float xdiff = 0;
    float ydiff = 0;
    float[][] yused, xused, xleftx, xlefty, yleftx, ylefty;
    float[] yusedx, xusedy;
    int x, y, radius, dont=0;
    static int totxm = 0;
    static int totym = 0;
    int cunter = 0;
    float[] xcoords, ycoords;
    int canter = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Context contexts;
    Event1 main;


    public drawline_frag(Context context, AttributeSet attrs) {
        super(context, attrs);
        sp = context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        if(!isInEditMode()) {
            editor = sp.edit();
            editor.apply();
        }
        setupDrawing();
        contexts = context;
    }

    private void setupDrawing() {
        ccolor = new int[]{0xff224040, 0xff847034, 0xff5ab78d, 0xff886bb8, 0xff7f2fd4, 0xff606f7f, 0xffb64e80, 0xff64394d, 0xff1f8df0, 0xff1f40a5, 0xd2a5e6};
        color = new int[]{0xffff4040, 0xff8470ff, 0xffdab7ed, 0xfff86b78, 0xff7fffd4, 0xff00ff7f, 0xff76ee00, 0xff60994d, 0xffff8d00, 0xff4040ff, 0xd0f0e0};
        xused = new float[2][2];
        yused = new float[2][2];
        xusedy= new float[2];
        yusedx = new float [2];
        xlefty = new float[2][2];
        xleftx = new float[2][2];
        totxm = 2;
        yleftx = new float[2][2];
        ylefty = new float[2][2];
        totym = 2;
        xcoords = new float[cols];
        ycoords = new float[rows];
        color3 = Color.rgb(10, 250, 250);
        color4 = Color.rgb(250, 60, 5);
        radius = 10;
        drawPath = new Path();
        drawPaint = new Paint();
        paints = new Paint();
        spaint = new Paint();
        drawPath1 = new Path();
        size();
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        spaint.setAntiAlias(true);
        spaint.setColor(0xFF9400D3);
        spaint.setStyle(Paint.Style.FILL);
        spaint.setStrokeCap(Paint.Cap.ROUND);
        paints.setAntiAlias(true);
        paints.setStyle(Paint.Style.STROKE);
        paints.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    private void size() {
        drawPaint.setStrokeWidth(10);
        paints.setStrokeWidth(7);
        if (sp.contains("size")) {
            int size = sp.getInt("size", 10);
            if (size == 5) {
                drawPaint.setStrokeWidth(5);
                radius = 5;
                paints.setStrokeWidth(6);
            } else if (size == 10) {
                drawPaint.setStrokeWidth(10);
                radius = 10;
                paints.setStrokeWidth(7);
            } else if (size == 15) {
                drawPaint.setStrokeWidth(13);
                radius = 13;
                paints.setStrokeWidth(10);
            }
        }
    }

    private void colors() {
        if (!isInEditMode()){
            if (sp.contains("c"))
                paints.setColor(ccolor[sp.getInt("c", 10)]);
            else
            paints.setColor(ccolor[0]);
            if (sp.contains("l"))
                drawPaint.setColor(ccolor[sp.getInt("l", 10)]);
            else
                drawPaint.setColor(ccolor[2]);
            if (sp.contains("a"))
                color4 = (color[sp.getInt("a", color4)]);
            else
                color4=color[5];
            if (sp.contains("u"))
                color3 = (color[sp.getInt("u", color3)]);
            else
                color3=color[0];
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        x = getWidth();
        y = getHeight();
        xdiff = x / (2 * (cols + 1));
        ydiff = y / (2 * (rows + 1));
        drawPaint.setStyle(Paint.Style.STROKE);
        if (cunter == 0) {
            colors();
            xcoords[0] = x / (cols + 1);
            for (int i = 1; i < cols; i++) {
                xcoords[i] = (2 * xdiff) + xcoords[i - 1];
            }
            ycoords[0] = y / (rows + 1);
            for (int j = 1; j < rows; j++) {
                ycoords[j] = (2 * ydiff) + ycoords[j - 1];
            }
            ylefty[0][0]=ycoords[0];
            ylefty[0][1]=ycoords[1];
            yleftx[0][0]=xcoords[0];
            yleftx[0][1]=xcoords[0];
            ylefty[1][0]=ycoords[0];
            ylefty[1][1]=ycoords[1];
            yleftx[1][0]=xcoords[1];
            yleftx[1][1]=xcoords[1];
            xleftx[0][0]=xcoords[0];
            xleftx[0][1]=xcoords[1];
            xlefty[0][0]=ycoords[0];
            xlefty[0][1]=ycoords[0];
            xleftx[1][0]=xcoords[0];
            xleftx[1][1]=xcoords[1];
            xlefty[1][0]=ycoords[1];
            xlefty[1][1]=ycoords[1];
            cunter = 1;
        }
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                canvas.drawCircle(xcoords[i], ycoords[j], radius, paints);
            }
        }
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawPath(drawPath1, spaint);
        if(canter>0) {
            main.awesome(canter);
        }
    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if(canter==0 || canter==2) {
            dont=0;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ax = event.getX();
                    ay = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    bx = event.getX();
                    by = event.getY();
                    checkLine(bx, by);
                    break;
                default:
                    return false;
            }
        }else if(dont!=1) {
            main.awesome(7);
            dont=1;
        }
        return true;
    }

    public void checkLine(float ax, float ay) {
        if (ay <= ycoords[0]) {
            if ((ax < xcoords[0]) || (ax > xcoords[cols - 1])) {
                //TODO: This is a dead zone!
            } else {
                for (int i = 1; i < cols; i++) {
                    if (ax < xcoords[i]) {
                        drawLine(xcoords[i - 1], ycoords[0], xcoords[i], ycoords[0]);
                        break;
                    }
                }
            }
        } else if (ay >= ycoords[rows - 1]) {
            if ((ax < xcoords[0]) || (ax > xcoords[cols - 1])) {
                //TODO: This is a dead zone!
            } else {
                for (int i = 0; i < cols; i++) {
                    if (ax < xcoords[i]) {
                        drawLine(xcoords[i - 1], ycoords[rows - 1], xcoords[i], ycoords[rows - 1]);
                        break;
                    }
                }
            }
        } else if (ax <= xcoords[0]) {
            if ((ay < ycoords[0]) || (ay > ycoords[rows - 1])) {
                //TODO: This is a dead zone!
            } else {
                for (int i = 1; i < rows; i++) {
                    if (ay < ycoords[i]) {
                        drawLine(xcoords[0], ycoords[i - 1], xcoords[0], ycoords[i]);
                        break;
                    }
                }
            }
        } else if (ax >= xcoords[cols - 1]) {
            if ((ay < ycoords[0]) || (ay > ycoords[rows - 1])) {
                //TODO: This is a dead zone!
            } else {
                for (int i = 1; i < rows; i++) {
                    if (ay < ycoords[i]) {
                        drawLine(xcoords[cols - 1], ycoords[i - 1], xcoords[cols - 1], ycoords[i]);
                        break;
                    }
                }
            }
        } else {
            int xi = 0, yj = 0;
            for (int i = 0; i < cols - 1; i++) {
                if (ax >= xcoords[i] & ax < xcoords[i + 1]) {
                    for (int j = 0; j < rows - 1; j++) {
                        if (ay >= ycoords[j] & ay < ycoords[j + 1]) {
                            xi = i;
                            yj = j;
                        }
                    }
                }
            }
            float line1 = ((xcoords[xi] - xcoords[xi + 1]) * (ay - ycoords[yj + 1])) - ((ycoords[yj] - ycoords[yj + 1]) * (ax - xcoords[xi + 1]));
            float line2 = ((xcoords[xi] - xcoords[xi + 1]) * (ay - ycoords[yj])) - ((ycoords[yj + 1] - ycoords[yj]) * (ax - xcoords[xi + 1]));
            if (line1 < 0 && line2 < 0) {
                drawLine(xcoords[xi], ycoords[yj + 1], xcoords[xi + 1], ycoords[yj + 1]); // Horizontal down
            } else if (line1 > 0 && line2 < 0) {
                drawLine(xcoords[xi + 1], ycoords[yj], xcoords[xi + 1], ycoords[yj + 1]); // Vertical right
            } else if (line1 < 0 && line2 > 0) {
                drawLine(xcoords[xi], ycoords[yj], xcoords[xi], ycoords[yj + 1]); // Vertical left
            } else if (line1 > 0 && line2 > 0) {
                drawLine(xcoords[xi], ycoords[yj], xcoords[xi + 1], ycoords[yj]); // Horizontal up
            }
        }
    }

    public void drawLine(float gx, float gy, float hx, float hy) {
        if (gx == hx || gy == hy) {
            if (((gx - hx) == (2 * xdiff)) || ((gy - hy) == (2 * ydiff)) || ((hx - gx) == ((2 * xdiff)) || (hy - gy) == (2 * ydiff))) {
                if (gy == hy) {
                    if ((gy != xusedy[0]) && (gy != xusedy[1])) {
                        xsed(gx, hx, gy);
                        canter += 1;
                        drawPath.moveTo(gx, gy);
                        drawPath.lineTo(hx, hy);
                        drawCanvas.drawPath(drawPath, drawPaint);
                        drawPath.reset();
                        invalidate(); // causes onDraw to execute
                    } else
                        main.awesome(5);
                } else if (gx == hx) {
                    if ((gx != yusedx[0]) && ((gx != yusedx[1]))) {
                        ysed(gy, hy, gx);
                        canter += 1;
                        drawPath.moveTo(gx, gy);
                        drawPath.lineTo(hx, hy);
                        drawCanvas.drawPath(drawPath, drawPaint);
                        drawPath.reset();
                        invalidate();
                    } else
                        main.awesome(5);
                }
            }
        } else
            main.awesome(6);
    }


    private void xsed(float gx, float hx, float gy) { //function to update xused and xleft
        int k = xloc(gx, gy);
        xused[k][0] = gx;
        xused[k][1] = hx;
        xusedy[d] = gy;
        d+=1;
        xleftx[k][0] = xleftx[k][1] = xlefty[k][0] = xlefty[k][1] = 0;
        totxm -= 1;
    }

    public void ysed(float gy, float hy, float gx) { // function to update yused and yleft
        int j = yloc(gx, gy);
        yused[j][0] = gy;
        yused[j][1] = hy;
        yusedx[e]=gx;
        e+=1;
        ylefty[j][1] = ylefty[j][0] = yleftx[j][0] = yleftx[j][1] = 0;
        totym -= 1;
    }

    public int yloc(float gx, float gy) {   // only for one less row in y
        int o = 0;
        if(gx!=xcoords[0])
            o=1;
        int l = 0;
        if(gy!=ycoords[0])
            l=1;
        return l + o;
    }

    public int xloc(float gx, float gy) {   // only for one less column in x
        int j = 0;
        if(gx!=xcoords[0])
            j=1;
        int l = 0;
        if(gy!=ycoords[0])
            l=1;
        return j + l;
    }

    public void amove(){
        if(totxm>0) {
            float[][] listx1 = new float[totxm][2];
            float[][] listx2 = new float[totxm][2];
            xinitialise(listx1, listx2);
            if (listx1[0][0] != 0)
                aline(listx1[0][0], listx2[0][0], listx1[0][1], listx2[0][1]);
        }
        else if(totym>0){
            float[][] listy1 = new float[totym][2];
            float[][] listy2 = new float[totym][2];
            yinitialise(listy1, listy2);
            if(listy1[0][0]!=0)
                aline(listy1[0][0], listy2[0][0], listy1[0][1], listy2[0][1]);
        }
    }

    private void xinitialise(float[][] listx1, float[][] listx2){
        if(totxm>0) {
            int j = 0;
            for (int i = 0; i < 2; i++) {
                if (xleftx[i][0] != 0) {
                    listx1[j][0] = xleftx[i][0];
                    listx1[j][1] = xleftx[i][1];
                    listx2[j][0] = xlefty[i][0];
                    listx2[j][1] = xlefty[i][1];
                    j += 1;
                }
            }
        }
    }

    private void yinitialise(float[][] listy1, float[][] listy2){
        if(totym>0){
            int k = 0;
            for (int i = 0; i < 2; i++) {
                if (ylefty[i][0] != 0) {
                    listy1[k][0] = yleftx[i][0];
                    listy1[k][1] = yleftx[i][1];
                    listy2[k][0] = ylefty[i][0];
                    listy2[k][1] = ylefty[i][1];
                    k += 1;
                }
            }
        }
    }

    public void aline(float gx, float gy, float hx, float hy) {
        if (gx == hx || gy == hy) {
            if (gy < hy) ysed(gy, hy, gx);
            else if (gy > hy) ysed(hy, gy, hx);
            else if (gx < hx) xsed(gx, hx, gy);
            else if (gx > hx) xsed(hx, gx, hy);
            if(totxm==0 && totym==0){
                drawPath1.moveTo(xcoords[0]+4, ycoords[0]+4);
                drawPath1.lineTo(xcoords[0]+4, ycoords[1]-4);
                drawPath1.lineTo(xcoords[1]-4, ycoords[1]-4);
                drawPath1.lineTo(xcoords[1]-4, ycoords[0]+4);
                drawCanvas.drawPath(drawPath1, spaint);
                drawPath1.reset();
            }
            drawPath.moveTo(gx, gy);
            drawPath.lineTo(hx, hy);
            drawCanvas.drawPath(drawPath, drawPaint);
            drawPath.reset();
            canter+= 1;
            invalidate();
        }
    }

    public void init(Fragment fragment){
        if(fragment instanceof Event1){
            main=(Event1) fragment;
        } else{
            Log.i("tag", "atb Activity Must implement the interface");
        }
    }
}