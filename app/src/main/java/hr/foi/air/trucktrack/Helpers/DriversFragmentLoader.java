package hr.foi.air.trucktrack.Helpers;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.trucktrack.Interface.FragmentLoaderListener;

/**
 * Created by Nikola on 2.2.2018..
 */

public abstract class DriversFragmentLoader {
    protected FragmentLoaderListener fragmentLoaderListener;

    public DriversFragmentLoader(FragmentLoaderListener fragmentLoaderListener) {
        this.fragmentLoaderListener = fragmentLoaderListener;
    }

    public abstract void loadFragment(List<DriverModel> drivers);
}
