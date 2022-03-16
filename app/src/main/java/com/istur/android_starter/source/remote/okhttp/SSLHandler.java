package com.istur.android_starter.source.remote.okhttp;

import com.istur.android_starter.utils.Constants;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLHandler {

    public static boolean isHTTPS(String host) throws MalformedURLException {
        URL url = new URL(host);
        String protocol = url.getProtocol();

        return Constants.HTTPS.equals(protocol);
    }


    // Create a trust manager that does not validate certificate chains
    public static X509TrustManager trustAllCerts() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }
        };
    }

    public static SSLContext getSSLContext(TrustManager trustManager) throws NoSuchAlgorithmException, KeyManagementException {

        SSLContext sslContext;

        // Create an SSLContext that uses our TrustManager
        sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{trustManager}, null);

        return sslContext;
    }
}
