package itstudio.travel.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import itstudio.travel.R;
import itstudio.travel.adapter.CateringAdapter;
import itstudio.travel.entity.Catering;
import itstudio.travel.ui.HotelDetail;
import itstudio.travel.ui.NearFoodDetail;
import itstudio.travel.widget.KeywordsFlow;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

/**
* @Description 餐饮TabFragment 

* @author MR.Wang

* @date 2014-7-5 上午9:38:33 

* @version V1.0
*/
public class TabFragmentHotel extends Fragment {

	private static final String ARG_POSITION = "position";
	private int position;
	private ListView listView;
	private static Activity myActivity;
	private BaseAdapter listAdapter;
	private List<Catering> caterings;
	private View replaceView;

	
	public static TabFragmentHotel newInstance(int position,Activity activity) {
		TabFragmentHotel f = new TabFragmentHotel();
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
			
			if(replaceView==null){
				
				caterings = new ArrayList<Catering>();
				Catering catering;
				for(int i=0;i<5;i++){
					catering = new Catering();
					catering.setTitle("郑州建业艾美酒店");
					catering.setShortInfo("郑州建业艾美酒店坐落于中州大道 ，毗邻郑东新区和亚洲最大火车站新郑州东站，周边商业繁");
					catering.setPicUrl("drawable://" + R.drawable.pic_hotel1);
					catering.setDistance("100米");
					if(i==1){
						catering.setTitle("郑州悦莱时尚酒店");
						catering.setShortInfo("酒店设计时尚简约，全新配置中央空调、液晶电视、电话、时尚高档家俬、24小时热水、免费");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel2);
						catering.setDistance("200米");
					}
					if(i==2){
						
						catering.setTitle("河南永和铂爵国际酒店");
						catering.setShortInfo("酒店完美地融合了亚洲待客之道中的体贴、热情和真诚，以极其专业的高服务水准营造充满温");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel3);
						catering.setDistance("300米");
					}
					if(i==3){
						catering.setTitle("郑州中油花园酒店");
						catering.setShortInfo("郑州中油花园酒店位于郑东新区CBD商务区，毗邻郑州国际会展中心、河南省艺术中心、如意湖");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel4);
						catering.setDistance("400米");
					}
					if(i==4){
						catering.setTitle("郑州希尔顿酒店");
						catering.setShortInfo("酒店提供闻名全球的希尔顿会议服务及12间大小不同的会议室，其中包括1000平米无柱式豪华");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel5);
						catering.setDistance("500米");
					}
					catering.setRating(4);
					caterings.add(catering);
				}			
				
				replaceView = inflater.inflate(R.layout.normal_listview ,null);
				listView = (ListView)  replaceView.findViewById(R.id.normal_listview);
				listAdapter = new CateringAdapter(myActivity, caterings);
				AnimationAdapter animAdapter = new ScaleInAnimationAdapter(listAdapter);
				animAdapter.setAbsListView(listView);
				animAdapter.setInitialDelayMillis(300);
				listView.setAdapter(animAdapter);
				listView.setAdapter(listAdapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						System.out.println("点击了listview");
						Intent intent=new Intent(getActivity(), HotelDetail.class);
						startActivity(intent);
					}
				});
				
			}
			
			 ViewGroup parent = (ViewGroup) replaceView.getParent();  
		     if (parent != null) {  
		    	 parent.removeView(replaceView);  
		     }  
			fl.addView(replaceView);
		}
		if(position==1){
			
			
			if(replaceView==null){
				
				caterings = new ArrayList<Catering>();
				Catering catering;
				for(int i=0;i<5;i++){
					catering = new Catering();
					catering.setTitle("郑州希尔顿酒店");
					catering.setShortInfo("酒店提供闻名全球的希尔顿会议服务及12间大小不同的会议室，其中包括1000平米无柱式豪华");
					catering.setPicUrl("drawable://" + R.drawable.pic_hotel5);
					catering.setDistance("100米");
					if(i==1){
						catering.setTitle("河南永和铂爵国际酒店");
						catering.setShortInfo("酒店完美地融合了亚洲待客之道中的体贴、热情和真诚，以极其专业的高服务水准营造充满温");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel3);
						catering.setDistance("300米");
					}
					if(i==2){
						
						catering.setTitle("郑州悦莱时尚酒店");
						catering.setShortInfo("酒店设计时尚简约，全新配置中央空调、液晶电视、电话、时尚高档家俬、24小时热水、免费");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel2);
						catering.setDistance("200米");
					}
					if(i==3){
						catering.setTitle("郑州中油花园酒店");
						catering.setShortInfo("郑州中油花园酒店位于郑东新区CBD商务区，毗邻郑州国际会展中心、河南省艺术中心、如意湖");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel4);
						catering.setDistance("400米");
					}
					if(i==4){
						catering.setTitle("郑州建业艾美酒店");
						catering.setShortInfo("郑州建业艾美酒店坐落于中州大道 ，毗邻郑东新区和亚洲最大火车站新郑州东站，周边商业繁");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel1);
						catering.setDistance("500米");
					}
					catering.setRating(4);
					caterings.add(catering);
				}			
				

				replaceView = inflater.inflate(R.layout.normal_listview ,null);
				listView = (ListView)  replaceView.findViewById(R.id.normal_listview);
				listAdapter = new CateringAdapter(myActivity, caterings);
				AnimationAdapter animAdapter = new ScaleInAnimationAdapter(listAdapter);
				animAdapter.setAbsListView(listView);
				animAdapter.setInitialDelayMillis(300);
				listView.setAdapter(animAdapter);
				listView.setAdapter(listAdapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
					
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						System.out.println("点击了listview");
						Intent intent=new Intent(getActivity(), HotelDetail.class);
						startActivity(intent);
					}
				});
			}
			
			 ViewGroup parent = (ViewGroup) replaceView.getParent();  
		     if (parent != null) {  
		    	 parent.removeView(replaceView);  
		     }  
			fl.addView(replaceView);
		}
		if(position==2){
			
			if(replaceView==null){
				
				caterings = new ArrayList<Catering>();
				Catering catering;
				for(int i=0;i<5;i++){

					catering = new Catering();
					catering.setTitle("郑州希尔顿酒店");
					catering.setShortInfo("酒店提供闻名全球的希尔顿会议服务及12间大小不同的会议室，其中包括1000平米无柱式豪华");
					catering.setPicUrl("drawable://" + R.drawable.pic_hotel5);
					catering.setDistance("500米");
					if(i==2){
						catering.setTitle("河南永和铂爵国际酒店");
						catering.setShortInfo("酒店完美地融合了亚洲待客之道中的体贴、热情和真诚，以极其专业的高服务水准营造充满温");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel3);
						catering.setDistance("300米");
					}
					if(i==1){
						
						catering.setTitle("郑州悦莱时尚酒店");
						catering.setShortInfo("酒店设计时尚简约，全新配置中央空调、液晶电视、电话、时尚高档家俬、24小时热水、免费");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel2);
						catering.setDistance("200米");
					}
					if(i==4){
						catering.setTitle("郑州中油花园酒店");
						catering.setShortInfo("郑州中油花园酒店位于郑东新区CBD商务区，毗邻郑州国际会展中心、河南省艺术中心、如意湖");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel4);
						catering.setDistance("400米");
					}
					if(i==3){
						catering.setTitle("郑州建业艾美酒店");
						catering.setShortInfo("郑州建业艾美酒店坐落于中州大道 ，毗邻郑东新区和亚洲最大火车站新郑州东站，周边商业繁");
						catering.setPicUrl("drawable://" + R.drawable.pic_hotel1);
						catering.setDistance("100米");
					}
					catering.setRating(4);
					caterings.add(catering);
				
				}			
				
				replaceView = inflater.inflate(R.layout.normal_listview,null);
				listView = (ListView)  replaceView.findViewById(R.id.normal_listview);
				listAdapter = new CateringAdapter(myActivity, caterings);
				AnimationAdapter animAdapter = new ScaleInAnimationAdapter(listAdapter);
				animAdapter.setAbsListView(listView);
				animAdapter.setInitialDelayMillis(300);
				listView.setAdapter(animAdapter);
				listView.setAdapter(listAdapter);
				
				listView.setOnItemClickListener(new OnItemClickListener() {
					
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						System.out.println("点击了listview");
						Intent intent=new Intent(getActivity(), HotelDetail.class);
						startActivity(intent);
					}
				});
			}
			 ViewGroup parent = (ViewGroup) replaceView.getParent();  
		     if (parent != null) {  
		    	 parent.removeView(replaceView);  
		     }  
			fl.addView(replaceView);
			return fl;	
		}

		return fl;
	}	

}