package com.tarsicio.bibliotecagamesve.modelo;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.tarsicio.bibliotecagamesve.interfaces.Musica;

import java.io.IOException;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Musica
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class AndroidMusica implements Musica, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private boolean isPrepared;

    public AndroidMusica(AssetFileDescriptor assetDescriptor){
        setIsPrepared(false);
        setMediaPlayer(assetDescriptor);
    }

    @Override
    public void play(){
        if (getMediaPlayer().isPlaying())
            return;
        try {
            synchronized (this) {
                if (!getIsPrepared())
                    getMediaPlayer().prepare();
                getMediaPlayer().start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
        getMediaPlayer().stop();
        synchronized (this){
            setIsPrepared(false);
        }
    }

    @Override
    public void pause(){
        if (getMediaPlayer().isPlaying())
            getMediaPlayer().pause();
    }

    @Override
    public void setLooping(boolean isLooping){
        getMediaPlayer().setLooping(isLooping);
    }

    @Override
    public void setVolume(float volume){
        getMediaPlayer().setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying(){
        return getMediaPlayer().isPlaying();
    }

    @Override
    public boolean isStopped(){
        return !getIsPrepared();
    }

    @Override
    public boolean isLoping(){
        return getMediaPlayer().isLooping();
    }

    @Override
    public void dispose(){
        if (getMediaPlayer().isPlaying())
            getMediaPlayer().stop();
        getMediaPlayer().release();
    }

    @Override
    public void onCompletion(MediaPlayer player){
        synchronized (this){
            setIsPrepared(false);
        }
    }

    private void setMediaPlayer(AssetFileDescriptor assetDescriptor){
        this.mediaPlayer = new MediaPlayer();
        try {
            this.mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
                    assetDescriptor.getStartOffset(),assetDescriptor.getLength());
            this.mediaPlayer.prepare();
            setIsPrepared(true);
            this.mediaPlayer.setOnCompletionListener(this);
        } catch (Exception e){
            throw new RuntimeException("No se ha podido Cargar la Musica");
        }
    }

    private void setIsPrepared(boolean estado){
        this.isPrepared = estado;
    }

    public MediaPlayer getMediaPlayer(){return this.mediaPlayer;}

    public boolean getIsPrepared(){return this.isPrepared;}
}
