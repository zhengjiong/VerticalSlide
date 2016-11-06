package com.stone.verticalslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class VerticalFragment4 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.vertical_fragment4, null);
		initView(rootView);
		return rootView;
	}

	/**
	 * 初始化ListView
	 * 
	 * @param rootView
	 *            根View
	 */
	private void initView(View rootView) {
	}

	public static VerticalFragment4 newInstance() {

		Bundle args = new Bundle();

		VerticalFragment4 fragment = new VerticalFragment4();
		fragment.setArguments(args);
		return fragment;
	}
}
