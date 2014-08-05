package itstudio.travel.fragment;

import java.util.ArrayList;
import java.util.List;
import itstudio.travel.config.Constants;
import itstudio.travel.sortlistview.ChooseCityActivity;
import itstudio.travel.ui.LoginActivity;
import itstudio.travel.ui.MainActivity;
import itstudio.travel.ui.MenuWindow;
import itstudio.travel.widget.CustomScrollView;
import itstudio.travel.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
* @Description 侧滑左Fragment

* @author MR.Wang

* @date 2014-7-5 上午1:13:57 

* @version V1.0
*/

public class LeftSlidingMenuFragment extends Fragment implements
		OnClickListener {

	private View homeBtnLayout;
	private View personBtnLayout;
	private View teamBtnLayout;
	private View discountBtnLayout;
	private View fineBtnLayout;
	private View messageBtnLayout;
	private View orderBtnLayout;
	private View commentBtnLayout;
	private View collectBtnLayout;
	private View settingBtnLayout;
	
	private View currentView;			//当当前整个View
	private View headImageView;			// 左侧图像 ImageView
	private TextView cityTv; 	// 左侧改变地址  textView
	private TextView nameTv; 			// 左侧名字 textView
	private TextView cityTvTitle;		// 顶部地点 textView
	private TextView titleTv;		// 顶部栏目标题 textView
	View parentView;
	
	private List<String > imgUrl = new ArrayList<String>();
	private PopupWindow popupWindow;
	private CustomScrollView mScrollView = null;
	private ImageView mBackgroundImageView = null;
	
	public static final int OPERATION_CHOOSE_CITY = 0;
	public static final int RESULT_OK = 1;
	public static final int RESULT_NONE = 2;
	
	public LeftSlidingMenuFragment() {
		// TODO Auto-generated constructor stub
		imgUrl.add("drawable://" + R.drawable.pic_slide_1);
		imgUrl.add("drawable://" + R.drawable.pic_slide_2);
		imgUrl.add("drawable://" + R.drawable.pic_slide_3);
		imgUrl.add("drawable://" + R.drawable.pic_slide_4);
		imgUrl.add("drawable://" + R.drawable.pic_slide_5);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.main_left_fragment, container,
				false);
		
		findViewById();
		initView();
		return parentView;
	}
	
	protected void findViewById() {
		mBackgroundImageView = (ImageView)  parentView.findViewById(R.id.personal_background_image);
		mScrollView = (CustomScrollView)  parentView.findViewById(R.id.personal_scrollView);
		
		mScrollView.setImageView(mBackgroundImageView);
		
		homeBtnLayout = parentView.findViewById(R.id.homeBtnLayout);
		personBtnLayout = parentView.findViewById(R.id.personBtnLayout);
		teamBtnLayout = parentView.findViewById(R.id.teamBtnLayout);
		discountBtnLayout = parentView.findViewById(R.id.discountBtnLayout);
		fineBtnLayout = parentView.findViewById(R.id.fineBtnLayout);
		messageBtnLayout = parentView.findViewById(R.id.messageBtnLayout);
		orderBtnLayout = parentView.findViewById(R.id.orderBtnLayout);
		commentBtnLayout = parentView.findViewById(R.id.commentBtnLayout);
		collectBtnLayout = parentView.findViewById(R.id.collectBtnLayout);
		settingBtnLayout = parentView.findViewById(R.id.settingBtnLayout);
		
		cityTv = (TextView) parentView.findViewById(R.id.city_change);
		headImageView = parentView.findViewById(R.id.headImageView);
		nameTv= (TextView) parentView.findViewById(R.id.nameTextView);
	}
	protected void initView() {
		// TODO Auto-generated method stub
		
		mScrollView.setImageView(mBackgroundImageView);
		cityTv.setOnClickListener(this);
		homeBtnLayout.setOnClickListener(this);
		personBtnLayout.setOnClickListener(this);
		teamBtnLayout.setOnClickListener(this);
		discountBtnLayout.setOnClickListener(this);
		fineBtnLayout.setOnClickListener(this);
		messageBtnLayout.setOnClickListener(this);
		orderBtnLayout.setOnClickListener(this);
		commentBtnLayout.setOnClickListener(this);
		collectBtnLayout.setOnClickListener(this);
		settingBtnLayout.setOnClickListener(this);
		
		//登陆view 
		headImageView.setOnClickListener(this);
		
		cityTvTitle = MainActivity.mTextView;
		titleTv = MainActivity.titleTextView ;
		currentView = homeBtnLayout;
		currentView.setSelected(true);
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		switch (v.getId()) {
		case R.id.homeBtnLayout:
			newContent = FragmentHome.getInstance(getActivity());
			titleTv.setText(R.string.home);
			currentView.setSelected(false);
			currentView =homeBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.personBtnLayout:
			newContent = FragmentPerson.getInstance();
			titleTv.setText(R.string.gerenyou);
			currentView.setSelected(false);
			currentView =personBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.teamBtnLayout:
			//App.LEFT_MENU_PAGE = 1;
			newContent = new FragmentTeamTravel();
			titleTv.setText(R.string.tuanyou);
			currentView.setSelected(false);
			currentView =teamBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.discountBtnLayout:
			newContent = new FragmentDiscount();
			titleTv.setText(R.string.tehuiyou);
			currentView.setSelected(false);
			currentView =discountBtnLayout;
			currentView.setSelected(true);
			break;

		case R.id.fineBtnLayout:
			newContent = FragmentFine.getInstance();
			titleTv.setText(R.string.jinpingyou);
			currentView.setSelected(false);
			currentView =fineBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.messageBtnLayout:
			newContent = FragmentMesss.getInstance();
			titleTv.setText(R.string.message_center);
			currentView.setSelected(false);
			currentView =messageBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.orderBtnLayout:
			newContent = FragmentOrder.getInstance();
			titleTv.setText(R.string.order);
			currentView.setSelected(false);
			currentView =orderBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.commentBtnLayout:
			newContent = FragmentComment.getInstance();
			titleTv.setText(R.string.comment);
			currentView.setSelected(false);
			currentView =commentBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.collectBtnLayout:
			newContent = FragmentCollect.getInstance();
			titleTv.setText(R.string.collect);
			currentView.setSelected(false);
			currentView =collectBtnLayout;
			currentView.setSelected(true);
			break;
			
		case R.id.settingBtnLayout:
			View rootView = getActivity().findViewById(R.id.llRoot);
			popupWindow = new MenuWindow(getActivity(),popuwindowOnClick,rootView.getWidth()+24);
			//设置layout在PopupWindow中显示的位置
			popupWindow.showAtLocation(getActivity().findViewById(R.id.collectBtnLayout), Gravity.BOTTOM|Gravity.LEFT, 0, 0); 
			break;
			
		case R.id.headImageView:
			if(!Constants.isLogin){
				rootView = getActivity().findViewById(R.id.llRoot);
				Intent intent = new Intent();
				intent.setClass(getActivity(), LoginActivity.class);
				intent.putExtra("width", rootView.getWidth()+24);
				startActivity(intent);
			}
			break;
			
		case R.id.backBtnLayout:
			if(popupWindow.isShowing()){
				
				popupWindow.dismiss();
			}
			break;
		case R.id.city_change:
			rootView = getActivity().findViewById(R.id.llRoot);
			Intent intent = new Intent ();
			intent.setClass(getActivity(), ChooseCityActivity.class);
			startActivityForResult(intent, OPERATION_CHOOSE_CITY);
			break;
		default:
			break;
		}

		if (newContent != null)
			switchFragment(newContent);

	}

	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		MainActivity ra = (MainActivity) getActivity();
		ra.switchContent(fragment);
	}
	//为弹出窗口实现监听类 
    private OnClickListener  popuwindowOnClick = new OnClickListener(){

		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.backBtnLayout:
				if(popupWindow.isShowing()){
					popupWindow.dismiss();
				}
    			break;
			case R.id.logout_btn:
				v.setVisibility(View.GONE);
				Constants.isLogin= false;
				nameTv.setText(R.string.login_hint);
				break;
			default:
				break;
			}
		}
    };
    @Override
    public void onResume() {
    	super.onResume();
    	if(Constants.isLogin==true){
    		nameTv.setText(Constants.account);
    	}
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	switch (resultCode) {
		case RESULT_OK:
			String city = data.getStringExtra("city");
			cityTvTitle.setText(city);
			cityTv .setText(city);
			break;

		default:
			break;
		}
    }
}
