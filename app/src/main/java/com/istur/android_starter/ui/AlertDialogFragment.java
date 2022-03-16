package com.istur.android_starter.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.istur.android_starter.R;

import java.util.Objects;

import timber.log.Timber;

public class AlertDialogFragment extends DialogFragment {

    public static final String TAG = AlertDialogFragment.class.getName();

    public static final int ICON_POSITION_TOP = 1;
    public static final int ICON_POSITION_CENTER = 2;
    public static final int CONTROLS_ORIENTATION_HORIZONTAL = 1;
    public static final int CONTROLS_ORIENTATION_VERTICAL = 2;

    private static final String BUNDLE_TITLE = "bundleTitle";
    private static final String BUNDLE_MESSAGE = "bundleMessage";
    private static final String BUNDLE_RESOURCE_ICON = "bundleResourceIcon";
    private static final String BUNDLE_RESOURCE_ICON_COLOR = "bundleResourceIconColor";
    private static final String BUNDLE_RESOURCE_ICON_POSITION = "bundleResourceIconPosition";
    private static final String BUNDLE_RESOURCE_POSITIVE_BUTTON = "bundleResourcePositiveButton";
    private static final String BUNDLE_RESOURCE_NEGATIVE_BUTTON = "bundlebundleResourceNegativeButton";
    private static final String BUNDLE_RESOURCE_CONTROLS_ORIENTATION = "bundleResourceControlsOrientation";
    private static final String BUNDLE_RESOURCE_SECOND_NEGATIVE_BUTTON = "bundlebundleResourceSecondNegativeButton";

    private DialogInterface.OnClickListener mPositiveCallback;
    private DialogInterface.OnClickListener mNegativeCallback;
    private DialogInterface.OnClickListener mSecondNegativeCallback;
    private DialogInterface.OnClickListener mThirdNegativeCallback;
    private String mResourceTitleString;
    private CharSequence mResourceMessageString;
    private int mIcon;
    private int mIconColor;
    private int mIconPosition = ICON_POSITION_CENTER;
    private int mControlsOrientation = CONTROLS_ORIENTATION_HORIZONTAL;
    private int mTitleSize;
    private Integer mResourceButtonPositiveString;
    private Integer mResourceButtonNegativeString;
    private Integer mResourceButtonSecondNegativeString;
    private Integer mResourceButtonThirdNegativeString;
    private TextView alertTitle;
    private TextView alertMessage;
    private ImageView centerIcon;
    private ImageView topIcon;
    private ButtonCompound positiveButtonHorizontal;
    private ButtonCompound negativeButtonHorizontal;
    private ButtonCompound positiveButtonVertical;
    private ButtonCompound negativeButtonVertical;
    private ButtonCompound secondNegativeButtonVertical;
    private ButtonCompound thirdNegativeButtonVertical;
    private ViewGroup vgControlPanelHorizontal;
    private ViewGroup vgControlPanelVertical;
    private boolean firstButtonEnable = true;
    private boolean secondButtonEnable = true;
    private boolean thirdButtonEnable = true;

    public AlertDialogFragment setPositiveButtonCallback(DialogInterface.OnClickListener callback) {
        mPositiveCallback = callback;
        return this;
    }

    public AlertDialogFragment setPositiveButtonText(int resourceText) {
        mResourceButtonPositiveString = resourceText;
        return this;
    }

    public AlertDialogFragment setPositiveButtonCallback(Integer resourceText, DialogInterface.OnClickListener callback) {
        mResourceButtonPositiveString = resourceText;
        mPositiveCallback = callback;
        return this;
    }

    public AlertDialogFragment setNegativeButtonCallback(DialogInterface.OnClickListener callback) {
        mNegativeCallback = callback;
        return this;
    }

    public AlertDialogFragment setNegativeButtonText(int resourceText) {
        mResourceButtonNegativeString = resourceText;
        return this;
    }

    public AlertDialogFragment setNegativeButtonCallback(Integer resourceText, DialogInterface.OnClickListener callback) {
        mResourceButtonNegativeString = resourceText;
        mNegativeCallback = callback;
        return this;
    }

