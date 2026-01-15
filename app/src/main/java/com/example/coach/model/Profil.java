package com.example.coach.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe métier concernant le profil
 */
public class Profil implements Serializable {
    private static final int MIN_FEMME = 25;
    private static final int MAX_FEMME = 30;
    private static final int MIN_HOMME = 15;
    private static final int MAX_HOMME = 20;
    /**
     * Message qui doit être affiché en fonction de l'img
     */
    private static final String[] MESSAGE = {"trop faible", "normal", "trop élevé"};
    /**
     * Image qui doit être affiché en fonction de l'img
     */
    private static final String[] IMAGE = {"maigre", "normal", "graisse"};
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private transient double img;
    private transient int indice;
    Date dateMesure;

    /**
     * Retourne la date de mesure
     *
     * @return
     */
    public Date getDateMesure() {
        return dateMesure;
    }

    /**
     * Retourne le poids
     *
     * @return poids
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * Retourne la taille
     *
     * @return taille
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * Retourne l'âge
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Retourne le sexe
     *
     * @return sexe
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * Constructeur
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calculIndice();
        this.dateMesure = dateMesure;
    }

    /**
     * Retourne l'img
     *
     * @return img
     */
    public double getImg() {
        return calculImg();
    }

    /**
     * Retourne le message
     *
     * @return MESSAGE
     */
    public String getMessage() {
        return MESSAGE[indice];
    }

    /**
     * Retourne l'image
     *
     * @return IMAGE
     */
    public String getImage() {
        return IMAGE[indice];
    }

    /**
     * Retourne vrai si l'indice correspond à "normal" et faux dans le cas contraire
     *
     * @return true or false
     */
    public boolean normal() {
        return indice==1;
    }

    /**
     * Méthode permettant de calculer l'img
     *
     * @return calcul result
     */
    private double calculImg() {
        double tailleInMeter = taille/100.0;
        return (1.2 * poids/(tailleInMeter * tailleInMeter)) + (0.23 * age) - (10.83 * sexe) - 5.4;
    }

    /**
     * Méthode permettant de déterminer la valeur de l'indice
     *
     * @return 0, 1 or 2
     */
    private int calculIndice() {
        int min = (sexe == 0) ? MIN_FEMME : MIN_HOMME;
        int max = (sexe == 0) ? MAX_FEMME : MAX_HOMME;
        if (img > max) {
            return 2; // au-dessus de la norme
        }
        if (img >= min) {
            return 1; // dans la norme
        }
        return 0; // en dessous de la norme
    }
}
