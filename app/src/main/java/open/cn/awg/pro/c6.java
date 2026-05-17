package open.cn.awg.pro;

import i.app.iActivity;
 import java.io.File; import android.net.Uri; import java.nio.file.Files; import android.content.Intent; import android.content.ComponentName; import android.os.Bundle; import android.os.Build; import androidx.core.content.FileProvider; import android.provider.Settings; import net.lingala.zip4j.core.ZipFile;import java.lang.*;

public class c6 extends iActivity {

private void $_onClick_e1433e26d6(android.view.View vw){

final open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{


final i.runlibrary.app.sj$lb lb=sj.lb(vw);

final java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

final java.lang.Object n=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

final java.lang.String es=st.wb(open.cn.awg.pro.R.id.zywblj).zf();

final java.lang.String c=String.valueOf(lb.lbcfsj(open.cn.awg.pro.R.id.wb3));
gj.xc(new java.lang.Thread(){

public void run(){


java.lang.String d1_rootpaths;
d1_rootpaths=open.cn.awg.pro.abc.d1_rootpath;

if(zf.dy(n,"-1")){

e1.cdx(0,null,null,null);


}

else if(zf.dy(n,"0")){

e1.cdx(6,null,c,null);


}

else if(zf.dy(n,"a")||zf.dy(n,"b")||zf.dy(n,"c")||zf.dy(n,"d")||zf.dy(n,"e")||zf.dy(n,"f")||zf.dy(n,"g")){

e1.dkfs(n,c,0);


}
else{


if(zf.dy(n,"a1")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(d2.class,name,value);


}

});


}

else if(zf.dy(n,"a2")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String[] name=new java.lang.String[]{"url"};

java.lang.String[] value=new java.lang.String[]{c};
gj.tz(d5.class,name,value);


}

});


}

else if(zf.dy(n,"b1")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(d3.class,name,value);


}

});


}

else if(zf.dy(n,"c1")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g3.class,name,value);


}

});


}

else if(zf.dy(n,"c2")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g2.class,name,value);


}

});


}

else if(zf.dy(n,"c3")){


java.lang.String pa1="/HankMi/cache/music/keydata.hmd";
pa1=wj.hqml("%")+pa1;

java.lang.String nr="hmmedia="+c;
wj.xrwb(pa1,nr);
gj.jmxc(new java.lang.Runnable(){

public void run(){


try{

xt.dkyy("com.hankmi.wearmusic");


}catch(java.lang.Throwable e){

e1.tsk("提示","打开异常\n"+e);


}


}

});


}

else if(zf.dy(n,"d2")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g2.class,name,value);


}

});


}

else if(zf.dy(n,"d3")){


final java.lang.String url=c;

java.lang.String[] fhValue=e1.fileinfo(d1_rootpaths,c);

java.lang.String bts=fhValue[3];

final java.lang.String title=bts;

try{

gj.jmxc(new java.lang.Runnable(){

public void run(){


boolean fhs=true;

              android.content.Context context=open.cn.awg.pro.e6.getContext();
              fhs=com.demo.e4.wearvideo(context,url,title);
            

if(fhs==false){

e1.tsk("提示","打开异常");


}


}

});


}catch(java.lang.Throwable e){

e1.tsk("提示","打开异常\n"+e);


}


}

else if(zf.dy(n,"d4")){


java.lang.String pa1="/HankMi/cache/media/keydata.hmd";
pa1=wj.hqml("%")+pa1;

java.lang.String nr="hmmedia="+c;
wj.xrwb(pa1,nr);
gj.jmxc(new java.lang.Runnable(){

public void run(){


try{

xt.dkyy("com.hankmi.media");


}catch(java.lang.Throwable e){

e1.tsk("提示","打开异常\n"+e);


}


}

});


}

else if(zf.dy(n,"e1")){


java.lang.String[] fhValue=e1.fileinfo(d1_rootpaths,c);

final java.lang.String lj=fhValue[2];

final java.lang.String c1=c;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);


}

});

if(wj.jy(c1,lj,true)>0){

e1.tsk("提示","解压成功");


}
else{

e1.tsk("提示","解压失败");


}
e1.sx(c,es);


}

else if(zf.dy(n,"f1")){


final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(d4.class,name,value);


}

});


}

else if(zf.dy(n,"f2")){


java.lang.String b=wj.hqml("%");

java.lang.String kl=zf.th(c,b,"%");

final java.lang.String[] name=new java.lang.String[]{"url","mode"};

final java.lang.String[] value=new java.lang.String[]{c,"0"};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g5.class,name,value);


}

});


}

else if(zf.dy(n,"f3")){


java.lang.String b=wj.hqml("%");

java.lang.String kl=zf.th(c,b,"%");

final java.lang.String[] name=new java.lang.String[]{"url","mode"};

final java.lang.String[] value=new java.lang.String[]{c,"1"};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g5.class,name,value);


}

});


}

else if(zf.dy(n,"g1")){


java.lang.String b=wj.hqml("%");

java.lang.String kl=zf.th(c,b,"%");

final java.lang.String[] name=new java.lang.String[]{"url"};

final java.lang.String[] value=new java.lang.String[]{c};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g4.class,name,value);


}

});


}

else if(zf.dy(n,"g2")){

e1.tsk("提示","腕管Pro已不再支持应用主题文件");


}

else if(zf.dy(n,"g3")){


if(wj.dx(c)>102400){

e1.tsk("提示","文件过大,无法打开");


}
else{


java.lang.String nrs=wj.dqwb(c);

final java.lang.String[] name=new java.lang.String[]{"cmd"};

final java.lang.String[] value=new java.lang.String[]{nrs};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(j3.class,name,value);


}

});


}


}

else if(zf.dy(n,"g4")){


if(wj.dx(c)>102400){

e1.tsk("提示","文件过大,无法打开");


}
else{


java.lang.String nrs=wj.dqwb(c);

final java.lang.String[] name=new java.lang.String[]{"sh"};

final java.lang.String[] value=new java.lang.String[]{nrs};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(j4.class,name,value);


}

});


}


}

else if(zf.dy(n,"g5")){

e1.tsk("提示","请前往 应用设置>关于应用>应用备份 页面恢复应用备份");


}

else if(zf.dy(n,"h1")||zf.dy(n,"h2")||zf.dy(n,"h3")||zf.dy(n,"h4")){


java.lang.String choose="/data/user/0/open.cn.awg.pro/cache/file/choose";
wj.sc(choose);
wj.xrwb("/data/user/0/open.cn.awg.pro/cache/chat/upload/path",c);
gj.gb();


}

else if(zf.dy(n,"i1")){


java.lang.String choose2="/data/user/0/open.cn.awg.pro/cache/file/choose2";
wj.sc(choose2);
wj.xrwb("/data/user/0/open.cn.awg.pro/cache/user/upload/path",c);
gj.gb();


}
else{

e1.tsk("提示","未找到该打开方式");


}
e1.cdx(0,null,null,null);


}


}

});


}catch(java.lang.Throwable e){

e1.upload_error(e,"c6.*");


}

}

private boolean $_onLongClick_e1433e26d6(android.view.View vw){

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

open.cn.awg.pro.e1  e1=new e1(_APPINFO);
e1.tsk("详细信息",a);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_e1433e26d6=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_e1433e26d6(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e1433e26d6(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);
e1433e26d6.setOnLongClickListener($_on_setOnLongClickListener_e1433e26d6);

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);

android.widget.RelativeLayout i9f15e08dfa = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c6_v);

__layoutIsLoaded(ay, vw);
}

    public final c6 lei = this, 类 = this;
}
