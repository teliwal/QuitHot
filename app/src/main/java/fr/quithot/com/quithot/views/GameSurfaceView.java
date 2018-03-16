package fr.quithot.com.quithot.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import fr.quithot.com.quithot.domain.Balle;
import fr.quithot.com.quithot.domain.BalleFactory;
import fr.quithot.com.quithot.domain.Personnage;
import fr.quithot.com.quithot.domain.TiltType;
import fr.quithot.com.quithot.sensors.OrientationConsumer;
import fr.quithot.com.quithot.sensors.OrientationListener;
import fr.quithot.com.quithot.sensors.ScreenConsumer;
import fr.quithot.com.quithot.sensors.ScreenListener;


/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, OrientationConsumer {

    private GameThread thread;
    private Personnage perso;
    private BalleFactory balleFactory;
    private boolean screenSet = false;
    private OrientationListener orientationListener = new OrientationListener(this);
    private ScreenListener screenListener;
    private SensorManager manager;

    public GameSurfaceView(Context context) {
        super(context);

        getHolder().addCallback(this);
        thread = new GameThread(this);

        perso = new Personnage(this.getContext(), 600.0f, 500.0f);
        balleFactory = new BalleFactory(this.getContext());

        System.err.println("Création instance surface");
    }


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        thread = new GameThread(this);
        this.screenListener = new ScreenListener(this);
        System.err.println("Création instance surface");
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        manager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(orientationListener,
                manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);

        thread=new GameThread(this);

        thread.setRunning(true);
        thread.start();

        System.err.println("Lancement Thread dessin");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // on efface l'écran, en blanc
        canvas.drawColor(Color.WHITE);

        // on dessine la balle
        balleFactory.drawAll(canvas);
        perso.seDeplacer(canvas.getHeight(), getWidth());
        perso.dessiner(canvas);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update() {

        balleFactory.addBalle();

        if (!screenSet) {
            screenSet = true;
            balleFactory.setHeight(this.getHeight());
            balleFactory.setWidth(this.getWidth());
        }

        balleFactory.moveAll();
    }


    @Override
    public void notifierShake() {

    }

    @Override
    public void notifierTilt(TiltType type) {
        switch (type) {
            case BAS:
                perso.setDirX(0.0f);
                perso.setDirY(25.0f);
                break;
            case HAUT:
                perso.setDirX(0.0f);
                perso.setDirY(-25.0f);
                break;
            case DROITE:
                perso.setDirX(25.0f);
                perso.setDirY(.0f);
                break;
            case GAUCHE:
                perso.setDirX(-25.0f);
                perso.setDirY(0.0f);
                break;
        }
    }

    public BalleFactory getBalleFactory() {
        return balleFactory;
    }

    public void setBalleFactory(BalleFactory balleFactory) {
        this.balleFactory = balleFactory;
    }
}
