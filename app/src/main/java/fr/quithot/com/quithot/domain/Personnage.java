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
    private int nbUseArmure;
    private int nbUseStop;
    private boolean armure;


    private Context contex;
    public Personnage(){

    }

    public Personnage(Bitmap image,float x,float y){
        nbVie = 5;
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public Personnage(Context context, float x, float y){
        this.contex = context;
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        nbVie = 5;
        nbUseArmure = 3;
        nbUseStop = 3;
        this.image = bm;
        this.x = x;
        this.y = y;
    }

    public void mettreArmure(){
        if(nbUseArmure > 0) {
            Bitmap bm = BitmapFactory.decodeResource(contex.getResources(), R.drawable.player_armor);
            image = bm;
            armure = true;
            nbUseArmure --;
        }
    }

    public void enleverArmure(){
        Bitmap bm = BitmapFactory.decodeResource(contex.getResources(), R.drawable.player);
        image = bm;
        armure = false;
    }

    public void incrementerVie(){
        nbVie++;
}


    public void incrementerNbArmure(){
        nbUseArmure ++;
    }

    public void incrementerNbArret(){
        nbUseStop ++;
    }

    public void  decrementerArret(){
        nbUseStop--;
    }
    public int getNbUseStop() {
        return nbUseStop;
    }

    public int getNbUseArmure() {
        return nbUseArmure;
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

        if ( ! (tempX <= 0.0f) && !(tempY <= 0.0f) && !(tempX >= screenWidth) && !(tempY >= screenHeight)) {
            x = tempX;
            y = tempY;
        }

    }

    public void dessiner(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawBitmap(image, x, y, paint);
    }

    public int getNbVie() {
        return nbVie;
    }


}
