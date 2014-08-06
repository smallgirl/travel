package itstudio.travel.fragment;

import itstudio.travel.R;
import itstudio.travel.adapter.ADPagerAdapter;
import itstudio.travel.adapter.CardsAnimationAdapter;
import itstudio.travel.adapter.HomeAdapter;
import itstudio.travel.entity.Recommend;
import itstudio.travel.ui.HotelActivity;
import itstudio.travel.ui.MainActivity;
import itstudio.travel.ui.SightPreviewActivity;
import itstudio.travel.util.FixedSpeedScroller;
import itstudio.travel.widget.MyListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhaarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;


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
	private MyListView listView;
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
	private List<Recommend> recommends;
	private Recommend recommend ;
	

	private static FragmentHome singleton;

	public static FragmentHome getInstance(Context context){
		if(singleton==null){
			singleton=new FragmentHome(context);
		}
		return singleton;
	}
	private FragmentHome(Context context) {
		this.context = context;

		recommends = new ArrayList<Recommend>();
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.icon_hot1);
		recommend.setTitle("河南省康辉旅行社团有限公司");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.icon_hot2);
		recommend.setTitle("河南省中州 国际旅行社");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.icon_hot3);
		recommend.setTitle("河南省新华飞扬国际旅行社");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.icon_hot4);
		recommend.setTitle("郑州铁路国际旅行社");
		recommends.add(recommend);
		
		recommend = new Recommend();
		recommend.setPicUrl("drawable://" + R.drawable.icon_hot5);
		recommend.setTitle("河南省中国青年旅行社第三营业部");
		recommends.add(recommend);
		
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		picScale = itstudio.travel.util.BitmapPicUtils.getPicScale(getResources(), R.drawable.pic_slide_1);
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
		return rootView;
	}

	 
    //为弹出窗口实现监听类
    private OnClickListener  onClickListener = new OnClickListener(){
    	
    	public void onClick(View v) {
    		Intent intent = new Intent();
    		
    		switch (v.getId()) {
    		

    		case R.id.strategy_img:
    			startZoomOutAnimations(v,TabStrategy.class);
    			break;

    		case R.id.preview_img:
    			startZoomOutAnimations(v,SightPreviewActivity.class);
    			break;
    			
    		case R.id.ticket_img:
    			startZoomOutAnimations(v,TabTicket.class);
    			break;

    		case R.id.route_img:
    			startZoomOutAnimations(v,TabRoute2.class);
    			break;
    			
		    case R.id.catering_img:
		    	startZoomOutAnimations(v,TabCatering.class);
		    	break;
		    	
		    case R.id.hotel_img:
		    	startZoomOutAnimations(v,HotelActivity.class);
		    	break;
		    	
		    case R.id.shopping_img:
		    	startZoomOutAnimations(v,TabCatering.class);
		    	break;
		    case R.id.weather_img:
		    	startZoomOutAnimations(v,TabCatering.class);
		    	break;
		    case R.id.home_jinpin:
		    	startZoomOutAnimations(v,TabTicket.class);
		    	break;
		    case R.id.home_tehui:
		    	startZoomOutAnimations(v,TabTicket.class);
		    	break;
		    case R.id.home_image1:
		    	startZoomOutAnimations(v,TabTicketDetail.class);
		    	break;
		    case R.id.home_image2:
		    	startZoomOutAnimations(v,TabTicketDetail.class);
		    	break;
    		default:
    			break;
    		}
    	}
    };
    
	/**
	 * 展现菜单动画效果
	 * 
	 * @param view
	 * @param runnable
	 */
	private void startZoomOutAnimations(final View view,final Class IntentClass) {
		
		AnimationSet anim=new AnimationSet(true); 
		anim.addAnimation(new ScaleAnimation(1F, 0.9F, 1F, 0.9F, 1, 0.5F, 1, 0.5F));
		anim.setDuration(50);
		anim.setFillAfter(true);
		anim.setAnimationListener(new AnimationListener() {
			
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
				view.clearAnimation();
				AnimationSet anim2=new AnimationSet(true); 
				anim2.addAnimation(new ScaleAnimation(0.9F, 1.0F, 0.9F, 1.0F, 1, 0.5F, 1, 0.5F));
				//anim2.addAnimation(new AlphaAnimation(1F, 0F));
				anim2.setDuration(150);
				anim2.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationEnd(Animation animation) {
						Intent intent = new Intent();
						intent.setClass(getActivity(), IntentClass);
						startActivity(intent);
					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}});
				view.startAnimation(anim2);
				// TODO Auto-generated method stub

				
				
			}
		});
		view.startAnimation(anim);
	}
	private void initListView(View view) {
		// TODO Auto-generated method stub
		listView = (MyListView) view.findViewById(R.id.list);
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
		adpater = new HomeAdapter(getActivity(), recommends);
		
		View catering_img =header.findViewById(R.id.catering_img);
		View preview_img=header.findViewById(R.id.preview_img);
		View ticket_img =header.findViewById(R.id.ticket_img);
		View route_img=header.findViewById(R.id.route_img);
		View strategy_img =header.findViewById(R.id.strategy_img);
		View hotel_img =header.findViewById(R.id.hotel_img);
		View shopping_img =header.findViewById(R.id.shopping_img);
		View weather_img =header.findViewById(R.id.weather_img);
		
		//特惠 精品
		ImageView iv_tehui=(ImageView) header.findViewById(R.id.home_tehui);
		ImageView iv_jipin=(ImageView) header.findViewById(R.id.home_jinpin);
		ImageView iv_image1=(ImageView) header.findViewById(R.id.home_image1);
		ImageView iv_image2=(ImageView) header.findViewById(R.id.home_image2);
		
		iv_jipin.setOnClickListener(onClickListener);
		iv_tehui.setOnClickListener(onClickListener);
		iv_image1.setOnClickListener(onClickListener);
		iv_image2.setOnClickListener(onClickListener);
		
		catering_img.setOnClickListener(onClickListener);
		preview_img.setOnClickListener(onClickListener);
		ticket_img.setOnClickListener(onClickListener);
		route_img.setOnClickListener(onClickListener);
		strategy_img.setOnClickListener(onClickListener);
		hotel_img.setOnClickListener(onClickListener);
		shopping_img.setOnClickListener(onClickListener);
		weather_img.setOnClickListener(onClickListener);
		
		
		listView.addHeaderView(header);
		
		AnimationAdapter animAdapter = new CardsAnimationAdapter (adpater);
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
				false, false)));
		
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
