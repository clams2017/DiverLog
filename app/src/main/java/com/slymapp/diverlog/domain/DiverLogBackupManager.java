package com.slymapp.diverlog.domain;

import android.content.Context;

import java.io.IOException;

/**
 * ダイバーログの外部保存・読み込みをするインターフェース
 */
public interface DiverLogBackupManager {

    /**
     * ダイバーログをファイル保存する
     * 外部保存をするため、外部ストレージの書き込みパーミッションが必要
     *
     * @param context {@link Context}
     * @throws IOException 書き込みに失敗した場合
     */
    void exportAllLog(Context context) throws IOException;

    /**
     * ダイバーログをファイルから読み込む
     * 外部ストレージの読み込みパーミッションが必要
     *
     * @param context {@link Context}
     * @throws IOException       書き込みに失敗した場合
     */
    void importLogs(Context context) throws IOException;
}
