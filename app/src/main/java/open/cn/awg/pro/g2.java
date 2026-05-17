package open.cn.awg.pro;

import i.app.iActivity;
 import xyz.doikki.videoplayer.ijk.IjkPlayerFactory; import xyz.doikki.videoplayer.ijk.IjkPlayer; import xyz.doikki.videoplayer.controller.BaseVideoController; import xyz.doikki.videoplayer.controller.GestureVideoController; import xyz.doikki.videoplayer.controller.IVideoController; import xyz.doikki.videoplayer.player.VideoViewManager; import xyz.doikki.videoplayer.player.VideoViewConfig; import android.media.AudioManager; import android.net.Uri; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools; import java.util.HashMap; import java.util.Map; import android.media.MediaPlayer; import android.content.Context; import java.lang.Exception;import java.lang.*;

public class g2 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.g2);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

final java.lang.String isOpenMap="/data/user/0/open.cn.awg.pro/settings/f15/set3.inf";

try{

cu6();
gj.xc(new java.lang.Thread(){

public void run(){


      android.content.Intent intent = getIntent();
      Context context=open.cn.awg.pro.e6.getContext();
      String action = intent.getAction();
      if (intent.ACTION_VIEW.equals(action)) {
        android.net.Uri uri = intent.getData();
                                                              
        try {
          hqdz = com.demo.e4.getRealPathFromUri(context, uri);
        } catch (Exception e) {
          hqdz = com.demo.e4.getFilePathForN(uri, context);
        }
      }
    
hqdz=zf.qctwkg(hqdz);

if(!zf.dy(hqdz,"")&&!zf.dy(hqdz,null)){

url=hqdz;


}
else{

url=sj.hqtz("url");


}
url=zf.qctwkg(url);

if(!zf.dy(url,null)&&!zf.dy(url,"")){


java.lang.String bt=sj.hqtz("title");
bt=zf.qctwkg(bt);

if(!zf.dy(bt,null)&&!zf.dy(bt,"")){



}
else{


if(zf.ckt(url,"http")){



}
else{


open.cn.awg.pro.e1  e1=new e1(_APPINFO);

java.lang.String[] fhValue=e1.fileinfo("%",url);

java.lang.String bts=fhValue[3];


}


}

if(zf.ckt(url,"http")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj38).kjd(0);


}

});


}

if(zf.cjw(url,".mp3")||zf.cjw(url,".flac")||zf.cz(url,".mp3?")||zf.cz(url,".flac?")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.appInfo.st(open.cn.awg.pro.R.id.video_v2).setVisibility(8);
isMusic=true;
st.xxbj(open.cn.awg.pro.R.id.xxbj13).kjd(8);

if(zf.ckt(url,"http")){

st.xxbj(open.cn.awg.pro.R.id.xxbj38).kjd(0);


}


}

});


}
mPlayer=((xyz.doikki.videoplayer.player.VideoView)(lei.findViewById(open.cn.awg.pro.R.id.video_v2)));

if(zf.dy(wj.dqwb(isOpenMap),"true")){


if(zf.dy(sj.hqtz("identity_name"),"腕上哔哩")){

gj.jmxc(new java.lang.Runnable(){

public void run(){


              Map<String, String> map = new HashMap<String,String>();
              map.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
              map.put("Referer","https://www.bilibili.com");
              map.put("Origin","https://www.bilibili.com");
              mPlayer.setUrl(url,map);
            


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

mPlayer.setUrl(url);


}

});


}


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

mPlayer.setUrl(url);


}

});


}
gj.jmxc(new java.lang.Runnable(){

public void run(){


          mPlayer.setPlayerFactory(IjkPlayerFactory.create());
          mPlayer.setLooping(true);
          mPlayer.start();                 
        


}

});
msetspeed();
msetScreenScaleType();
msetRotation();
maudio();
mplay();
mxh();


}
else{

e1.tsk("提示","文件打开失败");
gj.gb();


}


}

});


}catch(java.lang.Throwable e){

e1.upload_error(e,"g2()");
gj.gb();


}

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
xt.sbxm(false);

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


if(zf.dy(a,"1")){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).nbj(0,"3dp",0,"30dp");
st.xxbj(open.cn.awg.pro.R.id.xxbj11).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj18).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj16).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj25).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj32).nbj("3dp",0,"3dp",0);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");

