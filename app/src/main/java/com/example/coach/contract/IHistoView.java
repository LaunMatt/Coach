package com.example.coach.contract;

import com.example.coach.model.Profil;

import java.util.List;

/**
 * Interface qui permet le lien entre la vue et l'adapter
 */
public interface IHistoView extends IAllView {
    void afficherListe(List profils);
    void transfertProfil(Profil profil);
}
