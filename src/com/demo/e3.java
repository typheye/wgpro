package com.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;

/**
* 执行shell脚本工具类
* @author Mountain
*
*/
public class e3 {
  
  public static final String TAG = "CommandExecution";
  
  public final static String COMMAND_SU       = "su";
  public final static String COMMAND_SH       = "sh";
  public final static String COMMAND_EXIT     = "exit\n";
  public final static String COMMAND_LINE_END = "\n";
  
  /**
  * Command执行结果
  * @author Mountain
  *
  */
  public static class CommandResult {
    public int result = -1;
    public String errorMsg;
    public String successMsg;
  }
  
  /**
  * 执行命令—单条
  * @param command
  * @param isRoot
  * @return
  */
  public static String[] cmd(Context context,String command, boolean isRoot) {
    String[] commands = {command};
    return execCommand(context, commands, isRoot);
  }
  
  /**
  * 执行命令-多条
  * @param commands
  * @param isRoot
  * @return
  */
  public static String[] execCommand(Context context,String[] commands, boolean isRoot) {
    //CommandResult commandResult = new CommandResult();
    String[] back={"",""};
    if (commands == null || commands.length == 0) return back;
    Process process = null;
    DataOutputStream os = null;
    BufferedReader successResult = null;
    BufferedReader errorResult = null;
    StringBuilder successMsg = null;
    StringBuilder errorMsg = null;
    try {
      process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
      os = new DataOutputStream(process.getOutputStream());
      for (String command : commands) {
        if (command != null) {
          os.write(command.getBytes());
          os.writeBytes(COMMAND_LINE_END);
          os.flush();
        }
      }
      os.writeBytes(COMMAND_EXIT);
      os.flush();
      //commandResult.result = process.waitFor();
      //获取错误信息
      successMsg = new StringBuilder();
      errorMsg = new StringBuilder();
      successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
      errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String s;
      while ((s = successResult.readLine()) != null) successMsg.append(s);
      while ((s = errorResult.readLine()) != null) errorMsg.append(s);
      //commandResult.successMsg = successMsg.toString();
      //commandResult.errorMsg = errorMsg.toString();
      back[0] = successMsg.toString();
      back[1] = errorMsg.toString();
    } catch (IOException e) {
      Intent intent = new Intent(context, open.cn.awg.pro.b1.class);
      Bundle bundle=new Bundle();
      bundle.putString("log",e.toString());
      bundle.putString("location","e3.execCommand(context,s[],boolean)");
      intent.putExtras(bundle);
      context.startActivity(intent);
    } catch (Exception e) {
      Intent intent = new Intent(context, open.cn.awg.pro.b1.class);
      Bundle bundle=new Bundle();
      bundle.putString("log",e.toString());
      bundle.putString("location","e3.execCommand(context,s[],boolean)");
      intent.putExtras(bundle);
      context.startActivity(intent);
    } finally {
      try{
        if (os != null) os.close();
        if (successResult != null) successResult.close();
        if (errorResult != null) errorResult.close();
      } catch (IOException e) {
      }
      if (process != null) process.destroy();
    }
    return back;
  }
  
}