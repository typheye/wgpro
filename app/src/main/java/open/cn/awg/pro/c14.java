package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c14 extends iActivity {

private void $_onClick_i9d5d168854(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.String l=String.valueOf(lb.lbcfsj(-1));

if(zf.dy(l,"l5_a1_fuwuq_d0313_y2022")){

gj.tz(l5_a1_fuwuq_d0313_y2022.class);


}

else if(zf.dy(l,"j2")){

gj.tz(j2.class);


}

else if(zf.dy(l,"l6")){

gj.tz(l6.class);


}

else if(zf.dy(l,"j4")){


java.lang.String[] a=new java.lang.String[]{"szk"};

java.lang.String[] b=new java.lang.String[]{"true"};
gj.tz(j4.class,a,b);


}
else{

e1.tsk("提示","该功能不存在");


}

}

private boolean $_onLongClick_i9d5d168854(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object l1=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object l2=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object l=lb.lbcfsj(-1);
e1.tsk_QR("详细信息","[功能名称]\n"+l1+"\n\n[功能简介]\n"+l2+"\n\n[功能标识]",l);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i9d5d168854=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i9d5d168854(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i9d5d168854=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i9d5d168854(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.RelativeLayout i9d5d168854 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c14_v);
i9d5d168854.setOnClickListener($_on_setOnClickListener_i9d5d168854);
i9d5d168854.setOnLongClickListener($_on_setOnLongClickListener_i9d5d168854);

__layoutIsLoaded(ay, vw);
}

    public final c14 lei = this, 类 = this;
}
