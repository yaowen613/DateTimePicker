package com.yaowen.calendarview2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class DateTimePicActivity extends AppCompatActivity implements View.OnTouchListener {
    private EditText etStart, etEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time_pic_main);
        etStart = (EditText) findViewById(R.id.etStart);
        etEnd = (EditText) findViewById(R.id.etEnd);
        etStart.setOnTouchListener(this);
        etEnd.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.content_date_time_pic, null);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            final TextView textViewForStartTime= (TextView) view.findViewById(R.id.textViewDate);
//            final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
            builder.setView(view);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), null);
            //datePicker.setSpinnersShown(false);
//            timePicker.setIs24HourView(true);
//            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
//            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
            String titleYear= String.valueOf(calendar.get(Calendar.YEAR));
            String titleMonth= String.valueOf(calendar.get(Calendar.MONTH)+1);
            String titleDay= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            String title="今天是："+titleYear+"年"+titleMonth+"月"+titleDay+"日";
            if (v.getId() == R.id.etStart) {
                textViewForStartTime.setText("选择开始日期：");
                final int inType = etStart.getInputType();
                etStart.setInputType(InputType.TYPE_NULL);
                etStart.onTouchEvent(event);
                etStart.setInputType(inType);
                etStart.setSelection(etStart.getText().length());
                builder.setTitle(title);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d年%02d月%02d日",
                                datePicker.getYear(),
                                datePicker.getMonth()+1,
                                datePicker.getDayOfMonth()));
//                        sb.append("   ");
//                        sb.append(timePicker.getCurrentHour())
//                                .append(":")
//                                .append(timePicker.getCurrentMinute());
                        etStart.setText(sb);
//                        etEnd.requestFocus();
                        dialog.cancel();
                    }
                });
            }

            else if (v.getId() == R.id.etEnd) {
                textViewForStartTime.setText("选择结束日期：");
                int inType = etEnd.getInputType();
                etEnd.setInputType(InputType.TYPE_NULL);
                etEnd.onTouchEvent(event);
                etEnd.setInputType(inType);
                etEnd.setSelection(etEnd.getText().length());
                builder.setTitle(title);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d年%02d月%02d日",
                                datePicker.getYear(),
                                datePicker.getMonth()+1,
                                datePicker.getDayOfMonth()));
//                        sb.append("    ");
//                        sb.append(timePicker.getCurrentHour())
//                                .append(":")
//                                .append(timePicker.getCurrentMinute());
                        etEnd.setText(sb);
                        dialog.cancel();
                    }
                });
            }
            Dialog dialog = builder.create();
            dialog.show();
        }
        return true;
    }
}
