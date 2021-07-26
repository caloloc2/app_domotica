package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UnoActivity extends AppCompatActivity {
    Button btn_regresar_uno, btn_actualizar;
    TextView RpmActual, RpmSet, AnguloAct, AnguloSet;

    //Instancia a la base de datos
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    //apuntamos al nodo que queremos leer
    DatabaseReference myRef = fdb.getReference("MOTOR");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno);
        FirebaseApp.initializeApp(this);

        RpmActual = (TextView)findViewById(R.id.RpmActual);
        RpmSet = (TextView)findViewById(R.id.RpmSet);
        AnguloAct = (TextView)findViewById(R.id.AnguloAct);
        AnguloSet = (TextView)findViewById(R.id.AnguloSet);

        btn_regresar_uno = (Button)findViewById(R.id.btn_regresar_uno);
        btn_actualizar = (Button)findViewById(R.id.btn_actualizar);

        btn_regresar_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnoActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rpms, angulos;

                rpms = RpmSet.getText().toString();
                angulos = AnguloSet.getText().toString();

                if ((!rpms.isEmpty()) && (!angulos.isEmpty())){
                    myRef.child("RpmSet").setValue(Integer.parseInt(rpms));
                    myRef.child("AnguloSet").setValue(Integer.parseInt(angulos));

                    Toast.makeText(getApplicationContext(), "Valores Actualizados", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Los valores deben ser numericos y son obligatorios", Toast.LENGTH_LONG).show();
                }

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String rpm_actual = dataSnapshot.child("RpmAct").getValue().toString();
                String angulo_actual = dataSnapshot.child("AnguloAct").getValue().toString();

                String rpm_set = dataSnapshot.child("RpmSet").getValue().toString();
                String angulo_set = dataSnapshot.child("AnguloSet").getValue().toString();

                Log.d("DEBUG", rpm_actual);
                RpmActual.setText("RPM "+rpm_actual);
                AnguloAct.setText("Angulo "+angulo_actual+ " °");

                RpmSet.setText(rpm_set);
                AnguloSet.setText(angulo_set);

            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });
    }
}