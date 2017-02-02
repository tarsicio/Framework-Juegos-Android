package com.tarsicio.bibliotecagamesve.interfaces;

import android.view.View.OnTouchListener;

import com.tarsicio.bibliotecagamesve.interfaces.Input.TouchEvent;

import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface TouchHandler extends OnTouchListener {

    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<TouchEvent> getTouchEvents();
}
