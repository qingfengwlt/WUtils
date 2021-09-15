package pc.dialog.com;

import android.app.Application;

import pc.dialog.com.wutils.BuildConfig;
import pc.wlt.com.superlibrary.utils.L;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化日志管理
        L.isDebug= BuildConfig.DEBUG;
    }
}
