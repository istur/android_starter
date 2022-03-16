package com.istur.android_starter.ui;

import android.content.DialogInterface;

import androidx.fragment.app.FragmentActivity;

import com.istur.android_starter.R;

// TODO: 18/11/2020 pensare ad un singleton?
//  per la gestione di dialog multiple come nell'errorManager? vengono bloccate se c'è nè già un'altra aperta?

public class DialogManager {

    public static void showDialog(FragmentActivity activity, DialogType type, CharSequence message,
                                  Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                  Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback,
                                  boolean cancelable) {
        createDialog(activity, type, message,
                positiveButtonTextResId, positiveCallback,
                negativeButtonTextResId, negativeCallback, cancelable)
                .show(activity.getSupportFragmentManager(), AlertDialogFragment.TAG);
    }

    private static AlertDialogFragment createDialog(FragmentActivity activity, DialogType type, CharSequence message,
                                                    Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                                    Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback,
                                                    boolean cancelable) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();

        switch (type) {
            case ALERT:
                alertDialogFragment
                        .setIcon(R.drawable.ic_error)
                        .setTitle(activity.getString(R.string.alert));
                break;
            case INFO:
                alertDialogFragment
                        .setIcon(R.drawable.ic_info_24dp)
                        .setIconColor(R.color.lightgrey)
                        .setTitle(activity.getString(R.string.info));
                break;
        }
        alertDialogFragment.setCancelable(cancelable);
        return alertDialogFragment
                .setIconPosition(AlertDialogFragment.ICON_POSITION_TOP)
                .setControlsOrientation(AlertDialogFragment.CONTROLS_ORIENTATION_VERTICAL)
//                .setMessage(Html.fromHtml(message).toString())
                .setMessage(message)
                .setPositiveButtonCallback(positiveButtonTextResId, positiveCallback)
                .setNegativeButtonCallback(negativeButtonTextResId, negativeCallback);
    }

    public static void showErrorDialog(FragmentActivity activity, CharSequence message,
                                       Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                       Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback, boolean cancelable) {
        showDialog(activity, DialogType.ALERT, message,
                positiveButtonTextResId, positiveCallback,
                negativeButtonTextResId, negativeCallback, cancelable);
    }

    public static void showInfoDialog(FragmentActivity activity, CharSequence message,
                                      Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                      Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback) {
        showDialog(activity, DialogType.INFO, message,
                positiveButtonTextResId, positiveCallback,
                negativeButtonTextResId, negativeCallback, true);
    }

    public static void showInfoDialog(FragmentActivity activity, CharSequence message,
                                      Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                      Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback, boolean cancelable) {
        showDialog(activity, DialogType.INFO, message,
                positiveButtonTextResId, positiveCallback,
                negativeButtonTextResId, negativeCallback, cancelable);
    }

    public static void showThreeButtonDialog(FragmentActivity activity, CharSequence message, DialogType type,
                                             Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                             Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback,
                                             Integer secondNegativeButtonTextResId, DialogInterface.OnClickListener secondNegativeCallback) {
        AlertDialogFragment dialog = createDialog(activity, type, message, positiveButtonTextResId, positiveCallback, negativeButtonTextResId, negativeCallback, false);
        dialog.setSecondNegativeButtonCallback(secondNegativeButtonTextResId, secondNegativeCallback);
        dialog.show(activity.getSupportFragmentManager(), AlertDialogFragment.TAG);
    }

    public static void showThreeButtonDialog(FragmentActivity activity, CharSequence message, DialogType type,
                                             Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback, boolean enabledPositiveButton,
                                             Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback, boolean enableNegativeButton,
                                             Integer secondNegativeButtonTextResId, DialogInterface.OnClickListener secondNegativeCallback, boolean enabledSecondNegativeButton) {
        AlertDialogFragment dialog = createDialog(activity, type, message, positiveButtonTextResId, positiveCallback, negativeButtonTextResId, negativeCallback, false);
        dialog.setSecondNegativeButtonCallback(secondNegativeButtonTextResId, secondNegativeCallback);
        dialog.setEnableButton(enabledPositiveButton, enableNegativeButton, enabledSecondNegativeButton);
        dialog.show(activity.getSupportFragmentManager(), AlertDialogFragment.TAG);
    }

    public static void showFourButtonDialog(FragmentActivity activity, CharSequence message, DialogType type,
                                             Integer positiveButtonTextResId, DialogInterface.OnClickListener positiveCallback,
                                             Integer negativeButtonTextResId, DialogInterface.OnClickListener negativeCallback,
                                             Integer secondNegativeButtonTextResId, DialogInterface.OnClickListener secondNegativeCallback,
                                             Integer thirdNegativeButtonTextResId, DialogInterface.OnClickListener thirdNegativeCallback) {
        AlertDialogFragment dialog = createDialog(activity, type, message, positiveButtonTextResId, positiveCallback, negativeButtonTextResId, negativeCallback, false);
        dialog.setSecondNegativeButtonCallback(secondNegativeButtonTextResId, secondNegativeCallback);
        dialog.setThirdNegativeButtonCallback(thirdNegativeButtonTextResId, thirdNegativeCallback);
        dialog.show(activity.getSupportFragmentManager(), AlertDialogFragment.TAG);
    }

    public enum DialogType {
        ALERT,
        INFO
    }
}
