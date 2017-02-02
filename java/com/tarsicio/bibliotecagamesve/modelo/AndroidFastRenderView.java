package com.tarsicio.bibliotecagamesve.modelo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see SurfaceView
 * Aplicación de Juego el Pirata (PiratasGames)
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable {

    private AndroidJuego juego;
    private Bitmap framebuffer;
    private Thread renderThread = null;
    private SurfaceHolder holder;
    private volatile boolean running;

    public AndroidFastRenderView(AndroidJuego juego, Bitmap framebuffer){
        super(juego);
        setJuego(juego);
        setFramebuffer(framebuffer);
        setHolder();
        setRunning(false);
    }

    public void resume(){
        setRunning(true);
        this.renderThread = new Thread(this);
        this.renderThread.start();
    }

    public void run(){
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while (getRunning()){
            if (!holder.getSurface().isValid())
                continue;

            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            getJuego().getPantallaCorriente().actualizar(deltaTime);
            getJuego().getPantallaCorriente().presente(deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(getFramebuffer(),null,dstRect,null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause(){
        setRunning(false);
        while (true){
            try {
                renderThread.join();
                break;
            } catch (InterruptedException e){
                //Mensaje
            }
        }
    }

    /**
     * ********************************* SET ***********************************************
     */

    private void setJuego(AndroidJuego juego){this.juego = juego;}
    private void setFramebuffer(Bitmap framebuffer){this.framebuffer = framebuffer;}
    private void setHolder(){this.holder = getHolder();}
    private void setRunning(boolean estado){this.running = estado;}

    /**
     * ******************************* GET *************************************************
     */

    public AndroidJuego getJuego(){return this.juego;}
    public Bitmap getFramebuffer(){return this.framebuffer;}
    public boolean getRunning(){return this.running;}
}
