package itstudio.travel.ui;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import itstudio.travel.R;
import itstudio.travel.ui.MyScrollView.OnScrollListener;

public class NearFoodDetail extends Activity implements OnScrollListener{
	
	
	
	private MyScrollView myScrollView;
	private LinearLayout mBuyLayout;
	private WindowManager mWindowManager;
	/**
	 * 手机屏幕宽度
	 */
	private int screenWidth;
	/**
	 * 悬浮框View
	 */
	private static View suspendView;
	/**
	 * 悬浮框的参数
	 */
	private static WindowManager.LayoutParams suspendLayoutParams;
	/**
	 * 购买布局的高度
	 */
	private int buyLayoutHeight;
	/**
	 * myScrollView与其父类布局的顶部距离
	 */
	private int myScrollViewTop;

	/**
	 * 购买布局与其父类布局的顶部距离
	 */
	private int buyLayoutTop;
	
	
	private int imageIds[];
	private String[] titles;
	private ArrayList<ImageView> images;
	private ArrayList<View> dots;
	private TextView title;
	private ViewPager mViewPager;
	private ViewPagerAdapter adapter;

	private int oldPosition = 0;// 记录上一次点的位置
	private int currentItem; // 当前页面
	private ScheduledExecutorService scheduledExecutorService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_item_nearfood_detail);
		

		// 图片ID
		imageIds = new int[] { R.drawable.a, R.drawable.b, R.drawable.c,
				R.drawable.d, R.drawable.e };

		// 图片标题
		titles = new String[] { "1", "2", "3", "4", "5" };

		// 显示的图片
		images = new ArrayList<ImageView>();
		for (int i = 0; i < imageIds.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setBackgroundResource(imageIds[i]);

			images.add(imageView);
		}

		// 显示的点
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.dot_0));
		dots.add(findViewById(R.id.dot_1));
		dots.add(findViewById(R.id.dot_2));
		dots.add(findViewById(R.id.dot_3));
		dots.add(findViewById(R.id.dot_4));

		title = (TextView) findViewById(R.id.title);
		title.setText(titles[0]);

		mViewPager = (ViewPager) findViewById(R.id.vp);

		adapter = new ViewPagerAdapter();
		mViewPager.setAdapter(adapter);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				title.setText(titles[position]);

				dots.get(oldPosition).setBackgroundResource(
						R.drawable.dot_normal);
				dots.get(position)
						.setBackgroundResource(R.drawable.dot_focused);

				oldPosition = position;
				currentItem = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		myScrollView = (MyScrollView) findViewById(R.id.scrollView);
		mBuyLayout = (LinearLayout) findViewById(R.id.buy);
		
		myScrollView.setOnScrollListener(this);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = mWindowManager.getDefaultDisplay().getWidth();  
	}
	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return images.size();
		}

		// 是否是同一张图片
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup view, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			// view.removeViewAt(position);
			view.removeView(images.get(position));

		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			// TODO Auto-generated method stub
			view.addView(images.get(position));

			return images.get(position);
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		// 每隔2秒钟切换一张图片
		scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2,
				2, TimeUnit.SECONDS);
	}

	// 切换图片
	private class ViewPagerTask implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			currentItem = (currentItem + 1) % imageIds.length;
			// 更新界面
			// handler.sendEmptyMessage(0);
			handler.obtainMessage().sendToTarget();
		}

	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			// 设置当前页面
			mViewPager.setCurrentItem(currentItem);
		}

	};

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	/**
	 * 窗口有焦点的时候，即所有的布局绘制完毕的时候，我们来获取购买布局的高度和myScrollView距离父类布局的顶部位置
	 */
	@Override  
	public void onWindowFocusChanged(boolean hasFocus) {  
	    super.onWindowFocusChanged(hasFocus);  
	    if(hasFocus){
	    	buyLayoutHeight = mBuyLayout.getHeight();
	    	buyLayoutTop = mBuyLayout.getTop();
	    	
	    	myScrollViewTop = myScrollView.getTop();
	    }
	} 




	/**
	 * 滚动的回调方法，当滚动的Y距离大于或者等于 购买布局距离父类布局顶部的位置，就显示购买的悬浮框
	 * 当滚动的Y的距离小于 购买布局距离父类布局顶部的位置加上购买布局的高度就移除购买的悬浮框
	 * 
	 */
	@Override
	public void onScroll(int scrollY) {
		if(scrollY >= buyLayoutTop){
			if(suspendView == null){
				showSuspend();
			}
		}else if(scrollY <= buyLayoutTop + buyLayoutHeight){
			if(suspendView != null){
				removeSuspend();
			}
		}
	}


	/**
	 * 显示购买的悬浮框
	 */
	private void showSuspend(){
		if(suspendView == null){
			suspendView = LayoutInflater.from(this).inflate(R.layout.listview_item_nearfood_detail_goumai, null);
			if(suspendLayoutParams == null){
				suspendLayoutParams = new LayoutParams();
				suspendLayoutParams.type = LayoutParams.TYPE_PHONE;  
				suspendLayoutParams.format = PixelFormat.RGBA_8888;  
				suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL  
	                     | LayoutParams.FLAG_NOT_FOCUSABLE;  
				suspendLayoutParams.gravity = Gravity.TOP;  
				suspendLayoutParams.width = screenWidth;
				suspendLayoutParams.height = buyLayoutHeight;  
				suspendLayoutParams.x = 0;  
				suspendLayoutParams.y = myScrollViewTop;  
			}
		}
		
		/*添加   权限<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />  */
		mWindowManager.addView(suspendView, suspendLayoutParams);
	}
	
	
	/**
	 * 移除购买的悬浮框
	 */
	private void removeSuspend(){
		if(suspendView != null){
			mWindowManager.removeView(suspendView);
			suspendView = null;
		}
	}

}
