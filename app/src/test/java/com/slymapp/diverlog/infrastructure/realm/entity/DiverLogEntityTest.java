package com.slymapp.diverlog.infrastructure.realm.entity;

import com.slymapp.diverlog.domain.DiverLog;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@link DiverLogEntity}のテストクラス
 */
public class DiverLogEntityTest {

    @Test
    public void constructorWithDiverLog() {
        DiverLog diverLog = new DiverLog();
        diverLog.setDivingNumber(1);
        diverLog.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 0, 0, 0).getTime());
        diverLog.setWeather("晴れ");
        diverLog.setPlace("どこかの海");
        diverLog.setEntryMethod("ボート");
        diverLog.setTransparent(30);
        diverLog.setStartTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 0, 0).getTime());
        diverLog.setEndTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 30, 0).getTime());
        diverLog.setStartPressure(300);
        diverLog.setEndPressure(0);
        diverLog.setSuits("ドライ");
        diverLog.setWeight(15);
        diverLog.setAverageDepth(20);
        diverLog.setMaxDepth(40);
        diverLog.setTemperature(10);

        DiverLogEntity actual = new DiverLogEntity(diverLog);

        assertThat(actual.getDivingNumber(), is(diverLog.getDivingNumber()));
        assertThat(actual.getDate(), is(diverLog.getDate()));
        assertThat(actual.getWeather(), is(diverLog.getWeather()));
        assertThat(actual.getPlace(), is(diverLog.getPlace()));
        assertThat(actual.getEntryMethod(), is(diverLog.getEntryMethod()));
        assertThat(actual.getTransparent(), is(diverLog.getTransparent()));
        assertThat(actual.getStartTime(), is(diverLog.getStartTime()));
        assertThat(actual.getEndTime(), is(diverLog.getEndTime()));
        assertThat(actual.getStartPressure(), is(diverLog.getStartPressure()));
        assertThat(actual.getEndPressure(), is(diverLog.getEndPressure()));
        assertThat(actual.getSuits(), is(diverLog.getSuits()));
        assertThat(actual.getWeight(), is(diverLog.getWeight()));
        assertThat(actual.getAverageDepth(), is(diverLog.getAverageDepth()));
        assertThat(actual.getMaxDepth(), is(diverLog.getMaxDepth()));
        assertThat(actual.getTemperature(), is(diverLog.getTemperature()));
    }

    @Test
    public void toDiverLog() throws Exception {
        DiverLogEntity entity = new DiverLogEntity();
        entity.setDivingNumber(1);
        entity.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 0, 0, 0).getTime());
        entity.setWeather("晴れ");
        entity.setPlace("どこかの海");
        entity.setEntryMethod("ボート");
        entity.setTransparent(30);
        entity.setStartTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 0, 0).getTime());
        entity.setEndTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 30, 0).getTime());
        entity.setStartPressure(300);
        entity.setEndPressure(0);
        entity.setSuits("ドライ");
        entity.setWeight(15);
        entity.setAverageDepth(20);
        entity.setMaxDepth(40);
        entity.setTemperature(10);

        DiverLog actual = entity.toDiverLog();

        assertThat(actual.getDivingNumber(), is(entity.getDivingNumber()));
        assertThat(actual.getDate(), is(entity.getDate()));
        assertThat(actual.getWeather(), is(entity.getWeather()));
        assertThat(actual.getPlace(), is(entity.getPlace()));
        assertThat(actual.getEntryMethod(), is(entity.getEntryMethod()));
        assertThat(actual.getTransparent(), is(entity.getTransparent()));
        assertThat(actual.getStartTime(), is(entity.getStartTime()));
        assertThat(actual.getEndTime(), is(entity.getEndTime()));
        assertThat(actual.getStartPressure(), is(entity.getStartPressure()));
        assertThat(actual.getEndPressure(), is(entity.getEndPressure()));
        assertThat(actual.getSuits(), is(entity.getSuits()));
        assertThat(actual.getWeight(), is(entity.getWeight()));
        assertThat(actual.getAverageDepth(), is(entity.getAverageDepth()));
        assertThat(actual.getMaxDepth(), is(entity.getMaxDepth()));
        assertThat(actual.getTemperature(), is(entity.getTemperature()));
    }
}