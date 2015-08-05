package lee.devis.joker.HttpMethod;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import lee.devis.joker.R;

public class ImageAsyncTask extends AsyncTask<String, String, Bitmap> {

	private ImageView mImageView;

	public ImageAsyncTask(ImageView imageView) {
		super();
		this.mImageView = imageView;
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap bitmap = null;
		try {
			if (params[0] != null) {
				Log.i("TTT", "doInBackground " + params[0]);
				bitmap = getImage(params[0]);// 根据每个URL分别下载网络图片存入bitmap数组中
			} else {

			}
			return bitmap;
		} catch (Exception e) {
			Log.i("TTT", "doInBackground error");
			return null;
		}
	}

	public Bitmap getImage(String path) throws Exception {
		URL myFileUrl;
		Bitmap bitmap;
		try {
			myFileUrl = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
            return null;
		}
		try {
			// 访问URL指向的网络
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
//			conn.setConnectTimeout(8000);
			conn.connect();
			// 把获取的流转换成BITMAP
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
            return null;
		}
		return bitmap; // 返回一个bitmap
	}

	@Override
	protected void onPostExecute(Bitmap result) {
//		super.onPostExecute(result);
		Log.e("TTTT", "onPostExecute");
		if (result != null) {
			mImageView.setImageBitmap(result);
		} else {
			mImageView.setImageResource(R.drawable.ic_launcher);
		}
	}

}
