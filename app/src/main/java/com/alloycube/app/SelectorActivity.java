package com.alloycube.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.company1)
    public void company1() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.company2)
    public void company2() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }
}
