package hr.foi.air.trucktrack.ViewHolders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.RouteModel;
import hr.foi.air.trucktrack.Adapters.DriverJobsAdapter;
import hr.foi.air.trucktrack.R;

/**
 * Created by Ivan on 7.12.2017..
 */

public class DisponentJobsFragment extends Fragment {
    RecyclerView mRecyclerView;
    static DisponentJobsFragment instance = null;
    static List<RouteModel> data = null;

    public static DisponentJobsFragment getInstance(List<RouteModel> dataJobs) {
        if(instance == null) {
            instance = new DisponentJobsFragment();
        }
        data = dataJobs;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disponent_jobs, container, false);
        mRecyclerView = view.findViewById(R.id.rvDriverJobs);
        ArrayList<Object> arrayData = new ArrayList<Object>(data);
        showRecycleView(arrayData);
        return view;
    }

    private void showRecycleView(ArrayList<Object> data) {
        DriverJobsAdapter adapter = new DriverJobsAdapter(data,"Disponent");
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
