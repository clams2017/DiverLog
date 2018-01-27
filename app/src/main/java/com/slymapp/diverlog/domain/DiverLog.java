package com.slymapp.diverlog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * ダイバーログ
 */
public class DiverLog implements Serializable {

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
    private float temperature;

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

    public float getTemperature() {
        return temperature;
    }

    // 以下、モックを作るためのセッター
    // TODO Builderクラスに以降する

    public void setDivingNumber(int divingNumber) {
        this.divingNumber = divingNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setEntryMethod(String entryMethod) {
        this.entryMethod = entryMethod;
    }

    public void setTransparent(int transparent) {
        this.transparent = transparent;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setStartPressure(int startPressure) {
        this.startPressure = startPressure;
    }

    public void setEndPressure(int endPressure) {
        this.endPressure = endPressure;
    }

    public void setSuits(String suits) {
        this.suits = suits;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAverageDepth(float averageDepth) {
        this.averageDepth = averageDepth;
    }

    public void setMaxDepth(float maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
