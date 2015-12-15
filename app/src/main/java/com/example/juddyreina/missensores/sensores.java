package com.example.juddyreina.missensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;


public class sensores extends AppCompatActivity{
    //_____________________________________________________PASO 1
    private TextView LSensores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        LSensores= (TextView) findViewById(R.id.listSensores);

        //_____________________________________________________PASO 2
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//SOLICITAR AL SISTEMA UN SERVICIO ESPECIFICO
        //con SENSOR_SERVICE se especifica que se van a usar los SENSORES
        //*** el método getSystemService pertenece a la clase Context (Al ser Activity, se es Context)

        //_____________________________________________________PASO 3
        List<Sensor> listaSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        //____________________________________________________PASO 5
        for(Sensor sensor : listaSensores) {
            mostrar(sensor.getName());
        }

    }

    //_________________________________________________________PASO 4

    private void mostrar(String string) {
        LSensores.append("-" + string + "\n");
    }

}
