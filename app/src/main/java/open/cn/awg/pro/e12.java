package open.cn.awg.pro;

import i.app.iClass;
 import com.kingqi.zwcj.应用工具;import java.lang.*;

public class e12 extends iClass {

public i.runlibrary.app.v.v7lb$UserAdapter nmyylbspq=null;

public static android.content.Context context;

public java.lang.String cookiepath="/data/user/0/open.cn.awg.pro/data/plugin/tunefree/cookie";

public void csh(){
context= open.cn.awg.pro.e6.getContext();

i.runlibrary.app.v.v7lb lb=st.v7lb(open.cn.awg.pro.R.id.nmyyv7lb1);
nmyylbspq=lb.v7lbspq(c15.class,open.cn.awg.pro.R.layout.c15);

android.support.v7.widget.LinearLayoutManager nx=new android.support.v7.widget.LinearLayoutManager(context,android.support.v7.widget.LinearLayoutManager.VERTICAL,true);

androidx.recyclerview.widget.RecyclerView v7lbdx=lb.st;
v7lbdx.setLayoutManager(((androidx.recyclerview.widget.RecyclerView.LayoutManager)(nx)));

}

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String lrcFix(java.lang.String lr){

java.lang.String lrc=zf.qctwkg(lr);

java.lang.String[] lrcH=zf.fg(lrc,"\n",true);

for(java.lang.String x:lrcH){


java.lang.String x2=zf.qc(x,10,11);

if(zf.ckt(x,"[")&&zf.dy(x2,"]")){


java.lang.String a=zf.qc(x,1,10);

java.lang.String b=zf.qc(x,1,9);
a="["+a+"]";
b="["+b+"]";
lrc=zf.th(lrc,a,b);


}


}
return lrc;

}

public void search_i1(java.lang.Object v, int o){
nmyylbspq.sc();

final int oo=o;

int[] kj=new int[]{open.cn.awg.pro.R.id.wb1,open.cn.awg.pro.R.id.wb3,open.cn.awg.pro.R.id.wb2};

java.lang.String[] jk=new java.lang.String[]{"","",""};

i.runlibrary.app.zf$json jo=zf.json(v);

org.json.JSONObject json=jo.json;

java.lang.Object v_result=jo.hq(json,"result");

java.lang.Object v_code=jo.hq(json,"code");

if(!zf.dy(v_result,"")){


i.runlibrary.app.zf$json jo_z1=zf.json(v_result);

org.json.JSONObject json_z1=jo_z1.json;

java.lang.Object v_songs=jo_z1.hq(json_z1,"songs");

java.lang.Object v_songCount=jo_z1.hq(json_z1,"songCount");

if(!zf.dy(v_songs,"")){


org.json.JSONArray list=jo_z1.dxlb(json_z1,"songs");

int size=jo_z1.cd(list);

while(size>0){

size--;

org.json.JSONObject dx=jo_z1.dx(list,size);

java.lang.Object v_l_name=jo_z1.hq(dx,"name");

java.lang.Object v_l_id=jo_z1.hq(dx,"id");

java.lang.Object v_l_ar=jo_z1.hq(dx,"ar");

if(!zf.dy(v_l_ar,"")){

v_l_ar="";

i.runlibrary.app.zf$json jo_z2=zf.json(dx);

org.json.JSONObject json_z2=jo_z2.json;

org.json.JSONArray list2=jo_z2.dxlb(json_z2,"ar");

int size2=jo_z2.cd(list2);

while(size2>0){

size2=size2-1;

org.json.JSONObject dx2=jo_z2.dx(list2,size2);

java.lang.Object v_l2_id=jo_z2.hq(dx2,"id");

java.lang.Object v_l2_name=jo_z2.hq(dx2,"name");
v_l_ar=v_l_ar+String.valueOf(v_l2_name)+";";


}


}

java.lang.String _v_l_ar=v_l_ar.toString();
v_l_ar=zf.qc(v_l_ar,null,_v_l_ar.length()-1);
jk=new java.lang.String[]{v_l_name.toString(),v_l_ar.toString(),v_l_id.toString()};
nmyylbspq.j(kj,jk);


}


}

java.lang.String nums2=v_songCount.toString();

final java.lang.String ynums=nums2;

int nums2_=Integer.parseInt(nums2)%20;

if(nums2_>0){

nums2=String.valueOf(Integer.parseInt(nums2)-nums2_+20);


}

final java.lang.String nums=nums2;
gj.jmxc(new java.lang.Runnable(){

public void run(){


int loo=Integer.parseInt(nums)/20;

if(zf.dy(ynums,"0")){

nmyylbspq.sc();
st.wb(open.cn.awg.pro.R.id.wb3).zf("未搜索到相关歌曲/歌手");
st.wb(open.cn.awg.pro.R.id.myswb).zf("第1/1页");
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.wb(open.cn.awg.pro.R.id.wb3).kjd(0);


}
else{

st.wb(open.cn.awg.pro.R.id.wb3).zf("共搜索到"+nums+"首歌曲");
st.wb(open.cn.awg.pro.R.id.myswb).zf("第"+oo+"/"+loo+"页");

if(oo==1&&loo==1){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}
st.wb(open.cn.awg.pro.R.id.wb3).kjd(8);


}


}

});


}
gj.jmxc(new java.lang.Runnable(){

public void run(){

nmyylbspq.sx();


}

});

}

public java.lang.String getMCU(){

java.lang.String cookie=wj.dqwb(cookiepath);

java.lang.String[] cf=zf.fg(cookie,";",false);

java.lang.String mcu="";

for(java.lang.String line: cf){


if(zf.ckt(line,"MUSIC_U")){

mcu=line;


}


}
return mcu;

}

public void search_i2(java.lang.Object v, int o){
nmyylbspq.sc();

final int oo=o;

int[] kj=new int[]{open.cn.awg.pro.R.id.wb1,open.cn.awg.pro.R.id.wb3,open.cn.awg.pro.R.id.wb2};

java.lang.String[] jk=new java.lang.String[]{"","",""};

i.runlibrary.app.zf$json jo=zf.json(v);

org.json.JSONObject json=jo.json;

org.json.JSONArray list=jo.dxlb(json,"data");

java.lang.Object count=jo.hq(json,"count");

if(!zf.dy(list,"")){


int size=jo.cd(list);

while(size>0){

size--;

org.json.JSONObject dx=jo.dx(list,size);

java.lang.String v_l_name=jo.hq(dx,"songName").toString();

java.lang.String v_l_id=jo.hq(dx,"songId").toString();

java.lang.String v_l_ar=jo.hq(dx,"artist").toString();

if(zf.dy(v_l_ar,"")){


if(zf.cz(v_l_name," - ")){

v_l_ar=zf.qc(v_l_name," - ",null);
v_l_ar=zf.qctwkg(v_l_ar);
v_l_name=zf.qc(v_l_name,null," - ");
v_l_name=zf.qctwkg(v_l_name);

if(zf.dy(v_l_ar,"")){

v_l_ar="未知歌手";


}


}
else{

v_l_ar="未知歌手";


}


}
jk=new java.lang.String[]{v_l_name,v_l_ar,v_l_id};
nmyylbspq.j(kj,jk);


}

java.lang.String nums2=count.toString();

final java.lang.String ynums=nums2;

int nums2_=Integer.parseInt(nums2)%20;

if(nums2_>0){

nums2=String.valueOf(Integer.parseInt(nums2)-nums2_+20);


}

final java.lang.String nums=nums2;
gj.jmxc(new java.lang.Runnable(){

public void run(){


int loo=Integer.parseInt(nums)/20;

if(zf.dy(ynums,"0")){

nmyylbspq.sc();
st.wb(open.cn.awg.pro.R.id.wb3).zf("未搜索到相关歌曲/歌手");
st.wb(open.cn.awg.pro.R.id.myswb).zf("第1/1页");
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.wb(open.cn.awg.pro.R.id.wb3).kjd(0);


}
else{

st.wb(open.cn.awg.pro.R.id.wb3).zf("共搜索到"+nums+"首歌曲");
st.wb(open.cn.awg.pro.R.id.myswb).zf("第"+oo+"/"+loo+"页");

if(oo==1&&loo==1){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}
st.wb(open.cn.awg.pro.R.id.wb3).kjd(8);


}


}

});


}
gj.jmxc(new java.lang.Runnable(){

public void run(){

nmyylbspq.sx();


}

});

}

public java.lang.String endurl="";

public java.lang.String api="/song/url";

public void loadMusic(java.lang.Object id, java.lang.Object title){

java.lang.String set35="/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/url";

java.lang.String set36="/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/cookie";

final java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

final java.lang.Object xx=id;

final java.lang.String url=zf.qctwkg(wj.dqwb(set35))+api;

final java.lang.String url22="https://auth.sayqz.com/";

final java.lang.String url_=zf.qctwkg(wj.dqwb(set35));

final i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj6);

final i.runlibrary.app.v.xxbj x3=st.xxbj(open.cn.awg.pro.R.id.xxbj4);

final java.lang.Object t=title;
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(0);
x3.kjd(8);


}

});

