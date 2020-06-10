package com.example.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ShopFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ShopitemModel> data;
    private DatabaseReference dbReference;



    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shop , container, false);
        //initializeFavShopRecyclerView(rootView);
        showShops(rootView);
        return rootView;
    }

    private void showShops(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_id_shop);
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        dbReference = FirebaseDatabase.getInstance().getReference("shops");
        Query query = FirebaseDatabase.getInstance().getReference("shops")
                .orderByChild("categoryid").equalTo("categoryID1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ShopitemModel shops = snapshot.getValue(ShopitemModel.class);
                    data.add(shops);
                }

                ShopRecycAdaptar adapter = new ShopRecycAdaptar(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void initializeFavShopRecyclerView(View v) {

//        recyclerView = v.findViewById(R.id.recyclerview_id_shop);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        ArrayList<ShopitemModel> data = getList();
//        ShopRecycAdaptar Adapter = new ShopRecycAdaptar(data);
//        recyclerView.setAdapter(Adapter);
    }

//    private ArrayList<ShopitemModel> getList(){
//        ArrayList<ShopitemModel> data = new ArrayList<ShopitemModel>();
//        data.add(new ShopitemModel(R.drawable.photo1,R.drawable.icon3,"Shop Name","Cairo Egypt"));
//        data.add(new ShopitemModel(R.drawable.photo4,R.drawable.icon4,"Shop Name","Cairo Egypt"));
//        return data ;
//    }



}
