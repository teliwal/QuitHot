package fr.quithot.com.quithot.domain;

import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by telly on 16/03/18.
 */

public class Personnage {

    private float x;
    private float y;
    private BitmapDrawable image;

    public Personnage(){}

    public Personnage(BitmapDrawable image,float x,float y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }


    public BitmapDrawable getImage() {
        return image;
    }

    public void setImage(BitmapDrawable image) {
        this.image = image;
    }

    public void seDeplacer(float vitesseX, float vitesseY){
        this.x = vitesseX;
        this.y = vitesseY;
    }
}
