package open.cn.awg.pro;

import i.app.iClass;
 import com.kingqi.zwcj.应用工具; import android.app.NotificationManager; import androidx.core.app.NotificationCompat; import android.app.Notification; import android.content.Intent; import android.os.Bundle; import android.graphics.BitmapFactory; import android.app.PendingIntent; import android.os.Build; import android.app.NotificationChannel; import android.app.KeyguardManager;import java.lang.*;

public class e13 extends iClass {

public static java.lang.String set17="/data/user/0/open.cn.awg.pro/settings/db/talking.db";

public static i.runlibrary.app.sj$sjk sjk;

public static java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f18/set2.inf";

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public open.cn.awg.pro.e11  e11=new e11(_APPINFO);

public open.cn.awg.pro.easy  easy=new easy(_APPINFO);

public open.cn.awg.pro.carton  carton=new carton(_APPINFO);

public static android.content.Context context;

public static android.app.NotificationManager notificationManager;

public static java.lang.String channelId="chat";

public static int msgids=1;

public static i.runlibrary.app.st$xfc xfc=null;

public boolean state_remotelock=false;

public boolean state_ltstz=false;

public void upload_log(java.lang.Object type_, java.lang.Object log_){

final java.lang.Object type=type_;

final java.lang.Object log=log_;
gj.xc(new java.lang.Thread(){

public void run(){


java.lang.String urls="";

final java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f11/set2.inf";
urls=e1.urlUnlockString(wj.dqwb(set2));

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String idget=urls+"class/api.php?type=release"+ljf+"id=405";

if(zf.dy(log,"")){



}
else{


java.lang.String namex0="日志上报";

final java.lang.String namex=namex0;

java.lang.String xxk="[日志类型]\n"+type+"\n[日志信息]\n"+log;

final java.lang.String xx=xxk;

final java.lang.String url=idget;

java.lang.String setback="";

java.lang.String[] wlhq1=new java.lang.String[]{"name="+namex,"value="+xx,"code=baseTextUpload"};

java.lang.String back=wl.hq(url,wlhq1,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{


if(zf.dy(setback,"0")){

setback="0";


}
else{

setback=back;


}


}

if(zf.dy(setback,"0")){



}
else{


if(zf.dy(setback,"true")){



}
else{



}


}


}


}

});

}

public void bhxfc(){

final java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/other/0xK9HJ4/Enable";

if(zf.dy(wj.dqwb(set1),"true")&&e1.islogin()){

gj.jmxc(new java.lang.Runnable(){

public void run(){


i.runlibrary.app.v.xxbj xxbj=st.xxbj();
xxbj.kg(-2,-2);
xxbj.fx(1);

i.runlibrary.app.v.wb wb=st.wb();
wb.kg(1,1);
wb.zf("");
xxbj.j(wb);

if(zf.dy(xfc,null)){

xfc=st.xfc(xxbj,1,1,0,0);


}


}

});


}

}

public void remotelock(){

final java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/other/0xK9HJ4/Enable";

final java.lang.String user="/data/user/0/open.cn.awg.pro/settings/account/user";

final java.lang.String urls="https://service.typheye.cn/app/com.typheye.awg.remote/command/";

if(state_remotelock==false){

state_remotelock=true;
gj.xc(new java.lang.Thread(){

public void run(){

bhxfc();

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String url=urls+wj.dqwb(user)+"/running.dat";

java.lang.String back=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"cmdv1:lock")){

carton.lockNow(context);


}
state_remotelock=false;


}

});


}

}

public void downAssets(){

java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

final java.lang.String url=urls+"download/assets";

final java.lang.String filename="/data/user/0/open.cn.awg.pro/data/down/assets";

final java.lang.String filename2="@resource/a9d2b8288117333d815efe8e2feb5862";

final java.lang.String assetspath="/data/user/0/open.cn.awg.pro/data/assets/";
gj.xc(new java.lang.Thread(){

public void run(){


int st5=wl.xz(url,filename,true,null,"utf-8","",true,"User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN");

if(st5==0||st5==1){


if(wj.jy(filename,assetspath,true)>0){



}
else{



}


}
else{

wj.fz(filename2,filename,true);

if(wj.jy(filename,assetspath,true)>0){



}
else{



}

if(wj.cz(filename)==false){

e1.tsk("提示","应用资源下载失败，可能导致应用部分内容显示异常！\n请在联网后，重启腕管Pro，我们将重新为您下载应用资源。");


}
else{



}


}


}

});

}

