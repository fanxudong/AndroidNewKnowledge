package com.github.lbjfan.newknowledgedemo.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.lbjfan.newknowledgedemo.mvp.MvpPresenter;
import com.github.lbjfan.newknowledgedemo.mvp.MvpView;

/**
 * Created by Administrator on 2017/5/10.
 */

public abstract class BaseMvpActivity<P extends MvpPresenter> extends BaseActivity implements MvpView {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
            if (presenter == null) {
                throw new RuntimeException("presneter is null");
            }
        }
        presenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onMvpStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onMvpPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onMvpResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onMvpStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onMvpSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onMvpDetachView(false);
        presenter.onMvpDestroy();
    }
}
