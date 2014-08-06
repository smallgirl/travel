package itstudio.travel.fragment;

import itstudio.travel.R;
import itstudio.travel.adapter.TicketListAdapter;
import itstudio.travel.adapter.TicketPagerAdapter;
import itstudio.travel.entity.Notice;
import itstudio.travel.entity.Strategy;
import itstudio.travel.util.FixedSpeedScroller;
import itstudio.travel.widget.ChildViewPager;
import itstudio.travel.widget.ChildViewPager.OnSingleTouchListener;
import itstudio.travel.xlistview.XListView;
import itstudio.travel.xlistview.XListView.IXListViewListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class TabTicket extends FragmentActivity implements IXListViewListener,
		OnSingleTouchListener {
	private XListView listView;
	DisplayImageOptions options;
	DisplayImageOptions options_header;
	private TicketListAdapter adpater;
	private ArrayList<Strategy> strategyList;
	private View back_view;
	private View collect_view;

	private ChildViewPager viewPager;
	private List<View> dots;
	private TextView tv_title;
	private int currentItem = 0;
	private ScheduledExecutorService scheduledExecutorService;
	private List<Notice> notices;
	private Notice notice;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.tab_ticket);
		findById();
		setOnclick();
		initView();
	}

	class clickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.ticket_back:
				finish();
				break;

			default:
				break;
			}

		}
	}

	private void findById() {
		// TODO Auto-generated method stub
		listView = (XListView) findViewById(R.id.ticket_listview);
		back_view = findViewById(R.id.ticket_back);

	}

	private void setOnclick() {
		// TODO Auto-generated method stub
		clickListener click = new clickListener();
		back_view.setOnClickListener(click);

	}

	private void initView() {
		// TODO Auto-generated method stub
		LayoutInflater layoutInflater = getLayoutInflater();
		View header = layoutInflater.inflate(R.layout.viewpaper_ticket,
				listView, false);

		notices = new ArrayList<Notice>();

		notice = new Notice();
		notice.setPicUrl("drawable://" + R.drawable.pic_ticket3);
		notice.setTitle("黄河风景区");
		notices.add(notice);

		notice = new Notice();
		notice.setPicUrl("drawable://" + R.drawable.pic_ticket_detai2);
		notice.setTitle("世纪欢乐园");
		notices.add(notice);

		notice = new Notice();
		notice.setPicUrl("drawable://" + R.drawable.pic_ticket4);
		notice.setTitle("郑州海洋馆");
		notices.add(notice);

		dots = new ArrayList<View>();
		dots.add(header.findViewById(R.id.v_dot0));
		dots.add(header.findViewById(R.id.v_dot1));
		dots.add(header.findViewById(R.id.v_dot2));

		tv_title = (TextView) header.findViewById(R.id.tv_titles);
		tv_title.setText(notices.get(0).getTitle());//
		viewPager = (ChildViewPager) header.findViewById(R.id.vp);
		setViewPagerScrollSpeed();
		viewPager.setAdapter(new TicketPagerAdapter(notices, TabTicket.this,
				options_header));
		viewPager.setOnPageChangeListener(new MyPageChangeListener());

		viewPager.setOnSingleTouchListener(this);

		strategyList = new ArrayList<Strategy>();
		Strategy strategy;

		strategy = new Strategy();
		strategy.setBuyer("1004");
		strategy.setPrice("68");
		strategy.setPicUrl("drawable://" + R.drawable.pic_ticket_strategy1);
		strategy.setTitle("黄河风景区");
		strategy.setDetailUrl("郑州黄河风景名胜区有炎黄景区、五龙峰、岳山寺、骆驼岭、是海湖五大景区");
		strategyList.add(strategy);

		strategy = new Strategy();
		strategy.setBuyer("1004");
		strategy.setPrice("68");
		strategy.setPicUrl("drawable://" + R.drawable.pic_ticket_strategy2);
		strategy.setTitle("世纪欢乐园");
		strategy.setDetailUrl("集探寻火车文化与感受迪斯尼欢乐为一体的文化主题乐园");
		strategyList.add(strategy);

		strategy = new Strategy();
		strategy.setBuyer("1004");
		strategy.setPrice("68");
		strategy.setPicUrl("drawable://" + R.drawable.pic_ticket_strategy3);
		strategy.setTitle("郑州海洋馆");
		strategy.setDetailUrl("世界上最新一代的大型现代水族馆，也是中国中西部地区最大的水族馆");
		strategyList.add(strategy);

		// 加载延迟

		listView.addHeaderView(header);
		adpater = new TicketListAdapter(TabTicket.this, strategyList, options);
		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				adpater);
		listView.setPullLoadEnable(true);
		animAdapter.setAbsListView(listView);
		animAdapter.setInitialDelayMillis(300);
		listView.setAdapter(animAdapter);
		listView.setXListViewListener(this);
		listView.setPullRefreshEnable(false);// 取消下拉刷新
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TabTicket.this, TabTicketDetail.class);
				startActivity(intent);

			}
		});

	}

	private void initImageOptions() {
		options_header = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.showImageOnLoading(R.drawable.loading_0)
				.showImageOnFail(R.drawable.loading_0).cacheOnDisc(true)
				.considerExifParams(true)
				// 设置下载的图片是否缓存在内存中
				.displayer(new FadeInBitmapDisplayer(200))
				.bitmapConfig(Bitmap.Config.ALPHA_8).build();
		options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				// .showImageOnLoading(R.drawable.user_image_load_loading)
				// .showImageOnFail(R.drawable.user_image_load_fail)
				.cacheOnDisc(true).considerExifParams(true)
				// 设置下载的图片是否缓存在内存中
				.displayer(new FadeInBitmapDisplayer(200))
				.bitmapConfig(Bitmap.Config.ALPHA_8).build(); // 设置图片的解码类型
	}

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem, true);
		};
	};

	@Override
	public void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 3, 5,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % strategyList.size();
				handler.obtainMessage().sendToTarget();
			}
		}

	}

	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		/**
		 * This method will be invoked when a new page becomes selected.
		 * position: Position index of the new selected page.
		 */
		public void onPageSelected(final int position) {
			currentItem = position;

			Animation animation = new AlphaAnimation(1.0f, 0);
			animation.setDuration(300);
			animation.setInterpolator(new DecelerateInterpolator());
			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					tv_title.setText(strategyList.get(position).getTitle());
					Animation animation1 = new AlphaAnimation(0, 1.0f);
					animation1.setDuration(300);
					animation1.setInterpolator(new AccelerateInterpolator());
					tv_title.startAnimation(animation1);
				}
			});
			tv_title.startAnimation(animation);

			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;

		}

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	/**
	 * 设置ViewPager的滑动速度
	 * 
	 * */
	private void setViewPagerScrollSpeed() {
		try {
			Field mScroller = null;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			FixedSpeedScroller scroller = new FixedSpeedScroller(
					viewPager.getContext());
			mScroller.set(viewPager, scroller);
		} catch (NoSuchFieldException e) {

		} catch (IllegalArgumentException e) {

		} catch (IllegalAccessException e) {

		}
	}

	// 设置下拉刷新时间
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				onLoad();
				// mAdapter.notifyDataSetChanged();
			}
		}, 2000);

	}

	// 设置loadmore刷新时间
	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		Handler mHandler = new Handler();
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				onLoad();
			}
		}, 2000);

	}

	private void onLoad() {
		listView.stopRefresh();
		listView.stopLoadMore();
		listView.setRefreshTime("刚刚");
	}

	@Override
	public void onSingleTouch() {
		// TODO Auto-generated method stub
		System.out.println("当前 点击的是" + viewPager.getCurrentItem());
		Toast.makeText(TabTicket.this, R.string.wait_none, Toast.LENGTH_SHORT)
				.show();

	}

}
