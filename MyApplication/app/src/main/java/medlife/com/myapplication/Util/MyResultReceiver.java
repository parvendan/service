package medlife.com.myapplication.Util;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import medlife.com.myapplication.interfaces.Receiver;

/**
 * Created by parvendan on 10/04/18.
 */

public class MyResultReceiver extends ResultReceiver {

    public Receiver receiver;

    public void setResultReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public MyResultReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceive(resultCode, resultData);
        }
        super.onReceiveResult(resultCode, resultData);
    }
}
