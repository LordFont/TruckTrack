package hr.foi.air.drivermodule;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ivan on 28.10.2017..
 */

public class DriversRVFragment extends android.support.v4.app.Fragment {
    RecyclerView mRecyclerView;
    ArrayList<String> a;

    public DriversRVFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rv, container, false);
        // Initialize contacts
        a = new ArrayList<>();
        a.add("Ivan");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        a.add("Pero");
        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvDrivers);
        Log.d("debugMode", "The application stopped after this");
        DriversAdapter adapter = new DriversAdapter(getContext(),a);
        // Attach the adapter to the recyclerview to populate items
        mRecyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        // That's all!
        return view;
    }
}
