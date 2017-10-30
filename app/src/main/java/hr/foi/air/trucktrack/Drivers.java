package hr.foi.air.trucktrack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import hr.foi.air.drivermodule.DriversAdapter;
import hr.foi.air.drivermodule.DriversRVFragment;

import static android.R.attr.id;
import static android.R.attr.tag;
import static hr.foi.air.trucktrack.R.id.imageView;

public class Drivers extends AppCompatActivity {

    ArrayList<String> a;
    ImageView viewIcon;
    int changeImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
//        // Lookup the recyclerview in activity layout
//        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvDrivers);
//
//        // Initialize contacts
//        a = new ArrayList<>();
//        a.add("Ivan");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        a.add("Pero");
//        // Create adapter passing in the sample user data
//        DriversAdapter adapter = new DriversAdapter(this, a);
//        // Attach the adapter to the recyclerview to populate items
//        rvContacts.setAdapter(adapter);
//        // Set layout manager to position the items
//        rvContacts.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//        // That's all!

        displayView(0); // fragment at 0 position
        viewIcon = (ImageView) findViewById(R.id.viewIcon);
        changeImage = 0;
        viewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("App", "Im here");
                if (changeImage == 0) {
                    viewIcon.setImageResource(R.drawable.ic_grid_view);
                    changeImage = 1;
                }
                else {
                    viewIcon.setImageResource(R.drawable.ic_list_view);
                    changeImage = 0;
                }
            }
        });
    }

    public void displayView(int position) {
        switch (position) {
            case 0:
                //tvTitle.setText(getResources().getString(R.string.signin_tile));
                showFragment(new DriversRVFragment(), position);
                break;
        }
    }

    public void showFragment(Fragment fragment, int position) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();

        mTransactiont.replace(R.id.main_container, fragment, fragment.getClass().getName());
        mTransactiont.commit();
    }
}
