package hr.foi.air.trucktrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.RouteModel;
import hr.foi.air.trucktrack.Adapters.DriverJobsAdapter;
import hr.foi.air.trucktrack.Interface.CustomDialog;

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
        DriverJobsAdapter adapter = new DriverJobsAdapter(data,"Vozac", instance, customDialog);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void clickedOnMap(int id) {
        Intent i = new Intent(getContext(), MapsJobDriver.class);
        i.putExtra("JOB_ID", id);
        startActivityForResult(i, OPEN_MAP);
    }


    public void setDialogAndSendEmailToDisponent(int id) {
        //send to backend that job is done
        final AlertDialog.Builder dialog= new AlertDialog.Builder(getContext());
        dialog.setTitle("Posao odrađen ?");
        dialog.setNegativeButton("Odustani", null);

        dialog.setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "POTVRDA POSLA");
                intent.putExtra(Intent.EXTRA_TEXT, "Posao je odrađen od strane vozača Pero Perić");
                intent.setData(Uri.parse("mailto:stellyrepsolka@gmail.com")); // or just "mailto:" for blank
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
                startActivity(intent);
            }
        });
        dialog.show();
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
