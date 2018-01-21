package hr.foi.air.trucktrack.Helpers;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import hr.foi.air.trucktrack.R;

import static hr.foi.air.trucktrack.R.id.input_vozac;

/**
 * Created by Ivan on 5.12.2017..
 */

public abstract class FragmentManager {
    protected FragmentActivity mCurrent;
    protected Fragment mNnext;
    protected void showFragment() {
        FragmentTransaction mTransactiont = mCurrent.getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, mNnext, mNnext.getClass().getName());
        mTransactiont.commit();
    }
}
