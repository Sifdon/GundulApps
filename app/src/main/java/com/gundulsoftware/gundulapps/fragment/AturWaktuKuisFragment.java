package com.gundulsoftware.gundulapps.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gundulsoftware.gundulapps.AlarmReceiver;
import com.gundulsoftware.gundulapps.MyConstants;
import com.gundulsoftware.gundulapps.R;

import java.util.Calendar;


public class AturWaktuKuisFragment extends Fragment{

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
  //  TextView textView1;
//    private static TextView textView2;

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private FloatingActionButton floatBtn;
    private FloatingActionButton floatBtn2;
    private TextView textView;
    private TextView textView2;


    private Button btnTab2;
//
//    public AturWaktuKuisFragment() {
//        // Required empty public constructor
//
//    }

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_atur_waktu_kuis, container, false);

        floatBtn = (FloatingActionButton) view.findViewById(R.id.floatbutton);
        floatBtn2 = (FloatingActionButton)view.findViewById(R.id.floatbuttonCancel);
        textView = (TextView)view.findViewById(R.id.alarmEmpty);
        textView2 = (TextView)view.findViewById(R.id.alarmPlace);

//        btnTab2 = (Button) view.findViewById(R.id.btnTab2);
    //    textView1 = (TextView)view.findViewById(R.id.msg1);
    //    textView1.setText(timeHour + ":" + timeMinute);
      //  textView2 = (TextView)view.findViewById(R.id.msg2);

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

        OnClickListener listener1 = new OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstants.HOUR, timeHour);
                bundle.putInt(MyConstants.MINUTE, timeMinute);
                MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, MyConstants.TIME_PICKER);
                transaction.commit();
            }
        };

        floatBtn.setOnClickListener(listener1);

        //Button btn1 = (Button)view.findViewById(R.id.button1);
        //btn1.setOnClickListener(listener1);
        OnClickListener listener2 = new OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                cancelAlarm();
            }
        };
        floatBtn2.setOnClickListener(listener2);
//        Button btn2 = (Button)view.findViewById(R.id.button2);
  //      btn2.setOnClickListener(listener2);

        return view;
    }
    //
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState){
//        super.onActivityCreated(savedInstanceState);
//        btnTab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Halo", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
    class MyHandler extends Handler {
        @Override
        public void handleMessage (Message msg){
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(MyConstants.HOUR);
            timeMinute = bundle.getInt(MyConstants.MINUTE);
            textView.setText(timeHour + ":" + timeMinute);
            setAlarm();
        }
    }

    private void setAlarm(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        textView2.setText("Mulai");

    }
    private void cancelAlarm() {
        if (alarmManager!= null) {
            alarmManager.cancel(pendingIntent);

        }
    }
}
