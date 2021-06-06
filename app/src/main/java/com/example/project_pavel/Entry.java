package com.example.project_pavel;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
    }


    public void StartStart(View view) {
        eem = findViewById(R.id.email_lbl_entry);
        email = eem.getText().toString();
        epas = findViewById(R.id.editTextTextPassword_entry);
        password = epas.getText().toString().trim();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER);

        ValueEventListener vListener = new ValueEventListener() {
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
                                Log.d("ff",user1.getfCom());
                                intent.putExtra("admin",user1.getAdmin().toString());
                                Log.d("fff",user1.getAdmin().toString());
                                startActivity(intent);
                            } else {
                                setContentView(R.layout.activity_fail);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
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
}