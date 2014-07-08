package itstudio.travel.fragment;

import itstudio.travel.adapter.ADPagerAdapter;
import itstudio.travel.adapter.HomeAdapter;
import itstudio.travel.entity.Recommend;
import itstudio.travel.ui.MainActivity;
import itstudio.travel.util.img.BitmapPicUtils;
import itstudio.travel.util.img.FixedSpeedScroller;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import itstudio.travel.R;
import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nhaarman.listviewanimations.swinginadapters.prepared.ScaleInAnimationAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**

* @Description 首页 Fragment

* @author MR.Wang

* @date 2014-7-5 上午12:32:06 

* @version V1.0
*/

@SuppressLint("ValidFragment")
public class FragmentHome extends Fragment  implements
AbsListView.OnItemClickListener {

	private View rootView;
	private ListView listView;
	private Context context;
	private ViewPager viewPager;
	private String[] titles;
	private int[] imageResId;
	private List<View> dots;
	private TextView tv_title;
	private int currentItem = 0;
	private ScheduledExecutorService scheduledExecutorService;
	private static double picScale;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private HomeAdapter adpater;
	private List<String> adImgUrl;// viewPaper广告位的 图片url
	private List<String > imgUrl;	// listview的 图片url
	private List<Recommend> recommends;
	private Recommend recommend ;
	
	//private List<String > titles;

	private static FragmentHome singleton;

	public static FragmentHome getInstance(Context context){
		if(singleton==null){
			singleton=new FragmentHome(context);
		}
		return singleton;
	}
	private FragmentHome(Context context) {
		this.context = context;
		imgUrl = new ArrayList<String>();
		imgUrl.add("drawable://" + R.drawable.pic_seckill_1);
		imgUrl.add("drawable://" + R.drawable.pic_slide_2);
		imgUrl.add(R.layout.listview_item_home_category+"");
		imgUrl.add("drawable://" + R.drawable.pic_slide_3);
		imgUrl.add("drawable://" + R.drawable.pic_slide_4);
		imgUrl.add("drawable://" + R.drawable.pic_slide_5);
		recommends = new ArrayList<Recommend>();
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.pic_seckill_1);
		recommend.setTitle("秒杀1");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.pic_slide_2);
		recommend.setTitle("秒杀2");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.pic_slide_3);
		recommend.setTitle("秒杀3");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl(R.layout.listview_item_home_category+"");
		recommend.setTitle("分割icon");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.pic_slide_4);
		recommend.setTitle("推荐1");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.pic_slide_5);
		recommend.setTitle("推荐1");
		recommends.add(recommend);
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		picScale = BitmapPicUtils.getPicScale(getResources(), R.drawable.pic_slide_1);
		imageResId = new int[] { R.drawable.pic_slide_5, R.drawable.pic_slide_1,
				R.drawable.pic_slide_2, R.drawable.pic_slide_3, R.drawable.pic_slide_4, };

		Context mContext = getActivity();
		
		titles = new String[imageResId.length];
		titles[0] = "丽江风水";
		titles[1] = "草堆";
		titles[2] = "亲亲草原";
		titles[3] = "城市风光";
		titles[4] = "蓝天白云";

		
		adImgUrl =new ArrayList<String>();
		for (int i = 0; i < imageResId.length; i++) {

			adImgUrl.add("drawable://" + imageResId[i]);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 if(rootView==null){ 
			 rootView = inflater.inflate(R.layout.fragment_home, container, false);
			 initListView(rootView);
		 }
		 ViewGroup parent = (ViewGroup) rootView.getParent();  
	     if (parent != null) {  
	    	 parent.removeView(rootView);  
	     }  
	     initView();
		return rootView;
	}

	/**
	 * 初始化布局文件中的控件
	 */

	private void initView() {
		View catering_img =rootView.findViewById(R.id.catering_img);
		catering_img.setOnClickListener(onClickListener);
		View strategy_img =rootView.findViewById(R.id.strategy_img);
		strategy_img.setOnClickListener(onClickListener);
		
	}
	 
    //为弹出窗口实现监听类
    private OnClickListener  onClickListener = new OnClickListener(){
    	
    	public void onClick(View v) {
    		Intent intent = new Intent();
    		
    		switch (v.getId()) {
    		
    		case R.id.catering_img:
	    		intent.setClass(getActivity(), TabCatering.class);
	    		startActivity(intent);
    			break;
    		
    		case R.id.strategy_img:
    			intent.setClass(getActivity(), TabStrategy.class);
    			startActivity(intent);
    			break;
    		default:
    			break;
    		}
    	}
    };
	private void initListView(View view) {
		// TODO Auto-generated method stub
		listView = (ListView) view.findViewById(R.id.list);
		LayoutInflater layoutInflater = getActivity().getLayoutInflater();
		View header = layoutInflater.inflate(R.layout.viewpaper_image,
				listView, false);
		
		dots = new ArrayList<View>();
		dots.add(header.findViewById(R.id.v_dot0));
		dots.add(header.findViewById(R.id.v_dot1));
		dots.add(header.findViewById(R.id.v_dot2));
		dots.add(header.findViewById(R.id.v_dot3));
		dots.add(header.findViewById(R.id.v_dot4));
		tv_title = (TextView) header.findViewById(R.id.tv_titles);
		tv_title.setText(titles[0]);//
		viewPager = (ViewPager) header.findViewById(R.id.vp);
		RelativeLayout.LayoutParams viewPaLayoutParams = new RelativeLayout.LayoutParams(
				MainActivity.screenWidthDip,
					(int) (MainActivity.screenWidthDip * picScale));
		viewPager.setLayoutParams(viewPaLayoutParams);
		setViewPagerScrollSpeed();
		viewPager.setAdapter(new ADPagerAdapter(adImgUrl,
				getActivity()));
		viewPager.setOnPageChangeListener(new MyPageChangeListener());
		adpater = new HomeAdapter(getActivity(), recommends, imageLoader);
		listView.addHeaderView(header);
		AnimationAdapter animAdapter = new ScaleInAnimationAdapter(adpater);
		animAdapter.setAbsListView(listView);
		animAdapter.setInitialDelayMillis(300);
		listView.setAdapter(animAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getActivity(), R.string.wait_none, Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub

			}
		});
		listView.setOnScrollListener((new PauseOnScrollListener(imageLoader,
				true, true)));
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
				currentItem = (currentItem + 1) % adImgUrl.size();
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
					tv_title.setText(titles[position]);
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



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
