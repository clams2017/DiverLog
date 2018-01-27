package com.slymapp.diverlog.domain;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * {@link DiverLogRepository}のモッククラス
 */
public class DiverLogRepositoryMock implements DiverLogRepository {

    private SparseArray<DiverLog> diverLogSparseArray;

    public DiverLogRepositoryMock() {
        diverLogSparseArray = createWithMock();
    }

    @Override
    public DiverLog fetch(int divingNo) {
        return diverLogSparseArray.get(divingNo);
    }

    @Override
    public List<DiverLog> fetchAll() {
        List<DiverLog> list = new ArrayList<>(diverLogSparseArray.size());
        for (int i = 0; i < diverLogSparseArray.size(); i++) {
            list.add(diverLogSparseArray.valueAt(i));
        }
        return list;
    }

    @Override
    public int publishDivingNumber() {
        //TODO ログ削除があった場合の動作を確認する
        return diverLogSparseArray.size() + 1;
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
        return diverLog;
    }

    private SparseArray<DiverLog> createWithMock() {
        SparseArray<DiverLog> sparseArray = new SparseArray<>();
        for (int i = 0; i < 30; i++) {
            DiverLog log = createDiverLogMock();
            log.setDivingNumber(i + 1);
            log.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, i + 1, 0, 0, 0).getTime());
            sparseArray.put(log.getDivingNumber(), log);
        }
        return sparseArray;
    }
}
