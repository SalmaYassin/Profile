package com.example.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<CategoryitemModel> data;
    private DatabaseReference dbReference;


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category , container, false);
        showCategories(rootView) ;
        //initializeFavItemRecyclerView(rootView);
        return rootView ;
    }

    private void showCategories(View rootView) {
        recyclerView = rootView.findViewById(R.id.recyclerview_id);
         data = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dbReference = FirebaseDatabase.getInstance().getReference("products");
        Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("categoryid").equalTo("categoryID1");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CategoryitemModel categ = snapshot.getValue(CategoryitemModel.class);
                    data.add(categ);
                }
                CategoryRecycAdapter Adapter = new CategoryRecycAdapter(data);
                recyclerView.setAdapter(Adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity() , databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

//    private void initializeFavItemRecyclerView(View v) {
//        recyclerView = v.findViewById(R.id.recyclerview_id);
//        ArrayList<CategoryitemModel> data = getList();
//        CategoryRecycAdapter Adapter = new CategoryRecycAdapter(data);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
//        recyclerView.setAdapter(Adapter);
//    }
//
//
//    private ArrayList<CategoryitemModel> getList() {
//        ArrayList<CategoryitemModel> data = new ArrayList<CategoryitemModel>();
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo1, R.drawable.fav_icon));
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo2, R.drawable.fav_icon));
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo5, R.drawable.fav_icon));
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo4, R.drawable.fav_icon));
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo3, R.drawable.fav_icon));
//        data.add(new CategoryitemModel("Full Item Name", "150$", R.drawable.photo1, R.drawable.fav_icon));
//        return data;
//    }

}
