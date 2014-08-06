package itstudio.travel.adapter;

import itstudio.travel.R;
import itstudio.travel.entity.OrderBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {

	/**
	 * 上下文对象
	 */
	private Context context = null;

	private List<OrderBean> datas = null;
	DisplayImageOptions options;
	private ImageLoader imageLoader;
	
	/**
	 * CheckBox 是否选择的存储集合,key 是 position , value 是该position是否选中
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();
	

	public OrderAdapter(Context context, List<OrderBean> datas) {
		this.datas = datas;
		this.context = context;

		// 初始化,默认都没有选中
		configCheckMap(false);
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.big_bg)
		.showImageForEmptyUri(R.drawable.big_bg_bad)
		.showImageOnFail(R.drawable.big_bg_bad).cacheInMemory(true)
		.cacheOnDisc(true).considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(200))
		.bitmapConfig(Bitmap.Config.ALPHA_8).build();
		
		
		
	}

	/**
	 * 首先,默认情况下,所有项目都是没有选中的.这里进行初始化
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewGroup layout = null;

		/**
		 * 进行ListView 的优化
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.listview_item_order, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}

		OrderBean bean = datas.get(position);



		/*
		 * 设置每一个item的文本
		 */
		TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
		TextView tvPrice  = (TextView) layout.findViewById(R.id.price_tv);
		TextView tvNum  = (TextView) layout.findViewById(R.id.num_tv);
		ImageView food_img = (ImageView) layout.findViewById(R.id.food_img);
		tvTitle.setText(bean.getGoodsName());
		tvPrice.setText(bean.getPrice()+"");
		tvNum.setText(bean.getNum()+"");
		
		imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(bean.getImgUrl(),food_img, options, new SimpleImageLoadingListener());
		/*
		 * 获得单选按钮
		 */
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox2);

		/*
		 * 设置单选按钮的选中
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				/*
				 * 将选择项加载到map里面寄存
				 */
				isCheckMap.put(position, isChecked);
			}
		});


			cbCheck.setVisibility(View.VISIBLE);

			if (isCheckMap.get(position) == null) {
				isCheckMap.put(position, false);
			}

			cbCheck.setChecked(isCheckMap.get(position));

			ViewHolder holder = new ViewHolder();

			holder.cbCheck = cbCheck;

			holder.tvTitle = tvTitle;

			/**
			 * 将数据保存到tag
			 */
			layout.setTag(holder);
		

		return layout;
	}

	/**
	 * 增加一项的时候
	 */
	public void add(OrderBean bean) {
		this.datas.add(0, bean);

		// 让所有项目都为不选择
		configCheckMap(false);
	}

	// 移除一个项目的时候
	public void remove(int position) {
		this.datas.remove(position);
	}

	public Map<Integer, Boolean> getCheckMap() {
		return this.isCheckMap;
	}

	public static class ViewHolder {

		public TextView tvTitle = null;
		public TextView tvPrice = null;
		public TextView tvNum = null;
		public CheckBox cbCheck = null;
		public ImageView food_img = null;
		public Object data = null;

	}

	public List<OrderBean> getDatas() {
		return datas;
	}

}
