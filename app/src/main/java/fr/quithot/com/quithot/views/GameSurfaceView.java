package fr.quithot.com.quithot.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;

import java.util.TimerTask;

import fr.quithot.com.quithot.activity.MainActivity;
import fr.quithot.com.quithot.domain.Balle;
import fr.quithot.com.quithot.domain.BalleFactory;
import fr.quithot.com.quithot.domain.Difficulte;
import fr.quithot.com.quithot.domain.BonusType;
import fr.quithot.com.quithot.domain.Personnage;
import fr.quithot.com.quithot.domain.TiltType;
import fr.quithot.com.quithot.sensors.LuminosityConsumer;
import fr.quithot.com.quithot.sensors.LuminositySensor;
import fr.quithot.com.quithot.sensors.OrientationConsumer;
import fr.quithot.com.quithot.sensors.OrientationListener;
import fr.quithot.com.quithot.sensors.ScreenConsumer;
import fr.quithot.com.quithot.sensors.ScreenListener;


/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback, OrientationConsumer,ScreenConsumer,LuminosityConsumer {

    private GameThread thread;
    private Personnage perso;
    private BalleFactory balleFactory;
    private boolean screenSet = false;
    private OrientationListener orientationListener = new OrientationListener(this);
    private LuminositySensor luminosityListerner = new LuminositySensor(this);
    private SensorManager managerTilt;
    private SensorManager managerShake;
    private ScreenListener screenListener;
    private SensorManager managerLimunosite;

    private TimerTask armureTimer;

    final Handler handler2 = new Handler();
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            MainActivity.gameOver();
        }
    };

    public GameSurfaceView(Context context, String diff) {
        super(context);

        getHolder().addCallback(this);
        thread = new GameThread(this);

        perso = new Personnage(this.getContext(), 600.0f, 500.0f);
        balleFactory = new BalleFactory(this.getContext(),perso, Difficulte.valueOf(diff));
        screenListener = new ScreenListener(this,this);
        setOnTouchListener(screenListener);
    }


    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
        thread = new GameThread(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        managerTilt = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        managerTilt.registerListener(orientationListener,
                managerTilt.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_NORMAL);

        managerShake = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        managerShake.registerListener(orientationListener,
                managerShake.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        managerLimunosite = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        managerLimunosite.registerListener(luminosityListerner,
                managerLimunosite.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);

        thread=new GameThread(this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (perso.getNbVie() > 3)
            canvas.drawColor(Color.WHITE);
        else if (perso.getNbVie() > 1)
            canvas.drawColor(Color.MAGENTA);
        if (perso.getNbVie() == 1)
            canvas.drawColor(Color.RED);
        if (perso.getNbVie() < 1) {
            gameOver();
        }

        // on dessine la balle
        balleFactory.drawAll(canvas);
        perso.seDeplacer(canvas.getHeight(), getWidth());
        perso.dessiner(canvas);
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
            case BAS_DROIT:
                perso.setDirX(25.0f);
                perso.setDirY(25.0f);
                break;
            case BAS_GAUCHE:
                perso.setDirX(-25.0f);
                perso.setDirY(25.0f);
                break;
            case HAUT_DROITE:
                perso.setDirX(25.0f);
                perso.setDirY(-25.0f);
                break;
            case HAUT_GAUCHE:
                perso.setDirX(-25.0f);
                perso.setDirY(-25.0f);
                break;
            case CENTRE:
                perso.setDirX(0.0f);
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

    public void gameOver() {
        thread.pause();
        handler2.post(runnable2);
    }

    @Override
    public void notifierBonus(Balle balle) {
        //handle bonus
    }

    @Override
    public void notifierLuminosity() {
        System.out.println("NOIR");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                perso.enleverArmure();
            }
        },1000);
        perso.mettreArmure();
    }

    @Override
    public void notifierShake() {
        System.out.println("SHAKE SHAKE");
        balleFactory.pause();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                balleFactory.redemarrer();
            }
        },2000);
    }

    private void lancerVie(){
        balleFactory.addBonus(BonusType.VIE);
    }

    private void lancerArmure(){
        balleFactory.addBonus(BonusType.ARMURE);
    }

    private void lancerArret(){
        balleFactory.addBonus(BonusType.PAUSE);
    }
}