i.runlibrary.app.v.wb btab=st.wb(open.cn.awg.pro.R.id.backt);
btab.nbj(0,"15dp",0,"3dp");
btab.dqfs("center");


}

else if(zf.dy(a,"2")){


i.runlibrary.app.v.v7lb lbx=st.v7lb(open.cn.awg.pro.R.id.v7lb1);

java.lang.String a001="/data/user/0/open.cn.awg.pro/settings/dpi.inf";

if(wj.cz(a001)==true||zf.dy(wj.dqwb(a001),"true")){


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"3dp",0,"3dp");
wtab.dqfs("center");

i.runlibrary.app.v.wb btab=st.wb(open.cn.awg.pro.R.id.backt);
btab.nbj(0,"3dp",0,"3dp");
btab.dqfs("center");


}
else{


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"9dp",0,"9dp");
wtab.dqfs("center");

i.runlibrary.app.v.wb btab=st.wb(open.cn.awg.pro.R.id.backt);
btab.nbj(0,"9dp",0,"9dp");
btab.dqfs("center");


}
st.xxbj(open.cn.awg.pro.R.id.xxbj8).nbj(0,"3dp",0,"30dp");
st.xxbj(open.cn.awg.pro.R.id.xxbj11).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj18).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj16).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj25).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj32).nbj("3dp",0,"3dp",0);


}

else if(zf.dy(a,"3")){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).nbj(0,"3dp",0,"30dp");
st.xxbj(open.cn.awg.pro.R.id.xxbj11).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj18).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj16).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj25).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj32).nbj("3dp",0,"3dp",0);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");

i.runlibrary.app.v.wb btab=st.wb(open.cn.awg.pro.R.id.backt);
btab.nbj(0,"15dp",0,"3dp");
btab.dqfs("center");


}

else if(zf.dy(a,"4")){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).nbj(0,"3dp",0,"30dp");
st.xxbj(open.cn.awg.pro.R.id.xxbj11).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj18).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj16).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj25).nbj("3dp",0,"3dp",0);
st.xxbj(open.cn.awg.pro.R.id.xxbj32).nbj("3dp",0,"3dp",0);

int uih=sj.zh().pxzdp(pm.ztl);
uih=uih+9;

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,uih+"dp",0,"9dp");
wtab.dqfs("center");


}

java.lang.String set="/data/user/0/open.cn.awg.pro/settings/set3.inf";

if(zf.dy(wj.dqwb(set),"true")){


java.lang.String te=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a8");

java.lang.String[] name=new java.lang.String[]{"title","text"};

java.lang.String[] value=new java.lang.String[]{"新手教程",te};
gj.tz(a3.class,name,value);


}

}

public float x1=((float)(0));

public float x2=((float)(0));

public float y1=((float)(0));

public float y2=((float)(0));

public boolean reTabShow=false;

public boolean isMusic=false;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String hqdz="";

public java.lang.String url;

public xyz.doikki.videoplayer.player.VideoView mPlayer;

public int screenScaleType=1;

public int setRotation=1;

public long jdmax=0;

public double setVolume=1.0;

public boolean play=true;

public boolean cacheplay=false;

public int maxVolume;

public int currentVolume;

public android.net.Uri uri;

public void cu6(){
gj.xc(new java.lang.Thread(){

public void run(){

gj.zt(4000);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj42).kjd(0);


}

});


}

});

}

public void maudio(){

    AudioManager mAudioManager = (AudioManager) getSystemService(lei.AUDIO_SERVICE);
            
    maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            
    currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
  
gj.jmxc(new java.lang.Runnable(){

public void run(){


i.runlibrary.app.v.tdt yin=st.tdt(open.cn.awg.pro.R.id.tdt3);
yin.sxz(maxVolume);
yin.jdz(currentVolume);


}

});

}

public void msetaudio(int i){

    AudioManager mAudioManager = (AudioManager) getSystemService(lei.AUDIO_SERVICE);
    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
  

}

