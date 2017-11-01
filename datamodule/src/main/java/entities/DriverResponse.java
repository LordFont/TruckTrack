package entities;

import java.util.List;

/**
 * Created by Ivan on 1.11.2017..
 */

public class DriverResponse {
    public List<DriverModel> getResults() {
        return results;
    }

    public void setResults(List<DriverModel> results) {
        this.results = results;
    }

    private List<DriverModel> results;
}
