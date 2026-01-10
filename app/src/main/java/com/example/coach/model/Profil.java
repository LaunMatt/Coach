package com.example.coach.model;

/**
 * Classe métier concernant le profil
 */
public class Profil {
    private static final int MIN_FEMME = 25;
    private static final int MAX_FEMME = 30;
    private static final int MIN_HOMME = 15;
    private static final int MAX_HOMME = 20;
    private static final String[] MESSAGE = {"trop faible", "normal", "trop élevé"};
    private static final String[] IMAGE = {"maigre", "normal", "graisse"};
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private double img;
    private int indice;

    /**
     * Constructeur
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.img = calculImg();
        this.indice = calculIndice();
    }

    /**
     * Retourne l'img
     *
     * @return
     */
    public double getImg() {
        return img;
    }

    /**
     * Retourne le message
     *
     * @return
     */
    public String getMessage() {
        return MESSAGE[indice];
    }

    /**
     * Retourne l'image
     *
     * @return
     */
    public String getImage() {
        return IMAGE[indice];
    }

    /**
     * Retourne vrai si l'indice correspond à "normal" et faux dans le cas contraire
     *
     * @return
     */
    public boolean normal() {
        return indice==1;
    }

    /**
     * Méthode permettant de calculer l'img
     *
     * @return
     */
    private double calculImg() {
        double tailleInMeter = taille/100.0;
        return (1.2 * poids/(tailleInMeter * tailleInMeter)) + (0.23 * age) - (10.83 * sexe) - 5.4;
    }

    /**
     * Méthode permettant de déterminer la valeur de l'indice
     * @return
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
