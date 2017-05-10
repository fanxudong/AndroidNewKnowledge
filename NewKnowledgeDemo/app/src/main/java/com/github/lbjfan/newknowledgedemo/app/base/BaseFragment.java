package com.github.lbjfan.newknowledgedemo.app.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/10.
 */

public class BaseFragment extends Fragment {

    protected static final int DEFAULT_CONTENT_VIEW_ID = -1;
    private Context context;
    private View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidget();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int contentViewId = getContentViewId();
        if (contentViewId == DEFAULT_CONTENT_VIEW_ID) {
            throw new RuntimeException("No such a layout");
        }
        mRootView = inflater.inflate(contentViewId, container, false);
        return mRootView;
    }

    protected int getContentViewId() {
        return DEFAULT_CONTENT_VIEW_ID;
    }

    ;

    protected void initWidget() {
    }

    ;

    /**
     * 启动Activity
     *
     * @param bundle：携带的参数
     * @param baseActivityClass：需要跳转的Activity
     */
    private void startActivity(Bundle bundle, Class<BaseActivity> baseActivityClass) {
        Intent intent = new Intent(context, baseActivityClass);
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
        ((Activity) context).overridePendingTransition(enterAnim, exitAnim);
    }
}
