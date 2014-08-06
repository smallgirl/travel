package itstudio.travel.ui;

import itstudio.travel.R;
import itstudio.travel.fragment.TabHotel;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author miss
 * 
 */
public class HotelActivity extends Activity {
    

	private View backLayout;
	private Button searchButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_hotel);
		backLayout = findViewById(R.id.back_layout);
		backLayout.setOnClickListener(onClickListener);
		searchButton = (Button) findViewById(R.id.search_hotel);
		searchButton.setOnClickListener(onClickListener);
		
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
			case R.id.search_hotel:
				Intent intent = new Intent();
				intent.setClass(HotelActivity.this, TabHotel.class);
				startActivity(intent);
				break;

			default:
				break;
			}
			
		}
		  
	};

}
