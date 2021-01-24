package com.sihun.timermake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txt_time, txt_round;
    Button btn_start, btn_reset, btn_stop;


    int count = 0;
    int min = 0;
    int hour = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xml파일의 id검색
        txt_time = findViewById(R.id.txt_time);
        txt_round = findViewById(R.id.txt_round);


        btn_start = findViewById(R.id.btn_start);
        btn_reset = findViewById(R.id.btn_reset);
        btn_stop = findViewById(R.id.btn_stop);


        //버튼 감지자 설정
        btn_reset.setOnClickListener(click);
        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);

    }//oncreate

    //버튼 클릭 감지자
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    handler.sendEmptyMessage(0);
                    btn_start.setVisibility(View.GONE);
                    btn_stop.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_reset:
                    handler.removeMessages(0);
                    count = 0;
                    int sec = 0;
                    int min = 0;
                    int hour = 0;
                    String result = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
                    txt_time.setText(result);
                    btn_stop.setVisibility(View.GONE);
                    btn_start.setVisibility(View.VISIBLE);
                    break;

                case R.id.btn_stop:
                    handler.removeMessages(0);
                    btn_stop.setVisibility(View.GONE);
                    btn_start.setVisibility(View.VISIBLE);
                    break;
            }//switch
        }
    };//OnClickListener

    Handler handler = new Handler(){

        @Override
        public void handleMessage( Message msg) {
            //1초에 한번씩 자신을 반복 수행
            handler.sendEmptyMessageDelayed(0, 1000);

            count++;

            int sec = count;

            if (count > 58){

                count = 0;
                min++;
            };
            if (min > 58){
                count = 0;
                min = 0;
                hour++;
            };

            String result = String.format("운동 \n%02d : %02d : %02d", hour, min, sec);
            txt_time.setText(result);
        }
    };



}