package hr.foi.air.trucktrack.Adapters;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import entities.JobModel;
import entities.RouteModel;
import hr.foi.air.trucktrack.R;
import hr.foi.air.trucktrack.ViewHolders.ChildViewHolder;
import hr.foi.air.trucktrack.ViewHolders.ParentViewHolder;

/**
 * Created by Ivan on 1.12.2017..
 */

public class DriverJobsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Object> dataOfTheList = null;
    String mTipPrikaza = "";

    public DriverJobsAdapter(ArrayList<Object> data, String tip) {
        dataOfTheList = data;
        mTipPrikaza = tip;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View v;
            if (mTipPrikaza == "Vozac") {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_driver_job, parent, false);
            }
            else {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_disponent_job, parent, false);
            }
            return new ParentViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_driver_job, parent, false);
            return new ChildViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final View parent = holder.itemView;

        if (holder instanceof ParentViewHolder) {
            String datum1 = ((RouteModel) dataOfTheList.get(position)).getUtovarDatum().toString();
            String datum2 = ((RouteModel) dataOfTheList.get(position)).getIstovarDatum().toString();
            ((TextView) parent.findViewById(R.id.datumi)).setText(datum1 + " - " + datum2);
            ((TextView) parent.findViewById(R.id.utovar)).setText(((RouteModel) dataOfTheList.get(position)).getMjestoUtovara().toString());
            ((TextView) parent.findViewById(R.id.istovar)).setText(((RouteModel) dataOfTheList.get(position)).getMjestoIstovara().toString());
            ((TextView) parent.findViewById(R.id.status)).setText(((RouteModel) dataOfTheList.get(position)).getStatus().toString());

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<JobModel> jobs = ((RouteModel) dataOfTheList.get(position)).getPoslovi();
                    if(parent.isActivated()) {
                        removeFrom(position, jobs.size());
                    } else {
                        addInFrontOf(position, jobs);
                    }
                    parent.setActivated(!parent.isActivated());
                    notifyDataSetChanged();
                }
            });
        } else {
            ((TextView) parent.findViewById(R.id.poslovi)).setText(((JobModel) dataOfTheList.get(position)).getMjestoIstovara());
        }
    }

    @Override
    public int getItemCount() {
        if (dataOfTheList != null)
            return dataOfTheList.size();
        else return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataOfTheList.get(position) instanceof RouteModel) return 1;
        else return 0;
    }

    public void addInFrontOf(int position, List<JobModel> jobs){
        for(int i = 0; i < jobs.size(); i++)
            dataOfTheList.add(position+i+1, jobs.get(i));
    }

    public void removeFrom(int position, int numOfJobs){
        for(int i = 0; i < numOfJobs; i++)
            dataOfTheList.remove(position+1);
    }
}

