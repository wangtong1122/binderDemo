package com.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class RemoteServiceUseAidl extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IShowMessageAidlInterface.Stub() {
            @Override
            public void showMessage(String msg) throws RemoteException {
                if (ServerMainActivity.tvShowMessage != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            ServerMainActivity.tvShowMessage.setText(msg);
                        }
                    });
                }
            }
        };
    }
}
