package hr.foi.air.trucktrack;

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

import java.util.ArrayList;

import entities.JobModel;

/**
 * Created by ssajcic on 20.01.18..
 */

public class ListAdapterJob extends ArrayAdapter {
    private ArrayList<JobModel> data = null;
    private ArrayList<View> listBlocks = null;

    public ListAdapterJob(@NonNull Context context, @LayoutRes int resource, ArrayList<JobModel> jobBlocks) {
        super(context, resource);
        data = jobBlocks;
        listBlocks = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View v = convertView;


        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());

        if (getItemViewType(position) == 1) {
            v = vi.inflate(R.layout.job_block_header_sublist, null);
            v.findViewById(R.id.btnAddJob).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.add(new JobModel("", ""));
                    notifyDataSetChanged();
                }
            });
        } else {
            v = vi.inflate(R.layout.job_block_in_sublist, null);
        }

        listBlocks.add(v);
        return v;
    }

    @Override
    public int getCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 1;
        else return 0;
    }
}
