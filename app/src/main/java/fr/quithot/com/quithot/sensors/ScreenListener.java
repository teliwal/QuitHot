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

    GameSurfaceView gameSurfaceView;
    ScreenConsumer consumer;

    public ScreenListener() {
    }

    public ScreenListener(GameSurfaceView gameSurfaceView,ScreenConsumer consumer) {
        this.gameSurfaceView = gameSurfaceView;
        this.consumer = consumer;
    }

    public GameSurfaceView getGameSurfaceView() {
        return gameSurfaceView;
    }

    public void setGameSurfaceView(GameSurfaceView gameSurfaceView) {
        this.gameSurfaceView = gameSurfaceView;
    }

    public ScreenConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(ScreenConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();
        for (Balle balle: this.gameSurfaceView.getBalleFactory().getListeBalle()) {
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if((balle instanceof BalleBonus)){
                    if(verrifierTouch(touchX, touchY, balle)){
                        consumer.notifierBonus(balle);
                        return  true;
                    }
                }
            }
        }
        return false;
    }

    private boolean verrifierTouch(int touchX, int touchY, Balle balle) {
        float distance = (float) (Math.sqrt((touchX-balle.getX()) * (touchX-balle.getX())) + ((touchY-balle.getY() * (touchY-balle.getY()))));
        return (distance >= balle.getRadius());
    }
}
