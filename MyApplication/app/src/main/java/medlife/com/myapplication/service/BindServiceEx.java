package medlife.com.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import medlife.com.myapplication.interfaces.BinderInterface;

/**
 * Created by parvendan on 16/04/18.
 */

public class BindServiceEx extends Service implements BinderInterface{

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    private final IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public String getName() {
        return "Clicked!";
    }

    public class LocalBinder extends Binder {
        public BindServiceEx getService() {
            return BindServiceEx.this;
        }
    }
}
