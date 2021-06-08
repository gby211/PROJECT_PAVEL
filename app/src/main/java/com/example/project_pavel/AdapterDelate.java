package com.example.project_pavel;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterDelate extends RecyclerView.Adapter<AdapterDelate.MyViewClass2>{

    public ArrayList<DelateCls> del_cl;

    private DatabaseReference mDataBase;
    private String USER = "user";


    public AdapterDelate(ArrayList<DelateCls> del_cl){this.del_cl = del_cl;}

    @NonNull
    @Override
    public MyViewClass2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delate_adap,parent, false);
        AdapterDelate.MyViewClass2 myViewClass2 = new AdapterDelate.MyViewClass2(view);
        return myViewClass2;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewClass2 holder, int position) {
        DelateCls item = del_cl.get(position);
        holder.emailT.setText(item.getEmail());
        holder.nameT.setText(item.getName());
        mDataBase = FirebaseDatabase.getInstance().getReference(USER);

        holder.delBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mDataBase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {

                            User user1 = ds.getValue(User.class);
                            assert user1 != null;
                            if(item.getEmail().equals(user1.getEmail())){
                                String keyDelete = ds.getKey();
                                mDataBase.child(keyDelete).removeValue();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });
    }

    @Override
    public int getItemCount() {
        return del_cl.size();
    }


    public class MyViewClass2 extends RecyclerView.ViewHolder {
        TextView emailT;
        TextView nameT;
        Button delBtn;

        public MyViewClass2(@NonNull View itemView) {
            super(itemView);
            emailT = (TextView) itemView.findViewById(R.id.email_akk);
            nameT = (TextView) itemView.findViewById(R.id.name_akk);
            delBtn = (Button) itemView.findViewById(R.id.delete_btn);
        }
    }


}
