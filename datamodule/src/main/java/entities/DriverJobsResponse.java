package entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 7.12.2017..
 */

public class DriverJobsResponse {
    @SerializedName("jobs")
    RouteModel driverRoutes;

    public RouteModel getDriverRoutes() {
        return driverRoutes;
    }

    public void setDriverRoutes(RouteModel driverRoutes) {
        this.driverRoutes = driverRoutes;
    }
}
