package com.istur.android_starter.source.local.kvcomp.secure;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;

@TargetApi(Build.VERSION_CODES.M)
class Encryption23 extends Encryption {

    // AndroidKeyStore
    private static final String KEYSTORE_PROVIDER_NAME = new String(new byte[]{65, 110, 100, 114, 111, 105, 100, 75, 101, 121, 83, 116, 111, 114, 101});
    private static final String BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC;
    private static final String PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7;

    protected KeyStore prepareKeyStore() {
        try {
            KeyStore ks = KeyStore.getInstance(KEYSTORE_PROVIDER_NAME);
            ks.load(null);
            return ks;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void initKey(KeyStore ks, Context ctx) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(getAlgorithm(), KEYSTORE_PROVIDER_NAME);
            kg.init(new KeyGenParameterSpec.Builder(ALIAS, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .build());
            kg.generateKey();
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    // CBC
    protected String getMode() {
        return BLOCK_MODE;
    }

    // PKCS5Padding
    protected String getPadding() {
        return PADDING;
    }

}