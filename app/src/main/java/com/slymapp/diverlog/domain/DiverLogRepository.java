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
     * ダイバーログを追加します。
     * ダイビングナンバーが一意でない場合は追加に失敗します。
     * 一意となるダイビングナンバーは{@link #publishDivingNumber()}を利用してください。
     *
     * @param log {@link DiverLog}
     * @return true→追加成功, false→追加失敗
     */
    boolean create(DiverLog log);

    /**
     * 一意となるダイビングナンバーを発行します。
     *
     * @return ダイビングナンバー
     */
    int publishDivingNumber();
}

