package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.ArrayList;

import entities.DriverJobsResponse;
import entities.RouteModel;
import hr.foi.air.trucktrack.DisponentJobsFragment;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.trucktrack.MapsJobDriver;
import hr.foi.air.trucktrack.Singleton.JobCoordinates;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ssajcic on 22.01.18..
 */

public class CallbackDriverJobsForMap extends FragmentManager implements Callback<ArrayList<RouteModel>> {
    DriverJobsResponse djResponse;
    ArrayList<RouteModel> listOfRoutes = new ArrayList<RouteModel>();
    RouteModel mRouteModel;
    JobCoordinates jobCoordinates;
    MapsJobDriver dpFragment;

 /*   public CallbackDriverJobsForMap (Object curr, MapsJobDriver next) {
        if (curr instanceof FragmentActivity) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }
        mCurrent = next;
        dpFragment = next;
        showFragment();
    }*/

    @Override
    public void onResponse(Call<ArrayList<RouteModel>> call, Response<ArrayList<RouteModel>> response) {
        listOfRoutes = response.body();
        jobCoordinates = new JobCoordinates();
        jobCoordinates.setRute(listOfRoutes);
    }

    public void onFailure(Call<ArrayList<RouteModel>> call, Throwable t) {
        Log.d("ERROR", t.toString());
    }

    public interface DataFinished {
        void dataFinished();
    }
}
