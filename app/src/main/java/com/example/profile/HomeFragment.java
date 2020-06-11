package com.example.profile;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<SliderItemModel> datasider;
    private DatabaseReference dbReferenceslider;
    private ViewPager2 viewPager2;
    private SliderAdapter sliderAdapter;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewadapter;

    public HomeFragment() {
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = rootView.findViewById(R.id.tablayout);
        viewPager = rootView.findViewById(R.id.viewpager);
        viewadapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewadapter);
        tabLayout.setupWithViewPager(viewPager);
        viewadapter.setCatID("categoryID1");


        viewPager2 = rootView.findViewById(R.id.viewPagerSlider);
        showSlider(rootView);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
         viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
        //viewPager2.setCurrentItem(1 );
        if ( position == 0 ){

        }else if ( position == 1 ){

           }if ( position == 2 ){

        }if ( position == 3 ){

        }

        Log.d("PAGE_SELECTED", "onPageSelected: "+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
    }
});


         CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
            float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
            @Override
            public void transformPage(@NonNull View page, float position) {
                String TAG = "VIEWPAGER_POS";
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (position < -1) {
                    page.setTranslationX(-myOffset);
                } else if (position <= 1) {
                    float scaleFactor = Math.max(0.7f, 1 - Math.abs(position - 0.14285715f));
                    Log.d(TAG, "transformPageVALUE: " + scaleFactor);
                    page.setTranslationX(myOffset);
                    page.setScaleY(scaleFactor);
                    page.setAlpha(scaleFactor);
                } else {
                    page.setAlpha(0);
                    page.setTranslationX(myOffset);
                }

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);


//        RVAdapterCategory.ImageClickListener listenerImage = new RVAdapterCategory.ImageClickListener() {
//            @Override
//           public void onItemClick(int item) {
//
//                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
//            }
//        };

        RVAdapterCategory.ItemClickListener listener = new RVAdapterCategory.ItemClickListener() {
            @Override
            public void onItemClick(ItemModel item) {

                Toast.makeText(getActivity(), "item Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterRecentlyView.ImageClickListener listenerRecView = new RVAdapterRecentlyView.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterRecentFavorite.ImageClickListener listenerRecFav = new RVAdapterRecentFavorite.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };

        RVAdapterOtherCategory.ImageClickListener listenerotherCateg = new RVAdapterOtherCategory.ImageClickListener() {
            @Override
            public void onItemClick(int item) {

                Toast.makeText(getActivity(), "image Clicked", Toast.LENGTH_SHORT).show();
            }
        };


//        RVAdapterCategory adapterImages = new RVAdapterCategory( getListofImages(),listenerImage );
//        RecyclerView recyclerViewImages = (RecyclerView)rootView.findViewById(R.id.rv_images);
//        recyclerViewImages.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false) );
//        recyclerViewImages.setAdapter(adapterImages);
//

        RVAdapterCategory adapter = new RVAdapterCategory(getList(), listener);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.similarItem_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);


        RVAdapterRecentlyView adapterRecView = new RVAdapterRecentlyView(getListofRecentlyView(), listenerRecView);
        RecyclerView recyclerViewRecView = (RecyclerView) rootView.findViewById(R.id.recently_view_recyclerView);
        recyclerViewRecView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewRecView.setAdapter(adapterRecView);

        RVAdapterRecentFavorite adapterRecFav = new RVAdapterRecentFavorite(getListofRecentlyFav(), listenerRecFav);
        RecyclerView recyclerViewRecFav = (RecyclerView) rootView.findViewById(R.id.recently_fav_recyclerView);
        recyclerViewRecFav.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewRecFav.setAdapter(adapterRecFav);

//        RVAdapterOtherCategory adapterOtherCategory = new RVAdapterOtherCategory( getListofOtherCateg(),listenerotherCateg );
//        RecyclerView recyclerViewOtherCateg= (RecyclerView)rootView.findViewById(R.id.rv_other_categ);
//        recyclerViewOtherCateg.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false) );
//        recyclerViewOtherCateg.setAdapter(adapterOtherCategory);

        return rootView;
    }

    private void showSlider(View rootView) {

        datasider = new ArrayList<>();
        dbReferenceslider = FirebaseDatabase.getInstance().getReference("categories");
        dbReferenceslider.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    SliderItemModel item = snapshot.getValue(SliderItemModel.class);
                    datasider.add(item);
                }
                 sliderAdapter = new SliderAdapter(datasider, viewPager2);
                viewPager2.setAdapter(sliderAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        DatabaseReference dbReferences = FirebaseDatabase.getInstance().getReference("categories");
        dbReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0 ;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    viewadapter.setCatID(snapshot.getKey());
                    Log.d("FIREBASE", "ID OF POS: "+ i + snapshot.getKey());
                    i++;
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });





    }


    private ArrayList<ItemModel> getList() {
        final ArrayList<ItemModel> item = new ArrayList<ItemModel>();
        item.add(new ItemModel("Item Full Name", "$150", R.drawable.grid_one));
        item.add(new ItemModel("Item Full Name", "$150", R.drawable.grid_two));
        item.add(new ItemModel("Item Full Name", "$150", R.drawable.grid_three));
        item.add(new ItemModel("Item Full Name", "$150", R.drawable.grid_four));
        return item;
    }

    private ArrayList<Integer> getListofImages() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.ring_one);
        image.add(R.drawable.ring_two);
        image.add(R.drawable.ring_three);
        image.add(R.drawable.ring_four);
        return image;
    }

    private ArrayList<Integer> getListofOtherCateg() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_three);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_one);
        return image;
    }

    private ArrayList<Integer> getListofRecentlyView() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_two);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_four);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_one);
        image.add(R.drawable.test_image);

        return image;
    }

    private ArrayList<Integer> getListofRecentlyFav() {
        final ArrayList<Integer> image = new ArrayList<Integer>();
        image.add(R.drawable.grid_two);
        image.add(R.drawable.grid_three);
        image.add(R.drawable.grid_four);
        image.add(R.drawable.test_image);
        image.add(R.drawable.grid_one);
        image.add(R.drawable.test_image);


        return image;
    }

}
