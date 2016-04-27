package com.pankaj.chowkhunda;

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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Pankaj Baranwal on 14-08-2015.
 */
public class Testing_algo extends View {
    private Bitmap canvasBitmap;
    private Canvas drawCanvas;
    private Paint drawPaint, canvasPaint, spaint, paints;
    private Path drawPath, drawPath1;
    private int paintColor = 0xFF9400D3;
    private int[] ccolor, color;
    int color1 = Color.rgb(250, 60, 5);
    int color2 = Color.rgb(10, 250, 250);
    int color3 = 0xFFFFFF00;
    int color4 = 0xFF009900;
    Random rand;
    float ax, bx, ay, by;
    int rows, cols;
    String uscore1, ascore1, ascore2, ascore3;
    int userscore1 = 0, andscore1 = 0, andscore2 = 0, andscore3 = 0;
    int[] xposs, yposs;
    float xdiff = 0;
    float ydiff = 0;
    float yused[][];
    float xused[][];
    float ylefty[][], yleftx[][];
    float xleftx[][], xlefty[][];
    int x, y, radius;
    int dont = 0;
    int duble;
    int larst;
    int totxm = 0;
    int totym = 0;
    int cunter = 0;
    float xcoords[];
    float ycoords[];
    int canter = 0;
    int mover = 0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Toast toast;
    Context contexts;
    int pts = 20;
    int lpts = 5;
    Event eve;
    int players = 2;
    Minsquares min;
    int dirt = 0;
    int clouse = 7;
    int[] squares;
    int crow;
    int confused = 0;


    public Testing_algo(Context context, AttributeSet attrs) {
        super(context, attrs);
        sp = context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor = sp.edit();
        setupDrawing();
        contexts = context;
    }


    private void setupDrawing() {
        ccolor = new int[]{0xff224040, 0xff847034, 0xff5ab78d, 0xff886bb8, 0xff7f2fd4, 0xff606f7f, 0xffb64e80, 0xff64394d, 0xff1f8df0, 0xff1f40a5, 0xd2a5e6};
        color = new int[]{0xffff4040, 0xff8470ff, 0xffdab7ed, 0xfff86b78, 0xff7fffd4, 0xff00ff7f, 0xff76ee00, 0xff60994d, 0xffff8d00, 0xff4040ff, 0xd0f0e0};
        rowcolumn();
        canter = 0;
        if (sp.contains("level"))
            players = sp.getInt("level", 2);
        else players = 2;
        if (sp.contains("uname1"))
            uscore1 = sp.getString("uname1", "User");
        else uscore1 = "User";
        ascore1 = "Yahba:";
        ascore2 = "Nurav:";
        ascore3 = "Hbuhsir:";
        xused = new float[rows * (cols - 1)][2];
        yused = new float[(rows - 1) * cols][2];
        xlefty = new float[(cols - 1) * rows][2];
        xleftx = new float[(cols - 1) * rows][2];
        totxm = (cols - 1) * rows;
        yleftx = new float[(rows - 1) * cols][2];
        ylefty = new float[(rows - 1) * cols][2];
        totym = (rows - 1) * cols;
        xcoords = new float[cols];
        ycoords = new float[rows];
        xposs = new int[8];
        yposs = new int[8];
        rand = new Random();
        radius = 10;
        drawPath = new Path();
        drawPaint = new Paint();
        spaint = new Paint();
        drawPath1 = new Path();
        paints = new Paint();
        size();
        drawPaint.setAntiAlias(true);
        drawPaint.setColor(paintColor);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        spaint.setAntiAlias(true);
        spaint.setColor(paintColor);
        spaint.setStyle(Paint.Style.FILL);
        spaint.setStrokeCap(Paint.Cap.ROUND);
        paints.setAntiAlias(true);
        paints.setColor(Color.rgb(250, 250, 10));
        paints.setStyle(Paint.Style.STROKE);
        paints.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        int dcoin = 0;
        if (sp.contains("dcoins"))
            dcoin = sp.getInt("dcoins", 0);
        if (dcoin > 0) {
            editor.putInt("dcoins", dcoin - 1);
            editor.apply();
            pts = 2 * pts;
            lpts = 2 * lpts;
        }
    }

