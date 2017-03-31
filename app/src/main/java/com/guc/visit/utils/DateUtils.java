package com.guc.visit.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhihui on 2017/3/17.
 */

public class DateUtils {

    public static int getBetweenMonth(String date1,String date2){
        Date d = null;
        Date d1 = null;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1 = df.parse(date2);
            d = df.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();

        c.setTime(d);
        System.out.println(df.format(c.getTime()));// 按照yyyyMMdd格式输出
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.setTime(d1);
        int year1 = c.get(Calendar.YEAR);
        int month1 = c.get(Calendar.MONTH);
        int result;
        if (year == year1) {
            result = month1 - month;// 两个日期相差几个月，即月份差
        } else {
            result = 12 * (year1 - year) + month1 - month;// 两个日期相差几个月，即月份差
        }
        return Math.abs(result);
    }

}
