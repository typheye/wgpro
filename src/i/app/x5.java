package i.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebStorage;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import open.cn.awg.pro.e6;
import java.lang.*;
import java.util.HashMap;
import gl.xz.*;
import android.os.Bundle;


public class x5 {
  /**
  * 加载状态 0 等待加载，1 加载成功，-1 加载失败
  * */
  public static int zt = 0;
  
  private Context c;
  private Activity a = null;
  /**
  * 静态构造
  * */
  public static x5 x5(Context c)
  {
    return new x5(c);
  }
  /**
  * 静态构造
  * */
  public static x5 x5(Activity c)
  {
    return new x5(c);
  }
  /**
  * 静态初始化
  * */
  public static void x5csh(Context c)
  {
    e6.x5(c.getApplicationContext());
  }
  /**
  * 静态初始化
  * */
  public static void x5csh(Activity c)
  {
    e6.x5(c.getApplicationContext());
    
  }
  
  /**
  * 构造
  * */
  public x5(Context c)
  {
    this.c = c;
  }
  public x5(Activity c)
  {
    this.c = c;
    this.a = c;
  }
  
  private ValueCallback<Uri> mUploadMessage = null;
  private ValueCallback<Uri[]> mUploadMessage2 = null;
  public WebView st = null;
  /**
  * 配置浏览器
  * 输入lei, 输入宽度，输入高度，输入网址
  * 对象.llq(lei, -1, -1, "https://m.baidu.com")
  * */
  @android.annotation.SuppressLint("SetJavaScriptEnabled")
  public WebView llq(Object w, Object h, Object url)
  {
    st = new com.tencent.smtt.sdk.WebView(c);
    st.setLayoutParams(new android.view.ViewGroup.LayoutParams(Integer.parseInt(w.toString()), Integer.parseInt(h.toString())));
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    st.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//设置https
    st.getSettings().setAllowFileAccess(true);
    st.getSettings().setJavaScriptEnabled(true);
    st.getSettings().setAppCacheEnabled(true);
    st.getSettings().setAppCachePath(c.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath());
    st.getSettings().setAppCacheMaxSize(1024*1024*8);
    st.getSettings().setDatabaseEnabled(true);
    st.getSettings().setDatabasePath(c.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath());
    st.getSettings().setDomStorageEnabled(true);
    st.getSettings().setGeolocationEnabled(true);
    st.getSettings().setLightTouchEnabled(true);
    st.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);// 默认使用缓存
    //st.getSettings().setPluginsEnabled(true);
    st.getSettings().setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState.ON);
    // 设置可以支持缩放
    st.getSettings().setSupportZoom(true);
    // 设置出现缩放工具
    st.getSettings().setBuiltInZoomControls(true);
    // 扩大比例的缩放
    st.getSettings().setUseWideViewPort(true);
    // 自适应屏幕
    // wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
    st.getSettings().setLoadWithOverviewMode(true);
    if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
    {
      st.getSettings().setAllowUniversalAccessFromFileURLs(true);
      st.getSettings().setAllowFileAccessFromFileURLs(true);
    }
    
    st.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    st.setDownloadListener(new downloadListener());
    
    st.setWebChromeClient(new WebChromeClient() {
      
    });
    
    st.setWebViewClient(new WebViewClient(){
      
    });
    
    st.loadUrl(url.toString());
    return st;
  }
  
  // 下载接口
  public class downloadListener implements DownloadListener {
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
      // 用默认浏览器下载文件
      try {
        Intent intent = new Intent(a, open.cn.awg.pro.l2.class);
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        intent.putExtras(bundle);
        a.startActivity(intent);
        // Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        // a.startActivity(intent);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  /**
  * 浏览器网页前进后退控制
  * */
  public void kzqjht(Object value)
  {
    if(st == null) return;
    st.goBackOrForward(Integer.parseInt(value.toString()));
  }
  
  /**
  * 是否存在可后退的网页
  * */
  public boolean kht()
  {
    if(st == null) return false;
    return st.canGoBack();
  }
  /**
  * 是否存在可前进的网页
  * */
  public boolean kqj()
  {
    if(st == null) return false;
    return st.canGoForward();
  }
  
  /**
  * 设置载入的网址
  * */
  public void zrwz(Object value2)
  {
    if(st == null) return;
    st.loadUrl(value2.toString());
  }
  /**
  * 获取当前载入的网址
  * */
  public String wz()
  {
    if(st == null) return null;
    return st.getUrl();
  }
  /**
  * 设置载入的网址，可载入一个网页文件
  * */
  public void zrwz(Object value0, Object value, Object value2, Object value3, Object value5)
  {
    if(st == null) return;
    st.getSettings().setDefaultTextEncodingName(value2.toString());
    st.loadDataWithBaseURL(value0.toString(), value.toString(), value3.toString(),  value2.toString(), value5.toString());
  }
  
  /**
  * 获取当前网页标题
  * */
  public String bt()
  {
    if(st == null) return null;
    return st.getTitle();
  }
  
}