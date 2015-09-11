package com.geekcon.maytheforces;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MobileMainActivity extends Activity {

    private String serverUrl;

    @Bind(R.id.messageTextView)
    TextView messageTextView;

    @Bind(R.id.ipEditText)
    EditText ipEditText;

    @OnClick(R.id.saveButton)
    public void saveUrl() {
        serverUrl = ipEditText.getText().toString();
        droneService.setServerUrl(serverUrl);
        Toast.makeText(this, "Saved new server", Toast.LENGTH_SHORT).show();
    }

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

        droneService = new DroneProxyService(this, ipEditText.getText().toString());
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter("wear-event"));

        appendMessage("Started..");
    }


    private void appendMessage(String text) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        messageTextView.setText(messageTextView.getText() + "\n" + simpleDateFormat.format(new Date()) + " : " + text);
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