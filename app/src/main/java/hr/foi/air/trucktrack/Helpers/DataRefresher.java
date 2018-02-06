package hr.foi.air.trucktrack.Helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;
import entities.RouteModel;
import entities.SortRequest;
import hr.foi.air.drivermodule.DriversUpdateListener;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackAllRoutes;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobs;
import hr.foi.air.trucktrack.Callbacks.CallbackDriversSort;
import hr.foi.air.trucktrack.DisponentJobsFragment;
import hr.foi.air.trucktrack.DriverJobsFragment;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

import static hr.foi.air.trucktrack.R.id.activity_chooser_view_content;
import static hr.foi.air.trucktrack.R.id.input_vozac;

/**
 * Created by Ivan on 20.1.2018..
 */

public class DataRefresher {
    private ApiInterface apiService;

    public DataRefresher() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    public void osvjeziPosloveVozaca(Activity activity, DriverJobsFragment fragment) {
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("3"); //ovdje ide id korisnika, za testiranje uzet id 3
        call.enqueue(new CallbackDriverJobs(activity,fragment));
    }

    public void osvjeziPosloveDisponenta(Activity context, DisponentJobsFragment fragment) {
        Call<ArrayList<RouteModel>> call = apiService.getAllRoutes();
        call.enqueue(new CallbackAllRoutes(context,fragment));
    }

    public void osvjeziPopisVozacaSortiran(Activity context, Fragment fragment, SortRequest sortRequest) {
        Call<List<DriverModel>> call = apiService.driverSort(sortRequest);
        call.enqueue(new CallbackDriversSort(context,fragment));
    }
}
