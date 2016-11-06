package com.stone.verticalslide;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VerticalFragmentBottom1 extends Fragment {

	private ViewPager mViewPager;
    private CustBottomScrollView mScrollView;
    private List<ImageView> mImageViewList = new ArrayList<>();

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.vertical_fragment_bottom1, null);
        mScrollView = (CustBottomScrollView) rootView.findViewById(R.id.custScrollView);
        TextView oldTextView = (TextView) rootView
				.findViewById(R.id.old_textview);
		oldTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return rootView;
	}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);

        for (int i = 0;i<3;i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.image_layout, null);
            mImageViewList.add(imageView);
        }

        mViewPager.setAdapter(new ViewPagerAdapter());
    }

    class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(mImageViewList.get(position));
        }
    }

    public void goTop(){
        mScrollView.goTop();
    }
}
