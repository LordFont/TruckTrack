package hr.foi.air.trucktrack.Callbacks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.support.v4.app.Fragment;

import static android.R.attr.fragment;

/**
 * Created by Ivan on 4.12.2017..
 */

public class UserCallback implements Callback<List<DriverModel>> {
    FragmentTransaction mTransactiont;
    Fragment fragment;
    List<DriverModel> drivers;
    public UserCallback(Fragment frag, FragmentTransaction transactiont) {
        fragment = frag;
        mTransactiont = transactiont;
    }
    @Override
    public void onResponse(Call<List<DriverModel>> call, Response<List<DriverModel>> response) {
        drivers = response.body();
        if(fragment instanceof ListViewFragment) fragment = ListViewFragment.getInstance(drivers);
        else fragment = GridViewFragment.getInstance(drivers);

        showFragment(fragment);
    }

    public void onFailure(Call<List<DriverModel>> call, Throwable t) {
        Log.d("Error", t.toString());
    }

    private void showFragment(Fragment f) {
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }
}
