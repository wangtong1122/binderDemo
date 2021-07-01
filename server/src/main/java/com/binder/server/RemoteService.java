package com.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class RemoteService extends Service {
    public static final int TRANSAVTION_showMessage = IBinder.FIRST_CALL_TRANSACTION;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServerBinder();
    }

    static class ServerBinder extends Binder   {
        public ServerBinder() {
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            switch (code) {
                case TRANSAVTION_showMessage:
                    String message = data.readString();
                    Log.d("ServerBinder", "showMessage " + message);
                    if (ServerMainActivity.tvShowMessage != null) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                ServerMainActivity.tvShowMessage.setText(message);
                            }
                        });
                    }
                    if (reply != null) {
                        reply.writeNoException();
                    }
                    return true;
            }
            return super.onTransact(code, data, reply, flags);
        }


    }
}
