package hr.foi.air.trucktrack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;

import entities.JobModel;

/**
 * Created by ssajcic on 20.01.18..
 */

public class ListAdapterJob extends ArrayAdapter {
    private ArrayList<JobModel> data = null;
    private ArrayList<View> listBlocks = null;
    Activity act;
    public int lastClicked = -1;
    EditText koordinateIstovara = null, datum_istovara = null, poduzece_utovara = null;

    public ListAdapterJob(@NonNull Context context, @LayoutRes int resource, ArrayList<JobModel> jobBlocks, Activity activity) {
        super(context, resource);
        data = jobBlocks;
        listBlocks = new ArrayList<>();
        act = activity;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.job_block_in_sublist, null, false);
        }

        koordinateIstovara = v.findViewById(R.id.input_kordinateIstovara);
        datum_istovara = v.findViewById(R.id.input_datumIstovara);
        poduzece_utovara = v.findViewById(R.id.input_istovar);

        if(data.size() > 0) {
            koordinateIstovara.setText(data.get(position).getLatitude().toString() + "," + data.get(position).getLongitude().toString());
            datum_istovara.setText(data.get(position).getIstovarDatum());
            koordinateIstovara.setText(data.get(position).getLatitude()+","+data.get(position).getLongitude());

            v.findViewById(R.id.btnEndMap).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    poduzece_utovara.clearFocus();
                    ((NewJobFragment.ClickedOnMap) act).ClickedOnMap(koordinateIstovara.getText().toString());
                    lastClicked = position;
                }
            });

            datum_istovara.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                   ((NewJobFragment.CalendarClicked) act).calendarClicked(datum_istovara);
                }
            });

            datum_istovara.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    data.get(position).setIstovarDatum(s.toString());
                }
            });

            poduzece_utovara.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    data.get(position).setMjestoIstovara(s.toString());
                }
            });

        }
        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 1;
        else return 0;
    }

    public void getData() {

    }
}
