package com.stone.verticalslide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.stone.verticalslide.DragLayout.ShowNextPageNotifier;

public class MainActivity extends AppCompatActivity {

    private VerticalFragment1 fragment1;
    private VerticalFragment3 fragment3;
    private DragLayout draglayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        fragment1 = new VerticalFragment1();
        fragment3 = new VerticalFragment3();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.first, fragment1).add(R.id.second, fragment3)
                .commit();

        ShowNextPageNotifier nextIntf = new ShowNextPageNotifier() {
            @Override
            public void onDragNext() {
                fragment3.initView();
            }

            @Override
            public void onDragPrevious() {

            }
        };
        draglayout = (DragLayout) findViewById(R.id.draglayout);
        draglayout.setNextPageListener(nextIntf);


    }

}
