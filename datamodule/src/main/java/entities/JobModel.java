package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 21.11.2017..
 */

public class JobModel {
    String mMjestoUtovara;
    String mMjestoIstovara;
    Date mUtovarDatum;
    Date mIstovarDatum;

    JobModel(String mjestoUtovara, String mjestoIstovara, Date utovarDatum, Date istovarDatum) {
        mMjestoUtovara = mjestoUtovara;
        mMjestoIstovara = mjestoIstovara;
        mUtovarDatum = utovarDatum;
        mIstovarDatum = istovarDatum;
    }
}
