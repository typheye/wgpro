package open.cn.awg.pro;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.GestureDescription;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class SystemAccessibilityService extends AccessibilityService {
  
  public static boolean isAccessibilitySettingsOn(Context context,String className){
    if (context == null){
      return false;
    }
    ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningServiceInfo> runningServices =
    activityManager.getRunningServices(100);// 获取正在运行的服务列表
    if (runningServices.size()<0){
      return false;
    }
    for (int i=0;i<runningServices.size();i++){
      ComponentName service = runningServices.get(i).service;
      if (service.getClassName().equals(className)){
        return true;
      }
    }
    return false;
  }
  
  public void onAccessibilityEvent(AccessibilityEvent event) {
    if(event == null) return;
    
    if(event != null)
    Event(event);
  }
  
  public static AccessibilityNodeInfo node = null;
  public static String aeText = null;
  private void Event(AccessibilityEvent event)
  {
    try {
      AccessibilityNodeInfo node = event.getSource();
      if(event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED)
      SystemAccessibilityService.node = node;
      sb1.setLength(0);
      getAllText_api25(node, null);
      String ae = sb1.toString();
      if(!ae.equals(aeText))
      aeText = ae;
      //getAllText(node, new StringBuilder());
    }catch (Throwable e){ }
  }
  
  private ArrayList<AccessibilityNodeInfo> list1 = new ArrayList<AccessibilityNodeInfo>();
  private ArrayList<String> list2 = new ArrayList<String>();
  private StringBuilder sb1 = new StringBuilder();
  private void getAllText_api25(AccessibilityNodeInfo node, StringBuilder sb)
  {
    int j = node.getChildCount();
    for(int i=0; i<j; i++)
    {
      StringBuilder sb2 = new StringBuilder();
      if(sb != null)
      sb2.append(sb).append(",");
      sb2.append(i);
      AccessibilityNodeInfo all = node.getChild(i);
      sb1.append("【").append(sb2).append("】").append(all).append("\n\n");
      getAllText_api25(all, sb2);
    }
  }
  
  private AccessibilityNodeInfo get_api25(AccessibilityNodeInfo node, StringBuilder sb, String id)
  {
    AccessibilityNodeInfo anf = null;
    int j = node.getChildCount();
    for(int i=0; i<j; i++)
    {
      StringBuilder sb2 = new StringBuilder();
      if(sb != null)
      sb2.append(sb).append(",");
      sb2.append(i);
      AccessibilityNodeInfo all = node.getChild(i);
      log(id.equals(sb2.toString()) + ":" + id + ":" + sb2.toString());
      if(id.equals(sb2.toString()))
      return all;
      anf = get_api25(all, sb2, id);
      if(anf != null) return anf;
    }
    return null;
  }
  
  private void Sleep(int s)
  {
    try {
      Thread.sleep(s);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  // 可自行修改无障碍目标应用包名，可输入多个包名以逗号隔开
  public static String pns = "com.iapp.app";
  
  //public static String cmd = null;
  protected void onServiceConnected() {
    handler = new Handler(this.getMainLooper())
    {
      public void handleMessage(Message msg) {
        switch (msg.what)
        {
          case 1:
          Toast.makeText(SystemAccessibilityService.this, String.valueOf(msg.obj), Toast.LENGTH_LONG).show();
          break;
        }
      }
    };
    
    new Thread(){
      public void run() {
        
        while (true) {
          /*
          if(cmd != null)
          {
          try {
          cmd(cmd);
          }catch (Throwable e){
          aeText = e.toString();
          }
          cmd = null;
          }
          */
          if(list.size() > 0){
            try {
              cmd(list.get(0));
            }catch (Throwable e){
              aeText = e.toString();
            }
            list.remove(0);
          }
          Sleep(300);
        }
      }
    }.start();
    
    AccessibilityServiceInfo accessibilityServiceInfo = this.getServiceInfo();
    accessibilityServiceInfo.packageNames = pns.split(",", -1);
    setServiceInfo(accessibilityServiceInfo);
  }
  
  private static ArrayList<String> list = new ArrayList<String>();
  // 执行命令
  public static void addCmd(String cmd)
  {
    list.add(cmd);
  }
  
  private void cmd(String cmd)
  {
    cmd = cmd.replace("\r", "");
    AccessibilityNodeInfo ani;
    String[] sz = cmd.split("\n"), sz2, sz3;
    String all;
    int i2;
    for (String all2 : sz)
    {
      all = all2.trim();
      if(all.startsWith("暂停 "))
      {//暂停 1000
        Sleep(Integer.parseInt(all.substring(3)));
      }
      else if(all.startsWith("//"))
      {//注释
      }
      else if(all.startsWith("按键 "))
      {//按键 1
        key(Integer.parseInt(all.substring(3)));
      }
      else if(all.startsWith("提示 "))
      {//提示 halo
        Message msg = new Message();
        msg.what = 1;
        msg.obj = all.substring(3);
        handler.sendMessage(msg);
      }
      else if(all.startsWith("点击 "))
      {//点击 100,100
        sz2 = all.substring(3).trim().split(",");
        //log(sz2.length + "点击 " + all.substring(3));
        Click(Integer.parseInt(sz2[0]), Integer.parseInt(sz2[1]));
      }
      else if(all.startsWith("滑动 "))
      {//滑动 600,600,100,100
        sz2 = all.substring(3).trim().split(",");
        //log(sz2.length + "滑动 " + all.substring(3));
        Touch(Integer.parseInt(sz2[0]), Integer.parseInt(sz2[1]), Integer.parseInt(sz2[2]), Integer.parseInt(sz2[3]));
      }
      else if(all.startsWith("输入目标 "))
      {//输入目标 1,0,1 halo
        all = all.substring(5).trim();
        i2 = all.indexOf(' ');
        if(i2 != -1) {
          ani = get_api25(node, null, all.substring(0, i2).trim());
          if (ani != null)
          inputText(ani, all.substring(i2).trim().replace("\\n", "\n").replace("\\r", "\r"));
          
          //log("输入目标 " + sz2[0] + ":" + sz2[1] + ":" + ani);
        }
      }
      else if(all.startsWith("点击目标 "))
      {//点击目标 1,0,1
        sz2 = all.substring(5).trim().split(" ");
        ani = get_api25(node, null, sz2[0].trim());
        if(ani != null) {
          if(sz2.length == 1)
          ck(ani);
          else if(sz2.length > 1)
          ck(ani, Integer.parseInt(sz2[1].trim()));
        }
        //log(sz2.length + "点击目标 " + sz2[0].trim() + ":" + ani);
      }
      else if(all.startsWith("长按 "))
      {//长按 100,100
        sz2 = all.substring(3).trim().split(",");
        //log(sz2.length + "点击 " + all.substring(3));
        LongClick(Integer.parseInt(sz2[0]), Integer.parseInt(sz2[1]));
      }
      else if(all.startsWith("复制 "))
      {//复制 halo
        copy(all.substring(3));
      }
      else if(all.startsWith("启动 "))
      {//启动 com.abc
        openPackageName(all.substring(3));
      }
      else if(all.startsWith("结束 "))
      {//结束 com.abc
        closePackageName(all.substring(3));
      }
      else if(all.startsWith("正在运行应用"))
      {//正在运行应用
        runapplist();
      }
      else if(all.startsWith("应用列表"))
      {//应用列表
        applist();
      }
      
    }
  }
  
  /**
  * 获取手机已经安装的应用
  * */
  private void applist() {
    StringBuilder sb = new StringBuilder();
    try {
      List<PackageInfo> packageInfoList = getPackageManager()
      .getInstalledPackages(0);
      for (PackageInfo packageInfo : packageInfoList) {
        sb.append(packageInfo.packageName).append("\n").append(packageInfo.applicationInfo.loadLabel(
        getPackageManager()).toString()).append("\n").append(packageInfo.versionName).append("\n\n");
      }
    }catch (Exception e){}
    aeText = sb.toString();
  }
  
  /**
  * 获取正在运行的应用
  * */
  private void runapplist() {
    StringBuilder sb = new StringBuilder();
    try {
      ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
      List<ActivityManager.RunningAppProcessInfo> run = am.getRunningAppProcesses();
      for (ActivityManager.RunningAppProcessInfo ra : run) {
        sb.append(ra.processName).append("\n").append(ra.pid).append("\n").append(ra.uid).append("\n\n");
      }
    }catch (Exception e){}
    aeText = sb.toString();
  }
  
  /**
  * 结束应用
  * */
  public boolean closePackageName(String packageName) {
    ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    if(manager != null) {
      manager.killBackgroundProcesses(packageName);
      return true;
    }
    return false;
  }
  
  /**
  * 启动游戏应用
  * */
  public boolean openPackageName(String packageName) {
    PackageManager pm = getPackageManager();
    Intent i = pm.getLaunchIntentForPackage(packageName);
    if (i != null){
      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(i);
      return true;
    }
    return false;
  }
  
  public void copy(String content) {
    android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    if (clipboard != null)
    clipboard.setPrimaryClip(ClipData.newPlainText(null, content));
  }
  
  private void log(Object o)
  {
    
  }
  
  private Handler handler = null;
  
  /**
  * 全局事件
  * 值说明 1=返回键 2=HOME键 3=最近打开应用列表 4=打开通知栏 5=设置 6=长按电源 7=切换窗口 8=锁屏 9=截屏 10=接/挂电话
  * */
  public boolean key(int action)
  {
    return this.performGlobalAction(action);
  }
  
  public boolean ck(AccessibilityNodeInfo node, int action)
  {
    if (node.isEnabled())
    {
      return node.performAction(action);
    }
    return false;
  }
  public boolean ck(AccessibilityNodeInfo node)
  {
    return ck(node, AccessibilityNodeInfo.ACTION_CLICK);
  }
  
  public boolean inputText(AccessibilityNodeInfo node, String st)
  {
    boolean ok = false;
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
    {
      //Android 5.0 版本及以上
      Bundle arguments = new Bundle();
      arguments.putCharSequence(
      AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, st);
      ok = node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
    }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
      //Android 4.3 版本及以上
      ClipboardManager cm = (ClipboardManager) this.getBaseContext().getSystemService(Context.CLIPBOARD_SERVICE);
      ClipData mClipData = ClipData.newPlainText("iapp label", st);
      cm.setPrimaryClip(mClipData);
      node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
      ok = node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
    }
    return ok;
  }
  
  public void onInterrupt() {
    
  }
  
  /**
  * 全局事件
  * */
  @TargetApi(Build.VERSION_CODES.N)
  private void Click(float x, float y)
  {
    android.graphics.Path path = new android.graphics.Path();
    path.moveTo(x, y);
    android.accessibilityservice.GestureDescription.Builder builder = new android.accessibilityservice.GestureDescription.Builder();
    //100L 第一个是开始的时间，第二个是持续时间
    android.accessibilityservice.GestureDescription description = builder.addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 100L, 100L)).build();
    dispatchGesture(description, new GestureResultCallback() {
      public void onCompleted(GestureDescription gestureDescription) {
        super.onCompleted(gestureDescription);
        // 完成操作
      }
      public void onCancelled(GestureDescription gestureDescription) {
        super.onCancelled(gestureDescription);
        // 取消
      }
    }, null);
  }
  
  @TargetApi(Build.VERSION_CODES.N)
  private void LongClick(float x, float y)
  {
    android.graphics.Path path = new android.graphics.Path();
    path.moveTo(x, y);
    android.accessibilityservice.GestureDescription.Builder builder = new android.accessibilityservice.GestureDescription.Builder();
    //100L 第一个是开始的时间，第二个是持续时间
    android.accessibilityservice.GestureDescription description = builder.addStroke(new android.accessibilityservice.GestureDescription.StrokeDescription(path, 100L, 1000L)).build();
    dispatchGesture(description, new GestureResultCallback() {
      public void onCompleted(GestureDescription gestureDescription) {
        super.onCompleted(gestureDescription);
        // 完成操作
      }
      public void onCancelled(GestureDescription gestureDescription) {
        super.onCancelled(gestureDescription);
        // 取消
      }
    }, null);
  }
  
  /**
  * 全局事件
  * */
  @TargetApi(Build.VERSION_CODES.N)
  private void Touch(float x, float y, float x2, float y2)
  {
    Path path = new Path();
    path.moveTo(x, y);//滑动起点
    path.lineTo(x2, y2);//滑动终点
    GestureDescription.Builder builder = new GestureDescription.Builder();
    //100L 第一个是开始的时间，第二个是持续时间
    GestureDescription description = builder.addStroke(new GestureDescription.StrokeDescription(path, 100L, 500L)).build();
    dispatchGesture(description, new GestureResultCallback() {
      public void onCompleted(GestureDescription gestureDescription) {
        super.onCompleted(gestureDescription);
        // 完成操作
      }
      public void onCancelled(GestureDescription gestureDescription) {
        super.onCancelled(gestureDescription);
        // 取消
      }
    }, null);
  }
  
}