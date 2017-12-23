package com.slymapp.diverlog.domain;

import java.util.Date;

public class DiverLog {

    /**
     * ダイビングNo
     */
    private int divingNumber;

    /**
     * 日付
     */
    private Date date;

    /**
     * 天気
     */
    private String weather;

    /**
     * 潜った場所
     */
    private String place;

    /**
     * エントリー方法
     */
    private String entryMethod;

    /**
     * 透明度
     */
    private int transparent;

    /**
     * ダイビング開始時間
     */
    private Date startTime;

    /**
     * ダイビング終了時間
     */
    private Date endTime;

    /**
     * ダイビング開始時の酸素圧力
     */
    private int startPressure;

    /**
     * ダイビング終了時の酸素圧力
     */
    private int endPressure;

    /**
     * ダイビングスーツ種別
     */
    private String suits;

    /**
     * ウェイト
     */
    private int weight;

    /**
     * 平均深度
     */
    private float averageDepth;

    /**
     * 最大深度
     */
    private float maxDepth;

    /**
     * 水温
     */
    private float temperture;

    public int getDivingNumber() {
        return divingNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getWeather() {
        return weather;
    }

    public String getPlace() {
        return place;
    }

    public String getEntryMethod() {
        return entryMethod;
    }

    public int getTransparent() {
        return transparent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getStartPressure() {
        return startPressure;
    }

    public int getEndPressure() {
        return endPressure;
    }

    public String getSuits() {
        return suits;
    }

    public int getWeight() {
        return weight;
    }

    public float getAverageDepth() {
        return averageDepth;
    }

    public float getMaxDepth() {
        return maxDepth;
    }

    public float getTemperture() {
        return temperture;
    }
}
