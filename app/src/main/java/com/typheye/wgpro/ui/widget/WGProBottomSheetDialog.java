package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class WGProBottomSheetDialog extends BottomSheetDialog {
    private static final long RAPID_REOPEN_GUARD_MS = 350L;
    private static final WeakHashMap<Context, WeakReference<WGProBottomSheetDialog>> ACTIVE = new WeakHashMap<>();
    private static final WeakHashMap<Context, Long> LAST_DISMISS_AT = new WeakHashMap<>();

    private final SparseArray<MaterialButton> buttons = new SparseArray<>();
    private final Context owner;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private Runnable windowConfigurator;
    private boolean registered;
    private boolean actionHandled;
    private boolean allowImmediateReplacement;
    private boolean backDismissEnabled = true;
    private long minimumShowDurationMs;
    private long shownAtMs;
    private boolean dismissPending;
    private boolean showQueued;
    private android.window.OnBackInvokedCallback backCallback;

    WGProBottomSheetDialog(Context context) {
        super(context, R.style.ThemeOverlay_WGPro_BottomSheetDialog);
        owner = unwrapContext(context);
    }

    @Override
    public void show() {
        synchronized (ACTIVE) {
            WeakReference<WGProBottomSheetDialog> reference = ACTIVE.get(owner);
            WGProBottomSheetDialog active = reference == null ? null : reference.get();
            if (active != null && active != this && active.isShowing()) {
                if (active.dismissPending && !showQueued) {
                    showQueued = true;
                    active.allowImmediateReplacement = true;
                    mainHandler.postDelayed(() -> {
                        showQueued = false;
                        show();
                    }, active.remainingMinimumDuration() + 16L);
                }
                return;
            }

            Long lastDismiss = LAST_DISMISS_AT.get(owner);
            if (active != this && lastDismiss != null
                    && android.os.SystemClock.uptimeMillis() - lastDismiss < RAPID_REOPEN_GUARD_MS) return;
            ACTIVE.put(owner, new WeakReference<>(this));
            registered = true;
        }
        try {
            super.show();
            shownAtMs = android.os.SystemClock.uptimeMillis();
        } catch (RuntimeException exception) {
            releaseRegistration(false);
            throw exception;
        }
    }

    @Override
    public void setCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
        backDismissEnabled = cancelable;
        if (!isShowing()) return;
        unregisterDirectBackCallback();
        registerDirectBackCallback();
        View sheet = findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) {
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(sheet);
            behavior.setDraggable(cancelable);
            behavior.setHideable(cancelable);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (windowConfigurator != null) windowConfigurator.run();
        registerDirectBackCallback();
    }

    @Override
    protected void onStop() {
        unregisterDirectBackCallback();
        super.onStop();
    }

    @Override
    public void dismiss() {
        requestDismiss(false);
    }

    @Override
    public void cancel() {
        requestDismiss(true);
    }

    private void requestDismiss(boolean cancel) {
        long remaining = remainingMinimumDuration();
        if (isShowing() && remaining > 0L) {
            if (!dismissPending) {
                dismissPending = true;
                mainHandler.postDelayed(() -> performDismiss(cancel), remaining);
            }
            return;
        }
        performDismiss(cancel);
    }

    private void performDismiss(boolean cancel) {
        try {
            if (cancel) super.cancel(); else super.dismiss();
        } finally {
            dismissPending = false;
            releaseRegistration(true);
        }
    }

    private long remainingMinimumDuration() {
        if (minimumShowDurationMs <= 0L || shownAtMs <= 0L) return 0L;
        return Math.max(0L, minimumShowDurationMs
                - (android.os.SystemClock.uptimeMillis() - shownAtMs));
    }

    void setWindowConfigurator(Runnable configurator) { windowConfigurator = configurator; }
    void setMinimumShowDuration(long durationMs) {
        minimumShowDurationMs = Math.max(0L, durationMs);
    }
    boolean tryConsumeAction() {
        if (actionHandled) return false;
        actionHandled = true;
        return true;
    }
    public void dismissForReplacement() {
        allowImmediateReplacement = true;
        dismiss();
    }

    static WGProBottomSheetDialog activeFor(Context context) {
        synchronized (ACTIVE) {
            WeakReference<WGProBottomSheetDialog> reference = ACTIVE.get(unwrapContext(context));
            WGProBottomSheetDialog dialog = reference == null ? null : reference.get();
            return dialog != null && dialog.isShowing() ? dialog : null;
        }
    }

    private void releaseRegistration(boolean rememberDismiss) {
        synchronized (ACTIVE) {
            if (!registered) return;
            WeakReference<WGProBottomSheetDialog> reference = ACTIVE.get(owner);
            if (reference != null && reference.get() == this) ACTIVE.remove(owner);
            if (rememberDismiss && !allowImmediateReplacement) {
                LAST_DISMISS_AT.put(owner, android.os.SystemClock.uptimeMillis());
            } else if (allowImmediateReplacement) {
                LAST_DISMISS_AT.remove(owner);
            }
            registered = false;
        }
    }

    private static Context unwrapContext(Context context) {
        Context current = context;
        while (current instanceof ContextWrapper) {
            Context base = ((ContextWrapper) current).getBaseContext();
            if (base == null || base == current) break;
            current = base;
        }
        return current;
    }

    private void registerDirectBackCallback() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || !backDismissEnabled
                || getWindow() == null || backCallback != null) return;
        backCallback = this::handleDirectBack;
        getWindow().getOnBackInvokedDispatcher().registerOnBackInvokedCallback(
                android.window.OnBackInvokedDispatcher.PRIORITY_OVERLAY, backCallback);
    }

    private void unregisterDirectBackCallback() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || backCallback == null
                || getWindow() == null) return;
        getWindow().getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback(backCallback);
        backCallback = null;
    }

    private void handleDirectBack() {
        Window window = getWindow();
        if (window != null) {
            View decor = window.getDecorView();
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(decor);
            if (insets != null && insets.isVisible(WindowInsetsCompat.Type.ime())) {
                WindowCompat.getInsetsController(window, decor)
                        .hide(WindowInsetsCompat.Type.ime());
                return;
            }
        }
        dismiss();
    }

    void registerButton(int which, MaterialButton button) { buttons.put(which, button); }
    public MaterialButton getButton(int which) { return buttons.get(which); }
}
