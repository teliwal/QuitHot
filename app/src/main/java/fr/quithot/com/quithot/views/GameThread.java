package fr.quithot.com.quithot.views;

import android.content.Context;
import android.graphics.Canvas;

import fr.quithot.com.quithot.domain.BalleFactory;

/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class GameThread extends Thread {

    private final static int FRAMES = 30;
    private final static int SKIP_TICKS = 1000 / FRAMES;
    private final GameSurfaceView view;
    private boolean running = false;

    private boolean screenSet = false;

    public GameThread(GameSurfaceView v) {
        this.view = v;


    }

    public void setRunning(boolean r) {
        running = r;
    }

    @Override
    public void run() {

        long startTime;
        long sleepTime;

        while (running) {

            startTime = System.currentTimeMillis();



            synchronized (view.getHolder()) {view.update();}

            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {view.onDraw(c);}
            }
            finally
            {
                if (c != null) {view.getHolder().unlockCanvasAndPost(c);}
            }

            // Calcul du temps de pause, et pause si nécessaire
            // afin de ne réaliser le travail ci-dessus que X fois par secondes
            sleepTime = SKIP_TICKS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime >= 0) {sleep(sleepTime);}
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void pause() {
        running = false;
    }

}
