package com.example.juddyreina.missensores;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class magnetic extends AppCompatActivity implements SensorEventListener{

    private TextView salida;
    private TextView infoSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);

        salida= (TextView) findViewById(R.id.salida);
        infoSensor=(TextView) findViewById(R.id.info);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listasSensores = sensorManager.getSensorList(Sensor.TYPE_ALL);

        if(!listasSensores.isEmpty()){
            Sensor sensorMF= listasSensores.get(0);
            sensorManager.registerListener(this,sensorMF, SensorManager.SENSOR_DELAY_NORMAL);

            String info="Nombre: "+sensorMF.getName()+"\n"+"Fabricante: "+sensorMF.getVendor()
                    +"\n"+"Tipo: "+sensorMF.getType()+"\n"+"Version: "+sensorMF.getVersion()+"\n"
                    +"Potencia usada (mA): "+sensorMF.getPower()+"\n"+"Resolucion: "+sensorMF.getResolution()
                    +"\n"+"Rango Maximo: "+sensorMF.getMaximumRange()+"\n";
            infoSensor.setText(info);
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            switch (event.sensor.getType()){
                case Sensor.TYPE_MAGNETIC_FIELD:
                    String msj="X="+event.values[0]+"\nY= "+event.values[1]+"\nZ= "+event.values[2];
                    salida.setText(msj);
                    break;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
