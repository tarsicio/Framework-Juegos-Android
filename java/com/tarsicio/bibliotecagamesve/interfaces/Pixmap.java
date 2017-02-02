package com.tarsicio.bibliotecagamesve.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface Pixmap {

    public int getAncho();

    public int getAlto();

    public Graficos.formaMapaPixeles getFormat();

    public void dispose();
}
