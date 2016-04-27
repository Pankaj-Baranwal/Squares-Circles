package com.pankaj.chowkhunda;

import java.util.Random;

/**
 * Created by Pankaj on 30-12-2014.
 */
public class Minsquares {
    float dylefty[][], dyleftx[][];
    float dxleftx[][], dxlefty[][];
    int cheepu=0, ddtotxm=0, ddtotym=0;
    float[] dxcoords, dycoords;
    int ir=0;
    float dxdiff, dydiff;
    int monty=0;
    int dcols, drows;
    int dtotxm, dtotym;
    int dru=0;
    float [][] ddx1, ddy1, ddx2, ddy2;
    int xio=0, yio=0;
    int[] dsquares;
    public float[][] dxpt, dypt;
    int swap=0;
    Random rand= new Random();
    boolean finish=false;
    public Minsquares(float[][] xleftx, float[][]xlefty, float[][] yleftx, float[][] ylefty, float[] xcoords, float[]ycoords, int cols, int rows, float xdiff, float ydiff, int totxm, int totym){
        dtotxm=totxm;
        dtotym=totym;
        ddtotxm=dtotxm;
        ddtotym=dtotym;
        dcols=cols;
        drows=rows;
        dxdiff=xdiff;
        dydiff=ydiff;
        dsquares=new int[40];
        dxcoords=new float[xcoords.length];
        for(int juhu=0; juhu<xcoords.length; juhu++){
            dxcoords[juhu]=xcoords[juhu];
        }
        dycoords=new float[ycoords.length];
        for(int juhu=0; juhu<ycoords.length; juhu++){
            dycoords[juhu]=ycoords[juhu];
        }
        dyleftx=new float[yleftx.length][2];
        dylefty=new float[ylefty.length][2];
        for(int juhu=0; juhu<yleftx.length; juhu++){
            dyleftx[juhu][0]=yleftx[juhu][0];
            dyleftx[juhu][1]=yleftx[juhu][1];
            dylefty[juhu][0]=ylefty[juhu][0];
            dylefty[juhu][1]=ylefty[juhu][1];
        }
        dxleftx=new float[xleftx.length][2];
        dxlefty=new float[xlefty.length][2];
        for(int juhu=0; juhu<xleftx.length; juhu++){
            dxleftx[juhu][0]=xleftx[juhu][0];
            dxleftx[juhu][1]=xleftx[juhu][1];
            dxlefty[juhu][0]=xlefty[juhu][0];
            dxlefty[juhu][1]=xlefty[juhu][1];
        }
        dxpt=new float[40][2];
        dypt=new float[40][2];
        ddx1=new float[ddtotxm][2];
        ddx2=new float[ddtotxm][2];
        ddy1=new float[ddtotym][2];
        ddy2=new float[ddtotym][2];

    }

    private void dxsed(float gx, float hx, float gy) { //function to update xused and xleft
        int k = dxloc(gx, gy);
        if(dxleftx[k][0]!=0) {
            ddx1[xio][0] = dxleftx[k][0];
            ddx2[xio][0] = dxleftx[k][1];
            ddx1[xio][1] = dxlefty[k][0];
            ddx2[xio][1] = dxlefty[k][1];
            xio += 1;
            dxleftx[k][0] = 0;
            dxleftx[k][1] = 0;
            dxlefty[k][0] = 0;
            dxlefty[k][1] = 0;
            dtotxm -= 1;
        }
    }

    public void dysed(float gy, float hy, float gx) { // function to update yused and yleft
        int ju = dyloc(gx, gy);
        if(dyleftx[ju][0]!=0) {
            ddy1[yio][0] = dyleftx[ju][0];
            ddy2[yio][0] = dyleftx[ju][1];
            ddy1[yio][1] = dylefty[ju][0];
            ddy2[yio][1] = dylefty[ju][1];
            yio += 1;
            dylefty[ju][1] = 0;
            dylefty[ju][0] = 0;
            dyleftx[ju][0] = 0;
            dyleftx[ju][1] = 0;
            dtotym -= 1;
        }
    }

    public int dyloc(float gx, float gy) {   // only for one less row in y
        int o = 0;
        for (int i = (int) gx; i != dxcoords[0]; ) {
            o += 1;
            i -= 2 * dxdiff;
        }
        int l = 0;
        for (int i = (int) gy; i != dycoords[0]; ) {
            l += 1;
            i -= 2 * dydiff;
        }
        return l + (o * (drows - 1));
    }

