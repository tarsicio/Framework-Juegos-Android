package com.tarsicio.carrizales.frameworkgames2d.modelo;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.Audio;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.Musica;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.Sonido;

import java.io.IOException;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Audio
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public class AndroidAudio implements Audio {

    /**
     * @param assets
     * @param SoundPool
     *
     * La propiedad assets permite acceder a los archivos *.png o a los archivos *.ogg los cuales
     * estan ubicados en la Carpeta assets dentro del proyecto del presente juego
     *
     * La propiedad SoundPool permite Gestionar efectivamente de que forma se ejecutaran los
     * sonidos en nuestro juego
     */

    private AssetManager assets;
    private SoundPool soundPool;

    /**
     * La Clase AndroidAudio la cual nos permite manejar, todo lo relacionado con
     * el manejo de audio, para manejo de Sonido y musica.
     * Esta clase implementa de la interface Audio.java, en el futuro esta clase se puede
     * modificar e incluir nuevas características.
     * @param activity
     */
    public AndroidAudio(Activity activity) {
        setAssets(activity);
        setSoundPool();
    }

    /**
     * Este Metodo abre el nombrearchivo indicado el cual reproducirá la Musica
     * @param nombrearchivo
     * @return
     */
    @Override
    public Musica nuevaMusica(String nombrearchivo){
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(nombrearchivo);
            return new AndroidMusica(assetDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("No se ha podido cargar el Archivo '" + nombrearchivo + "'");
        }
    }

    /**
     *  Este Metodo abre el filename indicado el cual reproducirá el Sonido
     * @param filename
     * @return
     */
    @Override
    public Sonido nuevoSonido(String filename){
        try {
            AssetFileDescriptor assetDescriptor = assets.openFd(filename);
            int soundId = soundPool.load(assetDescriptor,0);
            return new AndroidSonido(soundPool, soundId);
        } catch (IOException e){
            throw new RuntimeException("No se ha podido cargar el Archivo '" + filename + "'");
        }
    }

    /**
     * Asigna a la Propiedad assets su nuevo valor
     * @param activity
     */
    private void setAssets(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
    }

    /**
     * Asigna a la Propiedad SoundPool su nuevo valor
     */
    private void setSoundPool(){
        this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
    }

    /**
     * Retorna el Valor de la Propiedad assets
     * @return
     */
    public AssetManager getAssets(){
        return this.assets;
    }

    /**
     * Retorna el Valor de la Propiedad SoundPool
     * @return
     */
    public SoundPool getSoundPool(){
        return this.soundPool;
    }
}
