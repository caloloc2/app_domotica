package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TresActivity extends AppCompatActivity {
    Button btn_regresar_tres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres);

        btn_regresar_tres = (Button)findViewById(R.id.btn_regresar_tres);

        btn_regresar_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TresActivity.this, PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}