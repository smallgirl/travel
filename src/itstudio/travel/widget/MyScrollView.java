package itstudio.travel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView{
	// 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;
    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
    }

    public MyScrollView(Context context) {
            super(context);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
            switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                    xDistance = yDistance = 0f;
                    xLast = ev.getX();
                    yLast = ev.getY();
                    break;
            case MotionEvent.ACTION_MOVE:
                    final float curX = ev.getX();
                    final float curY = ev.getY();

                    xDistance += Math.abs(curX - xLast);
                    yDistance += Math.abs(curY - yLast);
                    xLast = curX;
                    yLast = curY;

                    if (xDistance > yDistance) {
                            return false;   //表示向下传递事件
                    }
            }

            return super.onInterceptTouchEvent(ev);
    }
}
