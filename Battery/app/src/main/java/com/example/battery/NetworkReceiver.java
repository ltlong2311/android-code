package com.example.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

import java.util.ArrayList;

public class NetworkReceiver extends BroadcastReceiver {
    private static String conn="android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (isNetworkAvailable(context)) {
                Toast.makeText(context, "Có kết nối Internet!", Toast.LENGTH_LONG).show();
                ArrayList aList = new ArrayList();
                for (int i=0; i<10000;i++){
                    aList.add(i);
                }
                reverse(aList);
            } else {
                Toast.makeText(context, "Không có kết nối Internet!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = connectivityManager.getActiveNetwork();
            if (network == null) {
                return false;
            }
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
    public ArrayList<Object> reverse(ArrayList<Object> list) {
        if(list.size() > 1) {
            Object value = list.remove(0);
            reverse(list);
            list.add(value);
        }
        return list;
    }


}
