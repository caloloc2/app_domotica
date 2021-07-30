package com.nibemi.domotica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    TextView txt_username, txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = (TextView)findViewById(R.id.txt_username);
        txt_password = (TextView)findViewById(R.id.txt_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                if ((!user.isEmpty()) && (!password.isEmpty())){
                    if ((user.equals("admin")) && (password.equals("admin"))){
                        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Las credenciales con incorrectas.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Debe ingresar los dos campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}