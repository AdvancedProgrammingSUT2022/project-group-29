package models;

import java.util.HashMap;

public class Request {
    String action;
    HashMap<String, String> params = new HashMap<>();

    public Request(String action, HashMap<String, String> params) {
        this.action = action;
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public HashMap<String, String> getParams() {
        return params;
    }
}
