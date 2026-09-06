package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.util.SparseArray;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;

public final class WGProBottomSheetDialog extends BottomSheetDialog {
    private final SparseArray<MaterialButton> buttons = new SparseArray<>();
    WGProBottomSheetDialog(Context context) { super(context, R.style.ThemeOverlay_WGPro_BottomSheetDialog); }
    void registerButton(int which, MaterialButton button) { buttons.put(which, button); }
    public MaterialButton getButton(int which) { return buttons.get(which); }
}
