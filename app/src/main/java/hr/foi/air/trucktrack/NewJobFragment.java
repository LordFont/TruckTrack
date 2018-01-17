package hr.foi.air.trucktrack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import entities.DriverModel;
import hr.foi.air.webservice.ApiInterface;


public class NewJobFragment extends Fragment {

    static NewJobFragment instance = null;
    Fragment fragment;
    ImageView addDriver, addStart, addEnd;
    EditText inputStart, inputEnd, datumUtovara, datumIstovara;
    private ApiInterface apiService;
    private List<DriverModel> drivers = null;
    Button clearCoordinates;
    EditText input_vozac;
    View view;

    public static NewJobFragment getInstance() {
        if (instance == null) {
            instance = new NewJobFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_job, container, false);

        addDriver = view.findViewById(R.id.addDriverIcon);
        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DriverForJob) getActivity()).setDriverForJob();
            }
        });

        inputStart = view.findViewById(R.id.input_kordinateUtovara);
        inputEnd = view.findViewById(R.id.input_kordinateIstovara);

        clearCoordinates = view.findViewById(R.id.btnClearCoordinates);
        clearCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputStart.setText("");
                inputEnd.setText("");
            }
        });

        addStart = view.findViewById(R.id.btnStartMap);
        addStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClickedOnMap) getActivity()).ClickedOnMap(inputStart.getText().toString(), inputEnd.getText().toString());
            }
        });

        addEnd = view.findViewById(R.id.btnStartMap);
        addEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClickedOnMap) getActivity()).ClickedOnMap(inputStart.getText().toString(), inputEnd.getText().toString());
            }
        });

        datumUtovara = view.findViewById(R.id.input_datumUtovaraa);
        datumIstovara = view.findViewById(R.id.input_datumIstovara);

        datumUtovara.setInputType(0);
        datumIstovara.setInputType(0);

        datumUtovara.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) ((CalendarClicked) getActivity()).calendarClicked(view);

            }
        });

        datumIstovara.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b) ((CalendarClicked) getActivity()).calendarClicked(view);

            }
        });

        input_vozac = view.findViewById(R.id.input_vozac);

        view.findViewById(R.id.btn_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DriverForJob) getActivity()).saveNewJob();
            }
        });

        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PreviousActivity) getActivity()).cancelCurrent();
            }
        });
        return view;
    }

    public interface ClickedOnMap {
        void ClickedOnMap(String coordinatesStart, String coordinatesEnd);
    }

    public interface CalendarClicked {
        void calendarClicked(View input);
    }

    public interface DriverForJob {
        void setDriverForJob();
        void saveNewJob();
    }

    public interface PreviousActivity {
        void cancelCurrent();
    }

    //IVAN - ova metoda ažurira u ui threadu edittext, i zbog toga sada mozemo vidjeti na ekranu ažurirani box
    public void setDriverOnScreen(final DriverModel driver){
        Log.d("Prezime u fragmentu",driver.getPrezime());
        Thread timer = new Thread() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        input_vozac.setText(driver.getIme() + " " + driver.getPrezime());
                    }
                });
            }
        };
        timer.start();
    }
}
