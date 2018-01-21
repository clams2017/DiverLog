package com.slymapp.diverlog.domain;

import java.util.List;

public interface DiverLogRepository {
    DiverLog fetch(int divingNo);

    List<DiverLog> fetchAll();
}
