package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {
    Button btn_uno, btn_dos, btn_tres, btn_salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btn_uno = (Button)findViewById(R.id.btn_uno);
        btn_dos = (Button)findViewById(R.id.btn_dos);
        btn_tres = (Button)findViewById(R.id.btn_tres);
        btn_salir = (Button)findViewById(R.id.btn_salir);

        btn_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, UnoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, DosActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this, TresActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}