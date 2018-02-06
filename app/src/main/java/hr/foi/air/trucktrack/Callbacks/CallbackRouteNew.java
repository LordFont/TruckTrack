package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

import entities.RouteModel;
import hr.foi.air.trucktrack.DisponentHome;
import hr.foi.air.trucktrack.DisponentJobs;
import hr.foi.air.trucktrack.DisponentJobsFragment;
import hr.foi.air.trucktrack.DriverJobs;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.trucktrack.R;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivan on 21.1.2018..
 */

public class CallbackRouteNew extends FragmentManager implements Callback<RouteModel> {
    RouteModel mRouteModel = new RouteModel();

    public CallbackRouteNew (Object curr, RouteModel routeModel) {
        if (curr instanceof Fragment) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }
        mRouteModel = routeModel;
    }

    @Override
    public void onResponse(Call<RouteModel> call, Response<RouteModel> response) {
        Log.d("NEW ROUTE RESPONSE", String.valueOf(response.code()));
        if (response.code() == 200) {
            //ovdje se obavljaju radnje kada su uspjesno poslani podaci
            mCurrent.startActivity(new Intent(mCurrent.getApplicationContext(), DisponentJobs.class));
        } else {
            //ovdje se obavljaju radnje kada nisu uspjesno poslani podaci
        }
    }

    public void onFailure(Call<RouteModel> call, Throwable t) {
        Log.d("JakoCoolGreska", t.toString());
    }
}