    public AlertDialogFragment setSecondNegativeButtonCallback(Integer resourceText, DialogInterface.OnClickListener callback) {
        mResourceButtonSecondNegativeString = resourceText;
        mSecondNegativeCallback = callback;
        return this;
    }

    public AlertDialogFragment setThirdNegativeButtonCallback(Integer resourceText, DialogInterface.OnClickListener callback) {
        mResourceButtonThirdNegativeString = resourceText;
        mThirdNegativeCallback = callback;
        return this;
    }

    public AlertDialogFragment setTitle(String stringTitle) {
        mResourceTitleString = stringTitle;
        return this;
    }

    public AlertDialogFragment setTitleSize(int size) {
        mTitleSize = size;
        return this;
    }

    public AlertDialogFragment setMessage(CharSequence stringmsg) {
        mResourceMessageString = stringmsg;
        return this;
    }

    public AlertDialogFragment setIcon(int resId) {
        mIcon = resId;
        return this;
    }

    public AlertDialogFragment setIconColor(int color) {
        mIconColor = color;
        return this;
    }

    public AlertDialogFragment setIconPosition(int position) {
        mIconPosition = position;
        return this;
    }

    public AlertDialogFragment setControlsOrientation(int orientation) {
        mControlsOrientation = orientation;
        return this;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(BUNDLE_TITLE, mResourceTitleString);
        outState.putString(BUNDLE_MESSAGE, mResourceMessageString.toString());
        outState.putInt(BUNDLE_RESOURCE_ICON, mIcon);
        outState.putInt(BUNDLE_RESOURCE_ICON_COLOR, mIconColor);
        outState.putInt(BUNDLE_RESOURCE_ICON_POSITION, mIconPosition);
        outState.putInt(BUNDLE_RESOURCE_CONTROLS_ORIENTATION, mControlsOrientation);
        if (mResourceButtonPositiveString != null) {
            outState.putInt(BUNDLE_RESOURCE_POSITIVE_BUTTON, mResourceButtonPositiveString);
        }
        if (mResourceButtonNegativeString != null) {
            outState.putInt(BUNDLE_RESOURCE_NEGATIVE_BUTTON, mResourceButtonNegativeString);
        }
        if (mResourceButtonSecondNegativeString != null) {
            outState.putInt(BUNDLE_RESOURCE_SECOND_NEGATIVE_BUTTON, mResourceButtonSecondNegativeString);
        }

        super.onSaveInstanceState(outState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View alertLayout = inflater.inflate(R.layout.fragment_alert_dialog, null);
        alertTitle = alertLayout.findViewById(R.id.alert_title);
        alertMessage = alertLayout.findViewById(R.id.alert_message);
        centerIcon = alertLayout.findViewById(R.id.alert_icon_center);
        topIcon = alertLayout.findViewById(R.id.alert_icon_top);
        positiveButtonHorizontal = alertLayout.findViewById(R.id.positive_button_horizontal);
        negativeButtonHorizontal = alertLayout.findViewById(R.id.negative_button_horizontal);
        positiveButtonVertical = alertLayout.findViewById(R.id.positive_button_vertical);
        negativeButtonVertical = alertLayout.findViewById(R.id.negative_button_vertical);
        secondNegativeButtonVertical = alertLayout.findViewById(R.id.second_negative_button_vertical);
        thirdNegativeButtonVertical = alertLayout.findViewById(R.id.third_negative_button_vertical);
        vgControlPanelHorizontal = alertLayout.findViewById(R.id.vg_control_panel_horizontal);
        vgControlPanelVertical = alertLayout.findViewById(R.id.vg_control_panel_vertical);
        dialog.setContentView(alertLayout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_rounded_shape);
        initViews(savedInstanceState);
        return dialog;
    }

    private void initViews(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mResourceTitleString = savedInstanceState.getString(BUNDLE_TITLE);
            mResourceMessageString = savedInstanceState.getString(BUNDLE_MESSAGE);
            mIcon = savedInstanceState.getInt(BUNDLE_RESOURCE_ICON);
            mIconColor = savedInstanceState.getInt(BUNDLE_RESOURCE_ICON_COLOR);
            mIconPosition = savedInstanceState.getInt(BUNDLE_RESOURCE_ICON_POSITION);
            mControlsOrientation = savedInstanceState.getInt(BUNDLE_RESOURCE_CONTROLS_ORIENTATION);
            mResourceButtonPositiveString = savedInstanceState.getInt(BUNDLE_RESOURCE_POSITIVE_BUTTON);
            mResourceButtonNegativeString = savedInstanceState.getInt(BUNDLE_RESOURCE_NEGATIVE_BUTTON);
            mResourceButtonSecondNegativeString = savedInstanceState.getInt(BUNDLE_RESOURCE_SECOND_NEGATIVE_BUTTON);
        }

        if (mControlsOrientation == CONTROLS_ORIENTATION_HORIZONTAL) {
            vgControlPanelHorizontal.setVisibility(View.VISIBLE);
            vgControlPanelVertical.setVisibility(View.GONE);
        }
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            vgControlPanelHorizontal.setVisibility(View.GONE);
            vgControlPanelVertical.setVisibility(View.VISIBLE);
        }

