package com.example.profile;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.badge.BadgeDrawable;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

//    private  final List<Fragment> firstFragment = new ArrayList();
//    private  final List<String> firstTitles = new ArrayList();



    public ViewPagerAdapter(FragmentManager fm) {
     super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        if ( position == 0 ){
            return new CategoryFragment();
        } else if (position==1){
            return new ShopFragment() ;
        }else if (position == 2){
            return new ReviewFragment() ;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
            return "200" + "\nProducts" ;
            case  1 :
            return "20" + "\nShops" ;
            case  2 :
                return "Reviews" ;
        }
        return  null;
    }

//    public void AddFragment(Fragment fragment , String titles){
//        firstFragment.add(fragment);
//        firstTitles.add(titles);
//
//    }
}
