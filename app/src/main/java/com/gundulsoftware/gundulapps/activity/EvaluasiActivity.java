package com.gundulsoftware.gundulapps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gundulsoftware.gundulapps.R;

public class EvaluasiActivity extends AppCompatActivity {

    private int soalBenar;
    private int soalSalah;
    private int kodeSoal;
    private TextView textMapel;
    private TextView textBenar;
    private TextView textSalah;
    private TextView textSkor;
    private android.support.v7.widget.Toolbar toolbaHasilSoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);

        Intent intent = getIntent();
        soalBenar = intent.getIntExtra("soal_benar",0);
        soalSalah = intent.getIntExtra("soal_salah",0);
        kodeSoal = intent.getIntExtra("kode_soal",0);

//        textMapel = (TextView)findViewById(R.id.toolbarHasilSoal);
        textBenar = (TextView)findViewById(R.id.textBenar);
        textSalah = (TextView)findViewById(R.id.textSalah);
        textSkor = (TextView)findViewById(R.id.textSkor);
        toolbaHasilSoal = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarHasilSoal);

        if(kodeSoal == 1)
        {
//            textMapel.setText("Matematika IPA");
            toolbaHasilSoal.setTitle("Matematika IPA");
        } else if (kodeSoal == 2)
        {
//            textMapel.setText("Fisika");
            toolbaHasilSoal.setTitle("Fisika");
        } else if (kodeSoal == 3)
        {
//            textMapel.setText("Kimia");
            toolbaHasilSoal.setTitle("Kimia");
        } else if (kodeSoal == 4)
        {
//            textMapel.setText("Biologi");
            toolbaHasilSoal.setTitle("Biologi");
        } else if (kodeSoal == 5)
        {
//            textMapel.setText("Matematika IPS");
            toolbaHasilSoal.setTitle("Matematika IPS");
        } else if (kodeSoal == 6)
        {
//            textMapel.setText("Geografi");
            toolbaHasilSoal.setTitle("Geografi");
        } else if (kodeSoal == 7)
        {
//            textMapel.setText("Ekonomi");
            toolbaHasilSoal.setTitle("Ekonomi");
        } else if (kodeSoal == 8)
        {
//            textMapel.setText("Sosiologi");
            toolbaHasilSoal.setTitle("Sosiologi");
        } else if(kodeSoal == 9)
        {
//            textMapel.setText("Sejarah");
            toolbaHasilSoal.setTitle("Sejarah");
        }

        textBenar.setText("Jumlah Soal Benar = " + soalBenar);
        textSalah.setText("Jumlah Soal Salah = " + soalSalah);
        textSkor.setText("Total Skor = " + ((soalBenar*4) - (soalSalah*1)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EvaluasiActivity.this,MainActivity.class));
        finish();
    }
}
