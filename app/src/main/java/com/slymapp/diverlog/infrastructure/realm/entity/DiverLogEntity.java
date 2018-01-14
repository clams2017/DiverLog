package com.slymapp.diverlog.infrastructure.realm.entity;

import com.slymapp.diverlog.domain.DiverLog;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * RealmObject for {@link com.slymapp.diverlog.domain.DiverLog}
 */
public class DiverLogEntity extends RealmObject {

    @PrimaryKey
    private int divingNumber;
    private Date date;
    private String weather;
    private String place;
    private String entryMethod;
    private int transparent;
    private Date startTime;
    private Date endTime;
    private int startPressure;
    private int endPressure;
    private String suits;
    private int weight;
    private float averageDepth;
    private float maxDepth;
    private float temperature;

    public DiverLogEntity() {

    }

    public DiverLogEntity(DiverLog log) {
        setDivingNumber(log.getDivingNumber());
        setDate(log.getDate());
        setWeather(log.getWeather());
        setPlace(log.getPlace());
        setEntryMethod(log.getEntryMethod());
        setTransparent(log.getTransparent());
        setStartTime(log.getStartTime());
        setEndTime(log.getEndTime());
        setStartPressure(log.getStartPressure());
        setEndPressure(log.getEndPressure());
        setSuits(log.getSuits());
        setWeight(log.getWeight());
        setAverageDepth(log.getAverageDepth());
        setMaxDepth(log.getMaxDepth());
        setTemperature(log.getTemperature());
    }

    public int getDivingNumber() {
        return divingNumber;
    }

    public void setDivingNumber(int divingNumber) {
        this.divingNumber = divingNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEntryMethod() {
        return entryMethod;
    }

    public void setEntryMethod(String entryMethod) {
        this.entryMethod = entryMethod;
    }

    public int getTransparent() {
        return transparent;
    }

    public void setTransparent(int transparent) {
        this.transparent = transparent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStartPressure() {
        return startPressure;
    }

    public void setStartPressure(int startPressure) {
        this.startPressure = startPressure;
    }

    public int getEndPressure() {
        return endPressure;
    }

    public void setEndPressure(int endPressure) {
        this.endPressure = endPressure;
    }

    public String getSuits() {
        return suits;
    }

    public void setSuits(String suits) {
        this.suits = suits;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getAverageDepth() {
        return averageDepth;
    }

    public void setAverageDepth(float averageDepth) {
        this.averageDepth = averageDepth;
    }

    public float getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(float maxDepth) {
        this.maxDepth = maxDepth;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public DiverLog toDiverLog() {
        DiverLog log = new DiverLog();
        log.setDivingNumber(getDivingNumber());
        log.setDate(getDate());
        log.setWeather(getWeather());
        log.setPlace(getPlace());
        log.setEntryMethod(getEntryMethod());
        log.setTransparent(getTransparent());
        log.setStartTime(getStartTime());
        log.setEndTime(getEndTime());
        log.setStartPressure(getStartPressure());
        log.setEndPressure(getEndPressure());
        log.setSuits(getSuits());
        log.setWeight(getWeight());
        log.setAverageDepth(getAverageDepth());
        log.setMaxDepth(getMaxDepth());
        log.setTemperature(getTemperature());
        return log;
    }
}
