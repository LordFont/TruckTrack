package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ivan on 21.11.2017..
 */

public class RouteModel {
    String mMjestoUtovara;
    String mMjestoIstovara;
    Date mUtovarDatum;
    Date mIstovarDatum;
    ArrayList<JobModel> poslovi;

    RouteModel() {
        poslovi = new ArrayList<JobModel>();
    }

    public void AddJob(String mjestoUtovara, String mjestoIstovara, Date utovarDatum, Date istovarDatum) {
        mMjestoUtovara = mjestoUtovara;
        mMjestoIstovara = mjestoIstovara;
        mUtovarDatum = utovarDatum;
        mIstovarDatum = istovarDatum;

        JobModel job = new JobModel(mjestoUtovara,mjestoIstovara,utovarDatum,istovarDatum);
        poslovi.add(job);
    }

    public void CreateTestData() {
        Random rnd = new Random();
        Date date1 = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        rnd = new Random();
        Date date2 = new Date(Math.abs(System.currentTimeMillis() - rnd.nextLong()));
        AddJob("Mjesto A", "Mjesto B", date1,date2);
        AddJob("Mjesto C", "Mjesto D", date1,date2);
    }
}
