package itstudio.travel.adapter;

import java.util.List;

import itstudio.travel.R;
import itstudio.travel.entity.Strategy;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
* @Description 攻略Adpter

* @author MR.Wang

* @date 2014-7-5 上午8:16:42 

* @version V1.0
*/
public class StrategyAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	DisplayImageOptions options;
	private ImageLoader imageLoader;
	private List<Strategy> strategies;
	private Strategy strategy;

	public StrategyAdapter(Context context, List<Strategy> listStrategies) {
		this.strategies =  listStrategies;
		this.mInflater = LayoutInflater.from(context);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.big_bg)
				.showImageForEmptyUri(R.drawable.big_bg_bad)
				.showImageOnFail(R.drawable.big_bg_bad).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(false)
				.displayer(new RoundedBitmapDisplayer(0))	//设置图片角度,0为方形，360为圆角
				.bitmapConfig(Bitmap.Config.ALPHA_8).build();
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strategies.size() ;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		PicModelHodler holder = null;
		holder = new PicModelHodler();
		strategy = strategies.get(position);
		convertView = mInflater.inflate(R.layout.listview_item_strategy, null);
		holder.imageView = (ImageView) convertView.findViewById(R.id.strategy_img);
		holder.titleView = (TextView) convertView.findViewById(R.id.stgy_title);
		holder.authorView = (TextView) convertView.findViewById(R.id.stgy_author);
		convertView.setTag(holder); 
		holder.titleView.setText(strategy.getTitle());
		holder.authorView.setText(strategy.getAuthor());
		imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(strategy.getPicUrl(),holder.imageView, options, new SimpleImageLoadingListener());
		
		return convertView;
	}

	private final class PicModelHodler {
		public ImageView imageView;
		public TextView titleView;
		public TextView authorView;
	}
}
