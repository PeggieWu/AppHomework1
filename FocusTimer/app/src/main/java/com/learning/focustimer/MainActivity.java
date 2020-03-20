package com.learning.focustimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText inputet;
    private Button getTime,startTime,stopTime;
    private TextView time;
    private int i=0;
    private Timer timer = null;
    private TimerTask task = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private  void initView(){
        inputet = (EditText) findViewById(R.id.inputtime);
        getTime = findViewById(R.id.gettime);
        startTime = (Button) findViewById(R.id.starttime);
        stopTime = (Button) findViewById(R.id.stoptime);
        time = (TextView) findViewById(R.id.time);
        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gettime:
                time.setText(inputet.getText().toString());
                i = Integer.parseInt(inputet.getText().toString());
                Log.d("MainActivity","gettime");
                break;

            case R.id.starttime:
                startTime();
                break;
            case R.id.stoptime:
                stopTime();
                break;
        }
    }

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg){
            time.setText(msg.arg1+"");
            startTime();
        };
    };

    public void startTime(){

          timer = new Timer();
          task = new TimerTask(){

              @Override
              public void run(){

                  if(i>0){
                      i--;
                      Log.d("MainActivity","runtime");}
                  else
                      stopTime();
                  Message  message = mHandler.obtainMessage();
                  message.arg1 = i;
                  mHandler.sendMessage(message);
              }
          };
          timer.schedule(task,1000);
    }

    public void stopTime(){
        Log.d("MainActivity","stoptime");
        timer.cancel();
    }
}
