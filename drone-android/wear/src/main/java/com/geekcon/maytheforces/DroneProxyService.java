package com.geekcon.maytheforces;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Pavel 'PK' Kaminsky on 09/2015.
 */
public class DroneProxyService {
    public static final int CONNECTION_TIME_OUT_MS = 1000 * 5;

    private GoogleApiClient mGoogleApiClient;
    private String nodeId;

    public DroneProxyService(Context ctx) {

        mGoogleApiClient = new GoogleApiClient.Builder(ctx)
                .addApi(Wearable.API)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mGoogleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                NodeApi.GetConnectedNodesResult result =
                        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).await();
                List<Node> nodes = result.getNodes();

                if (nodes.size() > 0) {
                    nodeId = nodes.get(0).getId();
                }
                mGoogleApiClient.disconnect();
            }
        }).start();
    }

    public void message(final String command) {
        this.message(command, "");
    }

    public void message(final String command, final String data) {
        if (nodeId != null) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    mGoogleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
                    Wearable.MessageApi.sendMessage(mGoogleApiClient, nodeId, command, data.getBytes());
                    mGoogleApiClient.disconnect();
                }
            }).start();
        }
    }
}
