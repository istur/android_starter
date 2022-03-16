package com.istur.android_starter;

import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.istur.android_starter.common.logging.AppReleaseTree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;

public class AStartApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
//        firebaseInit();
        initTimberLogging();
    }

    // TODO: 1/31/2022 usare questo metodo se vogliamo tracciare gli eventi su Firebase
    private void firebaseInit() {
        FirebaseApp.initializeApp(getApplicationContext());
        // TODO: eventualmente abilitare solo per RELEASE e disabilitare per DEBUG
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
    }

    private void initTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected @Nullable
                String createStackElementTag(@NotNull StackTraceElement element) {
                    return String.format(
                            "Class:%s: Line: %s, Method: %s",
                            super.createStackElementTag(element),
                            element.getLineNumber(),
                            element.getMethodName()
                    );
                }
// TODO: 1/31/2022 To be implemented if we want log non-fatals in debug 
//                @Override
//                protected void log(int priority, String tag, @NotNull String message, Throwable t) {
//                    super.log(priority, tag, message, t);
//                    logCrashlytics(priority, tag, message, t);
//                }
            });
        } else {
            Timber.plant(new AppReleaseTree());
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationGraph.builder().create(this);
    }
}
