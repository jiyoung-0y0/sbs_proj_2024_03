package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class util {
    public static String getNowDateStr(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        Date now = new Date();
        return formatter.format(now);

    }
}
