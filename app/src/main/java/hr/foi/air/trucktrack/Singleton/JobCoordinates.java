package hr.foi.air.trucktrack.Singleton;

import java.util.ArrayList;
import java.util.List;

import entities.JobModel;
import entities.RouteModel;
import okhttp3.Route;

/**
 * Created by ssajcic on 22.01.18..
 */

public class JobCoordinates {
    private static JobCoordinates instance = null;
    private ArrayList<RouteModel> rute = new ArrayList<>();
    private ArrayList<JobModel> jobs = new ArrayList<>();

    public static JobCoordinates JobCoordinates() {
        if(instance != null) instance = new JobCoordinates();

        return instance;
    }

    public void setRute(ArrayList<RouteModel> rute) {
        this.rute = rute;
    }

    public ArrayList<JobModel> getJobs(int position) {
        jobs = rute.get(position).getPoslovi();
        return jobs;
    }
}
