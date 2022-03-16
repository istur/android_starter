package com.istur.android_starter.source.local.kvcomp.secure;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;

import javax.security.auth.x500.X500Principal;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class Encryption18 extends Encryption {

    private static final String CN_MY_KEY = "CN=myKey";

    // AndroidKeyStore
    private static final String KEYSTORE_PROVIDER_NAME = new String(new byte[]{65, 110, 100, 114, 111, 105, 100, 75, 101, 121, 83, 116, 111, 114, 101});

    @Override
    protected Key getEncryptionKey(KeyStore ks) {
        try {
            Certificate cert = ks.getCertificate(ALIAS);
            return cert.getPublicKey();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

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
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.YEAR, 25);
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(getAlgorithm(), KEYSTORE_PROVIDER_NAME);
            kpg.initialize(new KeyPairGeneratorSpec.Builder(ctx)
                    .setAlias(ALIAS)
                    .setSubject(new X500Principal(CN_MY_KEY))
                    .setSerialNumber(BigInteger.valueOf(1337))
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build());
            kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    // RSA
    @Override
    protected String getAlgorithm() {
        return new String(new byte[]{82, 83, 65});
    }

    // /ECB/PKCS1Padding
    @Override
    protected String getMode() {
        return new String(new byte[]{69, 67, 66});
    }

    // /ECB/PKCS1Padding
    @Override
    protected String getPadding() {
        return new String(new byte[]{80, 75, 67, 83, 49, 80, 97, 100, 100, 105, 110, 103});
    }

}