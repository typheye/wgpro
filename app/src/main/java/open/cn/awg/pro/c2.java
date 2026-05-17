package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c2 extends iActivity {

private void $_onClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object lj=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object text=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);
e1.fileTouch(text,lj);

}

private boolean $_onLongClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{


i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object bts=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object lj=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.String[] fhValue=e1.fileinfo("%",lj);

java.lang.String ljs=fhValue[2];

java.lang.Object text=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

if(zf.dy(text,"返回上层目录")){

e1.cdx(3,String.valueOf(bts),String.valueOf(lj),ljs);


}
else{


int ifs=wj.lx(lj);

if(ifs==1){

e1.cdx(2,String.valueOf(bts),String.valueOf(lj),ljs);


}

else if(ifs==2){

e1.cdx(1,String.valueOf(bts),String.valueOf(lj),ljs);


}


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"c2.*");


}
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

android.widget.RelativeLayout i9538cb665d = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c2_v);

__layoutIsLoaded(ay, vw);
}

    public final c2 lei = this, 类 = this;
}
