package android.support.design.widget;

public class AppBarLayout extends com.google.android.material.appbar.AppBarLayout {
    public AppBarLayout(android.content.Context c) { super(c); }
    public AppBarLayout(android.content.Context c, android.util.AttributeSet a) { super(c, a); }
    public AppBarLayout(android.content.Context c, android.util.AttributeSet a, int d) { super(c, a, d); }

    public static class ScrollingViewBehavior extends com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior {
        public ScrollingViewBehavior() { super(); }
        public ScrollingViewBehavior(android.content.Context c, android.util.AttributeSet a) { super(c, a); }
    }
}
