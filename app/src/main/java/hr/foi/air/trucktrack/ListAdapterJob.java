package hr.foi.air.trucktrack;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
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

        v.findViewById(R.id.btnEndMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NewJobFragment.ClickedOnMap) act).ClickedOnMap("", "");
                lastClicked = position;
            }
        });

        if(data.size() > 0) {
            ((EditText) v.findViewById(R.id.input_kordinateIstovara)).setText(data.get(position).getLatitude().toString()+","+data.get(position).getLongitude().toString());
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
}
