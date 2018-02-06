package hr.foi.air.drivermodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;

/**
 * Created by Ivan on 28.10.2017..
 */

public class GridViewFragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    static GridViewFragment instance = null;
    static List<DriverModel> data = null;
    private DriverSelectFromListInterface interfaceSelectedDriver;
    static DriversAdapter adapter;

    public static GridViewFragment getInstance(List<DriverModel> dataDrivers) {
        if(instance == null) {
            instance = new GridViewFragment();
        }
        data = dataDrivers;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv, container, false);
        mRecyclerView = view.findViewById(R.id.rvDrivers);
        showRecycleView(data);

        return view;
    }

    private void showRecycleView(List<DriverModel> data) {
        adapter = new DriversAdapter(getContext(),data, 0, interfaceSelectedDriver);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            interfaceSelectedDriver = (DriverSelectFromListInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ToolbarListener");
        }
    }

    public void updateAdapter(List<DriverModel> dataJobs) {
        data = dataJobs;
        adapter = new DriversAdapter(getContext(), data, 0, interfaceSelectedDriver);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();
    }
}
