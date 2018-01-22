package hr.foi.air.trucktrack;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import entities.RouteIdRequest;
import entities.RouteModel;
import hr.foi.air.trucktrack.Callbacks.CallbackAllRoutes;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobs;
import hr.foi.air.trucktrack.Helpers.DataRefresher;
import hr.foi.air.trucktrack.Helpers.FragmentManager;
import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.trucktrack.Interface.OpenEditFormatInterface;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hr.foi.air.trucktrack.R.id.input_vozac;

public class DisponentJobs extends AppCompatActivity implements CustomDialog, OpenEditFormatInterface{
    private ApiInterface apiService;
    DisponentJobsFragment fragment;
    final int DIALOG_DELETE_JOB = 100;
    final int DIALOG_SAVE_JOB = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disponent_jobs);
        initToolbar();
        findViewById(R.id.addNewJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NewJob.class));
            }
        });

        ArrayList<RouteModel> testList = new ArrayList<>();
        fragment = DisponentJobsFragment.getInstance(testList);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getAllRoutes(); //ovdje ide id korisnika, za testiranje uzet id 3
        call.enqueue(new CallbackAllRoutes(this,fragment));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard, menu);
        return true;
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.driver_jobs_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPoslovi));
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    @Override
    public void showCustomDialog(int type,final int idRuta) {
        final Activity act = this;
        if(type == DIALOG_DELETE_JOB) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton("Obriši", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface dialog, int which) {
                    /* DISPONENT-DELETE
                        Na temelju ID posla slati api poziv za brisanje istog
                        Ako je response api poziva uspješan otvoriti SnackBar s porukom "Uspješno obrisano" u protivnom "Neuspješno poslano"
                    */
                    final RouteIdRequest request = new RouteIdRequest();
                    request.setmIdRuta(idRuta);
                    Call<Void> call = apiService.routeDelete(request);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Uspješno obrisano!", Snackbar.LENGTH_LONG );
                                DataRefresher dataRefresher = new DataRefresher();
                                dataRefresher.osvjeziPosloveDisponenta(act,fragment);
                                mySnackbar.show();
                            } else {
                                Snackbar mySnackbar2 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Neuspješno poslano!", Snackbar.LENGTH_LONG );
                                mySnackbar2.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar mySnackbar3 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Neuspješno poslano!", Snackbar.LENGTH_LONG );
                        }
                    });
                }
            });
            dialog.setTitle("Obrisati rutu?");
            dialog.setMessage("Potvrdom brisanja rute ruta više neće biti vidljiva niti imati mogućnost povratka iste. Ako je ruta dodjeljena vozaču, vozač je više neće vidjeti niti će biti aktivna!");
            dialog.show();
        } else if (type == DIALOG_SAVE_JOB){
            /* DISPONENT-SAVE
                Spremanje tako što se poziva api poziv i uspješnim spremanjem se otvara SnackBar sa porukom : "Uspješno spremljeno" u protivnom "Neuspješno spremljeno".
            */
        }
    }

    @Override
    public void clickedToEditForm(int routeId) {
        //startActivity(new Intent(getApplicationContext(), NewJob.class).putExtra("EDIT", routeId));
        Snackbar.make(findViewById(R.id.driver_jobs_toolbar), getResources().getString(R.string.action_no_implement), Snackbar.LENGTH_LONG ).show();
    }
}
