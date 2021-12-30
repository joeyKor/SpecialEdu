package com.sedudevelop.specialedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Advise mAdvise;
    Random r;

    TextView tv_advise;
    Button btn_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAdvise = new Advise();
        r= new Random();

        int adviseNum = r.nextInt(mAdvise.Advise.length);

        tv_advise =(TextView)findViewById(R.id.tv_advise);
    btn_content = (Button)findViewById(R.id.btn_content);


    tv_advise.setText(mAdvise.Advise[adviseNum][1]+"\n\n"+"- "+ mAdvise.Advise[adviseNum][0]+" -");

    btn_content.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        Intent i = new Intent(MainActivity.this, ContentActivity.class);
        startActivity(i);

        }
    });
    }



}