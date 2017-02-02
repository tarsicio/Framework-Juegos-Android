package com.tarsicio.bibliotecagamesve.modelo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

import com.tarsicio.bibliotecagamesve.interfaces.Audio;
import com.tarsicio.bibliotecagamesve.interfaces.FileIO;
import com.tarsicio.bibliotecagamesve.interfaces.Graficos;
import com.tarsicio.bibliotecagamesve.interfaces.Input;
import com.tarsicio.bibliotecagamesve.interfaces.Juego;
import com.tarsicio.bibliotecagamesve.interfaces.Pantalla;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Activity
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public abstract class AndroidJuego extends Activity implements Juego {

    private AndroidFastRenderView renderView;
    private Graficos graficos;
    private Audio audio;
    private Input input;
    private FileIO fileIO;
    private Pantalla pantalla1;
    private PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

        float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();

        setRenderView(frameBuffer);
        setGraficos(frameBuffer);
        setAudio();
        setInput(scaleX,scaleY);
        setFileIO();
        setPantallaAndroidJuego1();
        setContentView(getRenderView());
        setWakeLock();
    }

    @Override
    public void onResume(){
        super.onResume();
        getWakeLock().acquire();
        getPantallaAndroidJuego1().resume();
        getRenderView().resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        getWakeLock().release();
        getRenderView().pause();
        getPantallaAndroidJuego1().pause();
        if (isFinishing())
            getPantallaAndroidJuego1().dispose();
    }

    @Override
    public Input getInput(){
        return getInputAndroidJuego1();
    }

    @Override
    public FileIO getFileIO(){
        return getFileIOAndroidJuego1();
    }

    @Override
    public Graficos getGraphics(){
        return getGraficos();
    }

    @Override
    public Audio getAudio(){
        return getAudioAndroidJuego1();
    }

    @Override
    public void setPantalla(Pantalla pantalla){
        if (pantalla == null)
            throw new IllegalArgumentException("La pantalla no puede ser Nula");
        getPantallaAndroidJuego1().pause();
        getPantallaAndroidJuego1().dispose();
        pantalla.resume();
        pantalla.actualizar(0);
        setPantallaAndroidJuego2(pantalla);
    }

    @Override
    public Pantalla getPantallaCorriente(){
        return getPantallaAndroidJuego1();
    }


    /**
     * **************************************************************************************
     * ************************************ SET *********************************************
     * **************************************************************************************
     */

    private void setRenderView(Bitmap frameBuffer){
        this.renderView = new AndroidFastRenderView(this,frameBuffer);
    }

    private void setGraficos(Bitmap frameBuffer){
        this.graficos = new AndroidGraficos(getAssets(),frameBuffer);
    }

    private void setAudio(){
        this.audio = new AndroidAudio(this);
    }

    private void setInput(float scaleX,float scaleY){
        input = new AndroidInput(this,getRenderView(),scaleX,scaleY);
    }

    private void setFileIO(){
        fileIO = new AndroidFileIO(getAssets());
    }

    private void setPantallaAndroidJuego1(){
        this.pantalla1 = getIniciarPantalla();
    }

    private void setPantallaAndroidJuego2(Pantalla pantalla){
        this.pantalla1 = pantalla;
    }

    private void setWakeLock(){
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"GLGame");
    }

    /**
     * **************************************************************************************
     * ************************************ GET *********************************************
     * **************************************************************************************
     */

    public AndroidFastRenderView getRenderView(){
        return this.renderView;
    }

    public Graficos getGraficos(){
        return this.graficos;
    }

    public Audio getAudioAndroidJuego1(){
        return this.audio;
    }

    public Input getInputAndroidJuego1(){
        return this.input;
    }

    public FileIO getFileIOAndroidJuego1(){
        return this.fileIO;
    }

    public Pantalla getPantallaAndroidJuego1(){
        return this.pantalla1;
    }

    public PowerManager.WakeLock getWakeLock(){
        return this.wakeLock;
    }

}