public void run(){

if(e1.getNowUserId()==0){


final java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/other/0xK9HJ4/Enable";

final java.lang.String user="/data/user/0/open.cn.awg.pro/settings/account/user";

final java.lang.String urls="https://service.typheye.cn/app/com.typheye.awg.remote/command/";
context=open.cn.awg.pro.e6.getContext();
wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/ftz");

i.runlibrary.app.sj$zh sjl=sj.zh(wj.dqwb(set2));

final long tim=sjl.zlong(3000);
N_csh();

final open.cn.awg.pro.e1  e1=new e1(_APPINFO);
gj.xc(new java.lang.Thread(){

public void run(){

e11.setup_talklist();
sjk=sj.sjk(set17);

if(!zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/a3.inf"),"0")&&zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f11/set1.inf"),"true")){

ltstz();


}
wj.xrwb("/data/user/0/open.cn.awg.pro/data/AwgCoreService.r","0");

while(wj.cz("/data/user/0/open.cn.awg.pro/data/AwgCoreService.r")){

gj.zt(tim);

if(e1.awgwl_state()==true&&!zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/a3.inf"),"0")&&zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f11/set1.inf"),"true")&&wj.cz("/data/user/0/open.cn.awg.pro/data/ltlb/ftz")==false){

ltstz();


}

boolean aa;

try{

aa=open.cn.awg.pro.SystemAccessibilityService.isAccessibilitySettingsOn(context,open.cn.awg.pro.SystemAccessibilityService.class.getName());


}catch(java.lang.Throwable e){

aa=false;


}

if(e1.islogin()&&zf.dy(wj.dqwb(set1),"true")&&carton.state(context)&&aa){

remotelock();


}
else{

wj.xrwb(set1,"false");


}

if(e1.islogin()==true){

easy.getDataUpdate();
easy.updateUserData();


}
e1.online();


}


}

});


}

}

public void N_cancel(){

try{

notificationManager.cancel(1);


}catch(java.lang.Throwable __$_e__){



}

}

public void N_csh(){

		notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		String channelName = "腕上微聊";
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			int importance = NotificationManager.IMPORTANCE_HIGH;
			NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
			                 
			                            
			                                        
			channel.setImportance(NotificationManager.IMPORTANCE_MAX);
			notificationManager.createNotificationChannel(channel);
		}
	

}

public void N_wl(java.lang.String a, java.lang.String b, java.lang.String c){

final java.lang.String cachehd="/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id";
wj.xrwb(cachehd,c);

final java.lang.String cachehd2="/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";
wj.xrwb(cachehd2,a);

		                                    
		Intent intent = new Intent(context, open.cn.awg.pro.i2.class);
		Bundle bundle=new Bundle();
		bundle.putString("uid",c.toString());
		intent.putExtras(bundle);
		Notification notification = new NotificationCompat.Builder(context, channelId)
		.setAutoCancel(true)
		.setContentTitle(a)
		.setContentText(b)
		.setPriority(Notification.PRIORITY_MAX)
		.setWhen(System.currentTimeMillis())
		.setSmallIcon(R.mipmap.a37)
		.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.c1))
		.setContentIntent(PendingIntent.getActivity(context, 0, intent, 0))
		                       
		.build();
		notificationManager.notify(msgids, notification);
		msgids++;
	

}

