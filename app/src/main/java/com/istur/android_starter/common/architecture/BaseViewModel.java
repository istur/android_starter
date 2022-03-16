package com.istur.android_starter.common.architecture;

import static com.istur.android_starter.common.architecture.bundle.MyBundleBuilder.BUNDLE_KEY;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.istur.android_starter.common.architecture.bundle.CacheRepository;
import com.istur.android_starter.common.architecture.bundle.MyBundle;
import com.istur.android_starter.common.architecture.communication.ApiStateResponse;
import com.istur.android_starter.common.architecture.communication.DefaultApiStateChangeObserver;
import com.istur.android_starter.common.architecture.communication.UiLiveData;
import com.istur.android_starter.common.architecture.utils.EspressoIdlingResource;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public abstract class BaseViewModel extends AndroidViewModel {

    private CompositeDisposable mCompositeDisposable;
    protected Context context;
    public Bundle bundle;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        mCompositeDisposable = new CompositeDisposable();
        context = application;
    }

    protected void init() {
        if (isBundleRequired()) {
            bundle = CacheRepository.getInstance().getNavigationBundle(this.getClass());
            if (bundle != null) {
                onBundleReceived(bundle);
            } else {
                Timber.e(new RuntimeException("Bundle is requested but is null"));
            }
        }
    }

    public void init(Bundle savedInstanceState) {
        Serializable serializable = savedInstanceState.getSerializable(BUNDLE_KEY);
        if (serializable instanceof MyBundle)
            CacheRepository.getInstance().saveNavigationBundle(this.getClass(), savedInstanceState);
        init();
    }

    protected boolean isBundleRequired() {
        return false;
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    protected void onBundleReceived(Bundle bundle) {

    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.clear();
        super.onCleared();
    }

    protected <T> void perform(Observable<ApiStateResponse<T>> observable) {
        addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tApiStateResponse -> {
                }, Timber::e)
        );
    }

    protected <T> void perform(Observable<ApiStateResponse<T>> observable, UiLiveData<T> uiLiveData, boolean isSubject) {
        perform(observable, uiLiveData, true, isSubject);
    }

    protected <T> void perform(Observable<ApiStateResponse<T>> observable, UiLiveData<T> uiLiveData) {
        perform(observable, uiLiveData, true, false);
    }

    protected <T> void perform(Observable<ApiStateResponse<T>> observable, UiLiveData<T> uiLiveData, boolean showLoading, boolean isSubject) {
        addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(ApiStateResponse.loading())
                .doOnSubscribe(disposable -> {
                    if (!isSubject) {
                        EspressoIdlingResource.increment();
                        Timber.d("Increment - status is :" + EspressoIdlingResource.getIdlingResource().isIdleNow());
                    }
                })
                .doFinally(() -> {
                    if (!isSubject) {
                        EspressoIdlingResource.decrement();
                        Timber.d("Decrement - status is :" + EspressoIdlingResource.getIdlingResource().isIdleNow());
                    }
                })
                .subscribeWith(new DefaultApiStateChangeObserver(uiLiveData, showLoading))
        );
    }

    protected <T> void performDao(Observable<ApiStateResponse<List<T>>> observable, UiLiveData<List<T>> uiLiveData) {
        addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DefaultApiStateChangeObserver(uiLiveData))
        );
    }

    protected <T> void performDao(Observable<T> observable) {
        addDisposable(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        );
    }


}
