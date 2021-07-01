package com.binder.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class RemoteService extends Service {
    private static final String DESCRIPTOR = "com.binder.server.RemoteService";
    public static final int TRANSAVTION_showMessage = IBinder.FIRST_CALL_TRANSACTION;
    public static IReceiveMessageListener receiveMessageListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServerBinder();
    }

    class ServerBinder extends Binder implements IInterface {
        public ServerBinder() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {

            switch (code) {
                case TRANSAVTION_showMessage:
                    data.enforceInterface(DESCRIPTOR);
                    String message = data.readString();
                    Log.d("ServerBinder","showMessage "+ message);
                    if (receiveMessageListener != null){
                        receiveMessageListener.updateMessage(message);
                    }
                    reply.writeNoException();
                    return true;
            }
            return super.onTransact(code, data, reply, flags);
        }

        @Override
        public IBinder asBinder() {
            return this;
        }
    }
}
