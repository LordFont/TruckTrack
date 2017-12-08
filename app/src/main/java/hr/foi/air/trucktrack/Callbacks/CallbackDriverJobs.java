package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import entities.DriverJobsResponse;
import entities.RouteModel;
import hr.foi.air.trucktrack.DriverJobsFragment;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.duration;
import static android.media.CamcorderProfile.get;

/**
 * Created by Ivan on 7.12.2017..
 */

public class CallbackDriverJobs extends FragmentManager implements Callback<List<DriverJobsResponse>>{
    List<DriverJobsResponse> djResponse;
    ArrayList<RouteModel> listOfRoutes = new ArrayList<RouteModel>();
    RouteModel mRouteModel;

    public CallbackDriverJobs(Object curr, Fragment next) {
        if (curr instanceof Fragment) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }
        mNnext = next;
    }

    @Override
    public void onResponse(Call<List<DriverJobsResponse>> call, Response<List<DriverJobsResponse>> response) {
        mRouteModel = new RouteModel();
        djResponse = response.body();
        for (int i = 0; i < djResponse.size(); i++) {
            if (i == 0) {
                String mjestoIstovara = djResponse.get(i).getDriverRoutes().getPoslovi().get(0).getMjestoIstovara();
                String mjestoUtovara = djResponse.get(i).getDriverRoutes().getPoslovi().get(0).getMjestoUtovara();
                String istovarDatum = djResponse.get(i).getDriverRoutes().getPoslovi().get(0).getIstovarDatum();
                if (istovarDatum == null) {
                    istovarDatum = "Datum2";
                }
                String utovarDatum = "Datum1";
                mRouteModel.AddJob(mjestoUtovara,mjestoIstovara,utovarDatum,istovarDatum);
                mRouteModel.setPoslovi(djResponse.get(i).getDriverRoutes().getPoslovi());
                Log.d("sss",mjestoIstovara); //ovo ispisuje
            }
        }
        listOfRoutes.add(mRouteModel);
        mNnext = DriverJobsFragment.getInstance(listOfRoutes);

        showFragment();
    }

    public void onFailure(Call<List<DriverJobsResponse>> call, Throwable t) {
        Log.d("JakoCoolGreska", t.toString());
    }
}
