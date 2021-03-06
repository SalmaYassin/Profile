package com.example.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
    Bundle bundle1 ;
    MainViewModel mainViewModel;




    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shop , container, false);
        showShops(rootView);
        return rootView;
    }

    private void showShops(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_id_shop);
        data = new ArrayList<>();
        bundle1 = getArguments();
        String idshop = bundle1.getString(Constants.CATEGORY_KEY);
        Log.d("ID_SHOP", "CAT_ID: "+idshop);
        initViewModel();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        getShopByCategory(idshop);
            }

    private void getShopByCategory(String catId) {
        dbReference = FirebaseDatabase.getInstance().getReference("shops");
        Query query = FirebaseDatabase.getInstance().getReference("shops")
                .orderByChild("categoryid").equalTo(catId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();

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
    void initViewModel() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mainViewModel.catId.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String newCatID) {
                getShopByCategory(newCatID);
            }
        });
    }


}
