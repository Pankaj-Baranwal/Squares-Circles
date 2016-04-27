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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.Random;

/**
 * Created by Pankaj on 15-12-2014.
 */
public class Drawline2player extends View {
    private Bitmap canvasBitmap;
    private Canvas drawCanvas;
    private Paint drawPaint, canvasPaint, spaint, paints;
    private Path drawPath, drawPath1;
    private int paintColor = 0xFF9400D3;
    private int[] ccolor, color;
    int color1=Color.rgb(250, 60, 5);
    int color2=Color.rgb(10, 250, 250);
    int color3=0xFFFFFF00;
    int color4=0xFF009900;
    Random rand;
    float ax, bx, ex, ay, by, ey;
    int rows, cols;
    String uscore1="User1", uscore2="User2", uscore3="User3", uscore4="User4";
    int userscore1=0, userscore2=0, userscore3=0, userscore4=0;
    int[] xposs, yposs;
    float xdiff = 0;
    float ydiff = 0;
    float yused[][];
    float xused[][];
    float ylefty[][], yleftx[][];
    float xleftx[][], xlefty[][];
    int x, y, radius;
    int larst;
    int totxm = 0;
    int totym = 0;
    int cunter = 0;
    float xcoords[];
    float ycoords[];
    int canter = 0;
    int players=2;
    int pts=20;
    int lpts=5;
    Toast toast;
    Context contexts;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Event eve;
    int shut=1;


    public Drawline2player(Context context, AttributeSet attrs) {
        super(context, attrs);
        sp=context.getSharedPreferences("Myprefs", Context.MODE_PRIVATE);
        editor=sp.edit();
        contexts=context;
        setupDrawing();
    }

    private void setupDrawing() {
        rowcolumn();
        canter = 0;
        ccolor = new int[]{0xff224040, 0xff847034, 0xff5ab78d, 0xff886bb8, 0xff7f2fd4, 0xff606f7f, 0xffb64e80, 0xff64394d, 0xff1f8df0, 0xff1f40a5, 0xd2a5e6};
        color = new int[]{0xffff4040, 0xff8470ff, 0xffdab7ed, 0xfff86b78, 0xff7fffd4, 0xff00ff7f, 0xff76ee00, 0xff60994d, 0xffff8d00, 0xff4040ff, 0xd0f0e0};
        rowcolumn();
        if (sp.contains("player"))
            players = sp.getInt("player", 2);
        else
            players=2;
        //name();
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
        paints = new Paint();
        drawPath1 = new Path();
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
    }

/*    private void name() {
        if(sp.contains("name1"))
            uscore1=sp.getString("name1", "User1");
        else
        uscore1="User1";
        if(sp.contains("name2"))
            uscore2=sp.getString("name2", "User2");
        else
            uscore2="User2";
        if(sp.contains("name3"))
            uscore3=sp.getString("name3", "User3");
        else
            uscore3="User3";
        if(sp.contains("name4"))
            uscore4=sp.getString("name4", "User4");
        else
            uscore4="User4";
    } */

