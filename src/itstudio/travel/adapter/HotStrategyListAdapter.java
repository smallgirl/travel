package itstudio.travel.adapter;

import itstudio.travel.R;
import itstudio.travel.entity.Strategy;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class HotStrategyListAdapter extends BaseAdapter {

	private final class ViewHolder {
		ImageView itemIcon;
		TextView itemDetail;
		TextView itemTitle;
		TextView itemDate;
		TextView itemDay;
	}

	private ArrayList<Strategy> strategyList;
	private Strategy strategy;
	DisplayImageOptions options;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	// 单行的布局
	private Context context;

	public HotStrategyListAdapter(Context context,
			ArrayList<Strategy> strategyList, DisplayImageOptions options) {

		this.context = context;
		this.strategyList = strategyList;
		this.options = options;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strategyList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		strategy = strategyList.get(position);

		holder = new ViewHolder();
		convertView = mInflater.inflate(R.layout.list_item_hot_strategy, null);
		holder.itemDay = (TextView) convertView
				.findViewById(R.id.hot_strategy_day);
		holder.itemDate = (TextView) convertView
				.findViewById(R.id.hot_strategy_date);
		holder.itemIcon = (ImageView) convertView
				.findViewById(R.id.hot_strategy_img);
		holder.itemTitle = (TextView) convertView
				.findViewById(R.id.hot_strategy_palce);
		holder.itemDetail = (TextView) convertView
				.findViewById(R.id.hot_strategy_palce_info);

		holder.itemDay.setText(strategy.getDay());
		holder.itemDate.setText(strategy.getDate());
		holder.itemTitle.setText(strategy.getTitle());
		holder.itemDetail.setText(strategy.getDetailUrl());
		imageLoader.displayImage(strategy.getPicUrl(), holder.itemIcon,
				options, new SimpleImageLoadingListener() {
					@Override
					public void onLoadingStarted(String imageUri, View view) {
						// holder.progressBar.setProgress(0);
						// holder.progressBar.setVisibility(View.VISIBLE);
					}

					@Override
					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
						// holder.progressBar.setVisibility(View.GONE);
					}

					@Override
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						// holder.progressBar.setVisibility(View.GONE);
					}
				}, new ImageLoadingProgressListener() {
					@Override
					public void onProgressUpdate(String imageUri, View view,
							int current, int total) {
						// holder.progressBar.setProgress(Math.round(100.0f *
						// current / total));
					}
				});
		return convertView;
	}

}
