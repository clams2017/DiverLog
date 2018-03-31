package com.slymapp.diverlog.domain;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

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
    public void exportAllLog(Context context, String exportedName) {
        File file = getTargetFile();
        try {
            if (!file.exists() && !file.createNewFile()) {
                Log.d("JsonBackupManager", "exportAllLog: not created");
                return;
            }
        } catch (IOException e) {
            Log.d("JsonBackupManager", "exportAllLog: lose file connection");
            return;
        }

        List<DiverLog> list = repository.fetchAll();
        String outputString = new Gson().toJson(list);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(outputString);
        } catch (IOException e) {
            Log.d("JsonBackupManager", "importLogs: cannot read");
        }
    }

    @Override
    public void importLogs(Context context, String exportedName) {
        List<DiverLog> inputList = readDiverLogs(getTargetFile());
        if (inputList == null || inputList.isEmpty()) {
            Log.d("JsonBackupManager", "importLogs: input is empty");
            return;
        }

        List<DiverLog> list = Flowable.fromIterable(inputList)
                .sorted(LOG_NUMBER_ASC_COMPARATOR)
                .toList()
                .blockingGet();

        for (DiverLog input : list) {
            repository.upsert(input);
        }
    }

    private File getTargetFile() {
        return new File(Environment.getExternalStorageDirectory().getPath(), TARGET_FILE);
    }

    @Nullable
    private List<DiverLog> readDiverLogs(File file) {
        try (FileReader reader = new FileReader(file)) {
            return new Gson().fromJson(reader, new TypeToken<List<DiverLog>>() {
            }.getType());
        } catch (IOException e) {
            Log.d("JsonBackupManager", "importLogs: cannot read");
            return null;
        }
    }

}
