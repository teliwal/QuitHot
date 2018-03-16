package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by telly on 16/03/18.
 */

public class Balle {
    private float x;
    private float y;
    private int radius;
    private double vitesseX;
    private double vitesseY;
    private boolean pause;
    private Context context;
    private Paint paint;

    private Personnage personnage;


    public Balle() {
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setColor(Color.BLACK);
    }

    public void collision(){
        if(!personnage.isArmure()){//personnage n'a d'armure
            if(touchePersonnage()){
                System.out.println("collision");
                personnage.decrementerVie();
                disparaitre();
            }
        } else{
            if(toucherArmure()){
                rebondir();
            }
        }
    }

    private void rebondir(){
        vitesseX = - vitesseX;
        vitesseY = - vitesseY;
    }

    private void disparaitre(){
        x = -1.2f;
    }

    private boolean touchePersonnage(){
        float posX = personnage.getX();
        float posY = personnage.getY();
        float distance = (float) Math.sqrt((x-posX) * ((x-posX)) + ((y-posY) * (y-posY)));
        if(distance < radius+48.f){
            return true;
        }
        return  false;
    }

    private boolean toucherArmure(){
        float posX = personnage.getX();
        float posY = personnage.getY();
        float distance = (float) Math.sqrt((x-posX) * ((x-posX)) + ((y-posY) * (y-posY)));
        if(distance < radius+58.f){
            return true;
        }
        return  false;
    }
    public Balle(float x, float y, int radius, double vitesseX, double vitesseY, boolean pause, Context context,Personnage personnage) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;
        this.pause = pause;
        this.context = context;
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setColor(Color.BLACK);
        this.personnage = personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public double getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void mouvementBalle(){
        System.err.println(x + " " + y + " " + vitesseX + " " + vitesseY);
        this.x += vitesseX;
        this.y += vitesseY;
        collision();
        System.err.println(x + " " + y + " " + vitesseX + " " + vitesseY);

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

    public void dessiner(Canvas canvas){
        canvas.drawCircle(x,y,this.radius,this.paint);
    }

    public boolean isOutOfRange(float screenHeight, float screenWidth) {
        if (x < 0.0f)
            return true;
        if (y < 0.0f)
            return true;
        if (x > screenWidth)
            return true;
        if (y > screenHeight)
            return true;

        return false;
    }
}

