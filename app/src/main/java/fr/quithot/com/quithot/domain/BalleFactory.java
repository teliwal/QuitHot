package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Canvas;

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

    public BalleFactory(Context c) {
        listeBalle = new ArrayList<>();

        context = c;
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

        while (cpt < 50) {

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

            dirX = rand.nextInt(100);
            dirY = rand.nextInt(100);

            if (negX)
                dirX = -dirX;

            if (negY)
                dirY = -dirY;

            listeBalle.add(new Balle(x, y, 50, dirX, dirY, false, context));

            cpt++;
        }
    }

    public void moveAll() {

        for (Balle b : listeBalle) {
            b.mouvementBalle();
        }
        System.err.println(listeBalle.size());

        List<Balle> temp = listeBalle.stream().filter(b -> b.isOutOfRange(screenHeight, screenWidth)).collect(Collectors.<Balle>toList());
        listeBalle.removeAll(temp);
    }

    public void drawAll(Canvas c) {
        for (Balle b : listeBalle) {
            b.dessiner(c);
        }
    }
}
