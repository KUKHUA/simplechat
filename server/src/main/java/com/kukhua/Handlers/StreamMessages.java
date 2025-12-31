package com.kukhua.Handlers;

import org.json.JSONObject;

import com.kukhua.ClientStore;
import com.kukhua.Util;

import io.fusionauth.http.server.HTTPResponse;

public class StreamMessages implements IHandler {
    public void handleRequest(JSONObject input, HTTPResponse res){
        String channelName = "";
        try {
            channelName = input.getJSONObject("input").getString("channel_name");
        } catch(Exception exception) {
            Util.closeRes(400, "Unable to find channel_name in input object." + exception.getMessage(), res);
            return;
        }
        
        if(channelName.isBlank()){
            Util.closeRes(400, "channel_name is blank", res);
            return;
        } else if(channelName.length() > 15){
            Util.closeRes(400, "channel_name is too long.", res);
            return; 
        }

        res.setHeader("Content-Type", "text/event-stream");
        res.setHeader("Cache-Control", "no-cache");
        res.setHeader("Connection", "keep-alive");

        try {
            res.getOutputStream().write("data: connected\n\n".getBytes());
            res.getOutputStream().flush();
        } catch (Exception exception){} //TODO: Do something with the error.

        ClientStore.get().addClient(channelName, res);
    }
    
}