public void mxh(){

final int outime=4000;

final i.runlibrary.app.v.xxbj xxbj8=st.xxbj(open.cn.awg.pro.R.id.xxbj8);

final i.runlibrary.app.v.xxbj xxbj1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);
gj.xc(new java.lang.Thread(){

public void run(){


int intime=0;

while(true){

gj.zt(100);
intime=intime+100;

if(reTabShow==true){

reTabShow=false;
intime=0;


}

if(xxbj8.kjd()==0){

intime=0;


}
else{


if(xxbj1.kjd()==0&&outime-intime<0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj1.kjd(8);


}

});


}
else{



}


}


}


}

});

}

public void mplay(){

final i.runlibrary.app.v.tdt tdt1=st.tdt(open.cn.awg.pro.R.id.tdt1);
gj.xc(new java.lang.Thread(){

public void run(){


while(play){

gj.zt(50);
gj.jmxc(new java.lang.Runnable(){

public void run(){

maudio();

long position=mPlayer.getCurrentPosition();

long duration=mPlayer.getDuration();
tdt1.jdz(position);
tdt1.sxz(duration);
jdmax=duration;
st.wb(open.cn.awg.pro.R.id.wb3).zf(timeChange(position));
st.wb(open.cn.awg.pro.R.id.wb4).zf(timeChange(duration));

if(!zf.dy(mPlayer,null)){


if(mPlayer.isPlaying()==false){

st.wb(open.cn.awg.pro.R.id.wb5).zf("播放");
st.tx(open.cn.awg.pro.R.id.tx2).tx(open.cn.awg.pro.R.mipmap.a12);


}
else{

st.wb(open.cn.awg.pro.R.id.wb5).zf("暂停");
st.tx(open.cn.awg.pro.R.id.tx2).tx(open.cn.awg.pro.R.mipmap.a11);
st.xxbj(open.cn.awg.pro.R.id.xxbj19).kjd(8);

if(isMusic==true){

st.tx(open.cn.awg.pro.R.id.tx3).kjd(0);


}


}


}


}

});


}


}

});

}

public java.lang.String timeChange(long position){

java.lang.String back="00:00:00";

try{

back = open.cn.awg.pro.util.DateUtil.stringForTime2(position);


}catch(java.lang.Throwable __$_e__){



}
return back;

}

public void mplaying(){
gj.xc(new java.lang.Thread(){

public void run(){


if(mPlayer.isPlaying()==true){

gj.jmxc(new java.lang.Runnable(){

public void run(){

mPlayer.pause();


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

mPlayer.start();


}

});


}


}

});

}

public void msetspeed(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


float i=mPlayer.getSpeed();

java.lang.String t=zf.zf(open.cn.awg.pro.R.color.colorAccent);

java.lang.String f="#00000000";

if(i==0.5){

st.xxbj(open.cn.awg.pro.R.id.xxbj17).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj21).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj22).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj23).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(i==1.0){

st.xxbj(open.cn.awg.pro.R.id.xxbj17).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj21).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj22).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj23).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(i==1.5){

st.xxbj(open.cn.awg.pro.R.id.xxbj17).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj21).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj22).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj23).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(i==2.0){

st.xxbj(open.cn.awg.pro.R.id.xxbj17).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj21).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj22).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj23).bj(open.cn.awg.pro.R.drawable.dkenbj);


}


}

});

}

public void msetScreenScaleType(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


int i=screenScaleType;

java.lang.String t=zf.zf(open.cn.awg.pro.R.color.colorAccent);

java.lang.String f="#00000000";

if(i==1){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkdisbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_DEFAULT);


}

else if(i==2){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkdisbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_ORIGINAL);


}

else if(i==3){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkdisbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_CENTER_CROP);


}

else if(i==4){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkdisbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_MATCH_PARENT);


}

else if(i==5){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkdisbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_4_3);


}

else if(i==6){

st.xxbj(open.cn.awg.pro.R.id.xxbj26).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj27).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj28).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj29).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj30).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj37).bj(open.cn.awg.pro.R.drawable.dkenbj);
mPlayer.setScreenScaleType(mPlayer.SCREEN_SCALE_16_9);


}


}

});

}

