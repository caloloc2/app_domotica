package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TresActivity extends AppCompatActivity {
    Button btn_regresar_tres;
    CheckBox Luz1, Luz2, Luz3, Luz4, Rele1, Rele2, Buzzer;
    TextView Entrada1, Entrada2, Entrada3, Entrada4, Potenciometro;
    //Instancia a la base de datos
    FirebaseDatabase fdb = FirebaseDatabase.getInstance();
    //apuntamos al nodo que queremos leer
    DatabaseReference myRef = fdb.getReference("IO");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres);

        Luz1 = (CheckBox)findViewById(R.id.Luz1);
        Luz2 = (CheckBox)findViewById(R.id.Luz2);
        Luz3 = (CheckBox)findViewById(R.id.Luz3);
        Luz4 = (CheckBox)findViewById(R.id.Luz4);

        Rele1 = (CheckBox)findViewById(R.id.Rele1);
        Rele2 = (CheckBox)findViewById(R.id.Rele2);

        Entrada1 = (TextView) findViewById(R.id.Entrada1);
        Entrada2 = (TextView)findViewById(R.id.Entrada2);
        Entrada3 = (TextView)findViewById(R.id.Entrada3);
        Entrada4 = (TextView)findViewById(R.id.Entrada4);

        Buzzer = (CheckBox)findViewById(R.id.Buzzer);

        Potenciometro = (TextView) findViewById(R.id.Potenciometro);

        btn_regresar_tres = (Button)findViewById(R.id.btn_regresar_tres);

        btn_regresar_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TresActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Luz1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Luz1", 1);
                }else{
                    IOSetValue("Luz1", 0);
                }
            }
        });

        Luz2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Luz2", 1);
                }else{
                    IOSetValue("Luz2", 0);
                }
            }
        });

        Luz3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Luz3", 1);
                }else{
                    IOSetValue("Luz3", 0);
                }
            }
        });

        Luz4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Luz4", 1);
                }else{
                    IOSetValue("Luz4", 0);
                }
            }
        });

        Rele1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Rele1", 1);
                }else{
                    IOSetValue("Rele1", 0);
                }
            }
        });

        Rele2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Rele2", 1);
                }else{
                    IOSetValue("Rele2", 0);
                }
            }
        });

        Buzzer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    IOSetValue("Buzzer", 1);
                }else{
                    IOSetValue("Buzzer", 0);
                }
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String val_luz1 = dataSnapshot.child("Luz1").getValue().toString();
                String val_luz2 = dataSnapshot.child("Luz2").getValue().toString();
                String val_luz3 = dataSnapshot.child("Luz3").getValue().toString();
                String val_luz4 = dataSnapshot.child("Luz4").getValue().toString();

                if (val_luz1.equals("1")){
                    Luz1.setChecked(true);
                }else{
                    Luz1.setChecked(false);
                }

                if (val_luz2.equals("1")){
                    Luz2.setChecked(true);
                }else{
                    Luz2.setChecked(false);
                }

                if (val_luz3.equals("1")){
                    Luz3.setChecked(true);
                }else{
                    Luz3.setChecked(false);
                }

                if (val_luz4.equals("1")){
                    Luz4.setChecked(true);
                }else{
                    Luz4.setChecked(false);
                }

                String val_rele1 = dataSnapshot.child("Rele1").getValue().toString();
                String val_rele2 = dataSnapshot.child("Rele2").getValue().toString();

                if (val_rele1.equals("1")){
                    Rele1.setChecked(true);
                }else{
                    Rele1.setChecked(false);
                }

                if (val_rele2.equals("1")){
                    Rele2.setChecked(true);
                }else{
                    Rele2.setChecked(false);
                }

                String val_buzzer = dataSnapshot.child("Buzzer").getValue().toString();

                if (val_buzzer.equals("1")){
                    Buzzer.setChecked(true);
                }else{
                    Buzzer.setChecked(false);
                }

                String val_entrada1 = dataSnapshot.child("Entrada1").getValue().toString();
                String val_entrada2 = dataSnapshot.child("Entrada2").getValue().toString();
                String val_entrada3 = dataSnapshot.child("Entrada3").getValue().toString();
                String val_entrada4 = dataSnapshot.child("Entrada4").getValue().toString();

                if (val_entrada1.equals("1")){
                    Entrada1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.encendido));
                }else{
                    Entrada1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.apagado));
                }

                if (val_entrada2.equals("1")){
                    Entrada2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.encendido));
                }else{
                    Entrada2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.apagado));
                }

                if (val_entrada3.equals("1")){
                    Entrada3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.encendido));
                }else{
                    Entrada3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.apagado));
                }

                if (val_entrada4.equals("1")){
                    Entrada4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.encendido));
                }else{
                    Entrada4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.apagado));
                }

                String val_potenciometro = dataSnapshot.child("Potenciometro").getValue().toString();

                Potenciometro.setText(val_potenciometro);
            }
            @Override
            public void onCancelled(DatabaseError error){
                Log.e("ERROR FIREBASE",error.getMessage());
            }

        });
    }

    private void IOSetValue(String tag, int valor) {
        myRef.child(tag).setValue(valor);
    }
}