package hr.foi.air.trucktrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.RouteModel;
import hr.foi.air.trucktrack.Adapters.JobListAdapter;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobsForMap;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

/**
 * Created by Ivan on 1.12.2017..
 */

public class DriverJobsFragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    static DriverJobsFragment instance = null;
    static List<RouteModel> data = null;
    final int OPEN_MAP = 3004;
    CustomDialog customDialog;

    public static DriverJobsFragment getInstance(List<RouteModel> dataJobs) {
        if(instance == null) {
            instance = new DriverJobsFragment();
        }
        if (dataJobs == null) {
            Log.d("ISITNULL","ISNULL");
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
        JobListAdapter adapter = new JobListAdapter(data,"Vozac", instance, customDialog, null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void clickedOnMap(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("3");
        call.enqueue(new CallbackDriverJobsForMap());

        Intent i = new Intent(getContext(), MapsJobDriver.class);
        i.putExtra("EDIT_JOB", id);
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
