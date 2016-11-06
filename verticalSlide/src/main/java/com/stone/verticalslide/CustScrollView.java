package com.stone.verticalslide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 只为顶部ScrollView使用
 * 如果使用了其它的可拖拽的控件，请仿照该类实现isAtBottom方法
 */
public class CustScrollView extends ScrollView {
    private static final int TOUCH_IDLE = 0;
    private static final int TOUCH_INNER_CONSIME = 1; // touch事件由ScrollView内部消费
    private static final int TOUCH_DRAG_LAYOUT = 2; // touch事件由上层的DragLayout去消费

    boolean isAtBottom; // 按下的时候是否在底部
    private int mTouchSlop = 4; // 判定为滑动的阈值，单位是像素
    private int scrollMode;
    public boolean isHorizontal;

    private float downX;
    private float downY;

    public CustScrollView(Context arg0) {
        this(arg0, null);
    }

    public CustScrollView(Context arg0, AttributeSet arg1) {
        this(arg0, arg1, 0);
    }

    public CustScrollView(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
        ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downX = ev.getRawX();
            downY = ev.getRawY();
            isHorizontal = false;
            isAtBottom = isAtBottom();
            scrollMode = TOUCH_IDLE;
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
                float xOffset = downX - ev.getRawX();
                float yOffset = downY - ev.getRawY();
                float yDistance = Math.abs(yOffset);
                if (yDistance > mTouchSlop) {
                    if (yOffset > 0 && isAtBottom) {
                        //System.out.println("yOffset > 0 && isAtBottom");
                        scrollMode = TOUCH_DRAG_LAYOUT;
                        getParent().requestDisallowInterceptTouchEvent(false);
                        //System.out.println("dispatchTouchEvent return true");
                        return true;
                    } else {
                        scrollMode = TOUCH_INNER_CONSIME;

                    }

                } else if (Math.abs(xOffset) * 0.5 > yDistance) {
                    //水平方向滑动, 让父view消耗事件
                    //System.out.println("水平方向滑动");
                    getParent().requestDisallowInterceptTouchEvent(false);
                    isHorizontal = true;
                }
        }
        boolean result = super.dispatchTouchEvent(ev);
        if (!result) {
            System.out.println("result=" + result);
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scrollMode == TOUCH_DRAG_LAYOUT) {
            //System.out.println("onTouchEvent TOUCH_DRAG_LAYOUT");
            return false;
        }
        return super.onTouchEvent(ev);
    }

    private boolean isAtBottom() {
        return getScrollY() + getMeasuredHeight() >= computeVerticalScrollRange() - 2;
    }

    public void goTop(){
        scrollTo(0, 0);
    }
}