public void msetRotation(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


int mr=setRotation;

java.lang.String t=zf.zf(open.cn.awg.pro.R.color.colorAccent);

java.lang.String f="#00000000";

if(mr==1){

st.xxbj(open.cn.awg.pro.R.id.xxbj33).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj34).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj35).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj36).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(mr==2){

st.xxbj(open.cn.awg.pro.R.id.xxbj33).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj34).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj35).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj36).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(mr==3){

st.xxbj(open.cn.awg.pro.R.id.xxbj33).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj34).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj35).bj(open.cn.awg.pro.R.drawable.dkenbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj36).bj(open.cn.awg.pro.R.drawable.dkdisbj);


}

else if(mr==4){

st.xxbj(open.cn.awg.pro.R.id.xxbj33).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj34).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj35).bj(open.cn.awg.pro.R.drawable.dkdisbj);
st.xxbj(open.cn.awg.pro.R.id.xxbj36).bj(open.cn.awg.pro.R.drawable.dkenbj);


}


}

});

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);
play=false;

if(!zf.dy(mPlayer,null)){

mPlayer.release();


}

}

public void onPause(){
super.onPause();

java.lang.String bfset1="/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

if(zf.dy(wj.dqwb(bfset1),"false")&&!zf.dy(mPlayer,null)){


if(mPlayer.isPlaying()==true){

mPlayer.pause();
cacheplay=true;


}


}

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


java.lang.String bfset1="/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

if(zf.dy(wj.dqwb(bfset1),"false")&&cacheplay==true&&!zf.dy(mPlayer,null)){

mPlayer.start();
cacheplay=false;


}

}

public void onStart(){
super.onStart();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


java.lang.String bfset1="/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

if(zf.dy(wj.dqwb(bfset1),"false")&&cacheplay==true&&!zf.dy(mPlayer,null)){

mPlayer.start();
cacheplay=false;


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

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


java.lang.String bfset1="/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

if(zf.dy(wj.dqwb(bfset1),"false")&&cacheplay==true&&!zf.dy(mPlayer,null)){

mPlayer.start();
cacheplay=false;


}

}

public void onStop(){
super.onStop();

java.lang.String bfset1="/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

if(zf.dy(wj.dqwb(bfset1),"false")&&!zf.dy(mPlayer,null)){


if(mPlayer.isPlaying()==true){

mPlayer.pause();
cacheplay=true;


}


}

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

java.lang.String set4="/data/user/0/open.cn.awg.pro/settings/f15/set4.inf";

if(zf.dy(wj.dqwb(set4),"false")){


if(kc==4){

gj.gb();


}


}
else{


if(kc==4){



}


}
return false;

}

private void $_onClick_dc0a1b897b(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){


if(st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.backt).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}


}
else{

reTabShow=true;
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}

}

private void $_onClick_d26d5d7080(android.view.View vw){
gj.gb();

}

private void $_onClick_i34dc7bf20a(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd()==8){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(0);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a25);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}

}

private void $_onClick_i2a86c78b06(android.view.View vw){
mplaying();

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
reTabShow=true;
mplaying();

}

private void $_onClick_i464e6cc6fb(android.view.View vw){
reTabShow=true;

}

private boolean $_onLongClick_i464e6cc6fb(android.view.View vw){

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f15/set1.inf";

if(zf.dy(wj.dqwb(set2),"true")){


if(st.wb(open.cn.awg.pro.R.id.backt).kjd()==8){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(0);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a25);
st.wb(open.cn.awg.pro.R.id.backt).kjd(0);
st.qtgd(open.cn.awg.pro.R.id.qtgd1).v.requestFocus();


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.backt).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}


}
return true;

}

private void $_onStopTrackingTouch_d11169f9c0(android.widget.SeekBar vw){

final i.runlibrary.app.v.tdt tdt1=st.tdt(open.cn.awg.pro.R.id.tdt1);

long jds=tdt1.jdz();
mPlayer.seekTo(jds);

}

private void $_onStartTrackingTouch_d11169f9c0(android.widget.SeekBar vw){

}

private void $_onProgressChanged_d11169f9c0(android.widget.SeekBar vw, int ps, boolean fu){

}

private void $_onProgressChanged_i7843ee147b(android.widget.SeekBar vw, int ps, boolean fu){

int jd=st.tdt(open.cn.awg.pro.R.id.tdt3).jdz();
msetaudio(jd);

}

private void $_onStartTrackingTouch_i7843ee147b(android.widget.SeekBar vw){

}

private void $_onStopTrackingTouch_i7843ee147b(android.widget.SeekBar vw){

}

