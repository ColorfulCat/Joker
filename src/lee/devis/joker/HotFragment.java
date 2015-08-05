package lee.devis.joker;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import lee.devis.joker.Entity.Joke;
import lee.devis.joker.Entity.Result;
import lee.devis.joker.HttpMethod.AsynTaskThread;
import lee.devis.joker.adapter.JokeListViewAdapter;
import lee.devis.joker.zrcrefreshview.SimpleFooter;
import lee.devis.joker.zrcrefreshview.SimpleHeader;
import lee.devis.joker.zrcrefreshview.ZrcListView;

import static lee.devis.joker.zrcrefreshview.ZrcListView.OnStartListener;

public class HotFragment extends BaseFragment {

	private OnFragmentInteractionListener mListener;

	private ZrcListView listView;
	private Handler handler;
	private JokeListViewAdapter adapter;

	private List<Joke> jokes;
	private int currentPage = 1;

	private ImageLoader imageLoader;

	public HotFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("TTT", "Fragment onCreateView");
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_newest, container, false);

		listView = (ZrcListView) view.findViewById(R.id.zListView);
		handler = new Handler();

		imageLoader = ImageLoader.getInstance();
		// 设置默认偏移量，主要用于实现透明标题栏功能。
		// float density = getResources().getDisplayMetrics().density;
		// listView.setFirstTopOffset((int) (50 * density));

		// 设置下拉刷新的样式
		SimpleHeader header = new SimpleHeader(getActivity());
		header.setTextColor(0xff0066aa);
		header.setCircleColor(0xff33bbee);
		listView.setHeadable(header);

		// 设置加载更多的样式
		SimpleFooter footer = new SimpleFooter(getActivity());
		footer.setCircleColor(0xff33bbee);
		listView.setFootable(footer);

		// 设置列表项出现动画
		listView.setItemAnimForTopIn(R.anim.topitem_in);
		listView.setItemAnimForBottomIn(R.anim.bottomitem_in);

		// 下拉刷新事件回调
		listView.setOnRefreshStartListener(new OnStartListener() {

			@Override
			public void onStart() {
				refresh();
			}
		});

		// 加载更多事件回调
		listView.setOnLoadMoreStartListener(new OnStartListener() {
			@Override
			public void onStart() {
				loadMore();
			}
		});

		// listView.setOnScrollListener(new PauseOnScrollListener(imageLoader,
		// true, true));

		// Bug，当ZListView为没有数据时，无法下位刷新。建议当无数据的加载使用loading界面、无数据界面。
		jokes = new ArrayList<Joke>();
		Joke joke = new Joke();
		jokes.add(joke);

		adapter = new JokeListViewAdapter(getActivity(), jokes);
		listView.setAdapter(adapter);
		listView.refresh(); // 主动下拉刷新

		return view;
	}

	private Handler myHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case R.id.tag_get_hot:
				Log.i("TTT", "myHandler  tag_get_hot");
				Result result = (Result) msg.obj;
				if (result != null) {
					List<Joke> list = (List<Joke>) result.getItems();
					if (list != null && list.size() > 0) {
						if (currentPage == 1) {
							jokes.clear();
						}
						jokes.addAll(list);
						adapter.notifyDataSetChanged();
						listView.setRefreshSuccess("刚刚更新"); // 通知加载成功
						listView.startLoadMore(); // 开启LoadingMore功能
					} else {
						Log.e("TTT", "error myHandler1");
						jokes.add(new Joke());
						adapter.notifyDataSetChanged();
						listView.setRefreshFail("没有更新");
					}
				} else {
					Log.e("TTT", "error myHandler2");
					jokes.add(new Joke());
					adapter.notifyDataSetChanged();
					listView.setRefreshFail("更新失败");
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (mListener != null)
			mListener = null;
		if (handler != null)
			handler.removeCallbacksAndMessages(handler);
	}

	@Override
	public void onDestroyView() {
		if (jokes != null) {
			jokes.clear();
			if (adapter != null && listView != null) {
				adapter.notifyDataSetChanged();
				listView = null;
				adapter = null;
			}
		}
		if (myHandler != null) {
			myHandler.removeCallbacksAndMessages(myHandler);
			myHandler = null;
		}
		if (handler != null) {
			handler.removeCallbacksAndMessages(handler);
			handler = null;
		}
		super.onDestroyView();
	}

	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

	private void refresh() {
		jokes.clear();
		currentPage = 1;
		new AsynTaskThread(myHandler, R.id.tag_get_hot, currentPage).execute();
	}

	private void loadMore() {
		new AsynTaskThread(myHandler, R.id.tag_get_hot, ++currentPage).execute();
	}

}
