package fr.quithot.com.quithot.domain;

import android.content.Context;

/**
 * Created by telly on 16/03/18.
 */

public class BalleBonus extends Balle {

    BonusType bonusType;

    BalleBonus(float x, float y, int radius, double vitesseX, double vitesseY, boolean pause, Context context,Personnage personnage,BonusType type){
        super(x,y,radius,vitesseX,vitesseY,pause,context,personnage);
        this.bonusType = type;
    }

    public BonusType getBonusType() {
        return bonusType;
    }

    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

}
