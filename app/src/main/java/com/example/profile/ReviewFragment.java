package com.example.profile;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewFragment extends Fragment {
    RecyclerView rv;
    RatingBar ratingBar;



    public ReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_review, container, false);
       
        initializeReviewRecyclerView(rootView);
        return rootView;
    }

    private void initializeReviewRecyclerView(View rootView) {
        rv = rootView.findViewById(R.id.recyclerview_id_review);
        ratingBar=rootView.findViewById(R.id.ratingBar);

        ArrayList<ReviewitemModel> reviewList = new ArrayList<>();
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 0, "israa nour", "hello i like it ,it's very butifull hello i like it ,it's very butifull hello i like it ,it's very butifull hello i like it ,it's very butifull hello i like it ,it's very butifull hello i like it ,it's very butifullhello i like it ,it's very butifull", "3:04 Am"));
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 4, "Azza", "hello i like it ,it's very butifull", "3:04 Am"));
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 3, "Huda", "hello i like it ,it's very butifull", "3:04 Am"));
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 5, "salma", "hello i like it ,it's very butifull", "3:04 Am"));
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 3, "Aya", "hello i like it ,it's very butifull", "3:04 Am"));
        reviewList.add(new ReviewitemModel(R.drawable.profile_bg, 35, R.drawable.ic_favorite_black_24dp, 0, "israa nour", "hello i like it ,it's very butifull", "3:04 Am"));
        ReviewRecycAdapter adapter = new ReviewRecycAdapter(reviewList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        DividerItemDecoration dv;
        dv = new DividerItemDecoration(rv.getContext(), ((LinearLayoutManager) lm).getOrientation());
        rv.addItemDecoration(dv);

//=======================================================================================//

        //========================================================================================//


        
    }

}
