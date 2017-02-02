package com.tarsicio.bibliotecagamesve.modelo;

import android.view.MotionEvent;
import android.view.View;

import com.tarsicio.bibliotecagamesve.interfaces.Input.TouchEvent;
import com.tarsicio.bibliotecagamesve.interfaces.Pool;
import com.tarsicio.bibliotecagamesve.interfaces.Pool.PoolObjectFactory;
import com.tarsicio.bibliotecagamesve.interfaces.TouchHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see TouchHandler
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class MultiTouchHandler implements TouchHandler {

    private boolean[] isTouched = new boolean[20];
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];
    private Pool<TouchEvent> touchEventPool;
    private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
    private float scaleX;
    private float scaleY;

    public MultiTouchHandler(View view, float scaleX, float scaleY){
        PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>(){
            @Override
            public TouchEvent createObject(){
                return new TouchEvent();
            }
        };

        this.touchEventPool = new Pool<TouchEvent>(factory,100);
        view.setOnTouchListener(this);

        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        synchronized (this) {
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            TouchEvent touchEvent;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = touchEventPool.newObject();
                    touchEvent.type = touchEvent.TOUCH_DOWN;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = this.touchX[pointerId] = (int) (event.getX(pointerIndex) * this.scaleX);
                    touchEvent.y = this.touchY[pointerId] = (int) (event.getY(pointerIndex) * this.scaleY);
                    this.isTouched[pointerId] = true;
                    this.touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent = this.touchEventPool.newObject();
                    touchEvent.type = touchEvent.TOUCH_UP;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = this.touchX[pointerId] = (int) (event.getX(pointerIndex) * this.scaleX);
                    touchEvent.y = this.touchY[pointerId] = (int) (event.getY(pointerIndex) * this.scaleY);
                    this.isTouched[pointerId] = false;
                    this.touchEventsBuffer.add(touchEvent);
                    break;

                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for (int i = 0; i < pointerCount; i++) {
                        pointerIndex = i;
                        pointerId = event.getPointerId(pointerIndex);
                        touchEvent = this.touchEventPool.newObject();
                        touchEvent.type = touchEvent.TOUCH_DRAGGED;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = this.touchX[pointerId] = (int) (event.getX(pointerIndex) * this.scaleX);
                        touchEvent.y = this.touchY[pointerId] = (int) (event.getY(pointerIndex) * this.scaleY);
                        this.touchEventsBuffer.add(touchEvent);
                    }
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean isTouchDown(int pointer){
        synchronized (this){
            if (pointer < 0 || pointer >= 20)
                return false;
            else
                return this.isTouched[pointer];
        }
    }

    @Override
    public int getTouchX(int pointer){
        synchronized (this){
            if (pointer < 0 || pointer >= 20)
                return 0;
            else
                return this.touchX[pointer];
        }
    }

    @Override
    public int getTouchY(int pointer){
        synchronized (this){
            if (pointer < 0 || pointer >= 20)
                return 0;
            else
                return this.touchY[pointer];
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents(){
        synchronized (this){
            int len = this.touchEvents.size();
            for (int i = 0; i < len; i++)
                this.touchEventPool.free(this.touchEvents.get(i));
            this.touchEvents.clear();
            this.touchEvents.addAll(this.touchEventsBuffer);
            this.touchEventsBuffer.clear();
            return this.touchEvents;
        }
    }
}
