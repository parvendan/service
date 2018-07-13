package medlife.com.myapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

/**
 * Created by parvendan on 10/04/18.
 */

public class IntentServiceEx extends IntentService {

    public IntentServiceEx() {
        super("IntentServiceEx");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ResultReceiver resultReceiver = intent.getParcelableExtra("tag");
        Bundle bundle = new Bundle();
        bundle.putString("value", "Pari");
        resultReceiver.send(1, bundle);
    }
}
