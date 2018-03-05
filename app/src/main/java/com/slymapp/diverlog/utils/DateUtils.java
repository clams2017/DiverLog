package com.slymapp.diverlog.utils;


import org.threeten.bp.DateTimeUtils;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.Date;

/**
 * 日付整形用クラス
 * Created by kaito on 18/01/27.
 */

public class DateUtils {

    private static final String DATE_PATTERN = "yyyy/MM/dd";
    private static final String TIME_PATTERN = "HH:mm";

    /**
     * {@link Date}を日付形式に変換する
     *
     * @param date {@link Date}
     * @return 日付形式 (yyyy/MM/dd)
     */
    public static String toDateString(Date date) {
        return DateUtils.format(date, DATE_PATTERN);
    }

    /**
     * {@link Date} を時刻形式に変換する
     * @param date {@link Date}
     * @return 時刻形式 (HH:mm)
     */
    public static String toTimeString(Date date) {
        return DateUtils.format(date, TIME_PATTERN);
    }

    /**
     * {@link Date} を日時形式に変換する
     * @param date {@link Date}
     * @return 日時形式 (yyyy-MM-dd HH:mm:ss)
     */
    public static String toDateTimeString(Date date){
        return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日付から{@link Date}を生成する
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return {@link Date} 時間は00:00:00で返却される
     */
    public static Date createFromDate(int year, int month, int day) {
        return of(year, month, day, 0, 0, 0);
    }

    /**
     * 時間から{@link Date}を生成する
     *
     * @param hour   時
     * @param minute 分
     * @param second 秒
     * @return {@link Date} 日付は1970/01/01で返却される
     */
    public static Date createFromTime(int hour, int minute, int second) {
        return of(1970, 1, 1, hour, minute, second);
    }

    /**
     * yyyy/MM/dd形式の時間文字列から{@link Date}を生成する
     *
     * @param dateString 日時
     * @return {@link Date} 時間は00:00:00で返却される
     */
    public static Date createFromDate(String dateString) {
        LocalDate ld = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DATE_PATTERN));
        LocalTime lt = LocalTime.of(0, 0, 0);
        return of(LocalDateTime.of(ld, lt));
    }

    /**
     * HH:mm形式の時間文字列から{@link Date}を生成する
     *
     * @param timeString 時刻
     * @return {@link Date} 日付は1970/01/01で返却される
     */
    public static Date createFromTime(String timeString) {
        LocalDate ld = LocalDate.of(1970, 1, 1);
        LocalTime lt = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(TIME_PATTERN));
        return of(LocalDateTime.of(ld, lt));
    }

    // 以下は必要があればpublicに変更して使用する

    /**
     * 日時から{@link Date}を生成する
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   時
     * @param minute 分
     * @param second 秒
     * @return {@link Date}
     */
    private static Date of(int year, int month, int day, int hour, int minute, int second) {
        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute, second);
        return DateTimeUtils.toDate(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 日時を指定した形式に変換する
     *
     * @param date    日時
     * @param pattern 変換形式
     * @return 指定形式で変換した文字列
     */
    private static String format(Date date, String pattern) {
        ZonedDateTime dateTime = DateTimeUtils.toInstant(date).atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * {@link LocalDateTime} を{@link Date}に変換する
     * @param ldt 日時
     * @return 変換後の{@Link Date}
     */
    private static Date of(LocalDateTime ldt) {
        Instant instant = ldt.toInstant(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
        return DateTimeUtils.toDate(instant);
    }
}
