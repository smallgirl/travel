package itstudio.travel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import itstudio.travel.R;

/**
* @Description 消息中心 Fragment

* @author MR.Wang

* @date 2014-7-5 上午1:11:22 

* @version V1.0
*/
public class FragmentMesss extends Fragment {

	private Context mContext;
	View parentView;
	
	private static FragmentMesss singleton;

	public static FragmentMesss getInstance(){
		if(singleton==null){
			singleton=new FragmentMesss();
		}
		return singleton;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(parentView==null){ 
			parentView = inflater.inflate(R.layout.fragment_message, container,
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
