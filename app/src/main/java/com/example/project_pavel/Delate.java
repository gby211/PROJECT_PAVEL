package com.example.project_pavel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Delate extends AppCompatActivity {


    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDelate adapter2;

    private DatabaseReference mDataBase;
    private String USER = "user";

    private ArrayList<DelateCls> resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delate);

        myRecyclerView = (RecyclerView) findViewById(R.id.res_del);


        mDataBase = FirebaseDatabase.getInstance().getReference(USER);
        resp = new ArrayList<DelateCls>();

//        mDataBase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                adapter2.notifyDataSetChanged();
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    User user1 = ds.getValue(User.class);
//                    assert user1 != null;
//                    resp.add(new DelateCls(user1.getName(),user1.getEmail()));
//
//                }
//                Log.d("delate",resp.toString());
//                adapter2.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        ValueEventListener vListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                resp.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user1 = ds.getValue(User.class);
                    assert user1 != null;
                    resp.add(new DelateCls(user1.getName(),user1.getEmail()));
                }

                Log.d("delate",resp.toString());
                adapter2.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };
        mDataBase.addValueEventListener(vListener);


        myRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        adapter2 = new AdapterDelate(resp);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter2);
    }
}