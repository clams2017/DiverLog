package com.slymapp.diverlog.infrastructure.realm;

import android.support.annotation.NonNull;

import com.slymapp.diverlog.domain.DiverLog;
import com.slymapp.diverlog.domain.DiverLogRepository;
import com.slymapp.diverlog.infrastructure.realm.entity.DiverLogEntity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * {@link com.slymapp.diverlog.domain.DiverLogRepository}のRealm実装
 */
public class DiverLogRepositoryImpl implements DiverLogRepository {

    public DiverLogRepositoryImpl() {
        //TODO fetch確認用にデータを入れておく。書き込み処理の実装後に削除する
        initWithMock();
    }

    @Override
    public DiverLog fetch(int divingNo) {
        DiverLogEntity diverLogEntity;
        try (Realm realm = Realm.getDefaultInstance()) {
            diverLogEntity = realm.where(DiverLogEntity.class)
                    .equalTo("divingNumber", divingNo)
                    .findFirst();
        }
        if (diverLogEntity == null) {
            return null;
        }
        return diverLogEntity.toDiverLog();
    }

    @Override
    public List<DiverLog> fetchAll() {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<DiverLogEntity> results = realm.where(DiverLogEntity.class).findAll();
            return Observable.fromIterable(results)
                    .map(new Function<DiverLogEntity, DiverLog>() {
                        @Override
                        public DiverLog apply(DiverLogEntity diverLogEntity) throws Exception {
                            return diverLogEntity.toDiverLog();
                        }
                    })
                    .toList()
                    .blockingGet();
        }
    }

    private void initWithMock() {
        try (Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {
                    for (int i = 0; i < 30; i++) {
                        DiverLogEntity entity = createDiverLogEntityMock();
                        // オブジェクト差分が分かりやすいよう、divingNumberとdateを置き換え
                        entity.setDivingNumber(i + 1);
                        entity.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, i + 1, 0, 0, 0).getTime());
                        realm.copyToRealmOrUpdate(entity);
                    }
                }
            });
        }
    }

    @NonNull
    private DiverLogEntity createDiverLogEntityMock() {
        DiverLog diverLog = new DiverLog();
        diverLog.setDivingNumber(1);
        diverLog.setDate(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 0, 0, 0).getTime());
        diverLog.setWeather("晴れ");
        diverLog.setPlace("どこかの海");
        diverLog.setEntryMethod("ボート");
        diverLog.setTransparent(30);
        diverLog.setStartTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 0, 0).getTime());
        diverLog.setEndTime(new GregorianCalendar(2017, Calendar.DECEMBER, 23, 12, 30, 0).getTime());
        diverLog.setStartPressure(300);
        diverLog.setEndPressure(0);
        diverLog.setSuits("ドライ");
        diverLog.setWeight(15);
        diverLog.setAverageDepth(20);
        diverLog.setMaxDepth(40);
        diverLog.setTemperature(10);
        return new DiverLogEntity(diverLog);
    }


}
