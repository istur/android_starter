package com.istur.android_starter.source.local.kvcomp.secure;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecureStore {

    private static final boolean SECURE_STORE_ENCRYPTION = true;
    private Context ctx;

    public SecureStore(Context ctx) {
        this.ctx = ctx;
        initKey();
    }

    public boolean contains(String key) {
        return getSharedPreferences().contains(toHash(key));
    }

    public void remove(String key) {
        getSharedPreferences().edit().remove(toHash(key)).commit();
    }

    public void put(String key, byte[] data) {
        EncryptedData encryptedData = null;
        if (SECURE_STORE_ENCRYPTION) {
            encryptedData = EncryptionFactory.getEncryption().encrypt(data);
        } else {
            encryptedData = new EncryptedData();
            encryptedData.data = data;
        }

        getSharedPreferences().edit()
                .putString(toHash(key), Base64.encodeToString(encryptedData.toIvAndEncryptedData(), Base64.NO_WRAP))
                .commit();
    }

    public void putString(String key, String value) {
        try {
            put(key, value.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] get(String key) {
        EncryptedData encryptedData = EncryptedData
                .fromIvAndEncryptedData(Base64.decode(getSharedPreferences().getString(toHash(key), null), Base64.NO_WRAP));
        if (SECURE_STORE_ENCRYPTION) {
            return EncryptionFactory.getEncryption().decrypt(encryptedData);
        } else {
            return encryptedData.data;
        }
    }

    public String getString(String key) {
        try {
            return new String(get(key), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private void initKey() {
        EncryptionFactory.getEncryption().init(ctx);
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    private String toHash(String input) {
        MessageDigest md;
        String b64 = null;
        try {
            md = MessageDigest.getInstance("SHA512");
            md.reset();
            md.update(input.getBytes("UTF-8"));
            b64 = Base64.encodeToString(md.digest(), Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return b64;
    }

    public void clear() {
        getSharedPreferences().edit().clear().commit();
    }
}