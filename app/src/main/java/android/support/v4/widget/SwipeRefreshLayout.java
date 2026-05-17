package android.support.v4.widget;

public class SwipeRefreshLayout extends android.view.ViewGroup {
    public interface OnRefreshListener { void onRefresh(); }
    private OnRefreshListener listener;
    private boolean refreshing;

    public SwipeRefreshLayout(android.content.Context c) { super(c); }
    public SwipeRefreshLayout(android.content.Context c, android.util.AttributeSet a) { super(c, a); }

    public void setOnRefreshListener(OnRefreshListener l) { this.listener = l; }
    public void setRefreshing(boolean b) { this.refreshing = b; }
    public boolean isRefreshing() { return refreshing; }
    public void setColorSchemeResources(int... colors) {}
    public void setColorSchemeColors(int... colors) {}
    public void setEnabled(boolean enabled) { super.setEnabled(enabled); }

    @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            android.view.View child = getChildAt(i);
            child.layout(0, 0, r - l, b - t);
        }
    }
}
