package com.tarsicio.carrizales.frameworkgames2d.modelo;


import android.view.View;
import android.view.View.OnKeyListener;

import com.tarsicio.carrizales.frameworkgames2d.interfaces.Input;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.Pool;
import com.tarsicio.carrizales.frameworkgames2d.interfaces.Pool.PoolObjectFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see OnKeyListener
 * Framework para utilizar y crear Juegos en 2D en Ventanas de 320x480
 */

public class KeyboardHandler implements OnKeyListener {

    private boolean[] pressedKeys = new boolean[128];
    private Pool<Input.KeyEvent> keyEventPool;
    private List<Input.KeyEvent> keyEventsBuffer = new ArrayList<Input.KeyEvent>();
    private List<Input.KeyEvent>  keyEvents = new ArrayList<Input.KeyEvent>();

    public KeyboardHandler(View view){
        PoolObjectFactory<Input.KeyEvent> factory = new PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };

        keyEventPool = new Pool<Input.KeyEvent>(factory,100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    @Override
    public boolean onKey(View v, int keyCode, android.view.KeyEvent event){
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
            return false;
        synchronized (this){
            Input.KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN){
                keyEvent.type = Input.KeyEvent.KEY_DOWN;
                if (keyCode > 0 && keyCode < 127 )
                    pressedKeys[keyCode] = true;
            }
            if (event.getAction() == android.view.KeyEvent.ACTION_UP){
                keyEvent.type = Input.KeyEvent.KEY_UP;
                if (keyCode > 0 && keyCode < 127 )
                    pressedKeys[keyCode] = false;
                keyEventsBuffer.add(keyEvent);
            }
            return false;
        }
    }

    public boolean isKeyPressed(int keyCode){
        if (keyCode < 0 || keyCode > 127)
            return false;
        return pressedKeys[keyCode];
    }

    public List<Input.KeyEvent> getKeyEvents(){
        synchronized (this){
            int len = keyEvents.size();
            for (int i = 0; i < len; i++)
                keyEventPool.free(keyEvents.get(i));
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }

}
