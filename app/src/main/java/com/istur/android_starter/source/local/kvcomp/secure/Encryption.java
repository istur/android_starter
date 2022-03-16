package com.istur.android_starter.source.local.kvcomp.secure;

import android.content.Context;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public abstract class Encryption {

    // key
    static final String ALIAS = new String(new byte[]{107, 101, 121});

    public void init(Context ctx) {
        KeyStore ks = prepareKeyStore();
        try {
            if (!ks.containsAlias(ALIAS)) initKey(ks, ctx);
            else if (!getDecryptionKey(ks).getAlgorithm().equals(getAlgorithm()))
                initKey(ks, ctx);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }

    public EncryptedData encrypt(byte[] data) {
        KeyStore ks = prepareKeyStore();
        Cipher cipher = prepareCipher();
        try {
            Key key = getEncryptionKey(ks);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            EncryptedData encryptedData = new EncryptedData();
            encryptedData.data = cipher.doFinal(data);
            encryptedData.iv = cipher.getIV();
            return encryptedData;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(EncryptedData encryptedData) {
        KeyStore ks = prepareKeyStore();
        Cipher cipher = prepareCipher();
        try {
            Key key = getDecryptionKey(ks);
            cipher.init(Cipher.DECRYPT_MODE, key, encryptedData.getIvParameterSpec());
            return cipher.doFinal(encryptedData.data);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException
                | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract KeyStore prepareKeyStore();

    protected abstract void initKey(KeyStore ks, Context ctx);

    protected Key getEncryptionKey(KeyStore ks) {
        return getDecryptionKey(ks);
    }

    private Key getDecryptionKey(KeyStore ks) {
        try {
            return ks.getKey(ALIAS, getPwd());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    protected char[] getPwd() {
        return null;
    }

    private Cipher prepareCipher() {
        final Cipher cipher;
        try {
            cipher = Cipher.getInstance(String.format("%s/%s/%s", getAlgorithm(), getMode(), getPadding()));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
        return cipher;
    }

    // AES
    protected String getAlgorithm() {
        return new String(new byte[]{65, 69, 83});
    }

    // CBC
    protected String getMode() {
        return new String(new byte[]{67, 66, 67});
    }

    // PKCS5Padding
    protected String getPadding() {
        return new String(new byte[]{80, 75, 67, 83, 53, 80, 97, 100, 100, 105, 110, 103});
    }

}