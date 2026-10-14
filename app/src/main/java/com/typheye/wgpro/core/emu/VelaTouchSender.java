package com.typheye.wgpro.core.emu;

import androidx.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 触摸事件发送器：**单线程 + 队列**按序发送，移动事件做合并。
 *
 * <p>为什么不能用"每个事件一个线程"：DOWN/MOVE/UP 会乱序到达客机，
 * 抬手可能先于按下 → 客机里手指卡住、点击与滑动都不生效。</p>
 *
 * <p>事件语义按官方 proto：{@code pressure>0} 为按下/移动，{@code pressure=0} 为抬手。</p>
 */
public final class VelaTouchSender {

    private static final int CAPACITY = 32;

    /** {x, y, identifier, action} action: 0=按下 1=移动 2=抬手 */
    private final ArrayBlockingQueue<int[]> queue = new ArrayBlockingQueue<>(CAPACITY);
    private final VelaFrames frames;
    private final Thread worker;
    private volatile boolean running = true;
    /** 当前是否按住 + 最后位置（用于长按保活：参考实现按住期间每 100ms 重发 buttons=1）。 */
    private volatile boolean holding;
    private volatile int holdX;
    private volatile int holdY;
    private volatile long lastSentAt;

    public VelaTouchSender(@NonNull VelaFrames frames) {
        this.frames = frames;
        worker = new Thread(this::loop, "vela-touch");
        worker.setDaemon(true);
        worker.start();
    }

    /** 点击（一次按下+抬手，间隔按参考实现的 35ms）。 */
    public void tap(int x, int y) {
        down(x, y, 1);
        try {
            Thread.sleep(35);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        up(x, y, 1);
    }

    /** 按下：不可丢。 */
    public void down(int x, int y, int identifier) {
        offer(new int[]{x, y, identifier, 0}, false);
    }

    /** 移动：队列积压时丢弃旧的移动（只保留最新位置）。 */
    public void move(int x, int y, int identifier) {
        offer(new int[]{x, y, identifier, 1}, true);
    }

    /** 抬手：不可丢，且必须把 pressure 归 0。 */
    public void up(int x, int y, int identifier) {
        offer(new int[]{x, y, identifier, 2}, false);
    }

    private void offer(@NonNull int[] event, boolean coalesce) {
        if (!running) {
            return;
        }
        if (coalesce) {
            // 合并：把队列里积压的移动事件丢掉，只留最新这一个
            for (int[] pending : queue.toArray(new int[0][])) {
                if (pending != null && pending[3] == 1) {
                    queue.remove(pending);
                }
            }
        }
        try {
            if (!queue.offer(event, 200, TimeUnit.MILLISECONDS)) {
                // 极端情况下也不能丢按下/抬手：清空后强塞
                queue.clear();
                queue.offer(event);
            }
        } catch (InterruptedException stop) {
            Thread.currentThread().interrupt();
        }
    }

    private void loop() {
        while (running) {
            int[] event;
            try {
                event = queue.poll(60, TimeUnit.MILLISECONDS);
            } catch (InterruptedException stop) {
                return;
            }
            if (event == null) {
                // 长按保活：手指按住且 120ms 没新事件时补发一次按下
                if (holding && System.currentTimeMillis() - lastSentAt > 120L) {
                    VelaFrames current = frames;
                    if (current != null) {
                        current.mouse(holdX, holdY, 1);
                        lastSentAt = System.currentTimeMillis();
                    }
                }
                continue;
            }
            if (event[3] == 0) {
                holding = true;
                holdX = event[0];
                holdY = event[1];
            } else if (event[3] == 1) {
                holdX = event[0];
                holdY = event[1];
            } else {
                holding = false;
            }
            lastSentAt = System.currentTimeMillis();
            VelaFrames current = frames;
            if (current == null) {
                continue;
            }
            // Vela 客机只认 sendMouse：按下/移动=1，抬手=0
            current.mouse(event[0], event[1], event[3] == 2 ? 0 : 1);
        }
    }

    public void close() {
        running = false;
        worker.interrupt();
        queue.clear();
    }
}
