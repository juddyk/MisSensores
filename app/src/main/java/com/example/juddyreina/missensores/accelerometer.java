package com.example.juddyreina.missensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

//_______________________________PASO 0 Implementar interface SensorEventListener
//                                     y sus metodos onSensorChanged() y onAccuracyChanged
public class accelerometer extends AppCompatActivity implements SensorEventListener {
    //________________________________
    private TextView salida;
    private TextView infoSensor;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
//____________________________
        salida = (TextView) findViewById(R.id.salida);
        infoSensor = (TextView) findViewById(R.id.info);
//____________________________PASO 1 Servicios de sensores
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//____________________________PASO 2 Listar los sensores de tipo Acelerometro
        List<Sensor> ListaSensores =sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
//____________________________PASO 3 Asegurarse de que el sensor existe
        if(!ListaSensores.isEmpty()){
//____________________________PASO 4
            Sensor sensorMF= ListaSensores.get(0);//selecciona el primer sensor
//____________________________PASO 5 registrar el sensor
            sensorManager.registerListener(this, sensorMF,SensorManager.SENSOR_DELAY_NORMAL);
//____________________________PASO 6 mostrar la información
            String info="Nombre: "+sensorMF.getName()+"\n"+"Fabricante: "+sensorMF.getVendor()
                    +"\n"+"Tipo: "+sensorMF.getType()+"\n"+"Version: "+sensorMF.getVersion()+"\n"
                    +"Potencia usada (mA): "+sensorMF.getPower()+"\n"+"Resolucion: "+sensorMF.getResolution()
                    +"\n"+"Rango Maximo: "+sensorMF.getMaximumRange()+"\n";
            infoSensor.setText(info);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){ //sincronizar acceso
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER:
//____________________________PASO 7 mostrar los valores del sensor
                    String msj="X="+event.values[0]+"\nY= "+event.values[1]+"\nZ= "+event.values[2];
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

    @Override
    protected void onPause() {
        super.onPause();
        // Se detiene el listener para no malgastar la bateria
        sensorManager.unregisterListener(this);
    }



}