    public int dxloc(float gx, float gy) {   // only for one less column in x
        int j = 0;
        for (int i = (int) gx; i != dxcoords[0]; ) {
            j += 1;
            i -= 2 * dxdiff;
        }
        int l = 0;
        for (int i = (int) gy; i != dycoords[0]; ) {
            l += 1;
            i -= 2 * dydiff;
        }
        return j + (l * (dcols - 1));
    }


    public void amove() {
        float[][] dlistx1 = new float[dtotxm][2];
        float[][] dlistx2 = new float[dtotxm][2];
        int j = 0, dchuy=0;
        for (int i = 0; i < dxleftx.length; i++) {
            if (dxleftx[i][0] != 0 && j<dtotxm) {
                dchuy = 0;
                for (int zuli = 0; zuli < ddtotxm; zuli++) {
                    if (dxleftx[i][0] == ddx1[zuli][0] && dxleftx[i][1] == ddx2[zuli][0] && dxlefty[i][0] == ddx1[zuli][1] && dxlefty[i][1] == ddx2[zuli][1])
                        dchuy += 1;
                }
                if (dchuy == 0) {
                    dlistx1[j][0] = dxleftx[i][0];
                    dlistx1[j][1] = dxleftx[i][1];
                    dlistx2[j][0] = dxlefty[i][0];
                    dlistx2[j][1] = dxlefty[i][1];
                    j += 1;
                }
            }
        }
        float[][] dlisty1 = new float[dtotym][2];
        float[][] dlisty2 = new float[dtotym][2];
        int k = 0;
        for (int i = 0; i < dylefty.length; i++) {
            if (dylefty[i][0] != 0 && k<dtotym) {
                dchuy = 0;
                for (int zuli = 0; zuli < ddtotym; zuli++) {
                    if (dyleftx[i][0] == ddy1[zuli][0] && dyleftx[i][1] == ddy2[zuli][0] && dylefty[i][0] == ddy1[zuli][1] && dylefty[i][1] == ddy2[zuli][1])
                        dchuy += 1;
                }
                if (dchuy == 0) {
                    dlisty1[k][0] = dyleftx[i][0];
                    dlisty1[k][1] = dyleftx[i][1];
                    dlisty2[k][0] = dylefty[i][0];
                    dlisty2[k][1] = dylefty[i][1];
                    k += 1;
                }
            }
        }
        if(cheepu==0)
            swap=dsquares[dru];
        else {
            swap = 0;
            cheepu=0;
        }
        if(dtotxm>0) {
            for (int i = 0; i < dtotxm; i++) {
                if(dlistx1[i][0]!=0) {
                    if (disSquare(dlistx1[i][0], dlistx1[i][1], dlistx2[i][0], dlistx2[i][1]) != 0) {
                        dsquares[dru] += 1;
                        daline(dlistx1[i][0], dlistx2[i][0], dlistx1[i][1], dlistx2[i][1]);
                    }
                }
            }
        }
        if(dsquares[dru]!=swap) amove();
        if(dtotym>0) {
            for (int ku = 0; ku < dtotym; ku++) {
                if(dlisty1[ku][0]!=0) {
                    if (disSquare(dlisty1[ku][0], dlisty1[ku][1], dlisty2[ku][0], dlisty2[ku][1]) != 0) {
                        dsquares[dru] += 1;
                        daline(dlisty1[ku][0], dlisty2[ku][0], dlisty1[ku][1], dlisty2[ku][1]);
                    }
                }
            }
        }
        if(dsquares[dru]!=swap) amove();
        if (dtotxm > 1) {
            cheepu=1;
            dru+=1;
            int chuy=0, drowsy=0;
                for(int zu=0; zu<dtotxm; zu++) {
                    if (dlistx1[zu][0] != 0) {
                        for (int zuli = 0; zuli < ddtotxm; zuli++) {
                            if (dlistx1[zu][0] == ddx1[zuli][0] && dlistx1[zu][1] == ddx2[zuli][0] && dlistx2[zu][0] == ddx1[zuli][1] && dlistx2[zu][1] == ddx2[zuli][1])
                                chuy += 1;
                        }
                    }if(chuy==0){
                        drowsy=zu; break;
                    }
                }
            xfill(); yfill();
            daline(dlistx1[drowsy][0], dlistx2[drowsy][0], dlistx1[drowsy][1], dlistx2[drowsy][1]);
            amove();
        } else if (dtotym > 1) {
            cheepu=1;
            dru+=1;
            int chuy=0, drowsy=0;
            for(int zu=0; zu<dtotym; zu++) {
                if (dlisty1[zu][0] != 0) {
                    for (int zuli = 0; zuli < ddtotym; zuli++) {
                        if (dlisty1[zu][0] == ddy1[zuli][0] && dlisty1[zu][1] == ddy2[zuli][0] && dlisty2[zu][0] == ddy1[zuli][1] && dlisty2[zu][1] == ddy2[zuli][1])
                            chuy += 1;
                    }
                }if(chuy==0){
                    drowsy=zu; break;
                }
            }
            yfill(); xfill();
            daline(dlisty1[drowsy][0], dlisty2[drowsy][0], dlisty1[drowsy][1], dlisty2[drowsy][1]);
            amove();
        } else if(dtotxm==1 && ir!=1) {
            dsquares[dru + 1] = 1;
            ir=1;
            int drowsy=0;
            for (int di=0; di<dlistx1.length; di++){
                int chowpsy=0;
                for(int ji=0; ji<ddx1.length; ji++) {
                    if (dlistx1[di][0]==ddx1[ji][0] && dlistx2[di][0]==ddx1[ji][1] && dlistx1[di][1]== ddx2[ji][0] && dlistx2[di][1]==ddx2[ji][1]){
                        chowpsy+=1;
                    }
                } if(chowpsy==0) {
                    drowsy=di;
                    break;
                }
            }
            dxpt[dru + 1][0] = dlistx1[drowsy][0];
            dxpt[dru + 1][1] = dlistx1[drowsy][1];
            dypt[dru + 1][0] = dlistx2[drowsy][0];
            dypt[dru + 1][1] = dlistx2[drowsy][1];
            dtotxm-=1;
        } else if(dtotym==1 && ir!=1){
            dsquares[dru + 1] = 1;
            ir=1;
            int drowsy=0;
            for (int di=0; di<dlisty1.length; di++){
                int chowpsy=0;
                for(int ji=0; ji<ddy1.length; ji++) {
                    if (dlisty1[di][0]==ddy1[ji][0] && dlisty2[di][0]==ddy1[ji][1] && dlisty1[di][1]== ddy2[ji][0] && dlisty2[di][1]==ddy2[ji][1]){
                        chowpsy+=1;
                    }
                } if(chowpsy==0) {
                    drowsy=di;
                    break;
                }
            }
            dxpt[dru + 1][0] = dlisty1[drowsy][0];
            dxpt[dru + 1][1] = dlisty1[drowsy][1];
            dypt[dru + 1][0] = dlisty2[drowsy][0];
            dypt[dru + 1][1] = dlisty2[drowsy][1];
            dtotym-=1;
        }
    }

