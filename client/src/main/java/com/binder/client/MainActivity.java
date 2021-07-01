package com.binder.client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button mSendString;
    private EditText mStingEditText;
    private static final String DESCRIPTOR = "com.binder.server.RemoteService";
    public static final int TRANSAVTION_showMessage = IBinder.FIRST_CALL_TRANSACTION;
    private IBinder mServer;//服务端的Binder对象代理
    private boolean isConnection = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isConnection = true;
            mServer = service;
            Log.d("Client"," onServiceConnected success");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnection = false;
        }
    };
    private void attemptToBindService() {
        Intent intent = new Intent();
        intent.setClassName("com.binder.server", "com.binder.server.RemoteService");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendString = findViewById(R.id.btn_send_string);
        mStingEditText = findViewById(R.id.et_string);
        mSendString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnection) {
                    attemptToBindService();
                    return;
                }

                Parcel data = Parcel.obtain();
                Parcel replay = Parcel.obtain();
                if (mServer != null) {
                    try {
                        data.writeInterfaceToken(DESCRIPTOR);
                        data.writeString(mStingEditText.getText().toString());
                        Log.d("Client"," mServer.transact call");
                        mServer.transact(TRANSAVTION_showMessage, data, replay, 0);
                        replay.readException();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } finally {
                        replay.recycle();
                        data.recycle();
                    }
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isConnection) {
            attemptToBindService();
        }
    }



}