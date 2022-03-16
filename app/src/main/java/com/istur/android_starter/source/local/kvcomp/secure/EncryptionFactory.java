package com.istur.android_starter.source.local.kvcomp.secure;


import android.os.Build;

public class EncryptionFactory {

    public static Encryption getEncryption() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return new Encryption23();
        } else {
            return new Encryption18();
        }
    }

}