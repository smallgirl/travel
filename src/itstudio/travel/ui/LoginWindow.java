package itstudio.travel.ui;


import itstudio.travel.R;
import itstudio.travel.config.Constants;
import itstudio.travel.widget.ProgressWheel;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class LoginWindow extends PopupWindow {

	private View popview;
	private View backBtnLayout;
	private Button loginBtn;
	boolean isLogin = false;
	LoginAsyncTask loginAsyncTask;
	ProgressWheel progressWheel;
	private TextView nameTv;
	private EditText user_name;
	private EditText user_psw;
	private Activity context;
	
	InputMethodManager inputMethodManager;

	public LoginWindow(final Activity context,int width,View rootView) {
		super(context);
		this.context =context;

		popview = LayoutInflater.from(context).inflate(R.layout.fragmen_login_, null);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int screenHeight = wm.getDefaultDisplay().getHeight();//屏幕高度
		Rect frame = new Rect();  
		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);  
		int stateHeight = frame.top;// 状态栏的高度  
		
		//set PopupWindow style 
		this.setBackgroundDrawable(new BitmapDrawable());
		this.setFocusable(true); // 设置PopupWindow可获得焦点
		this.setTouchable(true); // 设置PopupWindow可触摸
		this.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸
		this.setContentView(popview);
		this.setWidth(width);
		this.setHeight(screenHeight-stateHeight);
		this.setAnimationStyle(R.style.PopupAnimation);	//设置 popupWindow 动画样式
		
		inputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// initview setOnClickListener
		
		backBtnLayout = popview.findViewById(R.id.login_backBtnLayout);
		backBtnLayout.setOnClickListener(LoginListener);
		loginBtn = (Button) popview.findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(LoginListener);
		nameTv= (TextView) rootView.findViewById(R.id.nameTextView);
		user_name = (EditText) popview.findViewById(R.id.user_name);
		user_psw = (EditText) popview.findViewById(R.id.user_psw);
		progressWheel = (ProgressWheel) popview.findViewById(R.id.progressBarFour);
		
		LinearLayout linear = (LinearLayout) popview.findViewById(R.id.linear);
		linear.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_bottom_in);
		animation.setDuration(400);
		linear.startAnimation(animation);
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
				
				inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				View mRoot = (LinearLayout) popview.findViewById(R.id.root);  
				Button mSubmit = (Button) popview.findViewById(R.id.login_btn);  
			}
		});
		popview.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				popview.setFocusable(true);
				popview.setFocusableInTouchMode(true);
				popview.requestFocus();
				if (inputMethodManager.isActive()) { 
					 //如果开启 
					//inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS); 
					 //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的 
					
					inputMethodManager.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
					} 
				return false;
			}});


	}
	//为弹出窗口实现监听类
    private OnClickListener  LoginListener = new OnClickListener(){
    	
    	public void onClick(View v) {
    		switch (v.getId()) {
    		case R.id.login_backBtnLayout:
    			if(isShowing()){
    				dismiss();
    			}
    			break;
    		case R.id.login_btn:
    			login(v);
    			break;
    		default:
    			break;
    		}
    	}
    };
    
    
	public void login(View view) {
		// TODO Auto-generated method stub}
		if (inputMethodManager.isActive()) { 
			 //如果开启 
			System.out.println("hhhhhhhhhhhhhhhhhhh");
			inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS); 
			 //关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的 
			} 

		popview.setFocusable(true);
		popview.setFocusableInTouchMode(true);
		popview.requestFocus();
				
		if(loginAsyncTask==null){
			loginAsyncTask= new LoginAsyncTask();
		}
		if(isLogin==false){
			progressWheel.spin();
			loginBtn.setText("登录中...");
			loginAsyncTask = new LoginAsyncTask();
			loginAsyncTask.execute();
			isLogin = true;
		}
		else{
			progressWheel.stopSpinning();
			loginBtn.setText("登录");
			loginAsyncTask.cancel(true);
			isLogin = false;
		}

	}
    
	private class LoginAsyncTask extends AsyncTask<Object, Integer, Integer>{

		@Override
		protected Integer doInBackground(Object... params) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(2000);
				if("".equals(user_name.getText().toString())||"".equals(user_psw.getText().toString())){
					return 0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		protected void onPostExecute(Integer result){
			if(result==1){
				Constants.isLogin = true;
				Constants.account=user_name.getText().toString();
				nameTv.setText(Constants.account);
				dismiss();
			}
			
			if(result==0){
				progressWheel.stopSpinning();
				Toast.makeText(context, R.string.login_error, Toast.LENGTH_SHORT).show();
				loginBtn.setText("登录");
			}
			
		}
		
	}
	
}
