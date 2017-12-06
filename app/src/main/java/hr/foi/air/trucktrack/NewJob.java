package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import hr.foi.air.drivermodule.ListViewFragment;


public class NewJob extends AppCompatActivity implements ListViewFragment.ToolbarListener {

    Fragment fragment;
    boolean iNeedToChangeToolbar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);
        initToolbar();

        fragment = NewJobFragment.getInstance();
        showFragment(fragment);
        //fragment = ListViewFragment.getInstance(drivers);
        //showFragment(fragment);

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
        Toast toast = Toast.makeText(getApplicationContext(),"text", Toast.LENGTH_LONG);
        toast.show();
        iNeedToChangeToolbar = change;
    }
}