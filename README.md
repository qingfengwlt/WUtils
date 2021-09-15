# WUtils
工具类封装
1.内部存储Sharedpreferences的工具SharedP:简化了代码，用于存储个人信息；
2.ToastUtils：对应吐司进行了单例封装，解决Toast吐司的重复调用、弹起的不友好交互。
3.万能适配器CommonAdapter的封装，CommonViewHolder缓存处理，解决适配器的重复编写，降低耦合度
4. QRCodeUtil，二维码生产工具

L日志工具类（可写到手机里面,方便查看日志数据，请求的接口，埋点等等）
使用方式：
 @Override
    protected void onRestart() {
        super.onRestart();
        L.d(TAG,"onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        L.e(TAG,"onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i(TAG,"onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        L.v(TAG,"onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        L.d(TAG,"onStop");

    }
手机保存路径    /sdcard/"+"Log/"+yyyy-MM-ddLog.txt
