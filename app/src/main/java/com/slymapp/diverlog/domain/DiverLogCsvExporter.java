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
 */
public class DiverLogCsvExporter implements DiverLogExporter {

    private DiverLogRepository repository = new DiverLogRepositoryImpl();

    @Override
    public void exportAllLog(Context context, String exportedName) {
        List<DiverLog> list = repository.fetchAll();

        File file = new File(Environment.getExternalStorageDirectory().getPath(), exportedName);
        Log.d("EXPORT_FILE", file.getAbsolutePath());
        try {
            if (!file.createNewFile()) {
                return;
            }
            CSVWriter writer = new CSVWriter(new PrintWriter(file));
            for (DiverLog log : list) {
                writer.writeNext(DiverLogHelper.toStringArray(log));
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
