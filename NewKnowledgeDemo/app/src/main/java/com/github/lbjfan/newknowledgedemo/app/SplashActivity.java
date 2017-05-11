package com.github.lbjfan.newknowledgedemo.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.lbjfan.newknowledgedemo.R;
import com.github.lbjfan.newknowledgedemo.app.base.BaseMvpActivity;
import com.github.lbjfan.newknowledgedemo.app.utils.ViewUtil;
import com.github.lbjfan.newknowledgedemo.app.view.AdTimePickView;

/**
 * Created by Administrator on 2017/5/11.
 */

public class SplashActivity extends BaseMvpActivity<SplashConstract.ISplashPresenter> implements SplashConstract.ISplashView {

    private AdTimePickView vJumpAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        vJumpAd = ViewUtil.findById(this, R.id.fxd_jump_ad);
        vJumpAd.refresh();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fxd_act_splash;
    }

    @Override
    protected SplashConstract.ISplashPresenter createPresenter() {
        return new SplashPresenterImpl();
    }
}
