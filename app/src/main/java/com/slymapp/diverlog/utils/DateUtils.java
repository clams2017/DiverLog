package com.slymapp.diverlog.utils;


import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日付整形用クラス
 * Created by kaito on 18/01/27.
 */

public class DateUtils {
    public static String toDateString(int year, int month, int day) {
        // TODO 日付の暫定対応を修正する
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, 0, 0, 0);
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public static String toDateString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return toDateString(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }

    public static String toTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.JAPAN);
        return sdf.format(date);
    }
}
