package hr.foi.air.trucktrack;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;
import entities.RouteModel;
import hr.foi.air.drivermodule.DriversAdapter;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Adapters.DriverJobsAdapter;

/**
 * Created by Ivan on 1.12.2017..
 */

public class DriverJobsFragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    static DriverJobsFragment instance = null;
    static List<RouteModel> data = null;

    public static DriverJobsFragment getInstance(List<RouteModel> dataJobs) {
        if(instance == null) {
            instance = new DriverJobsFragment();
        }
        data = dataJobs;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_jobs, container, false);
        mRecyclerView = view.findViewById(R.id.rvDriverJobs);
        ArrayList<Object> arrayData = new ArrayList<Object>(data);
        showRecycleView(arrayData);
        return view;
    }


    private void showRecycleView(ArrayList<Object> data) {
        DriverJobsAdapter adapter = new DriverJobsAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
