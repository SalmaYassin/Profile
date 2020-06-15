package com.example.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewRecycAdapter extends RecyclerView.Adapter<ReviewRecycAdapter.ReviewViewHolder>{

    ArrayList<ReviewitemModel> reviewList;
    RatingBar ratingBar;

    public ReviewRecycAdapter(ArrayList<ReviewitemModel> chatList) {
        this.reviewList = chatList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review , null, false);

        return new ReviewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        ReviewitemModel ReviewitemModel = reviewList.get(position);

        holder.customer_name.setText(ReviewitemModel.getUser_name());
        holder.comment.setText(ReviewitemModel.getComment());
        holder.CImage.setImageResource(ReviewitemModel.getProfile_image());
//        holder.num_of_like.setText(ReviewitemModel.getNum_of_like());

        holder.time_of_comment.setText(ReviewitemModel.getComment_time());
        //=======================================================//
        //   holder.rate.setRating(ReviewitemModel.getRate());
        holder.rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float ratingValue=ratingBar.getRating();
            }
        });


        //=================================================================//
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    //=======================================================
    @Override
    public int getItemCount() {

        return reviewList.size();
    }


    //************************************************************************//
    class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView comment, num_of_like, customer_name,time_of_comment;
        Button like;
        CircleImageView CImage;
        RatingBar rate;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name = itemView.findViewById(R.id.tv_customer_name);
            comment = itemView.findViewById(R.id.tv_comment);
            time_of_comment = itemView.findViewById(R.id.tv_date);
            num_of_like = itemView.findViewById(R.id.num_of_like);
            rate=itemView.findViewById(R.id.ratingBar);

            like=itemView.findViewById(R.id.like_button);
            CImage = itemView.findViewById(R.id.circimage);
        }
        //********************************************************************//
    }
}
