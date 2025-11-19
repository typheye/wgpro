package com.typheye.wgpro.ui.function.account;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.account.accFragments.AccGrantFragment;
import com.typheye.wgpro.ui.function.account.accFragments.AccLoginFragment;
import com.typheye.wgpro.ui.function.account.accFragments.AccLoginQRFragment;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.ImageUtils;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AccMangerActivity extends AppCompatActivity {

    public String grant_requestId = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_accmanager);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        // 获取传入的参数
        Intent intent = getIntent();
        String target = intent.getStringExtra("TARGET_FRAGMENT");
        grant_requestId = intent.getStringExtra("REQUEST_ID");

        // 根据参数决定加载哪个 Fragment
        Fragment fragment;
        if ("login".equals(target)) {
            fragment = new AccLoginFragment();
            toolbar.setTitle("登录");
        } else if ("login_qr".equals(target)) {
            fragment = new AccLoginQRFragment();
            toolbar.setTitle("登录");
        } else if ("edit".equals(target)) {
            fragment = new AccEditFragment();
            toolbar.setTitle("编辑");
        } else if ("grant".equals(target)) {
            fragment = new AccGrantFragment();
            toolbar.setTitle("授权");
        } else {
            fragment = new AccLoginFragment();
            toolbar.setTitle("登录");
        }

        // 替换 Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class AccEditFragment extends PreferenceFragmentCompat {
        private tAccUtils accUtils;
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        // ✅ 新增：ActivityResultLauncher
        private ActivityResultLauncher<Intent> activityResultLauncher;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.account_preferences, rootKey);
            accUtils = new tAccUtils(requireContext());

            // ✅ 注册 Activity Result Launcher
            registerActivityResultLauncher();

            // 初始化并设置监听器
            initPreferenceListeners();
            updatePreferences();
        }

        private void initPreferenceListeners() {
            // 昵称监听
            EditTextPreference nickPref = findPreference("account_nick");
            if (nickPref != null) {
                nickPref.setOnPreferenceChangeListener((preference, newValue) -> {
                    String trimmedNick = newValue.toString().trim();
                    if (!trimmedNick.isEmpty()) {
                        saveNick(trimmedNick);
                        return true;
                    }
                    // 输入只有空格时，返回false阻止保存
                    return false;
                });
            }

            // 说说监听
            EditTextPreference shuoPref = findPreference("account_shuo");
            if (shuoPref != null) {
                shuoPref.setOnPreferenceChangeListener((preference, newValue) -> {
                    // ✅ 关键修改：始终去除空格，即使结果为空
                    String trimmedShuo = newValue.toString().trim();
                    saveShuo(trimmedShuo); // 保存trim后的字符串（可能为空）
                    return true;
                });
            }

            // 头像点击
            Preference avatarPref = findPreference("account_avatar");
            if (avatarPref != null) {
                avatarPref.setOnPreferenceClickListener(preference -> {
                    openImagePicker();
                    return true;
                });
            }
        }

        private void updatePreferences() {
            // 更新所有偏好设置
            updateNick();
            updateShuo();
            updateAvatar();
        }

        private void updateNick() {
            EditTextPreference nickPref = findPreference("account_nick");
            if (nickPref != null) {
                String nick = accUtils.getNick();
                nickPref.setText(nick != null ? nick : "");
            }
        }

        private void updateShuo() {
            EditTextPreference shuoPref = findPreference("account_shuo");
            if (shuoPref != null) {
                String shuo = accUtils.getShuo();
                shuoPref.setText(shuo != null ? shuo : "");
            }
        }

        private void updateAvatar() {
            Preference avatarPref = findPreference("account_avatar");
            if (avatarPref != null && accUtils.getUid() != null) {
                updateAvatarIcon(avatarPref, accUtils.getUid());
            }
        }

        private void saveNick(String nick) {
            accUtils.setNick(nick, new tAccUtils.SetCallback() {
                @Override
                public void onSuccess() {
                    mainHandler.post(() -> {
                        updateNick();
                        showSuccessToast("昵称更新成功");
                    });
                }

                @Override
                public void onError(String message) {
                    mainHandler.post(() -> showErrorToast("昵称更新失败: " + message));
                }
            });
        }

        private void saveShuo(String shuo) {
            accUtils.setShuo(shuo, new tAccUtils.SetCallback() {
                @Override
                public void onSuccess() {
                    mainHandler.post(() -> {
                        updateShuo();
                        showSuccessToast("简介更新成功");
                    });
                }

                @Override
                public void onError(String message) {
                    mainHandler.post(() -> showErrorToast("简介更新失败: " + message));
                }
            });
        }

        @SuppressLint("IntentReset")
        private void openImagePicker() {
            @SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            // ✅ 使用 launcher 启动，而不是 startActivityForResult
            activityResultLauncher.launch(intent);
        }

        // ✅ 新增：注册 launcher
        private void registerActivityResultLauncher() {
            activityResultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null && data.getData() != null) {
                                Uri selectedImage = data.getData();
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                            requireContext().getContentResolver(), selectedImage);
                                    if (bitmap != null) {
                                        File avatarFile = saveBitmapToFile(bitmap);
                                        if (avatarFile != null) {
                                            accUtils.setAvatar(avatarFile, new tAccUtils.SetCallback() {
                                                @Override
                                                public void onSuccess() {
                                                    mainHandler.post(() -> {
                                                        accUtils.saveAvatar(bitmap);
                                                        updateAvatar();
                                                        showSuccessToast("头像更新成功");
                                                    });
                                                }

                                                @Override
                                                public void onError(String message) {
                                                    mainHandler.post(() -> showErrorToast("头像更新失败: " + message));
                                                }
                                            });
                                        } else {
                                            mainHandler.post(() -> showErrorToast("头像保存失败"));
                                        }
                                        bitmap.recycle();
                                    }
                                } catch (IOException e) {
                                    mainHandler.post(() -> showErrorToast("图片处理失败"));
                                }
                            }
                        }
                    }
            );
        }

        // ✅ 修改：使用getFilesDir()而不是getCacheDir()
        private File saveBitmapToFile(Bitmap bitmap) {
            try {
                File cacheDir = requireContext().getCacheDir();
                File avatarFile = new File(cacheDir, "temp_avatar.jpg");
                FileOutputStream out = new FileOutputStream(avatarFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                return avatarFile;
            } catch (IOException e) {
                return null;
            }
        }

        private void updateAvatarIcon(Preference avatarPref, String uid) {
            if (uid == null || uid.isEmpty()) {
                avatarPref.setIcon(null);
                return;
            }

            // ✅ 修改：使用getFilesDir()而不是getCacheDir()
            File avatarFile = new File(requireContext().getFilesDir(), "avatar_" + uid + ".jpg");
            if (avatarFile.exists()) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(avatarFile.getAbsolutePath(), options);
                    if (bitmap != null) {
                        Bitmap roundedBitmap = ImageUtils.roundBitmap(bitmap);
                        if (roundedBitmap != null) {
                            avatarPref.setIcon(new BitmapDrawable(getResources(), roundedBitmap));
                            bitmap.recycle();
                            return;
                        }
                    }
                } catch (Exception e) {
                    return;
                }
            }
            avatarPref.setIcon(null);
        }

        private void showSuccessToast(String message) {
            android.util.Log.d("AccEdit", "Success: " + message);
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("修改成功")
                    .setMessage(message)
                    .setPositiveButton("确定", null)
                    .show();
        }

        private void showErrorToast(String message) {
            android.util.Log.e("AccEdit", "Error: " + message);
            new MaterialAlertDialogBuilder(requireContext())
                    .setTitle("修改失败")
                    .setMessage(message)
                    .setPositiveButton("确定", null)
                    .show();
        }
    }
}