private void $_onClick_i16d7dc6512(android.view.View vw){
screenScaleType=1;
msetScreenScaleType();

}

private void $_onClick_i91cc1b9bf5(android.view.View vw){
screenScaleType=2;
msetScreenScaleType();

}

private void $_onClick_i58d9590cdf(android.view.View vw){
screenScaleType=3;
msetScreenScaleType();

}

private void $_onClick_i3b389d6120(android.view.View vw){
screenScaleType=4;
msetScreenScaleType();

}

private void $_onClick_dc32622ccd(android.view.View vw){
screenScaleType=5;
msetScreenScaleType();

}

private void $_onClick_f2c0485bfc(android.view.View vw){
screenScaleType=6;
msetScreenScaleType();

}

private void $_onClick_i90adf494fc(android.view.View vw){
setRotation=1;
mPlayer.setRotation(((float)(0.0)));
msetScreenScaleType();
msetRotation();

}

private void $_onClick_cce8454c0e(android.view.View vw){
setRotation=2;
mPlayer.setRotation(((float)(90.0)));
msetScreenScaleType();
msetRotation();

}

private void $_onClick_i0924d0fe5d(android.view.View vw){
setRotation=3;
mPlayer.setRotation(((float)(180.0)));
msetScreenScaleType();
msetRotation();

}

private void $_onClick_i689cfe9338(android.view.View vw){
setRotation=4;
mPlayer.setRotation(((float)(270.0)));
msetRotation();
msetScreenScaleType();

}

private void $_onClick_adf370a235(android.view.View vw){
mPlayer.setSpeed(((float)(0.5)));
msetspeed();

}

private void $_onClick_i66be67e25d(android.view.View vw){
mPlayer.setSpeed(((float)(1.0)));
msetspeed();

}

private void $_onClick_f3906a9028(android.view.View vw){
mPlayer.setSpeed(((float)(1.5)));
msetspeed();

}

private void $_onClick_i2318575919(android.view.View vw){
mPlayer.setSpeed(((float)(2.0)));
msetspeed();

}

private void $_onClick_i81652f77b6(android.view.View vw){
xt.jqbxr(url);
e1.tsk_QR("提示","已复制链接至剪切板\n\n扫描二维码 快一步访问",url);

}

private void $_onClick_i263afb26ad(android.view.View vw){
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String[] iyu=new java.lang.String[]{"url"};

java.lang.String[] val=new java.lang.String[]{url};
gj.tz(l2.class,iyu,val);


}

});

}

private void $_onClick_i7c3e3c6b5d(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.backt).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xdbj(open.cn.awg.pro.R.id.xdbj3).kjd(0);

java.lang.String ts1="/data/user/0/open.cn.awg.pro/settings/f15/ts/ts1.inf";

if(wj.cz(ts1)==false){

wj.xrwb(ts1,"1");
e1.tsk("提示","长按屏幕正中间位置可退出\"锁定\"");


}

}

private void $_onClick_ff56a8e7f6(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.backt).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}
gj.tz(f15.class);

}

private void $_onClick_f71caff030(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}
gj.gb();

}

private void $_onClick_i3621e3f4a1(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(8);
st.wb(open.cn.awg.pro.R.id.backt).kjd(8);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a24);


}
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xdbj(open.cn.awg.pro.R.id.xdbj3).kjd(0);

java.lang.String ts1="/data/user/0/open.cn.awg.pro/settings/f15/ts/ts1.inf";

if(wj.cz(ts1)==false){

wj.xrwb(ts1,"1");
e1.tsk("提示","长按屏幕正中间位置可退出\"锁定\"");


}

}

private void $_onClick_i4a4248a0f2(android.view.View vw){
gj.tz(f15.class);

}

private void $_onClick_i6f2c7751cf(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}
gj.gb();

}

private boolean $_onTouch_a6dab51133(android.view.View vw, android.view.MotionEvent me){
reTabShow=true;

if(me.getAction()==me.ACTION_UP){

x2=me.getX();
y2=me.getY();

if(y1-y2>=50){


java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f15/set1.inf";

if(zf.dy(wj.dqwb(set2),"false")){

st.xxbj(open.cn.awg.pro.R.id.xxbj8).kjd(0);
st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx1).tx(open.cn.awg.pro.R.mipmap.a25);
st.wb(open.cn.awg.pro.R.id.backt).kjd(0);
st.qtgd(open.cn.awg.pro.R.id.qtgd1).v.requestFocus();


}


}

else if(y2-y1>=50){



}

else if(x1-x2>=50){



}

else if(x2-x1>=50){



}


}
return false;

}

