package com.typheye.wgpro.ui.function.debug;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.typheye.wgpro.R;
import com.typheye.wgpro.debug.TestHandler;

/**
 * 调试页·模拟操作：把原主页的「模拟」分组单独成页。
 *
 * <p>由 {@link TestActivity} 通过 {@code showList(...)} 以 Fragment 形式展示，
 * 切换动画与毛玻璃反馈沿用调试页的统一逻辑（{@code AppUtils.applyFragmentTransition}）。
 */
public final class DebugSimulationFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_debug_simulation, container, false);

        MaterialSwitch offline = root.findViewById(R.id.test_offline_switch);
        MaterialSwitch loggedOut = root.findViewById(R.id.test_logged_out_switch);
        MaterialSwitch simulatedUpdate = root.findViewById(R.id.test_update_switch);
        // 行级点击也要能切换（原来只有开关本身可点，整行既无水波纹也无响应）
        root.findViewById(R.id.row_test_offline).setOnClickListener(v -> offline.toggle());
        root.findViewById(R.id.row_test_logged_out).setOnClickListener(v -> loggedOut.toggle());
        root.findViewById(R.id.row_test_update).setOnClickListener(v -> simulatedUpdate.toggle());

        offline.setChecked(TestHandler.isOfflineSimulationEnabled(requireContext()));
        loggedOut.setChecked(TestHandler.isLoggedOutSimulationEnabled(requireContext()));
        simulatedUpdate.setChecked(TestHandler.isUpdateSimulationEnabled(requireContext()));

        offline.setOnCheckedChangeListener((button, checked) ->
                TestHandler.setOfflineSimulation(requireContext(), checked));
        loggedOut.setOnCheckedChangeListener((button, checked) ->
                TestHandler.setLoggedOutSimulation(requireContext(), checked));
        simulatedUpdate.setOnCheckedChangeListener((button, checked) ->
                TestHandler.setUpdateSimulation(requireContext(), checked));

        // 触发一次真实未捕获异常：走 CrashHandler → 本地日志 + 上报 + 崩溃页
        root.findViewById(R.id.row_test_crash).setOnClickListener(v -> {
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
                throw new IllegalStateException("测试崩溃：来自调试页的模拟异常 "
                        + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        java.util.Locale.CHINA).format(new java.util.Date()));
            }, 300L);
        });
        root.findViewById(R.id.row_test_oobe).setOnClickListener(v ->
                startActivity(new android.content.Intent(requireContext(),
                        com.typheye.wgpro.ui.oobe.OobeActivity.class)));

        DebugListFragment.applyBarInsets(this, root);
        return root;
    }
}
