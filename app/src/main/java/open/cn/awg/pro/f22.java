package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import android.view.View; import android.graphics.Bitmap;import java.lang.*;

public class f22 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.f22);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
update();

}

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String update_url="";

public void update(){

final java.lang.String auid="/data/user/0/open.cn.awg.pro/settings/account/user";

final java.lang.String sec_updater="/data/user/0/open.cn.awg.pro/data/sec/updater";

java.lang.String set36="/data/user/0/open.cn.awg.pro/settings/fixVersion";

int a11;

java.lang.String vn;
a11=com.demo.e4.getVersionCode(lei);
vn=com.demo.e4.getVersionName(lei);

final int myappversion=a11;

final java.lang.String myappversionname=vn;

final java.lang.String appfixversion=zf.qctwkg(wj.dqwb(set36));

java.lang.String set2e="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";

final java.lang.String set3="/data/user/0/open.cn.awg.pro/cache/update";
set2e=wj.dqwb(set2e);
set2e=e1.urlUnlockString(set2e);

if(zf.cjw(set2e,"/")){



}
else{

set2e=set2e+"/";


}
set2e=set2e+"config_v2.json";

final java.lang.String url=set2e;
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String e="Loading...";
st.wb(open.cn.awg.pro.R.id.wb5).zf(e);
st.wb(open.cn.awg.pro.R.id.wb8).zf(e);
st.wb(open.cn.awg.pro.R.id.wb11).zf(e);


}

});

java.lang.String setback="";

java.lang.String version="";

java.lang.String versionname="";

java.lang.String time="";

java.lang.String text="";

java.lang.String urls="";

java.lang.String allow="";

java.lang.String back=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{

setback=e1.htmlToText(back);

if(zf.dy(setback,"0")){

setback="0";


}
else{

version=e1.readJson(setback,"UpdateVersion");
versionname=e1.readJson(setback,"UpdateVersionName");
time=e1.readJson(setback,"UpdateTime");
text=e1.readJson(setback,"UpdateText");
allow=e1.readJson(setback,"UpdateAllow");
urls=e1.readJson(setback,"UpdateUrl");
urls=e1.urlUnlockString(urls);


}


}

if(zf.dy(setback,"0")){


if(wj.cz(sec_updater)){

setback=wj.dqwb(sec_updater);
version=e1.readJson(setback,"UpdateVersion");
versionname=e1.readJson(setback,"UpdateVersionName");
time=e1.readJson(setback,"UpdateTime");
text=e1.readJson(setback,"UpdateText");
urls=e1.readJson(setback,"UpdateUrl");
urls=e1.urlUnlockString(urls);

final int b1=Integer.parseInt(version);

final java.lang.String b2=time;

final java.lang.String b3=text;
update_url=urls;
ewm(update_url);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb5).zf(b1);
st.wb(open.cn.awg.pro.R.id.wb8).zf(b2);
st.wb(open.cn.awg.pro.R.id.wb11).zf(b3);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String e="加载异常...";
st.wb(open.cn.awg.pro.R.id.wb5).zf(e);
st.wb(open.cn.awg.pro.R.id.wb8).zf(e);
st.wb(open.cn.awg.pro.R.id.wb11).zf(e);


}

});


}


}
else{


final int b1=Integer.parseInt(version);

final java.lang.String b2=time;

final java.lang.String b3=text;

if((zf.dy(allow,"0")||zf.cz(allow,wj.dqwb(auid)))&&e1.islogin()){


if(myappversion<b1&&b1!=9999999){

wj.xrwb(sec_updater,setback);


}
else{


int ccc=versionname.compareTo(myappversionname);

if(!zf.dy(versionname,myappversionname)&&ccc>0){

wj.xrwb(sec_updater,setback);


}
else{

wj.sc(sec_updater);


}


}


}
else{

wj.sc(sec_updater);


}
update_url=urls;
ewm(update_url);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb5).zf(b1);
st.wb(open.cn.awg.pro.R.id.wb8).zf(b2);
st.wb(open.cn.awg.pro.R.id.wb11).zf(b3);


}

});


}


}

});

}

