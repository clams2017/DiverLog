package com.slymapp.diverlog.domain;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;
import com.slymapp.diverlog.utils.DiverLogHelper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * {@link DiverLog}をCSV形式でエクスポートするクラス
 * TODO Migrationのためのバージョン管理追加
 */
public class DiverLogCsvBackupManager implements DiverLogBackupManager {

    //TODO Daggerを利用してDIに置き換える
    private DiverLogRepository repository = new DiverLogRepositoryImpl();

    @Override
    public void exportAllLog(Context context) throws IOException {
        List<DiverLog> list = repository.fetchAll();

        //TODO パーミッションを確認するフローを追加する
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "DiverLogList.csv");
        Log.d("EXPORT_FILE", file.getAbsolutePath());
        if (!file.createNewFile()) {
            throw new IOException("File is not created");
        }
        CSVWriter writer = new CSVWriter(new PrintWriter(file));
        for (DiverLog log : list) {
            writer.writeNext(DiverLogHelper.toStringArray(log));
        }
        writer.flush();
    }

    @Deprecated
    @Override
    public void importLogs(Context context) throws IOException {
        throw new IOException("not implemented");
    }
}
