package hr.foi.air.trucktrack;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import entities.DriverModel;
import entities.JobModel;
import entities.RouteModel;
import hr.foi.air.webservice.ApiInterface;


public class NewJobFragment extends Fragment {

    static NewJobFragment instance = null;
    Fragment fragment;
    ImageView addDriver, addStart, addEnd;
    EditText inputStart, inputEnd, datumUtovara, datumIstovara;
    private ApiInterface apiService;
    private List<DriverModel> drivers = null;
    private static RouteModel rute = null;
    Button clearCoordinates, addJobs;
    EditText input_vozac, adresa_utovara;
    View view, viewBlock;
    ListView viewHolder;
    ArrayList<JobModel> jobs;
    ListAdapterJob adapterJob;
    Activity activity = null;

    public static NewJobFragment getInstance(RouteModel ruteData) {
        if (instance == null) {
            instance = new NewJobFragment();
            rute = ruteData;
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        jobs = new ArrayList<>();
        adapterJob = new ListAdapterJob(getContext(), R.layout.job_block_in_sublist, jobs, getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_job, container, false);
        viewHolder = view.findViewById(R.id.holderJob);
        viewHolder.setAdapter(adapterJob);


        addDriver = view.findViewById(R.id.addDriverIcon);
        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DriverForJob) getActivity()).setDriverForJob();
            }
        });

        input_vozac = view.findViewById(R.id.input_vozac);
        adresa_utovara = view.findViewById(R.id.input_utovar);

        view.findViewById(R.id.btnAddJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_vozac.getText().length() > 0 && adresa_utovara.getText().length() > 0) {
                    jobs.add(new JobModel("", ""));
                    rute.setPoslovi(jobs);
                    adapterJob.notifyDataSetChanged();
                } else {
                    ((DriverForJob) getActivity()).notFieldData();
                }
            }
        });


        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PreviousActivity) getActivity()).cancelCurrent();
            }
        });


        adresa_utovara.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rute.setMjestoUtovara(s.toString());
            }
        });

        rute.setMjestoUtovara(adresa_utovara.getText().toString());

        view.findViewById(R.id.btn_save_job).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validationPassed = true;
                if(adresa_utovara.getText().length() == 0 ) validationPassed = false;
                if(jobs.size() == 0 ) validationPassed = false;

                for(int i = 0; i < jobs.size(); i++) {
                    if(jobs.get(i).getMjestoIstovara().length() == 0) validationPassed = false;
                }

                ((DriverForJob) getActivity()).saveNewJob(validationPassed, rute);
            }
        });
        return view;
    }


    public interface ClickedOnMap {
        void ClickedOnMap(String coordinatesEnd);
    }

    public interface CalendarClicked {
        void calendarClicked(View input);
    }

    public interface DriverForJob {
        void setDriverForJob();

        void saveNewJob(boolean validationPassed, RouteModel rute);

        void notFieldData();
    }

    public interface PreviousActivity {
        void cancelCurrent();
    }

    public void setDriverOnScreen(final DriverModel driver) {
        Thread timer = new Thread() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((EditText) instance.getView().findViewById(R.id.input_vozac)).setText(driver.getIme() + " " + driver.getPrezime());
                    }
                });
            }
        };
        timer.start();

        rute.setKorisnikID(driver.getId());
        adapterJob.notifyDataSetChanged();
    }

    public void setNewCoordinates(String lan, String lon) {
        jobs.get(adapterJob.lastClicked).setLatitude(lan.toString());
        jobs.get(adapterJob.lastClicked).setLongitude(lon.toString());
        adapterJob.notifyDataSetChanged();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;

    }
}
