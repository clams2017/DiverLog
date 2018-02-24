package com.slymapp.diverlog.utils;

import com.slymapp.diverlog.domain.DiverLog;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class DiverLogHelperTest {
    @Test
    public void toStringArray() throws Exception {
        DiverLog diverLog = createDiverLogMock();

        String[] actual = DiverLogHelper.toStringArray(diverLog);

        assertThat(actual, is(notNullValue()));
        assertThat(actual.length, is(15));
        assertThat(actual[0], is("1"));
        assertThat(actual[1], is("2017-12-23 00:00:00"));
        assertThat(actual[2], is("晴れ"));
        assertThat(actual[3], is("どこかの海"));
        assertThat(actual[4], is("300"));
        assertThat(actual[5], is("0"));
        assertThat(actual[6], is("2017-12-23 12:00:00"));
        assertThat(actual[7], is("2017-12-23 12:30:00"));
        assertThat(actual[8], is("ボート"));
        assertThat(actual[9], is("30"));
        assertThat(actual[10], is("ドライ"));
        assertThat(actual[11], is("15"));
        assertThat(actual[12], is("20.0"));
        assertThat(actual[13], is("40.0"));
        assertThat(actual[14], is("10.0"));
    }

    private DiverLog createDiverLogMock() {
        DiverLog diverLog = new DiverLog();
        diverLog.setDivingNumber(1);
        diverLog.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 0, 0, 0).getTime());
        diverLog.setWeather("晴れ");
        diverLog.setPlace("どこかの海");
        diverLog.setStartPressure(300);
        diverLog.setEndPressure(0);
        diverLog.setStartTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 0, 0).getTime());
        diverLog.setEndTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 30, 0).getTime());
        diverLog.setEntryMethod("ボート");
        diverLog.setTransparent(30);
        diverLog.setSuits("ドライ");
        diverLog.setWeight(15);
        diverLog.setAverageDepth(20f);
        diverLog.setMaxDepth(40f);
        diverLog.setTemperature(10f);
        return diverLog;
    }


}