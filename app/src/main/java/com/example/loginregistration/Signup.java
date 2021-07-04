package com.example.loginregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class Signup extends AppCompatActivity {
    EditText ename, email, phone, epass, cpass;
    RadioGroup Gender;
    RadioButton radioButton;
    Button Register;
    DBHelper DB;
    //private String genderStr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Signup_Form");

        ename = findViewById(R.id.name);
        email = findViewById(R.id.mail);
        phone = findViewById(R.id.number);
        epass = findViewById(R.id.password);
        cpass = findViewById(R.id.confirm_password);
        Gender = findViewById(R.id.gender);
        Register = findViewById(R.id.register);
        DB = new DBHelper(this);
        //Gender.setOnCheckedChangeListener((group, checkedId) -> {
          //  if (checkedId == R.id.male) {
                //genderStr = "Male";
           // } else if (checkedId == R.id.Female) {
               // genderStr = "Female";
            //}
        //});

        Register.setOnClickListener(v -> {
            int id=Gender.getCheckedRadioButtonId();
            radioButton=findViewById(id);
            if (ename.getText().toString().isEmpty()) {
                Toast.makeText(Signup.this, "Please enter the name!", Toast.LENGTH_SHORT).show();
            } else if (email.getText().toString().isEmpty()) {
                Toast.makeText(Signup.this, "Please enter the email!", Toast.LENGTH_SHORT).show();
            } else if (!validEmail(email.getText().toString().trim())) {
                Toast.makeText(Signup.this, "Please enter the valid email!", Toast.LENGTH_SHORT).show();
            } else if (phone.getText().toString().isEmpty()) {
                Toast.makeText(Signup.this, "Please enter the phone number!", Toast.LENGTH_SHORT).show();
            } else if (!validPhoneNo(phone.getText().toString().trim())) {
                Toast.makeText(Signup.this, "Please enter the valid number!", Toast.LENGTH_SHORT).show();
            } else if (epass.getText().toString().isEmpty()) {
                Toast.makeText(Signup.this, "Please enter the password!", Toast.LENGTH_SHORT).show();
            } else if (cpass.getText().toString().isEmpty()) {
                Toast.makeText(Signup.this, "Please enter the confirm password!", Toast.LENGTH_SHORT).show();
            } else if (!cpass.getText().toString().matches(epass.getText().toString())) {
                Toast.makeText(Signup.this, "Please enter the valid confirm password!", Toast.LENGTH_SHORT).show();
            } else if(radioButton.getText().toString().isEmpty()){
                Toast.makeText(Signup.this, "please select gender!", Toast.LENGTH_SHORT).show();
            } else {
                boolean var = DB.register_user(ename.getText().toString(), email.getText().toString(), cpass.getText().toString(), phone.getText().toString(), radioButton.getText().toString());
                if (var) {
                    Toast.makeText(Signup.this, "User register successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup.this, "Unable to register user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean validPhoneNo(String phoneNo) {
        String regex = "[7-9][0-9]{9}";
        return phoneNo.matches(regex);
    }


}