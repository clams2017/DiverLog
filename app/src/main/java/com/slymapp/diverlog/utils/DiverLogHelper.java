package com.slymapp.diverlog.utils;

import com.slymapp.diverlog.domain.DiverLog;

/**
 * {@link com.slymapp.diverlog.domain.DiverLog}のヘルパークラス
 */
public class DiverLogHelper {

    /**
     * {@link DiverLog}を文字列配列に変換する
     * @param diverLog {@link DiverLog}
     * @return ダイビングNo,ダイビング日時,潜水場所,開始時圧力,終了時圧力,開始時間,終了時間,入水方法,透明度,スーツ種別,ウェイト,平均深度,最大深度,水温
     */
    public static String[] toStringArray(DiverLog diverLog) {
        return new String[]{
                String.valueOf(diverLog.getDivingNumber()),
                DateUtils.toDateTimeString(diverLog.getDate()),
                diverLog.getWeather(),
                diverLog.getPlace(),
                String.valueOf(diverLog.getStartPressure()),
                String.valueOf(diverLog.getEndPressure()),
                DateUtils.toDateTimeString(diverLog.getStartTime()),
                DateUtils.toDateTimeString(diverLog.getEndTime()),
                diverLog.getEntryMethod(),
                String.valueOf(diverLog.getTransparent()),
                diverLog.getSuits(),
                String.valueOf(diverLog.getWeight()),
                String.valueOf(diverLog.getAverageDepth()),
                String.valueOf(diverLog.getMaxDepth()),
                String.valueOf(diverLog.getTemperature())
        };
    }
}
