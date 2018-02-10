package com.hassdata.hserp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static SimpleDateFormat sdfymd=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdfymdhms=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdfymdhm=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdfymdh=new SimpleDateFormat("yyyy-MM-dd HH");
    public static String dateFormatYMD(Date date){
        return sdfymd.format(date);
    }
    public static String dateFormatYMDHMS(Date date){
        return sdfymdhms.format(date);
    }
    public static String dateFormatYMDHM(Date date){
        return sdfymdhm.format(date);
    }
    public static String dateFormatYMDH(Date date){
        return sdfymdh.format(date);
    }

}
