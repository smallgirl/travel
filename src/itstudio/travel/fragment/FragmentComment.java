package itstudio.travel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import itstudio.travel.R;

/**
* @Description 点评 Fragment 

* @author MR.Wang

* @date 2014-7-5 上午1:08:47 

* @version V1.0
*/
public class FragmentComment extends Fragment {

	private Context mContext;
	View parentView;
	
	private static FragmentComment singleton;

	public static FragmentComment getInstance(){
		if(singleton==null){
			singleton=new FragmentComment();
		}
		return singleton;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(parentView==null){ 
			parentView = inflater.inflate(R.layout.fragment_comment, container,
					false);
		}
		mContext = parentView.getContext();
		ViewGroup parent = (ViewGroup) parentView.getParent(); 
        if (parent != null) {  
            parent.removeView(parentView);  
        }  
		return parentView;
	}
}
