package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewJobFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Fragment fragment;
    private ApiInterface apiService;
    static NewJobFragment instance = null;
    ImageView addDriver;
    private List<DriverModel> drivers = null;

    public static NewJobFragment getInstance(){
        if(instance == null){
            instance = new NewJobFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job, container, false);
        addDriver = (ImageView) view.findViewById(R.id.addDriverIcon);
        addDriver.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        //Fragment nextFrag= new Fragment();
        fragment = ListViewFragment.getInstance(drivers);
        getDrivers();
    }
//sljedeca metoda(getDrivers) nesmije biti ovdje!
    private void getDrivers() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new Callback<List<DriverModel>>() {
            @Override
            public void onResponse(Call<List<DriverModel>> call, Response<List<DriverModel>> response) {
                drivers = response.body();
                if(fragment instanceof ListViewFragment) fragment = ListViewFragment.getInstance(drivers);
                else fragment = GridViewFragment.getInstance(drivers);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, fragment, fragment.getClass().getName())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onFailure(Call<List<DriverModel>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }


}




