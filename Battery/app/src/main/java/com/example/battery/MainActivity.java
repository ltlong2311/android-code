package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import static android.os.BatteryManager.*;

import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.PowerManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BatteryInfo";
    private static final String TAG2 = "Location";
    private BroadcastReceiver mBatteryChangedReceiver;
    private TextView mTextView;
    private String network_info, all_network_info, dataBackground;
    private LocationListener listener;
    private NetworkReceiver networkReceiver;

    private static String healthCodeToString(int health) {
        switch (health) {
            case BATTERY_HEALTH_DEAD:
                return "Dead";
            case BATTERY_HEALTH_GOOD:
                return "Good";
            case BATTERY_HEALTH_OVERHEAT:
                return "Overheat";
            case BATTERY_HEALTH_OVER_VOLTAGE:
                return "Over voltage";
            case BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return "Unspecified failure";
            case BATTERY_HEALTH_UNKNOWN:
            default:
                return "Unknown";
        }
    }

    private static String pluggedCodeToString(int plugged) {
        switch (plugged) {
            case 0:
                return "Battery";
            case BATTERY_PLUGGED_AC:
                return "AC";
            case BATTERY_PLUGGED_USB:
                return "USB";
            default:
                return "Unknown";
        }
    }

    private static String statusCodeToString(int status) {
        switch (status) {
            case BATTERY_STATUS_CHARGING:
                return "Charging";
            case BATTERY_STATUS_DISCHARGING:
                return "Discharging";
            case BATTERY_STATUS_FULL:
                return "Full";
            case BATTERY_STATUS_NOT_CHARGING:
                return "Not charging";
            case BATTERY_STATUS_UNKNOWN:
            default:
                return "Unknown";
        }
    }

    private void showBatteryInfo(Intent intent) {
        if (intent != null) {
            int health = intent.getIntExtra(EXTRA_HEALTH, BATTERY_HEALTH_UNKNOWN);
            String healthString = "Health: " + healthCodeToString(health);
            Log.i(TAG, healthString);
            int level = intent.getIntExtra(EXTRA_LEVEL, 0);
            int scale = intent.getIntExtra(EXTRA_SCALE, 100);
            float percentage = (scale != 0) ? (100.f * (level / (float) scale)) : 0.0f;
            @SuppressLint("DefaultLocale") String levelString = String.format("Level: %d/%d (%.2f%%)", level, scale,
                    percentage);
            Log.i(TAG, levelString);
            int plugged = intent.getIntExtra(EXTRA_PLUGGED, 0);
            String pluggedString = "Power source: " + pluggedCodeToString(plugged);
            Log.i(TAG, pluggedString);
            boolean present = intent.getBooleanExtra(EXTRA_PRESENT, false);
            String presentString = "Present? " + (present ? "Yes" : "No");
            Log.i(TAG, presentString);
            int status = intent.getIntExtra(EXTRA_STATUS, BATTERY_STATUS_UNKNOWN);
            String statusString = "Status: " + statusCodeToString(status);
            Log.i(TAG, statusString);

            String technology = intent.getStringExtra(EXTRA_TECHNOLOGY);
            String technologyString = "Technology: " + technology;
            Log.i(TAG, technologyString);
            int temperature = intent.getIntExtra(EXTRA_STATUS, Integer.MIN_VALUE);
            String temperatureString = "Temperature: " + temperature;
            Log.i(TAG, temperatureString);

            int voltage = intent.getIntExtra(EXTRA_VOLTAGE, Integer.MIN_VALUE);
            String voltageString = "Voltage: " + voltage;
            Log.i(TAG, voltageString);

            String s = healthString + "\n";
            s += levelString + "\n";
            s += pluggedString + "\n";
            s += presentString + "\n";
            s += statusString + "\n";
            s += technologyString + "\n";
            s += temperatureString + "\n";
            s += voltageString;
            mTextView.setText(s);
            // Note: using a StringBuilder object would have been more efficient
            int id = intent.getIntExtra(EXTRA_ICON_SMALL, 0);
            setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, id);
        } else {
            String s = "No battery information";
            Log.i(TAG, s);
            mTextView.setText(s);
            setFeatureDrawable(Window.FEATURE_LEFT_ICON, null);
        }
    }

    private void showBatteryInfo() {
        // no receiver needed
        Intent intent = registerReceiver(null, new
                IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        showBatteryInfo(intent);
    }

    private void createBatteryReceiver() {
        mBatteryChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                showBatteryInfo(intent);
            }
        };
    }

    private void enableBatteryReceiver(boolean enabled) {
        PackageManager pm = getPackageManager();
        ComponentName receiverName = new ComponentName(this, BatteryReceiver.class);
        int newState;
        if (enabled) {
            newState = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        } else {
            newState = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        }
        pm.setComponentEnabledSetting(receiverName, newState,
                PackageManager.DONT_KILL_APP);
    }

    @SuppressLint("SetTextI18n")

    private void showNetworkInfoToast() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        // hiển thị kết nối đang hoạt động
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            network_info = "Active: " + info.toString();

        }
        // hiển thị toàn bộ kết nối
        NetworkInfo[] array = cm.getAllNetworkInfo();
        if (array != null) {
            String s = "All: ";
            for (NetworkInfo i : array) {
                s += i.toString() + "\n";
            }
            all_network_info = s;
        }
    }

    @SuppressLint("MissingPermission")

    private void requestLocationUpdates() {
        LocationManager lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getAllProviders();
        if (providers != null && !providers.isEmpty()) {
            listener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.i(TAG2, location.toString());
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Log.i(TAG2, provider + " location provider disabled");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Log.i(TAG2, provider + " location provider enabled");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    Log.i(TAG2, provider + " location provider status changed to " +
                            status);
                }
            };

            for (String name : providers) {
                Toast.makeText(this, "Requesting location updates on:" + name, Toast.LENGTH_LONG).show();
                lm.requestLocationUpdates(name, 0, 0, listener);
                Toast.makeText(this, providers + name, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void disableLocationListener(LocationListener listener) {
        LocationManager lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        lm.removeUpdates(listener);
    }


    private void transferData() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean backgroundDataSetting = cm.getBackgroundDataSetting(); //
        if (backgroundDataSetting) {
            // transfer data
            dataBackground = "Background Data: true";
        } else {
            // honor setting and do not transfer data
            dataBackground = "Background Data: false";
        }
        Toast.makeText(this, dataBackground, Toast.LENGTH_LONG).show();
    }


    @SuppressLint("InvalidWakeLockTag")
    private void runInWakeLock(Runnable runnable, int flags) {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(flags, "My WakeLock");
        wl.acquire();
        runnable.run();
        wl.release();
    }

    private void registerWithAccelerometer() {
        SensorManager sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors != null && !sensors.isEmpty()) {
            SensorEventListener listener = new SensorEventListener() {
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                    Log.i(TAG, "Accuracy changed to " + accuracy);
                }

                @Override
                public void onSensorChanged(SensorEvent event) {
                    /*
                     * Accelerometer: array of 3 values
                     *
                     * event.values[0] = acceleration minus Gx on the x-axis
                     * event.values[1] = acceleration minus Gy on the y-axis
                     * event.values[2] = acceleration minus Gz on the z-axis
                     */
                    Log.i(TAG, String.format("x:%.2f y:%.2f z:%.2f ", event.values[0], event.values[1], event.values[2]));
                    // do something interesting here
                }
            };
            // we simply pick the first one
            Sensor sensor = sensors.get(0);
            Log.d(TAG, "Using sensor " + sensor.getName() + " from " + sensor.getVendor());
            sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            /*
               Độ trễ (On a Galaxy Tab 10.1):
               + SENSOR_DELAY_NORMAL: 180 ms
               + SENSOR_DELAY_UI : 60 ms
               + SENSOR_DELAY_GAME : 20 ms
               + SENSOR_DELAY_FASTEST: 10 ms

               Sensor listeners cũng cần bật tắt mỗi khi không cần thông báo bằng việc:
                unregisterListener() cho SensorManager
            */
        }
    }

    private void setupAlarm(boolean cancel) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        if (cancel) {
            am.cancel(pendingIntent); // will cancel all alarms whose intent matches this one
        } else {
            long interval = DateUtils.HOUR_IN_MILLIS * 1;
            long firstInterval = DateUtils.MINUTE_IN_MILLIS * 30;
            am.setRepeating(AlarmManager.RTC_WAKEUP, firstInterval, interval, pendingIntent);
            /* 4 types of alarm:
            + ELAPSED_TIME
            + ELAPSED_TIME_WAKEUP
            + RTC
            + RTC_WAKEUP
            */
            // RTC và ELAPSED_TIME chỉ khác nhau trong cách thời gian được thể hiện
            // Báo thức RTC hoặc ELAPSED_TIME tắt khi thiết bị đang ngủ sẽ không được delivered cho đến lần tiếp theo thiết bị thức dậy,
            // Báo thức RTC_WAKEUP hoặc LAPSED_TIME_WAKEUP sẽ đánh thức thiết bị khi nó đang tắt.

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.battery);
        // layout contains TextView to show battery information
        TextView networkActive = findViewById(R.id.network_info_active);
        Button btnShowNetworkActive = findViewById(R.id.showNetworkActive);
        Button btnShowAllNetwork = findViewById(R.id.showAllNetwork);
        Button btnCheckDataBackground = findViewById(R.id.checkDataBackground);
        Button btnCheckLocation = findViewById(R.id.checkLocation);

        networkReceiver = new NetworkReceiver();

        btnShowNetworkActive.setOnClickListener(v -> {
            showNetworkInfoToast();
            Toast.makeText(this, network_info, Toast.LENGTH_LONG).show();
        });
        btnShowAllNetwork.setOnClickListener(v -> {
            showNetworkInfoToast();
            Toast.makeText(this, all_network_info, Toast.LENGTH_LONG).show();
        });
        btnCheckDataBackground.setOnClickListener(v -> {
            transferData();
        });
        btnCheckLocation.setOnClickListener(v -> {
            requestLocationUpdates();
        });
        setupAlarm(false);
        showBatteryInfo();
        registerWithAccelerometer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregistering receiver khi app không ở nền trước để tiết kiệm pin
        unregisterReceiver(mBatteryChangedReceiver);
        enableBatteryReceiver(false); // battery receiver disabled
        disableLocationListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBatteryChangedReceiver == null) {
            createBatteryReceiver();
        }
        registerReceiver(mBatteryChangedReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        enableBatteryReceiver(true); // battery receiver enabled
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocationManager lm = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        lm.removeUpdates(listener);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        unregisterReceiver(mBatteryChangedReceiver);
        mBatteryChangedReceiver = null;
    }
}