package com.kukhua;

import io.fusionauth.http.server.HTTPListenerConfiguration;
import io.fusionauth.http.server.HTTPServer;
import io.fusionauth.http.server.HTTPHandler;

import org.json.JSONObject;

import com.kukhua.Handlers.IHandler;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        HTTPHandler handler = (req, res) -> {
            Charset reqCharset = req.getCharacterEncoding();
            final byte[] bodyByteArray = req.getBodyBytes();
            JSONObject JSONBody = new JSONObject("{}");

            if(reqCharset == null){
                //we have no clue what the charset is.
                reqCharset = Charset.defaultCharset(); // just use whatever the system default is.
            }


            try {
                final String body = new String(bodyByteArray, reqCharset);
                JSONBody = new JSONObject(body);
                if(JSONBody.getString("request_type") == null){
                    throw new IllegalArgumentException("No request_type provided.");
                }

            } catch (Exception decodeError){
                Util.closeRes(400, "Error while decoding request" + decodeError.getMessage(), res);
            }


            //if we got here, bodyText was decoded
            // so, send it off to the handler
            IHandler requestedHandler = HandlerManger.get().findHandler(JSONBody.getString("request_type"));
            if(requestedHandler != null){
                requestedHandler.handleRequest(JSONBody, res);
            } else {
                Util.closeRes(400, "Provided request_type does not exist.", res);
            }

        };

        HTTPServer server = new HTTPServer()
                .withHandler(handler).withListener(new HTTPListenerConfiguration(4242));
        server.start();
    }
}