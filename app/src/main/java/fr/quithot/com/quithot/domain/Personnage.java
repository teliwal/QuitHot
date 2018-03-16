package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import fr.quithot.com.quithot.R;

/**
 * Created by telly on 16/03/18.
 */

public class Personnage {

    private float x;
    private float y;
    private float dirX, dirY;
    private Bitmap image;
    private int nbVie;
    private boolean armure;


    public Personnage(){}

    public Personnage(Bitmap image,float x,float y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Personnage(Context context, float x, float y){

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        this.image = bm;
        this.x = x;
        this.y = y;
    }

    public void decrementerVie(){
        nbVie--;
    }

    public boolean isArmure() {
        return armure;
    }

    public void setArmure(boolean armure) {
        this.armure = armure;
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

    public void setDirX(float dirX) {
        this.dirX = dirX;
    }

    public void setDirY(float dirY) {
        this.dirY = dirY;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }



    public void seDeplacer(float screenHeight, float screenWidth){
        float tempX, tempY;

        tempX = x + dirX;
        tempY = y + dirY;

        if ( ! (x < 0.0f && y < 0.0f) && !(x > screenWidth && y > screenHeight)) {
            x = tempX;
            y = tempY;
        }

    }

    public void dessiner(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawBitmap(image, x, y, paint);    }
}