public void ltstz(){

final java.lang.String his="/data/user/0/open.cn.awg.pro/data/ltlb/history";

if(wj.cz("/data/user/0/open.cn.awg.pro/data/ltlb/showing")==false){


if(state_ltstz==false){

state_ltstz=true;
gj.xc(new java.lang.Thread(){

public void run(){

wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/ftz","0");

final java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f11/set2.inf";

java.lang.String urls=e1.urlUnlockString(wj.dqwb(set2));

java.lang.String table="_id,uid,name,utext";

java.lang.Object sqlx=null;

i.runlibrary.app.sj$cxsj sjk15=sjk.cxsj("talklist",table,sqlx);

while(sjk15.xyh()){


final java.lang.String rooms=sjk15.sj(2);

final java.lang.String uids=sjk15.sj(1);

java.lang.String sjhc="/data/user/0/open.cn.awg.pro/data/ltlb/chat/"+uids;

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String db="";

if(wj.cz(sjhc)==true){

db=String.valueOf(wj.dx(sjhc));

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String url=urls+"re.php?uid="+uids+ljf+"token="+db;

java.lang.String back=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){



}
else{


java.lang.String be=wj.dqwb(his);

java.lang.String pp="["+uids+"]:";

java.lang.String pp1="["+uids+"]";

if(zf.dy(back,"true")){


java.lang.String pp2=pp+db+pp1;

if(zf.cz(be,pp)){


java.lang.String db2=zf.qc(wj.dqwb(his),pp,pp1);

if(!zf.dy(db2,db)&&update(uids)==true){

N_wl(rooms,getdata(uids),uids);

java.lang.String pp3=pp+db2+pp1;
be=zf.th(be,pp3,pp2);
wj.xrwb(his,be);


}
else{



}


}
else{


if(update(uids)==true){

N_wl(rooms,getdata(uids),uids);
pp2="\n"+pp2;
wj.xrwb(his,pp2);


}
else{



}


}


}

else if(zf.dy(back,"error")){


java.lang.String pp2=pp+"error"+pp1;

if(zf.cz(be,pp)){


java.lang.String db2=zf.qc(wj.dqwb(his),pp,pp1);

if(!zf.dy(db2,"error")){

N_wl(rooms,"该房间不存在或已被删除",uids);

java.lang.String pp3=pp+db2+pp1;
be=zf.th(be,pp3,pp2);
wj.xrwb(his,be);


}
else{



}


}
else{

N_wl(rooms,"该房间不存在或已被删除",uids);
pp2="\n"+pp2;
wj.xrwb(his,pp2);


}


}


}


}


}
wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/ftz");
state_ltstz=false;


}

});


}


}

}

public boolean update(java.lang.Object uid){

boolean isF=false;

try{


if(wj.cz("/data/user/0/open.cn.awg.pro/data/ltlb/showing")==false){


final java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f11/set2.inf";

java.lang.String urls=e1.urlUnlockString(wj.dqwb(set2));

java.lang.String sjhc="/data/user/0/open.cn.awg.pro/data/ltlb/chat/"+uid;

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"chat/"+uid+".json";

java.lang.String back0=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(!zf.dy(back0,"")||!zf.dy(back0,null)){

wj.xrwb(sjhc,back0);
isF=true;


}


}


}catch(java.lang.Throwable __$_e__){



}
return isF;

}

public java.lang.String getdata(java.lang.Object uid){

java.lang.String back="";

java.lang.String sjhc="/data/user/0/open.cn.awg.pro/data/ltlb/chat/"+uid;
back=wj.dqwb(sjhc);

try{


i.runlibrary.app.zf$json jo=zf.json(back);

org.json.JSONObject json=jo.json;

org.json.JSONArray list=jo.dxlb(json,"data");

int size=jo.cd(list);
size=size-1;

org.json.JSONObject dx=jo.dx(list,size);

java.lang.Object data=jo.hq(dx,"data");

java.lang.Object name=jo.hq(dx,"name");

java.lang.String jname="";

java.lang.String jdata="";
jname=name.toString();
jdata=data.toString();

if(zf.cz(jdata,"[PHOTO]")&&zf.cz(jdata,"[P:END]")){


java.lang.String tx=zf.qc(jdata,null,"[PHOTO]");
jdata=tx+"[附件]图片";


}

else if(zf.cz(jdata,"[AUDIO]")&&zf.cz(jdata,"[A:END]")){


java.lang.String tx=zf.qc(jdata,null,"[AUDIO]");
jdata=tx+"[附件]音频";


}

else if(zf.cz(jdata,"[VIDEO]")&&zf.cz(jdata,"[V:END]")){


java.lang.String tx=zf.qc(jdata,null,"[VIDEO]");
jdata=tx+"[附件]视频";


}

else if(zf.cz(jdata,"[OTHER]")&&zf.cz(jdata,"[O:END]")){


java.lang.String tx=zf.qc(jdata,null,"[OTHER]");
jdata=tx+"[附件]文件";


}

else if(zf.cz(jdata,"[INFOS]")&&zf.cz(jdata,"[I:END]")){


java.lang.String tx=zf.qc(jdata,"[INFOS]","[I:END]");
jdata=tx;


}
back=jname+":"+jdata;


}catch(java.lang.Throwable e){

back="您有新的消息";


}
return back;

}

public e13(i.runlibrary.app.AppInfo _APPINFO){
super(_APPINFO);

}

    public final e13 lei = this, 类 = this;
}
