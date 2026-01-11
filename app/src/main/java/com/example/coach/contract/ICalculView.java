package com.example.coach.contract;

/**
 * Interface permettant le lien entre la vue et le presenter
 */
public interface ICalculView {
    /**
     * Méthode qui permet le transfert des résultats vers la vue
     *
     * @param image
     * @param img
     * @param message
     * @param normal
     */
    void afficherResultat(String image, double img, String message, boolean normal);

    /**
     * Méthode qui permet de remplir les champs dans la vue
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    void remplirChamps(Integer poids, Integer taille, Integer age, Integer sexe);
}
