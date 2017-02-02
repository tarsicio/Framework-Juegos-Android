package com.tarsicio.bibliotecagamesve.interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class Pool<T> {

    public interface PoolObjectFactory<T>{
        public T createObject();
    }

    private final List<T> objetoGratuito;
    private final PoolObjectFactory<T> fabrica;
    private final int asignacionMaxima;

public Pool(PoolObjectFactory<T> fabrica, int asignacionMaxima){
    this.fabrica = fabrica;
    this.asignacionMaxima = asignacionMaxima;
    this.objetoGratuito = new ArrayList<T>(asignacionMaxima);
    }

    public T newObject(){
        T object = null;
        if (objetoGratuito.size() == 0)
            object = fabrica.createObject();
        else
            object = objetoGratuito.remove(objetoGratuito.size() - 1);
        return object;
    }

    public void free(T object){
        if (objetoGratuito.size() < asignacionMaxima)
            objetoGratuito.add(object);
    }

}
