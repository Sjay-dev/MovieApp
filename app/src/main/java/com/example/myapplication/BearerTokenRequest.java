package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class BearerTokenRequest extends JsonObjectRequest {
    private final String token;


    public BearerTokenRequest(int method, String url, JSONObject jsonRequest, String token,
                              Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest ,  listener, errorListener);
            this.token = token;
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + token);
        return headers;
    }
}
