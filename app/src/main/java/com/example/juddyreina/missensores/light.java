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
public class light extends AppCompatActivity implements SensorEventListener {//implementar interface SensorEventListener
    // y sus metodos onSensorChanged() y onAccuracyChanged

    private TextView salida;
    private TextView infoSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        salida = (TextView) findViewById(R.id.salida);
        infoSensor = (TextView) findViewById(R.id.info);

        //____________________________________________________________________________________PASO 1

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//SOLICITAR AL SISTEMA UN SERVICIO ESPECIFICO
        //con SENSOR_SERVICE se especifica que se van a usar los SENSORES
        //*** el método getSystemService pertenece a la clase Context (Al ser Activity, se es Context)


        // __________________________________________________________________________________PASO 2
        List<Sensor> ListaSensores =sensorManager.getSensorList(Sensor.TYPE_LIGHT);//Se pide al sistema todos los sensores
        //de este tipo
        if(!ListaSensores.isEmpty()){//Asegurar que si se tenga el sensor
            Sensor sensorL= ListaSensores.get(0);//selecciona el primer sensor
            sensorManager.registerListener(this, sensorL,SensorManager.SENSOR_DELAY_NORMAL);//registrar sensor
            // el primer parametro es un objeto con interface SensorEventListener
            // el segundo parametro es el sensor
            // el tercer parametro es la frecuencia de recepción de actualizacion del sensor
            String info="Nombre: "+sensorL.getName()+"\n"+"Fabricante: "+sensorL.getVendor()+"\n"+"Tipo: "+sensorL.getType()+"\n"+"Version: "+sensorL.getVersion()+"\n";
            info=info+"Potencia usada (mA): "+sensorL.getPower()+"\n"+"Resolucion: "+sensorL.getResolution()+"\n"+"Rango Maximo: "+sensorL.getMaximumRange()+"\n";
            infoSensor.setText(info);
        }else{
            infoSensor.setText("NO");
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){ //sincronizar acceso
            switch (event.sensor.getType()){
                case Sensor.TYPE_LIGHT:
                    String msj="Luz Ambiente="+event.values[0]+"\n";
                    salida.setText(msj);
                    break;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
