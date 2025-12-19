package com.kukhua;

import io.fusionauth.http.server.HTTPResponse;

public class Util {
    public static void closeRes(int statusCode, String statusMessage, HTTPResponse res){
        res.setStatus(statusCode);
        res.setStatusMessage(statusMessage);

        try {
            res.getOutputStream().close();
        } catch(Exception exception){
            // Do nothing for now.
        }

    }
    
}
