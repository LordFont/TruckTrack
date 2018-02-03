package hr.foi.air.trucktrack.Helpers;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.trucktrack.Interface.FragmentLoaderListener;
import hr.foi.air.drivermodule.GridViewFragment;

/**
 * Created by Nikola on 2.2.2018..
 */

public class DriverFragmentGridLoader extends DriversFragmentLoader {

    public DriverFragmentGridLoader(FragmentLoaderListener fragmentLoaderListener) {
        super(fragmentLoaderListener);
    }

    @Override
    public void loadFragment(List<DriverModel> drivers) {
        fragmentLoaderListener.provideFragment(GridViewFragment.getInstance(drivers));
    }
}
