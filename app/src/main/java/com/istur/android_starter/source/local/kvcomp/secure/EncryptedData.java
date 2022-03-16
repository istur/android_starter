package com.istur.android_starter.source.local.kvcomp.secure;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.crypto.spec.IvParameterSpec;

class EncryptedData {

    private static final ByteOrder ORDER_FOR_ENCRYPTED_DATA = ByteOrder.BIG_ENDIAN;

    byte[] data;
    byte[] iv;

    byte[] toIvAndEncryptedData() {
        int l = Integer.SIZE + data.length;
        if (iv != null) l += iv.length;
        final byte[] ivAndEncryptedData = new byte[l];
        final ByteBuffer buffer = ByteBuffer.wrap(ivAndEncryptedData);
        buffer.order(ORDER_FOR_ENCRYPTED_DATA);
        if (iv != null) {
            buffer.putInt(iv.length);
            buffer.put(iv);
        } else buffer.putInt(0);
        buffer.put(data);
        return ivAndEncryptedData;
    }

    IvParameterSpec getIvParameterSpec() {
        return iv != null ? new IvParameterSpec(iv) : null;
    }

    static EncryptedData fromIvAndEncryptedData(byte[] ivAndEncryptedData) {
        EncryptedData encryptedData = new EncryptedData();
        ByteBuffer buffer = ByteBuffer.wrap(ivAndEncryptedData);
        int ivLength = buffer.getInt();
        if (ivLength != 0) {
            encryptedData.iv = new byte[ivLength];
            buffer.get(encryptedData.iv);
        }
        encryptedData.data = new byte[ivAndEncryptedData.length - Integer.SIZE - ivLength];
        buffer.get(encryptedData.data);
        return encryptedData;
    }

}