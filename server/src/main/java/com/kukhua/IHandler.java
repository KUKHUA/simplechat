package com.kukhua;
import org.json.JSONObject;

import io.fusionauth.http.server.HTTPResponse;

interface IHandler {
    void handleRequest(JSONObject input, HTTPResponse res);
}
