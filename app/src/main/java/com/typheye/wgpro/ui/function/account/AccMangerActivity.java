package com.typheye.wgpro.ui.function.account;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.account.accFragments.AccGrantFragment;
import com.typheye.wgpro.ui.function.account.accFragments.AccLoginFragment;
import com.typheye.wgpro.ui.function.account.accFragments.AccLoginQRFragment;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class AccMangerActivity extends AppCompatActivity {

    public String grant_requestId = "";
    private OnBackPressedCallback compatibilityBackCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_accmanager);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout), findViewById(R.id.fragment_container));

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        compatibilityBackCallback = new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() { finish(); }
        };
        getOnBackPressedDispatcher().addCallback(this, compatibilityBackCallback);

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
            toolbar.setTitle("编辑资料");
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

    @Override
    protected void onResume() {
        super.onResume();
        compatibilityBackCallback.setEnabled(!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false));
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        ViewCompat.requestApplyInsets(findViewById(R.id.app_bar_layout));
        ViewCompat.requestApplyInsets(findViewById(R.id.fragment_container));
    }

    public static class AccEditFragment extends Fragment {
        private tAccUtils accUtils;
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private ActivityResultLauncher<Intent> activityResultLauncher;
        private ShapeableImageView avatarView;
        private TextView avatarTextView;
        private TextView nicknameView;
        private TextView bioView;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            accUtils = new tAccUtils(requireContext());
            registerActivityResultLauncher();
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_account_edit_md3, container, false);
            avatarView = view.findViewById(R.id.account_avatar);
            avatarTextView = view.findViewById(R.id.account_avatar_text);
            nicknameView = view.findViewById(R.id.account_nickname);
            bioView = view.findViewById(R.id.account_bio);

            View.OnClickListener avatarClick = v -> openImagePicker();
            view.findViewById(R.id.row_avatar).setOnClickListener(avatarClick);
            view.findViewById(R.id.row_nickname).setOnClickListener(v ->
                    showEditDialog("修改昵称", "昵称", accUtils.getNick(), false));
            view.findViewById(R.id.row_bio).setOnClickListener(v ->
                    showEditDialog("修改简介", "简介", accUtils.getShuo(), true));
            updatePreferences();
            return view;
        }

        private void showEditDialog(String title, String hint, String currentValue, boolean allowEmpty) {
            View content = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edittext, null, false);
            TextInputLayout inputLayout = content.findViewById(R.id.textInputLayout);
            TextInputEditText input = content.findViewById(R.id.editText);
            inputLayout.setHint(hint);
            input.setText(currentValue == null ? "" : currentValue);
            input.setSelection(input.length());
            input.setSingleLine(!allowEmpty);
            input.setMaxLines(allowEmpty ? 4 : 1);
            input.setInputType(allowEmpty
                    ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    : InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

            WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                    .setTitle(title)
                    .setView(content)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("保存", null)
                    .create();
            dialog.setOnShowListener(ignored -> {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    String value = input.getText() == null ? "" : input.getText().toString().trim();
                    if (!allowEmpty && value.isEmpty()) {
                        inputLayout.setError("昵称不能为空");
                        return;
                    }
                    inputLayout.setError(null);
                    dialog.dismiss();
                    if (allowEmpty) saveShuo(value); else saveNick(value);
                });
                input.requestFocus();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            });
            dialog.show();
        }

        private void updatePreferences() {
            // 更新所有偏好设置
            updateNick();
            updateShuo();
            updateAvatar();
        }

        private void updateNick() {
            String nick = accUtils.getNick();
            nicknameView.setText(nick == null || nick.trim().isEmpty() ? "未设置" : nick);
        }

        private void updateShuo() {
            String shuo = accUtils.getShuo();
            bioView.setText(shuo == null || shuo.trim().isEmpty() ? "还没有填写简介" : shuo);
        }

        private void updateAvatar() {
            updateAvatarIcon(accUtils.getUid());
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
            activityResultLauncher.launch(intent);
        }

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
                                                         Bitmap cachedAvatar = BitmapFactory.decodeFile(
                                                                 avatarFile.getAbsolutePath());
                                                         if (cachedAvatar != null) {
                                                             accUtils.saveAvatar(cachedAvatar);
                                                             cachedAvatar.recycle();
                                                         }
                                                         bitmap.recycle();
                                                         updateAvatar();
                                                        showSuccessToast("头像更新成功");
                                                    });
                                                }

                                                @Override
                                                public void onError(String message) {
                                                    mainHandler.post(() -> {
                                                        bitmap.recycle();
                                                        showErrorToast("头像更新失败: " + message);
                                                    });
                                                }
                                            });
                                        } else {
                                            bitmap.recycle();
                                            mainHandler.post(() -> showErrorToast("头像保存失败"));
                                        }
                                    }
                                } catch (IOException e) {
                                    mainHandler.post(() -> showErrorToast("图片处理失败"));
                                }
                            }
                        }
                    }
            );
        }

        private File saveBitmapToFile(Bitmap bitmap) {
            try {
                File cacheDir = requireContext().getCacheDir();
                File avatarFile = new File(cacheDir, "temp_avatar.jpg");
                int maxSide = Math.max(bitmap.getWidth(), bitmap.getHeight());
                float scale = maxSide > 1024 ? 1024f / maxSide : 1f;
                Bitmap uploadBitmap = scale < 1f ? Bitmap.createScaledBitmap(bitmap,
                        Math.max(1, Math.round(bitmap.getWidth() * scale)),
                        Math.max(1, Math.round(bitmap.getHeight() * scale)), true) : bitmap;
                try (FileOutputStream out = new FileOutputStream(avatarFile)) {
                    uploadBitmap.compress(Bitmap.CompressFormat.JPEG, 88, out);
                }
                if (uploadBitmap != bitmap) uploadBitmap.recycle();
                return avatarFile;
            } catch (IOException e) {
                return null;
            }
        }

        private void updateAvatarIcon(String uid) {
            if (uid == null || uid.isEmpty()) {
                showTextAvatar();
                return;
            }

            File avatarFile = new File(requireContext().getFilesDir(), "avatar_" + uid + ".jpg");
            if (avatarFile.exists()) {
                try {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(avatarFile.getAbsolutePath(), options);
                    if (bitmap != null) {
                        avatarView.setImageBitmap(bitmap);
                        avatarView.setVisibility(View.VISIBLE);
                        avatarTextView.setVisibility(View.GONE);
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            }
            showTextAvatar();
        }

        private void showTextAvatar() {
            String nick = accUtils.getNick();
            String initial = nick == null || nick.trim().isEmpty()
                    ? "U"
                    : nick.trim().substring(0, 1).toUpperCase(Locale.getDefault());
            avatarTextView.setText(initial);
            avatarTextView.setVisibility(View.VISIBLE);
            avatarView.setVisibility(View.GONE);
        }

        private void showSuccessToast(String message) {
            android.util.Log.d("AccEdit", "Success: " + message);
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("修改成功")
                    .setMessage(message)
                    .setPositiveButton("确定", null)
                    .show();
        }

        private void showErrorToast(String message) {
            android.util.Log.e("AccEdit", "Error: " + message);
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("修改失败")
                    .setMessage(message)
                    .setPositiveButton("确定", null)
                    .show();
        }
    }
}
