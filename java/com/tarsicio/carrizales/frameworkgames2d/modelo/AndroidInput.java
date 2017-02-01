package com.tarsicio.carrizales.frameworkgames2d.modelo;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.Input;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.TouchHandler;

import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see Input
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public class AndroidInput implements Input {

    private AccelerometerHandler accelHandler;
    private KeyboardHandler keyHandler;
    private TouchHandler touchHandler;

    /**
     *
     * @param context
     * @param view
     * @param scaleX
     * @param scaleY
     */
    public AndroidInput(Context context, View view, float scaleX, float scaleY){
        setAccelHandler(context);
        setKeyHandler(view);
        setTouchHandler(view,scaleX,scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode){
        return getKeyHandler().isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer){
        return getTouchHandler().isTouchDown(pointer);
    }

    @Override
    public int getTouchX(int pointer){
        return getTouchHandler().getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer){
        return getTouchHandler().getTouchY(pointer);
    }

    @Override
    public float getAccelX(){
        return getAccelHandler().getAccelX();
    }

    @Override
    public float getAccelY(){
        return getAccelHandler().getAccelY();
    }

    @Override
    public float getAccelZ(){
        return getAccelHandler().getAccelZ();
    }

    @Override
    public List<TouchEvent> getTouchEvents(){
        return getTouchHandler().getTouchEvents();
    }

    @Override
    public List<KeyEvent> getKeyEvents(){
        return getKeyHandler().getKeyEvents();
    }

    /**************************** SET ***************************************************
     *
     * @param context
     */
    private void setAccelHandler(Context context){
        accelHandler = new AccelerometerHandler(context);
    }

    /**
     *
     * @param view
     */
    private void setKeyHandler(View view){
        keyHandler = new KeyboardHandler(view);
    }

    /**
     *
     * @param view
     * @param scaleX
     * @param scaleY
     */
    private void setTouchHandler(View view, float scaleX, float scaleY){
        if (Integer.parseInt(Build.VERSION.SDK) < 5)
            touchHandler = new SingleTouchHandler(view,scaleX,scaleY);
        else
            touchHandler = new MultiTouchHandler(view,scaleX,scaleY);
    }

    /************************* GET *****************************************************
     *
     * @return
     */
    private AccelerometerHandler getAccelHandler(){
        return this.accelHandler;
    }

    private KeyboardHandler getKeyHandler(){
        return this.keyHandler;
    }

    private TouchHandler getTouchHandler(){
        return this.touchHandler;
    }
}
