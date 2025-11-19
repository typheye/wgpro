package com.typheye.wgpro.core.xms;

import android.webkit.JavascriptInterface;
import com.google.gson.Gson;
import com.typheye.wgpro.ui.main.MainActivity;

public class JSKit {
    public Gson gson = new Gson();

    @JavascriptInterface
    public String GetUIParams(){
        //MainActivity.logs.add("[JavaScriptInterface] GetUIParams Return: " + result);
        return gson.toJson(MainActivity.current_params);
    }

    @JavascriptInterface
    public String GetLogs(){
        //MainActivity.logs.add("[JavaScriptInterface] GetLogs Return: " + result);
        return gson.toJson(MainActivity.logs);
    }

    @JavascriptInterface
    public void ClearQRKey(){
        MainActivity.current_params.qrcode_key = "";
    }
}
