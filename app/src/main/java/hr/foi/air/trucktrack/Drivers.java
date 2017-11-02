package hr.foi.air.trucktrack;

import android.graphics.Movie;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;
import entities.DriverResponse;
import hr.foi.air.drivermodule.DriversAdapter;
import hr.foi.air.drivermodule.DriversListViewFragment;
import hr.foi.air.drivermodule.DriversRVFragment;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.fragment;
import static android.R.attr.id;
import static android.R.attr.tag;
import static hr.foi.air.trucktrack.R.id.imageView;

public class Drivers extends AppCompatActivity {

    ArrayList<String> a;
    List <DriverModel> drivers;
    ImageView viewIcon;
    int changeImage;
    ApiInterface apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
//        // Lookup the recyclerview in activity layout
//        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvDrivers);

        //loading toolbar
        initToolbar();
        displayView(0); // fragment at 0 position
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drivers, menu);
        changeImage = 1;
        final MenuItem viewIcon = menu.findItem(R.id.viewIcon);
        viewIcon.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (changeImage == 1) {
                    item.setIcon(R.drawable.ic_dashboard_white_48px);
                    changeImage = 0;
                    displayView(1);
                }
                else {
                    item.setIcon(R.drawable.ic_view_list_white_48px);
                    changeImage = 1;
                    displayView(0);
                }
                return false;
            }
        });
        return true;
    }

    public void displayView(int position) {
        dohvatiVozace();
        DriversRVFragment gridfragment;
        DriversListViewFragment listfragment;
        switch (position) {
            case 0:
                gridfragment = new DriversRVFragment();
                showFragment(gridfragment, position);
                break;
            case 1:
                listfragment = new DriversListViewFragment();
                showFragment(listfragment, position);
                break;
        }
    }

    public void showFragment(Fragment fragment, int position) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();

        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        //loading toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.drivers_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle("Vozači");
    }

    private void dohvatiVozace() {
        Log.d("App", "Usao u funkciju");
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<DriverModel>> call = apiService.getDrivers();
        call.enqueue(new Callback<List<DriverModel>>() {
            @Override
            public void onResponse(Call<List<DriverModel>> call, Response<List<DriverModel>> response) {
                drivers = response.body();
                Log.d("App",""+response.body());
                for (DriverModel f: drivers) {
                    Log.d("App",f.getIme());
                }
            }

            @Override
            public void onFailure(Call<List<DriverModel>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }
}
