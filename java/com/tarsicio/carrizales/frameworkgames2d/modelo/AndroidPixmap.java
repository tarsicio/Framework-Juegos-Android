package com.tarsicio.carrizales.frameworkgames2d.modelo;

import android.graphics.Bitmap;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.Graficos;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.Pixmap;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Pixmap
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
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
