//package com.istur.android_starter.source.remote.okhttp;
//
//import com.istur.android_starter.source.local.kvcomp.KVComponent;
//import com.istur.android_starter.source.remote.RetrofitClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.Cookie;
//
//public class CookieManager {
//        private static CookieJar createCookieJar() {
//        Cookie jsessionId = KVComponent.getKVComponent(AStartApplication.getInstance()).getObject(RetrofitClient.JSESSIONID, Cookie.class);
//        if (jsessionId != null) {
//            boolean alreadySaved = false;
//            for (Cookie cookie : mCookies) {
//                if (isJSessionIDAlias(cookie)) {
//                    alreadySaved = true;
//                }
//            }
//            if (!alreadySaved) {
//                mCookies.add(jsessionId);
//            }
//        }
//        return new CookieJar() {
//            //            private List<Cookie> cookies = new ArrayList<>();
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookieList) {
//                mCookies = saveCookies(cookieList, mCookies);
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                if (mCookies != null) {
//                    List<Cookie> cookiesToLoad = new ArrayList<>();
//                    for (Cookie cok : mCookies) {
//                        if (BuildConfig.DEBUG)
//                            Log.d("Adding Header", cok.name() + " : " + cok.value());
//
//                        CookieManager cm = CookieManager.getInstance();
//                        /**
//                         *  The cookie are set against a domain/host, important to notice that
//                         *  these cookies are shared with the webview, thus the importance of
//                         *  the association with the domain, especially for the JSESSIONID used by the N&TS payment
//                         */
//                        if (cok.name().equals(RetrofitClient.JSESSIONID_ALIAS)) {
//                            cm.setCookie(BuildConfig.APP_HOST_URL, RetrofitClient.JSESSIONID + "=" + cok.value());
//                            cookiesToLoad.add(createNewCookie(RetrofitClient.JSESSIONID, cok));
//                        } else if (cok.name().equals(RetrofitClient.LTPATOKEN_ALIAS)) {
//                            cm.setCookie(BuildConfig.APP_HOST_URL, RetrofitClient.LTPATOKEN + "=" + cok.value());
//                            cookiesToLoad.add(createNewCookie(RetrofitClient.LTPATOKEN, cok));
//                        } else {
//                            cm.setCookie(BuildConfig.APP_HOST_URL, cok.name() + "=" + cok.value());
//                            cookiesToLoad.add(cok);
//                        }
//                    }
////                    for (Cookie cookie : cookiesToLoad) {
////                        if (!mCookies.contains(cookie)) {
////                            mCookies.add(cookie);
////                        }
////                    }
//                    return cookiesToLoad;
//                }
//                return Collections.emptyList();
//            }
//        };
//    }
//
//
//    /**
//     * It saves and/or update cookies with encrypted keys
//     *
//     * @param lastCookieListReceived
//     * @param listToUpdate           contains cookies with encrypted keys
//     * @return updated cookie list
//     */
//    public static List<Cookie> saveCookies(List<Cookie> lastCookieListReceived, List<Cookie> listToUpdate) {
//        List<Cookie> tempCookieListReceived = createNewCookieList(lastCookieListReceived);
//        if (listToUpdate.isEmpty()) {
//            listToUpdate = tempCookieListReceived;
//            for (Cookie cookie : listToUpdate) {
//                if (isJSessionIDAlias(cookie)) {
//                    persistCookie(cookie);
//                }
//            }
//        } else {
//            for (int i = 0; i < tempCookieListReceived.size(); i++) {
//                Cookie currentCookie = tempCookieListReceived.get(i);
//                boolean updated = false;
//                for (int j = 0; j < listToUpdate.size() && !updated; j++) {
//
//                    // if the names are equals it means the cookie has already been added in the cookieJar and only needs to be updated
//                    Cookie oldCookie = listToUpdate.get(j);
//                    if (currentCookie.name().equals(oldCookie.name())) {
//                        if (!currentCookie.value().equals(oldCookie.value()) && isJSessionIDAlias(currentCookie)) {
//                            persistCookie(currentCookie);
//                        }
//                        listToUpdate.set(j, currentCookie);
//                        updated = true;
//                    }
//                }
//                if (!updated) {
//                    if (isJSessionIDAlias(currentCookie)) {
//                        persistCookie(currentCookie);
//                        listToUpdate.add(currentCookie);
//                    }
//                    if (isLTPATokenAlias(currentCookie))
//                        listToUpdate.add(currentCookie);
//                }
//                if (BuildConfig.DEBUG)
//                    Timber.d("Saving Cookie: " + currentCookie.name() + " : " + currentCookie.value());
//            }
//        }
//        return listToUpdate;
//    }
//
//    private static void persistCookie(Cookie currentCookie) {
//        KVComponent.getKVComponent(C2CHandheldApplication.getInstance()).putObject(RetrofitClient.JSESSIONID,
//                currentCookie);
//    }
//
//    private static void setTimesUpUserSession() {
//        if (C2CHandheldApplication.getUser().getValue() != null && C2CHandheldApplication.getUser().getValue().isUserLogged()) {
//            ErrorManager.setTimesUp(true);
//        }
//    }
//
//    private static Cookie createNewCookie(String newCookieName, Cookie currentCookie) {
//        Cookie.Builder cBuilder = new Cookie.Builder();
//
//        Cookie newCookiee = cBuilder.name(newCookieName)
//                .domain(currentCookie.domain())
//                .expiresAt(currentCookie.expiresAt())
//                .value(currentCookie.value())
//                .build();
//
//        return newCookiee;
//    }
//
//    private static List<Cookie> createNewCookieList(List<Cookie> cookieList) {
//
//        List<Cookie> newList = new ArrayList<>();
//        for (Cookie currCokiee : cookieList) {
//            if (isJSessionID(currCokiee)) {
//                newList.add(createNewCookie(RetrofitClient.JSESSIONID_ALIAS, currCokiee));
//                //set the time's up session when setnew JSESSIONID and the user is registered
//                // 23.3.20121 actually never used
//                setTimesUpUserSession();
//            } else if (isLTPAToken(currCokiee))
//                newList.add(createNewCookie(RetrofitClient.LTPATOKEN_ALIAS, currCokiee));
//            else
//                newList.add(currCokiee);
//        }
//        return newList;
//    }
//}
