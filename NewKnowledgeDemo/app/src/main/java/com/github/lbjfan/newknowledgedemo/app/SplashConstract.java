package com.github.lbjfan.newknowledgedemo.app;

import com.github.lbjfan.newknowledgedemo.mvp.MvpPresenter;
import com.github.lbjfan.newknowledgedemo.mvp.MvpView;

/**
 * Created by Administrator on 2017/5/11.
 */

public class SplashConstract {

    public interface ISplashView extends MvpView {


    }

    public interface ISplashPresenter extends MvpPresenter<ISplashView> {

    }
}
