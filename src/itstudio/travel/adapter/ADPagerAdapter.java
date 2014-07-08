package itstudio.travel.adapter;

import itstudio.travel.R;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

/**
 *  @author miss
 */
public class ADPagerAdapter extends PagerAdapter {
	private Context context;
	private  List<String> adImageUrl;
	DisplayImageOptions options;
	private ImageLoader imageLoader;
	
	public ADPagerAdapter( List<String> adImageUrl, Context context) {
		this.context = context;
		this.adImageUrl = adImageUrl;
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.big_bg)
		.showImageForEmptyUri(R.drawable.big_bg_bad)
		.showImageOnFail(R.drawable.big_bg_bad).cacheInMemory(true)
		.cacheOnDisc(true).considerExifParams(true)
		.displayer(new FadeInBitmapDisplayer(200))
		.bitmapConfig(Bitmap.Config.ALPHA_8).build();
	};

	@Override
	public int getCount() {
		return adImageUrl.size();
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {

		
		imageLoader = ImageLoader.getInstance();
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		imageLoader.displayImage(adImageUrl.get(arg1),imageView, options, new SimpleImageLoadingListener());
		
		imageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, R.string.wait_none, Toast.LENGTH_SHORT).show();
			}
		});

		((ViewPager) arg0).addView(imageView);

		return imageView;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		ImageView image = (ImageView) arg2;
		image.setImageBitmap(null);

		((ViewPager) arg0).removeView(image);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}