package com.tarsicio.carrizales.frameworkgames2d.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public interface Musica {

    public void play();
    public void stop();
    public void pause();
    public void setLooping(boolean looping);
    public void setVolume(float volume);
    public boolean isPlaying();
    public boolean isStopped();
    public boolean isLoping();
    public void dispose();
}
