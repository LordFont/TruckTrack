package hr.foi.air.trucktrack;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.wang.avi.AVLoadingIndicatorView;
import java.util.ArrayList;


import entities.RouteIdRequest;
import entities.RouteModel;
import hr.foi.air.trucktrack.Callbacks.CallbackDriverJobs;

import hr.foi.air.trucktrack.Interface.CustomDialog;
import hr.foi.air.webservice.ApiClient;
import hr.foi.air.webservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverJobs extends AppCompatActivity implements CustomDialog {
    private ApiInterface apiService;
    AVLoadingIndicatorView avi;
    final int DIALOG_SET_DONE = 400;
    final int DIALOG_ACK_TO_JOB = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_jobs);

        initToolbar();

        //kreiranje dummy podataka u svrhu testiranja bez servera
        RouteModel routeJob1 = new RouteModel();
        RouteModel routeJob2 = new RouteModel();
        routeJob1.CreateTestData();
        routeJob2.CreateTestData();
        ArrayList<RouteModel> testList = new ArrayList<>();
        testList.add(routeJob1);

        //showFragment(DriverJobsFragment.getInstance(testList));
        Fragment fragment = DriverJobsFragment.getInstance(testList);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<RouteModel>> call = apiService.getDriverJobs("3"); //ovdje ide id korisnika, za testiranje uzet id 3
        call.enqueue(new CallbackDriverJobs(this,fragment));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard, menu);
        return true;
    }

    private void showFragment(Fragment f) {
        FragmentTransaction mTransactiont = getSupportFragmentManager().beginTransaction();
        mTransactiont.replace(R.id.main_container, f, f.getClass().getName());
        mTransactiont.commit();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.driver_jobs_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_view_headline_white_48px);
        getSupportActionBar().setTitle(getResources().getString(R.string.toolbarPoslovi));
    }

    @Override
    public void showCustomDialog(int type, final int idRuta) {
        //Toast.makeText(this, "idRuta: " + idRuta, Toast.LENGTH_SHORT).show();
        if(type == DIALOG_SET_DONE) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*DRIVER-DONE
                    * Klikom na ikonu done otvara se dialog. Pritiskom na “potvrdi” mora se pozvati
                    * api poziv za slanje id posla  koji je odrađen. Kada se vrati uspješan response
                    * vratiti će se email disponenta koji je nadređen vozaču koji će služiti za GMAIL
                    * api za slanje emaila. Nakon što se email bude poslao prikazat će se SnackBar
                    * poruka “Uspješno poslano” u protivnom “Neuspješno poslano”. Prije nego GMAIL
                    * API bude gotov, nakon dolaska prvog response-a refreshati listu poslova vozača !*/
                    RouteIdRequest request = new RouteIdRequest();
                    request.setmIdRuta(idRuta);
                    Call<Void> call = apiService.routeDone(request);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Ruta je uspješno odrađena!", Snackbar.LENGTH_LONG ).show();
                            } else {
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar mySnackbar3 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Ruta NIJE odrađena!", Snackbar.LENGTH_LONG );
                            mySnackbar3.show();
                        }
                    });
                }
            });
            dialog.setTitle("Potvrditi odrađenu rutu?");
            dialog.setMessage("Potvrdom odrađene rute, potvrda se šalje vašem disponentu kao dokaz istoga. Potvrdom ruta nestaje s vaše liste i u mogućnosti ste prihvatiti iduću rutu.");
            dialog.show();
        } else if (type == DIALOG_ACK_TO_JOB) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton("Odustani", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton("Potvrdi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   /*DRIVER-ACK
                   * Otvara se dialog. klikom na potvrdi se šalje api poziv za ažuriranje statusa na temelju id posla.
                   * te se mora ažutirati lista poslova ! */

                    RouteIdRequest request = new RouteIdRequest();
                    request.setmIdRuta(idRuta);
                    Call<Void> call = apiService.routeAccept(request);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.code() == 200) {
                                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "Ruta je uspješno potvrđena!", Snackbar.LENGTH_LONG );
                                mySnackbar.show();
                            } else {
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Snackbar mySnackbar3 = Snackbar.make(findViewById(R.id.driver_jobs_toolbar), "OnFailure", Snackbar.LENGTH_LONG );
                            mySnackbar3.show();
                            //nemam ideje stacu "onFailure"
                        }
                    });

                }
            });
            dialog.setTitle("Prihvatiti rutu?");
            dialog.setMessage("Potvrdom novo dodijeljene rute od strane disponenta ista će se pozivionirati na vrhu liste. Potvrdom dodijele nove rute potvrda se šalje vašem disponentu.");
            dialog.show();
        }
    }


}
