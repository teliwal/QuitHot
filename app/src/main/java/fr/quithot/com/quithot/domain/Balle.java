package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Point;

/**
 * Created by telly on 16/03/18.
 */

public class Balle {
    private double x;
    private double y;
    private int radius;
    private double vitesseX;
    private double vitesseY;
    private boolean pause;
    private Context context;


    public Balle() {
    }

    public Balle(double x, double y, int radius, double vitesseX, double vitesseY, boolean pause, Context context) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.pause = pause;
        this.context = context;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void mouvementBalle(double pvitesseX, double pvitesseY){
        this.x += pvitesseX;
        this.y += pvitesseY;
    }

    public double getVitesseX() {
        return vitesseX;
    }

    public void setVitesseX(double vitesseX) {
        this.vitesseX = vitesseX;
    }

    public double getVitesseY() {
        return vitesseY;
    }

    public void setVitesseY(double vitesseY) {
        this.vitesseY = vitesseY;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

