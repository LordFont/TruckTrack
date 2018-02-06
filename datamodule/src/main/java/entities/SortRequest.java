package entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by roman on 2/6/18.
 */

public class SortRequest {
    @SerializedName("sort_by")
    private String sort_by;
    @SerializedName("field")
    private String field;

    public SortRequest(String sort_by, String field) {
        this.sort_by = sort_by;
        this.field = field;
    }

    public String getSort_by() {
        return sort_by;
    }

    public void setSort_by(String sort_by) {
        this.sort_by = sort_by;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
