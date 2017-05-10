package com.github.lbjfan.newknowledgedemo.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/5/10.
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> vRefer;

    protected V getView() {
        return vRefer.get();
    }

    protected boolean isAttachdView() {
        return vRefer != null && vRefer.get() != null;
    }

    private void _attach(V view, Bundle savedInstanceState) {
        vRefer = new WeakReference<V>(view);
    }

    @Override
    public void onMvpAttachView(V view, Bundle savedInstanceState) {
        _attach(view, savedInstanceState);
    }

    @Override
    public void onMvpStart() {

    }

    @Override
    public void onMvpResume() {

    }

    @Override
    public void onMvpPause() {

    }

    @Override
    public void onMvpStop() {

    }

    @Override
    public void onMvpSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void onMvpDetachView(boolean retainInstance) {
        _detach(retainInstance);
    }

    private void _detach(boolean retainInstance) {
        if (vRefer != null) {
            vRefer.clear();
            vRefer = null;
        }
    }

    @Override
    public void onMvpDestroy() {

    }
}
