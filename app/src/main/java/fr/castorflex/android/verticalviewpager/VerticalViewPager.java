package fr.castorflex.android.verticalviewpager;

public class VerticalViewPager extends android.widget.FrameLayout {
    public VerticalViewPager(android.content.Context context) { super(context); }
    public VerticalViewPager(android.content.Context context, android.util.AttributeSet attrs) { super(context, attrs); }
    public VerticalViewPager(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr) { super(context, attrs, defStyleAttr); }

    public void setAdapter(Object adapter) {}
    public Object getAdapter() { return null; }
    public void setCurrentItem(int item) {}
    public void setCurrentItem(int item, boolean smoothScroll) {}
    public int getCurrentItem() { return 0; }
}
