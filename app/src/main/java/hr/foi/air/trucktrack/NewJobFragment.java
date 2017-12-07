package hr.foi.air.trucktrack;

import android.view.View;

import entities.NewJobRequest;
import hr.foi.air.webservice.ApiInterface;
import android.support.annotation.Nullable;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.trucktrack.Interface.InterfaceToolbarChange;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;


public class NewJobFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Fragment fragment;
    private ApiInterface apiService;
    static NewJobFragment instance = null;
    ImageView addDriver;
    Button btnCancel, btnAccept;
    private List<DriverModel> drivers = null;

    EditText utovar, istovar, dutovar, distovar, kutovar, kistovar, vozac;

    public static NewJobFragment getInstance(){
        if(instance == null){
            instance = new NewJobFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_job, container, false);
        addDriver = (ImageView) view.findViewById(R.id.addDriverIcon);
        addDriver.setOnClickListener(this);

        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        btnAccept = (Button) view.findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(this);

        utovar = (EditText)view.findViewById(R.id.input_utovar);
        istovar = (EditText) view.findViewById(R.id.input_istovar);
        dutovar = (EditText) view.findViewById(R.id.input_datumUtovara);
        distovar = (EditText) view.findViewById(R.id.input_datumIstovara);
        kutovar = (EditText) view.findViewById(R.id.input_kordinateUtovara);
        kistovar = (EditText) view.findViewById(R.id.input_kordinateIstovara);
        vozac = (EditText) view.findViewById(R.id.input_vozac);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_accept:
                String Utovar = utovar.getText().toString();
                String Istovar = istovar.getText().toString();
                String Dutovar = dutovar.getText().toString();
                String Distovar = distovar.getText().toString();
                String Kutovar = kutovar.getText().toString();
                String Kistovar = kistovar.getText().toString();
                String Vozac = vozac.getText().toString();

                apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<Void> call2 = apiService.newJob(new NewJobRequest(Utovar, Istovar, Dutovar, Distovar, Kutovar, Kistovar, Vozac));
                break;
            case R.id.btn_cancel:
                getActivity().finish();
                break;
            case R.id.addDriverIcon:
                fragment = ListViewFragment.getInstance(drivers);
                apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<List<DriverModel>> call = apiService.getDrivers();
                call.enqueue(new CallbackDriverList(this,fragment));
                break;
        }
    }
}




