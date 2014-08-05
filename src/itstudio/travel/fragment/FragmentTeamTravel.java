package itstudio.travel.fragment;

import itstudio.travel.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
* @Description 团游 Fragment

* @author MR.Wang

* @date 2014-7-5 上午1:13:26 

* @version V1.0
*/

public class FragmentTeamTravel extends Fragment  {

	//
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
