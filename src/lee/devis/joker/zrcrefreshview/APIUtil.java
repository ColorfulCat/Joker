package lee.devis.joker.zrcrefreshview;

import android.os.Build;

public class APIUtil {
    public static boolean isSupport(int apiNo){
        return Build.VERSION.SDK_INT >= apiNo;
    }
}
