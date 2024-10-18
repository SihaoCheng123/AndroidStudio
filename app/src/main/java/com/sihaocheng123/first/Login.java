package com.sihaocheng123.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        TextView loginRegisterText = findViewById(R.id.loginRegisterText);
        TextInputLayout loginUsernameTIL = findViewById(R.id.loginUsernameTIL);
        TextInputLayout loginPasswordTIL = findViewById(R.id.loginPasswordTIL);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loginUsername = String.valueOf(loginUsernameTIL.getEditText().getText());
                String loginPassword = String.valueOf(loginPasswordTIL.getEditText().getText());
                SharedPreferences sharedPreferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                String name= sharedPreferences.getString("userName","anonimo");
                String password= sharedPreferences.getString("userPassword","contraseña");
                if(name.equals(loginUsername)){
                    if (loginPassword.equals(password)){
                        launchMain();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Este usuario no existe", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        loginRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });
    }

    public void launchMain(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void launchRegister(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}