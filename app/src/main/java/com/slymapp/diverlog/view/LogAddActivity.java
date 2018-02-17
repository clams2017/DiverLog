package com.slymapp.diverlog.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.slymapp.diverlog.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_add);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.log_add_activity_fragment_container, LogAddFragment.newInstance())
                .commit();
    }
}
