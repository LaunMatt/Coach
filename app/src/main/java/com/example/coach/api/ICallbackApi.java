package com.example.coach.api;

/**
 *  Interface qui permet de réaliser des traitements suivant le retour de l'API (succès ou erreur)
 *
 * @param <T>
 */
public interface ICallbackApi<T> {
    void onSuccess(T result);
    void onError();
}
