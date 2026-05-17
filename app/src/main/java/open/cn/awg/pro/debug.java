package open.cn.awg.pro;

import i.app.iClass;
import java.lang.*;

public class debug extends iClass {

public java.lang.String de="/data/user/0/open.cn.awg.pro/data/debug/state/";

public java.lang.String dd="/data/user/0/open.cn.awg.pro/data/debug/showed.inf";

public boolean getDebugState(java.lang.Object i){

java.lang.String ph=de+i;

if(wj.cz(ph)){


if(zf.dy(wj.dqwb(ph),"1")){

return true;


}


}
return false;

}

public boolean setDebugState(java.lang.Object i, boolean i2){

java.lang.String ph=de+i;

if(i2){


if(wj.xrwb(ph,"1")){

return true;


}


}
else{


if(wj.xrwb(ph,"0")){

return true;


}


}
return false;

}

public boolean isShowed(){

if(wj.cz(dd)){


if(zf.dy(wj.dqwb(dd),"1")){

return true;


}


}
return false;

}

public boolean showed(){

if(wj.xrwb(dd,"1")){

return true;


}
return false;

}

public boolean delShowed(){
wj.sc(dd);
return true;

}

public debug(i.runlibrary.app.AppInfo _APPINFO){
super(_APPINFO);

}

    public final debug lei = this, 类 = this;
}
