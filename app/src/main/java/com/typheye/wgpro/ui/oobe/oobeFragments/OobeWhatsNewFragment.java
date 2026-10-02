package com.typheye.wgpro.ui.oobe.oobeFragments;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.oobe.OobeActivity;

/**
 * 新手引导的「新版变化」页。
 *
 * <p>因为 {@code AppUtils.CURRENT_OOBE_VERSION} 会随每次改版提升，
 * 从旧版本升级上来的用户也会重新走一遍引导，正好用来介绍本版新增能力。
 */
public class OobeWhatsNewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oobe_whatsnew, container, false);

        TextView version = view.findViewById(R.id.oobe_whatsnew_version);
        version.setText("新版变化 · " + versionName());

        MaterialButton back = view.findViewById(R.id.btn_back);
        MaterialButton forward = view.findViewById(R.id.btn_forward);
        back.setOnClickListener(v -> goForward(false));
        forward.setOnClickListener(v -> goForward(true));
        return view;
    }

    private void goForward(boolean isForward) {
        if (requireActivity() instanceof OobeActivity) {
            ((OobeActivity) requireActivity()).switchFragment(isForward ? "policies" : "welcome");
        }
    }

    private String versionName() {
        try {
            PackageInfo info = requireContext().getPackageManager()
                    .getPackageInfo(requireContext().getPackageName(), 0);
            return info.versionName == null ? "腕管Pro" : info.versionName;
        } catch (PackageManager.NameNotFoundException ignored) {
            return "腕管Pro";
        }
    }
}