    private void xfill() {
        int chow=0;
        for (int iu=0; iu<ddx1.length; iu++){
            if(ddx1[iu][0]!=0) {
                chow = dxloc(ddx1[iu][0], ddx1[iu][1]);
                if (dxleftx[chow][0] == 0) {
                    dxleftx[chow][0] = ddx1[iu][0];
                    dxleftx[chow][1] = ddx2[iu][0];
                    dxlefty[chow][0] = ddx1[iu][1];
                    dxlefty[chow][1] = ddx2[iu][1];
                }
            }
        }
    }

    private void yfill() {
        int chow=0;
        for (int iu=0; iu<ddy1.length; iu++){
            if(ddy1[iu][0]!=0) {
                chow = dyloc(ddy1[iu][0], ddy1[iu][1]);
                if (dyleftx[chow][0] == 0) {
                    dyleftx[chow][0] = ddy1[iu][0];
                    dyleftx[chow][1] = ddy2[iu][0];
                    dylefty[chow][0] = ddy1[iu][1];
                    dylefty[chow][1] = ddy2[iu][1];
                }
            }
        }
    }

    public void checkmin() {
        xio=0;yio=0;
        if(dtotxm>1 && dtotym>1){
            int dr1=rand.nextInt(2);
            if(dr1==0) {
                float[][] dlistx1 = new float[dtotxm][2];
                float[][] dlistx2 = new float[dtotxm][2];
                int j = 0;
                for (int i = 0; i < dxleftx.length; i++) {
                    if (dxleftx[i][0] != 0 && j<dtotxm) {
                        dlistx1[j][0] = dxleftx[i][0];
                        dlistx1[j][1] = dxleftx[i][1];
                        dlistx2[j][0] = dxlefty[i][0];
                        dlistx2[j][1] = dxlefty[i][1];
                        j += 1;
                    }
                }
                daline(dlistx1[dtotxm - 1][0], dlistx2[dtotxm - 1][0], dlistx1[dtotxm - 1][1], dlistx2[dtotxm - 1][1]);
                amove();
            }
            else {
                float[][] dlisty1 = new float[dtotym][2];
                float[][] dlisty2 = new float[dtotym][2];
                int k = 0;
                for (int i = 0; i < dylefty.length; i++) {
                    if (dylefty[i][0] != 0 && k<dtotym) {
                        dlisty1[k][0] = dyleftx[i][0];
                        dlisty1[k][1] = dyleftx[i][1];
                        dlisty2[k][0] = dylefty[i][0];
                        dlisty2[k][1] = dylefty[i][1];
                        k += 1;
                    }
                }
                daline(dlisty1[dtotym - 1][0], dlisty2[dtotym - 1][0], dlisty1[dtotym - 1][1], dlisty2[dtotym - 1][1]);
                amove();
            }
        }
    }

