package com.lusiwei.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created  by lusiwei on 2018/10/14
 */
public class DateUtil {
    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        return sdf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.dateFormat(new Date()));
    }
}