        if (mResourceTitleString != null)
            alertTitle.setText(mResourceTitleString);
        else alertTitle.setText(R.string.warning);
        alertMessage.setText(mResourceMessageString);
        setOnPrimaryButtonClickListener(mPositiveCallback);

        if (mTitleSize > 0 && alertTitle != null) {
            alertTitle.setTextSize(mTitleSize);
        }

        ButtonCompound positiveButton = null;
        if (mControlsOrientation == CONTROLS_ORIENTATION_HORIZONTAL) {
            positiveButton = positiveButtonHorizontal;
        }
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            positiveButton = positiveButtonVertical;
        }
        positiveButton.setAlertButtonSize();

        if (mIcon != 0) {
            ImageView ivIcon = null;
            if (mIconPosition == ICON_POSITION_TOP) {
                ivIcon = topIcon;
            }
            if (mIconPosition == ICON_POSITION_CENTER) {
                ivIcon = centerIcon;
            }

            ivIcon.setVisibility(View.VISIBLE);
            ivIcon.setImageResource(mIcon);
            if (mIconColor != 0 && getActivity() != null) {
                ivIcon.setColorFilter(ContextCompat.getColor(getActivity(), mIconColor));
            }
        }
        if (mNegativeCallback != null || mResourceButtonNegativeString != null) {
            ButtonCompound negativeButton = null;
            if (mControlsOrientation == CONTROLS_ORIENTATION_HORIZONTAL) {
                negativeButton = negativeButtonHorizontal;
            }
            if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
                negativeButton = negativeButtonVertical;
            }
            negativeButton.setAlertButtonSize();
            negativeButton.setVisibility(View.VISIBLE);
            setOnNegativeButtonClickListener(mNegativeCallback);
        }
        if (mSecondNegativeCallback != null || mResourceButtonSecondNegativeString != null) {
            ButtonCompound secondNegativeButton = null;
            // IN orizontale non avremo 3 bottoni
            if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
                secondNegativeButton = secondNegativeButtonVertical;
                secondNegativeButton.setAlertButtonSize();
                secondNegativeButton.setVisibility(View.VISIBLE);
                setOnSecondNegativeButtonClickListener(mSecondNegativeCallback);
            }
        }

        if (mThirdNegativeCallback != null || mResourceButtonThirdNegativeString != null) {
            ButtonCompound thirdNegativeButton = null;
            if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
                thirdNegativeButton = thirdNegativeButtonVertical;
                thirdNegativeButton.setAlertButtonSize();
                thirdNegativeButton.setVisibility(View.VISIBLE);
                setOnThirdNegativeButtonClickListener(mThirdNegativeCallback);
            }
        }
    }

    private void setOnPrimaryButtonClickListener(DialogInterface.OnClickListener onButtonClickListener) {
        ButtonCompound positiveButton = null;
        if (mControlsOrientation == CONTROLS_ORIENTATION_HORIZONTAL) {
            positiveButton = positiveButtonHorizontal;
        }
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            positiveButton = positiveButtonVertical;
        }

        if (mResourceButtonPositiveString != null) {
            try {
                Timber.i(getContext().getResources().getResourceName(mResourceButtonPositiveString));
            } catch (Exception e) {
                Timber.i(e);
            }
            positiveButton.setText(mResourceButtonPositiveString);
        } else {
            positiveButton.setText(R.string.alert_button_ok);
        }
        if (firstButtonEnable) {
            positiveButton.setStyle(ButtonCompound.PRIMARY_NO_PADDING);
        } else {
            positiveButton.setStyle(ButtonCompound.DISABLED_PRIMARY_NO_PADDING);
        }
        positiveButton.setOnClickListener(v -> {
            if (onButtonClickListener != null) {
                onButtonClickListener.onClick(getDialog(), DialogInterface.BUTTON_POSITIVE);
            } else if (getTargetFragment() != null) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
                setTargetFragment(null, 0);
            }
            dismiss();
        });
    }

    private void setOnNegativeButtonClickListener(DialogInterface.OnClickListener onButtonClickListener) {
        ButtonCompound negativeButton = null;
        if (mControlsOrientation == CONTROLS_ORIENTATION_HORIZONTAL) {
            negativeButton = negativeButtonHorizontal;
        }
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            negativeButton = negativeButtonVertical;
        }

        if (mResourceButtonNegativeString != null) {
            negativeButton.setText(mResourceButtonNegativeString);
        } else {
            negativeButton.setText(R.string.negative);
        }
        if (secondButtonEnable) {
            negativeButton.setStyle(ButtonCompound.SECONDARY_NO_PADDING);
        } else {
            negativeButton.setStyle(ButtonCompound.DISABLED_SECONDARY_NO_PADDING);
        }
        negativeButton.setOnClickListener(v -> {
            if (onButtonClickListener != null) {
                onButtonClickListener.onClick(getDialog(), DialogInterface.BUTTON_NEGATIVE);
            } else if (getTargetFragment() != null) {
                getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
            }
            dismiss();
        });
    }

    private void setOnSecondNegativeButtonClickListener(DialogInterface.OnClickListener onButtonClickListener) {
        ButtonCompound negativeButton = null;
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            negativeButton = secondNegativeButtonVertical;
            if (mResourceButtonSecondNegativeString != null) {
                negativeButton.setText(mResourceButtonSecondNegativeString);
            } else {
                negativeButton.setText(R.string.negative);
            }
            if (thirdButtonEnable) {
                negativeButton.setStyle(ButtonCompound.SECONDARY_NO_PADDING);
            } else {
                negativeButton.setStyle(ButtonCompound.DISABLED_SECONDARY_NO_PADDING);
            }
            negativeButton.setOnClickListener(v -> {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onClick(getDialog(), DialogInterface.BUTTON_NEGATIVE);
                } else if (getTargetFragment() != null) {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                }
                dismiss();
            });
        }
    }

    private void setOnThirdNegativeButtonClickListener(DialogInterface.OnClickListener onButtonClickListener) {
        ButtonCompound negativeButton = null;
        if (mControlsOrientation == CONTROLS_ORIENTATION_VERTICAL) {
            negativeButton = thirdNegativeButtonVertical;
            if (mResourceButtonThirdNegativeString != null) {
                negativeButton.setText(mResourceButtonThirdNegativeString);
            } else {
                negativeButton.setText(R.string.negative);
            }
            negativeButton.setOnClickListener(v -> {
                if (onButtonClickListener != null) {
                    onButtonClickListener.onClick(getDialog(), DialogInterface.BUTTON_NEGATIVE);
                } else if (getTargetFragment() != null) {
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
                }
            });
        }
    }

    public void onDismissDialogFragment() {
        dismiss();
    }

    public void setEnableButton(boolean enabledPositiveButton, boolean enableNegativeButton, boolean enabledSecondNegativeButton) {
        this.firstButtonEnable = enabledPositiveButton;
        this.secondButtonEnable = enableNegativeButton;
        this.thirdButtonEnable = enabledSecondNegativeButton;
    }
}
