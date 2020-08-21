package com.sagar.watchnext.activities.search.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SAGAR MAHOBIA on 11-Jul-18. at 17:37
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<FragmentHolder> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).fragment;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).title;
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(new FragmentHolder(fragment, title));
    }


    private class FragmentHolder {
        private Fragment fragment;
        private String title;

        private FragmentHolder(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }
    }
}
