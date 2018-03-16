package fr.quithot.com.quithot.views;

import android.graphics.Canvas;

/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class GameThread extends Thread {

    private final static int FRAMES = 30;
    private final static int SKIP_TICKS = 1000 / FRAMES;
    private final GameSurfaceView view;
    private boolean running = false;

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

            System.err.println("Tour");
            startTime = System.currentTimeMillis();

            synchronized (view.getHolder()) {view.update();}

            Canvas c = null;
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {view.draw(c);}
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

}
