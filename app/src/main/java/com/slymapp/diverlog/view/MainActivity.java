package com.slymapp.diverlog.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.slymapp.diverlog.R;

public class MainActivity extends AppCompatActivity {

    /**
     * {@link MainActivity}の{@link Intent}を生成する
     *
     * @param context 遷移元
     * @return {@link Intent}
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BootstrapButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(LogAddActivity.createIntent(MainActivity.this));
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment, new DummyFragment())
                        .commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, LogListFragment.newInstance())
                .commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.action_import:
//                importLogs();
//                return true;
//            case R.id.action_export:
//                exportLogs();
//                return true;
//            case R.id.action_settings:
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void importLogs() {
//        try {
//            new DiverLogJsonBackupManager().importLogs(this);
//            Toast.makeText(this, "ログを読み込みました。", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(this, "ログの読み込みに失敗した。", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void exportLogs() {
//        try {
//            new DiverLogJsonBackupManager().exportAllLog(this);
//            Toast.makeText(this, "ログを保存しました。", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            Toast.makeText(this, "ログの保存に失敗しました。。", Toast.LENGTH_SHORT).show();
//        }
//    }
}
