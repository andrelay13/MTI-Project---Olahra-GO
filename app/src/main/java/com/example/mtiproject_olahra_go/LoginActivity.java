package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtPassword, txtUsername;
    Button btnLogin;
    TextView tvRegister;
    UserDB userDb;
    Context context;
    public static final String SEND_LOGIN = "com.example.application.OlahraGO.SEND_LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        userDb = new UserDB(this);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkUsername() && checkPassword()){
                    String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    Integer id = userDb.getId(username);

                    if(userDb.checkUsers(username, password)){
                        Intent intent = new Intent(LoginActivity.this, NavigationBar.class);
                        SaveId(SEND_LOGIN, id);
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                        startActivity(intent);

                    }else{
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();
                        txtUsername.setError("Username is wrong!");
                        txtPassword.setError("Password does not match!");
                    }
                }

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean checkUsername(){
        if(txtUsername.getText().toString().isEmpty()) {
            txtUsername.setError("Usenrame cannot be empty");
            return false;
        }

        return true;
    }

    public Boolean checkPassword(){
        if(txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Password cannot be empty");
            return false;
        }

        return true;
    }

    public void SaveId(String key, int id){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, id);
        editor.commit();
    }

}