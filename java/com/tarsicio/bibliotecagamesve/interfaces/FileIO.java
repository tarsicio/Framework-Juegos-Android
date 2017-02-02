package com.tarsicio.bibliotecagamesve.interfaces;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface FileIO {

    /**
     * Metodo público sin implementar por la interface FileIO.java
     * La implementación se realiza en la clase AndroidFileIO.java
     *
     * El metodo leerAssets busca un archivo en particular dentro de la carpeta assets
     * dentro del proyecto del juego y lo retorna
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public InputStream leerAsset(String nombreArchivo) throws IOException;

    /**
     *Metodo público sin implementar por la interface FileIO.java
     * La implementación se realiza en la clase AndroidFileIO.java
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public InputStream leerArchivo(String nombreArchivo) throws IOException;

    /**
     *Metodo público sin implementar por la interface FileIO.java
     * La implementación se realiza en la clase AndroidFileIO.java
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public OutputStream escribirArchivo(String nombreArchivo) throws IOException;
}
