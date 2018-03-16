package fr.quithot.com.quithot.sensors;

import android.view.MotionEvent;
import android.view.View;

import fr.quithot.com.quithot.domain.Balle;
import fr.quithot.com.quithot.domain.BalleBonus;
import fr.quithot.com.quithot.domain.BalleFactory;
import fr.quithot.com.quithot.views.GameSurfaceView;

/**
 * Created by bahpetit on 16/03/2018.
 */
public class ScreenListener implements View.OnTouchListener{

    BalleFactory balleFactory;

    public ScreenListener() {
    }

    public ScreenListener(BalleFactory balleFactory) {
        this.balleFactory = balleFactory;
    }
    public BalleFactory getBalleFactory() {
        return balleFactory;
    }

    public void setBalleFactory(BalleFactory balleFactory) {
        this.balleFactory = balleFactory;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();
        for (Balle balle: this.balleFactory.getListeBalle()) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if((balle instanceof BalleBonus)){
                    return (verrifierTouch(touchX, touchY, balle));
                }
            }
        }
        return false;
    }

    private boolean verrifierTouch(int touchX, int touchY, Balle balle) {
        boolean axeX = (touchX <= (balle.getX() + balle.getRadius())) && (touchX >= (balle.getX() + balle.getRadius()));
        boolean axeY = (touchY <= (balle.getY() + balle.getRadius())) && (touchY >= (balle.getY() + balle.getRadius()));
        return (axeX && axeY);
    }
}
