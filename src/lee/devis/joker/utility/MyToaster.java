package lee.devis.joker.utility;

import android.content.Context;
import android.widget.Toast;

/**
 * @Type:MyToaster
 * @Description:Toast管理类  特点：避免toast以队列形式出现，实现实时更新。
 * @Author:Devis
 * @Date:2014-5-8
 */
public class MyToaster {

	private static Toast toast = null;

	private MyToaster() {
	}

	/******
	 * @MethodName: cancelToast
	 * @Description: 取消当前toast
	 * @Author: Libin
	 * @CreateDate: 2013-12-24
	 ******/
	public static void cancelToast() {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
	}

	/**
	 * @Method:showToast
	 * @Description:显示系统默认toast
	 * @Author:Devis
	 * @Date:2014-5-8
	 */
	public static void showToast(Context context, String string) {
		cancelToast();
		toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);//
		toast.show();
	}

	/**
	 * @Method:showToast
	 * @Description:显示系统默认toast 可改时间
	 * @Author:Devis
	 * @Date:2014-5-8
	 */
	public static void showToast(Context context, String string, int time) {
		cancelToast();
		toast = Toast.makeText(context, string, time);
		toast.show();
	}

}
