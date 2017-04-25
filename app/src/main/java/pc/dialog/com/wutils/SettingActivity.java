package pc.dialog.com.wutils;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import pc.wlt.com.superlibrary.base.BaseActivity;
import pc.wlt.com.superlibrary.utils.ToastUtils;
import pc.wlt.com.superlibrary.widget.CheckOverSizeTextView;

public class SettingActivity extends BaseActivity {


    private Toolbar mToolBar;
    private TextView mTvTitle,mEdtText;
    private CheckOverSizeTextView mTvText;
    @Override
    protected int resId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initUI() {
        mToolBar= (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle("");
        mTvTitle= (TextView) mToolBar.findViewById(R.id.toolbar_title);
        mTvTitle.setText("设置");
        setSupportActionBar(mToolBar);
        mToolBar.setNavigationIcon(R.drawable.back_green);
        mEdtText= (TextView) findViewById(R.id.edt_text);
        mTvText= (CheckOverSizeTextView) findViewById(R.id.tv_text);
    }

    @Override
    protected void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(SettingActivity.this,"Don't touch me");
            }
        });
        mEdtText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!TextUtils.isEmpty(s)){
                        mTvText.setText(s);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mTvText.checkOverLine()){
                    mTvText.displayAll();
                }else {
                    mTvText.hide(1);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

}
