package com.geekcon.maytheforces;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MobileMainActivity extends Activity {

    @Bind(R.id.messageTextView)
    TextView messageTextView;

    @OnClick(R.id.clearButton)
    public void clearLog() {
        messageTextView.setText("");
    }

    DroneProxyService droneService;


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String command = intent.getStringExtra("COMMAND");
                String data = intent.getStringExtra("DATA");

                appendMessage(command + " " + data);
                if (command != null) {
                    if (command.compareTo("takeoff") == 0)
                        droneService.takeoff();

                    if (command.compareTo("land") == 0)
                        droneService.land();

                    if (command.compareTo("stop") == 0)
                        droneService.stop();

                    if (command.compareTo("xyz") == 0) {
                        droneService.xyz(data);
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        droneService = new DroneProxyService(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("wear-event"));

        appendMessage("Started..");
    }


    private void appendMessage(String text) {
        messageTextView.setText(messageTextView.getText() + "\n" + new Date() + " : " + text);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appendMessage("paused WEARABLE");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}