package org.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class util {
    public static String getNowDateStr(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return formatter.format(now);

    }
}
