package com.tarsicio.bibliotecagamesve.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public abstract class Pantalla {

    protected final Juego juego;

    public Pantalla(Juego juego){
        this.juego = juego;
    }

    public abstract void actualizar(float deltatime);

    public abstract void presente(float deltatime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
