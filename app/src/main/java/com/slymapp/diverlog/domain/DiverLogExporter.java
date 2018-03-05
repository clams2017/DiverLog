package com.slymapp.diverlog.domain;

import android.content.Context;

/**
 * ダイバーログの外部保存・読み込みをするインターフェース
 */
public interface DiverLogExporter {

    /**
     * ダイバーログをファイル保存する
     * 外部保存をするため、外部ストレージの書き込みパーミッションが必要
     *
     * @param context {@link Context}
     * @param exportedName 出力ファイル名
     */
    void exportAllLog(Context context, String exportedName);
}
