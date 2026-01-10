package com.example.coach.contract;

/**
 * Interface permettant le lien entre la vue et le presenter
 */
public interface ICalculView {
    void afficherResultat(String image, double img, String message, boolean normal);
}