    private void size() {
        if (isInEditMode()) {
            drawPaint.setStrokeWidth(10);
            paints.setStrokeWidth(7);
        }else {
            if (sp.contains("size")) {
                int size = sp.getInt("size", 10);
                if (size == 5) {
                    drawPaint.setStrokeWidth(5);
                    radius=5;
                    paints.setStrokeWidth(6);
                } else if (size == 10) {
                    drawPaint.setStrokeWidth(10);
                    radius=10;
                    paints.setStrokeWidth(7);
                } else if (size == 15) {
                    drawPaint.setStrokeWidth(13);
                    radius=13;
                    paints.setStrokeWidth(10);
                }
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
                drawPaint.setColor(ccolor[4]);
            if (sp.contains("u1"))
                color1 = (color[sp.getInt("u1", color4)]);
            else
                color1= color[0];
            if (sp.contains("u2"))
                color2 = (color[sp.getInt("u2", color3)]);
            else
                color2= color[2];
            if (sp.contains("u3"))
                color3 = (color[sp.getInt("u3", color4)]);
            else if(color[5]!=color1 && color[5]!=color2)
                color3= color[5];
            else
            color3=color[7];
            if (sp.contains("u4"))
                color4 = (color[sp.getInt("u4", color3)]);
            else if(color[4]!=color1 && color[4]!=color2)
                color4= color[4];
            else
                color4=color[8];
        }
    }


    private void rowcolumn() {
        if(sp.contains("sublevel")){
            if(sp.getInt("sublevel", 1)==1){
                rows=3; cols=3;
            }
            else if(sp.getInt("sublevel", 1)==10){
                rows=8; cols=8;
            }
            for(int ki=2; ki<=9; ki++){
                if(sp.getInt("sublevel", 1)==ki){
                    if((ki%2)!=0){
                        rows=(ki/2)+3; cols=(ki/2)+3;
                    }
                    else{
                        rows=(ki/2)+2;
                        cols=(ki/2)+3;
                    }
                }
            }
        }
        else{
            rows=6;
            cols=5;
        }
    }


    @Override
        protected void onSizeChanged ( int w, int h, int oldw, int oldh){
            super.onSizeChanged(w, h, oldw, oldh);
            canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            drawCanvas = new Canvas(canvasBitmap);
        }

    @Override
        protected void onDraw (Canvas canvas) {
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
        public boolean onTouchEvent (@NonNull MotionEvent event){
        	if(shut==0){
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
                    if ((gx != xused[xloc(gx, gy)][0])) {
                        xsed(gx, hx, gy);
                        int square = isSquare(gx, hx, gy, hy);
                        if (square != 0) {
                            if (square!=5 && square!=6) {
                                if (canter == 0)
                                    userscore1 += pts;
                                else if (canter == 1)
                                    userscore2 += pts;
                                else if (canter == 2)
                                    userscore3 += pts;
                                else if (canter == 3)
                                    userscore4 += pts;
                            }else{
                                if (canter == 0)
                                    userscore1 += 2*pts;
                                else if (canter == 1)
                                    userscore2 += 2*pts;
                                else if (canter == 2)
                                    userscore3 += 2*pts;
                                else if (canter == 3)
                                    userscore4 += 2*pts;
                            }
                            fill(gx, gy, hx, hy);
                        } else {
                            if(canter==0)
                                userscore1 += lpts;
                            else if(canter==1)
                                userscore2 += lpts;
                            else if(canter==2)
                                userscore3 += lpts;
                            else if(canter==3)
                                userscore4 += lpts;
                            larst = 0;
                            if(canter!=players-1)
                            canter+= 1;
                            else canter=0;
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
                            if (square!=5 && square!=6) {
                                if (canter == 0)
                                    userscore1 += pts;
                                else if (canter == 1)
                                    userscore2 += pts;
                                else if (canter == 2)
                                    userscore3 += pts;
                                else if (canter == 3)
                                    userscore4 += pts;
                            }else{
                                if (canter == 0)
                                    userscore1 += 2*pts;
                                else if (canter == 1)
                                    userscore2 += 2*pts;
                                else if (canter == 2)
                                    userscore3 += 2*pts;
                                else if (canter == 3)
                                    userscore4 += 2*pts;
                            }
                            fill(gx, gy, hx, hy);
                        } else {
                            if(canter==0)
                                userscore1 += lpts;
                            else if(canter==1)
                                userscore2 += lpts;
                            else if(canter==2)
                                userscore3 += lpts;
                            else if(canter==3)
                                userscore4 += lpts;
                            larst = 0;
                            if(canter!=players-1)
                            canter+= 1;
                            else canter=0;
                            larst = 0;
                        }
                        drawPath.moveTo(gx, gy);
                        drawPath.lineTo(hx, hy);
                        drawCanvas.drawPath(drawPath, drawPaint);
                        drawPath.reset();
                        invalidate(); // causes onDraw to execute
                    }
                }
            }
            else {
                CharSequence text = "Make lines between the nearest two points only!";
                toast = Toast.makeText(contexts, text, Toast.LENGTH_SHORT);
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
    public void fill(float hx, float hy, float gx, float gy ){
        int square=isSquare(hx, gx, hy, gy);
        if (canter==1)
            spaint.setColor(color2);
        else if(canter==0)
            spaint.setColor(color1);
        else if(canter==2)
            spaint.setColor(color3);
        else if(canter==3)
            spaint.setColor(color4);
        if (square!=0) {
            if (square == 1) {
                drawpath(hx - (2 * xdiff) + 5, hy + 5, hx - 5, hy + 5, gx - 5, gy - 5, gx - (2 * xdiff) + 5, gy - 5);
            } else if (square == 2) {
                drawpath(hx + 5, hy + 5, hx - 5 + (2 * xdiff), hy + 5, gx - 5 + (2 * xdiff), gy - 5, gx + 5, gy - 5);
            } else if (square == 3) {
                drawpath(hx + 5, hy - 5, hx + 5, hy + 5 - (2 * ydiff), gx - 5, gy + 5 - (2 * ydiff), gx - 5, gy - 5);
            } else if (square == 4) {
                drawpath(hx + 5, hy + 5, hx + 5, hy - 5 + (2 * ydiff), gx - 5, gy - 5 + (2 * ydiff), gx - 5, gy + 5);
            } else if(square ==5) {
                drawpath(hx - (2 * xdiff) + 5, hy + 5, hx - 5, hy + 5, gx - 5, gy - 5, gx - (2 * xdiff) + 5, gy - 5);
                drawpath(hx + 5, hy + 5, hx - 5 + (2 * xdiff), hy + 5, gx - 5 + (2 * xdiff), gy - 5, gx + 5, gy - 5);
            } else if(square==6) {
                drawpath(hx + 5, hy - 5, hx + 5, hy + 5 - (2 * ydiff), gx - 5, gy + 5 - (2 * ydiff), gx - 5, gy - 5);
                drawpath(hx + 5, hy + 5, hx + 5, hy - 5 + (2 * ydiff), gx - 5, gy - 5 + (2 * ydiff), gx - 5, gy + 5);
            }
            drawCanvas.drawPath(drawPath1, spaint);
            drawPath1.reset();
        }
    }

    private void drawpath(float as, float sd, float df, float fg, float gh, float hj, float jk, float kl){
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
            if (left == 0 && right == 0){
                if (((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] == 0)) && ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0))) return 5;
                else if (((xleftx[l - 1][0] == 0) && (xleftx[l + cols - 2][0] == 0) && (yleftx[m - rows + 1][0] == 0))) return 1; //left=1
                else if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0)) return 2; //right=2
                else return 0;
            }
            else if (left == 1){
                if ((xleftx[l][0] == 0) && (xleftx[l + cols - 1][0] == 0) && (yleftx[m + rows - 1][0] == 0)) return 2;
                else return 0;
            }
            else {
                if ((xleftx[xloc((xa - (2 * xdiff)), ya)][0] == 0) && (xleftx[xloc((xa - (2 * xdiff)), (ya + (2 * ydiff)))][0] == 0) && (yleftx[m - rows+1][0] == 0)) return 1;
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
            if (top == 0 && bottom == 0){
                if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] == 0)) && ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0))) return 6;
                else if (((yleftx[l - 1][0] == 0) && (yleftx[l + rows - 2][0] == 0) && (xleftx[m - cols + 1][0] == 0))) return 3; // top=3
                else if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0)) return 4; //bottom=4
                else return 0;
            }

            else if (top == 1){
                if ((yleftx[l][0] == 0) && (yleftx[l + rows - 1][0] == 0) && (xleftx[m + cols - 1][0] == 0)) return 4;
                else return 0;
            }
            else{
                if ((yleftx[yloc(xa, (ya - (2 * ydiff)))][0] == 0) && (yleftx[yloc((xa + (2 * xdiff)), (ya - 2 * ydiff))][0] == 0) && (xleftx[m -cols + 1][0] == 0)) return 3;
                else return 0;
            }
        } else return 0;
    }

}
