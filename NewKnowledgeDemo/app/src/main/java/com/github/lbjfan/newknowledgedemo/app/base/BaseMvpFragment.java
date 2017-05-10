package com.github.lbjfan.newknowledgedemo.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.lbjfan.newknowledgedemo.mvp.MvpPresenter;
import com.github.lbjfan.newknowledgedemo.mvp.MvpView;

/**
 * Created by Administrator on 2017/5/10.
 */

public abstract class BaseMvpFragment<P extends MvpPresenter> extends BaseFragment implements MvpView {

    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter == null) {
            presenter = createPresenter();
            if (presenter == null) {
                throw new RuntimeException("presenter is null object");
            }
        }
        presenter.onMvpAttachView(this, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onMvpStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onMvpResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onMvpPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onMvpStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onMvpDetachView(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onMvpDestroy();
    }
}
