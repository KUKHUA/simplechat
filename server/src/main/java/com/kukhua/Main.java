package com.kukhua;

import io.fusionauth.http.server.HTTPListenerConfiguration;
import io.fusionauth.http.server.HTTPServer;
import io.fusionauth.http.server.HTTPHandler;

import org.json.JSONObject;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        HTTPHandler handler = (req, res) -> {
            Charset reqCharset = req.getCharacterEncoding();
            final byte[] bodyByteArray = req.getBodyBytes();
            JSONObject JSONBody = new JSONObject("{}");

            if(reqCharset == null){
                //we have no clue wtf the charset is.
                reqCharset = Charset.defaultCharset(); // just use whatever the system default is, ig.
            }


            try {
                final String body = new String(bodyByteArray, reqCharset);
                JSONBody = new JSONObject(body);

            } catch (Exception decodeError){
                res.setStatus(400);
                res.setStatusMessage("Error while decoding body: " + decodeError.getMessage());
                res.getOutputStream().close();
            }


            //if we got here, bodyText was decoded




            res.setStatus(200);
            res.setStatusMessage("It works!" + JSONBody.toString());
            res.getOutputStream().close();

        };

        HTTPServer server = new HTTPServer()
                .withHandler(handler).withListener(new HTTPListenerConfiguration(4242));
        server.start();
    }
}