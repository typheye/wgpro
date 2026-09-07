package com.typheye.wgpro.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.data.CloudCacheDatabase;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class tAccUtils {
    private static final String BASE_URL = "https://service.typheye.cn/api.php";
    // V2 profile routes are available; unsupported legacy-only features remain explicitly blocked.
    private static final boolean USE_V2_LOGIN = true;
    private static final String ACTION_LOGIN = USE_V2_LOGIN ? "login2" : "login";
    private static final String ACTION_GET_DATA_UPDATE = "get_data_update";
    private static final String ACTION_GET_DATA_UPDATE_V2 = "get_data_update2";
    private static final String ACTION_SESSION_LOGOUT_V2 = "session_logout2";
    private static final String ACTION_SET_NICK = "set_nick";
    private static final String ACTION_SET_SHUO = "set_shuo";
    private static final String ACTION_CONFIRM_LOGIN_REQUEST = "confirm_login_request";
    private static final String ACTION_APPROVE_LOGIN_REQUEST = "approve_login_request";
    private static final String ACTION_REJECT_LOGIN_REQUEST = "reject_login_request";
    private static final String ACTION_GET_USER_DATA_V2 = "get_user_data2";
    private static final String ACTION_SET_NICK_V2 = "set_nick2";
    private static final String ACTION_SET_SHUO_V2 = "set_shuo2";
    private static final String ACTION_GET_AVATAR_V2 = "get_avatar2";
    private static final String ACTION_SET_AVATAR_V2 = "account_avatar2";
    private static final String ACTION_GENERATE_LOGIN_REQUEST_V2 = "generate_login_request2";
    private static final String ACTION_CONFIRM_LOGIN_REQUEST_V2 = "confirm_login_request2";
    private static final String ACTION_APPROVE_LOGIN_REQUEST_V2 = "approve_login_request2";
    private static final String ACTION_REJECT_LOGIN_REQUEST_V2 = "reject_login_request2";
    private static final String ACTION_CHECK_LOGIN_REQUEST_STATUS_V2 = "check_login_request_status2";
    private static final String ACTION_CREATE_WEB_LOGIN_TICKET_V2 = "create_web_login_ticket2";
    private static final String WEB_AUTH_URL = "https://account.typheye.cn/auth";

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_COOKIE = "cookie";
    private static final String KEY_UID = "uid";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TIME = "time";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_NICK = "nick";
    private static final String KEY_SHUO = "shuo";
    private static final String KEY_VALUE = "value";
    private static final String PREFS_NAME = "account_prefs";
    private static final String PREFS_COOKIE = "cookie";
    private static final String PREFS_UID = "uid";
    private static final String PREFS_EMAIL = "email";
    private static final String PREFS_NICK = "nick";
    private static final String PREFS_SHUO = "shuo";
    private static final String PREFS_PROTOCOL = "protocol";
    private static final String PROTOCOL_LEGACY = "legacy";
    private static final String PROTOCOL_V2 = "v2";
    private static final String SECURE_PREFS_NAME = "account_secure_prefs";
    private static final String PREFS_SESSION_ID = "session_id";
    private static final String PREFS_SESSION_TOKEN = "session_token";
    private static final String PREFS_WEB_SESSION_SOURCE_ID = "web_session_source_id";
    private static final String KEY_REQUEST_ID = "request_id";
    private static final String ACTION_UPDATE_USER_DATA = "get_user_data";
    private final Context context;
    private final OkHttpClient client;
    private static final Object SECURE_PREFS_LOCK = new Object();
    private static volatile SharedPreferences securePreferences;
    private static final OkHttpClient SHARED_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build();

    private WGProBottomSheetDialog progressDialog;

    public tAccUtils(Context context) {
        this.context = context;
        this.client = SHARED_CLIENT;
    }

    public void login(String username, String password, @NonNull final LoginCallback callback) {
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在登录...");

        String encryptedPassword = encryptPassword(username, password);

        long time = System.currentTimeMillis();
        String token = calculateToken_login(username, encryptedPassword, time);

        try {
            String url = BASE_URL + "?type=" + ACTION_LOGIN;
            RequestBody body = new FormBody.Builder()
                    .add(KEY_USERNAME, username)
                    .add(KEY_PASSWORD, encryptedPassword)
                    .add(KEY_TIME, String.valueOf(time))
                    .add(KEY_TOKEN, token)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .headers(clientHeaders())
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("登录失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            try {
                                JSONObject json = new JSONObject(responseData);
                                if (json.has("code") && json.getInt("code") == 200) {
                                    JSONObject info = json.getJSONObject("info");
                                    String uid = info.getString(KEY_UID);
                                    String email = info.optString(KEY_EMAIL, "");
                                    String nick = info.optString(KEY_NICK, "");
                                    String shuo = info.optString(KEY_SHUO, "");
                                    LoginResult result;
                                    if (USE_V2_LOGIN) {
                                        JSONObject session = json.getJSONObject("session");
                                        String sessionId = session.getString(PREFS_SESSION_ID);
                                        String sessionToken = session.getString(PREFS_SESSION_TOKEN);
                                        result = LoginResult.v2(uid, email, nick, shuo, sessionId, sessionToken);
                                    } else {
                                        String cookie = info.getString(KEY_COOKIE);
                                        result = new LoginResult(cookie, uid, email, nick, shuo);
                                    }
                                    if (saveLoginData(result)) {
                                        callback.onSuccess(result);
                                    } else {
                                        callback.onError("无法安全保存登录会话");
                                    }
                                } else {
                                    String message = json.optString("msg", "登录失败");
                                    callback.onError(message);
                                }
                            } catch (JSONException e) {
                                callback.onError("服务器响应格式错误: " + responseData);
                            }
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("登录失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    private String calculateToken_login(String username, String encryptedPassword, long time) {
        String tokenString = "type=" + ACTION_LOGIN + "&username=" + username + "&password=" + encryptedPassword + "&time=" + time;
        return md5(tokenString);
    }

    private String calculateToken_getUserDataUpdateJson(String uid, String cookie, long time) {
        String tokenString = "type=get_data_update&uid=" + uid + "&cookie=" + cookie + "&time=" + time;
        return md5(tokenString);
    }

    private String encryptPassword(String username, String password) {
        String md5Username = md5(username);
        String substr6 = md5Username.substring(0, 6);
        String md5Substr6 = md5(substr6);
        String saltPart = md5Substr6.substring(0, 5);
        String salt = md5(saltPart);
        String md5Password = md5(password);
        return md5Password + salt;
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    private boolean saveLoginData(LoginResult result) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (PROTOCOL_V2.equals(result.protocol)) {
            SharedPreferences securePrefs = getSecurePreferences();
            if (securePrefs == null || !securePrefs.edit()
                    .putString(PREFS_SESSION_ID, result.sessionId)
                    .putString(PREFS_SESSION_TOKEN, result.sessionToken)
                    .commit()) {
                return false;
            }
        } else {
            clearSecureSession();
        }
        SharedPreferences.Editor editor = prefs.edit();
        if (PROTOCOL_LEGACY.equals(result.protocol)) {
            editor.putString(PREFS_COOKIE, result.cookie);
        } else {
            editor.remove(PREFS_COOKIE);
        }
        editor.putString(PREFS_UID, result.uid);
        editor.putString(PREFS_EMAIL, result.email);
        editor.putString(PREFS_NICK, result.nick);
        editor.putString(PREFS_SHUO, result.shuo);
        editor.putString(PREFS_PROTOCOL, result.protocol);
        return editor.commit();
    }

    private void showProgressDialog(String message) {
        new Handler(Looper.getMainLooper()).post(() -> {
            dismissProgressDialogNow();
            WGProAlertDialogBuilder builder = new WGProAlertDialogBuilder(context);
            builder.setTitle("请稍候");

            View view = View.inflate(context, R.layout.progress_dialog, null);
            view.findViewById(android.R.id.progress);
            TextView messageTextView = view.findViewById(android.R.id.message);
            messageTextView.setText(message);

            builder.setView(view);
            builder.setCancelable(false);
            builder.setNegativeButton("取消", (dialog, which) -> client.dispatcher().cancelAll());

            progressDialog = builder.create();
            progressDialog.show();
        });
    }

    private void hideProgressDialog() {
        new Handler(Looper.getMainLooper()).post(this::dismissProgressDialogNow);
    }

    public void cancelAllRequests() {
        client.dispatcher().cancelAll();
    }

    private void dismissProgressDialogNow() {
        WGProBottomSheetDialog dialog = progressDialog;
        progressDialog = null;
        if (dialog == null || !dialog.isShowing()) return;
        try {
            dialog.dismissForReplacement();
        } catch (IllegalArgumentException ignored) {
            // The host Activity may have been closed while the network request completed.
        }
    }

    private void dismissProgressDialogForReplacement() {
        WGProBottomSheetDialog dialog = progressDialog;
        progressDialog = null;
        if (dialog == null || !dialog.isShowing()) return;
        try {
            dialog.dismissForReplacement();
        } catch (IllegalArgumentException ignored) {
            // The host Activity may have closed while the network request completed.
        }
    }

    public static class LoginResult {
        public final String cookie;
        public final String uid;
        public final String email;
        public final String nick;
        public final String shuo;
        public final String protocol;
        public final String sessionId;
        private final String sessionToken;

        public LoginResult(String cookie, String uid, String email, String nick, String shuo) {
            this(cookie, uid, email, nick, shuo, PROTOCOL_LEGACY, "", "");
        }

        private LoginResult(String cookie, String uid, String email, String nick, String shuo,
                            String protocol, String sessionId, String sessionToken) {
            this.cookie = cookie;
            this.uid = uid;
            this.email = email;
            this.nick = nick;
            this.shuo = shuo;
            this.protocol = protocol;
            this.sessionId = sessionId;
            this.sessionToken = sessionToken;
        }

        private static LoginResult v2(String uid, String email, String nick, String shuo,
                                      String sessionId, String sessionToken) {
            return new LoginResult("", uid, email, nick, shuo, PROTOCOL_V2, sessionId, sessionToken);
        }
    }

    public interface LoginCallback {
        void onSuccess(LoginResult result);

        void onError(String message);
    }

    public boolean isLogin() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String uid = prefs.getString(PREFS_UID, "");
        if (uid.isEmpty()) return false;
        // Keystore initialization may involve disk and binder I/O. UI state is based on
        // the non-secret protocol marker; authenticated background requests verify V2 credentials.
        if (isV2Session()) return true;
        return !getCookie().isEmpty();
    }

    public String getNick() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_NICK, "");
    }

    public String getUid() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_UID, "");
    }

    public String getShuo() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_SHUO, "");
    }

    public String getCookie() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREFS_COOKIE, "");
    }

    public boolean isV2Session() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return PROTOCOL_V2.equals(prefs.getString(PREFS_PROTOCOL, PROTOCOL_LEGACY));
    }

    private String getSessionId() {
        SharedPreferences prefs = getSecurePreferences();
        return prefs == null ? "" : prefs.getString(PREFS_SESSION_ID, "");
    }

    private String getSessionToken() {
        SharedPreferences prefs = getSecurePreferences();
        return prefs == null ? "" : prefs.getString(PREFS_SESSION_TOKEN, "");
    }

    private SharedPreferences getSecurePreferences() {
        SharedPreferences cached = securePreferences;
        if (cached != null) return cached;
        synchronized (SECURE_PREFS_LOCK) {
            if (securePreferences != null) return securePreferences;
            securePreferences = createSecurePreferences();
            return securePreferences;
        }
    }

    private SharedPreferences createSecurePreferences() {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();
            return EncryptedSharedPreferences.create(
                    context,
                    SECURE_PREFS_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            Log.e("tAccUtils", "Secure session storage unavailable", e);
            return null;
        }
    }

    private void clearSecureSession() {
        SharedPreferences prefs = getSecurePreferences();
        if (prefs != null) prefs.edit().clear().apply();
    }

    private Headers clientHeaders() {
        String version = getClientVersion();
        String device = asciiHeaderValue(Build.MANUFACTURER + " " + Build.MODEL);
        String userAgent = "WristManagerPro/" + version + " (Android "
                + asciiHeaderValue(Build.VERSION.RELEASE) + "; " + device + ")";
        return new Headers.Builder()
                .add("X-Typheye-Platform", "android")
                .add("X-Typheye-Version", version)
                .add("User-Agent", userAgent)
                .build();
    }

    private String asciiHeaderValue(String value) {
        return value.replaceAll("[^\\x20-\\x7E]", "_");
    }

    private String getClientVersion() {
        try {
            String version = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName;
            return version == null ? "unknown" : version;
        } catch (Exception e) {
            return "unknown";
        }
    }

    private Request.Builder authenticatedRequest(String url, String sessionId, String sessionToken) {
        return new Request.Builder()
                .url(url)
                .headers(clientHeaders())
                .header("Authorization", "Bearer " + sessionToken)
                .header("X-Typheye-Session-Id", sessionId);
    }

    private String v2Url(String action, String uid) throws Exception {
        return BASE_URL + "?type=" + action + "&" + KEY_UID + "="
                + URLEncoder.encode(uid, "UTF-8");
    }

    public void logout() {
        clearLocalLoginData();
    }

    private void clearLocalLoginData() {
        File filesDir = context.getFilesDir();
        File avatarFile = new File(filesDir, "avatar_" + getUid() + ".jpg");
        // ✅ 保留头像文件删除逻辑（检查删除结果）
        if (!avatarFile.delete()) {
            Log.w("Logout", "Failed to delete avatar file: " + avatarFile.getAbsolutePath());
        }

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(PREFS_COOKIE);
        editor.remove(PREFS_UID);
        editor.remove(PREFS_EMAIL);
        editor.remove(PREFS_NICK);
        editor.remove(PREFS_SHUO);
        editor.remove(PREFS_PROTOCOL);
        editor.apply();
        clearSecureSession();
        clearWebViewCookies();
    }

    public static void saveAvatarToCache(Context context, String uid, Bitmap bitmap) {
        File filesDir = context.getFilesDir();
        File avatarFile = new File(filesDir, "avatar_" + uid + ".jpg");

        try (FileOutputStream out = new FileOutputStream(avatarFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        } catch (Exception e) {
            // 缓存失败不影响主流程
        }
    }

    public void getUserDataUpdateJson(@NonNull final UserDataUpdateCallback callback) {
        if (isV2Session()) {
            getV2UserDataUpdate(callback);
            return;
        }
        String uid = getUid();
        String cookie = getCookie();
        if (cookie == null || cookie.isEmpty()) {
            callback.onError("未获取到cookie");
            return;
        }

        long time = System.currentTimeMillis();
        String token = calculateToken_getUserDataUpdateJson(uid, cookie, time);

        try {
            String url = BASE_URL + "?type=" + ACTION_GET_DATA_UPDATE
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8")
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8")
                    + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                    + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onError("网络请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                boolean isLoginValid = true;
                                boolean v1Changed = false;
                                boolean v2Changed = false;
                                boolean v3Changed = false;

                                JSONObject info = json.getJSONObject("info");
                                if (info.getInt("v0") == 1) {
                                    isLoginValid = false;
                                    logout();
                                    new Handler(Looper.getMainLooper()).post(() -> new WGProAlertDialogBuilder(context)
                                            .setTitle("提示")
                                            .setCancelable(false)
                                            .setMessage("当前登录状态已失效，账户已在别处登录")
                                            .setPositiveButton("确定", null)
                                            .show());
                                } else {
                                    v1Changed = info.getInt("v1") == 1;
                                    v2Changed = info.getInt("v2") == 1;
                                    v3Changed = info.getInt("v3") == 1;
                                }

                                callback.onSuccess(new UserDataUpdateResult(isLoginValid, v1Changed, v2Changed, v3Changed));
                            } else {
                                callback.onError("服务器返回错误: " + json.optString("msg", "未知错误"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    private void getV2UserDataUpdate(@NonNull final UserDataUpdateCallback callback) {
        String uid = getUid();
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            callback.onError("本地登录会话读取失败");
            return;
        }

        try {
            String url = BASE_URL + "?type=" + ACTION_GET_DATA_UPDATE_V2
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8");
            Request request = authenticatedRequest(url, sessionId, sessionToken)
                    .post(new FormBody.Builder().add("v1", "").add("v2", "").add("v3", "").build())
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onError("网络请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    try (ResponseBody responseBody = response.body()) {
                        if (!response.isSuccessful() || responseBody == null) {
                            callback.onError("请求失败: " + response.code());
                            return;
                        }
                        JSONObject json = new JSONObject(responseBody.string());
                        if (json.optInt("code") != 200) {
                            callback.onError(json.optString("msg", "服务器返回错误"));
                            return;
                        }
                        JSONObject info = json.getJSONObject("info");
                        if (!info.has("v0")) {
                            callback.onError("服务器响应缺少会话状态");
                            return;
                        }
                        int sessionState = info.optInt("v0", -1);
                        if (sessionState != 0 && sessionState != 1) {
                            callback.onError("服务器返回了未知的会话状态");
                            return;
                        }
                        boolean valid = sessionState == 0;
                        if (sessionState == 1) {
                            clearLocalLoginData();
                            new Handler(Looper.getMainLooper()).post(() ->
                    new WGProAlertDialogBuilder(context)
                                            .setTitle("登录已失效")
                                            .setCancelable(false)
                                            .setMessage("当前设备的登录会话已失效，请重新登录")
                                            .setPositiveButton("确定", null)
                                            .show());
                        }
                        callback.onSuccess(new UserDataUpdateResult(valid,
                                info.optInt("v1") == 1, info.optInt("v2") == 1, info.optInt("v3") == 1));
                    } catch (IOException | JSONException e) {
                        callback.onError("解析响应失败: " + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    public void logoutCurrentSession(@NonNull final LogoutCallback callback) {
        new Thread(() -> logoutCurrentSessionInBackground(callback), "typheye-session-logout").start();
    }

    private void logoutCurrentSessionInBackground(@NonNull final LogoutCallback callback) {
        if (!isV2Session()) {
            clearLocalLoginData();
            callback.onComplete();
            return;
        }
        String uid = getUid();
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        try {
            String url = BASE_URL + "?type=" + ACTION_SESSION_LOGOUT_V2
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8");
            Request request = authenticatedRequest(url, sessionId, sessionToken)
                    .post(new FormBody.Builder().build())
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    clearLocalLoginData();
                    callback.onComplete();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    response.close();
                    clearLocalLoginData();
                    callback.onComplete();
                }
            });
        } catch (Exception e) {
            clearLocalLoginData();
            callback.onComplete();
        }
    }

    public interface LogoutCallback {
        void onComplete();
    }

    public interface UserDataUpdateCallback {
        void onSuccess(UserDataUpdateResult result);

        void onError(String message);
    }

    public static class UserDataUpdateResult {
        public final boolean isLoginValid;
        public final boolean v1Changed;
        public final boolean v2Changed;
        public final boolean v3Changed;

        public UserDataUpdateResult(boolean isLoginValid, boolean v1Changed, boolean v2Changed, boolean v3Changed) {
            this.isLoginValid = isLoginValid;
            this.v1Changed = v1Changed;
            this.v2Changed = v2Changed;
            this.v3Changed = v3Changed;
        }
    }

    public OkHttpClient getClient() {
        return client;
    }

    public interface JsonCallback {
        void onSuccess(@NonNull JSONObject json);
        void onError(int statusCode, @NonNull String message);
    }

    public void getV2Json(@NonNull String action, @NonNull Map<String, String> query,
                          boolean authenticationRequired, @NonNull JsonCallback callback) {
        String cacheKey = buildCloudCacheKey(action, query, authenticationRequired);
        executeV2Json(action, query, null, authenticationRequired, null, new JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                try (CloudCacheDatabase cache = new CloudCacheDatabase(context)) {
                    cache.put(cacheKey, json.toString());
                }
                callback.onSuccess(json);
            }

            @Override public void onError(int statusCode, @NonNull String message) {
                if (statusCode == 0 || statusCode >= 500) {
                    try (CloudCacheDatabase cache = new CloudCacheDatabase(context)) {
                        String payload = cache.get(cacheKey);
                        if (payload != null) {
                            callback.onSuccess(new JSONObject(payload));
                            return;
                        }
                    } catch (Exception ignored) { }
                }
                callback.onError(statusCode, message);
            }
        });
    }

    private String buildCloudCacheKey(String action, Map<String, String> query,
                                      boolean authenticationRequired) {
        StringBuilder key = new StringBuilder(action);
        if (authenticationRequired) key.append("|uid=").append(getUid());
        java.util.TreeMap<String, String> sorted = new java.util.TreeMap<>(query);
        for (Map.Entry<String, String> entry : sorted.entrySet()) {
            key.append('|').append(entry.getKey()).append('=').append(entry.getValue());
        }
        return key.toString();
    }

    public void postV2Json(@NonNull String action, @NonNull Map<String, String> fields,
                           @NonNull JsonCallback callback) {
        executeV2Json(action, java.util.Collections.emptyMap(), fields,
                true, java.util.UUID.randomUUID().toString(), callback);
    }

    public void getPublicJsonUrl(@NonNull String url, @NonNull JsonCallback callback) {
        String cacheKey = "url|" + url;
        Request request;
        try {
            request = new Request.Builder().url(url).headers(clientHeaders()).get().build();
        } catch (Exception error) {
            callback.onError(0, "请求地址无效");
            return;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                if (!deliverCachedJson(cacheKey, callback)) {
                    callback.onError(0, "网络请求失败: " + error.getMessage());
                }
            }

            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody body = response.body()) {
                    JSONObject json = new JSONObject(body == null ? "" : body.string());
                    int code = json.optInt("code", response.code());
                    if (response.isSuccessful() && (code == 200 || !json.has("code"))) {
                        try (CloudCacheDatabase cache = new CloudCacheDatabase(context)) {
                            cache.put(cacheKey, json.toString());
                        }
                        callback.onSuccess(json);
                    } else {
                        if (response.code() >= 500 && deliverCachedJson(cacheKey, callback)) return;
                        callback.onError(code, json.optString("msg", "请求失败: " + response.code()));
                    }
                } catch (Exception error) {
                    if (!deliverCachedJson(cacheKey, callback)) {
                        callback.onError(response.code(), "响应解析失败: " + error.getMessage());
                    }
                }
            }
        });
    }

    private boolean deliverCachedJson(String cacheKey, JsonCallback callback) {
        try (CloudCacheDatabase cache = new CloudCacheDatabase(context)) {
            String payload = cache.get(cacheKey);
            if (payload == null) return false;
            callback.onSuccess(new JSONObject(payload));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private void executeV2Json(String action, Map<String, String> query,
                               @Nullable Map<String, String> fields,
                               boolean authenticationRequired, @Nullable String idempotencyKey,
                               JsonCallback callback) {
        try {
            HttpUrl.Builder url = HttpUrl.get(BASE_URL).newBuilder()
                    .setQueryParameter("type", action);
            Request.Builder request = new Request.Builder().headers(clientHeaders());
            if (authenticationRequired) {
                String uid = getUid();
                String sessionId = getSessionId();
                String sessionToken = getSessionToken();
                if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
                    callback.onError(401, "请先登录 Typheye 账户");
                    return;
                }
                url.setQueryParameter(KEY_UID, uid);
                request.header("Authorization", "Bearer " + sessionToken)
                        .header("X-Typheye-Session-Id", sessionId);
            } else if (isV2Session()) {
                String uid = getUid();
                String sessionId = getSessionId();
                String sessionToken = getSessionToken();
                if (!uid.isEmpty() && !sessionId.isEmpty() && !sessionToken.isEmpty()) {
                    url.setQueryParameter(KEY_UID, uid);
                    request.header("Authorization", "Bearer " + sessionToken)
                            .header("X-Typheye-Session-Id", sessionId);
                }
            }
            for (Map.Entry<String, String> entry : query.entrySet()) {
                if (entry.getValue() != null) url.setQueryParameter(entry.getKey(), entry.getValue());
            }
            request.url(url.build());
            if (fields == null) {
                request.get();
            } else {
                FormBody.Builder form = new FormBody.Builder();
                for (Map.Entry<String, String> entry : fields.entrySet()) {
                    if (entry.getValue() != null) form.add(entry.getKey(), entry.getValue());
                }
                request.post(form.build());
                if (idempotencyKey != null) request.header("Idempotency-Key", idempotencyKey);
            }
            client.newCall(request.build()).enqueue(new Callback() {
                @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                    callback.onError(0, "网络请求失败: " + error.getMessage());
                }

                @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                    try (ResponseBody body = response.body()) {
                        String raw = body == null ? "" : body.string();
                        JSONObject json = raw.isEmpty() ? new JSONObject() : new JSONObject(raw);
                        int code = json.optInt("code", response.code());
                        if (response.code() == 401 || code == 401) {
                            logout();
                            callback.onError(401, json.optString("msg", "会话已失效"));
                        } else if (!response.isSuccessful() || code != 200) {
                            callback.onError(code, json.optString("msg", "请求失败: " + response.code()));
                        } else {
                            callback.onSuccess(json);
                        }
                    } catch (Exception error) {
                        callback.onError(response.code(), "响应解析失败: " + error.getMessage());
                    }
                }
            });
        } catch (Exception error) {
            callback.onError(0, "请求构建失败: " + error.getMessage());
        }
    }


    // 新增：确认登录请求
    public void goConfirmLoginRequest(String requestId, @NonNull final SetCallback callback) {
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在请求...");

        String uid = getUid();
        if (isV2Session()) {
            runV2LoginAuthorization(ACTION_CONFIRM_LOGIN_REQUEST_V2, uid, requestId, callback);
            return;
        }
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            hideProgressDialog();
            callback.onError("未获取到用户信息");
            return;
        }

        long time = System.currentTimeMillis();
        String token = calculateToken_goConfirmLoginRequest(uid, cookie, requestId, time);

        try {
            String url = BASE_URL + "?type=" + ACTION_CONFIRM_LOGIN_REQUEST
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8")
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8")
                    + "&" + KEY_REQUEST_ID + "=" + URLEncoder.encode(requestId, "UTF-8")
                    + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                    + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                callback.onSuccess();
                            } else {
                                callback.onError(json.optString("msg", "请求失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    // 新增：回应登录请求
    public void postLoginRequest(String requestId, boolean isApprove, @NonNull final SetCallback callback) {
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在请求...");

        String uid = getUid();
        if (isV2Session()) {
            runV2LoginAuthorization(isApprove ? ACTION_APPROVE_LOGIN_REQUEST_V2
                    : ACTION_REJECT_LOGIN_REQUEST_V2, uid, requestId, callback);
            return;
        }
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            hideProgressDialog();
            callback.onError("未获取到用户信息");
            return;
        }

        long time = System.currentTimeMillis();
        String token = calculateToken_postLoginRequest(requestId, isApprove, time);

        try {
            String LoginReqType = isApprove ? ACTION_APPROVE_LOGIN_REQUEST : ACTION_REJECT_LOGIN_REQUEST;
            String url = BASE_URL + "?type=" + LoginReqType
                    + "&" + KEY_REQUEST_ID + "=" + URLEncoder.encode(requestId, "UTF-8")
                    + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                    + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                callback.onSuccess();
                            } else {
                                callback.onError(json.optString("msg", "请求失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }


    private void runV2LoginAuthorization(String action, String uid, String requestId,
                                         @NonNull final SetCallback callback) {
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid == null || uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            hideProgressDialog();
            callback.onError("登录会话无效");
            return;
        }
        try {
            String url = v2Url(action, uid) + "&" + KEY_REQUEST_ID + "="
                    + URLEncoder.encode(requestId, "UTF-8");
            Request request = authenticatedRequest(url, sessionId, sessionToken).get().build();
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                    hideProgressDialog();
                    callback.onError("请求失败: " + error.getMessage());
                }

                @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    try (ResponseBody body = response.body()) {
                        JSONObject json = new JSONObject(body == null ? "" : body.string());
                        if (response.isSuccessful() && json.optInt("code") == 200) {
                            callback.onSuccess();
                        } else {
                            callback.onError(json.optString("msg", "请求失败: " + response.code()));
                        }
                    } catch (Exception error) {
                        callback.onError("解析响应失败: " + error.getMessage());
                    }
                }
            });
        } catch (Exception error) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + error.getMessage());
        }
    }

    // 新增：更新数据
    public void updateUserData(@NonNull final SetCallback callback) {
        if (isV2Session()) {
            getV2Profile(callback);
            return;
        }
        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            callback.onError("网络不可用");
            return;
        }

        String uid = getUid();
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            callback.onError("未获取到用户信息");
            return;
        }

        try {
            String url = BASE_URL + "?type=" + ACTION_UPDATE_USER_DATA
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8")
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onError("失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                JSONObject info = json.getJSONObject("info");
                                String nick = info.getString("nick");
                                String shuo = info.getString("shuo");
                                saveNick(nick);
                                saveShuo(shuo);
                                callback.onSuccess();
                            } else {
                                callback.onError(json.optString("msg", "修改失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    // 新增：设置昵称
    public void setNick(String value, @NonNull final SetCallback callback) {
        if (isV2Session()) {
            mutateV2Profile(ACTION_SET_NICK_V2, value, true, callback);
            return;
        }
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在修改昵称...");

        String uid = getUid();
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            hideProgressDialog();
            callback.onError("未获取到用户信息");
            return;
        }

        long time = System.currentTimeMillis();
        String token = calculateToken_setNick(uid, cookie, value, time);

        try {
            String url = BASE_URL + "?type=" + ACTION_SET_NICK
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8")
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8")
                    + "&" + KEY_VALUE + "=" + URLEncoder.encode(value, "UTF-8")
                    + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                    + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("修改失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                saveNick(value);
                                callback.onSuccess();
                            } else {
                                callback.onError(json.optString("msg", "修改失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    // 新增：设置签名(说说)
    public void setShuo(String value, @NonNull final SetCallback callback) {
        if (isV2Session()) {
            mutateV2Profile(ACTION_SET_SHUO_V2, value, false, callback);
            return;
        }
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在修改签名...");

        String uid = getUid();
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            hideProgressDialog();
            callback.onError("未获取到用户信息");
            return;
        }

        long time = System.currentTimeMillis();
        String token = calculateToken_setShuo(uid, cookie, value, time);

        try {
            String url = BASE_URL + "?type=" + ACTION_SET_SHUO
                    + "&" + KEY_UID + "=" + URLEncoder.encode(uid, "UTF-8")
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8")
                    + "&" + KEY_VALUE + "=" + URLEncoder.encode(value, "UTF-8")
                    + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                    + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("修改失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                saveShuo(value);
                                callback.onSuccess();
                            } else {
                                callback.onError(json.optString("msg", "修改失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    private void getV2Profile(@NonNull final SetCallback callback) {
        String uid = getUid();
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            callback.onError("登录会话无效");
            return;
        }
        final Request request;
        try {
            request = authenticatedRequest(v2Url(ACTION_GET_USER_DATA_V2, uid), sessionId, sessionToken)
                    .get().build();
        } catch (Exception error) {
            callback.onError("参数编码错误: " + error.getMessage());
            return;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                callback.onError("网络请求失败: " + error.getMessage());
            }

            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody body = response.body()) {
                    String responseData = body == null ? "" : body.string();
                    if (response.code() == 401) {
                        callback.onError("资料服务暂时无法验证登录会话");
                        return;
                    }
                    JSONObject json = new JSONObject(responseData);
                    if (!response.isSuccessful() || json.optInt("code") != 200) {
                        callback.onError(json.optString("msg", "请求失败: " + response.code()));
                        return;
                    }
                    JSONObject info = json.getJSONObject("info");
                    saveNick(info.optString("nick", ""));
                    saveShuo(info.optString("shuo", ""));
                    callback.onSuccess();
                } catch (Exception error) {
                    callback.onError("解析响应失败: " + error.getMessage());
                }
            }
        });
    }

    private void mutateV2Profile(String action, String value, boolean nickname,
                                 @NonNull final SetCallback callback) {
        String uid = getUid();
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            callback.onError("登录会话无效");
            return;
        }
        final Request request;
        try {
            request = authenticatedRequest(v2Url(action, uid), sessionId, sessionToken)
                    .post(new FormBody.Builder().add(KEY_VALUE, value).build()).build();
        } catch (Exception error) {
            callback.onError("参数编码错误: " + error.getMessage());
            return;
        }
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                callback.onError("网络请求失败: " + error.getMessage());
            }

            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (ResponseBody body = response.body()) {
                    String responseData = body == null ? "" : body.string();
                    if (response.code() == 401) {
                        callback.onError("资料服务暂时无法验证登录会话");
                        return;
                    }
                    JSONObject json = new JSONObject(responseData);
                    if (!response.isSuccessful() || json.optInt("code") != 200) {
                        callback.onError(json.optString("msg", "修改失败: " + response.code()));
                        return;
                    }
                    if (nickname) saveNick(value); else saveShuo(value);
                    callback.onSuccess();
                } catch (Exception error) {
                    callback.onError("解析响应失败: " + error.getMessage());
                }
            }
        });
    }

    // 修复：移除本地保存逻辑，由调用者处理
    public void setAvatar(File avatarFile, @NonNull final SetCallback callback) {
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        showProgressDialog("正在上传头像...");

        String uid = getUid();
        if (isV2Session()) {
            setV2Avatar(uid, avatarFile, callback);
            return;
        }
        String cookie = getCookie();
        if (uid == null || uid.isEmpty() || cookie == null || cookie.isEmpty()) {
            hideProgressDialog();
            callback.onError("未获取到用户信息");
            return;
        }

        try {
            String url = "https://service.typheye.cn/src/up.php?uid=" + URLEncoder.encode(uid, "UTF-8");
            MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");
            RequestBody fileBody = RequestBody.create(avatarFile, MEDIA_TYPE_JPEG);
            MultipartBody multipartBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("file", "avatar.jpg", fileBody) // 可替换为 avatarFile.getName()
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(multipartBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("上传失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            if ("上传成功".equals(responseData)) {
                                // ✅ 关键修复：由调用者处理本地缓存
                                callback.onSuccess();
                            } else {
                                callback.onError("上传失败: " + responseData);
                            }
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("上传失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    private void setV2Avatar(String uid, File avatarFile, @NonNull final SetCallback callback) {
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid == null || uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            hideProgressDialog();
            callback.onError("登录会话无效");
            return;
        }
        if (!avatarFile.isFile() || avatarFile.length() == 0 || avatarFile.length() > 3L * 1024L * 1024L) {
            hideProgressDialog();
            callback.onError("头像文件需为 3 MB 以内的 JPG、PNG 或 WebP 图片");
            return;
        }
        try {
            RequestBody fileBody = RequestBody.create(avatarFile, MediaType.parse("image/jpeg"));
            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("avatar", "avatar.jpg", fileBody).build();
            Request request = authenticatedRequest(v2Url(ACTION_SET_AVATAR_V2, uid),
                    sessionId, sessionToken).post(body).build();
            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                    hideProgressDialog();
                    callback.onError("上传失败: " + error.getMessage());
                }

                @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    try (ResponseBody responseBody = response.body()) {
                        JSONObject json = new JSONObject(responseBody == null ? "" : responseBody.string());
                        if (response.isSuccessful() && json.optInt("code") == 200) {
                            callback.onSuccess();
                        } else {
                            callback.onError(json.optString("msg", "上传失败: " + response.code()));
                        }
                    } catch (Exception error) {
                        callback.onError("解析响应失败: " + error.getMessage());
                    }
                }
            });
        } catch (Exception error) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + error.getMessage());
        }
    }

    public void getAvatarInfo(String localMd5, @NonNull final AvatarInfoCallback callback) {
        String uid = getUid();
        if (uid == null || uid.isEmpty()) {
            callback.onError("未获取到用户信息");
            return;
        }
        try {
            String url;
            Request.Builder builder;
            if (isV2Session()) {
                String sessionId = getSessionId();
                String sessionToken = getSessionToken();
                if (sessionId.isEmpty() || sessionToken.isEmpty()) {
                    callback.onError("登录会话无效");
                    return;
                }
                url = v2Url(ACTION_GET_AVATAR_V2, uid);
                builder = authenticatedRequest(url, sessionId, sessionToken);
            } else {
                url = BASE_URL + "?type=get_avatar&uid=" + URLEncoder.encode(uid, "UTF-8")
                        + "&md5=" + URLEncoder.encode(localMd5, "UTF-8");
                builder = new Request.Builder().url(url).headers(clientHeaders());
            }
            client.newCall(builder.get().build()).enqueue(new Callback() {
                @Override public void onFailure(@NonNull Call call, @NonNull IOException error) {
                    callback.onError("头像信息获取失败: " + error.getMessage());
                }

                @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                    try (ResponseBody body = response.body()) {
                        JSONObject json = new JSONObject(body == null ? "" : body.string());
                        if (!response.isSuccessful() || json.optInt("code") != 200) {
                            callback.onError(json.optString("msg", "头像信息获取失败: " + response.code()));
                            return;
                        }
                        JSONObject info = json.getJSONObject("info");
                        boolean hasAvatar = info.optBoolean("isHave", false);
                        String remoteMd5 = info.optString("md5", "");
                        boolean shouldDownload = isV2Session()
                                ? hasAvatar && (remoteMd5.isEmpty() || !remoteMd5.equalsIgnoreCase(localMd5))
                                : hasAvatar && !info.optBoolean("isUpdated", false);
                        callback.onSuccess(hasAvatar, shouldDownload, info.optString("url", ""));
                    } catch (Exception error) {
                        callback.onError("头像信息解析失败: " + error.getMessage());
                    }
                }
            });
        } catch (Exception error) {
            callback.onError("参数编码错误: " + error.getMessage());
        }
    }

    public interface AvatarInfoCallback {
        void onSuccess(boolean hasAvatar, boolean shouldDownload, String url);
        void onError(String message);
    }

    // 新增：计算postLoginRequest的token
    private String calculateToken_postLoginRequest(String requestId, boolean isApprove, long time) {
        String LoginReqType = isApprove? ACTION_APPROVE_LOGIN_REQUEST : ACTION_REJECT_LOGIN_REQUEST;
        String tokenString = "type=" + LoginReqType + "&requestId=" + requestId + "&time=" + time;
        return md5(tokenString);
    }

    // 新增：计算goConfirmLoginRequest的token
    private String calculateToken_goConfirmLoginRequest(String uid, String cookie, String requestId, long time) {
        String tokenString = "type=" + ACTION_CONFIRM_LOGIN_REQUEST + "&uid=" + uid + "&cookie=" + cookie + "&requestId=" + requestId + "&time=" + time;
        return md5(tokenString);
    }

    // 新增：计算set_nick的token
    private String calculateToken_setNick(String uid, String cookie, String value, long time) {
        String tokenString = "type=" + ACTION_SET_NICK + "&uid=" + uid + "&cookie=" + cookie + "&value=" + value + "&time=" + time;
        return md5(tokenString);
    }

    // 新增：计算set_shuo的token
    private String calculateToken_setShuo(String uid, String cookie, String value, long time) {
        String tokenString = "type=" + ACTION_SET_SHUO + "&uid=" + uid + "&cookie=" + cookie + "&value=" + value + "&time=" + time;
        return md5(tokenString);
    }

    // 新增：保存昵称到本地（与setNick()成功后调用）
    // 新增：保存昵称到本地（与setNick()成功后调用），返回boolean表示操作是否成功
    private void saveNick(String nick) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_NICK, nick).apply();
    }

    // 新增：保存签名到本地（与setShuo()成功后调用），返回boolean表示操作是否成功
    private void saveShuo(String shuo) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(PREFS_SHUO, shuo).apply();
    }

    // 新增：保存头像到本地缓存（与setAvatar()成功后调用），返回boolean表示操作是否成功
    public void saveAvatar(Bitmap avatar) {
        String uid = getUid();
        if (uid != null && !uid.isEmpty()) {
            saveAvatarToCache(context, uid, avatar);
        }
    }

    private boolean isNetworkAvailable() {
        if (context == null) return true;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            // 网络信息为空 -> 无网络
            if (networkInfo == null) {
                return true;
            }

            // API 21+ 使用 isConnectedOrConnecting() (更安全，包含正在连接状态)
            // API <21 仍使用 isConnected() (兼容旧版)
            return !networkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            return true; // 发生异常时默认认为无网络
        }
    }
    // 新增：设置回调接口
    public interface SetCallback {
        void onSuccess();

        void onError(String message);
    }


    // 添加方法：generateLoginRequest
    public void generateLoginRequest(@NonNull final GenerateRequestCallback callback) {
        hideProgressDialog();

        // 网络检查在主线程
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

        // 网络请求在后台线程
        showProgressDialog("正在生成登录请求...");
        new Thread(() -> {
            try {
                String url = BASE_URL + "?type=" + ACTION_GENERATE_LOGIN_REQUEST_V2;

                Request request = new Request.Builder()
                        .url(url)
                        .headers(clientHeaders())
                        .get()
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);

                    if (json.getInt("code") == 200) {
                        JSONObject info = json.optJSONObject("info");
                        String requestId = json.optString("request_id",
                                info == null ? "" : info.optString("request_id", ""));
                        String qrCodeUrl = json.optString("qr_code_url",
                                info == null ? "" : info.optString("qr_code_url", ""));
                        if (requestId.isEmpty() || qrCodeUrl.isEmpty()) {
                            throw new JSONException("响应缺少 request_id 或 qr_code_url");
                        }

                        // 在主线程回调
                        new Handler(Looper.getMainLooper()).post(() -> {
                            dismissProgressDialogForReplacement();
                            callback.onSuccess(requestId, qrCodeUrl);
                        });
                    } else {
                        final String errorMsg = json.optString("msg", "请求失败");
                        new Handler(Looper.getMainLooper()).post(() -> callback.onError(errorMsg));
                    }
                } else {
                    final String errorMsg = "请求失败: " + response.code();
                    new Handler(Looper.getMainLooper()).post(() -> callback.onError(errorMsg));
                }
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError("生成请求失败: " + e.getMessage()));
            } finally {
                hideProgressDialog();
            }
        }).start();
    }

    // 添加方法：checkLoginRequestStatus
    public void checkLoginRequestStatus(String requestId, @NonNull final CheckStatusCallback callback) {
        hideProgressDialog();

        // 1. 快速网络检测
        if (isNetworkAvailable()) {
            new Handler(Looper.getMainLooper()).post(() -> {
                Toast.makeText(context, "网络不可用，请检查网络", Toast.LENGTH_SHORT).show();
                callback.onError("网络不可用");
            });
            return;
        }

//        showProgressDialog("正在检查请求状态...");

        try {
            String url = BASE_URL + "?type=" + ACTION_CHECK_LOGIN_REQUEST_STATUS_V2
                    + "&" + KEY_REQUEST_ID + "=" + URLEncoder.encode(requestId, "UTF-8");

            Request request = new Request.Builder()
                    .url(url)
                    .headers(clientHeaders())
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    hideProgressDialog();
                    callback.onError("请求失败: " + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    hideProgressDialog();
                    if (response.isSuccessful()) {
                        try {
                            String responseData = response.body().string();
                            JSONObject json = new JSONObject(responseData);

                            if (json.getInt("code") == 200) {
                                String status = json.getString("status");
                                if ("approved".equals(status)) {
                                    JSONObject session = json.optJSONObject("session");
                                    JSONObject info = json.optJSONObject("info");
                                    if (info == null) info = json.optJSONObject("user");
                                    String uid = firstNonEmpty(json.optString(KEY_UID, ""),
                                            info == null ? "" : info.optString(KEY_UID, ""),
                                            session == null ? "" : session.optString(KEY_UID, ""));
                                    String sessionId = session == null ? ""
                                            : session.optString(PREFS_SESSION_ID, "");
                                    String sessionToken = session == null ? ""
                                            : session.optString(PREFS_SESSION_TOKEN, "");
                                    if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
                                        callback.onError("授权响应缺少用户或会话信息，请重新扫码");
                                        return;
                                    }
                                    String email = firstNonEmpty(json.optString(KEY_EMAIL, ""),
                                            info == null ? "" : info.optString(KEY_EMAIL, ""));
                                    String nick = firstNonEmpty(json.optString(KEY_NICK, ""),
                                            info == null ? "" : info.optString(KEY_NICK, ""));
                                    String shuo = firstNonEmpty(json.optString(KEY_SHUO, ""),
                                            info == null ? "" : info.optString(KEY_SHUO, ""));
                                    if (!saveLoginData(LoginResult.v2(uid, email, nick, shuo,
                                            sessionId, sessionToken))) {
                                        callback.onError("无法安全保存登录会话");
                                        return;
                                    }
                                }
                                callback.onSuccess(status);
                            } else {
                                callback.onError(json.optString("msg", "请求失败"));
                            }
                        } catch (JSONException e) {
                            callback.onError("解析响应失败: " + e.getMessage());
                        } catch (IOException e) {
                            callback.onError("读取响应失败: " + e.getMessage());
                        }
                    } else {
                        callback.onError("请求失败: " + response.code());
                    }
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            callback.onError("参数编码错误: " + e.getMessage());
        }
    }

    private String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.isEmpty()) return value;
        }
        return "";
    }

    // 添加接口：GenerateRequestCallback
    public interface GenerateRequestCallback {
        void onSuccess(String requestId, String qrCodeUrl);

        void onError(String message);
    }

    // 添加接口：CheckStatusCallback
    public interface CheckStatusCallback {
        void onSuccess(String status);

        void onError(String message);
    }

    public interface WebLoginTicketCallback {
        void onSuccess(String ticket, String authUrl, String redirectPath);

        void onError(String message);
    }

    /** Creates a short-lived, single-use ticket without exposing the V2 session to WebView. */
    public void createWebLoginTicket(String redirectPath,
                                     @NonNull final WebLoginTicketCallback callback) {
        if (!isV2Session()) {
            callback.onError("当前账户不是 V2 会话");
            return;
        }
        String uid = getUid();
        String sessionId = getSessionId();
        String sessionToken = getSessionToken();
        if (uid.isEmpty() || sessionId.isEmpty() || sessionToken.isEmpty()) {
            callback.onError("登录会话不完整，请重新登录");
            return;
        }
        String safeRedirect = redirectPath == null || !redirectPath.startsWith("/")
                || redirectPath.startsWith("//") ? "/site/user/center/" : redirectPath;
        try {
            Request request = authenticatedRequest(
                    v2Url(ACTION_CREATE_WEB_LOGIN_TICKET_V2, uid), sessionId, sessionToken)
                    .post(new FormBody.Builder()
                            .add("redirect_path", safeRedirect)
                            .add("client_nonce", UUID.randomUUID().toString())
                            .build())
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    callback.onError("无法同步网页登录状态，请检查网络");
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) {
                    try (Response closedResponse = response) {
                        String responseData = closedResponse.body() == null
                                ? "" : closedResponse.body().string();
                        JSONObject json = responseData.isEmpty()
                                ? new JSONObject() : new JSONObject(responseData);
                        if (!closedResponse.isSuccessful() || json.optInt("code") != 200) {
                            callback.onError(json.optString("msg",
                                    "网页登录同步失败: " + closedResponse.code()));
                            return;
                        }
                        JSONObject payload = json.optJSONObject("info");
                        if (payload == null) payload = json.optJSONObject("data");
                        String ticket = firstNonEmpty(json.optString("ticket", ""),
                                payload == null ? "" : payload.optString("ticket", ""));
                        String authUrl = firstNonEmpty(json.optString("auth_url", ""),
                                json.optString("exchange_url", ""),
                                payload == null ? "" : payload.optString("auth_url", ""),
                                payload == null ? "" : payload.optString("exchange_url", ""),
                                WEB_AUTH_URL);
                        String confirmedRedirect = firstNonEmpty(
                                json.optString("redirect_path", ""),
                                payload == null ? "" : payload.optString("redirect_path", ""),
                                safeRedirect);
                        if (ticket.isEmpty()) {
                            callback.onError("网页登录票据响应不完整");
                            return;
                        }
                        callback.onSuccess(ticket, authUrl, confirmedRedirect);
                    } catch (Exception e) {
                        callback.onError("无法解析网页登录票据");
                    }
                }
            });
        } catch (Exception e) {
            callback.onError("无法创建网页登录票据");
        }
    }

    // 添加方法：calculateToken_generateLoginRequest
    private String calculateToken_generateLoginRequest(long time) {
        String tokenString = "type=generate_login_request&time=" + time;
        return md5(tokenString);
    }

    // 添加方法：calculateToken_checkLoginRequestStatus
    private String calculateToken_checkLoginRequestStatus(String requestId, long time) {
        String tokenString = "type=check_login_request_status&requestId=" + requestId + "&time=" + time;
        return md5(tokenString);
    }

    /**
     * 为WebActivity设置登录Cookie
     */
    public void setWebViewCookies() {
        setWebViewCookies(null);
    }

    public void setWebViewCookies(Runnable onComplete) {
        if (isV2Session()) {
            clearWebViewCookies(onComplete);
            return;
        }
        try {
            String uid = getUid();
            String cookie = getCookie();
            android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);

            // 针对typheye.cn域设置Cookie
            String domain = "typheye.cn";

            // 1. 设置 TypheyeCookie = md5(md5(cookie + '#' + uid))
            String typheyeCookieValue = cookie + "#" + uid;
            String typheyeCookie = md5(md5(typheyeCookieValue));
            String cookie1 = "TypheyeCookie=" + typheyeCookie
                    + "; Domain=" + domain
                    + "; Path=/"
                    + "; Secure"
                    + "; Max-Age=360000";
            // 2. 设置 TypheyeUserCookie
            String cookie2 = "TypheyeUserCookie=" + cookie
                    + "; Domain=" + domain
                    + "; Path=/"
                    + "; Secure"
                    + "; Max-Age=360000";
            // 3. 设置 TypheyeUserUidCookie
            String cookie3 = "TypheyeUserUidCookie=" + uid
                    + "; Domain=" + domain
                    + "; Path=/"
                    + "; Secure"
                    + "; Max-Age=360000";
            cookieManager.setCookie("https://typheye.cn", cookie1, firstSaved ->
                    cookieManager.setCookie("https://typheye.cn", cookie2, secondSaved ->
                            cookieManager.setCookie("https://typheye.cn", cookie3, thirdSaved -> {
                                cookieManager.flush();
                                if (onComplete != null) onComplete.run();
                            })));
        } catch (Exception e) {
            Log.e("tAccUtils", "Failed to set WebView cookies", e);
            if (onComplete != null) new Handler(Looper.getMainLooper()).post(onComplete);
        }
    }

    public boolean hasWebViewSessionCookie() {
        try {
            SharedPreferences securePrefs = getSecurePreferences();
            String sourceSessionId = securePrefs == null ? ""
                    : securePrefs.getString(PREFS_WEB_SESSION_SOURCE_ID, "");
            if (sourceSessionId.isEmpty() || !sourceSessionId.equals(getSessionId())) return false;
            String cookies = android.webkit.CookieManager.getInstance()
                    .getCookie("https://account.typheye.cn");
            if (cookies == null || cookies.isEmpty()) return false;
            for (String cookie : cookies.split(";")) {
                if (cookie.trim().startsWith("TypheyeWebSession=")) {
                    return cookie.trim().length() > "TypheyeWebSession=".length();
                }
            }
        } catch (Exception e) {
            Log.w("tAccUtils", "Unable to inspect WebView session cookie", e);
        }
        return false;
    }

    public boolean markWebViewSessionSynchronized() {
        SharedPreferences securePrefs = getSecurePreferences();
        String sessionId = getSessionId();
        return securePrefs != null && !sessionId.isEmpty() && securePrefs.edit()
                .putString(PREFS_WEB_SESSION_SOURCE_ID, sessionId)
                .commit();
    }

    private void clearWebViewCookies() {
        clearWebViewCookies(null);
    }

    private void clearWebViewCookies(Runnable onComplete) {
        try {
            SharedPreferences securePrefs = getSecurePreferences();
            if (securePrefs != null) securePrefs.edit().remove(PREFS_WEB_SESSION_SOURCE_ID).apply();
            android.webkit.CookieManager cookieManager = android.webkit.CookieManager.getInstance();
            String expired = "; Domain=typheye.cn; Path=/; Max-Age=0; Secure";
            cookieManager.setCookie("https://typheye.cn", "TypheyeCookie=" + expired, firstCleared ->
                    cookieManager.setCookie("https://typheye.cn", "TypheyeUserCookie=" + expired, secondCleared ->
                            cookieManager.setCookie("https://typheye.cn", "TypheyeUserUidCookie=" + expired, thirdCleared -> {
                                String webExpired = "; Domain=.typheye.cn; Path=/; Max-Age=0; Secure";
                                cookieManager.setCookie("https://account.typheye.cn",
                                        "TypheyeWebSession=" + webExpired, webSessionCleared -> {
                                            cookieManager.flush();
                                            if (onComplete != null) onComplete.run();
                                        });
                            })));
        } catch (Exception e) {
            Log.w("tAccUtils", "Unable to clear WebView account cookies", e);
            if (onComplete != null) new Handler(Looper.getMainLooper()).post(onComplete);
        }
    }
}
