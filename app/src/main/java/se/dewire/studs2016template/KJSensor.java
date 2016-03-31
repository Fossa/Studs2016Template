package se.dewire.studs2016template;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kajo on 2016-03-31.
 */
public class KJSensor implements ISensor, SensorEventListener{
    List<Observer> observers = new ArrayList<>();

    private SensorManager sensorManager;
    private long lastUpdate;
    Boolean bool = false, bool1 = false;

    final float ACC_THRESHOLD = 20F;

    public KJSensor(Context ctx){
        sensorManager = (SensorManager) ctx.getSystemService(ctx.SENSOR_SERVICE);
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
        lastUpdate = System.currentTimeMillis();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            // Movement
            //float x = values[0]-SensorManager.GRAVITY_EARTH;
            //float y = values[1]-SensorManager.GRAVITY_EARTH;
            //float z = values[2]-SensorManager.GRAVITY_EARTH;

            float x = values[0];
            float y = values[1];
            float z = values[2];

            //float accelationSquareRoot = (x * x + y * y + z * z)
            //        / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            //System.out.println("accelationSquareRoot is:- "
            //        + accelationSquareRoot);
            long actualTime = System.currentTimeMillis();
            //System.out.println("Sensor Event x:"+x+" y:"+y+" z:"+z);
            if (x >= ACC_THRESHOLD && y > -ACC_THRESHOLD && y < ACC_THRESHOLD){ // Left shake
                notify(Observer.Event.LEFT);
                System.out.println("LEFT");
            }
            else if(x <= -ACC_THRESHOLD && y > -ACC_THRESHOLD && y < ACC_THRESHOLD){ // Right shake
                notify(Observer.Event.RIGHT);
                System.out.println("RIGHT");
            }
            else if(y >= ACC_THRESHOLD && x > -ACC_THRESHOLD && x < ACC_THRESHOLD) {
                notify(Observer.Event.UP);
                System.out.println("UP");
            }
            else if(y <= -ACC_THRESHOLD && x > -ACC_THRESHOLD && x < ACC_THRESHOLD) {
                notify(Observer.Event.DOWN);
                System.out.println("DOWN");
            }
        }
    }

    private void notify(Observer.Event e) {
        for(Observer o : observers) {
            o.event(e);
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }
}
