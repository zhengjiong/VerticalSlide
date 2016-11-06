package com.stone.verticalslide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: ViewPagerActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/8/5  17:52
 *
 * @author 郑炯
 * @version 1.0
 */
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private TabLayout tabLayout;

    private FragmentAdapter pagerAdapter;
    private List<String> mTitle = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tab);

        if (savedInstanceState == null) {
            fragments.add(VerticalFragment4.newInstance());
            fragments.add(VerticalFragment4.newInstance());
            fragments.add(VerticalFragment4.newInstance());
        }

        initViewPager();
    }

    private void initViewPager() {
        mTitle.add("A");
        mTitle.add("B");
        mTitle.add("C");

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, mTitle);
        viewpager.setOffscreenPageLimit(fragments.size());
        viewpager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
