package com.tarsicio.bibliotecagamesve.modelo;

import android.graphics.Bitmap;

import com.tarsicio.bibliotecagamesve.interfaces.Graficos;
import com.tarsicio.bibliotecagamesve.interfaces.Pixmap;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Pixmap
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class AndroidPixmap implements Pixmap {

    private Bitmap bitmap;
    private Graficos.formaMapaPixeles format;

    public AndroidPixmap(Bitmap bitmap, Graficos.formaMapaPixeles format){
        setBitmap(bitmap);
        setFormat(format);
    }

    /**
     * ******************************* SET ***************************************************
     * @param bitmap
     */
    private void setBitmap(Bitmap bitmap){this.bitmap = bitmap;}

    /**
     *
     * @param format
     */
    private void setFormat(Graficos.formaMapaPixeles format){this.format = format;}

    /**
     * ******************************* GET ***************************************************
     * @return
     */

    @Override
    public int getAncho(){
        return getBitmap().getWidth();
    }

    /**
     *
     * @return
     */
    @Override
    public int getAlto(){
        return getBitmap().getHeight();
    }

    /**
     *
     * @return
     */
    public Bitmap getBitmap(){return this.bitmap;}

    /**
     *
     * @return
     */
    @Override
    public Graficos.formaMapaPixeles getFormat(){
        return this.format;
    }

    /**
     *
     */
    @Override
    public void dispose(){
        getBitmap().recycle();
    }
}
