package com.sedudevelop.specialedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class ContentActivity extends AppCompatActivity {

    private DB_Law mDB_Law;
    private DB_SpecialEdu mDB_specialEdu;

    Random r;
    int domain, quizNum, size, i;

    TimerThread  mTimerThread;
    ProgressBar progressBar;
    Button btn_next;
    TextView tv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initiate();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            nextQuestion();
            }
        });
    }

    private void nextQuestion(){
        i=0;
        domain = r.nextInt(2)+0;



        switch (domain){
            case 0:
                size = mDB_Law.Content.length;
                quizNum = r.nextInt(size)+0;
                tv_content.setText(mDB_Law.Content[quizNum][1]);
                break;
            case 1:
                size = mDB_specialEdu.Content.length;
                quizNum = r.nextInt(size)+0;
                tv_content.setText(mDB_specialEdu.Content[quizNum][1]);
                break;
        }


    }

    private void initiate(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        mDB_Law = new DB_Law();
        mDB_specialEdu = new DB_SpecialEdu();
        r= new Random();

        btn_next = (Button)findViewById(R.id.btn_next);
        tv_content = (TextView)findViewById(R.id.tv_content);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(100);

        tv_content.setMovementMethod(new ScrollingMovementMethod());
        if(mTimerThread == null){
            mTimerThread = new TimerThread();
            mTimerThread.start();
        }

    }

    class TimerThread extends Thread {
        public boolean canRun=true;

        @Override
        public void run() {
            while (canRun){
                try {
                    i++;
                    progressBar.setProgress(i);
                    if(i > 99){
                        i=0;
                        nextQuestion();
                    }
                    sleep(100);
                }catch (Exception e){
                }
            }
        }
    }

}