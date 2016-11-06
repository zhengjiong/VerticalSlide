package com.stone.verticalslide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stone.verticalslide.DragLayout.ShowNextPageNotifier;

public class MainFragment extends Fragment {

    private VerticalFragment1 fragment1;
    private VerticalFragmentBottom1 fragment3;
    private DragLayout draglayout;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        draglayout = (DragLayout) view.findViewById(R.id.draglayout);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        final ImageView imgGoTop = (ImageView) getView().findViewById(R.id.img_go_top);
        imgGoTop.setVisibility(View.GONE);
        fragment1 = new VerticalFragment1();
        fragment3 = new VerticalFragmentBottom1();

        getChildFragmentManager().beginTransaction()
                .add(R.id.first, fragment1).add(R.id.second, fragment3)
                .commit();

        ShowNextPageNotifier nextIntf = new ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                //fragment3.initView();
                System.out.println("onDragNext");
                imgGoTop.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDragPrevious() {
                System.out.println("onDragPrevious");
                imgGoTop.setVisibility(View.GONE);
            }
        };
        draglayout.setNextPageListener(nextIntf);



        imgGoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment3.goTop();
                fragment1.goTop();
                draglayout.goTop();
            }
        });
    }

}
