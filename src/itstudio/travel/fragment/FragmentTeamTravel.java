package itstudio.travel.fragment;

import java.util.ArrayList;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import itstudio.travel.R;
import itstudio.travel.adapter.TicketListAdapter;
import itstudio.travel.entity.Strategy;
import itstudio.travel.xlistview.XListView;
import itstudio.travel.xlistview.XListView.IXListViewListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
* @Description 团游 Fragment

* @author MR.Wang

* @date 2014-7-5 上午1:13:26 

* @version V1.0
*/

public class FragmentTeamTravel extends Fragment implements IXListViewListener {

	//
	private View view;
	private XListView listView;
	DisplayImageOptions options;
	private ArrayList<Strategy> strategyList;
	private TicketListAdapter adpater;
	//
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_teamtravel, container, false);
		context = inflater.getContext();
		findById(view);
		initImageOptions();
		setOnclick();
		initView(view);
		return view;
	}

	private void findById(View v) {
		// TODO Auto-generated method stub
		listView = (XListView) v.findViewById(R.id.team_travel_listview);
	}

	private void setOnclick() {
		// TODO Auto-generated method stub
		
	}

	private void initView(View view2) {
		// TODO Auto-generated method stub
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
		
		strategy = new Strategy();
		strategy.setBuyer("1004");
		strategy.setPrice("68");
		strategy.setPicUrl("drawable://" + R.drawable.pic_ticket_strategy2);
		strategy.setTitle("郑州海洋馆");
		strategy.setDetailUrl("世界上最新一代的大型现代水族馆，也是中国中西部地区最大的水族馆");
		strategyList.add(strategy);
		
		strategy = new Strategy();
		strategy.setBuyer("1004");
		strategy.setPrice("68");
		strategy.setPicUrl("drawable://" + R.drawable.pic_ticket_strategy1);
		strategy.setTitle("郑州海洋馆");
		strategy.setDetailUrl("世界上最新一代的大型现代水族馆，也是中国中西部地区最大的水族馆");
		strategyList.add(strategy);

		// 加载延迟

		//listView.addHeaderView(header);
		adpater = new TicketListAdapter(getActivity(), strategyList, options);
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
				intent.setClass(getActivity(), TabTicketDetail.class);
				startActivity(intent);

			}
		});
		
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