    private void size() {
        if (isInEditMode()) {
            drawPaint.setStrokeWidth(10);
            paints.setStrokeWidth(7);
        } else {
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
    }

    private void colors() {
        if (!isInEditMode()) {
            int iuf;
            if (sp.contains("c"))
                paints.setColor(ccolor[sp.getInt("c", 10)]);
            else
                paints.setColor(ccolor[0]);
            if (sp.contains("l"))
                drawPaint.setColor(ccolor[sp.getInt("l", 10)]);
            else
                drawPaint.setColor(ccolor[3]);
            if (sp.contains("a"))
                color2 = (color[sp.getInt("a", color1)]);
            else
                color2 = color[4];
            if (sp.contains("u"))
                color1 = (color[sp.getInt("u", color3)]);
            else
                color1 = color[9];
            for (iuf = 0; iuf < 11; iuf++) {
                if (color[iuf] != color2 && color[iuf] != color1) {
                    color3 = color[iuf];
                    break;
                }
            }
            for (int iuh = iuf; iuh < 11; iuh++) {
                if (color[iuh] != color2 && color[iuh] != color1 && color[iuh] != color3) {
                    color4 = color[iuh];
                    break;
                }
            }
        }
    }

    private void rowcolumn() {
        if (isInEditMode()) {
            cols = 5;
            rows = 5;
        } else {
            if (sp.contains("sublevel")) {
                if (sp.getInt("sublevel", 1) == 1) {
                    rows = 3;
                    cols = 3;
                } else if (sp.getInt("sublevel", 1) == 10) {
                    rows = 8;
                    cols = 8;
                }
                for (int ki = 2; ki <= 9; ki++) {
                    if (sp.getInt("sublevel", 1) == ki) {
                        if ((ki % 2) != 0) {
                            rows = (ki / 2) + 3;
                            cols = (ki / 2) + 3;
                        } else {
                            rows = (ki / 2) + 2;
                            cols = (ki / 2) + 3;
                        }
                    }
                }
            } else {
                rows = 6;
                cols = 5;
            }
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
        drawPaint.setStyle(Paint.Style.STROKE);
        if (cunter == 0) {
            x = getWidth();
            y = getHeight();
            xdiff = x / (2 * (cols + 1));
            ydiff = y / (2 * (rows + 1));
            colors();
            xcoords[0] = x / (cols + 1);
            for (int i = 1; i < cols; i++) {
                xcoords[i] = (2 * xdiff) + xcoords[i - 1];
            }
            ycoords[0] = y / (rows + 1);
            for (int j = 1; j < rows; j++) {
                ycoords[j] = (2 * ydiff) + ycoords[j - 1];
            }
            for (int i = 1; i <= (rows - 1); i++) {
                int t = 0;
                for (int j = i - 1; j < ((rows - 1) * cols); ) {
                    ylefty[j][0] = ycoords[i - 1];
                    ylefty[j][1] = ycoords[i];
                    yleftx[j][0] = xcoords[t];
                    yleftx[j][1] = xcoords[t];
                    j += (rows - 1);
                    t = t + 1;
                }
            }
            for (int i = 1; i <= (cols - 1); i++) {
                int t = 0;
                for (int j = i - 1; j < (rows * (cols - 1)); ) {
                    xleftx[j][0] = xcoords[i - 1];
                    xleftx[j][1] = xcoords[i];
                    xlefty[j][0] = ycoords[t];
                    xlefty[j][1] = ycoords[t];
                    j += (cols - 1);
                    t += 1;
                }
            }
            cunter = 1;
        }
        eve.onOcc(players, canter);
        for (int j = 0; j < rows; j++) {
            canvas.drawCircle(xcoords[0], ycoords[j], radius, paints);
        }
        for (int i = 1; i < cols; i++) {
            canvas.drawCircle(xcoords[i], ycoords[0], radius, paints);
        }
        for (int i = 1; i < cols; i++) {
            for (int j = 1; j < rows; j++) {
                canvas.drawCircle(xcoords[i], ycoords[j], radius, paints);
            }
        }
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawPath(drawPath1, spaint);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (canter == 0) {
            dont = 0;
            if (toast != null)
                toast.cancel();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ax = event.getX();
                    ay = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    bx = event.getX();
                    by = event.getY();
                    mover = 0;
                    checkLine(bx, by);
                    break;
                default:
                    return false;
            }
        } else if (dont != 1) {
            CharSequence text = "Don't CHEAT! It's not your turn!";
            dont = 1;
            toast = Toast.makeText(contexts, text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        return true;
    }

    public void drawLine(float gx, float gy, float hx, float hy) {
        if (gx == hx || gy == hy) {
            if (((gx - hx) == (2 * xdiff)) || ((gy - hy) == (2 * ydiff)) || ((hx - gx) == ((2 * xdiff)) || (hy - gy) == (2 * ydiff))) {
                if (gy == hy) {
                    if ((gx != xused[xloc(gx, gy)][0])) {
                        xsed(gx, hx, gy);
                        int square = isSquare(gx, hx, gy, hy);
                        if (square != 0) {
                            if (square == 5 || square == 6)
                                userscore1 += 2 * pts;
                            else userscore1 += pts;
                            fill(gx, gy, hx, hy);
                            canter = 0;
                        } else {
                            userscore1 += lpts;
                            larst = 0;
                            if (confused > 0) {
                                canter = 0;
                                confused -= 1;
                            } else
                                canter = 1;
                            larst = 0;
                        }
                        drawPath.moveTo(gx, gy);
                        drawPath.lineTo(hx, hy);
                        drawCanvas.drawPath(drawPath, drawPaint);
                        drawPath.reset();
                        invalidate(); // causes onDraw to execute
                    }
                } else if (gx == hx) {
                    if ((gy != yused[yloc(gx, gy)][0])) {
                        ysed(gy, hy, gx);
                        int square = isSquare(gx, hx, gy, hy);
                        if (square != 0) {
                            if (square == 5 || square == 6)
                                userscore1 += 2 * pts;
                            else userscore1 += pts;
                            fill(gx, gy, hx, hy);
                            canter = 0;
                        } else {
                            userscore1 += lpts;
                            larst = 0;
                            if (confused > 0) {
                                canter = 0;
                                confused -= 1;
                            } else
                                canter = 1;
                            larst = 0;
                        }
                        drawPath.moveTo(gx, gy);
                        drawPath.lineTo(hx, hy);
                        drawCanvas.drawPath(drawPath, drawPaint);
                        drawPath.reset();
                        invalidate(); // causes onDraw to execute
                    }
                }
            } else {
                CharSequence text = "Make lines between the nearest two points only!";
                toast = Toast.makeText(contexts, text, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }
    }

    private void xsed(float gx, float hx, float gy) { //function to update xused and xleft
        int k = xloc(gx, gy);
        xused[k][0] = gx;
        xused[k][1] = hx;
        xleftx[k][0] = xleftx[k][1] = xlefty[k][0] = xlefty[k][1] = 0;
        totxm -= 1;
    }

    public void ysed(float gy, float hy, float gx) { // function to update yused and yleft
        int j = yloc(gx, gy);
        yused[j][0] = gy;
        yused[j][1] = hy;
        ylefty[j][1] = ylefty[j][0] = yleftx[j][0] = yleftx[j][1] = 0;
        totym -= 1;
    }

    public int yloc(float gx, float gy) {   // only for one less row in y
        int o = 0;
        for (int i = (int) gx; i != xcoords[0]; ) {
            o += 1;
            i -= 2 * xdiff;
        }
        int l = 0;
        for (int i = (int) gy; i != ycoords[0]; ) {
            l += 1;
            i -= 2 * ydiff;
        }
        return l + (o * (rows - 1));
    }

    public int xloc(float gx, float gy) {   // only for one less column in x
        int j = 0;
        for (int i = (int) gx; i != xcoords[0]; ) {
            j += 1;
            i -= 2 * xdiff;
        }
        int l = 0;
        for (int i = (int) gy; i != ycoords[0]; ) {
            l += 1;
            i -= 2 * ydiff;
        }
        return j + (l * (cols - 1));
    }


    public void amove() {
        if (totxm >= 1 && totym < 1) {
            float[][] listx1 = new float[totxm][2];
            float[][] listx2 = new float[totxm][2];
            xinitialise(listx1, listx2);
            for (int i = 0; i < totxm; i++) {
                if (isSquare(listx1[i][0], listx1[i][1], listx2[i][0], listx2[i][1]) != 0) {
                    aline(listx1[i][0], listx2[i][0], listx1[i][1], listx2[i][1]);
                    break;
                } else if (i >= totxm - 1)
                    axline();
            }
        } else if (totym >= 1 && totxm < 1) {
            float[][] listy1 = new float[totym][2];
            float[][] listy2 = new float[totym][2];
            yinitialise(listy1, listy2);
            for (int ku = 0; ku < totym; ku++) {
                if (isSquare(listy1[ku][0], listy1[ku][1], listy2[ku][0], listy2[ku][1]) != 0) {
                    aline(listy1[ku][0], listy2[ku][0], listy1[ku][1], listy2[ku][1]);
                    break;
                } else if (ku >= totym - 1)
                    ayline();
            }
        } else if (totxm >= 1 && totym >= 1) {
            float[][] listx1 = new float[totxm][2];
            float[][] listx2 = new float[totxm][2];
            float[][] listy1 = new float[totym][2];
            float[][] listy2 = new float[totym][2];
            xinitialise(listx1, listx2);
            yinitialise(listy1, listy2);
            for (int i = 0; i < totxm; i++) {
                if (isSquare(listx1[i][0], listx1[i][1], listx2[i][0], listx2[i][1]) != 0) {
                    aline(listx1[i][0], listx2[i][0], listx1[i][1], listx2[i][1]);
                    break;
                } else if (i >= (totxm - 1)) {
                    for (int ku = 0; ku < totym; ku++) {
                        if (isSquare(listy1[ku][0], listy1[ku][1], listy2[ku][0], listy2[ku][1]) != 0) {
                            aline(listy1[ku][0], listy2[ku][0], listy1[ku][1], listy2[ku][1]);
                            break;
                        } else if (ku >= totym - 1) {
                            int r1 = rand.nextInt(2);
                            if ((totym != 0 && r1 == 0) || (totym != 0 && totxm == 0))
                                ayline();
                            else if ((totxm != 0 && r1 != 0) || (totxm != 0 && totym == 0))
                                axline();
                        }
                    }
                }
            }
        }
    }

    private void xinitialise(float[][] listx1, float[][] listx2) {
        if (totxm > 0) {
            int j = 0;
            for (int i = 0; i < xleftx.length; i++) {
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

    private void yinitialise(float[][] listy1, float[][] listy2) {
        if (totym > 0) {
            int k = 0;
            for (int i = 0; i < ylefty.length; i++) {
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


    private void axline() {
        float[][] listx1 = new float[totxm][2];
        float[][] listx2 = new float[totxm][2];
        int xsqare[];
        xsqare = new int[totxm];
        xinitialise(listx1, listx2);
        for (int i = 0; i < totxm; i++) {
            canSquare(listx1[i][0], listx1[i][1], listx2[i][0], listx2[i][1]);
            for (int u = 0; u < xposs.length; u++) {
                if (xposs[u] != 0 || yposs[u] != 0) {
                    xsqare[i] = 1;
                    break;
                }
            }
        }
        int cuntus = 0;
        for (int aXsqare : xsqare) {
            if (aXsqare == 0) {
                cuntus += 1;
            }
        }
        if (cuntus != 0) {
            int zerosq[] = new int[cuntus];
            int bu = 0;
            for (int t = 0; t < xsqare.length; t++) {
                if (xsqare[t] == 0) {
                    zerosq[bu] = t;
                    bu += 1;
                }
            }
            int r3 = rand.nextInt(cuntus);
            aline(listx1[zerosq[r3]][0], listx2[zerosq[r3]][0], listx1[zerosq[r3]][1], listx2[zerosq[r3]][1]);
        } else {
            min = new Minsquares(xleftx, xlefty, yleftx, ylefty, xcoords, ycoords, cols, rows, xdiff, ydiff, totxm, totym);
            clouse = 1;
            clouse1();
        }
    }

    private void clouse1() {
        if (clouse == 1) {
            min.checkmin();
            awesome();
            crow = 0;
            if (dirt != 1) {
                if (squares.length > 1) {
                    Random rand = new Random();
                    crow = rand.nextInt(squares.length);
                } else if (squares.length == 1) crow = 0;
                clouse = 7;
                squares[crow] = 0;
                aline(min.dxpt[crow][0], min.dypt[crow][0], min.dxpt[crow][1], min.dypt[crow][1]);
            } else if (totxm != 0) {
                float[][] listx1 = new float[totxm][2];
                float[][] listx2 = new float[totxm][2];
                xinitialise(listx1, listx2);
                aline(listx1[0][0], listx2[0][0], listx1[0][1], listx2[0][1]);
            } else if (totym != 0) {
                float[][] listy1 = new float[totym][2];
                float[][] listy2 = new float[totym][2];
                yinitialise(listy1, listy2);
                aline(listy1[0][0], listy2[0][0], listy1[0][1], listy2[0][1]);
            }
        }
    }

    private void ayline() {
        float[][] listy1 = new float[totym][2];
        float[][] listy2 = new float[totym][2];
        int ysqare[];
        ysqare = new int[totym];
        yinitialise(listy1, listy2);
        for (int i = 0; i < totym; i++) {
            canSquare(listy1[i][0], listy1[i][1], listy2[i][0], listy2[i][1]);
            for (int u = 0; u < xposs.length; u++) {
                if (xposs[u] != 0 || yposs[u] != 0) {
                    ysqare[i] = 1;
                    break;
                }
            }
        }
        int cuntus = 0;
        for (int aYsqare : ysqare) {
            if (aYsqare == 0) {
                cuntus += 1;
            }
        }
        if (cuntus != 0) {
            int zerosq[] = new int[cuntus];
            int bu = 0;
            for (int t = 0; t < ysqare.length; t++) {
                if (ysqare[t] == 0) {
                    zerosq[bu] = t;
                    bu += 1;
                }
            }
            int r3 = rand.nextInt(cuntus);
            aline(listy1[zerosq[r3]][0], listy2[zerosq[r3]][0], listy1[zerosq[r3]][1], listy2[zerosq[r3]][1]);
        } else {
            min = new Minsquares(xleftx, xlefty, yleftx, ylefty, xcoords, ycoords, cols, rows, xdiff, ydiff, totxm, totym);
            clouse = 1;
            clouse1();
        }
    }


    public void aline(float gx, float gy, float hx, float hy) {
        if (gx == hx || gy == hy) {
            larst = 0;
            if (gy < hy) ysed(gy, hy, gx);
            else if (gy > hy) ysed(hy, gy, hx);
            else if (gx < hx) xsed(gx, hx, gy);
            else if (gx > hx) xsed(hx, gx, hy);
            drawPath.moveTo(gx, gy);
            drawPath.lineTo(hx, hy);
            int square = isSquare(gx, hx, gy, hy);
            if (square != 0) {
                if (square != 5 && square != 6) {
                    if (canter == 1)
                        andscore1 += pts;
                    else if (canter == 2)
                        andscore2 += pts;
                    else if (canter == 3)
                        andscore3 += pts;
                } else {
                    if (canter == 1)
                        andscore1 += 2 * pts;
                    else if (canter == 2)
                        andscore2 += 2 * pts;
                    else if (canter == 3)
                        andscore3 += 2 * pts;
                }
                mover = 1;
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                if (gx < hx || gy < hy) {
                    fill(gx, gy, hx, hy);
                } else
                    fill(hx, hy, gx, gy);
            } else {
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                if (canter == 1)
                    andscore1 += lpts;
                else if (canter == 2)
                    andscore2 += lpts;
                else if (canter == 3)
                    andscore3 += lpts;
                if (canter != players - 1)
                    canter += 1;
                else
                    canter = 0;
            }
            invalidate();
        }
    }

    public void fill(float hx, float hy, float gx, float gy) {
        int square = isSquare(hx, gx, hy, gy);
        if (canter == 1)
            spaint.setColor(color2);
        else if (canter == 0)
            spaint.setColor(color1);
        else if (canter == 2)
            spaint.setColor(color3);
        else if (canter == 3)
            spaint.setColor(color4);
        if (square != 0) {
            if (square == 1) {
                drawpath(hx - (2 * xdiff) + 5, hy + 5, hx - 5, hy + 5, gx - 5, gy - 5, gx - (2 * xdiff) + 5, gy - 5);
            } else if (square == 2) {
                drawpath(hx + 5, hy + 5, hx - 5 + (2 * xdiff), hy + 5, gx - 5 + (2 * xdiff), gy - 5, gx + 5, gy - 5);
            } else if (square == 3) {
                drawpath(hx + 5, hy - 5, hx + 5, hy + 5 - (2 * ydiff), gx - 5, gy + 5 - (2 * ydiff), gx - 5, gy - 5);
            } else if (square == 4) {
                drawpath(hx + 5, hy + 5, hx + 5, hy - 5 + (2 * ydiff), gx - 5, gy - 5 + (2 * ydiff), gx - 5, gy + 5);
            } else if (square == 5) {
                drawpath(hx - (2 * xdiff) + 5, hy + 5, hx - 5, hy + 5, gx - 5, gy - 5, gx - (2 * xdiff) + 5, gy - 5);
                drawpath(hx + 5, hy + 5, hx - 5 + (2 * xdiff), hy + 5, gx - 5 + (2 * xdiff), gy - 5, gx + 5, gy - 5);
            } else if (square == 6) {
                drawpath(hx + 5, hy - 5, hx + 5, hy + 5 - (2 * ydiff), gx - 5, gy + 5 - (2 * ydiff), gx - 5, gy - 5);
                drawpath(hx + 5, hy + 5, hx + 5, hy - 5 + (2 * ydiff), gx - 5, gy - 5 + (2 * ydiff), gx - 5, gy + 5);
            }
            drawCanvas.drawPath(drawPath1, spaint);
            drawPath1.reset();
        }
    }

    private void drawpath(float as, float sd, float df, float fg, float gh, float hj, float jk, float kl) {
        drawPath1.moveTo(as, sd);
        drawPath1.lineTo(df, fg);
        drawPath1.lineTo(gh, hj);
        drawPath1.lineTo(jk, kl);
    }

    public int isSquare(float xa, float xb, float ya, float yb) {
        if (xa == xb) {
            int left, right;
            left = 0;
            right = 0;
            if (xa == xcoords[0]) {
                left = 1;
            }
            if (xb == xcoords[cols - 1]) {
                right = 1;
            }
            int l = xloc(xa, ya);
            int m = yloc(xa, ya);
            if (left == 0 && right == 0) {
                if (((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] == 0)) && ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0)))
                    return 5;
                else if (((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] == 0)))
                    return 1; //left=1
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0))
                    return 2; //right=2
                else return 0;
            } else if (left == 1) {
                if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0))
                    return 2;
                else return 0;
            } else {
                if ((xleftx[xloc((xa - (2 * xdiff)), ya)][0] == 0) && (xleftx[xloc((xa - (2 * xdiff)), (ya + (2 * ydiff)))][0] == 0) && (yleftx[m - rows + 1][0] == 0))
                    return 1;
                else return 0;
            }
        } else if (ya == yb) {
            int top, bottom;
            top = 0;
            bottom = 0;
            if (ya == ycoords[0]) top = 1;
            if (yb == ycoords[rows - 1]) bottom = 1;
            int l = yloc(xa, ya);
            int m = xloc(xa, ya);
            if (top == 0 && bottom == 0) {
                if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] == 0)) && ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0)))
                    return 6;
                else if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] == 0)))
                    return 3; // top=3
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0))
                    return 4; //bottom=4
                else return 0;
            } else if (top == 1) {
                if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0))
                    return 4;
                else return 0;
            } else {
                if ((yleftx[yloc(xa, (ya - (2 * ydiff)))][0] == 0) && (yleftx[yloc((xa + (2 * xdiff)), (ya - 2 * ydiff))][0] == 0) && (xleftx[m - cols + 1][0] == 0))
                    return 3;
                else return 0;
            }
        } else return 0;
    }

    public void canSquare(float xa, float xb, float ya, float yb) {
        xposs = new int[8];
        yposs = new int[8];
        if (xa == xb) {
            int left, right;
            left = 0;
            right = 0;
            duble = 0;
            if (xa == xcoords[0]) {
                left = 1;
            }
            if (xb == xcoords[cols - 1]) {
                right = 1;
            }
            int l = xloc(xa, ya);
            int m = yloc(xa, ya);
            if (left == 0 && right == 0) {
                if ((xleftx[l - 1][0] != 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] == 0))
                    xposs[0] = 1;
                else if ((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] != 0) && (yleftx[m - rows + 1][0] == 0))
                    xposs[0] = 1;
                else if ((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] != 0))
                    yposs[0] = 1;
                else {
                    xposs[0] = 0;
                    yposs[0] = 0;
                }
                if ((xleftx[l][0] != 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0))
                    xposs[1] = 1;
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] != 0) && (yleftx[m + rows - 1][0] == 0))
                    xposs[1] = 1;
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] != 0))
                    yposs[1] = 1;
                else {
                    xposs[1] = 0;
                    yposs[1] = 0;
                }
            } else if (left == 1) {
                if ((xleftx[l][0] != 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0))
                    xposs[2] = 1;
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] != 0) && (yleftx[m + rows - 1][0] == 0))
                    xposs[2] = 1;
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] != 0))
                    yposs[2] = 1;
                else {
                    xposs[2] = 0;
                    yposs[2] = 0;
                }
            } else {
                if ((xleftx[xloc((xa - (2 * xdiff)), ya)][0] != 0) && (xleftx[xloc((xa - (2 * xdiff)), (ya + 2 * ydiff))][0] == 0) && (yleftx[m - rows + 1][0] == 0))
                    xposs[3] = 1;
                else if ((xleftx[xloc((xa - (2 * xdiff)), ya)][0] == 0) && (xleftx[xloc((xa - (2 * xdiff)), (ya + 2 * ydiff))][0] != 0) && (yleftx[m - rows + 1][0] == 0))
                    xposs[3] = 1;
                else if ((xleftx[xloc((xa - (2 * xdiff)), ya)][0] == 0) && (xleftx[xloc((xa - (2 * xdiff)), (ya + 2 * ydiff))][0] == 0) && (yleftx[m - rows + 1][0] != 0))
                    yposs[3] = 1;
                else {
                    xposs[3] = 0;
                    yposs[3] = 0;
                }
            }
        } else if (ya == yb) {
            int top, bottom;
            top = 0;
            bottom = 0;
            if (ya == ycoords[0]) top = 1;
            if (yb == ycoords[rows - 1]) bottom = 1;
            int l = yloc(xa, ya);
            int m = xloc(xa, ya);
            if (top == 0 && bottom == 0) {
                if (((yleftx[l - 1][0] != 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] == 0)))
                    yposs[4] = 1;
                else if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] != 0) && (xleftx[m - cols + 1][0] == 0)))
                    yposs[4] = 1;
                else if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] != 0)))
                    xposs[4] = 1;
                else {
                    xposs[4] = 0;
                    yposs[4] = 0;
                }
                if ((yleftx[l][0] != 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0))
                    yposs[5] = 1;
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] != 0) && (xleftx[m + cols - 1][0] == 0))
                    yposs[5] = 1;
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] != 0))
                    xposs[5] = 1;
                else {
                    xposs[5] = 0;
                    yposs[5] = 0;
                }
            } else if (top == 1) {
                if ((yleftx[l][0] != 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0))
                    yposs[6] = 1;
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] != 0) && (xleftx[m + cols - 1][0] == 0))
                    yposs[6] = 1;
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] != 0))
                    xposs[6] = 1;
                else {
                    yposs[6] = 0;
                    xposs[6] = 0;
                }
            } else {
                if ((yleftx[yloc(xa, (ya - (2 * ydiff)))][0] != 0) && (yleftx[yloc((xa + (2 * xdiff)), (ya - 2 * ydiff))][0] == 0) && (xleftx[m - cols + 1][0] == 0))
                    yposs[7] = 1;
                else if ((yleftx[yloc(xa, (ya - (2 * ydiff)))][0] == 0) && (yleftx[yloc((xa + (2 * xdiff)), (ya - 2 * ydiff))][0] != 0) && (xleftx[m - cols + 1][0] == 0))
                    yposs[7] = 1;
                else if ((yleftx[yloc(xa, (ya - (2 * ydiff)))][0] == 0) && (yleftx[yloc((xa + (2 * xdiff)), (ya - 2 * ydiff))][0] == 0) && (xleftx[m - cols + 1][0] != 0))
                    xposs[7] = 1;
                else {
                    yposs[7] = 0;
                    xposs[7] = 0;
                }
            }
        }
    }

    public void awesome() {
        int ji = 0, ry = 0;
        for (int i = 0; i < min.dsquares.length; i++) {
            if (min.dsquares[i] != 0) {
                ry += 1;
            }
        }
        squares = new int[ry];
        for (int i = 0; i < min.dsquares.length; i++) {
            if (min.dsquares[i] != 0) {
                squares[ji] = min.dsquares[i];
                ji += 1;
            }
        }
        if (squares.length == 0) dirt = 1;
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
}
