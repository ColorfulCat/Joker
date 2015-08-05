package lee.devis.joker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import lee.devis.joker.photoview.PhotoView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Description: Created by Devis on 14-7-17.
 */
public class ShowPicActivity extends Activity {
	private ImageView imageView;
	private ProgressBar spinner;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_showpic);

		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showImageOnLoading(null).showImageForEmptyUri(null)
				.showImageOnFail(null).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).displayer(new FadeInBitmapDisplayer(388)).build();
		String url = getIntent().getStringExtra("url");
		Log.e("TTT", "url = " + url);

		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		spinner = (ProgressBar) findViewById(R.id.loading);

		imageLoader.displayImage(url, imageView, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				spinner.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				String message = null;
				switch (failReason.getType()) {
				case IO_ERROR:
					message = "Input/Output error";
					break;
				case DECODING_ERROR:
					message = "Image can't be decoded";
					break;
				case NETWORK_DENIED:
					message = "Downloads are denied";
					break;
				case OUT_OF_MEMORY:
					message = "Out Of Memory error";
					break;
				case UNKNOWN:
					message = "Unknown error";
					break;
				}
				// Toast.makeText(ShowPicActivity.this, message,
				// Toast.LENGTH_SHORT).show();
				Log.e("TTTT", "error message = " + message);
				spinner.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				spinner.setVisibility(View.GONE);
				if (loadedImage != null) {
					ImageView imageView = (ImageView) view;
					imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
					boolean firstDisplay = !displayedImages.contains(imageUri);
					if (firstDisplay) {
						FadeInBitmapDisplayer.animate(imageView, 500);
						displayedImages.add(imageUri);
					}
				}
			}
		});
	}

	static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
