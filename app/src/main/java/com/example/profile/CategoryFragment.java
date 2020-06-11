package com.example.profile;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<CategoryitemModel> data;
    private DatabaseReference dbReference;
    Bundle bundle ;


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
         bundle = this.getArguments();
         String id = bundle.getString(Constants.CATEGORY_KEY);
        Log.d("ID", "CAT_ID: "+ id);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dbReference = FirebaseDatabase.getInstance().getReference("products");
        Query query = FirebaseDatabase.getInstance().getReference("products")
                .orderByChild("categoryid").equalTo(id);
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


}
