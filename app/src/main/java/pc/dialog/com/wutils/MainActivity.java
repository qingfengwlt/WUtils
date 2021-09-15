package pc.dialog.com.wutils;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pc.wlt.com.superlibrary.base.BaseActivity;
import pc.wlt.com.superlibrary.widget.MoveImageView;
import pc.wlt.com.superlibrary.widget.TitleBar;

public class MainActivity extends BaseActivity {

    private TitleBar mTitleBar;
    private MoveImageView mIv;
    private Button mBtn;
//    private TextView tv;
    @Override
    protected int resId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        mTitleBar= (TitleBar) findViewById(R.id.title_bar);
        mIv= (MoveImageView) findViewById(R.id.miv_home);
//        tv= (TextView) findViewById(R.id.textView);
        mBtn= (Button) findViewById(R.id.button);
//        initTitleBar(mTitleBar,"返回",R.drawable.back_green,"wlt");

    }

    @Override
    protected void initListener() {
        mTitleBar.setOnClickListener(this);
        mIv.setOnClickListener(this);
        mBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mTitleBar.setLeftText("返回");
        mTitleBar.setLeftImageResource(R.drawable.back_green);
        mTitleBar.setTitle("首页");
        mTitleBar.setTitleColor(R.color.white);
        mTitleBar.setLeftTextColor(R.color.white);
        mTitleBar.addAction(new TitleBar.ImageAction(R.drawable.collect) {
            @Override
            public void performAction(View view) {
                    openActivity(SettingActivity.class);
            }
        });
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.miv_home:
                    Toast.makeText(this,"Don't touch me ! leave me alone",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button:
                    openActivity(TestActivity.class);
//                    Toast.makeText(this, "isOver="+TextViewUtils.isOverFlowed(tv),Toast.LENGTH_SHORT).show();
                    break;
            }
    }
}
