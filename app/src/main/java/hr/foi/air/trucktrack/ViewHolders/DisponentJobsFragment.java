package hr.foi.air.trucktrack.ViewHolders;

import android.content.Context;
import android.content.Intent;
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
import hr.foi.air.trucktrack.Interface.ClickedOnMap;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.trucktrack.MapsJobDriver;
import hr.foi.air.trucktrack.R;

/**
 * Created by Ivan on 7.12.2017..
 */

public class DisponentJobsFragment extends Fragment implements ClickedOnMap{
    RecyclerView mRecyclerView;
    static DisponentJobsFragment instance = null;
    static List<RouteModel> data = null;
    final int OPEN_MAP = 3004;
    CustomDialog customDialog;

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
        DriverJobsAdapter adapter = new DriverJobsAdapter(data,"Disponent", instance, customDialog);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void ClickedOnMap(int id) {
        Intent i = new Intent(getContext(), MapsJobDriver.class);
        i.putExtra("JOB_ID", id);
        startActivityForResult(i, OPEN_MAP);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            customDialog = ((CustomDialog) context);
        } catch (ClassCastException e) {
            throw  new ClassCastException(e.toString());
        }
    }
}
