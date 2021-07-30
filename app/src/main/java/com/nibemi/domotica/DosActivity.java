package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DosActivity extends AppCompatActivity {
    Button btn_regresar_dos, btn_actualizar;
    TextView NivelAct, NivelSet, TempAct, TempSet;

    private LineChart grafica;
    ArrayList<Entry> yvalues = new ArrayList<>();
    int x = 0;

    //Instancia a la base de datos
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    //apuntamos al nodo que queremos leer
    DatabaseReference myRef = fdb.getReference("NIVTEMP");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);

        FirebaseApp.initializeApp(this);

        grafica = (LineChart)findViewById(R.id.chartLineaTemp);

        yvalues.add(new Entry(x, 0));
        x+=1;

        NivelAct = (TextView)findViewById(R.id.NivelAct);
        NivelSet = (TextView)findViewById(R.id.NivelSet);
        TempAct = (TextView)findViewById(R.id.TempAct);
        TempSet = (TextView)findViewById(R.id.TempSet);

        btn_regresar_dos = (Button)findViewById(R.id.btn_regresar_dos);
        btn_actualizar = (Button)findViewById(R.id.btn_actualizar_nt);

        btn_regresar_dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DosActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nivels = NivelSet.getText().toString();
                String temps = TempSet.getText().toString();

                if ((!nivels.isEmpty()) && (!temps.isEmpty())){
                    myRef.child("NivelSet").setValue(Integer.parseInt(nivels));
                    myRef.child("TempSet").setValue(Integer.parseInt(temps));

                    Toast.makeText(getApplicationContext(), "Valores Actualizados", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Los valores deben ser numericos y son obligatorios", Toast.LENGTH_LONG).show();
                }

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String nivel_actual = dataSnapshot.child("NivelAct").getValue().toString();
                String temp_actual = dataSnapshot.child("TempAct").getValue().toString();

                String nivel_set = dataSnapshot.child("NivelSet").getValue().toString();
                String temp_set = dataSnapshot.child("TempSet").getValue().toString();

                NivelAct.setText(nivel_actual+" %");
                TempAct.setText(temp_actual+ " °");

                NivelSet.setText(nivel_set);
                TempSet.setText(temp_set);

                yvalues.add(new Entry(x, Float.parseFloat(temp_actual)));
                x+=1;
                Graficar();
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });
    }

    void Graficar(){
        grafica.clear();
        grafica.setDragEnabled(true);
        grafica.setScaleEnabled(false);

        LineDataSet set1 = new LineDataSet(yvalues, "Valores Temp");
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        grafica.setData(data);
    }
}