package com.binder.server;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements IReceiveMessageListener {
    TextView tvShowMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RemoteService.receiveMessageListener = this;
        tvShowMessage = findViewById(R.id.tv_receive_string);
    }

    @Override
    public void updateMessage(String msg) {
        tvShowMessage.setText(msg);
    }


}
