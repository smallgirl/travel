package itstudio.travel.fragment;

import itstudio.travel.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class FragmentGouwuche extends Activity{
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_item_nearfood_detail_gouwuche);
		mListView=(ListView) findViewById(R.id.list_dingdan);
	}

	
	
}
