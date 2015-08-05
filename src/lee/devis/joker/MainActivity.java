package lee.devis.joker;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.util.ArrayList;

import lee.devis.joker.MyView.PathView;
import lee.devis.joker.utility.MyToaster;

public class MainActivity extends FragmentActivity implements NewestFragment.OnFragmentInteractionListener,
		HotFragment.OnFragmentInteractionListener, PictureFragment.OnFragmentInteractionListener,
		lee.devis.joker.MyView.PathView.OnItemClickListener {

	// 页面列表
	private ArrayList<android.support.v4.app.Fragment> fragmentList;
	// 标题列表
	ArrayList<String> titleList = new ArrayList<String>();

	private PagerTitleStrip pagerTitleStrip;

	private long myTime;// 两次返回的间隔

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

		/* 通过pagerTabStrip可以设置标题的属性 */
		PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
		// 设置下划线的颜色
		// pagerTabStrip.setTabIndicatorColor(getResources().getColor(android.R.color.holo_green_dark));
		pagerTabStrip.setTabIndicatorColor(Color.RED);
		pagerTabStrip.setPadding(18, 18, 18, 18);
		// 文字颜色
		pagerTabStrip.setTextColor(Color.WHITE);
		// 设置背景的颜色
		pagerTabStrip.setBackgroundColor(Color.BLACK);

		// pagerTitleStrip=(PagerTitleStrip) findViewById(R.id.pagertab);
		// //设置背景的颜色
		// pagerTitleStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

		NewestFragment newestFragment = new NewestFragment();
		HotFragment hotFragment = new HotFragment();
		PictureFragment pictureFragment = new PictureFragment();

		fragmentList = new ArrayList<android.support.v4.app.Fragment>();
		fragmentList.add(newestFragment);
		fragmentList.add(hotFragment);
		fragmentList.add(pictureFragment);

		titleList.add("最新糗事");
		titleList.add("最热段子");
		titleList.add("图说真相");
		FragmentManager fragmentManager = getSupportFragmentManager();
		viewPager.setAdapter(new MyViewPagerAdapter(fragmentManager));
		viewPager.setOffscreenPageLimit(3);

		setupView();
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragmentList.get(arg0);
		}

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return titleList.get(position);
		}
	}

	// 双击退出
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if ((System.currentTimeMillis() - myTime) > 2000) {// 两次按下的时间间隔如果小于2秒就退出，大于2秒就再次提示
				MyToaster.showToast(this, "再按一次返回键退出程序");
				myTime = System.currentTimeMillis();// 获取当前系统时间

			} else {
				MyToaster.showToast(this, "客观慢走，欢迎再来哟~");
				finish();// 退出应用
				// System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	// path菜单初始化
	private void setupView() {
		PathView mPathView = (PathView) findViewById(R.id.mPathView);
		ImageButton startMenu = new ImageButton(this);
		startMenu.setBackgroundResource(R.drawable.start_menu_btn);
		mPathView.setStartMenu(startMenu);

		int[] drawableIds = new int[] { R.drawable.menu_contact, R.drawable.menu_background, R.drawable.menu_native,
				R.drawable.menu_net };
		View[] items = new View[drawableIds.length];
		for (int i = 0; i < drawableIds.length; i++) {
			ImageButton button = new ImageButton(this);
			button.setBackgroundResource(drawableIds[i]);
			items[i] = button;
		}
		mPathView.setItems(items);
		mPathView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(View view, int position) {

	}
}
