package hr.foi.air.trucktrack;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
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

import java.util.Calendar;
import java.util.List;

import entities.DriverModel;
import hr.foi.air.drivermodule.GridViewFragment;
import hr.foi.air.drivermodule.ListViewFragment;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverList;
import hr.foi.air.drivermodule.DriverSelectFromListInterface;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;

import static android.R.attr.id;
import static hr.foi.air.trucktrack.R.id.input_vozac;


public class NewJob extends AppCompatActivity implements
        ListViewFragment.ToolbarListener,
        NewJobFragment.ClickedOnMap,
        NewJobFragment.CalendarClicked,
        NewJobFragment.DriverForJob,
        NewJobFragment.PreviousActivity,
        DriverSelectFromListInterface {

    Fragment fragment;
    NewJobFragment firstFragment;
    int changeImage;
    boolean iNeedToChangeToolbar = false;
    final Integer ENTER_IN_MAP = 3003;
    private ApiInterface apiService;
    private List<DriverModel> drivers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        initToolbar();

        firstFragment = NewJobFragment.getInstance();
        showFragment(firstFragment);
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.job_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPosao));
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
            getSupportActionBar().setTitle(getResources().getString(R.string.toolbarVozaci));
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
        Intent intent = new Intent(getApplicationContext(), MapJobDisponent.class);
        intent.putExtra("Start", coordinatesStart);
        intent.putExtra("End", coordinatesEnd);
        startActivityForResult(intent, ENTER_IN_MAP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ENTER_IN_MAP && data != null) {
            Intent i = data;
            String end = i.getStringExtra("END");
            Log.d("STELLA", "end"+end);
            String [] positions = end.split(",");
            showFragment(firstFragment);
            ((NewJobFragment) firstFragment).setNewCoordinates(positions[0], positions[1]);
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

        if (input.getId() == R.id.input_datumIstovara) dialog.setTitle(getResources().getString(R.string.datum_istovara));
        else dialog.setTitle(getResources().getString(R.string.datum_utovara));

        dialog.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void setDriverForJob() {
        fragment = ListViewFragment.getInstance(drivers);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new CallbackDriverList(this, fragment));
    }



    @Override
    public void driverSelected(DriverModel driver) {
        showFragment(firstFragment);
        firstFragment.setDriverOnScreen(driver);
        Log.d("Prezime",driver.getPrezime()); //IVAN KOMENTAR - ISPISUJE ISPRAVNO PREZIME, ZNACI DA GA JE TU USPIO DOHVATITI
        /*DISPONENT-CLICKED
        * STELLA TU DOBIJEM PODATKE - > KAKO FRAGMENT INPUT (ID: input_vozac) UPDATE-ati??*/
    }

    @Override
    public void cancelCurrent() {
        onBackPressed();
    }

    @Override
    public void saveNewJob() {
        /*DISPONENT-JOB-SAVE
        * metoda kao parametar mora dobiti instancu klase koja će spremati sve podatke forme posla.
        * nakon uspješnog spremanja prikazati SnackBar sa porukom "Uspješno spremanje" u protivnom
        * "Neuspješno spremanje". Nakon spremanja pogled se mora prikazati na listi poslova. Moguće je
        * koristiti metodu cancelCurrent koja poziva prethodnu aktivnost koja je upravo DisponentJobs.class
        * koja nam i treba.
        * KOORDINATE MORAJU BITI SPREMLJENE KAO STRING (TEXT)*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}