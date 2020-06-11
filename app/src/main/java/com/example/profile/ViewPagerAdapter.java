package com.example.profile;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //    private  final List<Fragment> firstFragment = new ArrayList();
//    private  final List<String> firstTitles = new ArrayList();
    String catID;



    public ViewPagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            ProductsFragment productsFragment = new ProductsFragment();
            Bundle arguments0 = new Bundle();
            arguments0.putString(Constants.CATEGORY_KEY, catID);
            Log.d("ARGUMENTS", "CAT_ID: "+ catID);

            productsFragment.setArguments(arguments0);
            return productsFragment;/*todo pass catID */
        } else if (position == 1) {
            ShopFragment shopFragment = new ShopFragment();
            Bundle arguments1 = new Bundle();
            arguments1.putString(Constants.CATEGORY_KEY, catID);
            shopFragment.setArguments(arguments1);
            return shopFragment; /*todo pass catID */
        } else if (position == 2) {
            return new ReviewFragment(); /*todo pass catID */
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Products";
            case 1:
                return "Shops";
            case 2:
                return "Reviews";
        }
        return null;
    }

//    public void AddFragment(Fragment fragment , String titles){
//        firstFragment.add(fragment);
//        firstTitles.add(titles);
//
//    }
}
