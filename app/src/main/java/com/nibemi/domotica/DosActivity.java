package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DosActivity extends AppCompatActivity {
    Button btn_regresar_dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);

        btn_regresar_dos = (Button)findViewById(R.id.btn_regresar_dos);

        btn_regresar_dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DosActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}