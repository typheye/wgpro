package open.cn.awg.pro;

import i.app.iClass;
import java.lang.*;

public class e11 extends iClass {

public static java.lang.String set17="/data/user/0/open.cn.awg.pro/settings/db/talking.db";

public static i.runlibrary.app.sj$sjk sjk;

public static java.lang.String set27="/data/user/0/open.cn.awg.pro/settings/db/file.db";

public static i.runlibrary.app.sj$sjk sjk2;

public static java.lang.String set47="/data/user/0/open.cn.awg.pro/settings/db/plugin.db";

public static i.runlibrary.app.sj$sjk sjk3;

public void setup_talklist(){
sjk=sj.sjk(set17);

if(sjk.czsjb("talklist")==false){


java.lang.String table="_id integer primary key,uid interger, name text,utext text";

boolean sjk8=sjk.cjsjb("talklist",table);


}

}

public boolean add_talklist(java.lang.Object a, java.lang.Object b, java.lang.Object c){

boolean fhz=false;

i.runlibrary.app.sj$cxsj back=sjk.cxsj("talklist","uid","uid="+a);
back.dyh();

java.lang.String fhs=back.sj(0);

if(zf.dy(fhs,null)){


java.lang.String table="uid,name,utext";

java.lang.String data=a+",'"+b+"','"+c+"'";

boolean sjk11=sjk.cjsj("talklist",table,data);
fhz=true;


}
else{

fhz=false;


}
return fhz;

}

public boolean update_talklist(java.lang.Object a, java.lang.Object b, java.lang.Object c){

boolean fhz=sjk.gxsj("talklist",b+"='"+c+"'","_id="+a);
return fhz;

}

public boolean delete_talklist(java.lang.Object a){

boolean fhz=sjk.scsj("talklist","_id="+a);
return fhz;

}

public int findid_talklist(java.lang.Object a){

int fhz=0;

i.runlibrary.app.sj$cxsj back=sjk.cxsj("talklist","_id,uid","uid="+a);
back.dyh();

java.lang.String fhs=back.sj(0);

if(zf.dy(fhs,null)){



}
else{

fhz=Integer.parseInt(fhs);


}
return fhz;

}

public void print_talklist(){

java.lang.String table="_id,uid,name,utext";

java.lang.Object sqlx=null;

i.runlibrary.app.sj$cxsj sjk15=sjk.cxsj("talklist",table,sqlx);

while(sjk15.xyh()){

gj.sc(":"+sjk15.sj(0)+", "+sjk15.sj(1)+", "+sjk15.sj(2)+", "+sjk15.sj(3));


}

}

public void setup_filelist(){
sjk2=sj.sjk(set27);

if(sjk2.czsjb("filelist")==false){


java.lang.String table="_id integer primary key, path text,value interger";

boolean sjk8=sjk2.cjsjb("filelist",table);


}

}

public boolean update_filelist(java.lang.Object a, int b){

boolean fhz=false;

i.runlibrary.app.sj$cxsj back=sjk2.cxsj("filelist","path","path='"+a+"'");
back.dyh();

java.lang.String fhs=back.sj(0);

if(zf.dy(fhs,null)){


java.lang.String table="path,value";

java.lang.String data="'"+a+"',"+b;
fhz=sjk2.cjsj("filelist",table,data);


}
else{


i.runlibrary.app.sj$cxsj back2=sjk2.cxsj("filelist","_id,path","path='"+a+"'");
back2.dyh();

int fhx=Integer.parseInt(back2.sj(0));
fhz=sjk2.gxsj("filelist","value="+b,"_id="+fhx);


}
return fhz;

}

public int get_filelist(java.lang.Object a){

int v=0;

i.runlibrary.app.sj$cxsj back=sjk2.cxsj("filelist","value,path","path='"+a+"'");
back.dyh();

java.lang.String b=back.sj(0);

if(zf.dy(b,null)){



}
else{

v=Integer.parseInt(b);


}
return v;

}

public void setup_pluginlist(){
sjk3=sj.sjk(set47);

if(sjk3.czsjb("pluginlist")==false){


java.lang.String table="_id integer primary key,uid interger, name text,utext text";

boolean sjk8=sjk3.cjsjb("pluginlist",table);


}

}

public boolean add_pluginlist(java.lang.Object a, java.lang.Object b, java.lang.Object c){

boolean fhz=false;

i.runlibrary.app.sj$cxsj back=sjk3.cxsj("pluginlist","uid","uid="+a);
back.dyh();

java.lang.String fhs=back.sj(0);

if(zf.dy(fhs,null)){


java.lang.String table="uid,name,utext";

java.lang.String data=a+",'"+b+"','"+c+"'";

boolean sjk11=sjk3.cjsj("pluginlist",table,data);
fhz=true;


}
else{

fhz=false;


}
return fhz;

}

public boolean update_pluginlist(java.lang.Object a, java.lang.Object b, java.lang.Object c){

boolean fhz=sjk3.gxsj("pluginlist",b+"='"+c+"'","_id="+a);
return fhz;

}

public boolean delete_pluginlist(java.lang.Object a){

boolean fhz=sjk3.scsj("pluginlist","_id="+a);
return fhz;

}

public int findid_pluginlist(java.lang.Object a){

int fhz=0;

i.runlibrary.app.sj$cxsj back=sjk3.cxsj("pluginlist","_id,uid","uid="+a);
back.dyh();

java.lang.String fhs=back.sj(0);

if(zf.dy(fhs,null)){



}
else{

fhz=Integer.parseInt(fhs);


}
return fhz;

}

public void print_pluginlist(){

java.lang.String table="_id,uid,name,utext";

java.lang.Object sqlx=null;

i.runlibrary.app.sj$cxsj sjk15=sjk3.cxsj("pluginlist",table,sqlx);

while(sjk15.xyh()){

gj.sc(":"+sjk15.sj(0)+", "+sjk15.sj(1)+", "+sjk15.sj(2)+", "+sjk15.sj(3));


}

}

public e11(i.runlibrary.app.AppInfo _APPINFO){
super(_APPINFO);

}

    public final e11 lei = this, 类 = this;
}
