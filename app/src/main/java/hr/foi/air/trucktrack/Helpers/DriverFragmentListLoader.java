package hr.foi.air.trucktrack.Helpers;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.trucktrack.Interface.FragmentLoaderListener;
import hr.foi.air.drivermodule.ListViewFragment;

/**
 * Created by Nikola on 2.2.2018..
 */

public class DriverFragmentListLoader extends DriversFragmentLoader {

    public DriverFragmentListLoader(FragmentLoaderListener fragmentLoaderListener) {
        super(fragmentLoaderListener);
    }

    @Override
    public void loadFragment(List<DriverModel> drivers) {
        fragmentLoaderListener.provideFragment(ListViewFragment.getInstance(drivers));
    }
}
