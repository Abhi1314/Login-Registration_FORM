package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
EditText email,password;
Button Login,register;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        getSupportActionBar().setTitle("Login_Form");
        email=findViewById(R.id.emaillogin);
        password=findViewById(R.id.loginpassword);
        Login=findViewById(R.id.login);

        register=findViewById(R.id.registration);
        DB=new DBHelper(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(Login_Activity.this, "Please enter the email!", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().isEmpty()){
                    Toast.makeText(Login_Activity.this, "Please enter the password!", Toast.LENGTH_SHORT).show();
                }else {
                    boolean var=DB.checkuser(email.getText().toString(),password.getText().toString());
                    if(var){

                        Toast.makeText(Login_Activity.this,"Login successfull",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(Login_Activity.this,"Login fail",Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Signup.class);
                startActivity(intent);
            }
        });
    }

}