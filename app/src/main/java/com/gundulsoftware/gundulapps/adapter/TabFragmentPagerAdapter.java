package com.gundulsoftware.gundulapps.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gundulsoftware.gundulapps.fragment.AturWaktuKuisFragment;
import com.gundulsoftware.gundulapps.fragment.KuisSekarangFragment;

/**
 * Created by Ardika Bagus on 19-May-16.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    String [] title = new String[]{
            "Kuis Sekarang", "Atur Waktu Kuis"
    };

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new KuisSekarangFragment();
                break;
            case 1:
                fragment = new AturWaktuKuisFragment();
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
