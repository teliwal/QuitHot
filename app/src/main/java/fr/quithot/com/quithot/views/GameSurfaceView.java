package fr.quithot.com.quithot.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import fr.quithot.com.quithot.domain.Balle;


/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private Balle balle;


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        thread = new GameThread(this);

        balle = new Balle(0.0f, 0.0f, 50, 1, 1, false, this.getContext());

        System.err.println("Création instance surface");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {


        thread=new GameThread(this);

        thread.setRunning(true);
        thread.start();

        System.err.println("Lancement Thread dessin");
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (canvas == null)
            return;

        // on efface l'écran, en blanc
        canvas.drawColor(Color.WHITE);

        // on dessine la balle
        balle.dessiner(canvas);
        System.err.println("Draw");
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    // Fonction obligatoire de l'objet SurfaceView
    // Fonction appelée juste avant que l'objet ne soit détruit.
    // on tente ici de stopper le processus de gameLoopThread
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e) {}
        }
    }

    public void update() {
        balle.mouvementBalle(5.0, 5.0);
    }


}
