package entities;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by roman on 2/5/18.
 */

public class FCMResult {
    @SerializedName("multicast_id")
    private String multicast_id;
    @SerializedName("success")
    private short success;
    @SerializedName("failure")
    private short failure;
    @SerializedName("results")
    private Object results;

    public FCMResult() {}

    public FCMResult(String multicast_id, short success, short failure, JSONObject results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.results = results;
    }

    public String getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(String multicast_id) {
        this.multicast_id = multicast_id;
    }

    public short getSuccess() {
        return success;
    }

    public void setSuccess(short success) {
        this.success = success;
    }

    public short getFailure() {
        return failure;
    }

    public void setFailure(short failure) {
        this.failure = failure;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(JSONObject results) {
        this.results = results;
    }
}
