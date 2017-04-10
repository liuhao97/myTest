package com.bawei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        final CircleProgress circleProgress = (CircleProgress)findViewById(R.id.circle_progress);
        Button start = (Button)findViewById(R.id.start);
        Button reset = (Button)findViewById(R.id.reset);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgress.start();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgress.reset();
            }
        });
    }
}
