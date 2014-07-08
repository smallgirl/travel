package itstudio.travel.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import itstudio.travel.R;
import itstudio.travel.adapter.StrategyAdapter;
import itstudio.travel.entity.Strategy;
import itstudio.travel.widget.KeywordsFlow;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Toast;

/**
* @Description 攻略TabFragment  

* @author MR.Wang

* @date 2014-7-5 上午9:39:08 

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
	public static final String[] keywords = { "奥尔良烤翅", "咚咚烧", "鳗鱼饭三吃",
		"火锅", "红烧排骨",//
		"绝味", "鱼", "三文鱼", "冰激凌", "双皮奶",//
		"奶昔", "臭豆腐", "鸡蛋卷", "黑森林", "马芬",//
		"甜甜圈", "榴莲补丁", "干炒牛河", "叉烧包", "猪肝烧麦",//
		"炖牛杂", "大盘鸡", "羊角包", "安全", "虾饺皇",//
		"可颂", "钵仔糕", "牛腩面", "心太软", "提拉米苏",//
		"欧朋", "手抓羊肉", "愤怒的小鸟", "杨枝甘露", "椰汁西米露",//
		"水果捞", "椰汁西米露", "烧鸭", "南京卤水鸭", "酸菜鱼",//
		"烤松茸", "香煎马鲛鱼", "鱼头泡饼", "油焖春笋", "干炒牛河",//
		"兰州拉面", " 岐山臊子面", "大煮干丝", "豆腐脑" };
	
	public static TabFragmentStrategy newInstance(int position,Activity activity) {
		TabFragmentStrategy f = new TabFragmentStrategy();
		myActivity = activity;
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);
		if(position==0){
			strategies = new ArrayList<Strategy>();
			Strategy strategy;
			for(int i=1;i<=5;i++){
				strategy = new Strategy();
				strategy.setTitle("国庆7日游");
				strategy.setAuthor("逍遥游一生");
				strategy.setPicUrl("drawable://" + R.drawable.pic_strategy3);
				strategies.add(strategy);
			}
			
			replaceView = inflater.inflate(R.layout.tab_third_fragment ,null);
			listView = (ListView)  replaceView.findViewById(R.id.nearfoods_listview);
			listAdapter = new StrategyAdapter(myActivity, strategies);
			AnimationAdapter animAdapter = new ScaleInAnimationAdapter(listAdapter);
			animAdapter.setAbsListView(listView);
			animAdapter.setInitialDelayMillis(300);
			listView.setAdapter(animAdapter);
			listView.setAdapter(listAdapter);
			fl.addView(replaceView);
			return fl;	
		}
		if(position==2){
			replaceView = inflater.inflate(R.layout.tab_fragment_serch_fly ,null);
			
			EditText keyWords  = (EditText) replaceView.findViewById(R.id.keywords) ;
			keyWords.setHint("输入攻略关键字");
			keywordsFlow = (KeywordsFlow) replaceView.findViewById(R.id.frameLayout1);
			keywordsFlow.setDuration(800l);
			// 添加
			feedKeywordsFlow(keywordsFlow, keywords);
			keywordsFlow.go2Show(KeywordsFlow.ANIMATION_OUT);
			
			keywordsFlow.setOnItemClickListener(onClick);
			fl.addView(replaceView);
			return fl;	
		}
		
		View life_view = inflater.inflate(R.layout.fragment_comment, null);
		fl.addView(life_view);
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
	OnClickListener onClick  = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			TextView  textview  = (TextView) view;
			Toast.makeText(getActivity(),textview.getText(),Toast.LENGTH_SHORT).show();
		}
	};
}