private void $_onClick_i6614d6d216(android.view.View vw){
gj.gb();

}

private boolean $_onTouch_i28d59c5074(android.view.View vw, android.view.MotionEvent me){
return true;

}

private void $_onClick_i20c2777fab(android.view.View vw){

int i;

}

private boolean $_onLongClick_fc1598ee09(android.view.View vw){
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
st.xdbj(open.cn.awg.pro.R.id.xdbj3).kjd(8);
reTabShow=true;
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_fc1598ee09=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_fc1598ee09(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i20c2777fab(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i28d59c5074=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i28d59c5074(vw, me);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i6614d6d216=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i6614d6d216(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_a6dab51133=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_a6dab51133(vw, me);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i6f2c7751cf(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4a4248a0f2(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i3621e3f4a1=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3621e3f4a1(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_f71caff030=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_f71caff030(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_ff56a8e7f6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_ff56a8e7f6(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i7c3e3c6b5d=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i7c3e3c6b5d(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i263afb26ad=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i263afb26ad(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i81652f77b6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i81652f77b6(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2318575919=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2318575919(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_f3906a9028=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_f3906a9028(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i66be67e25d=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i66be67e25d(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_adf370a235=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_adf370a235(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i689cfe9338=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i689cfe9338(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i0924d0fe5d=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i0924d0fe5d(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_cce8454c0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_cce8454c0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i90adf494fc=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i90adf494fc(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_f2c0485bfc=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_f2c0485bfc(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_dc32622ccd=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_dc32622ccd(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i3b389d6120=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3b389d6120(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i58d9590cdf=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i58d9590cdf(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i91cc1b9bf5=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i91cc1b9bf5(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i16d7dc6512=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i16d7dc6512(vw);
}

};
private android.widget.SeekBar.OnSeekBarChangeListener $_on_setOnSeekBarChangeListener_i7843ee147b=new android.widget.SeekBar.OnSeekBarChangeListener(){

public void onProgressChanged(android.widget.SeekBar vw, int ps, boolean fu){
$_onProgressChanged_i7843ee147b(vw, ps, fu);
}

public void onStartTrackingTouch(android.widget.SeekBar vw){
$_onStartTrackingTouch_i7843ee147b(vw);
}

public void onStopTrackingTouch(android.widget.SeekBar vw){
$_onStopTrackingTouch_i7843ee147b(vw);
}

};
private android.widget.SeekBar.OnSeekBarChangeListener $_on_setOnSeekBarChangeListener_d11169f9c0=new android.widget.SeekBar.OnSeekBarChangeListener(){

public void onStopTrackingTouch(android.widget.SeekBar vw){
$_onStopTrackingTouch_d11169f9c0(vw);
}

public void onStartTrackingTouch(android.widget.SeekBar vw){
$_onStartTrackingTouch_d11169f9c0(vw);
}

public void onProgressChanged(android.widget.SeekBar vw, int ps, boolean fu){
$_onProgressChanged_d11169f9c0(vw, ps, fu);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i464e6cc6fb=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i464e6cc6fb(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i464e6cc6fb=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i464e6cc6fb(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2a86c78b06=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2a86c78b06(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_d26d5d7080(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_dc0a1b897b=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_dc0a1b897b(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

xyz.doikki.videoplayer.player.VideoView b773cd988a = (xyz.doikki.videoplayer.player.VideoView) findViewById(ay, vw, open.cn.awg.pro.R.id.video_v2);

android.widget.LinearLayout dc0a1b897b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj2);
dc0a1b897b.setOnClickListener($_on_setOnClickListener_dc0a1b897b);

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

android.widget.LinearLayout i9926c2f556 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj9);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.TextView i2a86c78b06 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb5);
i2a86c78b06.setOnClickListener($_on_setOnClickListener_i2a86c78b06);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);

android.widget.LinearLayout i464e6cc6fb = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj10);
i464e6cc6fb.setOnClickListener($_on_setOnClickListener_i464e6cc6fb);
i464e6cc6fb.setOnLongClickListener($_on_setOnLongClickListener_i464e6cc6fb);

android.widget.SeekBar d11169f9c0 = (android.widget.SeekBar) findViewById(ay, vw, open.cn.awg.pro.R.id.tdt1);
d11169f9c0.setOnSeekBarChangeListener($_on_setOnSeekBarChangeListener_d11169f9c0);

android.widget.SeekBar i7843ee147b = (android.widget.SeekBar) findViewById(ay, vw, open.cn.awg.pro.R.id.tdt3);
i7843ee147b.setOnSeekBarChangeListener($_on_setOnSeekBarChangeListener_i7843ee147b);

android.widget.TextView i16d7dc6512 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb19);
i16d7dc6512.setOnClickListener($_on_setOnClickListener_i16d7dc6512);

android.widget.TextView i91cc1b9bf5 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb20);
i91cc1b9bf5.setOnClickListener($_on_setOnClickListener_i91cc1b9bf5);

android.widget.TextView i58d9590cdf = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb21);
i58d9590cdf.setOnClickListener($_on_setOnClickListener_i58d9590cdf);

android.widget.TextView i3b389d6120 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb22);
i3b389d6120.setOnClickListener($_on_setOnClickListener_i3b389d6120);

android.widget.TextView dc32622ccd = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb24);
dc32622ccd.setOnClickListener($_on_setOnClickListener_dc32622ccd);

android.widget.TextView f2c0485bfc = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb31);
f2c0485bfc.setOnClickListener($_on_setOnClickListener_f2c0485bfc);

android.widget.TextView i90adf494fc = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb26);
i90adf494fc.setOnClickListener($_on_setOnClickListener_i90adf494fc);

android.widget.TextView cce8454c0e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb27);
cce8454c0e.setOnClickListener($_on_setOnClickListener_cce8454c0e);

android.widget.TextView i0924d0fe5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb28);
i0924d0fe5d.setOnClickListener($_on_setOnClickListener_i0924d0fe5d);

android.widget.TextView i689cfe9338 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb29);
i689cfe9338.setOnClickListener($_on_setOnClickListener_i689cfe9338);

android.widget.TextView adf370a235 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb13);
adf370a235.setOnClickListener($_on_setOnClickListener_adf370a235);

android.widget.TextView i66be67e25d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb14);
i66be67e25d.setOnClickListener($_on_setOnClickListener_i66be67e25d);

android.widget.TextView f3906a9028 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb16);
f3906a9028.setOnClickListener($_on_setOnClickListener_f3906a9028);

android.widget.TextView i2318575919 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb17);
i2318575919.setOnClickListener($_on_setOnClickListener_i2318575919);

android.widget.TextView i81652f77b6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb10);
i81652f77b6.setOnClickListener($_on_setOnClickListener_i81652f77b6);

android.widget.TextView i263afb26ad = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb15);
i263afb26ad.setOnClickListener($_on_setOnClickListener_i263afb26ad);

android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);
i7c3e3c6b5d.setOnClickListener($_on_setOnClickListener_i7c3e3c6b5d);

android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);

android.widget.TextView f71caff030 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb33);
f71caff030.setOnClickListener($_on_setOnClickListener_f71caff030);

android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);
i3621e3f4a1.setOnClickListener($_on_setOnClickListener_i3621e3f4a1);

android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

android.support.v4.widget.NestedScrollView a6dab51133 = (android.support.v4.widget.NestedScrollView) findViewById(ay, vw, open.cn.awg.pro.R.id.qtgd1);
a6dab51133.setOnTouchListener($_on_setOnTouchListener_a6dab51133);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);

android.widget.TextView i6614d6d216 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb23);
i6614d6d216.setOnClickListener($_on_setOnClickListener_i6614d6d216);

android.widget.LinearLayout i28d59c5074 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj19);
i28d59c5074.setOnTouchListener($_on_setOnTouchListener_i28d59c5074);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);

android.widget.TextView fc1598ee09 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb8);
fc1598ee09.setOnLongClickListener($_on_setOnLongClickListener_fc1598ee09);

__layoutIsLoaded(ay, vw);
}

    public final g2 lei = this, 类 = this;
}
