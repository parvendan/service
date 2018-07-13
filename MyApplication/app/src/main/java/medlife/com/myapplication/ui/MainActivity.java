package medlife.com.myapplication.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import medlife.com.myapplication.R;
import medlife.com.myapplication.Util.MyResultReceiver;
import medlife.com.myapplication.interfaces.Receiver;
import medlife.com.myapplication.service.BindServiceEx;
import medlife.com.myapplication.service.IntentServiceEx;

public class MainActivity extends AppCompatActivity implements Receiver, View.OnClickListener {

    private Button click_me;
    public MyResultReceiver resultReceiver;
    public BindServiceEx binderService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click_me = findViewById(R.id.click_me);
        click_me.setOnClickListener(this);

        // intent service to get the value from the service
        resultReceiver = new MyResultReceiver(new Handler());
        resultReceiver.setResultReceiver(this);
    }

    @Override
    public void onReceive(int resultCode, Bundle data) {
        if (resultCode == 1) {
            click_me.setText(data.getString("value"));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.click_me:
                bindServiceEx();
                break;
        }
    }

    private void startService() {
        Intent intent = new Intent(this, IntentServiceEx.class);
        intent.putExtra("tag", resultReceiver);
        startService(intent);

    }

    private void bindServiceEx(){
        Intent intent = new Intent(this, BindServiceEx.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    // Bind service to get the value from the service
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BindServiceEx.LocalBinder localBinder = (BindServiceEx.LocalBinder) service;
            binderService = localBinder.getService();
            click_me.setText(binderService.getName());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
