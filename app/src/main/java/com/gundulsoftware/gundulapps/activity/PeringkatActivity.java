package com.gundulsoftware.gundulapps.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gundulsoftware.gundulapps.R;

public class PeringkatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peringkat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPeringkat);
        setSupportActionBar(toolbar);
    }
}
