package com.tarsicio.bibliotecagamesve.interfaces;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface Graficos {


 enum formaMapaPixeles{
        ARGB8888, ARGB4444, ARGB565
 }

    /**
     *
     * @param fileName
     * @param format
     * @return
     */
    public Pixmap nuevoMapaPixeles(String fileName, formaMapaPixeles format);

    /**
     *
     * @param color
     */
    public void limpiar(int color);


    /**
     *
     * @param x
     * @param y
     * @param color
     */
    public void dibujarPixel(int x, int y, int color);


    /**
     *
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @param color
     */
    public void dibujarLinea(int x, int y, int x2, int y2, int color);


    /**
     *
     * @param x
     * @param y
     * @param ancho
     * @param alto
     * @param color
     */
    public void dibujarRectangulo(int x, int y, int ancho, int alto, int color);


    /**
     *
     * @param pixmap
     * @param x
     * @param y
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public  void dibujarMapaPixeles(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);


    /**
     *
     * @param pixmap
     * @param x
     * @param y
     */
    public void dibujarMapaPixeles(Pixmap pixmap, int x, int y);


    /**
     *
     * @return
     */
    public int getAncho();


    /**
     *
     * @return
     */
    public int getAlto();
}
