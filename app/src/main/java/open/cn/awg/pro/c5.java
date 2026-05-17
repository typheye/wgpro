package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c5 extends iActivity {

private void $_onClick_e1433e26d6(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{


i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object b=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

if(zf.dy(a,"返回")){

e1.cdx(0,null,null,null);


}
else{

e1.tsk(a,b);


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"c5.*");


}

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e1433e26d6(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);

__layoutIsLoaded(ay, vw);
}

    public final c5 lei = this, 类 = this;
}
