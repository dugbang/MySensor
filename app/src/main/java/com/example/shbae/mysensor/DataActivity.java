package com.example.shbae.mysensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class DataActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;

    private SensorManager manager;
    private List<Sensor> sensors;
    private String sensorName;
    private int sensorIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensors = manager.getSensorList(Sensor.TYPE_ALL);

        Intent passedIntent = getIntent();
        processCommand(passedIntent);
    }

    private void processCommand(Intent intent) {
        if (intent != null) {
            sensorIndex = intent.getIntExtra("SensorIndex", 0);
            sensorName = intent.getStringExtra("SensorName");
            textView.setText(sensorName);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        manager.registerListener(this, sensors.get(sensorIndex), SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String timestamp = "Sensor Timestamp -> " + sensorEvent.timestamp;
        textView2.setText(timestamp);

        String sensorValue = sensorEvent.values[0] + ", " + sensorEvent.values[1] + ", " +sensorEvent.values[2];
        textView3.setText(sensorValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
