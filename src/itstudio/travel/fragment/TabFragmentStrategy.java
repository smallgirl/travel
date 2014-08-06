package itstudio.travel.fragment;

import itstudio.travel.R;
import itstudio.travel.adapter.StrategyAdapter;
import itstudio.travel.entity.Strategy;
import itstudio.travel.ui.TabHotStrategy;
import itstudio.travel.widget.KeywordsFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;

/**
 * @Description 攻略TabView TabFragment
 * 
 * @author MR.Wang
 * 
 * @date 2014-7-5 上午9:39:08
 * 
 * @version V1.0
 */
public class TabFragmentStrategy extends Fragment {

	private static final String ARG_POSITION = "position";
	private int position;
	private ListView listView;
	private static Activity myActivity;
	private BaseAdapter listAdapter;
	private View replaceView;
	private List<Strategy> strategies;
	private KeywordsFlow keywordsFlow;
	public static final String[] keywords = { "玉龙雪山", "大研古城", "云杉坪", "白水河",
			"甘海子",//
			"冰塔林", "丽江木府", "东巴万神园", "泸沽湖", "束河古镇",//
			"清凉台", "排云亭", "丹霞峰", "始信峰", "光明顶",//
			"九寨沟", "桂林山水", "鼓浪屿", "长城", "张家界",//
			"布达拉宫", "西湖", "洋人街", "千佛山", "天池",//
			"千山", "大理古镇", "钟鼓楼", "峨眉山", "武当山",//
			"富士山", "趵突泉", "红旗渠", "嵖岈山", "南海禅寺",//
			"博物馆", "八关山", "日照", "青岛", "兵马俑",//
			"都江堰", "故宫", "西双版纳", "迪士尼", "青海湖",//
			"长江三峡", "少林寺", "金丝大峡谷", "大昭寺" };

	// 不能动
	public static TabFragmentStrategy newInstance(int position,
			Activity activity) {
		TabFragmentStrategy f = new TabFragmentStrategy();
		myActivity = activity; // 定义activity
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);
		// pager1
		if (position == 0) {
			strategies = new ArrayList<Strategy>();
			Strategy strategy;

			strategy = new Strategy();
			strategy.setTitle("国庆7日游");
			strategy.setAuthor("逍遥游一生");
			strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_tianya);
			strategies.add(strategy);

			strategy = new Strategy();
			strategy.setTitle("这里是曼谷");
			strategy.setAuthor("韩大黑");
			strategy.setPicUrl("drawable://" + R.drawable.pic_strategy1);
			strategies.add(strategy);

			strategy = new Strategy();
			strategy.setTitle("青海湖");
			strategy.setAuthor("妮妮");
			strategy.setPicUrl("drawable://" + R.drawable.pic_strategy2);
			strategies.add(strategy);

			strategy = new Strategy();
			strategy.setTitle("菊花岛4日游");
			strategy.setAuthor("黑大老万");
			strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_wuzhizhou);
			strategies.add(strategy);

			strategy = new Strategy();
			strategy.setTitle("塞班游");
			strategy.setAuthor("一镜收江南");
			strategy.setPicUrl("drawable://" + R.drawable.pic_strategy5);
			strategies.add(strategy);

			// 设置pager
			replaceView = inflater.inflate(R.layout.tab_three_listview, null);
			listView = (ListView) replaceView
					.findViewById(R.id.nearfoods_listview); // 找到listview 的item
			listAdapter = new StrategyAdapter(myActivity, strategies); // StrategyAdapter为自定义的listview的adapter
			AnimationAdapter animAdapter = new ScaleInAnimationAdapter(
					listAdapter);
			animAdapter.setAbsListView(listView);
			animAdapter.setInitialDelayMillis(300);
			listView.setAdapter(animAdapter);
			listView.setAdapter(listAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(getActivity(), TabHotStrategy.class);
					startActivity(intent);

				}
			});
			fl.addView(replaceView);
			return fl;
		}
		// pager3
		if (position == 2) {
			replaceView = inflater.inflate(R.layout.tab_fragment_serch_fly,
					null);

			EditText keyWords = (EditText) replaceView
					.findViewById(R.id.keywords);
			keyWords.setHint("输入攻略关键字");
			keywordsFlow = (KeywordsFlow) replaceView
					.findViewById(R.id.frameLayout1);
			keywordsFlow.setDuration(800l);
			// 添加
			feedKeywordsFlow(keywordsFlow, keywords);
			keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);

			keywordsFlow.setOnItemClickListener(onClick);
			fl.addView(replaceView);
			return fl;
		}
		strategies = new ArrayList<Strategy>();
		Strategy strategy;
		strategy = new Strategy();
		strategy.setTitle("三清山半月游");
		strategy.setAuthor("瞬间烟火");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy2);
		strategies.add(strategy);

		strategy = new Strategy();
		strategy.setTitle("这里是曼谷");
		strategy.setAuthor("韩大黑");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy_tianya);
		strategies.add(strategy);

		strategy = new Strategy();
		strategy.setTitle("大理双廊！！！");
		strategy.setAuthor("王左树");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy1);
		strategies.add(strategy);

		strategy = new Strategy();
		strategy.setTitle("菊花岛4日游");
		strategy.setAuthor("黑大老万");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy5);
		strategies.add(strategy);

		strategy = new Strategy();
		strategy.setTitle("塞班游");
		strategy.setAuthor("一镜收江南");
		strategy.setPicUrl("drawable://" + R.drawable.pic_strategy4);
		strategies.add(strategy);

		// 设置pager
		replaceView = inflater.inflate(R.layout.tab_three_listview, null);
		listView = (ListView) replaceView.findViewById(R.id.nearfoods_listview); // 找到listview
																					// 的item
		listAdapter = new StrategyAdapter(myActivity, strategies); // StrategyAdapter为自定义的listview的adapter
		AnimationAdapter animAdapter = new ScaleInAnimationAdapter(listAdapter);
		animAdapter.setAbsListView(listView);
		animAdapter.setInitialDelayMillis(300);
		listView.setAdapter(animAdapter);
		listView.setAdapter(listAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), TabHotStrategy.class);
				startActivity(intent);

			}
		});
		fl.addView(replaceView);
		return fl;
	}

	private static void feedKeywordsFlow(KeywordsFlow keywordsFlow, String[] arr) {
		Random random = new Random();
		for (int i = 0; i < KeywordsFlow.MAX; i++) {
			int ran = random.nextInt(arr.length);
			String tmp = arr[ran];
			keywordsFlow.feedKeyword(tmp);
		}
	}

	OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			TextView textview = (TextView) view;
			Toast.makeText(getActivity(), textview.getText(),
					Toast.LENGTH_SHORT).show();
		}
	};
}