package com.tarsicio.bibliotecagamesve.modelo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Creado por el Autor el día 19/05/2016.
 * @author  Tarsicio Carrizales, twitter: @tarsicio_tic, Mail: telecom.com.ve@gmail.com
 * @version 1.0
 * @see SensorEventListener
 * Aplicación de Juego el Pirata (PiratasGames)
 */

public class AccelerometerHandler implements SensorEventListener {

    private float accelX;
    private float accelY;
    private float accelZ;

    public AccelerometerHandler(Context context){
        SensorManager manager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        if (manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0){
            Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        //No hace nada
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        setAccelX(event);
        setAccelY(event);
        setAccelZ(event);
    }

    /**
     * *************************************** SET ********************************************
     * @param event
     */
    private void setAccelX(SensorEvent event){this.accelX = event.values[0];}

    private void setAccelY(SensorEvent event){this.accelY = event.values[1];}

    private void setAccelZ(SensorEvent event){this.accelZ = event.values[2];}

    /**
     * **************************************** GET *******************************************
     * @return
     */
    public float getAccelX(){return this.accelX;}

    public float getAccelY(){return this.accelY;}

    public float getAccelZ(){return this.accelZ;}

}
