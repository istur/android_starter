package com.istur.android_starter.source.local.kvcomp;


public interface KVKeys {

    // Principali legati al dispositivo
    String DEVICE_ID = "DEVICE_ID";
    String IMEI_NUMBER = "IMEI_NUMBER";
    String APP_LANGUAGE = "APP_LANGUAGE";
    String FIREBASE_DEVICE_TOKEN = "FIREBASE_DEVICE_TOKEN";

    String CHANNEL_ID = "CHANNEL_ID";

    // Legati al tipo di sessione login effettuato
    String USER = "USER";
    String OPERATOR = "OPERATOR";
    String FASTTICKETS_LOCATION = "FASTTICKETS_LOCATION";

    // Al momento non gestiti
    String LTPA_TOKEN2 = "LTPA_TOKEN2";

    String SUBSCRIPTION_OFFLINE_WRAPPER = "SUBSCRIPTION_OFFLINE_WRAPPER";
    String JOURNEY_OPTIONS = "JOURNEY_OPTIONS";
    String CUSTOM_IP = "CUSTOM_IP";
    String PED_USER_CHOISE = "PED_USER_CHOISE";
    String PED_ADDRESS = "PED_ADDRESS";
}