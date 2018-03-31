package com.slymapp.diverlog.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.slymapp.diverlog.R;
import com.slymapp.diverlog.databinding.FragmentDummyBinding;
import com.slymapp.diverlog.domain.DiverLogBackupManager;
import com.slymapp.diverlog.domain.DiverLogJsonBackupManager;

import java.io.IOException;

/**
 * Hello Worldを表示する、画面遷移の実装のダミー画面
 */
public class DummyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        FragmentDummyBinding binding = DataBindingUtil.bind(view);
        binding.exportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiverLogBackupManager backupManager = new DiverLogJsonBackupManager();
                try {
                    backupManager.exportAllLog(getContext());
                    Toast.makeText(view.getContext(), "exported!!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.d("DummyFragment", e.getMessage());
                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiverLogBackupManager backupManager = new DiverLogJsonBackupManager();
                try {
                    backupManager.importLogs(getContext());
                    Toast.makeText(view.getContext(), "imported!!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Log.d("DummyFragment", e.getMessage());
                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}
