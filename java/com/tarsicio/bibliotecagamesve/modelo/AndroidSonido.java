package com.tarsicio.bibliotecagamesve.modelo;

import android.media.SoundPool;

import com.tarsicio.bibliotecagamesve.interfaces.Sonido;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Sonido
 * Aplicación de Juego el Pirata (PiratasGames)
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
