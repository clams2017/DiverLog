package com.slymapp.diverlog.domain;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.slymapp.diverlog.infrastructure.realm.DiverLogRepositoryImpl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Flowable;

/**
 * {@link DiverLogBackupManager}のJsonフォーマットの実装
 */
public class DiverLogJsonBackupManager implements DiverLogBackupManager {

    private DiverLogRepository repository = new DiverLogRepositoryImpl();

    private static final String TARGET_FILE = "diverlog.json";

    private final Comparator<DiverLog> LOG_NUMBER_ASC_COMPARATOR = new Comparator<DiverLog>() {
        @Override
        public int compare(DiverLog l1, DiverLog l2) {
            return l1.getDivingNumber() - l2.getDivingNumber();
        }
    }; // ラムダ式で書きたいがバージョンにより対応していない…

    @Override
    public void exportAllLog(Context context) throws IOException {
        File file = getTargetFile();
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("File is not created");
        }

        List<DiverLog> list = repository.fetchAll();
        String outputString = new Gson().toJson(list);
        // exportする場合は必ず上書きで保存する
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(outputString);
        }
    }

    @Override
    public void importLogs(Context context) throws IOException {
        List<DiverLog> inputList = readDiverLogs(getTargetFile());
        if (inputList == null || inputList.isEmpty()) {
            throw new IOException("input is null or empty");
        }

        List<DiverLog> list = Flowable.fromIterable(inputList)
                .sorted(LOG_NUMBER_ASC_COMPARATOR)
                .toList()
                .blockingGet();

        // インポート対象データのみでデータを構築し直す
        repository.deleteAll();
        for (DiverLog input : list) {
            repository.upsert(input);
        }
    }

    private File getTargetFile() {
        return new File(Environment.getExternalStorageDirectory().getPath(), TARGET_FILE);
    }

    @Nullable
    private List<DiverLog> readDiverLogs(File file) throws IOException {
        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, new TypeToken<List<DiverLog>>() {
            }.getType());
        }
    }

}
