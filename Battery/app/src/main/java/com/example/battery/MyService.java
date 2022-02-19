package com.example.battery;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent intent) {
        // trả về null vì khách hàng không thể liên kết với dịch vụ
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "Alarm went off – Service was started");
        stopSelf();  // gọi stopSelf () khi hoàn tất để giải phóng tài nguyên;
    }
}
