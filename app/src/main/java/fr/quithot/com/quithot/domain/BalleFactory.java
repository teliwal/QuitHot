package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by mathieukostiuk on 16/03/2018.
 */

public class BalleFactory {

    ArrayList<Balle> listeBalle;
    private float screenHeight, screenWidth;
    Random rand = new Random();
    private Context context;
    private Personnage personnage;
    private int maxVitesse;
    private int nbBalles;

    public BalleFactory(Context c, Personnage personnage, Difficulte diff) {
        listeBalle = new ArrayList<>();
        this.personnage = personnage;
        context = c;

        switch (diff) {
            case FACILE:
                maxVitesse = 25;
                nbBalles = 3;
                break;
            case MOYEN:
                maxVitesse = 50;
                nbBalles = 5;
                break;
            case DIFFICILE:
                maxVitesse = 65;
                nbBalles = 5;
                break;
        }
    }

    public void setHeight(int height) {
        screenHeight = height;
    }

    public void setWidth(int width) {
        screenWidth = width;
    }

    public ArrayList<Balle> getListeBalle() {
        return listeBalle;
    }

    public void addBalle() {

        int cpt = listeBalle.size();

        while (cpt < nbBalles) {


            listeBalle.add(createBalle(false,null));

            cpt++;
        }
    }

    private Balle createBalle(boolean bonus,BonusType type){
        int posInit = rand.nextInt(8);

        float x, y, dirX, dirY;
        boolean negX, negY;
        negX = false;
        negY = false;

        x = 0.0f;
        y = 0.0f;

        switch (posInit) {
            case 0:
                x = 0.0f;
                y = 0.0f;
                break;
            case 1:
                x = 0.0f;
                y = screenHeight / 2;
                break;
            case 2:
                x = 0.0f;
                y = screenHeight;
                break;
            case 3:
                x = screenWidth / 2;
                y = screenHeight;
                break;
            case 4:
                x = screenWidth;
                y = screenHeight;
                break;
            case 5:
                x = screenWidth;
                y = screenHeight / 2;
                break;
            case 6:
                x = screenWidth;
                y = 0.0f;
                break;
            case 7:
                x = screenWidth / 2;
                y = 0.0f;
                break;
        }

        switch (rand.nextInt(4)) {
            case 0:
                negX = false;
                negY = false;
                break;
            case 1:
                negX = true;
                negY = false;
                break;
            case 2:
                negX = false;
                negY = true;
                break;
            case 3:
                negX = true;
                negY = true;
                break;
        }

        dirX = rand.nextInt(maxVitesse);
        dirY = rand.nextInt(maxVitesse);

        if (negX)
            dirX = -dirX;

        if (negY)
            dirY = -dirY;
        if(bonus){
            return new BalleBonus(x, y, 50, dirX, dirY, false, context,personnage,type);
        }
        return new Balle(x, y, 50, dirX, dirY, false, context,personnage);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void moveAll() {

        List<Balle> temp = new ArrayList<>();

        for (Balle b : listeBalle) {
            b.mouvementBalle();
            if (b.isOutOfRange(screenHeight, screenWidth))
                temp.add(b);
        }
        listeBalle.removeAll(temp);
    }

    public void drawAll(Canvas c) {
        for (Balle b : listeBalle) {
            b.dessiner(c);
        }
    }

    public void addBonus(BonusType type){
        listeBalle.add(createBalle(true,type));
    }

    public void  pause(){
        for(Balle b: listeBalle){
            b.setPause(true);
        }
    }

    public void redemarrer(){
        for(Balle b: listeBalle){
            b.setPause(false);
        }
    }

}
