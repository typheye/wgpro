package android.support.v7.app;

public class AlertDialog extends androidx.appcompat.app.AlertDialog {
    protected AlertDialog(android.content.Context c) { super(c); }
    protected AlertDialog(android.content.Context c, int themeResId) { super(c, themeResId); }
    protected AlertDialog(android.content.Context c, boolean cancelable, android.content.DialogInterface.OnCancelListener cancelListener) { super(c, cancelable, cancelListener); }

    public static class Builder extends androidx.appcompat.app.AlertDialog.Builder {
        public Builder(android.content.Context c) { super(c); }
        public Builder(android.content.Context c, int themeResId) { super(c, themeResId); }
    }
}
