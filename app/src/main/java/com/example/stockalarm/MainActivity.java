package com.example.stockalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private Intent intent;
    private EditText editText;
    private Button removeBtn;
    private PendingIntent alarmIntent;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        intent = new Intent(context, StockService.class);
        editText = findViewById(R.id.editText);
        removeBtn = findViewById(R.id.removeBtn);
    }

    public void onClick(View view){
        String stockNo = editText.getText().toString();
        if(stockNo == null || stockNo.trim().length() == 0){
            stockNo = "2330";
        }
        intent.putExtra("stockNo", stockNo);
        startService(intent);

        switch(view.getId()){
            case R.id.setupBtn:
                alarmIntent = PendingIntent.getService(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                calendar = Calendar.getInstance();
                TimePickerDialog dialog = new TimePickerDialog(context, new MyTimeSetListener(), calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);
                dialog.show();
                break;
            case R.id.removeBtn:
                AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
                manager.cancel(alarmIntent);
                Toast.makeText(context, "移除鬧鐘", Toast.LENGTH_SHORT).show();
                removeBtn.setEnabled(false);
                break;

        }
    }

    private class MyTimeSetListener implements TimePickerDialog.OnTimeSetListener{
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            //以set()設定的 PendingIntent 只會執行一次
            AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
            manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
            Toast.makeText(context, "設定鬧鐘時間為"+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
            removeBtn.setEnabled(true);
        }
    }
}