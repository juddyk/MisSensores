package com.example.juddyreina.missensores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    private Listas[] datos =
            new Listas[]{
                    new Listas(R.drawable.ic_sensor,"Search Sensors"),
                    new Listas(R.drawable.magnetic, "Magnetic Field Sensor"),
                    new Listas(R.drawable.proximity, "Proximity Sensor"),
                    new Listas(R.drawable.light, "Light Sensor"),
                    new Listas(R.drawable.ic_acelerometro, "Accelerometer Sensor"),
                    new Listas(R.drawable.rotation, "Rotation Sensor"),
                    new Listas(R.drawable.orientation, "Orientation Sensor")};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Adapter adaptador = new Adapter(this, datos);
        lst = (ListView) findViewById(R.id.lst);
        lst.setAdapter(adaptador);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, String.valueOf(parent.getItemIdAtPosition(position)), Toast.LENGTH_SHORT).show();
                Intent i;
                int idActivity = (int) parent.getItemIdAtPosition(position);
                switch (idActivity) {
                    case 0:
                        i = new Intent(MainActivity.this, sensores.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, magnetic.class);
                        startActivity(i);
                        break;
                    case 2:
                        i = new Intent(MainActivity.this, proximity.class);
                        startActivity(i);
                        break;
                    case 3:
                        i = new Intent(MainActivity.this, light.class);
                        startActivity(i);
                        break;
                    case 4:
                        i = new Intent(MainActivity.this, accelerometer.class);
                        startActivity(i);
                        break;
                    case 5:
                        i = new Intent(MainActivity.this, rotation.class);
                        startActivity(i);
                        break;
                    case 6:
                        i = new Intent(MainActivity.this, orientation.class);
                        startActivity(i);
                        break;

                }
            }
        });
    }
    public class Adapter extends ArrayAdapter<Listas> {

        public Adapter(Context context, Listas[] datos) {
            super(context, R.layout.list, datos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.list,null);

            TextView nombre = (TextView) item.findViewById(R.id.tv_sensor_name);
            nombre.setText(datos[position].getNombre());

            ImageView imagen = (ImageView) item.findViewById(R.id.imSensor);
            imagen.setImageResource(datos[position].getIdImage());

            return item;
        }
    }
}
