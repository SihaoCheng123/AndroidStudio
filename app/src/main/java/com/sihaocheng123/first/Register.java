package com.sihaocheng123.first;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextInputLayout registerUsernameTIL = findViewById(R.id.registerUsernameTIL);
        TextInputLayout registerEmailTIL = findViewById(R.id.registerEmailTIL);
        TextInputLayout registerPasswordTIL = findViewById(R.id.registerPasswordTIL);
        TextInputLayout registerConfirmPasswordTIL = findViewById(R.id.registerConfirmPasswordTIL);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = String.valueOf(registerUsernameTIL.getEditText().getText());
                String userEmail = String.valueOf(registerEmailTIL.getEditText().getText());
                String userPassword = String.valueOf(registerPasswordTIL.getEditText().getText());
                String userConfirmPassword = String.valueOf(registerConfirmPasswordTIL.getEditText().getText());
                Pattern pattern = Patterns.EMAIL_ADDRESS;

                try {
                    if(username == null || userEmail == null || userPassword == null || userConfirmPassword == null){
                        Toast toast = Toast.makeText(getApplicationContext(), "No puede haber campos vacíos", Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        if(!userPassword.equals(userConfirmPassword)){
                            Toast toast = Toast.makeText(getApplicationContext(), "Tus contraseñas no coincidden", Toast.LENGTH_SHORT);
                            toast.show();
                        }else{
                            SharedPreferences preferences = getSharedPreferences("Usuario",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("userName", username);
                            editor.putString("userPassword", userPassword);
                            editor.putString("userEmail", userEmail);
                            editor.apply();
                            if (pattern.matcher(userEmail).matches()){
                                launchLogin();
                            }else{
                                Toast toast = Toast.makeText(getApplicationContext(), "Email no válido", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    }

                }catch (NullPointerException e){
                    System.out.println("Error " + e);
                }
            }
        });
    }
    public void launchLogin(){
        Intent intent = new Intent(Register.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}





