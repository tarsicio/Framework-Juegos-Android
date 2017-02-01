package com.tarsicio.carrizales.frameworkgames2d.modelo;

import android.content.res.AssetManager;
import android.os.Environment;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see FileIO
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public class AndroidFileIO implements FileIO {

    private AssetManager assets;
    private String rutaAlmacenamientoExterno;

    /**
     *
     * @param assets
     */
    public AndroidFileIO(AssetManager assets){
        setAssets(assets);
        setRutaAlmacenamientoExterno();
    }

    /**
     *
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    @Override
    public InputStream leerAsset(String nombreArchivo) throws IOException{
        return assets.open(nombreArchivo);
    }

    /**
     *
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    @Override
    public InputStream leerArchivo(String nombreArchivo) throws IOException{
        return new FileInputStream(getRutaAlmacenamientoExterno() + nombreArchivo);
    }

    /**
     *
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    @Override
    public OutputStream escribirArchivo(String nombreArchivo) throws IOException{
        return new FileOutputStream(getRutaAlmacenamientoExterno() + nombreArchivo);
    }

    /**
     *
     * @param assets
     */
    private void setAssets(AssetManager assets){
        this.assets = assets;
    }

    /**
     *
     */
    private void setRutaAlmacenamientoExterno(){
        this.rutaAlmacenamientoExterno = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
    }

    /**
     *
     * @return
     */
    public AssetManager getAssets(){
        return this.assets;
    }

    /**
     *
     * @return
     */
    public String getRutaAlmacenamientoExterno(){
        return this.rutaAlmacenamientoExterno;
    }
}
