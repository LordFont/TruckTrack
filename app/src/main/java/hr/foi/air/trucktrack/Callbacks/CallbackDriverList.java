package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.trucktrack.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v4.app.Fragment;

import static android.R.attr.fragment;

/**
 * Created by Ivan on 4.12.2017..
 */

public class CallbackDriverList extends FragmentManager implements Callback<List<DriverModel>> {
    List<DriverModel> drivers;

    public CallbackDriverList(Object curr, Fragment next) {
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
        if(mNnext instanceof ListViewFragment) mNnext = ListViewFragment.getInstance(drivers);
        else mNnext = GridViewFragment.getInstance(drivers);

        showFragment();
    }

    public void onFailure(Call<List<DriverModel>> call, Throwable t) {
        Log.d("Error", t.toString());
    }

}
