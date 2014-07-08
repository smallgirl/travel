package itstudio.travel.fragment;

import itstudio.travel.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
* @Description ’€ø€”ŒFragment

* @author MR.Wang

* @date 2014-7-5 …œŒÁ1:09:51 

* @version V1.0
 */
public class FragmentDiscount extends Fragment  {
	private View view;
	//
	private Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_teamtravel, container, false);
		context = inflater.getContext();
		
		return view;
	}
}
