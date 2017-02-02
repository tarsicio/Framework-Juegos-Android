package com.tarsicio.bibliotecagamesve.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface Audio {

    /**
     * Metodo público sin implementar por la interface Audio.java
     * La implementación se realiza en la clase AndroidAudio.java
     * @param nombreArchivo
     * @return
     */
    public Musica nuevaMusica(String nombreArchivo);

    /**
     * Metodo público sin implementar por la interface Audio.java
     * La implementación se realiza en la clase AndroidAudio.java
     * @param nombreArchivo
     * @return
     */
    public Sonido nuevoSonido(String nombreArchivo);
}
