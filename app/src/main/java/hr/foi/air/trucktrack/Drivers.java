package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.List;

import entities.DriverModel;
import entities.SortRequest;
import hr.foi.air.trucktrack.Callbacks.CallbackDriversSort;
import hr.foi.air.trucktrack.Helpers.DataRefresher;
import hr.foi.air.trucktrack.Helpers.DriverFragmentGridLoader;
import hr.foi.air.trucktrack.Helpers.DriverFragmentListLoader;
import hr.foi.air.drivermodule.DriverSelectFromListInterface;
import hr.foi.air.trucktrack.Helpers.DriversFragmentLoader;
import hr.foi.air.trucktrack.Interface.FragmentLoaderListener;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Drivers extends AppCompatActivity implements
        ListViewFragment.ToolbarListener,
        DriverSelectFromListInterface,
        FragmentLoaderListener {

    private List<DriverModel> drivers = null;
    private boolean isListFragment = false;
    private ApiInterface apiService;
    Fragment fragment;
    private Drivers thisInstance;
    Spinner spinnerSortBy, spinnerField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        spinnerSortBy = (Spinner) findViewById(R.id.spinner_sort_by);
        spinnerField = (Spinner) findViewById(R.id.spinner_field);

        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDriversSorted();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerField.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDriversSorted();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        thisInstance = this;
        initToolbar();
        getDrivers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drivers, menu);

        menu.findItem(R.id.viewIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            DriversFragmentLoader driversFragmentLoader = null;
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (isListFragment){
                    driversFragmentLoader = new DriverFragmentGridLoader(thisInstance);
                    isListFragment = false;
                    item.setIcon(R.drawable.ic_view_list_white_48px);
                }
                else {
                    driversFragmentLoader = new DriverFragmentListLoader(thisInstance);
                    isListFragment = true;
                    item.setIcon(R.drawable.ic_dashboard_white_48px);
                }

                driversFragmentLoader.loadFragment(drivers);
                getDrivers();

                return true;
            }
        });

        menu.findItem(R.id.refreshIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                getDrivers();
                return true;
            }
        });
        return true;
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.drivers_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarVozaci));
    }

    private void getDrivers() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new CallbackDriverList(this,fragment));
    }

    private void getDriversSorted() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        SortRequest sortRequest = new SortRequest(spinnerSortBy.getSelectedItem().toString(), spinnerField.getSelectedItem().toString());
        DataRefresher df = new DataRefresher();
        df.osvjeziPopisVozacaSortiran(this, fragment, sortRequest);
    }

    @Override
    public void onFragmentAttached(boolean change) {
        //needs to be implemented
    }

    @Override
    public void driverSelected(DriverModel driver) {

    }

    @Override
    public void provideFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
