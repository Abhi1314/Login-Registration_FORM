package com.example.loginregistration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText pass,email;
DBHelper db;
Button update,delete,view,logbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pass=findViewById(R.id.pass);
        email=findViewById(R.id.Email);
        update=findViewById(R.id.updatebtn);
        delete=findViewById(R.id.deletebtn);
        view=findViewById(R.id.viewbtn);
        logbtn=findViewById(R.id.logoutbtn);
        db=new DBHelper(MainActivity.this);
        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String password=pass.getText().toString();
                boolean i=db.update(mail,password);
                if(i==true){
                    Toast.makeText(MainActivity.this,"data updated!",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Not succesfull",Toast.LENGTH_SHORT).show();

                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                boolean i=db.delete(mail);
                if(i==true){
                    Toast.makeText(MainActivity.this,"data deleted!",Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Not deleted",Toast.LENGTH_SHORT).show();

                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor t= db.getinfo();
                if(t.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else{
                    StringBuffer buffer=new StringBuffer();
                    while (t.moveToNext()){
                        buffer.append("NAME::"+t.getString(1)+"\n");
                        buffer.append("EMAIL::"+t.getString(2)+"\n");
                        buffer.append("PHONE_NUMBER::"+t.getString(3)+"\n");
                        buffer.append("GENDER::"+t.getString(5)+"\n");
                        buffer.append("PASSWORD::"+t.getString(4)+"\n\n\n");



                    }
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("DETAILS");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });



    }

}