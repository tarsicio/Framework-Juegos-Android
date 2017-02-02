package com.tarsicio.bibliotecagamesve.interfaces;

import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public interface Input {
    /**
     *Esta Interface contiene los metodos a implementar para cuando el usuario toque la
     * tecla de dirección \, metodo Arriba y abajo ( DOWN y UP)
     */
    public static class KeyEvent{
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public int keyChar;

        public String toString(){
            StringBuilder builder = new StringBuilder();
            if (type == KEY_DOWN)
                builder.append("Tecla Pulsada");
            else
                builder.append("Tecla levantada");
            builder.append(keyCode);
            builder.append(",");
            builder.append(keyChar);
            return builder.toString();
        }


    }

    /**
     * la interface implementa los eventos al tocar la pantalla.
     */

    public static class TouchEvent{
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int type;
        public int x, y;
        public int pointer;

        public String toString(){
            StringBuilder builder = new StringBuilder();
            if (type == TOUCH_DOWN)
                builder.append("Dedo en pantalla");
            else if (type == TOUCH_DRAGGED)
                builder.append("desplazando por Pantalla");
            else
                builder.append("dedo Levantado");
            builder.append(pointer);
            builder.append(",");
            builder.append(x);
            builder.append(",");
            builder.append(y);
            return builder.toString();
        }

    }

    /**
     * Para los metodos a implementar en los cuales se puede ver cual fue la tecla que piso el\
     * Usuario
     * @param keyCode
     * @return
     */

    public boolean isKeyPressed(int keyCode);
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public float getAccelX();
    public float getAccelY();
    public float getAccelZ();

    public List<KeyEvent> getKeyEvents();
    public List<TouchEvent> getTouchEvents();
}
