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
import androidx.appcompat.app.AlertDialog;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.R;

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
import java.util.concurrent.TimeUnit;

public class tAccUtils {
    private static final String BASE_URL = "https://service.typheye.cn/api.php";
    private static final String ACTION_LOGIN = "login2";
    private static final String ACTION_GET_DATA_UPDATE = "get_data_update";
    private static final String ACTION_GET_DATA_UPDATE_V2 = "get_data_update2";
    private static final String ACTION_SESSION_LOGOUT_V2 = "session_logout2";
    private static final String ACTION_SET_NICK = "set_nick";
    private static final String ACTION_SET_SHUO = "set_shuo";
    private static final String ACTION_CONFIRM_LOGIN_REQUEST = "confirm_login_request";
    private static final String ACTION_APPROVE_LOGIN_REQUEST = "approve_login_request";
    private static final String ACTION_REJECT_LOGIN_REQUEST = "reject_login_request";

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
    private static final String KEY_REQUEST_ID = "request_id";
    private static final String ACTION_UPDATE_USER_DATA = "get_user_data";
    private final Context context;
    private final OkHttpClient client;

    private static AlertDialog progressDialog;

    public tAccUtils(Context context) {
        this.context = context;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
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
                                    JSONObject session = json.getJSONObject("session");
                                    String sessionId = session.getString(PREFS_SESSION_ID);
                                    String sessionToken = session.getString(PREFS_SESSION_TOKEN);

                                    LoginResult result = LoginResult.v2(uid, email, nick, shuo, sessionId, sessionToken);
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
            hideProgressDialog();
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            builder.setTitle("请稍候");

            View view = View.inflate(context, R.layout.progress_dialog, null);
            view.findViewById(android.R.id.progress);
            TextView messageTextView = view.findViewById(android.R.id.message);
            messageTextView.setText(message);

            builder.setView(view);
            builder.setCancelable(false);

            progressDialog = builder.create();
            progressDialog.show();
        });
    }

    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
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
        if (isV2Session()) {
            return !getSessionId().isEmpty() && !getSessionToken().isEmpty();
        }
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
                                    new Handler(Looper.getMainLooper()).post(() -> new MaterialAlertDialogBuilder(context)
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
            clearLocalLoginData();
            callback.onSuccess(new UserDataUpdateResult(false, false, false, false));
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
                        boolean valid = info.optInt("v0", 1) == 0;
                        if (!valid) {
                            clearLocalLoginData();
                            new Handler(Looper.getMainLooper()).post(() ->
                                    new MaterialAlertDialogBuilder(context)
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
                    + "&" + KEY_COOKIE + "=" + URLEncoder.encode(cookie, "UTF-8")
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


    // 新增：更新数据
    public void updateUserData(@NonNull final SetCallback callback) {
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
                long time = System.currentTimeMillis();
                String token = calculateToken_generateLoginRequest(time);

                String url = BASE_URL + "?type=generate_login_request"
                        + "&" + KEY_TIME + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8")
                        + "&" + KEY_TOKEN + "=" + URLEncoder.encode(token, "UTF-8");

                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                Response response = client.newCall(request).execute();

                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    JSONObject json = new JSONObject(responseData);

                    if (json.getInt("code") == 200) {
                        String requestId = json.getString("request_id");
                        String qrCodeUrl = json.getString("qr_code_url");

                        // 在主线程回调
                        new Handler(Looper.getMainLooper()).post(() -> callback.onSuccess(requestId, qrCodeUrl));
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

        long time = System.currentTimeMillis();
        String token = calculateToken_checkLoginRequestStatus(requestId, time);

        try {
            String url = BASE_URL + "?type=check_login_request_status"
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
                                String status = json.getString("status");
                                if ("approved".equals(status)) {
                                    JSONObject info = json.getJSONObject("info");
                                    String cookie = info.getString(KEY_COOKIE);
                                    String uid = info.getString(KEY_UID);
                                    String email = info.getString(KEY_EMAIL);
                                    String nick = info.optString(KEY_NICK, "");
                                    String shuo = info.optString(KEY_SHUO, "");
                                    LoginResult result = new LoginResult(cookie, uid, email, nick, shuo);
                                    saveLoginData(result);
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
        if (isV2Session()) return;
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
            cookieManager.setCookie("https://typheye.cn", cookie1);

            // 2. 设置 TypheyeUserCookie
            String cookie2 = "TypheyeUserCookie=" + cookie
                    + "; Domain=" + domain
                    + "; Path=/"
                    + "; Secure"
                    + "; Max-Age=360000";
            cookieManager.setCookie("https://typheye.cn", cookie2);

            // 3. 设置 TypheyeUserUidCookie
            String cookie3 = "TypheyeUserUidCookie=" + uid
                    + "; Domain=" + domain
                    + "; Path=/"
                    + "; Secure"
                    + "; Max-Age=360000";
            cookieManager.setCookie("https://typheye.cn", cookie3);

            // 强制同步
            cookieManager.flush();

            Log.d("tAccUtils", "Cookies set for WebView - UID: " + uid);
        } catch (Exception e) {
            Log.e("tAccUtils", "Failed to set WebView cookies", e);
        }
    }
}
