package com.example.mtiproject_olahra_go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView toLogin;
    EditText txtUsername, txtPhone, txtEmail, txtPassword, txtConfirmationPass;
    Button btnRegist;
    UserDB userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = findViewById(R.id.txtUsername);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmationPass = findViewById(R.id.txtConfirmPassword);
        btnRegist = findViewById(R.id.btnRegist);
        toLogin  = findViewById(R.id.toLogin);
        userDB = new UserDB(this);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkusername() && checkPhone() && checkPhone() && checkconfirmationpass()){
                    User user  = new User();
                    user.setUserName(txtUsername.getText().toString());
                    user.setUserPhone(txtPhone.getText().toString());
                    user.setUserEmail(txtEmail.getText().toString());
                    user.setUserPassword(txtPassword.getText().toString());
                    userDB.insertUser(user);

                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private Boolean checkusername(){
        String usertext = txtUsername.getText().toString();
        int usernamelength = txtUsername.getText().toString().length();
        if(usernamelength >=6 && usernamelength<=12 ){
            return true;
        }
        else if(usertext.isEmpty()){
            txtUsername.setError("Username cannot be empty");
            return false;
        }
        else{
            txtUsername.setError("Username must be between 6 and 12 characters");
            return false;
        }
    }

    private Boolean checkpassword(){
        String passtext = txtPassword.getText().toString();
        int passLength = txtPassword.getText().toString().length();
        boolean checknumeric = false , checkalpha = false;
        char[] passtextarray = new char[passLength];
        for(int i=0 ; i<passLength ; i++){
            passtextarray[i] = passtext.charAt(i);
        }

        if(passLength > 8){
            for (char cekchar:passtextarray) {
                if(checknumeric && checkalpha) {
                    break;
                }
                else{
                    if (cekchar >= '0' && cekchar <= '9') {
                        checknumeric = true;
                    }
                    if (cekchar >= 'A' && cekchar <= 'Z' || cekchar >= 'a' && cekchar <= 'z') {
                        checkalpha = true;
                    }
                }
            }
        }
        if(checknumeric && checkalpha){
            return true;
        }
        else if(passtext.isEmpty()){
            txtPassword.setError("Password cannot be empty");
            return false;
        }
        else if (passtext.length()<8){
            txtPassword.setError("Password must be more than 8 characters");
            return false;
        }
        else if(!checknumeric && !checkalpha){
            txtPassword.setError("Password must be alphanumeric");
            return false;
        }

        else{
            return false;
        }
    }

    private boolean checkconfirmationpass(){
        String passtext = txtPassword.getText().toString();
        String confirmpasstext = txtConfirmationPass.getText().toString();
        if(passtext.equals(confirmpasstext)){
            return true;
        }
        else if(confirmpasstext.isEmpty()){
            txtConfirmationPass.setError("Confirmation Password cannot be empty");
            return false;
        }
        else {
            txtConfirmationPass.setError("Confirmation Password must be the same with Password");
            return false;
        }
    }

    private boolean checkPhone()
    {
        String PhoneNumber = txtPhone.getText().toString();
        int PhoneNumberlength = txtPhone.getText().toString().length();
        boolean phonenum = false, phonedigit =false;
        char[] PhoneNumberArray = new char[PhoneNumberlength];
        for(int i=0 ; i<PhoneNumberlength ; i++){
            PhoneNumberArray[i] = PhoneNumber.charAt(i);
        }

        if(PhoneNumberlength >=10 && PhoneNumberlength <=12){
            phonedigit = true;
            for (char cekphone:PhoneNumberArray) {
                if(phonenum) {
                    break;
                }
                else{
                    if (cekphone >= '0' && cekphone <= '9') {
                        phonenum = true;
                    }
                }
            }
        }
        if(phonenum){
            return true;
        }
        else if(PhoneNumber.isEmpty()){
            txtPhone.setError("Phone Number cannot be emtpy");
            return false;
        }
        else if(!phonenum){
            txtPhone.setError("Phone Number must contain only numbers");
            return false;
        }
        else if(PhoneNumberlength<10 && PhoneNumberlength>12){
            txtPhone.setError("Phone Number must be between 10 to 12 digits");
            return false;
        }
        else {
            return false;
        }
    }
}