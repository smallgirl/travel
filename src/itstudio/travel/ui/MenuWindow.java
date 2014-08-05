package itstudio.travel.ui;

import itstudio.travel.R;
import itstudio.travel.config.Constants;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

public class MenuWindow extends PopupWindow {


	//private Button btn_take_photo, btn_pick_photo, btn_cancel;
	private View backBtnLayout;
	private Button logoutBtn;

	public MenuWindow(Activity context,OnClickListener itemsOnClick,int width) {
		super(context);
		
		View popview = LayoutInflater.from(context).inflate(R.layout.fragment_setting, null);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenHeight = wm.getDefaultDisplay().getHeight();//屏幕高度
		Rect frame = new Rect();  
		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
		int stateHeight = frame.top;// 状态栏的高度  
		//设置风格  
		this.setBackgroundDrawable(new BitmapDrawable());
		this.setFocusable(true); // 设置PopupWindow可获得焦点
		this.setTouchable(true); // 设置PopupWindow可触摸
		this.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
		this.setContentView(popview);
		this.setWidth(width);
		this.setHeight(screenHeight-stateHeight);
		this.setAnimationStyle(R.style.LeftRightAnimation);	//设置 popupWindow 动画样式
		
		backBtnLayout = popview.findViewById(R.id.backBtnLayout);
		backBtnLayout.setOnClickListener(itemsOnClick);
		logoutBtn= (Button) popview.findViewById(R.id.logout_btn);
		if(Constants.isLogin){
			logoutBtn.setVisibility(View.VISIBLE);
			logoutBtn.setOnClickListener(itemsOnClick);
		}
		else{
			logoutBtn.setVisibility(View.GONE);
		}

		

		popview.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				/*
				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y=(int) event.getY();
				if(event.getAction()==MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}		*/		
				return true;
			}
		});

	}

}
