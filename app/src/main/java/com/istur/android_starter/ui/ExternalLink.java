package com.istur.android_starter.ui;

import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;

import com.istur.android_starter.R;

public class ExternalLink {

    public static void open(final FragmentActivity context, final String urlToOpen) {
        DialogManager.showInfoDialog(context, "a",
                R.string.yes, (dialog, which) -> {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(urlToOpen));
                    if (i.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(i);
                    } else {
                        DialogManager.showErrorDialog(context, "An error occured",
                                null, null, null, null, true);
                    }
                },
                R.string.label_stay, (dialogInterface, i) -> {
                });
    }
}
