package hr.foi.air.trucktrack;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;

import hr.foi.air.drivermodule.GridViewFragment;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.trucktrack.Interface.InterfaceToolbarChange;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;


public class NewJobFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    static NewJobFragment instance = null;
    Fragment fragment;
    ImageView addDriver, addStart, addEnd;
    EditText inputStart, inputEnd, datumUtovara, datumIstovara;
    private ApiInterface apiService;
    private List<DriverModel> drivers = null;

    public static NewJobFragment getInstance() {
        if (instance == null) {
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

        inputStart = view.findViewById(R.id.input_kordinateUtovara);
        inputEnd = view.findViewById(R.id.input_kordinateIstovara);

        addStart = view.findViewById(R.id.btnStartMap);
        addStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClickedOnMap) getActivity()).ClickedOnMap(inputStart.getText().toString(), inputEnd.getText().toString());
            }
        });

        addEnd = view.findViewById(R.id.btnStartMap);
        addEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClickedOnMap) getActivity()).ClickedOnMap(inputStart.getText().toString(), inputEnd.getText().toString());
            }
        });

        datumUtovara = view.findViewById(R.id.input_datumUtovaraa);
        datumIstovara = view.findViewById(R.id.input_datumIstovara);

        datumUtovara.setInputType(0);
        datumIstovara.setInputType(0);

        datumUtovara.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) ((CalendarClicked) getActivity()).calendarClicked(view);

            }
        });


        datumIstovara.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) ((CalendarClicked) getActivity()).calendarClicked(view);

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        //Fragment nextFrag= new Fragment();
        fragment = ListViewFragment.getInstance(drivers);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new CallbackDriverList(this, fragment));
    }


    public interface ClickedOnMap {
        void ClickedOnMap(String coordinatesStart, String coordinatesEnd);
    }

    public interface CalendarClicked {
        void calendarClicked(View input);
    }
}
