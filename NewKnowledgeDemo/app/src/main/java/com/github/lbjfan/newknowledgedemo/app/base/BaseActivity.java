package com.github.lbjfan.newknowledgedemo.app.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Administrator on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final int DEFAULT_CONTENT_VIEW_ID = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int contentViewId = getContentViewId();
        if (contentViewId == DEFAULT_CONTENT_VIEW_ID) {
            throw new RuntimeException("No such layout id");
        }
        setContentView(getContentViewId());
    }

    protected abstract int getContentViewId();

    protected abstract void initWidget();

    /**
     * 启动Activity
     *
     * @param bundle：携带的参数
     * @param baseActivityClass：需要跳转的Activity
     */
    private void startActivity(Bundle bundle, Class<BaseActivity> baseActivityClass) {
        Intent intent = new Intent(this, baseActivityClass);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    /**
     * 带动画启动Activity
     *
     * @param bundle:携带的参数
     * @param baseActivityClass：需要跳转的Activity
     * @param enterAnim：进入的动画
     * @param exitAnim：退出的动画
     */
    private void startActivityWithAnim(Bundle bundle, Class<BaseActivity> baseActivityClass, int enterAnim, int exitAnim) {
        startActivity(bundle, baseActivityClass);
        overridePendingTransition(enterAnim, exitAnim);
    }

}
