package com.alloycube.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity2 extends AppCompatActivity {

    private static DecimalFormat df2 = new DecimalFormat(".##");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);

        String result = getIntent().getStringExtra("res");
        result = df2.format(Float.parseFloat(result)) + "";
        TextView totalTextView = findViewById(R.id.totalTextView);
        totalTextView.setText("Your total is $" + result);
    }

    @OnClick(R.id.nextOrderButton)
    public void nextOnClick() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}
