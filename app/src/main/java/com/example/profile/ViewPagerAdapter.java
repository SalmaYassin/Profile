package com.example.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    //    private  final List<Fragment> firstFragment = new ArrayList();
//    private  final List<String> firstTitles = new ArrayList();
    String catID;


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            CategoryFragment categoryFragment = new CategoryFragment();
            Bundle arguments = new Bundle();
            arguments.putString(Constants.PRODUCT_KEY, catID);
            categoryFragment.setArguments(arguments);
            return categoryFragment;/*todo pass catID */
        } else if (position == 1) {
            return new ShopFragment(); /*todo pass catID */
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
                return "200" + "\nProducts";
            case 1:
                return "20" + "\nShops";
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
