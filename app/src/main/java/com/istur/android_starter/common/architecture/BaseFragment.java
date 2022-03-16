package com.istur.android_starter.common.architecture;

import static com.istur.android_starter.common.architecture.bundle.MyBundleBuilder.BUNDLE_KEY;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<VM extends BaseViewModel> extends DaggerFragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    protected VM mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getViewModelClass() != null) {
            mViewModel = (VM) new ViewModelProvider(getVMOwner(), viewModelFactory).get(getViewModelClass());
        }
    }

    protected Class getViewModelClass() {
        return null;
    }

    protected ViewModelStoreOwner getVMOwner() {
        return this;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            if (mViewModel != null && initViewModel()) {
                mViewModel.init();
            }
        } else {
            if (mViewModel != null && initViewModel()) {
                mViewModel.init(savedInstanceState);
            }
        }
    }

    protected boolean initViewModel() {
        return true;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        if (mViewModel!= null && mViewModel.bundle != null)
            outState.putSerializable(BUNDLE_KEY, mViewModel.bundle.getSerializable(BUNDLE_KEY));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        // gestione errore unica per tutti i VM
//        if (mViewModel != null) {
//            mViewModel.getGenericErrorLiveData().observe(this, e ->
//                    ErrorManager.getErrorManager().showError(getActivity(),
//                            getString(R.string.session_expired_error)));
//        }
    }
}
