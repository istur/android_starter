package com.istur.android_starter.common.architecture;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.istur.android_starter.BuildConfig;
import com.istur.android_starter.R;

// Common Base Activity for the whole app
public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        showActivityInfo();
        if (savedInstanceState == null) {
            setActivityMainContentFragment();
        }
    }

    //set fragment from the child activity
    private void setActivityMainContentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_activity_content_container, getMainContentFragment());
        fragmentTransaction.commit();
    }

    protected abstract Fragment getMainContentFragment();

    private void showActivityInfo() {
        if (BuildConfig.DEBUG)
            Toast.makeText(this, this.getClass().getSimpleName(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onOptionsItemSelected(item);
                onBackPressed();
                return true;
            default:
                super.onOptionsItemSelected(item);
                break;
        }
        return false;
    }


}
