package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import java.io.File; import android.view.View;import java.lang.*;

public class l5_a3_zhibo_d0420_y2022_a1 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.l5_a3_zhibo_d0420_y2022_a1);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
title=sj.hqtz("title");
text=sj.hqtz("text");
st.wb(open.cn.awg.pro.R.id.wb1).zf("[频道] "+title+"\n[简介] "+text);

java.lang.String urls_=sj.hqtz("urls");

i.runlibrary.app.v.xlcd xlcd1=st.xlcd(open.cn.awg.pro.R.id.xlcd1);

if(zf.dy(urls_,"")){


java.lang.String _url=sj.hqtz("url");
urls=new java.lang.String[]{_url};

java.lang.String[] show=new java.lang.String[]{"[默认源] "+_url};
xlcd1.csh(show);


}
else{

urls=zf.fg(urls_,",",false);
url=urls[0];

java.util.ArrayList<java.lang.String> show=new java.util.ArrayList<java.lang.String>();

int i=0;

for(java.lang.String j: urls){


if(i==0){

show.add("[默认源] "+j);


}
else{

show.add("[备用源"+sj.zh(i).zstring()+"] "+j);


}
i++;


}
xlcd1.csh(show);


}

}

public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw){

if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"),"true")){



}

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

if(zf.dy(a,"4")){

st.xdbj(open.cn.awg.pro.R.id.l5_a3_zhibo_d0420_y2022_a1_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.l5_a3_zhibo_d0420_y2022_a1_v).shxtck(false);

        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

if(zf.dy(a,"1")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");


}

else if(zf.dy(a,"2")){


java.lang.String a001="/data/user/0/open.cn.awg.pro/settings/dpi.inf";

if(wj.cz(a001)==true||zf.dy(wj.dqwb(a001),"true")){


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"3dp",0,"3dp");
wtab.dqfs("center");


}
else{


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"9dp",0,"9dp");
wtab.dqfs("center");


}
st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0,0,0,0);


}

else if(zf.dy(a,"3")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");


}

else if(zf.dy(a,"4")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0,0,0,0);


}

}

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String url="";

public java.lang.String title="";

public java.lang.String text="";

public java.lang.String[] urls=new java.lang.String[]{""};

public void onWindowFocusChanged(boolean hs){
super.onWindowFocusChanged(hs);

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public void onRestart(){
super.onRestart();

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public void onStart(){
super.onStart();

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

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

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_d26d5d7080(android.view.View vw){
gj.gb();

}

private void $_onItemSelected_i004a739c93(android.widget.AdapterView vw, android.view.View view, int pn, long id){
url=urls[pn];

}

private void $_onNothingSelected_i004a739c93(android.widget.AdapterView vw){

}

private void $_onClick_i171ea34f1a(android.view.View vw){

final java.lang.String[] name=new java.lang.String[]{"url","title"};

final java.lang.String[] value=new java.lang.String[]{url,title};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g2.class,name,value);


}

});

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
gj.gb();

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171ea34f1a(vw);
}

};
private android.widget.AdapterView.OnItemSelectedListener $_on_setOnItemSelectedListener_i004a739c93=new android.widget.AdapterView.OnItemSelectedListener(){

public void onItemSelected(android.widget.AdapterView vw, android.view.View view, int pn, long id){
$_onItemSelected_i004a739c93(vw, view, pn, id);
}

public void onNothingSelected(android.widget.AdapterView vw){
$_onNothingSelected_i004a739c93(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_d26d5d7080(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

android.widget.Spinner i004a739c93 = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd1);
i004a739c93.setOnItemSelectedListener($_on_setOnItemSelectedListener_i004a739c93);

android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

__layoutIsLoaded(ay, vw);
}

    public final l5_a3_zhibo_d0420_y2022_a1 lei = this, 类 = this;
}
