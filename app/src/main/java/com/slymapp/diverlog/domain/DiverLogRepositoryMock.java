package com.slymapp.diverlog.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * {@link DiverLogRepository}のモッククラス
 */
public class DiverLogRepositoryMock implements DiverLogRepository {
    @Override
    public DiverLog fetch(int divingNo) {
        return createDiverLogMock();
    }

    private DiverLog createDiverLogMock() {
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
        return null;
    }
}