if(zf.dy(xx,"")){

e1.tsk("提示","ID不能为空");
gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(8);
x3.kjd(0);


}

});


}
else{


java.lang.String setback="";

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String sjzx=xt.sj(4);

java.lang.String whq="?path=song/url"+ljf+"id="+xx+ljf+"level=standard"+ljf+"cookie="+getMCU();

java.lang.String back=wl.hq(url22+whq,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{

setback=back;


}

if(zf.dy(setback,"0")){

e1.tsk("提示","加载失败,请重试(-1)");
gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(8);
x3.kjd(0);


}

});


}
else{


java.lang.String whq2="/lyric?id="+xx;

java.lang.String back2=wl.hq(url_+whq2,null,"utf-8");

if(!zf.dy(back2,"")||!zf.dy(back2,null)){


try{


i.runlibrary.app.zf$json jo_l1=zf.json(back2);

org.json.JSONObject json_l1=jo_l1.json;

java.lang.Object lrc=jo_l1.hq(json_l1,"lrc");

if(lrc.toString().length()>4){


i.runlibrary.app.zf$json jo_l2=zf.json(lrc);

org.json.JSONObject json_l2=jo_l2.json;
back2=String.valueOf(jo_l2.hq(json_l2,"lyric"));
back2=back2.toString();
back2=lrcFix(back2);


}
else{

back2="";


}


}catch(java.lang.Throwable e){

back2="";


}


}
else{

back2="";


}

try{


i.runlibrary.app.zf$json jo_z1=zf.json(setback);

org.json.JSONObject json_z1=jo_z1.json;

org.json.JSONArray list=jo_z1.dxlb(json_z1,"data");

int size=jo_z1.cd(list);

java.lang.String url="";

while(size>0){

size=size-1;

org.json.JSONObject dx=jo_z1.dx(list,size);
url=String.valueOf(jo_z1.hq(dx,"url"));


}

if(zf.dy(e1.urlLockString(url),"0")){

e1.tsk("提示","获取链接失败(-2)");


}
else{


final java.lang.String[] name=new java.lang.String[]{"url","title","lrc"};

final java.lang.Object[] value=new java.lang.Object[]{url,t,back2};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(g2.class,name,value);


}

});


}


}catch(java.lang.Throwable e){

e1.tsk("提示","获取链接失败(-3)");


}
gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(8);
x3.kjd(0);


}

});


}


}


}

});

}

public e12(i.runlibrary.app.AppInfo _APPINFO){
super(_APPINFO);

}

    public final e12 lei = this, 类 = this;
}
