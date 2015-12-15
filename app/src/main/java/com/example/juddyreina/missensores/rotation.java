package com.example.juddyreina.missensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

//____________________________________________________________________________________________PASO 0
public class rotation extends AppCompatActivity implements SensorEventListener {

    private TextView salida;
    private TextView infoSensor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        salida = (TextView) findViewById(R.id.salida);
        infoSensor = (TextView) findViewById(R.id.info);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> ListaSensores = sensorManager.getSensorList(Sensor.TYPE_ROTATION_VECTOR);

        if (!ListaSensores.isEmpty()) {//Asegurar que si se tenga el sensor
            Sensor sensorA = ListaSensores.get(0);//selecciona el primer sensor
            sensorManager.registerListener(this, sensorA, SensorManager.SENSOR_DELAY_NORMAL);//registrar sensor
            // el primer parametro es un objeto con interface SensorEventListener
            // el segundo parametro es el sensor
            // el tercer parametro es la frecuencia de recepción de actualizacion del sensor
            String info = "Nombre: " + sensorA.getName() + "\n" + "Fabricante: " + sensorA.getVendor() + "\n" + "Tipo: " + sensorA.getType() + "\n" + "Version: " + sensorA.getVersion() + "\n";
            info = info + "Potencia usada (mA): " + sensorA.getPower() + "\n" + "Resolucion: " + sensorA.getResolution() + "\n" + "Rango Maximo: " + sensorA.getMaximumRange() + "\n";
            infoSensor.setText(info);
        } else {
            infoSensor.setText("NO");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) { //sincronizar acceso
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ROTATION_VECTOR:
                    float x = (float) Math.round(event.values[0] * 10000) / 10000;
                    float y = (float) Math.round(event.values[1] * 10000) / 10000;
                    float z = (float) Math.round(event.values[2] * 10000) / 10000;

                    String msj = "X*SIN(th/2)=" + event.values[0] + "\nY*SIN(th/2)= " + event.values[1] + "\nZ*SIN(th/2)= " + event.values[2];
                    salida.setText(msj);
                    break;
                default:
                    salida.setText("NADA");

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}