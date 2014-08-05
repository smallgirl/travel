package itstudio.travel.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**   
* @Description  重写ScrollView 使其能够 出现徐悬浮效果
* @author MR.Wang  
* @date 2014-7-9 下午12:49:07 
* @version V1.0   
*/ 
public class BuyScrollView extends ScrollView {
	private OnScrollListener onScrollListener;
	
	public BuyScrollView(Context context) {
		this(context, null);
	}
	
	public BuyScrollView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public BuyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	
	/**
	 * 设置滚动接口
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener != null){
			onScrollListener.onScroll(t);
		}
	}



	/**
	 * 滚动的回调接口
	 * @author xiaanming
	 *
	 */
	public interface OnScrollListener{
		/**
		 * 回调方法， 返回MyScrollView滑动的Y方向距离
		 * @param scrollY
		 * 				、
		 */
		public void onScroll(int scrollY);
	}
	
	

}
