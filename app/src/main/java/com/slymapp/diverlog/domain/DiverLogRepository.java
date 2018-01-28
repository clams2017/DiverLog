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
     * ダイビングナンバーが一意となっている場合は追加、
     * ダイビングナンバーが一意でない場合は更新します。
     * 新しいレコードを追加したい場合は、{@link #publishDivingNumber()}を利用して、
     * 一意となるダイビングナンバーを発行してください。
     *
     * @param log {@link DiverLog}
     */
    void upsert(DiverLog log);

    /**
     * 一意となるダイビングナンバーを発行します。
     *
     * @return ダイビングナンバー
     */
    int publishDivingNumber();
}

