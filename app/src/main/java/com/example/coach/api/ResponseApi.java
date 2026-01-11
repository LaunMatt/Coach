package com.example.coach.api;

/**
 * Classe qui permet de récupérer les éléments de réponse de l'API
 *
 * @param <T>
 */
public class ResponseApi<T> {
    private int code;
    private String message;
    private T result;

    /**
     * Retourne le code
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * Retourne le message
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retourne le result
     *
     * @return
     */
    public T getResult() {
        return result;
    }
}
