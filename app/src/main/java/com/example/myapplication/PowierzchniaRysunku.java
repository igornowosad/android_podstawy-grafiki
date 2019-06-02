package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class PowierzchniaRysunku extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mPojemnik;

    private Thread mWatekRysujacy;

    private boolean mWatekPracuje = false;

    private Object mBlokada = new Object();

    private Bitmap mBitmapa = null;
    private Canvas mKanwa = null;

    public PowierzchniaRysunku(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPojemnik = getHolder();
        mPojemnik.addCallback(this);
    }

    public void wznowRysowanie() {
        mWatekRysujacy = new Thread(this);
        mWatekPracuje = true;
        mWatekRysujacy.start();
    }

    public void pauzujRysowanie() {
        mWatekPracuje = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        synchronized (mBlokada) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
        }
        return true;
    }

    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mBitmapa = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mKanwa = new Canvas(mBitmapa);
        mKanwa.drawARGB(255, 255, 255, 255);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mWatekPracuje = false;
    }

    @Override
    public void run() {
        while (mWatekPracuje) {
            Canvas kanwa = null;
            try {
                synchronized (mPojemnik) {
                    if (!mPojemnik.getSurface().isValid()) continue;

                    kanwa = mPojemnik.lockCanvas(null);
                    synchronized (mBlokada) {
                        if (mWatekPracuje) {
                            mKanwa.drawBitmap(mBitmapa, 0, 0, null);
                        }
                    }
                }
            } finally {
                if (kanwa != null) {
                    mPojemnik.unlockCanvasAndPost(kanwa);
                }
            }

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {

            }
        }
    }
}
