package itstudio.travel.ui;

import itstudio.travel.R;
import itstudio.travel.adapter.HotStrategyListAdapter;
import itstudio.travel.entity.Strategy;
import itstudio.travel.xlistview.XListView;
import itstudio.travel.xlistview.XListView.IXListViewListener;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class TabHotStrategy extends Activity implements IXListViewListener {
	private XListView listView;
	DisplayImageOptions options;
	private HotStrategyListAdapter adpater;
	private ArrayList<Strategy> strategyList;
	private View back_view;
	private View download_view;
	private View collect_view;
	private View header;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_hot_strategy);
		initImageOptions();
		findById();
		setOnclick();
		initView();

	}

	private void setOnclick() {
		// TODO Auto-generated method stub
		clickListener click = new clickListener();
		back_view.setOnClickListener(click);
		download_view.setOnClickListener(click);
		collect_view.setOnClickListener(click);

	}

	private void findById() {
		// TODO Auto-generated method stub
		
		LayoutInflater layoutInflater = getLayoutInflater();
		header = layoutInflater.inflate(R.layout.tab_hot_strate_header,
				listView, false);
		listView = (XListView) findViewById(R.id.listview_hot_strategy);
		download_view = header.findViewById(R.id.donwload_layout);
		back_view = findViewById(R.id.backLayout_hot_strategy);
		collect_view = findViewById(R.id.collect_view);

	}

	private void initView() {
		// TODO Auto-generated method stub
		strategyList = new ArrayList<Strategy>();
		Strategy strategy;

		strategy = new Strategy();
		strategy.setDay("第一天");
		strategy.setDate("2014.08.12");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_yalongwan);
		strategy.setTitle("亚龙湾");
		strategy.setDetailUrl("绵软细腻的沙滩绵延伸展约8公里，海滩长度约是美国夏威夷的3倍。享有“天下第一湾”的美誉。");
		strategyList.add(strategy);

		strategy = new Strategy();
		strategy.setDay("第二天");
		strategy.setDate("2014.08.13");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_wuzhizhou);
		strategy.setTitle("蜈支洲岛");
		strategy.setDetailUrl("蜈支洲岛坐落在三亚市北部的海棠湾内，距三亚林旺镇后海村2.7公里的海面上。四周海域毫无污染，海水清澈透明，最高能见度达27 米。");
		strategyList.add(strategy);

		strategy = new Strategy();
		strategy.setDay("第三天");
		strategy.setDate("2014.08.14");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_tianya);
		strategy.setTitle("天涯海角");
		strategy.setDetailUrl("是海南建省20年第一亮丽品牌。这里有“天涯”“海角”的浪漫，也有“南天一柱”的壮观，在爱情石前许下山盟海誓的承诺。");
		strategyList.add(strategy);

		// 加载延迟

		
		listView.addHeaderView(header);
		adpater = new HotStrategyListAdapter(TabHotStrategy.this, strategyList,
				options);
		AnimationAdapter animAdapter = new SwingBottomInAnimationAdapter(
				adpater);
		listView.setPullLoadEnable(true);
		
		animAdapter.setAbsListView(listView);
		animAdapter.setInitialDelayMillis(300);
		listView.setAdapter(animAdapter);
		listView.setXListViewListener(this);

		listView.setPullRefreshEnable(false);// 取消下拉刷新

	}

	class clickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.donwload_layout:
				Toast.makeText(getApplication(), "已下载", 0).show();
				break;
			case R.id.backLayout_hot_strategy:
				finish();
				break;
			case R.id.collect_view:
				Toast.makeText(getApplication(), "已收藏", 0).show();
				break;

			default:
				break;
			}

		}
	}

	private void initImageOptions() {
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

}
