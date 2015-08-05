package lee.devis.joker.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import lee.devis.joker.Entity.Joke;
import lee.devis.joker.Entity.User;
import lee.devis.joker.HttpMethod.ImageAsyncTask;
import lee.devis.joker.MyApplication;
import lee.devis.joker.R;
import lee.devis.joker.ShowPicActivity;

/**
 * Description:
 * Created by Devis on 14-7-17.
 */
public class JokeListViewAdapter extends BaseAdapter {

    private List<Joke> jokes;
    private Context context;
    private DisplayImageOptions options;
    private ImageLoader imageLoader;

    public JokeListViewAdapter(Context context, List<Joke> data) {
        this.jokes = data;
        this.context = context;
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(null)
                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(388))
                .build();
    }

    @Override
    public int getCount() {
        return jokes == null ? 0 : jokes.size();
    }

    @Override
    public Object getItem(int position) {
        return jokes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.joke_item, null);

            viewHolder = new ViewHolder();
            viewHolder.userNam = (TextView) convertView.findViewById(R.id.name);
            viewHolder.content = (TextView) convertView.findViewById(R.id.content);
            viewHolder.updateTime = (TextView) convertView.findViewById(R.id.time);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        User user = jokes.get(position).getUser();
        if (user != null) {
            viewHolder.userNam.setText(jokes.get(position).getUser().getLogin());
            if(user.getIcon() != null && !user.getIcon().equals("null")){

                String url  = "http://pic.qiushibaike.com/system/avtnew/"+user.getId().substring(0,user.getId().length()-4)+"/"+user.getId()+"/thumb/"+user.getIcon();
                Log.e("TTTT", "url = " +url);
            imageLoader.displayImage(url, viewHolder.icon, options, animateFirstListener);
//            new ImageAsyncTask(viewHolder.icon).execute(url);
            }
        } else {
            viewHolder.userNam.setText("匿名用户");
            viewHolder.icon.setImageResource(R.drawable.joke);
        }
        String imageUrl = jokes.get(position).getImage();
        if(imageUrl != null && imageUrl.length()>0 && !imageUrl.equals("null")){
            String id = jokes.get(position).getId();
            String url = "http://pic.qiushibaike.com/system/pictures/"+id.substring(0,id.length()-4)+"/"+id+"/small/"+imageUrl;
            final String bigUrl = "http://pic.qiushibaike.com/system/pictures/"+id.substring(0,id.length()-4)+"/"+id+"/medium/"+imageUrl;
            Log.e("TTTT", "url = " +url + "\n bigUrl = " + bigUrl);
            viewHolder.image.setVisibility(View.VISIBLE);
            imageLoader.displayImage(url, viewHolder.image, options, animateFirstListener);
            viewHolder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShowPicActivity.class);
                    intent.putExtra("url", bigUrl);
                    context.startActivity(intent);
                }
            });
//          new ImageAsyncTask(viewHolder.image).execute(url);
        }else{
            viewHolder.image.setVisibility(View.GONE);
        }
        String content = jokes.get(position).getContent();
        if(content!=null && content.length()>0){
            viewHolder.content.setText(content);
        }else{
            viewHolder.content.setText("您好像没有获取到数据哦！~");
        }

        String time = "更新于 "+jokes.get(position).getCreated_at();
        viewHolder.updateTime.setText(time);

        return convertView;
    }
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    class ViewHolder {
        private ImageView icon;
        private TextView userNam;
        private TextView content;
        private TextView updateTime;
        private ImageView image;

    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            super.onLoadingComplete(imageUri,view,loadedImage);
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                imageView.setVisibility(View.VISIBLE);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }else{
                ImageView imageView = (ImageView) view;
                imageView.setVisibility(View.GONE);
            }
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            super.onLoadingFailed(imageUri, view, failReason);
//            ImageView imageView = (ImageView) view;
//            imageView.setVisibility(View.GONE);
        }
    }
}