package nowshare.myzchh.cn.nowshare.util;

import android.app.Application;

/**
 * Created by chao on 2015/5/19.
 */
public class localUser{
    private static  String USERNAME;
    private static  String UUID;

    public static  String getUSERNAME(){
        return USERNAME;
    }
    public static  String getUUID(){
        return UUID;
    }
    public static  void setUSERNAME(String username){
        USERNAME= username;
    }
    public static void setUUID(String uuid){
        UUID= uuid;
    }
}