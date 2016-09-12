package com.gundulsoftware.gundulapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gundulsoftware.gundulapps.R;
import com.gundulsoftware.gundulapps.fragment.SettingFragment;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPengaturan);
        setSupportActionBar(toolbar);


        getFragmentManager().beginTransaction().replace(R.id.konten_frame, new SettingFragment()).commit();

    }
}
