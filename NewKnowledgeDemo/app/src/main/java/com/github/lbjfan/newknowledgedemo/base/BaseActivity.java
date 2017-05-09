package com.github.lbjfan.newknowledgedemo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewId() != -1) {
            setContentView(getContentViewId());
        }
    }

    protected abstract int getContentViewId();

    /**
     * 启动Activity
     *
     * @param bundle：携带的参数
     * @param BaseActivity：需要跳转的Activity
     */
    private void startActivity(Bundle bundle, Class BaseActivity) {
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    /**
     * 带动画启动Activity
     *
     * @param bundle:携带的参数
     * @param BaseActivity：需要跳转的Activity
     * @param enterAnim：进入的动画
     * @param exitAnim：退出的动画
     */
    private void startActivityWithAnim(Bundle bundle, Class BaseActivity, int enterAnim, int exitAnim) {
        startActivity(bundle, BaseActivity);
        overridePendingTransition(enterAnim, exitAnim);
    }

}
