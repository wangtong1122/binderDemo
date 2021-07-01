package com.binder.server;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServerMainActivity extends AppCompatActivity {
   public static TextView tvShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShowMessage = findViewById(R.id.tv_receive_string);
    }
}