public void ewm(java.lang.String url){

final java.lang.String qr=url;
gj.jmxc(new java.lang.Runnable(){

public void run(){


try{


				android.widget.ImageView imageView=(android.widget.ImageView)findViewById(R.id.tx4);
				Bitmap bitmap=com.demo.e4.createQRCodeBitmap(qr, 800, 800,"UTF-8","H", "1");
				imageView.setImageBitmap(bitmap);
			
st.tx(open.cn.awg.pro.R.id.tx4).kjd(0);


}catch(java.lang.Throwable e){

st.tx(open.cn.awg.pro.R.id.tx4).kjd(8);


}


}

});

}

public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw){

i.runlibrary.app.xt$pm pm=xt.pm();

if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"),"false")){


if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"),"true")){

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);


}
else{

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);


}


}

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

final int u=Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


if(zf.dy(a,"1")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);


}

else if(zf.dy(a,"2")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0,0,0,0);


}

else if(zf.dy(a,"3")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);


}

else if(zf.dy(a,"4")){



}
st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj12).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj11).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);

}

public void onWindowFocusChanged(boolean hs){
super.onWindowFocusChanged(hs);

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onRestart(){
super.onRestart();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onStart(){
super.onStart();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


i.runlibrary.app.v.qtgd qtgd1=st.qtgd(open.cn.awg.pro.R.id.qtgd1);

  qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
    @Override
    public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
      if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {
                                         
        float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
        androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
        android.view.ViewConfiguration.get(lei), lei
        );
                                                         
        vw.scrollBy(0, java.lang.Math.round(delta));
        return true;
      }
      return false;
    }
  });

qtgd1.v.requestFocus();

}

public void onResume(){
super.onResume();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){



}
return false;

}

private void $_onClick_i4a4248a0f2(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(0);


}

});

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);


}

});

}

private void $_onClick_i3ab5c5fd62(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);


}

});

}

private void $_onClick_i8217d42a17(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);


}

});

}

private void $_onClick_a81b730195(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj12).kjd(0);


}

});

}

private void $_onClick_i75b44e9f85(android.view.View vw){
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj12).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj11).kjd(0);


}

});
gj.zt(2000);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj11).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(0);


}

});


}

});

}

private void $_onClick_i16ad18f521(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj12).kjd(8);


}

});

}

private void $_onClick_ac03b05d0f(android.view.View vw){
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj12).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj11).kjd(0);


}

});
gj.zt(500);
gj.gb();


}

});

}

private void $_onClick_i11aeec1890(android.view.View vw){

if(st.wb(open.cn.awg.pro.R.id.wb16).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb16).kjd(8);
st.an(open.cn.awg.pro.R.id.an1).kjd(8);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb16).kjd(0);
st.an(open.cn.awg.pro.R.id.an1).kjd(0);


}

});


}

}

private void $_onClick_i171ea34f1a(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);

i.runlibrary.app.v.tx tx14=st.tx(open.cn.awg.pro.R.id.tx14);
tx14.v.performClick();


}

});

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171ea34f1a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i11aeec1890=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i11aeec1890(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_ac03b05d0f=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_ac03b05d0f(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i16ad18f521=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i16ad18f521(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i75b44e9f85=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i75b44e9f85(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_a81b730195=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_a81b730195(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i8217d42a17(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i3ab5c5fd62=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3ab5c5fd62(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4a4248a0f2(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);

android.widget.ImageView i3ab5c5fd62 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx10);
i3ab5c5fd62.setOnClickListener($_on_setOnClickListener_i3ab5c5fd62);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

android.widget.ImageView a81b730195 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx16);
a81b730195.setOnClickListener($_on_setOnClickListener_a81b730195);

android.widget.ImageView i75b44e9f85 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx11);
i75b44e9f85.setOnClickListener($_on_setOnClickListener_i75b44e9f85);

android.widget.ImageView i16ad18f521 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx13);
i16ad18f521.setOnClickListener($_on_setOnClickListener_i16ad18f521);

android.widget.ImageView ac03b05d0f = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx14);
ac03b05d0f.setOnClickListener($_on_setOnClickListener_ac03b05d0f);

android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

__layoutIsLoaded(ay, vw);
}

    public final f22 lei = this, 类 = this;
}
