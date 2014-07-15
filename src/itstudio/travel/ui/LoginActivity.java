package itstudio.travel.ui;

import itstudio.travel.R;
import itstudio.travel.config.Constants;
import itstudio.travel.widget.InputMethodRelativeLayout;
import itstudio.travel.widget.InputMethodRelativeLayout.OnSizeChangedListenner;
import itstudio.travel.widget.ProgressWheel;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity  implements OnSizeChangedListenner{
	
	private View rootView;
	private View backBtnLayout;
	private Button loginBtn;
	boolean isLogin = false;
	LoginAsyncTask loginAsyncTask;
	ProgressWheel progressWheel;
	private EditText user_name;
	private EditText user_psw;
	private View boot;
	private InputMethodRelativeLayout layout;  
	InputMethodManager inputMethodManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int width = intent.getIntExtra("width", 180);
        getWindow().setGravity(Gravity.LEFT); 		//设置靠右对齐
		setContentView(R.layout.fragmen_login_);
	    //窗口高度  
		getWindow().setLayout(width, LayoutParams.MATCH_PARENT);
		initview();
	}

	private void initview() {
		// TODO Auto-generated method stub
		
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		rootView = findViewById(R.id.root);
		backBtnLayout =  findViewById(R.id.login_backBtnLayout);
		backBtnLayout.setOnClickListener(LoginListener);
		loginBtn = (Button)  findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(LoginListener);
		user_name = (EditText) findViewById(R.id.user_name);
		user_psw = (EditText) findViewById(R.id.user_psw);
		progressWheel = (ProgressWheel)  findViewById(R.id.progressBarFour);
		
		LinearLayout linear = (LinearLayout)  findViewById(R.id.linear);
		linear.setVisibility(View.VISIBLE);
		layout = (InputMethodRelativeLayout) findViewById(R.id.root);
		layout.setOnSizeChangedListenner(this) ;
        //取得找回密码和新注册布局
        boot = (LinearLayout) this.findViewById(R.id.reg_and_forget_password_layout2) ;
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_bottom_in);
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
			}
		});
		rootView.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				rootView.setFocusable(true);
				rootView.setFocusableInTouchMode(true);
				rootView.requestFocus();
				inputMethodManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(),
						InputMethodManager.HIDE_NOT_ALWAYS);
				return false;
			}});
		
	}
	//为弹出窗口实现监听类
    private OnClickListener  LoginListener = new OnClickListener(){
    	
    	public void onClick(View v) {
    		switch (v.getId()) {
    		case R.id.login_backBtnLayout:
    			inputMethodManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(),
    					InputMethodManager.HIDE_NOT_ALWAYS);
    			finish();
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

		inputMethodManager.hideSoftInputFromWindow(LoginActivity.this.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);

		rootView.setFocusable(true);
		rootView.setFocusableInTouchMode(true);
		rootView.requestFocus();
				
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
				finish();
			}
			
			if(result==0){
				progressWheel.stopSpinning();
				Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
				loginBtn.setText("登录");
			}
			
		}
		
	}
	@Override
	public void onSizeChange(boolean flag,int w ,int h) {  
        if(flag){//键盘弹出时
           boot.setVisibility(View.GONE) ;
        }else{ //键盘隐藏时
           boot.setVisibility(View.VISIBLE) ;
        }
    }  

}
