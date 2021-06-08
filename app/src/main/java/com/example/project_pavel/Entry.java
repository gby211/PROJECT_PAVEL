package com.example.project_pavel;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Entry extends AppCompatActivity {


    public static String USER_NAME;
    public static String IDUSER;

    public String email;
    private String password;
    private DatabaseReference mDataBase;
    private String USER = "user";
    private EditText eName, epas, eem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        if (!isOnline(this)) {
            setContentView(R.layout.activity_fail);
        }
    }


    public void StartStart(View view) {
        eem = findViewById(R.id.email_lbl_entry);
        email = eem.getText().toString();
        epas = findViewById(R.id.editTextTextPassword_entry);
        password = epas.getText().toString().trim();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER);

        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    if (email.equals(user1.getEmail())) {
                        if (password.equals(user1.getPassword())) {
                            if (isOnline(Entry.this)) {
                                USER_NAME = user1.getName();
                                IDUSER = ds.getKey();

                                Intent intent = new Intent(Entry.this, MainActivity.class);
                                intent.putExtra("user_name", user1.getName());
                                intent.putExtra("fCom", user1.getfCom());
                                intent.putExtra("admin",user1.getAdmin().toString());
                                Log.d("hh","gg");
                                startActivity(intent);

                                email = null;
                                password = null;
                                finish();


                            }
                        }
                        else {
                            Toast tost = Toast.makeText(Entry.this,"incorrect password",Toast.LENGTH_LONG);
                            tost.setGravity(Gravity.CENTER, 0, 0);
                            tost.show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        ValueEventListener vListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    User user1 = ds.getValue(User.class);
//                    assert user1 != null;
//                    if (email.equals(user1.getEmail())) {
//                        if (password.equals(user1.getPassword())) {
//                            if (isOnline(Entry.this)) {
//                                USER_NAME = user1.getName();
//                                IDUSER = ds.getKey();
//
//                                Intent intent = new Intent(Entry.this, MainActivity.class);
//                                intent.putExtra("user_name", user1.getName());
//                                intent.putExtra("fCom", user1.getfCom());
//                                intent.putExtra("admin",user1.getAdmin().toString());
//                                startActivity(intent);
//
//
//                                finish();
//
//
//                            }
//                        }
//                        else {
//                            Toast tost = Toast.makeText(Entry.this,"incorrect password",Toast.LENGTH_LONG);
//                            tost.setGravity(Gravity.CENTER, 0, 0);
//                            tost.show();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mDataBase.addValueEventListener(vListener);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    public void goToGo(View view){
        Intent intent = new Intent(Entry.this, Login.class);
            startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hh","ggssssss");
    }
}