package open.cn.awg.pro;

import i.app.iActivity;
 import android.content.Intent; import android.content.Context;import java.lang.*;

public class c4 extends iActivity {

private void $_onClick_e1433e26d6(android.view.View vw){

final open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{


final i.runlibrary.app.sj$lb lb=sj.lb(vw);
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);


}

});

java.lang.String[] aa;

java.lang.String[] bb;

java.lang.String dbl="";

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object b=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object c=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

java.lang.Object d=lb.lbcfsj(open.cn.awg.pro.R.id.wb4);

java.lang.String es=st.wb(open.cn.awg.pro.R.id.zywblj).zf();

java.lang.String cache1="/data/user/0/open.cn.awg.pro/cache/1";

java.lang.String cache2="/data/user/0/open.cn.awg.pro/cache/2";

java.lang.String cache3="/data/user/0/open.cn.awg.pro/cache/3";

java.lang.String cache00="/data/user/0/open.cn.awg.pro/cache/00";

java.lang.String cache41="/data/user/0/open.cn.awg.pro/cache/41";

if(zf.dy(a,"返回")){

e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"删除")){


final java.lang.String[] name=new java.lang.String[]{"b","c","d","es"};

final java.lang.Object[] value=new java.lang.Object[]{b,c,d,es};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(d8.class,name,value);


}

});
gj.xc(new java.lang.Thread(){

public void run(){

gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

e1.cdx(0,null,null,null);


}

});


}

});


}

else if(zf.dy(a,"刷新")){

e1.sx(String.valueOf(c),es);
e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"复制")){


if(wj.cz(cache1)==true){

wj.sc(cache1);


}
wj.xrwb(cache1,d);

java.lang.String z="已复制,长按粘贴";
e1.tsk("提示",z);
e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"剪切")){


if(wj.cz(cache2)==true){

wj.sc(cache2);


}
wj.xrwb(cache2,d);

java.lang.String z="已剪切,长按粘贴";
e1.tsk("提示",z);
e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"粘贴到当前目录")){


if(wj.cz(cache1)==true){


java.lang.String h1=wj.dqwb(cache1);
bb=e1.fileinfo(wj.dqwb(cache00),h1);

if(zf.dy(bb[0],"true")){

dbl=es+bb[3];


}

java.lang.String pb1="/data/user/0/open.cn.awg.pro/settings";

java.lang.String pb2="/data/user/0/open.cn.awg.pro/data";

java.lang.String pb3="/data/user/0/open.cn.awg.pro/fix";

java.lang.String pb4="/data/user/0/open.cn.awg.pro/cache";

java.lang.String pb5="/data/user/0/open.cn.awg.pro/files/cache";

java.lang.String pb6="/storage/emulated/0/Android/data";

java.lang.String pb7="/storage/emulated/0/Android/obb";

if(zf.ckt(dbl,pb1)||zf.ckt(dbl,pb2)||zf.ckt(dbl,pb3)||zf.ckt(dbl,pb4)||zf.ckt(dbl,pb5)||(xt.sbxx().sdk>29&&zf.ckt(dbl,pb6))||(xt.sbxx().sdk>29&&zf.ckt(dbl,pb7))){

e1.tsk("提示","非法操作");


}
else{


if(zf.dy(h1,es)){


final java.lang.String z="粘贴失败\n请选择其他目录";
e1.tsk("提示",z);


}
else{


java.lang.String m="cp -r \""+h1+"\" \""+es+"\"";
wj.sc(cache1);
aa=com.demo.e3.cmd(lei,m,false);

if(zf.dy(aa[0],"")&&!zf.dy(aa[1],"")){


final java.lang.String z="粘贴失败\n"+aa[1];
e1.tsk("提示",z);


}

else if(!zf.dy(aa[0],"")&&zf.dy(aa[1],"")){


final java.lang.String z="粘贴成功";
e1.tsk("提示",z);


}
else{


final java.lang.String z="粘贴成功";
e1.tsk("提示",z);


}


}


}


}

else if(wj.cz(cache2)==true){


java.lang.String h2=wj.dqwb(cache2);
bb=e1.fileinfo(wj.dqwb(cache00),h2);

if(zf.dy(bb[0],"true")){

dbl=es+bb[3];


}

java.lang.String pb1="/data/user/0/open.cn.awg.pro/settings";

java.lang.String pb2="/data/user/0/open.cn.awg.pro/data";

java.lang.String pb3="/data/user/0/open.cn.awg.pro/fix";

java.lang.String pb4="/data/user/0/open.cn.awg.pro/cache";

java.lang.String pb5="/data/user/0/open.cn.awg.pro/files/cache";

java.lang.String pb6="/storage/emulated/0/Android/data";

java.lang.String pb7="/storage/emulated/0/Android/obb";

if(zf.ckt(dbl,pb1)||zf.ckt(dbl,pb2)||zf.ckt(dbl,pb3)||zf.ckt(dbl,pb4)||zf.ckt(dbl,pb5)||(xt.sbxx().sdk>29&&zf.ckt(dbl,pb6))||(xt.sbxx().sdk>29&&zf.ckt(dbl,pb7))){

e1.tsk("提示","非法操作");


}
else{


if(zf.dy(h2,es)){


final java.lang.String z="粘贴失败\n请选择其他目录";
e1.tsk("提示",z);


}
else{


java.lang.String m="mv \""+h2+"\" \""+es+"\"";
wj.sc(cache2);
aa=com.demo.e3.cmd(lei,m,false);

if(zf.dy(aa[0],"")&&!zf.dy(aa[1],"")){


final java.lang.String z="粘贴失败\n"+aa[1];
e1.tsk("提示",z);


}

else if(!zf.dy(aa[0],"")&&zf.dy(aa[1],"")){


final java.lang.String z="粘贴成功";
e1.tsk("提示",z);


}
else{


final java.lang.String z="粘贴成功";
e1.tsk("提示",z);


}


}


}


}
e1.sx(String.valueOf(c),es);
e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"取消粘贴")){

wj.sc(cache1);
wj.sc(cache2);

java.lang.String z="已取消";
e1.tsk("提示",z);
e1.cdx(0,null,null,null);


}

