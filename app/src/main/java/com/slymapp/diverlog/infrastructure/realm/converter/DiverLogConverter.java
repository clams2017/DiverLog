package com.slymapp.diverlog.infrastructure.realm.converter;

import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.infrastructure.realm.entity.DiverLogEntity;

/**
 * {@link com.slymapp.diverlog.domain.DiverLog}のコンバータ
 */
public class DiverLogConverter {

    /**
     * {@link DiverLog}から{@link DiverLogEntity}を生成する
     *
     * @param log {@link DiverLog}
     * @return {@link DiverLogEntity}
     */
    public static DiverLogEntity createEntity(DiverLog log) {
        DiverLogEntity entity = new DiverLogEntity();
        entity.setDivingNumber(log.getDivingNumber());
        entity.setDate(log.getDate());
        entity.setWeather(log.getWeather());
        entity.setPlace(log.getPlace());
        entity.setEntryMethod(log.getEntryMethod());
        entity.setTransparent(log.getTransparent());
        entity.setStartTime(log.getStartTime());
        entity.setEndTime(log.getEndTime());
        entity.setStartPressure(log.getStartPressure());
        entity.setEndPressure(log.getEndPressure());
        entity.setSuits(log.getSuits());
        entity.setWeight(log.getWeight());
        entity.setAverageDepth(log.getAverageDepth());
        entity.setMaxDepth(log.getMaxDepth());
        entity.setTemperature(log.getTemperature());
        return entity;
    }

    /**
     * {@link DiverLogEntity}から{@link DiverLog}を生成する
     *
     * @param entity {@link DiverLogEntity}
     * @return {@link DiverLog}
     */
    public static DiverLog createDiverLog(DiverLogEntity entity) {
        DiverLog log = new DiverLog();
        log.setDivingNumber(entity.getDivingNumber());
        log.setDate(entity.getDate());
        log.setWeather(entity.getWeather());
        log.setPlace(entity.getPlace());
        log.setEntryMethod(entity.getEntryMethod());
        log.setTransparent(entity.getTransparent());
        log.setStartTime(entity.getStartTime());
        log.setEndTime(entity.getEndTime());
        log.setStartPressure(entity.getStartPressure());
        log.setEndPressure(entity.getEndPressure());
        log.setSuits(entity.getSuits());
        log.setWeight(entity.getWeight());
        log.setAverageDepth(entity.getAverageDepth());
        log.setMaxDepth(entity.getMaxDepth());
        log.setTemperature(entity.getTemperature());
        return log;
    }
}
