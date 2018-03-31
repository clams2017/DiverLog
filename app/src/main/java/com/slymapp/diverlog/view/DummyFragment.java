package com.slymapp.diverlog.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.databinding.FragmentDummyBinding;
import com.slymapp.diverlog.domain.DiverLogBackupManager;
import com.slymapp.diverlog.domain.DiverLogJsonBackupManager;

/**
 * Hello Worldを表示する、画面遷移の実装のダミー画面
 */
public class DummyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        FragmentDummyBinding binding = DataBindingUtil.bind(view);
        binding.sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiverLogBackupManager backupManager = new DiverLogJsonBackupManager();
                backupManager.importLogs(getContext(), "DiverLogList.csv");
                //              backupManager.exportAllLog(getContext(), "DiverLogList.csv");
                Toast.makeText(view.getContext(), "exported!!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
