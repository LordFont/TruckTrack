package entities;

import org.json.JSONObject;

/**
 * Created by roman on 2/5/18.
 */

public class FCMResponse {
    private int multicast_id;
    private short success;
    private short failure;
    private JSONObject results;

    public FCMResponse() {}

    public FCMResponse(int multicast_id, short success, short failure, JSONObject results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.results = results;
    }

    public int getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(int multicast_id) {
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

    public JSONObject getResults() {
        return results;
    }

    public void setResults(JSONObject results) {
        this.results = results;
    }
}