else if(zf.dy(a,"重命名")){

wj.xrwb(cache3,"重命名");
e1.cdx(4,String.valueOf(c),String.valueOf(d),es);


}

else if(zf.dy(a,"新建目录")){

wj.xrwb(cache3,"新建目录");
e1.cdx(4,String.valueOf(c),String.valueOf(d),es);


}

else if(zf.dy(a,"新建文件")){

wj.xrwb(cache3,"新建文件");
e1.cdx(4,String.valueOf(c),String.valueOf(d),es);


}

else if(zf.dy(a,"打开方式")){

e1.cdx(6,String.valueOf(c),String.valueOf(d),es);


}

else if(zf.dy(a,"属性")){


if(wj.lx(d)==2){


java.lang.String back;

java.lang.String m="du -sh \""+d+"\"";
aa=com.demo.e3.cmd(lei,m,false);

if(zf.dy(aa[0],"")&&!zf.dy(aa[1],"")){

back="获取失败";


}

else if(!zf.dy(aa[0],"")&&zf.dy(aa[1],"")){

back=aa[0];
back=zf.qc(back,null,d);
back=zf.qctwkg(back);


}
else{

back="获取失败";


}
wj.xrwb(cache41,back);


}
else{



}
e1.cdx(5,String.valueOf(c),String.valueOf(d),es);


}
else{



}


}

});


}catch(java.lang.Throwable e){

e1.upload_error(e,"c4.*");


}

}

private boolean $_onLongClick_e1433e26d6(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);
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

android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

android.widget.RelativeLayout i1aae26e9a0 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c4_v);

__layoutIsLoaded(ay, vw);
}

    public final c4 lei = this, 类 = this;
}
