package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 2/6/18.
 */

public class CallbackDriversSort extends FragmentManager implements Callback<List<DriverModel>> {
    List<DriverModel> drivers;
    private String sort_by, field;

    public CallbackDriversSort(Object curr, Fragment next) {
        if (curr instanceof Fragment) {
            mCurrent = ((Fragment) curr).getActivity();
        }
        if (curr instanceof Activity) {
            mCurrent = (FragmentActivity)curr;
        }

        mNnext = next;
    }


    @Override
    public void onResponse(Call<List<DriverModel>> call, Response<List<DriverModel>> response) {
        drivers = response.body();
        if (mNnext instanceof ListViewFragment) mNnext = ListViewFragment.getInstance(drivers);
        else mNnext = GridViewFragment.getInstance(drivers);
        showFragment();
    }

    @Override
    public void onFailure(Call<List<DriverModel>> call, Throwable t) {
        Log.d("Error", t.toString());
    }
}
