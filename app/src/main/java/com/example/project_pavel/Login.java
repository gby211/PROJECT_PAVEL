package com.example.project_pavel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private String name,password,email;
    private DatabaseReference mDataBase;
    private String USER = "user";

    private EditText eName,epas,eem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void StartLogIn(View view){
        eName =findViewById(R.id.name_lbl);
        name = eName.getText().toString();
        epas = findViewById(R.id.password_lbl);
        password = epas.getText().toString();
        eem = findViewById(R.id.email_text_lbl);
        email = eem.getText().toString();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER);

        String id = mDataBase.getKey();
        User user_now = new User(name,email,password,0,"",id);
        mDataBase.push().setValue(user_now);

        Intent intent = new Intent(Login.this, Entry.class);
        startActivity(intent);
    }



}