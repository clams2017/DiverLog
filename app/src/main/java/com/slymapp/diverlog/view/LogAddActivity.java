package com.slymapp.diverlog.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.domain.DiverLog;

public class LogAddActivity extends AppCompatActivity {

    public static final String KEY_DIVER_LOG = "DIVER_LOG";

    /**
     * {@link LogAddActivity}の{@link Intent}を生成する
     *
     * @param context 遷移元
     * @return {@link Intent}
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, LogAddActivity.class);
    }

    /**
     * {@link LogAddActivity}の{@link Intent}を生成し、{@link DiverLog}を格納する
     * @param context 遷移元
     * @param diverLog 編集するログブック
     * @return {@link Intent}
     */
    public static Intent createIntent(Context context, DiverLog diverLog) {
        Intent intent = new Intent(context, LogAddActivity.class);
        intent.putExtra(KEY_DIVER_LOG, diverLog);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_add);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.log_add_activity_fragment_container, LogAddFragment.newInstance())
                .commit();
    }
}
