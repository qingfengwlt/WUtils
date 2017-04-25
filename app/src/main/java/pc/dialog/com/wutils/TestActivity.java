package pc.dialog.com.wutils;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {


    private WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    private static WindowManager windowManager;
    private static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1、获取系统级别的WindowManager
        windowManager = (WindowManager) getApplication().getSystemService(WINDOW_SERVICE);
        //添加一个UI空间，作为悬浮窗的内容 ，当然Demo是一个ImageView作为悬浮窗内容，实际项目中就需要用复杂View,ViewGroup来扩展功能了
        // 判断UI控件是否存在，存在则移除，确保开启任意次应用都只有一个悬浮窗
        if (imageView != null){
            windowManager.removeView(imageView);
        }
        // 2、使用Application context 创建UI控件，避免Activity销毁导致上下文出现问题
        imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.mipmap.ic_launcher);


        // 3、设置系统级别的悬浮窗的参数，保证悬浮窗悬在手机桌面上
        // 系统级别需要指定type 属性
        // TYPE_SYSTEM_ALERT 允许接收事件
        // TYPE_SYSTEM_OVERLAY 悬浮在系统上
        // 注意清单文件添加权限

        //系统提示。它总是出现在应用程序窗口之上。
//        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT|WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
        lp.type |= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        lp.type |= WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;

        // FLAG_NOT_TOUCH_MODAL不阻塞事件传递到后面的窗口
        // FLAG_NOT_FOCUSABLE 悬浮窗口较小时，后面的应用图标由不可长按变为可长按,不设置这个flag的话，home页的划屏会有问题
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                |WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

        //悬浮窗默认显示的位置
        lp.gravity = Gravity.LEFT|Gravity.TOP;
        //显示位置与指定位置的相对位置差
        lp.x = 0;
        lp.y = 0;
        //悬浮窗的宽高
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置悬浮窗背景透明
        lp.format = PixelFormat.TRANSPARENT;
        windowManager.addView(imageView,lp);

        //设置悬浮窗监听事件
        imageView.setOnTouchListener(new View.OnTouchListener() {

            private float lastX; //上一次位置的X.Y坐标
            private float lastY;
            private float nowX;  //当前移动位置的X.Y坐标
            private float nowY;
            private float tranX; //悬浮窗移动位置的相对值
            private float tranY;

            private float lastX1; //上一次位置的X.Y坐标
            private float lastY1;
            private float nowX1;  //当前移动位置的X.Y坐标
            private float nowY1;

            private float tranX1; //悬浮窗移动位置的相对值
            private float tranY1;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean ret = false;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        // 获取按下时的X，Y坐标

                        lastX = event.getRawX();
                        lastY = event.getRawY();
                        lastX1 = event.getRawX();
                        lastY1 = event.getRawY();
                        ret = true;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 获取移动时的X，Y坐标
                        nowX = event.getRawX();
                        nowY = event.getRawY();
                        // 计算XY坐标偏移量
                        tranX = nowX - lastX;
                        tranY = nowY - lastY;
                        // 移动悬浮窗
                        lp.x += tranX;
                        lp.y += tranY;
                        //更新悬浮窗位置
                        windowManager.updateViewLayout(imageView,lp);
                        //记录当前坐标作为下一次计算的上一次移动的位置坐标
                        lastX = nowX;
                        lastY = nowY;
                        break;
                    case MotionEvent.ACTION_UP:
                        // 获取移动时的X，Y坐标
                        nowX1 = event.getRawX();
                        nowY1= event.getRawY();
                        // 计算XY坐标偏移量
                        tranX1 = nowX1 - lastX1;
                        tranY1 = nowY1 - lastY1;
                        if (Math.abs(tranX1) <6 &&Math.abs(tranY1)<6){
                            Toast.makeText(TestActivity.this,"onclick",Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        break;
                }
                return ret;
            }
        });

    }
}
