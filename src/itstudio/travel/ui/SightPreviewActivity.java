package itstudio.travel.ui;

import itstudio.travel.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author miss
 * 
 */
public class SightPreviewActivity extends Activity {
    
	public Context mContext;
	private View backLayout; 
	private View quanjingImg; 
	private TextView intro; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_quanjing);
		backLayout = findViewById(R.id.back_layout);
		quanjingImg = findViewById(R.id.quanjing_img);
		intro = (TextView) findViewById(R.id.intro);
		backLayout.setOnClickListener(onClickListener);
		quanjingImg.setOnClickListener(onClickListener);
		SpannableStringBuilder style=new SpannableStringBuilder("景点简介:"+" 2001年建成，总建筑面积为18296平方米。欧风街借鉴了欧洲不同国家不同历史时期的建筑风格，创意组合为欧洲街景，汇成了一个别具特色的欧式建筑群。"); 
		style.setSpan(new AbsoluteSizeSpan(35), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		style.setSpan(new AbsoluteSizeSpan(30), 5, style.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		intro.setText(style);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	private OnClickListener  onClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.back_layout:
				finish();
				break;
			case R.id.quanjing_img:
				Intent intent = new Intent();
				intent.setClass(SightPreviewActivity.this, QuanjingActivity.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
		}
		  
	};

}
