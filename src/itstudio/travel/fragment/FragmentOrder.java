package itstudio.travel.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import itstudio.travel.R;
import itstudio.travel.adapter.OrderAdapter;
import itstudio.travel.entity.OrderBean;
/**
* @Description 订单Fragment

* @author MR.Wang

* @date 2014-7-5 上午1:12:06 

* @version V1.0
*/
public class FragmentOrder extends Fragment {

	
	//选择所有
	private Button btnSelectAll = null;

	//清除所有
	private Button btnDelete = null;

	//ListView列表
	private ListView lvListView = null;

	//适配对象
	private OrderAdapter adpAdapter = null;
	
	private Context mContext;
	View parentView;
	
	private static FragmentOrder singleton;

	public static FragmentOrder getInstance(){
		if(singleton==null){
			singleton=new FragmentOrder();
		}
		return singleton;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if(parentView==null){ 
			parentView = inflater.inflate(R.layout.fragment_order, container,
					false);
		}
		mContext = parentView.getContext();
		ViewGroup parent = (ViewGroup) parentView.getParent(); 
        if (parent != null) {  
            parent.removeView(parentView);  
        }  
        initView(parentView);
        initData();
		return parentView;
	}
	
	
	private void initView(View view) {

		btnDelete = (Button) view.findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(onClickListener);

		btnSelectAll = (Button) view.findViewById(R.id.btnSelectAll);
		btnSelectAll.setOnClickListener(onClickListener);

		lvListView = (ListView) view.findViewById(R.id.lvListView);
		//lvListView.setOnItemClickListener(onClickListener);

	}
	
	private void initData() {

		// 模拟假数据
		List<OrderBean> demoDatas = new ArrayList<OrderBean>();

		demoDatas.add(new OrderBean("5hours火锅", "drawable://" +R.drawable.pic_nearfoods1, 2, 22));
		demoDatas.add(new OrderBean("粥之缘", "drawable://" +R.drawable.pic_nearfoods2, 1, 18));
		demoDatas.add(new OrderBean("爱好之love创意蛋糕", "drawable://" +R.drawable.pic_nearfoods3, 3, 30));
		demoDatas.add(new OrderBean("方燕烤猪蹄", "drawable://" +R.drawable.pic_nearfoods4, 2, 35));

		adpAdapter = new OrderAdapter(getActivity(), demoDatas);

		lvListView.setAdapter(adpAdapter);

	}
	
	private void selectAll(){
		
		if (btnSelectAll.getText().toString().trim().equals("全选")) {

			// 所有项目全部选中
			adpAdapter.configCheckMap(true);

			adpAdapter.notifyDataSetChanged();

			btnSelectAll.setText("全不选");
		} else {

			// 所有项目全部不选中
			adpAdapter.configCheckMap(false);

			adpAdapter.notifyDataSetChanged();

			btnSelectAll.setText("全选");
		}

	}
	private void delete(){
		
		/*
		 * 删除算法最复杂,拿到checkBox选择寄存map
		 */
		Map<Integer, Boolean> map = adpAdapter.getCheckMap();

		// 获取当前的数据数量
		int count = adpAdapter.getCount();

		// 进行遍历
		for (int i = 0; i < count; i++) {

			// 因为List的特性,删除了2个item,则3变成2,所以这里要进行这样的换算,才能拿到删除后真正的position
			int position = i - (count - adpAdapter.getCount());

			if (map.get(i) != null && map.get(i)) {

				OrderBean bean = (OrderBean) adpAdapter.getItem(position);
				adpAdapter.getCheckMap().remove(i);
				adpAdapter.remove(position);

			}
		}

		adpAdapter.notifyDataSetChanged();

	
	}
	 private OnClickListener  onClickListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			
			case R.id.btnDelete:
				delete();
				break;
			case R.id.btnSelectAll:
				selectAll();
				break;

			default:
				break;
			}
		}
		 
	 };
}
