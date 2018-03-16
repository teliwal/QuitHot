package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by telly on 16/03/18.
 */

public class BalleBonus extends Balle {

    BonusType bonusType;

    BalleBonus(float x, float y, int radius, double vitesseX, double vitesseY, boolean pause, Context context,Personnage personnage,BonusType type){
        super(x,y,radius,vitesseX,vitesseY,pause,context,personnage);
        this.bonusType = type;
        if(type==BonusType.PAUSE){
            setColor(Color.BLUE);
        } else if(type == BonusType.ARMURE){
            setColor(Color.YELLOW);
        } else setColor(Color.RED);
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

}
