package com.tarsicio.carrizales.frameworkgames2d.modelo;

import android.media.SoundPool;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.Sonido;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Sonido
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public class AndroidSonido implements Sonido {

    private int soundId;
    private SoundPool soundPool;

    public AndroidSonido(SoundPool soundPool, int soundId){
        setSoundId(soundId);
        setSoundPool(soundPool);
    }

    @Override
    public void play(float volume){
        getSoundPool().play(getSoundId(),volume, volume,0, 0, 1);
    }

    @Override
    public void dispose(){
        getSoundPool().unload(getSoundId());
    }

    private void setSoundId(int soundId){this.soundId = soundId;}
    private void setSoundPool(SoundPool soundPool){this.soundPool = soundPool;}

    public int getSoundId(){return this.soundId;}
    public SoundPool getSoundPool(){return this.soundPool;}
}
