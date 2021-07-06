package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UnoActivity extends AppCompatActivity {
    Button btn_regresar_uno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uno);

        btn_regresar_uno = (Button)findViewById(R.id.btn_regresar_uno);

        btn_regresar_uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnoActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}