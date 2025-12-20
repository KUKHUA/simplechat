package com.kukhua.Handlers;
import org.json.JSONObject;

import io.fusionauth.http.server.HTTPResponse;

public interface IHandler {
    void handleRequest(JSONObject input, HTTPResponse res);
}
