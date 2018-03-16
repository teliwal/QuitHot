package fr.quithot.com.quithot.domain;

/**
 * Created by telly on 16/03/18.
 */

public class Score {

    private String joueur;
    private  int temps;

    public Score(String nom,int t){
        joueur = nom;
        temps = t;
    }

    public int getTemps() {
        return temps;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }
}
