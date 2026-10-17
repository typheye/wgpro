package open.cn.awg.pro.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.Calendar;
import java.util.Locale;

/**
 * Watch-style time text: always "HH:MM" (24h, zero padded), refreshed once per minute.
 */
public class WatchTimeText extends AppCompatTextView {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            update();
            long delay = 1000L * (60 - Calendar.getInstance().get(Calendar.SECOND));
            handler.postDelayed(this, Math.max(1000L, delay));
        }
    };

    public WatchTimeText(Context context) {
        super(context);
        init();
    }

    public WatchTimeText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WatchTimeText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        update();
    }

    private void update() {
        Calendar c = Calendar.getInstance();
        setText(String.format(Locale.US, "%02d:%02d",
                c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        update();
        handler.removeCallbacks(tick);
        handler.post(tick);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(tick);
    }
}