    public void daline(float gx, float gy, float hx, float hy) {
        if (gx == hx || gy == hy) {
            if (gy < hy) dysed(gy, hy, gx);
            else if (gy > hy) dysed(hy, gy, hx);
            else if (gx < hx) dxsed(gx, hx, gy);
            else if (gx > hx) dxsed(hx, gx, hy);
            if(dxpt[dru][0]==0){
                dxpt[dru][0]=gx;
                dxpt[dru][1]=hx;
                dypt[dru][0]=gy;
                dypt[dru][1]=hy;
            }
        }
    }

    public int disSquare(float xa, float xb, float ya, float yb) {
        if (xa == xb) {
            int left, right;
            left = 0;
            right = 0;
            if (xa == dxcoords[0]) {
                left = 1;
            }
            if (xb == dxcoords[dcols - 1]) {
                right = 1;
            }
            int l = dxloc(xa, ya);
            int m = dyloc(xa, ya);
            if (left == 0 && right == 0){
                if (((dxleftx[l - 1][0] == 0) && (dxleftx[l + dcols - 2][0] == 0) && (dyleftx[m - drows + 1][0] == 0)) && ((dxleftx[l][0] == 0) && (dxleftx[l + dcols - 1][0] == 0) && (dyleftx[m + drows - 1][0] == 0))) return 5;
                else if (((dxleftx[l - 1][0] == 0) && (dxleftx[l + dcols - 2][0] == 0) && (dyleftx[m - drows + 1][0] == 0))) return 1; //left=1
                else if ((dxleftx[l][0] == 0) && (dxleftx[l + dcols - 1][0] == 0) && (dyleftx[m + drows - 1][0] == 0)) return 2; //right=2
                else return 0;
            }
            else if (left == 1){
                if ((dxleftx[l][0] == 0) && (dxleftx[l + dcols - 1][0] == 0) && (dyleftx[m + drows - 1][0] == 0)) return 2;
                else return 0;
            }
            else {
                if ((dxleftx[dxloc((xa - (2 * dxdiff)), ya)][0] == 0) && (dxleftx[dxloc((xa - (2 * dxdiff)), (ya + (2 * dydiff)))][0] == 0) && (dyleftx[m - drows+1][0] == 0)) return 1;
                else return 0;
            }
        } else if (ya == yb) {
            int top, bottom;
            top = 0;
            bottom = 0;
            if (ya == dycoords[0]) top = 1;
            if (yb == dycoords[drows - 1]) bottom = 1;
            int l = dyloc(xa, ya);
            int m = dxloc(xa, ya);
            if (top == 0 && bottom == 0){
                if (((dyleftx[l - 1][0] == 0) && (dyleftx[l + drows - 2][0] == 0) && (dxleftx[m - dcols + 1][0] == 0)) && ((dyleftx[l][0] == 0) && (dyleftx[l + drows - 1][0] == 0) && (dxleftx[m + dcols - 1][0] == 0))) return 6;
                else if (((dyleftx[l - 1][0] == 0) && (dyleftx[l + drows - 2][0] == 0) && (dxleftx[m - dcols + 1][0] == 0))) return 3; // top=3
                else if ((dyleftx[l][0] == 0) && (dyleftx[l + drows - 1][0] == 0) && (dxleftx[m + dcols - 1][0] == 0)) return 4; //bottom=4
                else return 0;
            }

            else if (top == 1){
                if ((dyleftx[l][0] == 0) && (dyleftx[l + drows - 1][0] == 0) && (dxleftx[m + dcols - 1][0] == 0)) return 4;
                else return 0;
            }
            else{
                if ((dyleftx[dyloc(xa, (ya - (2 * dydiff)))][0] == 0) && (dyleftx[dyloc((xa + (2 * dxdiff)), (ya - 2 * dydiff))][0] == 0) && (dxleftx[m -dcols + 1][0] == 0)) return 3;
                else return 0;
            }
        } else return 0;
    }
}
