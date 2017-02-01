package com.tarsicio.carrizales.frameworkgames2d.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
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
