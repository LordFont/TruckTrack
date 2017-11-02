package hr.foi.air.drivermodule;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;

/**
 * Created by roman on 10/31/17.
 */

public class DriversListViewFragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> a;
    List<DriverModel> mListDrivers;

    public DriversListViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv, container, false);
        // Initialize contacts
        a = new ArrayList<>();
        a.add("Ivan");
        a.add("Marko");
        a.add("Petar");
        a.add("Mislav");
        a.add("Matija");
        a.add("Svemirko");

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvDrivers);
        Log.d("debugMode", "The application stopped after this");
        DriversAdapter adapter = new DriversAdapter(getContext(),a, 1);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // That's all!
        return view;
    }

}
