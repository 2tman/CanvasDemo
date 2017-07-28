package club.iandroid.canvasdemo;

import android.app.Application;

import club.iandroid.sevenchartslib.utils.LogUtils;
import club.iandroid.sevenchartslib.utils.Utils;

/**
 * Created by 加荣 on 2017/7/28.
 */

public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LogUtils.init(this);
    }
}
