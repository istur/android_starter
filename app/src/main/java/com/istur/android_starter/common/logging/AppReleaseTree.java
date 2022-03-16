package com.istur.android_starter.common.logging;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class AppReleaseTree extends Timber.Tree {

    public static final String CRASHLYTICS_KEY_PRIORITY = "priority";
    public static final String CRASHLYTICS_KEY_TAG = "tag";
    public static final String CRASHLYTICS_KEY_MESSAGE = "message";

    @Override
    protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable throwable) {
//        logCrashlytics(priority, tag, message, throwable);
    }

    // TODO: 1/31/2022 To Be impelemented if we want use Firebase Crashlytics for Non-Fatals
    public static void logCrashlytics(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable throwable) {
//        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
//        switch (priority) {
//            case Log.WARN:
//            case Log.INFO:
//                //CRASHLYTICS
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_PRIORITY, priority);
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_TAG, tag);
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_MESSAGE, message);
//                crashlytics.log(message);
//                break;
//            case Log.ERROR:
//                //CRASHLYTICS codice per implementare su firebase
//                Throwable t = throwable != null
//                        ? throwable
//                        : new TimberException(message);
//
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_PRIORITY, priority);
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_TAG, tag);
//                crashlytics.setCustomKey(CRASHLYTICS_KEY_MESSAGE, message);
//                crashlytics.recordException(t);
//                break;
//        }
    }
}
