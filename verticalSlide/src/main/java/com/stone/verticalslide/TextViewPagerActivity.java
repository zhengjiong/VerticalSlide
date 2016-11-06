package com.stone.verticalslide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: TextViewPagerActivity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:16/9/26  14:53
 *
 * @author 郑炯
 * @version 1.0
 */
public class TextViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout appBarLayout;

    private FragmentAdapter pagerAdapter;

    protected List<String> mTitle = new ArrayList<>();
    protected List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_viewpager_layout);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        appBarLayout = (TabLayout) findViewById(R.id.tab);

        mTitle = new ArrayList<>();
        mTitle.add("商品1");
        mTitle.add("商品2");
        mTitle.add("商品3");

        fragments.add(MainFragment.newInstance());
        fragments.add(AFragment.newInstance());
        fragments.add(AFragment.newInstance());

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, mTitle);
        mViewPager.setOffscreenPageLimit(fragments.size());
        mViewPager.setAdapter(pagerAdapter);
        appBarLayout.setupWithViewPager(mViewPager);
    }
}
