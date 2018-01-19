package hr.foi.air.trucktrack.Helpers;

import android.view.View;
import android.widget.ProgressBar;

import com.wang.avi.AVLoadingIndicatorView;


/**
 * Created by Ivan on 19.1.2018..
 */

public abstract class DataLoadingProgress {
    public void hideLoadingScreen(AVLoadingIndicatorView avi) {
        avi.hide();
    }


}
