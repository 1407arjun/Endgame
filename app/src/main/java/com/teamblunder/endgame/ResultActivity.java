package com.teamblunder.endgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.teamblunder.endgame.fragments.SummaryFragment;
import com.teamblunder.endgame.models.TabAdapter;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ArrayList<String> resultList = new ArrayList<>();
    ArrayList<String> keywordsList = new ArrayList<>();
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        if (!text.equals("")) {
            resultList.clear();
            keywordsList.clear();
            //Do something with the text
            if (!result.equals("")) {
                SummaryFragment summaryFragment = new SummaryFragment();
                Bundle args = new Bundle();
                args.putString("summary", result);
                summaryFragment.setArguments(args);
            }
        }

        TabLayout tabLayout= findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        final TabAdapter adapter = new TabAdapter(this , getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}