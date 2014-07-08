
package itstudio.travel.fragment;
import itstudio.travel.R;
import itstudio.travel.widget.PagerSlidingTabStrip;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
* @Description 攻略FragmentActivity 

* @author MR.Wang

* @date 2014-7-5 上午9:40:10 

* @version V1.0
*/
@SuppressLint("NewApi")
public class TabStrategy extends FragmentActivity {

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_three_card);
		TextView titleName  = (TextView) findViewById(R.id.title_category);
		titleName.setText(R.string.gonglue);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs_signup);
		pager = (ViewPager) findViewById(R.id.pagers_signup);
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		tabs.setShouldExpand(true);
		tabs.setTypeface(Typeface.DEFAULT_BOLD,Typeface.ITALIC);
		tabs.setIndicatorColor(Color.parseColor("#99d64b"));
		tabs.setIndicatorHeight(9);
		tabs.setViewPager(pager);

	}
	
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { getResources().getString(R.string.hot_strategy),
				getResources().getString(R.string.recommen_strategy),
				getResources().getString(R.string.strategy_search)};

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public Fragment getItem(int position) {
			return TabFragmentStrategy.newInstance(position,TabStrategy.this);
		}

	}
	public void back(View view) {
		// TODO Auto-generated method stub
		finish();
	}

}