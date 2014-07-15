package itstudio.travel.adapter;
import java.io.File;
import java.util.List;
import itstudio.travel.R;
import itstudio.travel.entity.Catering;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
* @Description: 餐饮Adpter

* @author MR.Wang

* @date 2014-7-5 上午9:28:31 

* @version V1.0
*/
public class CateringAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	DisplayImageOptions options;
	private ImageLoader imageLoader;
	private List<Catering> caterings;
	private Catering catering;

	public CateringAdapter(Context context,List<Catering> caterings) {
		this.mInflater = LayoutInflater.from(context);
		this.caterings = caterings;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.big_bg)
				.showImageForEmptyUri(R.drawable.big_bg_bad)
				.showImageOnFail(R.drawable.big_bg_bad)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.displayer(new RoundedBitmapDisplayer(8))	//设置图片角度,0为方形，360为圆角
				.bitmapConfig(Bitmap.Config.ALPHA_8).build();
		File cacheDir = StorageUtils.getCacheDirectory(context);
		System.out.println(cacheDir);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return caterings.size();
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
		catering = caterings.get(position);
		convertView = mInflater.inflate(R.layout.listview_item_nearfoods, null);
		holder.imageView = (ImageView) convertView.findViewById(R.id.food_img);
		holder.titleView = (TextView) convertView.findViewById(R.id.cater_title);
		holder.shortInfoView = (TextView) convertView.findViewById(R.id.cater_info);
		holder.distanceView = (TextView) convertView.findViewById(R.id.cater_distance);
		convertView.setTag(holder); 
		holder.titleView.setText(catering.getTitle());
		holder.shortInfoView.setText(catering.getShortInfo());
		holder.distanceView.setText(catering.getDistance());
		
		imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(catering.getPicUrl(),holder.imageView, options, new SimpleImageLoadingListener());
		
		return convertView;
	}

	private final class PicModelHodler {
		public ImageView imageView;
		public TextView titleView;
		public TextView shortInfoView;
		public TextView distanceView;
	}
}
