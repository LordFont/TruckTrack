package hr.foi.air.trucktrack;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.trucktrack.Interface.ClickedOnMap;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

import static java.sql.DriverManager.getDrivers;


public class NewJob extends AppCompatActivity implements ListViewFragment.ToolbarListener, NewJobFragment.ClickedOnMap, NewJobFragment.CalendarClicked {

    Fragment fragment;
    private ApiInterface apiService;
    int changeImage;
    boolean iNeedToChangeToolbar = false;
    ClickedOnMap clickedOnMapInterface;
    final Integer ENTER_IN_MAP = 3003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        initToolbar();

        fragment = NewJobFragment.getInstance();
        showFragment(fragment);
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.job_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle("Posao");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_job, menu);
        menu.findItem(R.id.refreshIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (iNeedToChangeToolbar) {
            menu.clear();
            getMenuInflater().inflate(R.menu.menu_drivers, menu);
            getSupportActionBar().setTitle("Vozači");
            menu.findItem(R.id.viewIcon).setIcon(R.drawable.ic_dashboard_white_48px);

            menu.findItem(R.id.viewIcon).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (changeImage == 1) {
                        item.setIcon(R.drawable.ic_dashboard_white_48px);
                        changeImage = 0;
                        fragment = new ListViewFragment();
                        getDrivers();
                    } else {
                        item.setIcon(R.drawable.ic_view_list_white_48px);
                        changeImage = 1;
                        fragment = new GridViewFragment();
                        getDrivers();
                    }
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
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void showFragment(Fragment f) {
        FragmentTransaction myTransaction = getSupportFragmentManager().beginTransaction();
        myTransaction.replace(R.id.main_container, f, f.getClass().getName());
        myTransaction.commit();
    }

    @Override
    public void onFragmentAttached(boolean change) {
        iNeedToChangeToolbar = change;
        changeImage = 0;
    }

    private void getDrivers() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new CallbackDriverList(this, fragment));
    }


    @Override
    public void ClickedOnMap(String coordinatesStart, String coordinatesEnd) {
        Intent intent = new Intent(getApplicationContext(), MapJob.class);
        intent.putExtra("Start", coordinatesStart);
        intent.putExtra("End", coordinatesEnd);
        startActivityForResult(intent, ENTER_IN_MAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ENTER_IN_MAP && data != null) {
            Intent i = data;
            String start = i.getStringExtra("START");
            String end = i.getStringExtra("END");
            if (start != null && end != null) {
                ((EditText) fragment.getView().findViewById(R.id.input_kordinateUtovara)).setText(start.toString());
                ((EditText) fragment.getView().findViewById(R.id.input_kordinateIstovara)).setText(end.toString());
            }
        }
    }

    @Override
    public void calendarClicked(final View input) {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int _month = month + 1;
                ((EditText) input).setText(day + "/" + _month + "/" + year);
            }
        }, 2013, 2, 18);

        if (input.getId() == R.id.input_datumIstovara) dialog.setTitle("Datum istovara");
        else dialog.setTitle("Datum utovara");

        dialog.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

}