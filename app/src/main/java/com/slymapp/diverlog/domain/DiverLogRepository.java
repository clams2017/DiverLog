package com.slymapp.diverlog.domain;

import java.util.List;

/**
 * ダイバーログのリポジトリ
 */
public interface DiverLogRepository {
    /**
     * ダイバーログを取得します。
     *
     * @param divingNo 対象ログのダイビングナンバー
     * @return {@link DiverLog}
     */
    DiverLog fetch(int divingNo);

    /**
     * ダイバーログを全て取得します。
     *
     * @return {@link DiverLog}のリスト
     */
    List<DiverLog> fetchAll();

    /**
     * 一意となるダイビングナンバーを発行します。
     *
     * @return ダイビングナンバー
     */
    int publishDivingNumber();
}

