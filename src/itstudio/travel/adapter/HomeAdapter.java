package itstudio.travel.adapter;

import java.util.List;
import itstudio.travel.entity.Recommend;
import itstudio.travel.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater mInflater;
	DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private List<Recommend> recommends;
	private Recommend recommend ;

	public HomeAdapter(Context context, List<Recommend> recommends) {
		this.recommends = recommends;
		this.context = context;
		this.mInflater = LayoutInflater.from(context);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.big_bg)
				.showImageForEmptyUri(R.drawable.big_bg_bad)
				.showImageOnFail(R.drawable.big_bg_bad).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.displayer(new FadeInBitmapDisplayer(0))
				.bitmapConfig(Bitmap.Config.ALPHA_8).build();
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return recommends.size() ;
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
		recommend = recommends.get(position);
		holder = new PicModelHodler();
		convertView = mInflater.inflate(R.layout.listview_item_home, null);
		holder.imageView = (ImageView) convertView.findViewById(R.id.image_icon);
		holder.textView = (TextView) convertView.findViewById(R.id.agency_name);
		
		holder.textView.setText(recommend.getTitle());
		
		convertView.setTag(holder);
		imageLoader.displayImage(recommend.getPicUrl(),holder.imageView, options, new SimpleImageLoadingListener() {
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

	private final class PicModelHodler {
		public ImageView imageView;
		public TextView textView;
	}
}
