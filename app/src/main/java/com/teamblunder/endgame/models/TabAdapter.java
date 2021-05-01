package com.teamblunder.endgame.models;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.teamblunder.endgame.fragments.KeywordFragment;
import com.teamblunder.endgame.fragments.SummaryFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    int tabs;

    public TabAdapter(Context context, FragmentManager manager, int totalTabs) {
        super(manager);
        this.tabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SummaryFragment();
            case 1:
                return new KeywordFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs;
    }
}