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
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VerticalFragment1 extends Fragment {

	private ViewPager mViewPager;
	private CustomViewPager mGridViewPager;
    private CustScrollView mScrollView;
    private List<ImageView> mImageViewList = new ArrayList<>();
    private List<GridView> mGridViewList = new ArrayList<>();

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.vertical_fragment1, null);
        mScrollView = (CustScrollView) rootView.findViewById(R.id.custScrollView);
        TextView oldTextView = (TextView) rootView
				.findViewById(R.id.old_textview);
		oldTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		return rootView;
	}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mGridViewPager = (CustomViewPager) view.findViewById(R.id.gridviewpager);

        for (int i = 0;i<10;i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.image_layout, null);
            mImageViewList.add(imageView);
        }

        mViewPager.setAdapter(new ViewPagerAdapter());

        for (int i = 0;i<20;i++) {
            GridView gridView = new GridView(getContext());
            gridView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            gridView.setColumnWidth(GridView.STRETCH_COLUMN_WIDTH);
            gridView.setNumColumns(3);
            gridView.setAdapter(new GridAdapter());
            mGridViewList.add(gridView);
        }

        mGridViewPager.setAdapter(new GridViewPagerAdapter());
    }

    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                ImageView imageView = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.image_layout, null);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(R.drawable.capture01);
                convertView = imageView;
            }
            return convertView;
        }
    }

    class GridViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mGridViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mGridViewList.get(position));
            return mGridViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView(mGridViewList.get(position));
        }
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
