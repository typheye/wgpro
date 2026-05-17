package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c17 extends iActivity {

private void $_onClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

open.cn.awg.pro.e12  e12=new e12(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object app=lb.lbcfsj(-1);

if(zf.dy(app,"open.cn.awg.pro")){

e1.tsk("提示","您已经在该应用内了");


}
else{


try{

xt.dkyy(app);


}catch(java.lang.Throwable e){

e1.upload_error(e,"c17.*");


}


}

}

private boolean $_onLongClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object app=lb.lbcfsj(-1);

android.content.Context ct;
ct=e6.getContext();

java.lang.String a2=com.kingqi.zwcj.应用工具.获取应用签名(ct,String.valueOf(app));

java.lang.String a3=com.kingqi.zwcj.日期工具.格式化日期时间(com.kingqi.zwcj.应用工具.获取应用第一次安装日期(ct,String.valueOf(app)));

java.lang.String a4=com.kingqi.zwcj.日期工具.格式化日期时间(com.kingqi.zwcj.应用工具.获取应用更新日期(ct,String.valueOf(app)));

java.lang.String a5=com.kingqi.zwcj.应用工具.获取应用的安装市场(ct,String.valueOf(app));

java.lang.String a6=com.kingqi.zwcj.应用工具.获取应用版本名称(ct,String.valueOf(app));

int a7=com.kingqi.zwcj.应用工具.获取应用版本号(ct,String.valueOf(app));

java.lang.String a8=com.kingqi.zwcj.应用工具.获取应用名称(ct,String.valueOf(app));

java.lang.String sho="[应用名称]\n"+a8+"\n\n[应用版本]\n"+a6+"\n\n[应用版本号]\n"+a7+"\n\n[应用安装日期]\n"+a3+"\n\n[应用更新日期]\n"+a4;
e1.tsk("详细信息",sho);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_aaeebbd136(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_aaeebbd136(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

android.widget.RelativeLayout i6b77a3b312 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c17_v);

__layoutIsLoaded(ay, vw);
}

    public final c17 lei = this, 类 = this;
}
