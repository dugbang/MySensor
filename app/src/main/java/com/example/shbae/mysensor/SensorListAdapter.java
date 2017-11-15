package com.example.shbae.mysensor;

import android.content.Context;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shbae on 2017-11-15.
 */

class SensorListAdapter extends ArrayAdapter<Sensor> {

    private Context context;
    private final List<Sensor> items;

    public SensorListAdapter(@NonNull Context context, int resource, @NonNull List<Sensor> objects) {
        super(context, resource, objects);

        this.context = context;
        items = objects;
    }

    public int getCount() {
        return (items != null) ? items.size() : 0;
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sensor_item, null);
        } else {
            view = convertView;
        }

        Sensor sensor = items.get(position);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        TextView textView3 = (TextView) view.findViewById(R.id.textView3);

        textView.setText("센서명; " + sensor.getName());
        textView2.setText("제조사; " + sensor.getVendor());
        textView3.setText("버전; " + sensor.getVersion());

        return view;
    }
}
