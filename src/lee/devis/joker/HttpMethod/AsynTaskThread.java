package lee.devis.joker.HttpMethod;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import lee.devis.joker.R;


/**
 * Description: 异步进程类
 * Created by Devis on 14-7-16.
 */
public class AsynTaskThread extends AsyncTask<Object, Integer, Object> {
    private Handler myHandler = null;
    private int myThreadTag = -1;
    private int myPage = -1;

    public AsynTaskThread(Handler handler, int threadTag, int page) {
        this.myHandler = handler;
        this.myThreadTag = threadTag;
        this.myPage = page;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Object object = null;
        String url = "";
        switch (myThreadTag) {
            case R.id.tag_get_newest://latest
                Log.e("TTTT", "myPage = " + myPage);
                url = "http://m2.qiushibaike.com/article/list/latest?count=20&page=" + myPage;

                object = JsonParser.getJokes(HttpGet.getResultString(url));
                break;
            case R.id.tag_get_hot:  // suggest
                Log.e("TTTT", "myPage = " + myPage);
                url = "http://m2.qiushibaike.com/article/list/suggest?count=20&page=" + myPage;

                object = JsonParser.getJokes(HttpGet.getResultString(url));
                break;
            case R.id.tag_get_picture://imgrank
                Log.e("TTTT", "myPage = " + myPage);
                url = "http://m2.qiushibaike.com/article/list/imgrank?count=20&page=" + myPage;

                object = JsonParser.getJokes(HttpGet.getResultString(url));

                break;
            default:
                break;
        }

        return object;
    }

    @Override
    protected void onPostExecute(Object object) {
        if(myHandler != null){
            Message message = myHandler.obtainMessage();
            message.what = myThreadTag;
            message.obj = object;
            myHandler.sendMessage(message);
        }
    }
}
