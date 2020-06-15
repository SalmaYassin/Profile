package com.example.profile;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ProductitemModel> data;
    private DatabaseReference dbReference;
    Bundle bundle;
    MainViewModel mainViewModel;

    Map<String, String> productsMap;


    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        showCategories(rootView);
        return rootView;
    }

    private void showCategories(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_id);
        data = new ArrayList<>();
        bundle = this.getArguments();
        String id = bundle.getString(Constants.CATEGORY_KEY);
        Log.d("ID", "CAT_ID: " + id);
        initViewModel();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getProductsByCategoryId(id);
    }

    void getProductsByCategoryId(String catID) {
        dbReference = FirebaseDatabase.getInstance().getReference("products");
        Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("categoryid").equalTo(catID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ProductitemModel categ = snapshot.getValue(ProductitemModel.class);
                    data.add(categ);

                }
                ProductRecycAdapter Adapter = new ProductRecycAdapter( getContext() ,data , ListenerProducts , ListenerFavourite);
                recyclerView.setAdapter(Adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    void initViewModel() {
        mainViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
        mainViewModel.catId.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String newCatID) {
                getProductsByCategoryId(newCatID);
            }
        });
    }

    ProductRecycAdapter.ItemClickListener ListenerProducts = new ProductRecycAdapter.ItemClickListener() {
        @Override
        public void onItemClick(ProductitemModel item) {
            Toast.makeText(getActivity(), "item Clicked", Toast.LENGTH_SHORT).show();
            dbReference = FirebaseDatabase.getInstance().getReference("RecentViewed");
            String key = dbReference.push().getKey();
            dbReference.child(key).setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
//                    Toast.makeText(getActivity() , "Product Added Succcesfully.." , Toast.LENGTH_LONG).show();
                }
            });



        }
    };

    ProductRecycAdapter.FavouriteClickListener ListenerFavourite = new ProductRecycAdapter.FavouriteClickListener() {
        @Override
        public void onFavouriteClicked(final int position, final boolean isFav) {
            dbReference = FirebaseDatabase.getInstance().getReference("products");
            dbReference.child(data.get(position).getKey()).child("favourite").setValue(isFav)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getActivity() , "Product favourite Succcesfully.." , Toast.LENGTH_LONG).show();

                }
            });


//                    productsMap = (Map<String, String>) dataSnapshot.getValue();
//                    Log.d("MAP", "KEY: " + productsMap.keySet().toArray()[position].toString());
//                    dbReference.child(productsMap.keySet().toArray()[position].toString()).child("favourite")
//                            .setValue(isFav);

        }
    };
}
