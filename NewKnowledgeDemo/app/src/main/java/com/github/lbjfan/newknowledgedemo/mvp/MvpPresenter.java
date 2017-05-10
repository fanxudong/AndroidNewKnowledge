package com.github.lbjfan.newknowledgedemo.mvp;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/5/10.
 */

public interface MvpPresenter<V extends MvpView> {

    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpStart();

    void onMvpResume();

    void onMvpPause();

    void onMvpStop();

    void onMvpSaveInstanceState(Bundle savedInstanceState);

    void onMvpDetachView(boolean retainInstance);

    void onMvpDestroy();
}
